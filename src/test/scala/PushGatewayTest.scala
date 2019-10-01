import com.spark.metrics.SparkJobPushGatewayGauge
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.PushGateway

/**
  * @author ${user.name}
  */
object PushGatewayTest {

  /**
    * job: 一般一个job一个job_name。然后用 metric 来区分
    *
    * @param args
    */
  def main(args: Array[String]) {
    val registry = new CollectorRegistry();
    val pushGateway = new PushGateway("192.168.99.188:9091")

    val kafkaOffset = new SparkJobPushGatewayGauge
    kafkaOffset.setGauge("kafka_topic_partition_offset",
                         "注释",
                         "topic",
                         "partition")(registry)
    while (true) {
      for (i <- 1 to 10) {
        println(System.currentTimeMillis() % 19394 * i)
        kafkaOffset.push(System.currentTimeMillis() % 19394 * i,
                         "topicName5",
                         i.toString)
      }
      pushGateway.push(registry, "job_name_pushgateway2")
      Thread.sleep(500)
    }
  }

}
