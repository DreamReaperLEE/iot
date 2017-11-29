package heu.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "heu.iot.Dao")
@SpringBootApplication
public class CeatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeatsApplication.class, args);
	}
}
