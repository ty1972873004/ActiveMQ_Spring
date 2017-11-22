package cn.javaxxw.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

import cn.javaxxw.MQProducer;
import cn.javaxxw.model.Order;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-22 17:22
 **/
public class ProducerTest {

    private static final Log logger = LogFactory.getLog(ProducerTest.class);

    public static void main(String[] args)  {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();

            MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
            // 邮件发送
            Order order = new Order();
            order.setOrderNo("20171122172500001");
            order.setPrice(new BigDecimal(2.00));
            order.setTitle("商品标题");
            order.setDesc("商品内容");
            mqProducer.sendMessage(order);
            Thread.sleep(100);
            context.stop();
        } catch (Exception e) {
            logger.error("MQProducer context start error:", e);
            System.exit(0);
        } finally {
            logger.info("System.exit");
            System.exit(0);
        }
    }




}
