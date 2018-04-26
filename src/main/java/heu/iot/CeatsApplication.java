package heu.iot;

import heu.iot.MyThread.GradePaper;
import heu.iot.MyThread.IndexThread;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "heu.iot.Dao")
@SpringBootApplication
public class CeatsApplication {

	public static void main(String[] args) {

		SpringApplication.run(CeatsApplication.class, args);

		Thread paperThread = new Thread(new GradePaper());
		paperThread.start();
		Thread indexThread = new Thread(new IndexThread());
		indexThread.start();
	}
}
