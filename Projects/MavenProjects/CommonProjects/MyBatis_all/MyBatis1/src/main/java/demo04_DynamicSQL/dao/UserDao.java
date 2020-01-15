package demo04_DynamicSQL.dao;

import demo04_DynamicSQL.domain.QueryVo;
import demo04_DynamicSQL.domain.User;

import java.util.List;

public interface UserDao {
    public abstract List<User> findAll();
    public abstract User findById( Integer id );
    public abstract List<User> findByName( String username ); //根据名称模糊查询用户信息
    public abstract List<User> findUserByVo( QueryVo vo ); //根据queryVo中的条件查询用户

    //：user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能是都有
    public abstract List<User> findUserByCondition( User user );

    public abstract List<User> findUserInIds( QueryVo vo ); //根据queryvo中提供的id集合，查询用户信息

    /* 2020-01-15 17:16:03
    看懂了 <foreach /> 标签的用法了！
    核心：生成 in字段后面的内容： (value1,value2,value3,…)，然后拼入原本的SQL语句中！
    把 集合list或数组array中的每个元素 都 “一一放进SQL语句 in字段后面的 ()括号中，每个元素都以英文逗号, 分隔开”

    select * from user表 where XXX in (value1,value2,value3,…)  //最好不要用空格隔开！
    <foreach /> 标签，就是生成 in字段后面的内容： (value1,value2,value3,…)
      开头是左括号(
      中间的value是 集合list或数组array中的每个元素
      分隔符是英文逗号,
      结尾是右括号)


    <foreach> 标签有循环的功能，可以用来生成有规律的 SQL 语句，主要属性有：
      1.collection：该属性值为【传入形参parameterType中的属性A，该属性A必需为list集合或array数组】
         如 传入形参parameterType为 QueryVo实例对象
         需要被迭代的集合是 QueryVo实例对象中的 属性 【集合ids】
         所以，collection="ids" 而不是 collection="list" ！！

      2.item：在迭代过程中，代表 集合每一个元素的【变量名 或称 别名】，
         用于在 <foreach />标签体中的内容：表示被迭代的元素value！

      3.index：在迭代过程中，代表 每次迭代到的位置 的【变量名】 ——较少用

      ——以下开始拼串环节！
      4.open：表示该语句以什么开始：开头是左括号(
      5.separator：表示每次迭代之间以什么符号作为分隔符：中间的value是 集合list或数组array中的每个元素
      6.close：表示该语句以什么结束：结尾是右括号)

     示例：
     <select id="selectPostIn" resultType="domain.blog.Post">
        SELECT * FROM POST P WHERE ID in
        <foreach
              collection="list"  //collection：该属性值为【传入形参parameterType中的属性A，该属性A必需为list集合或array数组】
              item="item别名"  //被迭代的元素value的别名是可任意的
              index="index"

              open="("
              separator=","
              close=")"   >

            // 别名，用在<foreach />标签体中的内容：表示被迭代的元素value！
            #{item} //被迭代的元素value的别名是可任意的

        <//foreach>  //为了复制进xml中的注释，我在结束标签这里搞了两条斜杠//
    <//select>

    *  */

}
