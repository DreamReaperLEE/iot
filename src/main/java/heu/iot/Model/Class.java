package heu.iot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: Sumail-Lee
 * @Date: 9:56 2017/11/28
 */

@Entity
public class Class {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer sid;
    private String c;

    public Class() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
}
