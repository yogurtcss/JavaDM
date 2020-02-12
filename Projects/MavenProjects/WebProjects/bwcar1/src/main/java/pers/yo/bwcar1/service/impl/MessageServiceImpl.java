package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yo.bwcar1.dao.MessageMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Message;
import pers.yo.bwcar1.pojo.MessageExample;
import pers.yo.bwcar1.service.MessageService;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public int addMessage(Message message) {
        return messageMapper.insertSelective(message);
    }

    @Override
    public int delMessage(Long id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateMessage(Message message) {
        return messageMapper.updateByPrimaryKeyWithBLOBs(message);
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        MessageExample example = new MessageExample();
        String sort = queryDTO.getSort();
        if(!StringUtils.isEmpty(sort)){
            example.setOrderByClause(sort);
        }
        List<Message> ms = messageMapper.selectByExampleWithBLOBs(example);
        PageInfo<Message> info = new PageInfo<>(ms);
        long total = info.getTotal();
        DataGridResult dgRst = new DataGridResult(total,ms);
        return dgRst;
    }
}
