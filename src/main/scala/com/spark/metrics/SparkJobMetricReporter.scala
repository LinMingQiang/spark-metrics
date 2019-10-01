package com.spark.metrics

import io.prometheus.client.CollectorRegistry

class SparkJobMetricReporter {
  lazy val pushRegistry = new CollectorRegistry

}
