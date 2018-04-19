package heu.iot.Service;

import heu.iot.Dao.HomepageMapper;
import heu.iot.Model.Homepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/30 15:50
 */
@Service("homepageService")
public class HomepageService {

    @Autowired
    private HomepageMapper homepageMapper;

    public List<Homepage> selectByType(Integer type) {
        return homepageMapper.selectByType(type);

    }

    public Homepage selectByPrimaryKey(Integer id) {
        return homepageMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Homepage record) {
        return homepageMapper.updateByPrimaryKeySelective(record);
    }
}
