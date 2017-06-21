package sparkSample

import org.apache.spark.SparkContext

/**
  * Created by OEM on 19/06/2017.
  */
object MapCube {
  def doMapUsingFunction(sc : SparkContext) : Unit = {
    val rdd = sc.parallelize(List(1,2,3,4))
    val cube = rdd.map(cubeIt)
    cube.foreach(x => println(x))
  }

  def cubeIt (x : Int) : Int = {
    return x*x*x
  }
}
