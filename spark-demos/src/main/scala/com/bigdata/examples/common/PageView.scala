package com.bigdata.examples.common

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/27.
  */
object PageView {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("pvtest").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val rawRdd = sc.textFile("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\access.txt ")
    //filter 过滤长度小于0， 过滤不包含GET与POST的URL
    val filtered = rawRdd.filter(_.length()>0).filter( line => (line.indexOf("GET")>0 || line.indexOf("POST")>0) )

    //转换成键值对操作
    val res = filtered.map( line => {
      if(line.indexOf("GET")>0){ //截取 GET 到URL的字符串
        (line.substring(line.indexOf("GET"),line.indexOf("HTTP/1.0")).trim,1)
      }else{   //截取 POST 到URL的字符串
        (line.substring(line.indexOf("POST"),line.indexOf("HTTP/1.0")).trim,1)
      }//最后通过reduceByKey求sum
    }).reduceByKey(_+_)

    //触发action事件执行
    res.foreach(println)
  }
}
