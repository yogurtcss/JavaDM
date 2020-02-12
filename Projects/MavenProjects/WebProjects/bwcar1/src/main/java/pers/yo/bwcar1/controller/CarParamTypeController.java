package pers.yo.bwcar1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarParamType;
import pers.yo.bwcar1.service.CarParamTypeService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarParamTypeController {

    @Autowired
    private CarParamTypeService cptService;

    @RequestMapping( "/sys/paramtype/list" )
    @ResponseBody
    public DataGridResult findCarParamType(QueryDTO queryDTO){
        return cptService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/paramtype/del" )
    @ResponseBody
    public R delCP(@RequestBody List<Integer> ids){
        for( Integer id:ids ){
            cptService.delCarParmType(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/paramtype/info/{id}" )
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        CarParamType rst = cptService.findById(id);
        return R.ok().put("paramtype",rst);
    }

    @RequestMapping( "/sys/paramtype/save" )
    @ResponseBody
    public R addCarParamType( @RequestBody CarParamType carParamType ){
        int i = cptService.addCarParamType(carParamType);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping( "/sys/param/update" )
    @ResponseBody
    public R updateCarParamType( @RequestBody CarParamType carParamType ){
        int i = cptService.updateCarParamType(carParamType);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }



}
