package pers.yo.start;


import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.service.ProcessService;
import pers.yo.service.impl.DownloadServiceImpl;
import pers.yo.service.impl.YOUKUProcessServiceImpl;
import pers.yo.service.impl.YOUKUProcessServiceImpl2;

//电视剧爬虫执行入口类
public class StartDSJCount {
    private DownloadService ds;
    private ProcessService ps;

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

    public Page downloadPage(String url ){ //下载页面
        //调用 this的下载的服务实例对象
        return this.ds.download(url);
    }
    public void processPage(Page page){ //解析页面
        this.ps.process(page);
    }



    public static void main(String[] args) {
        StartDSJCount dsj = new StartDSJCount(); //new一个实体类
        dsj.setDs( new DownloadServiceImpl() ); //设置下载服务实例对象
        //dsj.setPs( new YOUKUProcessServiceImpl()); //设置解析服务的实例对象，优酷的用优酷的实例，搜狐的用搜狐的实例
        dsj.setPs( new YOUKUProcessServiceImpl2() ); //重构后的代码：使用俺的自定义工具类嗷！

        String url = "https://list.youku.com/show/id_z9cd2277647d311e5b692.html?spm=a2htv.20005143.m13050845531.5~5~1~3~A&from=y1.3-tv-index-2640-5143.40177.1-1";
        Page page =  dsj.downloadPage(url); //下载页面
        dsj.processPage(page); //解析页面
        //System.out.println( page.getContent() );
    }
}
