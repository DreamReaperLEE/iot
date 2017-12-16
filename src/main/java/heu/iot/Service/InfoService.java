package heu.iot.Service;

import heu.iot.Dao.EmploeeMapper;
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

    public int changePassword(Integer id,String origin,String password){
        return emploeeMapper.updatePassword(id,origin,password);
    }
}
