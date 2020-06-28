package club.iexhibition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@MapperScan(value = "club.iexhibition.MonsterIexhibition.MainForce.*.*.Mapper")
@ComponentScan(basePackages = {"club.iexhibition.*"})
public class IexhibitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(IexhibitionApplication.class, args);
    }
}

