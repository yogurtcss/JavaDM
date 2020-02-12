package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.service.TagService;
import pers.yo.bwcar1.utils.R;

@Controller
public class EchartsController {

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping("/sys/echarts/line")
    public R findLine(){
        return tagService.findLineData();
    }

    @ResponseBody
    @RequestMapping("/sys/echarts/bar")
    public R findBar(){
        return tagService.findBarData();
    }

    @RequestMapping( "/sys/echarts/pie" )
    @ResponseBody
    public R findPie(){
        return tagService.findPieData();
    }

}
