package pers.yo.bwcar1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Car;
import pers.yo.bwcar1.pojo.CarMake;
import pers.yo.bwcar1.service.CarMakeService;
import pers.yo.bwcar1.service.CarService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService cService;
    @Autowired
    private CarMakeService cmService;


    @RequestMapping("/sys/car/list")
    @ResponseBody
    public DataGridResult findCar(QueryDTO queryDTO){
        return cService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/car/del")
    @ResponseBody
    public R delCar(@RequestBody List<Integer> ids){
        for( Integer id : ids ){
            cService.delCar(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/car/typeall")
    @ResponseBody
    public R typeAll(){
        List<CarMake> all = cmService.findAll();
        return R.ok().put("sites",all);
    }

    @ResponseBody
    @RequestMapping("/sys/car/info/{id}")
    public R findById(@PathVariable("id") Integer id){
        Car byId = cService.findById(id);
        return R.ok().put("car",byId);
    }


    @RequestMapping("/sys/car/save")
    @ResponseBody
    public R addCar(@RequestBody Car car){
        int i = cService.addCar(car);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping("/sys/car/update")
    @ResponseBody
    public R updateCar(@RequestBody Car car){
        int i = cService.updateCar(car);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }
}