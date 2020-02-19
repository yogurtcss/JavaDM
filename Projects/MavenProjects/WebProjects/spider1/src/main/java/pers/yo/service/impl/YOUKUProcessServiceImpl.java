package pers.yo.service.impl;

import org.htmlcleaner.HtmlCleaner;
import pers.yo.entity.Page;
import pers.yo.service.ProcessService;

public class YOUKUProcessServiceImpl implements ProcessService {
    @Override
    public void process(Page page) { //爬虫爬到的页面对象Page：里面混杂一堆HTML标签
        String content = page.getContent(); //获取页面对象的正文内容：一堆HTML标签，需要用正则表达式整活

        HtmlCleaner cleaner = new HtmlCleaner();

    }
}
