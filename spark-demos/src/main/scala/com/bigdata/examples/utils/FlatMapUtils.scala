package com.bigdata.examples.utils

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/19.
  */
object FlatMapUtils {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[4]")
    val sc = new SparkContext(conf)

    val ListToRdd = Seq((List("A", "B", "C"), "X"), (List("C", "D", "E"), "Y"))

    val rdd = sc.parallelize(ListToRdd)

    val listResult = rdd.flatMap {
      case (list, label) => {
        list.map((_, label))
      }
    }
    listResult.foreach(println)


    val TupleToRdd = Seq((("A", ("B", 1)), ("C", ("D", 2))))
    var tupleRdd = sc.parallelize(TupleToRdd)
    val tupleResult = tupleRdd.flatMap(item => {
      item match {
        case (tuple1, tuple2) => Array(tuple1, tuple2)
        case _ => Seq()
      }
    })
    tupleResult.foreach(println)

    sc.stop()
  }
}

