package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Administrator on 2017/5/22.
  */

/**
  * 数据说明：
  * 第15-19个字符是year
  * 第45-50位是温度表示，+表示零上 -表示零下，且温度的值不能是9999，9999表示异常数据
  * 第50位值只能是0、1、4、5、9几个数字
  */
object MaxTempresure {
  def main(args: Array[String]) = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(3))
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\tempreture")

    val yearAndTemp = rawRdd.filter(line => {
      val quality = line.substring(50, 51);
      var airTemperature = 0
      if (line.charAt(45) == '+') {
        airTemperature = line.substring(46, 50).toInt
      } else {
        airTemperature = line.substring(45, 50).toInt
      }
      airTemperature != 9999 && quality.matches("[01459]")
    }).map {
      line => {
        val year = line.substring(15, 19)
        var airTemperature = 0

        if (line.charAt(45) == '+') {
          airTemperature = line.substring(46, 50).toInt
        } else {
          airTemperature = line.substring(45, 50).toInt
        }
        (year, airTemperature)
      }
    }

    val res = yearAndTemp.reduceByKey(
      (x, y) => if (x > y) x else y
    )

    res.collect.foreach(x => println("year : " + x._1 + ", max : " + x._2))
  }

}
