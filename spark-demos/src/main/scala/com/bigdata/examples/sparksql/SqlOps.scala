package com.bigdata.examples.sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by Administrator on 2017/5/14.
  */
object SqlOps {
  def main(args : Array[String]) :Unit = {
    val conf = new SparkConf().setAppName("SqlOps").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val rawDF = sqlContext.read.format("json").load("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\json\\people.json")

    //注册成临时表
    rawDF.registerTempTable("people")

    //查看schema
    rawDF.printSchema()

    //查看某个字段
    sqlContext.sql("select name from people ").show()
    //查看多个字段
    sqlContext.sql("select name,age+1 from people ").show()
    //过滤某个字段的值
    sqlContext.sql("select age from people where age>=25").show()
    //count group 某个字段的值
    sqlContext.sql("select age,count(*) cnt from people group by age").show()

    //foreach 处理各字段返回值
    sqlContext.sql("select id,name,age  from people ").foreach { x =>
    {
      //通过下标获取数据
      println("col1: " + x.get(0) + ", col2: " + "name: " + x.get(2) + ", col3: " + x.get(2))
    }
    }

    //foreachPartition 处理各字段返回值，生产中常用的方式
    sqlContext.sql("select id,name,age  from people ").foreachPartition { iterator =>
      iterator.foreach(x => {
        //通过字段名获取数据
        println("id: " + x.getAs("id") + ", age: " + "name: " + x.getAs("name") + ", age: " + x.getAs("age"))

      })
    }
    sc.stop()
  }
}
