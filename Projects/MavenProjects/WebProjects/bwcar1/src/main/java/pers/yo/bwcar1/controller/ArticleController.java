package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pers.yo.bwcar1.dto.ArticleDTO;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.service.ArticleService;
import pers.yo.bwcar1.utils.R;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping( "/sys/article/list" )
    @ResponseBody
    public DataGridResult findArti_title( QueryDTO queryDTO ){
        return articleService.findByPage( queryDTO );
    }

    @RequestMapping( "/sys/article/del" )
    @ResponseBody
    public R delArti_title( @RequestBody List<Long> ids ){
        for( Long id : ids ){
            articleService.delArt( id );
        }
        return R.ok();
    }

    @RequestMapping( "/sys/article/save" )
    @ResponseBody
    public R addArticle( @RequestBody ArticleDTO articleDTO ) throws InvocationTargetException, IllegalAccessException {
        int i = articleService.addArticle( articleDTO );
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @RequestMapping( "/sys/article/update" )
    @ResponseBody
    public R updateArticle( @RequestBody ArticleDTO articleDTO ) throws InvocationTargetException, IllegalAccessException {
        int i = articleService.updateArticle( articleDTO );
        return( i>0 ? (R.ok()):(R.error("修改失败")) );
    }

    @RequestMapping( "/ytupload" )
    @ResponseBody
    public R upload( @RequestParam("mypic") MultipartFile multipartFile, HttpServletRequest request ){
        String fileName = multipartFile.getOriginalFilename(); //得到文件名称
        File dest = new File( "E:\\Data\\bwcar1\\"+fileName );
        try{
            multipartFile.transferTo( dest ); //上传文件的目的地
        }catch( IOException e ){
            e.printStackTrace();
        }
        return R.ok().put("file",fileName);
    }
}
