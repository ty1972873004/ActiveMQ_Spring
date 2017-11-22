package cn.javaxxw.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import cn.javaxxw.model.Order;
import cn.javaxxw.util.JsonUtils;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2017-11-22 17:32
 **/
public class DefaultMessageQueueListener  implements MessageListener {

    private static final Log logger = LogFactory.getLog(DefaultMessageQueueListener.class);


    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                final String ms = textMessage.getText();
                Order order = JsonUtils.jsonToPojo(ms,Order.class);
                logger.info("接收到消息"+order.toString());

                 //TODO 接收到消息后这里进行业务处理
            }catch (Exception e){
                logger.error("have a error");
            }

        }


    }
}
