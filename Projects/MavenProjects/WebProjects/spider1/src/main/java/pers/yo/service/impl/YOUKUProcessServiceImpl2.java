package pers.yo.service.impl;

import pers.yo.entity.Page;
import pers.yo.service.ProcessService;
import pers.yo.utils.LoadPropertiesUtil;
import pers.yo.utils.RegExHtmlUtil;

//使用properties配置文件，与自定义工具类嗷
public class YOUKUProcessServiceImpl2 implements ProcessService {
    @Override
    public void process(Page page) { //开始发大招了！

        //剧集别名
        String aliasRst = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("aliasRegEx"),  1
        );
        //上映时间
        String showTimeRst = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("showTimeRegEx"),  1
        );
        //主演名称
        String mainActor = RegExHtmlUtil.getFieldByRegEx(
                page,  LoadPropertiesUtil.getPropsFromYouKuByKeyName("mainActorRegEx"),  1
        );

        System.out.println( "剧集别名："+aliasRst );
        System.out.println( "上映时间："+showTimeRst );
        System.out.println( "主演名称："+mainActor );
    }
}
