package main

import breeze.linalg.min
import org.apache.spark.SparkContext

/**
  * Created by OEM on 21/06/2017.
  */
object MinTemperature {
    def parseLine(line : String)= {
        val fields = line.split(",")
        (fields(0), fields(2), fields(3).toInt)
    }

    def main(args: Array[String]): Unit = {
        val sc = new SparkContext("local[*]", "MinTemperature")
        val lines = sc.textFile("../SparkSamples/data/1800.csv")

       // lines.foreach(x => println(x))

        val parsedLines = lines.map(parseLine)
        val rdd = parsedLines
          .filter(x => x._2 == "TMIN")
          .map(x => (x._1, x._3.toFloat))

        //rdd.foreach(x => println(x))

        val minByKey = rdd.reduceByKey((x,y) => min(x, y))
        minByKey.foreach(x => println(x))

        val collectResult = minByKey.collect()
        for (item <- collectResult){
            val station = item._1
            val minTem = item._2
            println(s"$station's min temperature is $minTem")
        }

    }
}
