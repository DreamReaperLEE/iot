package heu.iot.Service;

import com.github.pagehelper.PageHelper;
import heu.iot.Dao.Course_EmploeeMapper;
import heu.iot.Dao.EmploeeMapper;
import heu.iot.Model.Emploee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService {
    @Value("${pageNum}")
    private int pageNum;

    @Autowired
    private EmploeeMapper emploeeMapper;

    public int deleteEmploee(Integer id){
       return emploeeMapper.deleteByPrimaryKey(id);
    }
    public int insertEmploee(Emploee emploee){return emploeeMapper.insert(emploee);}
    public Emploee selectByPrimaryKey(Integer id){return emploeeMapper.selectByPrimaryKey(id);}
    public int updateByPrimaryKey(Emploee emploee){return emploeeMapper.updateByPrimaryKey(emploee);}
    public List<Emploee> selectByEmploeePriv(Integer priv){
        return  emploeeMapper.selectByEmploeePriv(priv);

    }

    public List<Emploee> showAdminByPage(int page){
        PageHelper.startPage(page,pageNum);
        return emploeeMapper.selectByEmploeePriv(0);
    }
    public  List<Emploee> showTeacherByPage(int page){
        PageHelper.startPage(page,pageNum);
        return  emploeeMapper.selectByEmploeePriv(1);
    }
    public  List<Emploee> showStudentByPage(int page){
        PageHelper.startPage(page,pageNum);
        return  emploeeMapper.selectByEmploeePriv(2);
    }

}
