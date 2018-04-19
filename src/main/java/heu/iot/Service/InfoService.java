package heu.iot.Service;

import heu.iot.Dao.EmploeeMapper;
import heu.iot.Model.Emploee;
import heu.iot.Util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Sumail-Lee
 * @Date: 15:18 2017/12/2
 */
@Service("infoService")
public class InfoService {
    @Autowired
    private EmploeeMapper emploeeMapper;

    private MD5 md5 = new MD5();

    public Emploee login(Integer id,String password){
        password= MD5.getMd5(password);
        Emploee emploee=emploeeMapper.checkPassword(id,password);
        return emploee;
    }

    public int changePassword(Integer id,String origin,String password){
        return emploeeMapper.updatePassword(id,origin,password);
    }
}
