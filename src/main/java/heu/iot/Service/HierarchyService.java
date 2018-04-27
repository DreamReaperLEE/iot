package heu.iot.Service;

import heu.iot.Dao.HierarchyMapper;
import heu.iot.Model.Hierarchy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/8 16:37
 */
@Service("hierarchyService")
public class HierarchyService {
    @Autowired
    private HierarchyMapper hierarchyMapper;

    public List<Hierarchy> showAll(){
        return hierarchyMapper.showAll();
    }

    public Hierarchy selectByPrimaryKey(Integer id){
        return hierarchyMapper.selectByPrimaryKey(id);
    }

    public int deleteByPrimaryKey(Integer id){return hierarchyMapper.deleteByPrimaryKey(id);}

    public int insertSelective(Hierarchy hierarchy){return hierarchyMapper.insertSelective(hierarchy);}

    public int updateByPrimaryKeySelective(Hierarchy hierarchy){return hierarchyMapper.updateByPrimaryKeySelective(hierarchy);}

}
