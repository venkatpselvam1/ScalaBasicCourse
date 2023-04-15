package lectures.part3

import scala.util.{Failure, Random, Success, Try}

object A13_HandlingFailureWithTry extends App{
println("Try is the scala function which add try - catch to our unsafe code and returns success or failure")
  var success = Success(3)
  var failure = Failure(new RuntimeException("This is runtime exception"))
  println(success)
  println(failure)

  println("This above shown how to create a success and failure objects. But usually it will be autocreated when you use try")

  def unsafeMethod(): String = throw new Exception("No string for you")
  var attempt = Try(unsafeMethod())
  println(attempt)
  println("Notice we didn't get the exception above")

  println("synthetic sugar")
  var attempt2 = Try{
     throw new Exception("venkat")
  }
  println(attempt2)

  //utilities
  if(attempt.isSuccess) println("attempt is success")
  else println("attempt is failure")

  //or else
  def backUpMethod():String = "back up string"
  var fallbackTry = attempt.orElse(Try(backUpMethod()))
  println(fallbackTry)

  //exapple
  def betterUnSafeMethod:Try[String] = Failure(new Exception("exception"))
  def betterBackupMethod:Try[String] = Success("My back up")
  var result = betterUnSafeMethod orElse betterBackupMethod
  println(result)

  //map, filter, flatmap
  println(success.map(x => x * 10))
  println(success.filter(x =>x%2==0))
  println(success.flatMap(x => Success(12)))

  //failure
  println(attempt.map(x => x * 10))
  println(attempt.filter(x => x.length == 2))
  println(attempt.flatMap(x => Success(12)))

  //For comprehension
  //Exercise
  var host = "localhost"
  var port = "145"
  def render(html: String): Unit  = println(html)

  class Connection
  {
    def get(url: String): String = {
      var random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html></html>"
      else throw new Exception("connection error")
    }
  }
  object HttpService{
    def getConnection(host: String, port: String) = {
      var random = new Random(System.nanoTime())
      if (random.nextBoolean()) new Connection()
      else throw new Exception("connection error  2")
    }
  }


  //solution - 1: using for comprehension
  println("Solution 1")
  var connection = Try( HttpService.getConnection(host, port) )
  var html = connection.flatMap(x => Try(x.get("venkaturl")) )
  html.foreach(render)

  //solution - 2: using map, flatmap, filter, foreach
  println("Solution 2")
  Try(HttpService.getConnection(host, port)).flatMap(x => Try(x.get("venkat"))).foreach(render)

  //solution - 3: using for comprehension
  println("Solution 3")
  for{
    connection <- Try(HttpService.getConnection(host, port))
    html <- Try(connection.get("venkat"))
  } render(html)
}
