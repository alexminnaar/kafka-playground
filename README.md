# How to Run Locally

1.  Download kafka [here](http://kafka.apache.org/downloads.html)
2.  Run zookeeper

    ```bin/zookeeper-server-start.sh config/zookeeper.properties```
3.  Run kafka

    ```bin/kafka-server-start.sh config/server.properties```

4.  Add some kafka topic - let's call it ```kafkatopic```

    ```bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic kafkatopic```
    
    where zookeeper is running on port 2181 on localhost, a replication factor of 1, and 3 partitions.
    
5.  Run ```SimpleHLConsumer```

    ```sbt run SimpleHLConsumer localhost:2181 testgroup kafkatopic```
    
    where ```localhost:2181``` is where zookeeper is running, ```testgroup``` is the groupid of the consumer, and ```kafkatopic``` is the kafka topic that is being consumed (this topic was created in the previous step).

6.  Run ```SimpleProducer```

    ```sbt run SimpleProducer kafkatopic 4```
    
    where ```kafkatopic``` is the kafka topic being written to, and 4 is the number of messages pushed. 