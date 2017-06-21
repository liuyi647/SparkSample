package sparkSample

import org.apache.spark.SparkContext

/** Count up how many of each star rating exists in the MovieLens 100K data set. */
object RatingsCounter {

  /** Our main function where the action happens */
  def count(sc : SparkContext) {
    // Create a SparkContext using every core of the local machine, named RatingsCounter

    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("../SparkSamples/data/movies.data")

    // Convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)
    val ratings = lines.map(x => x.toString().split("\t")(2))

    // Count up how many times each value (rating) occurs
    val results = ratings.countByValue()

    // Sort the resulting map of (rating, count) tuples
    val sortedResults = results.toSeq.sortBy(_._1)

    // Print each result on its own line.
    sortedResults.foreach(println)
  }
}
