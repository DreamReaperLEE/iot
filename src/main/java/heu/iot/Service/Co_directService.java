package heu.iot.Service;

import heu.iot.Dao.Co_directMapper;
import heu.iot.Model.Co_direct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/29 15:23
 */
@Service("co_directService")
public class Co_directService {
    @Autowired
    private Co_directMapper co_directMapper;

    public List<Co_direct> showAllDirect(){
        return co_directMapper.showAll();
    }
}
