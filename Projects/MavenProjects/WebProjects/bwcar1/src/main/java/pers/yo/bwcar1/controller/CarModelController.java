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
import pers.yo.bwcar1.pojo.CarModel;
import pers.yo.bwcar1.service.CarMakeService;
import pers.yo.bwcar1.service.CarModelService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarModelController {

    @Autowired
    private CarModelService cmdService;
    @Autowired
    private CarMakeService cmkService;

    @RequestMapping( "/sys/model/list" )
    @ResponseBody
    public DataGridResult findCarPage(QueryDTO queryDTO){
        return cmdService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/model/del" )
    @ResponseBody
    public R deleteCarModel(@RequestBody List<Integer> ids){
        for( Integer id:ids ){
            cmdService.delCarModel(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/model/typeall" )
    @ResponseBody
    public R typeAll(){
        List<CarMake> all = cmkService.findAll();
        return R.ok().put("sites",all);
    }


    @RequestMapping("/sys/model/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        CarModel rst = cmdService.findById(id);
        return R.ok().put("model",rst);
    }

    @RequestMapping( "/sys/model/save" )
    @ResponseBody
    public R addCarModel( @RequestBody CarModel carModel ){
        int i = cmdService.addCarModel(carModel);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping("/sys/model/update")
    @ResponseBody
    public R updateCarModel( @RequestBody CarModel carModel ){
        int i = cmdService.updateCarModel(carModel);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }

}
