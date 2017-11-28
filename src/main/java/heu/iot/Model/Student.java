package heu.iot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: Sumail-Lee
 * @Date: 9:42 2017/11/27
 */

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Student() {
    }
}
