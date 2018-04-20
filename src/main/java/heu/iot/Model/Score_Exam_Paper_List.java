package heu.iot.Model;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 17:28 2017/12/11
 */
public class Score_Exam_Paper_List {
    private int pid;
    private String ename;
    private String edesc;
    private String date;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEdesc() {
        return edesc;
    }

    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
