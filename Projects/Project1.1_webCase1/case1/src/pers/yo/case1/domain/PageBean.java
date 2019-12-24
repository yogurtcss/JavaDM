package pers.yo.case1.domain;


import java.util.List;

/* 泛型类型用于类的定义中，被称为泛型类。
通过泛型可以完成对一组类的操作对外开放相同的接口。

*  */
public class PageBean<T> { //使用泛型类
    /* 泛型类型用于类的定义中，被称为泛型类。
    * 通过泛型可以完成对一组类的操作对外开放相同的接口。
    *  */
    private int totalCount; //总记录数
    private int totalPage; //总页码
    private List<T> list; //每页的数据
    private int currentPage; //当前页码
    private int rows; //每页显示的记录数

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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
