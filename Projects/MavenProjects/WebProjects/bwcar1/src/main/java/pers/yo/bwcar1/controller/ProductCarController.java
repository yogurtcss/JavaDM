package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;

import pers.yo.bwcar1.pojo.ProductWithBLOBs;
import pers.yo.bwcar1.service.ProductCarService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class ProductCarController {
    @Autowired
    private ProductCarService pcService;

    @RequestMapping("/sys/product/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDTO queryDTO){
        return pcService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/product/del")
    @ResponseBody
    public R delCar(@RequestBody List<Integer> ids){
        for( Integer id:ids ){
            pcService.delProduct(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/product/info/{id}")
    @ResponseBody
    public R findCarById(@PathVariable("id") Integer id){
        ProductWithBLOBs rst = pcService.findById(id);
        return R.ok().put("product",rst);
    }

    @RequestMapping("/sys/product/save")
    @ResponseBody
    public R addCarPro( @RequestBody ProductWithBLOBs productWithBLOBs ){
        int i = pcService.addProduct(productWithBLOBs);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping("/sys/product/update")
    @ResponseBody
    public R updateCarPro(@RequestBody ProductWithBLOBs productWithBLOBs){
        int i = pcService.updateProduct(productWithBLOBs);
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }

    //2020-02-11 22:53:23 鏖战结束！
}
