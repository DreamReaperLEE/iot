package heu.iot.Model;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Since: 17:28 2017/12/11
 */
public class Score_Exam_Paper {
    private int pid;
    private String ename;
    private float score;

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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
