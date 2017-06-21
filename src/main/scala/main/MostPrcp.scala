package main

import breeze.linalg.max
import org.apache.spark.SparkContext

/**
  * Created by OEM on 21/06/2017.
  */
object MostPrcp {
    def parseLine(line : String)= {
        val fields = line.split(",")
        (fields(1), fields(2), fields(3).toInt)
    }

    def main(args: Array[String]): Unit = {
        val sc = new SparkContext("local[*]", "MostPrcp")
        val lines = sc.textFile("../SparkSamples/data/1800.csv")
        val parsedLines = lines.map(parseLine)
        val rdd = parsedLines
          .filter(x => x._2 == "PRCP")
          .map(x => (x._1, x._3))

        //rdd.foreach(x => println(x))

        val maxPrcpByDate = rdd.reduceByKey((x,y) => max(x, y))
        maxPrcpByDate.foreach(x => println(x))
        val dateByPrcp = maxPrcpByDate.map(x => (x._2, x._1))

        val collectResult = dateByPrcp.collect()
        collectResult.sorted.foreach(println)
    }
}
