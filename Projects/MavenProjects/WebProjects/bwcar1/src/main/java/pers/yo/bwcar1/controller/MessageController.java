package pers.yo.bwcar1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.Message;
import pers.yo.bwcar1.service.MessageService;
import pers.yo.bwcar1.utils.R;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/sys/message/list")
    @ResponseBody
    public DataGridResult findMessage(QueryDTO queryDTO){
        return messageService.findByPage(queryDTO);
    }

    @RequestMapping("/sys/message/del")
    @ResponseBody
    public R delMes(@RequestBody List<Long> ids){
        for(Long id:ids){
            messageService.delMessage(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/message/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Long id){
        Message rst = messageService.findById(id);
        return R.ok().put("message",rst);
    }

    @RequestMapping("/sys/message/save")
    @ResponseBody
    public R addMsg(@RequestBody Message message){
        int i = messageService.addMessage(message);
        return( i>0 ? (R.ok()):(R.error("添加失败")) );
    }

    @ResponseBody
    @RequestMapping("/sys/message/update")
    public R updateMsg(@RequestBody Message message){
        int i = messageService.updateMessage(message);
        return i>0?R.ok():R.error("修改失败");
    }

}
