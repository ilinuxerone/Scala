package com.bigdata.examples.sparksql

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType
import org.apache.spark.sql.types._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SQLContext, SaveMode}

/**
  * Created by Administrator on 2017/5/14.
  */
object Rdd2DataFrameByReflect {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DataFrameTest3").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val rawRdd = sc.textFile("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\text\\people.txt")

    val rowRdd = rawRdd.mapPartitions(Iterator => {
      Iterator.map(row => {
        row.split(",")
      }).map(x => {
        Row(x(0).trim.toInt, x(1).trim, x(2).trim.toInt)
      })
    })

    val schema = StructType(Seq(StructField("id", IntegerType, false), StructField("name", StringType, false), StructField("age", IntegerType, false)))

    val df = sqlContext.createDataFrame(rowRdd, schema)
    /**
      * Tips:手动编程方式构建rowRdd和schema生成dataframe时要先对数据进行过滤，异常空值转换，比如如果为double类型，则实际值不能为""
      */
    df.printSchema()

    df.write.mode(SaveMode.Overwrite).format("json").save("perple1")

    sc.stop()
  }
}
