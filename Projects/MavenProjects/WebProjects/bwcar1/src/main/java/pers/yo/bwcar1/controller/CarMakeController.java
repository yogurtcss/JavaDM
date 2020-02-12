package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarMake;
import pers.yo.bwcar1.service.CarMakeService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarMakeController {

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping( "/sys/make/list" )
    @ResponseBody
    public DataGridResult findMake(QueryDTO queryDTO){
        return carMakeService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/make/del" )
    @ResponseBody
    public R delMake(@RequestBody List<Integer> ids){
        for( Integer id : ids ){
            carMakeService.delCarMake(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/make/info/{id}" )
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        CarMake rst = carMakeService.findById(id);
        return R.ok().put("make",rst);
    }

    @RequestMapping( "/sys/make/save" )
    @ResponseBody
    public R addMake( @RequestBody CarMake carMake ){
        int i = carMakeService.addCarMake( carMake );
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping( "/sys/make/update" )
    @ResponseBody
    public R updateMake( @RequestBody CarMake carMake ){
        int i = carMakeService.updateCarMake(carMake);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }

}
