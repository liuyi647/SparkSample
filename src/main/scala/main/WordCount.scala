package main

import org.apache.spark.SparkContext

/**
  * Created by OEM on 21/06/2017.
  */
object WordCount {
    def main(args: Array[String]): Unit = {
        val sc = new SparkContext("local[*]", "WordCount")
        val content = sc.textFile("../SparkSamples/data/book.txt")
        val words = content.flatMap(x => x.split("\\W+"))

        val wordCountKV = words
            .filter(word => word != "you")
            .map(x => (x.toLowerCase, 1))
            .reduceByKey((x,y) => x + y)
//        val wordCount = words.countByValue()
        val countWordKV = wordCountKV.map(x => (x._2, x._1))
//        val sorted = countByWord
        val sorted = countWordKV.sortByKey()
        sorted.foreach(x => println(x))
    }
}
