package pers.yo.bwcar1.service.impl;
//2020-02-11 17:45:50 鏖战

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.bwcar1.dao.TagMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Tag;
import pers.yo.bwcar1.pojo.TagExample;
import pers.yo.bwcar1.service.TagService;
import pers.yo.bwcar1.utils.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public int addTag(Tag tag) {
        return( tagMapper.insertSelective(tag) );
    }

    @Override
    public void delTag(Integer id) {
        tagMapper.deleteByPrimaryKey( id );
    }

    @Override
    public int updateTag(Tag tag) {
        return( tagMapper.updateByPrimaryKeySelective(tag) );
    }

    @Override
    public Tag findById(Integer id) {
        return( tagMapper.selectByPrimaryKey(id) );
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(), queryDTO.getLimit() ); //开始分页

        TagExample example = new TagExample();
        if( (queryDTO.getSort()!=null)&&(!queryDTO.getSort().equals("")) ){
            example.setOrderByClause( "id "+queryDTO.getOrder() );
        }

        List<Tag> tags = tagMapper.selectByExample( example ); //查询出结果
        PageInfo<Tag> info = new PageInfo<>(tags); //分页对象 包装内容，这里老师忘记了！
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult( total,tags );

        return dgRst;
    }

    @Override
    public R findLineData() {
        List<String> xAxisData = new ArrayList<>();
        List<Long> seriesData = new ArrayList<>();
        List<Tag> tags = tagMapper.selectByExample( null );

        for( Tag tag:tags ){
            String name = tag.getName();
            xAxisData.add(name);
            Long clickCount = tag.getClickCount();
            seriesData.add(clickCount);
        }

        return R.ok().put("xAxis",xAxisData).put("seriesData",seriesData);
    }

    @Override
    public R findBarData() {
        List<String> xAxisData = new ArrayList<>();
        List<Long> seriesData = new ArrayList<>();

        List<Tag> tags = tagMapper.selectByExample(null);

        for( Tag tag:tags ){
            xAxisData.add( tag.getName() );
            seriesData.add( tag.getClickCount() );
        }

        return R.ok().put("xAxis",xAxisData).put("seriesData",seriesData);
    }

    @Override
    public R findPieData() {
        List<String> legendData = new ArrayList<>();
        List<Map<String,Object>> seriesData = new ArrayList<>();
        List<Tag> tags = tagMapper.selectByExample(null);
        for( Tag tag:tags ){
            String name = tag.getName();
            Long clickCount = tag.getClickCount();
            legendData.add(name);

            Map<String,Object> map = new HashMap<>();
            map.put("name",name);
            map.put("value",clickCount);
            seriesData.add(map);
        }
        return R.ok().put("legendData",legendData).put("seriesData",seriesData);

    }
}
