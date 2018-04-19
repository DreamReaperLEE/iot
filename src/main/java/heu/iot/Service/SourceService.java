package heu.iot.Service;

import heu.iot.Dao.Course_SourceMapper;
import heu.iot.Dao.SourceMapper;
import heu.iot.Model.Course_Source;
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

    @Autowired
    private Course_SourceMapper course_sourceMapper;

    public List<Source> showCourseDetail(Integer id,Integer lesson){
        return sourceMapper.selectByCourseLesson(id,lesson);
    }

    //展示所有的课程资源
    public List<Source> showAllSource(){
        return sourceMapper.showAllSource();
    }

    public Source selectByPrimaryKey(Integer id)
    {
        return sourceMapper.selectByPrimaryKey(id);
    }

    public List<Source> selectByCid(Integer cid){
        return sourceMapper.selectByCid(cid);
    }

    public List<Source> selectByCourseLesson(Integer cid,Integer lesson){return sourceMapper.selectByCourseLesson(cid,lesson);}

    public int insertSelective(Source source){
        return sourceMapper.insertSelective(source);
    }

    //返回每一章，且每章只显示一行，唯一！
    public List<Course_Source> showOnlyLesson(Integer id)
    {
        return course_sourceMapper.showOnlyLesson(id);
    }

    public List<Course_Source> showSpecialLesson(Integer cid,Integer lesson)
    {
        return course_sourceMapper.showSpecialLesson(cid,lesson);
    }

    public List<Course_Source> showSpecialLessonType0(Integer cid,Integer lesson)
    {
        return course_sourceMapper.showSpecialLessonType0(cid,lesson);
    }

    public Source showSourceContent(Integer id, Integer lesson, String topic)
    {
        return sourceMapper.selectByPrimaryKeyAndLowKeyAndTopic(id,lesson,topic);
    }

    public int addSource(Source source) throws Exception
    {
        return sourceMapper.insert(source);
    }

    public List<Course_Source> showAllLessonSource(Integer id){
        return course_sourceMapper.showAllLessonSource(id);
    }

    public int deleteByPrimaryKey(Integer id)
    {
        return sourceMapper.deleteByPrimaryKey(id);
    }

    public int deleteByPrimaryKeyAndLowKey(Integer id,Integer lesson){
        return sourceMapper.deleteByPrimaryKeyAndLowKey(id,lesson);

    }
    public int deleteSource(Integer id,Integer lesson,String topic)
    {
        return sourceMapper.deleteByPrimaryKeyAndLowKeyAndTopic(id,lesson,topic);
    }



    public List<Course_Source> showAllSourse()
    {
        return course_sourceMapper.showAllSource();
    }
    public Source selectByPrimaryKeyAndLessonAndTopic(Integer id,Integer lesson,String topic) {
        return sourceMapper.selectByPrimaryKeyAndLowKeyAndTopic(id,lesson,topic);
    }
}
