package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/24.
  */
object SortUtils {
  def main(args : Array[String]) : Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile(Array("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\sort1","E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\sort2").mkString(","))
    val sortedRdd = rawRdd.filter(_.trim().length>0).map(line=>(line.trim.toInt,1)).sortByKey(false).keys.zipWithIndex().map({x => (x._2 + 1, x._1)})

    sortedRdd.foreach(println)

  }
}
