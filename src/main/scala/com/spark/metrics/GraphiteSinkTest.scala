package com.spark.metrics

import org.apache.spark.{SparkConf, SparkContext}

object GraphiteSinkTest {
  def main(args: Array[String]): Unit = {
    val spark = new SparkContext(new SparkConf())
    spark.parallelize(1 to 10).collect().foreach(println)
  }
}