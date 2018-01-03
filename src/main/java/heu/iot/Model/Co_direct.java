package heu.iot.Model;

public class Co_direct {
    private Integer id;

    private String directName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirectName() {
        return directName;
    }

    public void setDirectName(String directName) {
        this.directName = directName == null ? null : directName.trim();
    }
}