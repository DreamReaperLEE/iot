package heu.iot.Service;

import heu.iot.Dao.ExamMapper;
import heu.iot.Dao.PaperMapper;
import heu.iot.Model.Exam;
import heu.iot.Model.Paper;
import heu.iot.Util.TimeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/18 17:15
 */
@Service("paperService")
public class PaperService {
    @Autowired
    private PaperMapper paperMapper;

    public Paper showPaper(Integer id) {
        return  paperMapper.selectByEid(id);
    }

    public Paper selectByPrimaryKey(Integer pid){
        return paperMapper.selectByPrimaryKey(pid);
    }

}
