package sparkSample

import org.apache.spark.SparkContext

/**
  * Created by OEM on 19/06/2017.
  */
class SparkSample(sc : SparkContext) {
  def doMap() : Unit = {
    //test create a rdd, and map function on each element of the rdd
    val rdd = sc.parallelize(List(1,2,3,4))
    val squares = rdd.map(x=>x*x)
    squares.foreach(x => println(x))
  }

  def doMapUsingFunction() : Unit = {
    //test call object in function
    MapCube.doMapUsingFunction(sc)
  }

  def readTxt() : Unit= {
    //test read text file, and count lines
    val logFile = "C:/spark/README.md" // Should be some file on your system
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
