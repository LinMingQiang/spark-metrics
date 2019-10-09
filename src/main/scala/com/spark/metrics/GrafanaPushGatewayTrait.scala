package com.spark.metrics

import io.prometheus.client.{CollectorRegistry, Gauge}

trait GrafanaPushGatewayTrait {
  var gauge: Gauge = _

  /**
    * 初始化一个gauge
    * @param metric
    * @param help
    * @param labelNames
    * @param registry
    * @return
    */
  def setGauge(metric: String, help: String, labelNames: String*)(
      registry: CollectorRegistry) = {
    gauge = Gauge
      .build()
      .name(metric)
      .help(help)
      .labelNames(labelNames: _*)
      .register(registry)
    gauge
  }

  /**
    * set value
    * @param value
    * @param labelValues
    */
  def push(value: Double, labelValues: String*) = {
    gauge
      .labels(labelValues: _*)
      .set(value)
  }
}
