package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/24.
  */
object AverageUtils {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawRdd = sc.textFile(Array("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\math", "E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\china","E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\english").mkString(","))
    val averageRdd = rawRdd.filter(_.trim.length>0).map(_.trim.split(" ")).map(x => (x(0), x(1).toInt)).groupByKey()
      .map(x => {
        val name = x._1
        val score = x._2.asInstanceOf[Seq[Int]].sum
        val max = x._2.asInstanceOf[Seq[Int]].max
        val min = x._2.asInstanceOf[Seq[Int]].min
        val count = x._2.asInstanceOf[Seq[Int]].length
        val avg = score / count
        Array(name, avg).mkString(" ")
      })

    averageRdd.foreach(println)
  }
}
