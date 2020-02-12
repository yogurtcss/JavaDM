package pers.yo.bwcar1.controller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.CarMake;
import pers.yo.bwcar1.pojo.CarManufacturer;
import pers.yo.bwcar1.service.CarMakeService;
import pers.yo.bwcar1.service.CarMenuFacturerService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class CarMenuFacturerController {

    @Autowired
    private CarMenuFacturerService cmfService;
    @Autowired
    private CarMakeService cmService;

    //我佛了，前端的menu写错了！不管了
    @RequestMapping( "/sys/manufacturer/list" )
    @ResponseBody
    public DataGridResult findCarMenu(QueryDTO queryDTO){
        return cmfService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/manufacturer/del" )
    @ResponseBody
    public R delCarMenu(@RequestBody List<Integer> ids){
        for( Integer id:ids ){
            cmfService.delCarMenu(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/manufacturer/typeall" )
    @ResponseBody
    public R carType(){
        List<CarMake> all = cmService.findAll();
        return R.ok().put("sites",all);
    }

    @RequestMapping( "/sys/manufacturer/info/{id}" )
    @ResponseBody
    public R findById(@PathVariable("id") Integer id){
        CarManufacturer rst = cmfService.findById(id);
        return R.ok().put("manufacturer",rst);
    }

    @RequestMapping( "/sys/manufacturer/save" )
    @ResponseBody
    public R addCarMenu( @RequestBody CarManufacturer carManufacturer ){
        int i = cmfService.addCarMenuFacturer(carManufacturer);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping( "/sys/manufacturer/update" )
    @ResponseBody
    public R updateCarMenu( @RequestBody CarManufacturer carManufacturer ){
        int i = cmfService.updateCarManuFacturer(carManufacturer);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }

}
