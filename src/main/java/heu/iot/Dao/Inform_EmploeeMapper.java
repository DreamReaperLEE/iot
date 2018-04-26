package heu.iot.Dao;

import heu.iot.Model.Inform_Emploee;

import java.util.List;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2017/12/27 19:35
 */
public interface Inform_EmploeeMapper {
    List<Inform_Emploee> showRecent(Integer num);

    List<Inform_Emploee> showAll();


    List<Inform_Emploee> showAdminAll();
}
