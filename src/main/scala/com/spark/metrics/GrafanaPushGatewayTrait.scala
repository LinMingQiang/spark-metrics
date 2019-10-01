package com.spark.metrics

import io.prometheus.client.{CollectorRegistry, Gauge}

trait GrafanaPushGatewayTrait {
  var gauge: Gauge = _
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

  def push(value: Double, labelValues: String*) = {
    gauge
      .labels(labelValues: _*)
      .set(value)
  }
}
