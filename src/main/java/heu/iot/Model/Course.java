package heu.iot.Model;

public class Course {
    private Integer id;

    private String cname;

    private String cdetail;

    private String cintroduce;

    private Integer tid;

    private String date;

    private String ctype;

    private String cdirect;

    private String cpic;

    private String cpoint;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCdetail() {
        return cdetail;
    }

    public void setCdetail(String cdetail) {
        this.cdetail = cdetail == null ? null : cdetail.trim();
    }

    public String getCintroduce() {
        return cintroduce;
    }

    public void setCintroduce(String cintroduce) {
        this.cintroduce = cintroduce == null ? null : cintroduce.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    public String getCdirect() {
        return cdirect;
    }

    public void setCdirect(String cdirect) {
        this.cdirect = cdirect == null ? null : cdirect.trim();
    }

    public String getCpic() {
        return cpic;
    }

    public void setCpic(String cpic) {
        this.cpic = cpic == null ? null : cpic.trim();
    }

    public String getCpoint() {
        return cpoint;
    }

    public void setCpoint(String cpoint) {
        this.cpoint = cpoint == null ? null : cpoint.trim();
    }
}