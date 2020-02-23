package pers.yo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.utils.IsChineseInUrlUtil;
import pers.yo.utils.LoadPropertiesUtil;
import pers.yo.utils.ParseSearchRstUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class backup {
    public void processAsyncPage( DownloadService downloadService ) {
        String urlList_async = LoadPropertiesUtil.getPropsFromYouKuByKeyName("urlList_async"); //初始网址
        int from = Integer.parseInt( LoadPropertiesUtil.getPropsFromYouKuByKeyName("from") ); //异步加载 开始页数from
        int to = Integer.parseInt( LoadPropertiesUtil.getPropsFromYouKuByKeyName("to") ); //异步加载 结束页数to

        for( int i=from; i<=to; i++ ){
            String next_urlList_async = urlList_async + i; //构造下一个异步加载的网址
            System.out.println( next_urlList_async ); //输出一哈
            Page pageList_next = downloadService.download( next_urlList_async ); //下载 “下一个页面pageList”，不是详情页

            if( pageList_next!=null ){ //如果下一个电视剧列表内容不为空，才接着往下做
                //System.out.println( page_next.getContent() );
                //---1.把【JSON格式的响应数据】转为JSON对象
                JSONObject content_JObject = JSONObject.parseObject( pageList_next.getContent() );
                //---1.1 获取 键名为data的值：目前是字符串类型[ {..JSON格式..},{..JSON格式..},{..JSON格式..}, ]
                String data_str = content_JObject.getString("data");
                //System.out.println( data_str );
                // 键名为data的值，目前是字符串类型[ {..JSON格式..},{..JSON格式..},{..JSON格式..}, ]

                //---2.将字符串类型的data_str 转换为 集合List<泛型?>
                /* 注意，因为原本 [ {..JSON格式..},{..JSON格式..},{..JSON格式..}, ] 这里面已经是标准的JSON格式字符串了！
                 * 所以我们这里直接将 {..JSON格式..}字符串转为 JSON对象！
                 * 所以 List的泛型是 JSON对象！ 即 List<泛型JSONObject>
                 *  */
                List<JSONObject> list = JSON.parseArray( data_str,JSONObject.class ); //将字符串类型的data_str 转换为 集合List<泛型?>，
                for( JSONObject oneJObject: list ){ //遍历每一个JSON对象

                    /* 2020-02-22 21:08:28
                     * 以下的代码部分。我傻了：我以为videoLink的值就是详情页，不是！！结果是“搜索结果页!”
                     * JSON数据就有 videoId，我直接在配置文件properties中整一个baseUrl，拼上videoId就是详情页了啊！
                     *
                     * 2020-02-22 21:50:46
                     * 最新问题。JSON数据中：
                     * videoLink值那些带有中文名的url，baseUrlDetail与videoId拼串后，并不是它的详情页，而是404！
                     *
                     * 需要先 urlEncode之后，再进去url编码后的“搜索结果页”，再次爬取真正的详情页url！我佛了！
                     *  */

                    //原生url中 不支持中文，需对url的中文部分进行url编码
                    String oneUrl = oneJObject.getString( "videoLink" ); //取出每一条url，判断是否有中文字符(和空格)
                    if( IsChineseInUrlUtil.isChinese(oneUrl) ){ //结果为true，说明url夹杂着中文字符和空格，需要单独处理！
                        //System.out.println( oneUrl ); //输出看看 url夹杂着中文字符和空格
                        /* 我佛了！
                        //so.youku.com/search_video/q_迷 粤语版
                        //so.youku.com/search_video/q_EU超时任务 粤语版

                        注意规律：以 _ 下划线为分隔符split，中文和空格的部分chinese_space在 下标为2的元素
                        *  */
                        String chinese_space = oneUrl.split("_")[2];
                        //System.out.println( chinese_space );
                        try {
                            String encoded_part = URLEncoder.encode( chinese_space,"UTF-8" ); //转码后的中文部分part
                            //System.out.println( "中文是："+chinese_space+"——————URLEncoder.encode转换后："+encoded_part );
                            //重新拼接 URLEncoder.encode编码转换后的 正确的URL
                            String urlEncoded = "https:" +
                                    oneUrl.split("_")[0] + "_" +
                                    oneUrl.split("_")[1] + "_" +
                                    encoded_part; //注意 拼接时，不要漏掉了原本的 _ 下划线嗷！！
                            //System.out.println( "URLEncoder.encode转换后的url："+urlEncoded );

                            //终于开始下载页面了！
                            //下载页面时，同时把这个下载的网址、正确的编码后的url 设置set进了page对象中！
                            Page page_searchRst = downloadService.download( urlEncoded ); //这不是详情页啊兄弟，是“搜索结果页”！
                            //this.processDetailPage( page_urlEncoded ); //这不是详情页啊兄弟，是“搜索结果页”！

                            //在“搜索结果页”中，再次爬取真正的详情页链接！
                            System.out.println( ParseSearchRstUtil.parseSearchRst( page_searchRst ) );

                            //Page page_realDetail = dsj.ds.download( real_urlDetail ); //爬取真正的详情页正文！
                            //dsj.ps.processDetailPage( page_realDetail ); //爬取真正的详情页
                            //dsj.ss.store( page_realDetail );
                            //System.out.println( page_realDetail ); //直接输出一下
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }else{//否则，结果为false，说明url夹杂着中文字符和空格，拼串baseUrlDetail，开爬！
                        String videoId = oneJObject.getString( "videoId" );
                        //System.out.println( videoId );
                        String baseUrlDetail = LoadPropertiesUtil.getPropsFromYouKuByKeyName("baseUrlDetail"); //基本的详情页url
                        String urlDetail = baseUrlDetail + videoId + ".html"; //拼上后缀.html
                        //System.out.println( urlDetail );

                        //前面铺垫了160行，终于要开始整活了！
                        Page pageDetail = downloadService.download( urlDetail ); //下载详情页的 正文
                        //this.processDetailPage( pageDetail ); //解析详情页
                        //dsj.ss.store( pageDetail );
                        System.out.println( pageDetail );
                    }


                    /* 2020-02-22 21:08:28
                     * 以上的代码部分。我傻了：我以为videoLink的值就是详情页，不是！！结果是“搜索结果页!”
                     * JSON数据就有 videoId，我直接在配置文件properties中整一个baseUrl，拼上videoId就是详情页了啊！
                     *  */

                    /* 2020-02-22 21:50:46
                     * 最新问题。JSON数据中：
                     * videoLink值那些带有中文名的url，baseUrlDetail与videoId拼串后，并不是它的详情页，而是404！
                     *
                     * 需要先 urlEncode之后，再进去url编码后的“搜索结果页”，再次爬取真正的详情页url！我佛了！
                     *  */
                }
            }else{ //如果下一个电视剧列表内容为空，跳出循环
                System.out.println( "当前列表内容为空，程序终止。" );
                break;
            }
        }
    }
}
