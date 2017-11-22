package cn.javaxxw.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-22 17:41
 **/
public class ConsumerTest {
    private static final Log logger = LogFactory.getLog(ConsumerTest.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
        } catch (Exception e) {
            logger.error("==>MQ context start error:", e);
            System.exit(0);
        }
    }
}
