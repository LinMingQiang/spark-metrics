package com.spark.metrics

import io.prometheus.client.CollectorRegistry

class SparkJobPushGatewayGauge extends GrafanaPushGatewayTrait {

  /**
    * 构造方法
    * @param metric
    * @param help
    * @param labelNames
    * @param registry
    */
  def this(metric: String, help: String, labelNames: String*)(
      registry: CollectorRegistry) = {
    this()
    setGauge(metric, help, labelNames: _*)(registry)
  }
  // 在这里实现一些必要的方法或者工具
}
object SparkJobPushGatewayGauge {
  lazy implicit val registry: CollectorRegistry = new CollectorRegistry()
  def apply(metric: String, help: String, labelNames: String*)(
      implicit registry: CollectorRegistry): SparkJobPushGatewayGauge =
    new SparkJobPushGatewayGauge(metric, help, labelNames: _*)(registry)
}
