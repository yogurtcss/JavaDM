package pers.yo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.service.ProcessService;
import pers.yo.utils.*;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

//使用properties配置文件，与自定义工具类嗷
public class YOUKUProcessServiceImpl2 implements ProcessService { //解析页面，并将解析结果封装进对象中

    @Override //解析详情页
    public void processDetailPage(Page page) {
        //剧集id
        String tvId = RegExHtmlUtil.getTvIdFromUrlByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("tvIdRegEx"),  1
        ); //这家伙需要特殊处理split一下
        //page.setAlias( aliasRst.split("：")[1] ); //把匹配的结果 “封装” 进对象中
        page.setTvId( tvId );
        //System.out.println( "tvId："+tvId );

        //剧集名
        String tvName = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("tvNameRegEx"),  1
        );
        page.setTvName( tvName );
        //System.out.println( "tvName："+tvName );

        //剧集热度
        String heat = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("heatTextRegEx"),  1
        );//返回值：热度(空格)7067
        /* String 的 split 方法支持正则表达式；
        正则表达式 \s 表示匹配任何空白字符，+ 表示匹配一次或多次。
        *  */
        //System.out.println( heat );
        if( heat!=null ){
            page.setHeatText( heat.split("\\s")[1] ); // 以空格\\s (反斜杠又要转义！)为分隔符
        }
        //System.out.println( "heat："+heat.split("\\s")[1] ); // 以空格\\s (反斜杠又要转义！)为分隔符

        //剧集评分，用到了我自定义的拼接匹配结果的工具类嗷！
        String score = ConcatMatchRstUtil.concatMatchRst( page,1,3, "scoreRegEx" );
        page.setScore( score );
        //System.out.println( "score："+score );

        //导演、主演、概要，这3个属性太难拿了，我暂时不拿！2020-02-21 21:49:42
        //导演和主演
        String director_actor = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("director_actor_RegEx"),  1
        );
//        if( director_actor.split(",").length>0 ){
//            page.setDirector( director_actor.split(",")[1] ); //split后，下标为1 的元素，就是导演！
//        }else{
//            page.setDirector(null);
//        }

        //System.out.println( "director_actor："+director_actor );
        //"微微一笑很倾城 第1集 一见倾心主动邀结侠侣 微微一笑很倾城,林玉芬,杨洋,郑爽,毛晓彤,电视剧" /

//        if( director_actor.split(",").length>2 ){ //如果切割出来的东西多于2，说明有主演
//            StringBuilder sb = new StringBuilder();
//            for( int i=2; i<=4; i++ ){ //split后，下标2~4 的元素，就是主演们！
//                sb.append( director_actor.split(",")[i] );
//                if( i!=4 ){
//                    sb.append(",");
//                }
//            }
//            page.setMainActor( new String(sb) );
//        }else{
//            page.setMainActor( null );
//        }

        //概要
        String info = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("infoRegEx"),  1
        );

        //System.out.println( "原本的info："+info );
//        String info_split_final = info.split(":")[3].split("\"")[0]; //最后真正的info
//        //System.out.println( "分割后的info："+ info_split_final ); //我佛了，连续切割！有点恶心！
//        page.setInfo( info_split_final  );

        //标签
        String tags = ConcatMatchRstUtil.concatMatchRst_split( page,1,2,"tagsRegEx" );
        page.setTag( tags );
        //System.out.println( "tags："+tags );
    }


    @Override //把 列表页中的详情url全抓出来，放入 redis url仓库中，然后直接 处理详情页得了！
    public void addDetailUrlIntoRedis(String urlList, DownloadService downloadService) {
        Page pageList_next = downloadService.download( urlList ); //下载 “下一个页面pageList”，不是详情页

        //System.out.println( pageList_next );

        //如果下一个电视剧列表内容不为空，才接着往下做
        if( pageList_next!=null ){
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

                        //把编码后的url，放入 redis url仓库中
                        JedisPoolUtils.add( JedisPoolUtils.lowKey,urlEncoded ); //低优先级-详情url

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }else{//否则，结果为false，说明url夹杂着中文字符和空格，拼串baseUrlDetail，开爬！
                    String videoId = oneJObject.getString( "videoId" );
                    //System.out.println( videoId );
                    String baseUrlDetail = LoadPropertiesUtil.getPropsFromYouKuByKeyName("baseUrlDetail"); //基本的详情页url
                    String urlDetail = baseUrlDetail + videoId + ".html"; //拼上后缀.html
                    //System.out.println( urlDetail );

                    //把拼接后的url，放入 redis url仓库中
                    JedisPoolUtils.add( JedisPoolUtils.lowKey,urlDetail ); //低优先级-详情url
                }
            }
        }else{ //如果下一个电视剧列表内容为空，跳出循环
            System.out.println( "列表页url中的详情url已经全部添加到Redis url仓库中，进入下一步..." );
        }
    }

}
