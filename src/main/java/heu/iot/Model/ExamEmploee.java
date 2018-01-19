package heu.iot.Model;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/16 10:48
 */
public class ExamEmploee {
    private int id;
    private String name;
    private String ename;
    private String date;

    public ExamEmploee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
