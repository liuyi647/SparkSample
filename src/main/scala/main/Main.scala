package sparkSample

import org.apache.spark.{SparkConf, SparkContext}

object Main {
  val argMap : String = "-map"
  val argTxt : String = "-txt"
  val argMapCube : String = "-mapCube"
  val argRateCount : String = "-rc"

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sample = new SparkSample(sc)
    args(0) match {
      case `argMap` =>
        sample.doMap()
      case `argTxt` =>
        sample.readTxt()
      case  `argMapCube` =>
        sample.doMapUsingFunction()
      case `argRateCount` =>
        RatingsCounter.count(sc)
      case _ => {
        println(argMap + " : square sample")
        println(argTxt + " : get readme")
      }
    }
  }

}
