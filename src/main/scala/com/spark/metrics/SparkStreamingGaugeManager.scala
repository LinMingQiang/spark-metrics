package com.spark.metrics

import io.prometheus.client.CollectorRegistry

// 统一管理gauge
class SparkStreamingGaugeManager(metricPre: String,
                                 registry: CollectorRegistry) {
  // 监控层级为： applicationId -> query_name -> topic ->partitionid -> offset
  // qurey Name 下的各个 topic 的 kafka的输入速率
  lazy val kafkaInputRateGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_kafka_rate",
                             s"kafka 写入输入",
                             "applicationId",
                             "query_name",
                             "topic")(registry)
  // 记录kafka的堆积情况
  lazy val kafkaLagGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_kafka_lag",
                             s"各个query 下的 各 topic 的各 partition的堆积情况",
                             "applicationId",
                             "query_name",
                             "topic",
                             "partition")(registry)
// 各个queray下的最新的kafka偏移量
  lazy val kafkaLeatestOffsetGuage =
    SparkJobPushGatewayGauge(s"${metricPre}_kafka_leatestOffset",
                             s"sparkstream_kafka_leatestOffset",
                             "applicationId",
                             "query_name",
                             "topic",
                             "partition")(registry)
  // 触发executor前的执行时间
  lazy val triggerExecutionGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_durationMs_triggerExecution",
                             s"getOffset + getBatch + addBatch",
                             "applicationId",
                             "query_name")(registry)
  // 获取batch的时间
  lazy val getBatchGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_durationMs_getBatch",
                             "create a DataFrame from source.",
                             "applicationId",
                             "query_name")(registry)
  // 添加batch
  lazy val addBatchGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_durationMs_addBatch",
                             "run the DataFrame in a sink",
                             "applicationId",
                             "query_name")(registry)

  lazy val numInputRowsGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_numInputRows",
                             "numInputRows",
                             "applicationId",
                             "query_name")(registry)

  lazy val processedRowsPerSecondGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_processedRowsPerSecond",
                             "sparkstreaming_processedRowsPerSecond",
                             "applicationId",
                             "query_name")(registry)

  lazy val inputRowsPerSecondGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_inputRowsPerSecond",
                             "sparkstreaming_inputRowsPerSecond",
                             "applicationId",
                             "query_name")(registry)

  lazy val batchIdGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_batchId",
                             "sparkstreaming_batchId",
                             "applicationId",
                             "query_name")(registry)
  // 某个query下的所有topic的开始offset
  lazy val startOffsetGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_startOffset",
                             "sparkstreaming_startOffset",
                             "applicationId",
                             "query_name",
                             "topic",
                             "partition")(registry)

  lazy val endOffsetGauge =
    SparkJobPushGatewayGauge(s"${metricPre}_endOffset",
                             "sparkstreaming_endOffset",
                             "applicationId",
                             "query_name",
                             "topic",
                             "partition")(registry)
}
