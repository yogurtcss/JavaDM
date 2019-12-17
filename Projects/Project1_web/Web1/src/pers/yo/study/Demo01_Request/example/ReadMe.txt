1.将配置文件druid.properties复制到本项目的src中
    * 在这里我把配置文件复制到了 src/ pers/ yo/ props文件夹中，不想放在全局中
      通过类加载器读取这配置文件的时候，要注意书写的路径了……
      书写路径为 pers/yo/props/druid.properties 吗？
      src文件夹下的 ：pers/yo/props/druid.properties 路径确认可用

    * 此配置文件中的url需要更改为 我电脑中的配置：端口为5306，使用我自己db1的数据库嗷！
      url=jdbc:mysql://127.0.0.1:5306/db1数据库

    * 在IDEA外部，提前在db1数据库中建好所需的表user，并向表user中插入若干新数据
      列名（全小写嗷！）有 id、username、password


2.把依赖的jar包导入本项目中。要放在哪里?
    * 把依赖的jar包放在：项目中 web/ WEB-INF/ lib文件夹中
    * 右键，将此lib文件夹 "添加为库..."，选择级别level 为 "模块库"，表示此库仅能在模块级别使用

    * 注意操作顺序：先把依赖的jar包复制进web/ WEB-INF/ lib文件夹，
    （这样右键lib文件夹，所列的菜单中才会出现"添加为库.."的选项）
    然后才能将此lib文件夹 "添加为库.." ！！


3.正式打码了

4.使用BeanUtils工具类，简化数据封装
    * 将 commons-beanutils-1.8.0.jar 复制到lib目录下