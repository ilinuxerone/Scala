package com.bigdata.examples.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/14.
  */
object Rdd2DataFrameByCaseClass {

  case class People(id: Int, name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrameTest3").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val rawRdd = sc.textFile("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\people.txt")

    //这里需要隐式转换一把
    import sqlContext.implicits._

    val df = rawRdd.map(x => {
      x.split(",")
    }).map(data => {
      People(data(0).trim().toInt, data(1).trim(), data(2).trim().toInt)
    }).toDF()
    df.registerTempTable("people")

    df.show()
    df.printSchema()
    /**
      * Tips:手动编程方式构建rowRdd和schema生成dataframe时要先对数据进行过滤，异常空值转换，比如如果为double类型，则实际值不能为""
      */
    sc.stop()
  }



}
