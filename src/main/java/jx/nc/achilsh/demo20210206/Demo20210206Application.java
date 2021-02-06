package jx.nc.achilsh.demo20210206;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Data
public class Demo20210206Application {

    private static ValueDemo demo;
    private static DefCfg cfgdo;

    @Autowired
    void setDemo(ValueDemo de) { //通过set自动注入（将bean 对象注入进来）
        this.demo = de;
    }
    @Autowired
    void setCfgdo(DefCfg d) {  //通过set自动注入（将bean 对象注入进来）
        cfgdo = d;
    }


    public static void main ( String[] args ) {


        SpringApplication.run ( Demo20210206Application.class , args );
        demo.display ();
        cfgdo.display ();
    }

}

@Configuration
@Slf4j
class ValueDemo {

    //@Value 用于加载配置文件 application.properties文件的中的配置项。
    // ${} 是属性占位符,里面是配置项名，后面是默认值； 在运行时计算所注入的值。
    @Value ("${server.port:8000}" )
    private int port;

    @Value("${server.host:10.12.12.12}")
    private String host;

    void display() {
        log.info("-=====> port: " + port);
        log.info("host: ===> " + host);
    }
}

//读取自定义配置文件的
@Configuration
@Slf4j
@PropertySource(value ={"classpath:valueDemo.properties"}) //这是具体配置文件位置。
@ConfigurationProperties(prefix="def.demo")   //配置文件中配置项的前缀
@Data
class DefCfg {
    private String host;
    private int port;

    public void display() {
        log.info("host: " + this.host + ", port: " + this.port);
    }

}
