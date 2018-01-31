package heu.iot.Service;

import heu.iot.Dao.FeedbackMapper;
import heu.iot.Model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/15 16:10
 */
@Service("feedbackService")
public class FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;

    public int insertSelective(Feedback feedback){
        return feedbackMapper.insertSelective(feedback);
    }

    public List<Feedback> showAll(){
        return feedbackMapper.showAll();
    }

    public Feedback selectByPrimaryKey(Integer id){
        return feedbackMapper.selectByPrimaryKey(id);
    }
}