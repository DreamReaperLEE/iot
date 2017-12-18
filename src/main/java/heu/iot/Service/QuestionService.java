package heu.iot.Service;

import heu.iot.Dao.QuestionMapper;
import heu.iot.Model.Question;
import heu.iot.Model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public List<Question> showAllQuestion() {
        return questionMapper.showAllQuestion();
    }

}
