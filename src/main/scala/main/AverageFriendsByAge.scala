package sparkSample

import org.apache.spark.SparkContext

/**
  * Created by OEM on 21/06/2017.
  */
object AverageFriendsByAge {

  def parseLine(line : String)= {
    val fields = line.split(",")
    (fields(2).toInt, fields(3).toInt)
  }

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "FriendsByAge")
    val lines = sc.textFile("../SparkSamples/data/fakefriends.csv")
    val rdd = lines.map(parseLine)

    rdd.foreach(x => println(x))

    val totalFriends = rdd
      .mapValues(x => (x, 1))
      .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2 ))

    totalFriends.foreach(x => println(x))

    val averageFriendByAge = totalFriends.mapValues(x => x._1 / x._2)

    val ret = averageFriendByAge.collect()
    ret.sorted.foreach(println)
  }

}
