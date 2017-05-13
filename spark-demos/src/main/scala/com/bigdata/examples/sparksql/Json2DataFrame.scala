package com.bigdata.examples.sparksql

import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/13.
  */
object Json2DataFrame {
  def main(arg : Array[String]) :Unit ={
    val conf = new SparkConf( ).setAppName("Json2DataframeDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    //jons对象不支持跨行
    val rawdf = sqlContext.read.format("json").load("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\json\\tweet.json")
    rawdf.printSchema()
/*    |-- content: array (nullable = true)
    |    |-- element: struct (containsNull = true)
    |    |    |-- bar: string (nullable = true)
    |    |    |-- foo: long (nullable = true)
    |-- dates: array (nullable = true)
    |    |-- element: string (containsNull = true)
    |-- reason: string (nullable = true)
    |-- status: string (nullable = true)
    |-- user: string (nullable = true)*/
    //val rdd = rawRdd.select(rawRdd.schema.asInstanceOf[Seq[Row]](0).name)
    val rdd = rawdf.select(rawdf.schema.asInstanceOf[Seq[Row]](4).asInstanceOf[StructField].name,rawdf.schema.asInstanceOf[Seq[Row]](0).asInstanceOf[StructField].name).map(row => {
      val user = row.getString(0)
      val content = row.getAs[Seq[Row]](1).map(nestRow => {
        nestRow match {
          case  Row(bar : String, foo : Long) => f"$bar=$foo"
        }
      })mkString(",")
      Array(user, content).mkString("|")
      //gT35Hhhre9m|val1=123,val2=456,val3=789,val4=124,val5=126
    })

    rdd.saveAsTextFile("test")
    sc.stop()
  }
}
