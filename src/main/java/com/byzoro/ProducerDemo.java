package com.byzoro;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

public class ProducerDemo {

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.124.100:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
//            for (int i = 0; i < 10; i++) {
                String msg = "This is Message " ;
                producer.send(new ProducerRecord<String, String>("xiaopang1","20"));
                System.out.println("Sent:" + msg);
//            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
//            producer.close();
        }
        //列出topic的相关信息
        List<PartitionInfo> partitions = new ArrayList<PartitionInfo>() ;
        partitions = producer.partitionsFor("xiaopang1");
        for(PartitionInfo p:partitions)
        {
            System.out.println(p);
        }
    }
}
