package com.spark.listener

import org.apache.spark.sql.streaming.StreamingQueryListener

/**
  * listener执行方式为阻塞，队列，单例
  */
class StreamingGrafanaMetricListener extends StreamingQueryListener {
  lazy val grafanaGauge = new StreamingListenerMetricReporter
  override def onQueryStarted(
      event: StreamingQueryListener.QueryStartedEvent): Unit = ???

  override def onQueryProgress(
      event: StreamingQueryListener.QueryProgressEvent): Unit = {
    val progress = event.progress
    val queryName = event.progress.name
    queryName match {
      case "a" =>
        println(queryName)

      case "b" => println(queryName)
    }
  }

  override def onQueryTerminated(
      event: StreamingQueryListener.QueryTerminatedEvent): Unit = ???
}
