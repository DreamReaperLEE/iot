package heu.iot.Service;

import heu.iot.Dao.InformMapper;
import heu.iot.Dao.Inform_EmploeeMapper;
import heu.iot.Model.Inform;
import heu.iot.Model.Inform_Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/27 19:41
 */
@Service("informService")
public class InformService {
    @Autowired
    private Inform_EmploeeMapper inform_emploeeMapper;

    @Autowired
    private InformMapper informMapper;

    public List<Inform_Emploee> showRecent5(){
        return inform_emploeeMapper.showRecent5();
    }

    public List<Inform_Emploee> showAll(){return inform_emploeeMapper.showAll();}

    public Inform selectByPrimaryKey(Integer id){
        return informMapper.selectByPrimaryKey(id);
    }

}
