package com.example.demo.producer;



import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a demo group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("test");
        // Specify name server addresses.
        producer.setNamesrvAddr("rd3:9876");
//        demo.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ " +
                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the demo instance is not longer in use.
        producer.shutdown();
    }
}
