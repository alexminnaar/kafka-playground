
import java.util.{Date, Properties}

import kafka.javaapi.producer.Producer
import kafka.producer.KeyedMessage
import kafka.producer.ProducerConfig

object SimpleProducer extends App {


  val t = args(0)
  val mCount = args(1).toInt


  val props = new Properties()

  props.put("metadata.broker.list", "localhost:9092")
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  props.put("request.required.acks", "1")
  val config = new ProducerConfig(props)

  val producer = new Producer[String, String](config)

  publishMessage(t, mCount)

  def publishMessage(topic: String, messageCount: Int): Unit = {


    for (mCount <- 0 until messageCount) {

      val runtime = new Date().toString

      val msg = "Message Publishing Time - " + runtime

      println(msg)

      val data = new KeyedMessage[String, String](topic, msg)

      producer.send(data)

    }

    producer.close

  }
}


