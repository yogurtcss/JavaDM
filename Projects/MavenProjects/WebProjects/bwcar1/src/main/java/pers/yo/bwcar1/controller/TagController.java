package pers.yo.bwcar1.controller;

//2020-02-11 18:37:18 鏖战

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Tag;
import pers.yo.bwcar1.service.TagService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping( "/sys/tag/list" )
    @ResponseBody
    public DataGridResult findTag( QueryDTO queryDTO ){
        return tagService.findByPage(queryDTO);
    }

    @RequestMapping( "/sys/tag/del" )
    @ResponseBody
    public R deleteTag( @RequestBody List<Integer> ids ){
        for( Integer id: ids ){
            tagService.delTag(id);
        }
        return R.ok();
    }

    @RequestMapping( "/sys/tag/save" )
    @ResponseBody
    public R saveTag( @RequestBody Tag tag ){
        int i = tagService.addTag(tag);
        return( i>0 ? (R.ok()):(R.error("添加失败")) ); //三目运算符
    }

    @RequestMapping( "/sys/tag/info/{id}" )
    @ResponseBody
    public R findById( @PathVariable("id") Integer id ){
        Tag rst_id = tagService.findById( id );
        return R.ok().put("tag",rst_id);
    }

    @RequestMapping( "/sys/tag/update" )
    @ResponseBody
    public R updateTag( @RequestBody Tag tag ){
        int i = tagService.updateTag( tag );
        return( i>0 ? (R.ok()):(R.error("更新失败")) );
    }

}
