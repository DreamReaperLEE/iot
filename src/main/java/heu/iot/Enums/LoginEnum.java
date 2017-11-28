package heu.iot.Enums;

/**
 * @Author: Sumail-Lee
 * @Date: 10:03 2017/11/27
 */
public enum LoginEnum {
    SUCCESS(1,"success"),
    PASSWORD_ERROR(0,"密码错误");

    private Integer code;

    private String msg;

    LoginEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static LoginEnum getEnumByCode(Integer code){
        switch (code){
            case 1:
                return SUCCESS;
            default:
                return PASSWORD_ERROR;
        }
    }
}
