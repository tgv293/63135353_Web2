package vn.gvt.QLTB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QltbApplication {

	public static void main(String[] args) {
		SpringApplication.run(QltbApplication.class, args);
	}

}
