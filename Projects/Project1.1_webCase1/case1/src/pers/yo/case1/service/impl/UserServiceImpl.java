package pers.yo.case1.service.impl;

import pers.yo.case1.dao.impl.UserDaoImpl;
import pers.yo.case1.dao.UserDao;
import pers.yo.case1.domain.PageBean;
import pers.yo.case1.domain.User;
import pers.yo.case1.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User userTry) {
        return dao.findUserByUsernameAndPassword( userTry.getUsername(), userTry.getPassword() );
    }

    @Override
    public void addUser(User user) {
        dao.add( user );
    }

    @Override
    public void deleteUser(String id) {
        dao.delete( Integer.parseInt(id) );
    }

    @Override
    public User findUserById(String id) {
        return dao.findById( Integer.parseInt(id) );
    }

    @Override
    public void updateUser(User user) {
        dao.update( user );
    }

    @Override
    public void delSeletedUser(String[] uids) {
        /* delSeletedUser( String[] uids )
        * 遍历这些用户id，逐个地删除之
        *  */
        if( uids!=null && uids.length>0 ){
            for( String oneUid : uids ){ //遍历这些用户id，逐个地删除之
                dao.delete( Integer.parseInt(oneUid) );
            }
        }

    }

    @Override
    //将所需数据【setXXX-填入】PageBean 实例对象pb中，返回给前端页面以【动态展示数据】嗷！
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        //----------1.创建空的PageBean 实例对象pb
        PageBean<User> pb = new PageBean<>();

        //先把字符串形式的数据转为 整型数据
        int currentPage = Integer.parseInt( _currentPage );
        int rows = Integer.parseInt( _rows );
        //----------2.为实例对象pb【setXXX-填入数据】 当前页面currentPage、和 rows
        pb.setCurrentPage( currentPage );
        pb.setRows( rows );

        //----------3.调用dao查询totalCount总记录数，并【setXXX-填入数据】进实例对象pb中
        int totalCount = dao.findTotalCount();
        pb.setTotalCount( totalCount );

        //----------4.计算某页的开始索引start
        //start = (currentPage-1)*rows
        int start = (currentPage-1)*rows;

        //----------5.调用dao查询list集合
        //dao.findByPage( int start, int rows );
        List<User> list = dao.findByPage( start, rows );
        pb.setList( list );

        //----------6.计算总页码totalPage，并【setXXX-填入数据】进实例对象pb中
        /* private int totalPage; //总页码
        * 总页码的计算公式：三目运算符 注意括号的包裹嗷！
        * 【 (所有页码的总记录数totalCount % 每页显示条数rows)==0】 ? (totalCount/整除rows) : (totalCount/除以rows +1)
        *
        * (所有页码的总记录数totalCount)%(每页显示条数rows)==0 直接相除，余数等于0吗？
        * 是(即余数等于0)，就用此式计算(totalCount/整除rows)
        * 否则(余数不等于0，除不尽)，就用此式计算(totalCount/除以rows +1)
        *  */
        int totalPage = ( (totalCount%rows)==0 ) ? (totalCount/rows): (totalCount/rows+1);
        pb.setTotalPage( totalPage );

        //----------7.返回PageBean对象
        return pb;
    }
}
