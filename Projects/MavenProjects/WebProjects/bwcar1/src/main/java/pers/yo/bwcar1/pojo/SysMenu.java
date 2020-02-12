package pers.yo.bwcar1.pojo;

import java.io.Serializable;

public class SysMenu implements Serializable {
    private Long menuId;

    private Long parentId;

    private String name;

    /* 2020-02-06 19:49:50
    * 在SysMenuMapper.xml中的BaseResultMap中，
    * 增加name的别名 parentName，注意也要在SysMenu的pojo类中加这个字段！！
    * 并为此属性生成getter、setter方法！
    * <result column="parentName" property="parentName" jdbcType="VARCHAR"
    *  */
    private String parentName;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private static final long serialVersionUID = 1L;

    //2020-02-06 19:51:31
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms == null ? null : perms.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}