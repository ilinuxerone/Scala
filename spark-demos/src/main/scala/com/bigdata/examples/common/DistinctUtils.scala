package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/24.
  */
object DistinctUtils {
  def main(args : Array[String]) :Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile(Array("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\file1","E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\file2").mkString(","))
    val groupRdd = rawRdd.filter(_.trim().length>0).map(line=>(line.trim,"")).groupByKey().sortByKey().keys
/*    val sortRdd = groupRdd.map(x => {
      x._1
    })
    sortRdd.foreach(println)*/
    groupRdd.foreach(println)
  }
}
