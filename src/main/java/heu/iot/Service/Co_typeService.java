package heu.iot.Service;

import heu.iot.Dao.Co_typeMapper;
import heu.iot.Model.Co_type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/29 15:24
 */
@Service("co_typeService")
public class Co_typeService {
    @Autowired
    private Co_typeMapper co_typeMapper;

    public List<Co_type> showAllType(){
        return co_typeMapper.showAll();
    }

    public int insertSelective(Co_type co_type){return co_typeMapper.insertSelective(co_type);}

    public int deleteByPrimaryKey(Integer id){return co_typeMapper.deleteByPrimaryKey(id);}
}
