package com.spark.metrics

import io.prometheus.client.CollectorRegistry

object GrafanaGaugeManager {
  val metricPre = "sparkstream"
  val registry = new CollectorRegistry();
  // qurey Name 下的各个 topic 的 kafka的输入速率
  lazy val kafkaInputRateGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_kafka_rate",
                        s"kafka 写入输入",
                        "query_name",
                        "topic")(registry)

  // 记录kafka的堆积情况
  lazy val kafkaLagGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_kafka_lag",
                        s"各个query 下的 各 topic 的各 partition的堆积情况",
                        "query_name",
                        "topic",
                        "partition")(registry)

}
