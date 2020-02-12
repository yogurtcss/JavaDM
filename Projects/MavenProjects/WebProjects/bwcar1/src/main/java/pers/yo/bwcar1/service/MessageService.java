package pers.yo.bwcar1.service;

import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Message;

public interface MessageService {

    public abstract int addMessage(Message message);
    public abstract int delMessage(Long id);
    public abstract int updateMessage(Message message);
    public abstract Message findById(Long id);
    public abstract DataGridResult findByPage(QueryDTO queryDTO);

}
