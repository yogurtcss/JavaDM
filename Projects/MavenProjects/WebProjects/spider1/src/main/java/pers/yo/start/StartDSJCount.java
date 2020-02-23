package pers.yo.start;


import com.alibaba.fastjson.JSONObject; //先转为JSON对象
import com.alibaba.fastjson.JSON; //将JSON格式的字符串转为数组

import com.sun.tools.javadoc.Start;
import org.apache.commons.lang.StringUtils;
import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.service.ProcessService;
import pers.yo.service.RedisRepositoryService;
import pers.yo.service.StoreService;
import pers.yo.service.impl.*;
import pers.yo.utils.*;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//电视剧爬虫执行入口类
public class StartDSJCount {
    private DownloadService ds; //下载服务
    private ProcessService ps; //解析服务
    private StoreService ss; //存储服务

    private Queue<String> urlList_queue = new ConcurrentLinkedDeque<>(); //电视剧列表url的队列
    private RedisRepositoryService rrs; //redis仓库服务类

    public void setRrs(RedisRepositoryService rrs) {
        this.rrs = rrs;
    }

    public DownloadService getDs() {
        return ds;
    }
    public void setDs(DownloadService ds) {
        this.ds = ds;
    }
    public ProcessService getPs() {
        return ps;
    }
    public void setPs(ProcessService ps) {
        this.ps = ps;
    }
    public StoreService getSs() {
        return ss;
    }
    public void setSs(StoreService ss) {
        this.ss = ss;
    }

    private ExecutorService newFixedThreadPool = Executors.newFixedThreadPool( //线程数
            Integer.parseInt( LoadConfigPropertiesUtil.getPropsFromConfigByKeyName("threadNum") )
    );

    public Page downloadPage(String url ){ //调用 this的下载的服务实例对象，下载页面
        return this.ds.download(url);
    }

    public void processDetailPage(Page page){ //解析单页面
        this.ps.processDetailPage(page);
    }
    public void addDetailUrlIntoRedis( String urlList, DownloadService downloadService ){
        this.ps.addDetailUrlIntoRedis(urlList,downloadService);
    }

    public void storePage(Page page){ //存储页面
        this.ss.store(page);
    }

    public void startSpider(){

        while(true){ //往 redis url仓库中塞url
            //String url = urlList_queue.poll(); //从队列中拉出一个url
            final String url = JedisPoolUtils.poll(JedisPoolUtils.lowKey);
            //System.out.println( url );
            if( StringUtils.isNotBlank(url) ){
                newFixedThreadPool.execute(new Runnable() { //开启多线程
                    @Override
                    public void run() {
                        System.out.println( "当前是第 "+Thread.currentThread().getId()+" 个线程在工作：" );
                        Page page = StartDSJCount.this.downloadPage( url ); //下载当前列表的url，需要改为final！！
                        StartDSJCount.this.ps.processDetailPage(page); //要加上 StartDSJCount.this.XXX
                        StartDSJCount.this.ss.store(page);
                        System.out.println( page );
                        ThreadUtil.sleep( //爬取一下，我就休息几秒
                                Long.parseLong( LoadConfigPropertiesUtil.getPropsFromConfigByKeyName("millions") )
                        );
                    }
                });
            }else{
                //System.out.println( "队列中的 列表urlList 解析完毕" );
                System.out.println( "所有页面解析完毕。" );
                break;
            }
        }
    }


    public static void main(String[] args) {
        StartDSJCount dsj = new StartDSJCount(); //new一个实体类
        dsj.setDs( new DownloadServiceImpl() ); //设置下载服务实例对象
        //dsj.setPs( new YOUKUProcessServiceImpl()); //设置解析服务的实例对象，优酷的用优酷的实例，搜狐的用搜狐的实例
        dsj.setPs( new YOUKUProcessServiceImpl2() ); //重构后的代码：使用俺的自定义工具类嗷！
        dsj.setSs( new StoreServiceImpl() ); //设置具体的实现类

        dsj.setRrs( new RedisRepositoryServiceImpl()); //设置Redis仓库实现类

        //2016年的微微一笑很倾城
        //String url = "https://list.youku.com/show/id_z9cd2277647d311e5b692.html?spm=a2htv.20005143.m13050845531.5~5~1~3~A&from=y1.3-tv-index-2640-5143.40177.1-1";
        //2020年的微微一笑很倾城 XMTY5NDg2MzY5Ng==
        //2020年的天赐的声音 第一季 XNDUxNTUzODI1Ng==
//        String url = LoadPropertiesUtil.getPropsFromYouKuByKeyName("urlDetail");
//        Page page = dsj.ds.download(url);
//        dsj.ps.processDetailPage(page);
//        //System.out.println( page.getContent() );
//        System.out.println( page );


        //以上是解析某个具体详情页面的做法嗷！下面我要解析【异步加载数据的电视剧列表了！】
        //dsj.ps.processAsyncPage( dsj.ds );
        int from = Integer.parseInt( LoadPropertiesUtil.getPropsFromYouKuByKeyName("from") );
        int to = Integer.parseInt( LoadPropertiesUtil.getPropsFromYouKuByKeyName("to") );
        String urlList_async = LoadPropertiesUtil.getPropsFromYouKuByKeyName("urlList_async");
        for( int i=from; i<=to; i++ ){ //构造【异步加载的】url，放进队列中
            String next_urlList_async = urlList_async + i;
            //dsj.urlList_queue.add( next_urlList_async ); //不用队列了
            dsj.rrs.add_HighLevelUrl_IntoRedis( next_urlList_async );
            dsj.ps.addDetailUrlIntoRedis( next_urlList_async,dsj.ds ); //同时，把这列表url中的所有详情url添加到redis url仓库中
        }
        dsj.startSpider();

    }


}
