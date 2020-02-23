package pers.yo.service;

import pers.yo.entity.Page;

//页面解析接口
public interface ProcessService {

    //解析单页面
    public abstract void processDetailPage( Page page );

    /* 页面数据对象page，
    * 涉及到异步加载多页：开始页数from，终止页数to
    *
    * 2020-02-23 13:43:14
    * 因为用了redis url仓库，现在不需要这个方法了
    * */
    // public abstract void processAsyncPage( String url_queue,DownloadService downloadService );

    //第一步爬取页面，把所有详情url放进redis url仓库中
    public abstract void addDetailUrlIntoRedis( String url, DownloadService downloadService );
}
