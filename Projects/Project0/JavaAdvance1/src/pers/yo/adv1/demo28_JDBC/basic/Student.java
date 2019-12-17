package pers.yo.adv1.demo28_JDBC.basic;

/* db1的stu表中：
* sno、sname、sex、math、PE
*
* RowMapper的测试类
*  */

public class Student {
    private String sno;
    private String sname;
    private String sex;
    private int math;
    private int PE;

    public Student(){
    }
    public Student( String sno, String sname, String sex, int math, int PE ){
        this.sno = sno;
        this.sname = sname;
        this.sex = sex;
        this.math = math;
        this.PE = PE;
    }

    public void setSno( String sno ){
        this.sno = sno;
    }
    public String getSno(){
        return this.sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }


//    @Override
//    public String toString() { //IDEA为我智能重写了Student的toString方法！
//        return "Student{" +
//                "sno='" + sno + '\'' +
//                ", sname='" + sname + '\'' +
//                ", sex='" + sex + '\'' +
//                ", math=" + math +
//                ", PE=" + PE +
//                '}';
//    }

    public String toString(){ //我手动重写的toString()方法
        return "Student{ "  +
                "sno=" + sno + ", "  +
                "sname=" + sname + ", " +
                "sex=" + sex + ", "  +
                "math=" + math + ", " +
                "PE=" + PE +
                " }" ;
    }

}
