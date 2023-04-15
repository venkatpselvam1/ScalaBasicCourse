package lectures.part3

import scala.util.Random

object A12_Options extends App{
  println("Options avoid null checks in our code")
  var a = 5
  println(a.getClass)
  var b = Option(5)
  println(b.getClass)
  var c = Some(5)
  println(c.getClass)
  println(a+", "+b+", "+c)

  def unSafeMethod() : String = null
  def backUp() : String = "back"
  def betterUnSafeMethod() : Option[String] = Option(null)

  def callerUsingUnSafe(str: String) = {
    str.length
  }

  def callerUsingBetterUnSafe(str: Option[String]) = {
    if(str.isEmpty) 0
    else str.get.length
  }



  //Chained methods
  def betterBackUp(): Option[String] = Option("")
  var result = Option(unSafeMethod()) orElse Option(backUp())
  var result1 = betterUnSafeMethod().orElse(betterBackUp())

  var myFirstOption = Option(4)
  println(myFirstOption)
  println(myFirstOption.map(x => x* 4))
  println(myFirstOption.filter(x => x% 2 == 0))
  println(myFirstOption.flatMap(x => Option(x% 2 == 0)) )

  //use map, flatmap, filter
  var config: Map[String, String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "80"
  )
  class Connection{
    def connect = "connected"
  }

  object Connection{
    var rand = new Random(System.nanoTime())
    def apply(host: String, port: String) : Option[Connection] = {
      if(rand.nextBoolean()) None
      else Some(new Connection())
    }

    def apply(host: Option[String], port: Option[String]): Option[Connection] = {
      if (rand.nextBoolean()) None
      else Some(new Connection())
    }
  }

  println("map")
  var connection = Connection(config.get("host"), config.get("port") )
  if(connection.isEmpty)
  {
    println("unable to connect to server")
  }else{
    println("connected successfully")
  }

  println("The below shows the way the instructor written it")
  var host = config.get("host")
  var port = config.get("port")
  var connection1 = host.flatMap(h => port.flatMap(p => Connection(h, p) ) )
  var connectionStatus = connection1.map(x => x.connect)
  println(connectionStatus)
  connectionStatus.foreach(println)

  println("below is the chained solution")
  config.get("host").flatMap(h => config.get("port").flatMap(p => Connection(h, p)))
    .map(x => x.connect)
    .foreach(println)

  println("For comprehension  solution")
  var forConnectionStatus = for{
    h <- config.get("host")
    p <- config.get("port")
    connection <- Connection(h, p)
  }yield connection.connect
  forConnectionStatus.foreach(println)

}
