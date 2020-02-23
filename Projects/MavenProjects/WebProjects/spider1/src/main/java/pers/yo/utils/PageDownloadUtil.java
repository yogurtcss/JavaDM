package pers.yo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.service.ProcessService;
import pers.yo.service.impl.DownloadServiceImpl;
import pers.yo.service.impl.YOUKUProcessServiceImpl;

import java.io.IOException;

public class PageDownloadUtil {

    public static String getPageContent(String url){
        //---1.创建httpclient的实例对象 —— CloseableHttpClient类
        // 通过builder来创建client实例对象，而不能直接 new HttpClient();
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();

        //---2.构造请求方法：get或post请求
        HttpGet req = new HttpGet( url ); //使用的实例对象 HttpGet 或HttpPost
        //设置Header模拟浏览器行为
        req.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.81 Safari/537.36");
        String content = null; //---2.1 定义 待爬取的正文
        try{
            //---3.提交 get或post请求，接收返回值-响应数据 CloseableHttpResponse实例对象
            CloseableHttpResponse res = client.execute( req );
            //---4.取出响应的实体 HttpEntity
            HttpEntity entity = res.getEntity();
            //---5.使用官方提供的EntityUtils 处理 响应的实体数据
            content = EntityUtils.toString( entity );
        } catch( IOException e ){
            e.printStackTrace();
        }
        return content;
    }

//    public static void main(String[] args) {
//        String url = "https://list.youku.com/show/id_z9cd2277647d311e5b692.html?spm=a2htv.20005143.m13050845531.5~5~1~3~A&from=y1.3-tv-index-2640-5143.40177.1-1";
//        DownloadService ds = new DownloadServiceImpl(); //接口回调
//        Page page = ds.download( url );
//        //System.out.println( page.getContent() );
//        ProcessService ps = new YOUKUProcessServiceImpl();
//        ps.process( page );
//    }

}
