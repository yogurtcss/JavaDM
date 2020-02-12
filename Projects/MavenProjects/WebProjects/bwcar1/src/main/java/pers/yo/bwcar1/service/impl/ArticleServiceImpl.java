package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.ArticleMapper;
import pers.yo.bwcar1.dto.ArticleDTO;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Article;
import pers.yo.bwcar1.pojo.ArticleExample;
import pers.yo.bwcar1.service.ArticleService;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service //这个差点忘了！
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public int addArticle(ArticleDTO articleDTO) throws InvocationTargetException, IllegalAccessException {
        Article record = new Article();
        BeanUtils.copyProperties( articleDTO, record );
        Boolean allowUp = articleDTO.getAllowUp();
        record.setAllowUp( (byte)0 );

        if( allowUp!=null ){
            if( allowUp ){
                record.setAllowUp( (byte)1 );
            }
        }

        Boolean allowFav = articleDTO.getAllowFav();
        record.setAllowFav( (byte)0 );
        if( allowFav!=null ){
            if(allowFav){
                record.setAllowFav( (byte)1 );
            }
        }

        int i = articleMapper.insertSelective( record );
        return i;
    }

    @Override
    public void delArt(Long id) {
        articleMapper.deleteByPrimaryKey( id );
    }

    @Override
    public int updateArticle(ArticleDTO articleDTO) throws InvocationTargetException, IllegalAccessException {
        Article record = new Article();
        BeanUtils.copyProperties( articleDTO,record );

        Boolean allowUp = articleDTO.getAllowUp();
        if( allowUp ){
            record.setAllowUp( (byte)1 );
        }else{
            record.setAllowUp( (byte)0 );
        }

        Boolean allowFav = articleDTO.getAllowFav();
        if( allowFav ){
            record.setAllowFav( (byte)1 );
        }else{
            record.setAllowFav( (byte)0 );
        }

        int i = articleMapper.updateByPrimaryKeySelective( record );
        return i;
    }

    @Override
    public Article findById(Long id) {
        return( articleMapper.selectByPrimaryKey(id) );
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(), queryDTO.getLimit() );
        ArticleExample example = new ArticleExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort) ){
            example.setOrderByClause( "id" );
        }

        List<Article> articles = articleMapper.selectByExample( example );
        PageInfo<Article> info = new PageInfo<>(articles); //分页对象 包装内容！
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult( total, articles );
        return dgRst;
    }
}
