package heu.iot.Service;

import heu.iot.Dao.EmploeeMapper;
import heu.iot.Model.Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/29 17:10
 */
@Service("emploeeService")
public class EmploeeService {
    @Autowired
    private EmploeeMapper emploeeMapper;



    public int deleteEmploee(Integer id){
        return emploeeMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(Emploee emploee){return emploeeMapper.insertSelective(emploee);}

    public Emploee selectByPrimaryKey(Integer id){return emploeeMapper.selectByPrimaryKey(id);}

    public int updateByPrimaryKey(Emploee emploee){return emploeeMapper.updateByPrimaryKey(emploee);}

    public List<Emploee> selectByEmploeePriv(Integer priv){
        return  emploeeMapper.selectByEmploeePriv(priv);

    }

    public List<Emploee> selectByActive(Integer active){return emploeeMapper.selectByActive(active);}

    public List<Emploee> selectEmploeeByName(String name){
        return emploeeMapper.selectEmploeeByName(name);
    }

    public int updateByPrimaryKeySelective(Emploee emploee){
        return emploeeMapper.updateByPrimaryKeySelective(emploee);
    }

    public int countStudentNum(){
        return emploeeMapper.countStudentNum();
    }

    public int countTeacherNum(){
        return emploeeMapper.countTeacherNum();
    }

    public List<Emploee> showSelected(List<Integer> record){
        return emploeeMapper.showSelected(record);
    }
}
