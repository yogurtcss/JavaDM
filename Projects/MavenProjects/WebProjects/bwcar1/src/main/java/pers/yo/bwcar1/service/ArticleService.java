package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.ArticleDTO;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Article;

import java.lang.reflect.InvocationTargetException;

public interface ArticleService { //咨询管理接口

    public abstract int addArticle( ArticleDTO articleDTO ) throws InvocationTargetException, IllegalAccessException;
    public abstract void delArt( Long id );
    public abstract int updateArticle( ArticleDTO articleDTO ) throws InvocationTargetException, IllegalAccessException;
    public abstract Article findById( Long id );
    public abstract DataGridResult findByPage( QueryDTO queryDTO );

}
