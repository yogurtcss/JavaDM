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
    public void delSeletedUser(String[] ids) {
        if( ids!=null && ids.length>0 ){
            for( String id: ids ){
                dao.delete( Integer.parseInt(id) );
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
