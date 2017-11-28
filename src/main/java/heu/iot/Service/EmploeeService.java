package heu.iot.Service;

import heu.iot.Dao.EmploeeDao;
import heu.iot.Model.Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 19:38 2017/11/28
 */
@Service("emploeeService")
public class EmploeeService {
    @Autowired
    private EmploeeDao emploeedao;

    public List<Emploee> getEmploee(){
        return emploeedao.findAll();
    }
}
