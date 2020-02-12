package pers.yo.bwcar1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yo.bwcar1.dao.SysUserMapper;
import pers.yo.bwcar1.dto.DataGridResult;
import pers.yo.bwcar1.dto.QueryDTO;
import pers.yo.bwcar1.pojo.SysUser;
import pers.yo.bwcar1.service.SysUserService;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    /* 2020-02-06 11:01:51
    * 此时若dao层接口 sysUserMapper飘红：提示无法注入，
    * 实际上我在配置文件中已经配置完成了。
    * 这是能注入的，IDEA警告的而已。
    *
    * 解决飘红：修改它的“编译级别” 为 warning
    * 鼠标光标放到到 飘红的sysUserMapper单词上 -
    * 键盘 alt+Enter - 弹出菜单中的第一项：检查 Autowiring for Bean Class 选项 -
    * (又是第一项：) 编辑检查配置文件设置 -
    * 将 Autowiring for Bean Class 的 “严重性” 修改为 warning 即可。
    *  */
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> findAll() {
        return( sysUserMapper.selectByExample(null) );
    }


    //2020-02-08 13:33:29
    @Override
    public DataGridResult findUserByByPage(QueryDTO queryDTO) {
        PageHelper.offsetPage( queryDTO.getOffset(), queryDTO.getLimit() ); //分页工具类：开始分页

        /* 2020-02-08 14:47:17  来自 MenuServiceImpl.java中的 menu_id 的解释
        从前端传过来的、输入至queryDTO中，也有排序字段(sort)，为什么还要将排序字段修改为字符串menu_id？
        因为前端传来的排序字段sort是字符串 "menuId"，没有下划线；(见前端发送Ajax请求的代码段)
        而数据库sys_menu表中的列名"menu_id"是有下划线的！！

        //数据库sys_menu表中没有"menuId"的列名，只有 "menu_id"(带下划线)的列名；(select一下sys_menu表看看)

        所以在后端这里，先接收到 字符串 "menuId"，然后转换为带下划线的字符串"menu_id"，
        后续把字符串"menu_id"传给dao查询的方法：让数据库能按照正确的列名"menu_id"来排序！

        以上所述，在bwcar视频第11集末尾，老师也有解释。

        //这样，bwcar视频 第22集中出现的 queryDTO.setSort( "user_id" ) 也是同理解释的。
        *  */
        if( (queryDTO.getSort()!=null)&&(!queryDTO.getSort().equals("")) ){
            queryDTO.setSort("user_id");
        };

        //查询出结果list_rst
        List<SysUser> list_rst  = sysUserMapper.findByPage( queryDTO );
        //将查询结果list包装为PageInfo类对象(故new的时候要传入查询结果list_rst)
        PageInfo<SysUser> info = new PageInfo<>(list_rst);

        long total = info.getTotal();
        List<SysUser> rows = info.getList();
        DataGridResult rst = new DataGridResult( total,rows );
        return rst;
    }

    @Override
    public Workbook exportUser() {
        //---创建一个空的excel文件
        Workbook workbook = new HSSFWorkbook();
        //---填充数据：创建sheet
        Sheet sheet = workbook.createSheet("某某公司的员工信息");
        //---标题数组
        String titles[] = {"用户id","用户名","邮箱","电话","创建时间"};
        String colums[] ={"userId", "username", "email", "mobile", "createTime"};

        List<Map<String,Object>> maps = sysUserMapper.exportUser();
        Row rowTile = sheet.createRow(0);

        for( int i=0; i<titles.length; i++ ){ //标题行
            Cell cell = rowTile.createCell(i);
            cell.setCellValue(titles[i]);
        };
        for( int i=0; i<maps.size(); i++ ){
            //一条记录应该创建一个Row对象 这里从第二行开始所以+1
            Row row = sheet.createRow(i+1);//这个是空的，需要填充数据
            //填充单元格
            for (int j = 0; j < titles.length; j++) {
                Cell cell = row.createCell(j);
                //获取用户id的值
                Map<String, Object> rowValue = maps.get(i);
                //循环动态设置多个字段的值
                Object o = rowValue.get(colums[j]);//这里获取的值可以是"userId"..
                //这里也就是为什么查询数据库使用map封装的原因。
                cell.setCellValue(o+"");
            }
        }

        return workbook;
    }

    @Override
    public SysUser findByUsername(String username) {

        return( sysUserMapper.findByUsername(username) );
    }
}
