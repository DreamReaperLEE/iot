package heu.iot.Service;

import heu.iot.Dao.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/27 15:27
 */
@Service("recordService")
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    public int countLearnNum(int sid){
        return recordMapper.countLearnNum(sid);
    }
}
