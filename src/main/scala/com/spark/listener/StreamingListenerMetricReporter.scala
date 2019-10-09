package com.spark.listener

import com.spark.metrics.SparkStreamingGaugeManager
import io.prometheus.client.CollectorRegistry

//不同的query name 具有不同的实例
class StreamingListenerMetricReporter {
  lazy val manager =
    new SparkStreamingGaugeManager("sparkstreaming", new CollectorRegistry)

  def report(): Unit ={


  }
}
