package heu.iot.Service;

import heu.iot.Dao.EmploeeMapper;
import heu.iot.Model.Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 11:18 2017/11/29
 */
@Service("emploeeService")
public class EmploeeService {
    @Autowired
    private EmploeeMapper emploeeMapper;

    public Emploee showEmploee()  throws Exception{
        Integer a=2013201308;
        return emploeeMapper.selectByPrimaryKey(a);
    }

    public List<Emploee> showAll()  throws Exception{
        return emploeeMapper.showAll();
    }
}