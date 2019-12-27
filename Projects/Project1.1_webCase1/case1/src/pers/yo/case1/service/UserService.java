package pers.yo.case1.service;


import pers.yo.case1.domain.PageBean;
import pers.yo.case1.domain.User;

import java.util.List;
import java.util.Map;

//用户管理的业务接口
public interface UserService {

    /**
     * 查询所有用户信息
     * @return 所有用户信息的集合list
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param userTry 尝试登陆的用户
     * @return 登录成功的用户
     */
    User login( User userTry );

    /**
     * 保存 某个user实例对象的数据 进入数据库中
     * @param user 某个用户
     */
    void addUser( User user );

    /**
     * 根据id值，删除 在数据库中某个user的信息
     * @param id 就是 ID值！
     */
    void deleteUser( String id );

    /**
     * 根据id，在数据库中找到此id对应的user
     * @param id 就是 ID值！
     * @return 查找到的user
     */
    User findUserById( String id );

    /**
     * 指定某个用户userA —— 以修改这个用户userA的信息
     * @param user
     */
    void updateUser( User user );


    /**
     * 根据给定的若干个id --id 的字符串数据，在数据库中删除这一堆id值对应的user信息
     * @param ids 给定的若干个id --id 的字符串数据
     */
    void delSeletedUser( String[] ids );

    /**
     * 分页的条件 查询
     * @param _currentPage 当前页
     * @param _rows
     * //@param condition  //临时删掉了
     * //@return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition );


}
