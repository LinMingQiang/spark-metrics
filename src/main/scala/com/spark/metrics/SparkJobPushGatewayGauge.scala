package com.spark.metrics

import io.prometheus.client.CollectorRegistry

class SparkJobPushGatewayGauge extends GrafanaPushGatewayTrait {
  def this(metric: String, help: String, labelNames: String*)(
      registry: CollectorRegistry) = {
    this()
    setGauge(metric, help, labelNames: _*)(registry)
  }
}
object SparkJobPushGatewayGauge {
  lazy implicit val registry: CollectorRegistry = new CollectorRegistry()
  def apply(metric: String, help: String, labelNames: String*)(
      implicit registry: CollectorRegistry): SparkJobPushGatewayGauge =
    new SparkJobPushGatewayGauge(metric, help, labelNames: _*)(registry)
}
