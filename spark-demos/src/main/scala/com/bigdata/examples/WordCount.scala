package com.bigdata.examples

import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by Administrator on 2017/4/26.
  */
object WordCount {
  def main(args: Array[String]) {
 /*   if (args.length == 0) {
      System.err.println("Usage: SparkWordCount <inputfile> <outputfile>")
      System.exit(1)
    }*/

    val conf = new SparkConf().setAppName("SparkWordCount").setMaster("local")
    val sc = new SparkContext(conf)

    val data = sc.parallelize(List((1,3),(1,2),(1, 4),(2,3),(2,4)))
    def seqOp(a:Int, b:Int) : Int ={
      println("seq: " + a + "\t " + b)
      math.max(a,b)
    }

    def combineOp(a:Int, b:Int) : Int ={
      println("comb: " + a + "\t " + b)
      a + b
    }

    val localIterator=data.aggregateByKey(1)(seqOp, combineOp).toLocalIterator
    for(i<-localIterator) println(i)
    sc.stop()
  }
}
