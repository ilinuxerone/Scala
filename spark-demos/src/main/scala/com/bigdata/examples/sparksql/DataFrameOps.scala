package com.bigdata.examples.sparksql

import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/14.
  */
object DataFrameOps {
  def main(args : Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("DataFrameOps").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlC = new SQLContext(sc)

    val rawDF = sqlC.read.format("json").load("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\json\\people.json")
    //查看schema
    rawDF.printSchema()

    //获取指定列schema的类型和名字
    val name = rawDF.schema.asInstanceOf[Seq[Row]](0).asInstanceOf[StructField].name
    val datatype = rawDF.schema.asInstanceOf[Seq[Row]](0).asInstanceOf[StructField].dataType
    println("name: " + name + "dataType: "  + datatype)

    //查看内容
    rawDF.show()
    rawDF.take(1)
    rawDF.head()

   //查看某个字段
    //根据列名获取
    val singleColsByName1  = rawDF.select("name").show()
    val singleColsByName2 = rawDF.select(rawDF("name")).show()
    val singleColsByName3 = rawDF.select(rawDF.col("name")).show()

    //根据列序号获取
    val singColsByindex = rawDF.select(rawDF.schema.asInstanceOf[Seq[Row]](0).asInstanceOf[StructField].name).show()

    //过滤
    val filters = rawDF.filter(rawDF.col("age").gt(25)).show()

    //分组
   val groups =  rawDF.groupBy("age").count().show()

    //排序


    //遍历
    //foreach 处理各字段返回值  
    rawDF.select(rawDF.col("id"), rawDF.col("name"), rawDF.col("age")).foreach { x =>
    {
      //通过下标获取数据  
      println("col1: " + x.get(0) + ", col2: " + "name: " + x.get(2) + ", col3: " + x.get(2))
    }
    }

    //foreachPartition 处理各字段返回值，生产中常用的方式  
    rawDF.select(rawDF.col("id"), rawDF.col("name"), rawDF.col("age")).foreachPartition { iterator =>
      iterator.foreach(x => {
        //通过字段名获取数据  
        println("id: " + x.getAs("id") + ", age: " + "name: " + x.getAs("name") + ", age: " + x.getAs("age"))

      })
    }

    //转换



    rawDF.write.save("people")
    sc.stop()
  }

}
