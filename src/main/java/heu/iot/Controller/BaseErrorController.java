package heu.iot.Controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * @Author: Sumail-Lee
 * @Version: V1.0.0
 * @Description:
 * @Since: 2018/1/31 16:14
 */
@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {


    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }

}
