package com.bigdata.examples.streaming

import kafka.serializer.StringDecoder
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Administrator on 2017/5/20.
  */
object KafkaDataToObject {

  //封装case clase , 需放在main之前声明
  case class Person(id: Long, name: String, age: Int)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org.apache.spark").setLevel(Level.WARN);
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.ERROR);

    val conf = new SparkConf().setAppName("KafkaDataTest2").setMaster("local[3]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(3))
    val sqlContext = new SQLContext(sc)

    val topics = Set("MyTopic")

    val brokers = "spark1:9092,spark2:9092,spark3:9092"

    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers, "serializer.class" -> "kafka.serializer.StringEncoder")
    //  接收到自kafka的数据
    val kafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

    val dStream = kafkaStream.map(_._2)

    import sqlContext.implicits._

   // val rawRdd = sc.textFile("E:\\Workspace\\Scala\\spark-demos\\src\\main\\data\\kafka\\people.txt").map(_.split(" ")).filter(_.length >= 3).map(x => {
      val personDStream = dStream.map(_.split(" ")).filter(_.length >= 3).map(x =>{
      Person(x(0).toLong, x(1).toString, x(2).toInt)
    })

    //通过foreachRDD来触发DStream的Action
    personDStream.foreachRDD(rdd => {
      import sqlContext.implicits._

      //创建临时表，这个可以与Person名一致，也可以不致
      val df = rdd.toDF().registerTempTable("Person")

      //可通过sql进行过滤或者统计处理，字段名与Person设置的一致，通过Person类的字段反射来指定的
      val rcount = sqlContext.sql("select id,name,age from Person where age>=15")

      //处理sql的RDD需通过foreachPartition来处理
      rcount.foreachPartition(iterator =>
      {
        iterator.foreach(data => {
          val id = data.get(0)
          val name = data.get(1)
          val age = data.get(2)
          println("Person.id=" + id + ", Person.name=" + name + ", Person.age=" + age)

        })

      })

    })

    ssc.start()
    ssc.awaitTermination()
  }
}
