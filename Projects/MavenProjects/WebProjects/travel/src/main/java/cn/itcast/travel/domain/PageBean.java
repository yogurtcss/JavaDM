package cn.itcast.travel.domain;

import java.util.List;

//客户列表分页、商品列表分页…… 为了具有通用性，将此分页对象加个泛型T (T的类型由外部指定)
public class PageBean<T> {
    private int totalCount; //当前cid类别下的 总记录数
    private int totalPage; //当前cid类别下的 总页数
    private int currentPage; //当前cid类别下的 当前页的页码
    private int pageSize; //当前cid类别下的 当前页的显示的条数

    /* 总页码的计算公式：三目运算符
    * (所有页码的总记录数totalCount)%(每页显示条数rows)==0 ? (totalCount/整除rows) : (totalCount/除以rows +1)
    *
    * (所有页码的总记录数totalCount)%(每页显示条数rows)==0 直接相除，余数等于0吗？
    * 是(即余数等于0)，就用此式计算(totalCount/整除rows)
    * 否则(余数不等于0，除不尽)，就用此式计算(totalCount/除以rows +1)
    *  */

    /* list是什么类型？不确定，就用泛型T；
    * 泛型T从哪来？从当前类的外部传进来！！所以要在开头类的定义加个泛型<T>
    *  */
    private List<T> list; //分页对象，真正的内容

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
