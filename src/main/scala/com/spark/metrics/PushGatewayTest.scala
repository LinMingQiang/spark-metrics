package com.spark.metrics

import io.prometheus.client.{CollectorRegistry, Gauge}
import io.prometheus.client.exporter.PushGateway

/**
  * @author ${user.name}
  */
object PushGatewayTest {

  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

  def main(args: Array[String]) {
    val registry = new CollectorRegistry();

    val pushGateway = new PushGateway("192.168.99.194:9091")
    val g = Gauge
      .build()
      .name("test_name") // 查询专用
      .help("注释")
      .labelNames("topic", "partition") // 在展示折线图等的线
      .register(registry)

    while(true){
      for (i <- 1 to 10) {
         g.labels("test_topics", i.toString) // lable的值，一个值对应一条线
          .set(System.currentTimeMillis() % 100 * i)
      }
      pushGateway.push(registry, "lmq_job_name_pushgateway")
      Thread.sleep(500)
    }
  }

}
