package pers.yo.service.impl;

import pers.yo.entity.Page;
import pers.yo.service.DownloadService;
import pers.yo.utils.PageDownloadUtil;

//HttpClient 页面下载实现类
public class DownloadServiceImpl implements DownloadService {
    @Override
    public Page download(String url) {
        Page page = new Page();
        page.setContent(PageDownloadUtil.getPageContent(url)); //设置页面正文内容
        page.setUrl(url); //下载页面时，同时把这个下载的网址url 设置set进了page对象中！
        return page;
    }
}
