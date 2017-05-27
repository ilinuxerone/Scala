package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/24.
  */
object TopNUtils {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile(Array("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\a.txt ", "E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\b.txt ").mkString(","))
    val sortRdd = rawRdd.filter(x => (x.trim().length>0) && (x.split(",").length==4)).map(_.split(",")(2)).map(x=>{
      (x.toInt, "")
    }).sortByKey(false).map(x=>x._1).take(5)
    sortRdd.foreach(println)
  }
}
