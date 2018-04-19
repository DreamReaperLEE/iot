package heu.iot.Service;

import heu.iot.Dao.QuestionMapper;
import heu.iot.Model.Question;
import heu.iot.Model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionService")
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> showAllQuestion() {
        return questionMapper.showAllQuestion();
    }

    public List<Question> showSelected(List<Integer> info) {
        if(info.size()==0){
            info.add(0);
        }
        return questionMapper.showSelected(info);
    }

    public int countQuestionNum() {
        return questionMapper.countQuestionNum();
    }

    public List<Question> selectByCourseId(Integer cid){return questionMapper.selectByCourseId(cid);}

    public int insertSelective(Question question){return questionMapper.insertSelective(question);}

    public int deleteByPrimaryKey(Integer id){return questionMapper.deleteByPrimaryKey(id);}

    public Question selectByPrimaryKey(Integer id){return questionMapper.selectByPrimaryKey(id);}

    public int updateByPrimaryKeySelective(Question question){return questionMapper.updateByPrimaryKeySelective(question);}
}
