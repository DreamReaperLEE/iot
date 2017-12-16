package heu.iot.Service;

import heu.iot.Dao.SourceMapper;
import heu.iot.Model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Date: 9:46 2017/11/30
 */
@Service("sourceService")
public class SourceService {
    @Autowired
    private SourceMapper sourceMapper;

    public List<Source> showCourseDetail(Integer id,Integer lesson){
        return sourceMapper.selectByCourseLesson(id,lesson);
    }

    public List<Source> selectByCourse(Integer cid){
        return sourceMapper.selectByCourse(cid);
    }
}
