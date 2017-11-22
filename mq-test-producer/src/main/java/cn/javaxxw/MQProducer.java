package cn.javaxxw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import cn.javaxxw.model.Order;
import cn.javaxxw.util.JsonUtils;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-22 17:03
 **/
@Service("mqProducer")
public class MQProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息.
     * @param order
     */
    public void sendMessage(final Order order) {

        //发送消息到MQ
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JsonUtils.objectToJson(order));
            }
        });

    }



}
