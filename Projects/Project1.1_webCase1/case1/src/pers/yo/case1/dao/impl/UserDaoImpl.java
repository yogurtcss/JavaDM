package pers.yo.case1.dao.impl;


import org.omg.CORBA.Object;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.yo.case1.dao.UserDao;
import pers.yo.case1.domain.User;
import pers.yo.case1.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void add(User user) {
        /* 1.定义sql
        * 注意，sql语句中插入的值要对应各列
        * 在我自定义的user表中，各列按顺序为
        * id、username、password、name、gender、age、address、qq、email
        *  */
        String sql = "insert into user values(null,null,null,?,?,?,?,?,?)";
        //2.执行sql：填充后6个问号嗷!
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        /* 2019-12-25 16:39:21
        * 不能把后面的“分页查询”语句 给删了
        * 因为 list.jsp中已经写有分页查询的语句了，
        * 若删了，list.jsp就显示不出数据了！
        *
        * 所以，学到哪个功能时：先删掉原有代码，自己再重写一遍，不要冲动全删了！！
        *  */
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        template.update(sql, id);
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id=?";
        /* 通过 queryForObject( sql语句，  RowMapper<> rm,  ...args单个的可变参数-在第三位的  );
        *
        * 第二种重载方法：
        * queryForObject( sql语句,
        *                Object[] args,    -Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
        *                RowMapper<> rm )
        *  */
        User user = (User)template.queryForObject(
                sql,
                new BeanPropertyRowMapper<User>(User.class),
                id
        );
        return user;
    }

    @Override
    public void update(User user) {
        /* 一想到 update语句，突然有点懵
        再看看这个正确的写法！！
        update user
            set name="superbaby", gender="男", age=10, address="中国", qq="111", email="111@qqq.com"
        where id="1";
        *  */

        /* BeanUtils.populate( user, map );
        * 注意，map中就有 隐藏域id
        * 此时 post表单提交的【隐藏域id】已经被填充进用户user实例对象中，在sql语句中可直接从此user实例对象中取出id值
        * 还有 用户提交的表单数据没有 username和password！！
        *  */

        /* 花了一个小时来排这个BUG：2019-12-26 20:01:42
        * 正确的sql语句为：
        * update user set name=?, gender=?, age=?, address=?, qq=?, email=? where id=?
        * 要有空格！
        *
        * 最初的写法
        * String sql = "update user" +
                     "set name=?, gender=?, age=?, address=?, qq=?, email=?" +
                     "where id=?";
        *
        * System.out.println( sql );
        * //输出的结果是 update userset name=?, gender=?, age=?, address=?, qq=?, email=?where id=?
        * 拼串后：user和set之间没有空格了，email=? 与where也没有空格了！
        * 这样的sql语句当然无法执行！
        *
        * 所以，拼串的sql语句，在【拼接处末尾】一定要注意空格！
        * "update user -(拼接处末尾加一个空格)"
        * "set ...., email=? -(拼接处末尾加一个空格)"
        *
        * 或者直接一条sql语句完整写完它：
        * String sql2 = "update user set name=?, gender=?, age=?, address=?, qq=?, email=? where id=? " ;
        * 如果一条地写完sql语句，不会出现上述的 空格问题！
        *  */

        //所以，拼串的sql语句，在【拼接处末尾】一定要注意空格！
        String sql = "update user " +
                     "set name=?, gender=?, age=?, address=?, qq=?, email=? " +
                     "where id=? ";

        System.out.println( sql ); //查看拼接的sql语句是否是正确的：拼串的sql语句，在【拼接处末尾】一定要注意空格

        //执行更新操作
        template.update(
                sql,
                user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),
                //此时 post表单提交的【隐藏域id】已经被填充进用户user实例对象中，在sql语句中可直接从此user实例对象中取出id值
                user.getId()
        );

    }
    /* 【单条记录的查询语句】，返回值也是单个的。
    * queryForObject()与queryForMap()

    --------------------
     Object  queryForObject(sql语句, 【可选的 -Object[] args】, Class类类型<T> requiredType, )
    返回一个【基本数据类型 或String】的实例对象——可认为是 【返回一个“值”的 包装类实例对象】
    注：聚合函数的查询结果通常为一个“值”；
    故queryForObject()方法 常用于聚合函数的查询：count、avg、sum、max、min

    聚合函数：将一列数据作为一个整体，进行纵向的计算
     count 计算个数
     max
     min
     sum
     avg 计算平均值

    * sql：即sql语句
    * 【可选的 -Object[] args】，即 Object类型的数组；数组元素是：为原sql语句中各 ? 号按先后顺序赋值
       如 不用在外部new了，直接在此处传入一个 new Object[]{ 15-第一个?号, "user4"-第二个?号 }

    * Class类类型<T> requiredType：
      (此语句执行后的返回值)-等号左边、返回的实例对象所属的“类” 的【类_类型实例对象】
      如 返回的实例对象是整型int的，则它所属的“类”为Integer，所以 此处的requiredType为 Integer.class
    *
    *
    *  */

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        /* 2019-12-27 16:28:11
        * 查询总记录数
        *  */

        /* 根据 条件查询参数map集合中的【value值】 动态生成sql语句 —— “若某键key对应的value有值，我就 用and把此键key添进sql语句中！”
        *
        *  1.定义初始化的sql语句：使用StringBuilder变量嗷！因为要 append添加sql语句
        * StringBuilder实例对象sb 表示的字符串是 初始的sql语句
        * select count(*) from user 【where 1=1】--这是永真的，有没有1=1 都是正常运行的
        * 若某键key对应的value有值，我就 用and把此键key添进sql语句中！
        * select count(*) from user 【where 1=1】 and name=? and address=?
        *
        * 2.遍历map，判断每一个键key的value是否有值
        * 若有值， StringBuilder实例对象sb.append( "and 键key like %.." );
        *   其中 StringBuilder实例对象sb 表示的字符串是 初始的sql语句
        *
        *  */

        //1.定义初始化的sql语句
        String sql = "select count(*) from user where 1=1 "; //sql语句的关键字前后要加空格嗷！
        StringBuilder sb = new StringBuilder( sql );  //StringBuilder实例对象sb 表示的字符串是 初始的sql语句

        //2.遍历map，判断每一个键key的value是否有值
        Set<String> keys = condition.keySet(); //这里使用最简单的 增强for循环哈！
        List<String> params = new ArrayList<>(); //若键key对应的value有值，将此值存入params数组中
        for( String oneKey : keys ){
            //排除分页的参数currentPage、rows
            if( oneKey.equals("currentPage") || oneKey.equals("rows") ){
                continue; //跳过当轮循环，进行下一轮循环
            }

            //虽然 值是 String数组类型的，但我明确知道 String数组里面只有一个元素，直接取第一个元素-下标为0
            String oneValue = condition.get(oneKey)[0];

            if( oneValue!=null && !oneValue.equals("") ){
                /* 拼接sql语句，真的怕了
                * 每个关键字前后，都给我加空格！！
                *  */
                sb.append( " and "+oneKey+" like ? " ); //正式动态拼接sql语句
                params.add( "%"+oneValue+"%" );
            }
        }

        System.out.println( sb.toString() );
        System.out.println( params );

        /* 忘了这句话怎么用了，
        * Object  queryForObject(sql语句, 【可选的 -Object[] args】, Class类类型<T> requiredType )
        *  */
        //自动拆箱，将Integer类型自动转为int型。不需我们手动转换了
        // params.toArray() 就是可变参数的数组！！--按顺序填充sql语句中的各个问号
        int ans = template.queryForObject( sb.toString(), params.toArray(), Integer.class  );

        return ans;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        /* 2019-12-27 16:46:20
        * 分页查询
        *  */

        /* template.query方法
        * 将查询sql所得【每一行数据】映射成【每一个实例对象X】，
        * 并将 此映射后的实例对象X放入列表list中，
        * 所以 list列表的泛型是 实例对象的X类型(如 T)
        *
        * List<T> query( sql语句,
        *               new Object[]{ 数组元素-args为各问号?按顺序赋值 },
        *               RowMapper<T> rm -将查询sql所得数据映射到的实例对象
        * )
        *
        * List<T> query( sql语句,
        *               RowMapper<T> rm,  -将查询查询sql所得数据映射到的实例对象
        *               Object ...args    -单个的可变参数-各问号?按顺序赋值
        * )
        *  */
        /*
        List<User> list = template.query(
                sql,
                new Object[]{start,rows},  //使用其中一种重载方法：以对象数组的方式传入形参，为各问号赋值嗷！
                new BeanPropertyRowMapper<User>(User.class)
        );  */ //不能用这个写法 错误:(249, 30) java: 不兼容的类型: int无法转换为org.omg.CORBA.Object


        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合!!

        /* List list = null;
        * 从 list 的声明当中我们可以对比发现，原始类型没有为容器指定明确的元素类型，
        * 所以我们可以在容器中放入一个 String，也可以放入一个 Integer，甚至任意的类型，
        * value是字符串型， start和rows是整型，照样能放进List中！
        *  */
        List params = new ArrayList<>(); //List 没有设置泛型，所以可以放任意的数据！
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?, ? ");
        //添加分页查询参数值
        params.add( start );
        params.add( rows );
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query( sql,new BeanPropertyRowMapper<User>(User.class),params.toArray() );
    }
}
