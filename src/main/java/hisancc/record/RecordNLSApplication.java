package hisancc.record;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * by zh
 * 2019/11/25
 **/
@SpringBootApplication
@MapperScan("hisancc.record.mapper")
public class RecordNLSApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordNLSApplication.class, args);
    }
}
