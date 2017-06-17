name := "SparkSamples"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.novocode"   %  "junit-interface" % "0.11"   % Test,  // required only for plain JUnit testing
  "org.scalatest"  %% "scalatest"       % "3.0.1"  % Test,
  "org.scalacheck" %% "scalacheck"      % "1.13.4" % Test
)

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1"