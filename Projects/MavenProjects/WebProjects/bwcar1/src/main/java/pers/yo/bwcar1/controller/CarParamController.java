package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParamType;
import pers.yo.bwcar1.pojo.CarParams;
import pers.yo.bwcar1.service.CarParamService;
import pers.yo.bwcar1.service.CarParamTypeService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarParamController {

    @Autowired
    private CarParamService cpService;
    @Autowired
    private CarParamTypeService cptService;

    @RequestMapping( "/sys/params/list" )
    @ResponseBody
    public DataGridResult findCarParams(QueryDTO queryDTO){
        return cpService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/params/del" )
    @ResponseBody
    public R deleteCarParams(@RequestBody List<Integer> ids){
        for( Integer id : ids ){
            cpService.delCarParams(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/params/typeall" )
    @ResponseBody
    public R typeAll(){
        List<CarParamType> all = cptService.findAll();
        return R.ok().put("sites",all);
    }

    @RequestMapping( "/sys/params/info/{id}" )
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        CarParams cp = cpService.findById(id);
        return R.ok().put("params",cp);
    }

    @RequestMapping( "/sys/params/save" )
    @ResponseBody
    public R addCarParams( @RequestBody CarParams carParams ){
        int i = cpService.addCarParams(carParams);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping( "/sys/params/update" )
    @ResponseBody
    public R updateCarParams( @RequestBody CarParams carParams ){
        int i = cpService.updateCarParams( carParams );
        return( i>0 ? (R.ok()):(R.error()) );
    }
}
