package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/24.
  */
object
MaxMinUtils {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile(Array("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\eightteen_b.txt ", "E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\eightteen_a.txt ").mkString(","))
    val array1 = rawRdd.map(_.toInt).collect()
    val max = array1.max
    val min = array1.min
    array1.foreach(println)
    println(max + " : " + min)


    val res = rawRdd.filter(_.trim().length>0).map(line => ("key",line.trim.toInt)).groupByKey().map(x=> {
      val max = x._2.max
      val min = x._2.min

      Array(Array("Max", max).mkString(" "), Array("Min", min).mkString(" ")).mkString("\n")
    }).foreach(println)
  }
}
