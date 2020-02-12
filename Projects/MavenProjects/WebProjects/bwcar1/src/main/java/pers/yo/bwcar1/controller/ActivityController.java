package pers.yo.bwcar1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.ActivityDTO;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.service.ActivityService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/sys/activity/list")
    @ResponseBody
    public DataGridResult findAct(QueryDTO queryDTO){
        return activityService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/activity/del")
    @ResponseBody
    public R delAct(@RequestBody List<Long> ids){
        for( Long id : ids ){
            activityService.delActivity(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/activity/info/{id}")
    @ResponseBody
    public R findActById(@PathVariable("id") Long id){
        ActivityDTO rst = activityService.findById(id);
        return R.ok().put("activity",rst);
    }

    @RequestMapping("/sys/activity/save")
    @ResponseBody
    public R addAct(@RequestBody ActivityDTO activityDTO){
        int i = activityService.addActivity(activityDTO);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping("/sys/activity/update")
    @ResponseBody
    public R updateAct(@RequestBody ActivityDTO activityDTO){
        int i = activityService.updateActivity(activityDTO);
        return i>0?R.ok():R.error("修改失败");
    }
}
