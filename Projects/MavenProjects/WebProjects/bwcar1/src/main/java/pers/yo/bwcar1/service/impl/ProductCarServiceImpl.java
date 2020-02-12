package pers.yo.bwcar1.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.ProductMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.ProductExample;
import pers.yo.bwcar1.pojo.ProductWithBLOBs;
import pers.yo.bwcar1.service.ProductCarService;

import java.util.List;

@Service
public class ProductCarServiceImpl implements ProductCarService {

    @Autowired
    private ProductMapper pMapper;

    @Override
    public int addProduct(ProductWithBLOBs productWithBLOBs) {
        return pMapper.insertSelective(productWithBLOBs);
    }

    @Override
    public int delProduct(Integer id) {
        return pMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateProduct(ProductWithBLOBs productWithBLOBs) {
        return pMapper.updateByPrimaryKeyWithBLOBs(productWithBLOBs);
    }

    @Override
    public ProductWithBLOBs findById(Integer id) {
        return pMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        ProductExample example = new ProductExample();
        String sort = queryDTO.getSort();
        if( !StringUtils.isEmpty(sort) ){
            example.setOrderByClause("id");
        }
        List<ProductWithBLOBs> ps = pMapper.selectByExampleWithBLOBs(example);
        PageInfo<ProductWithBLOBs> info = new PageInfo<>(ps);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,ps);
        return dgRst;
    }
}
