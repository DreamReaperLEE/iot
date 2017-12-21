package heu.iot.Service;

import heu.iot.Dao.CourseMapper;
import heu.iot.Dao.Course_EmploeeMapper;
import heu.iot.Dao.PaperMapper;
import heu.iot.Model.Course;
import heu.iot.Model.Course_Emploee;
import heu.iot.Model.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paperService")
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;

    public int updatePaper(Paper record) {
        return paperMapper.insert(record);
    }

}
