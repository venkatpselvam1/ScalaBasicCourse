package lectures.part2

import org.omg.CORBA.WrongTransaction

import scala.annotation.tailrec

object OOBasicsExercise extends App{
  var writer = new Writer("Venkat", "Selvam", 1990)
  var imposterWriter = new Writer("Venkat", "Selvam", 1990)
  println(writer.fullName())
  var novel1 = new Novel("My book1", 1995, writer)
  var novel2 = new Novel("My book2", 1996, writer)
  println(novel1.authorAge +", "+novel2.authorAge)
  println("Novel1 is written by nandhu selvam " + novel1.isWrittenBy("nandhu selvam"))
  println("Novel1 is written by author obj " + novel1.isWrittenBy(writer))
  println("Novel1 is written by author obj " + novel1.isWrittenBy(imposterWriter))
  println("Novel1 is written by Venakt Selvam " + novel1.isWrittenBy("Venkat Selvam"))
  var novel1cpy = novel1.copy(2005)
  println(novel1cpy.authorAge+", "+novel1cpy.isWrittenBy("Venkat Selvam"))

  var counter = new Counter(5)
  println(counter)
  counter = counter.incrementCounter()
  println(counter.n)
  counter = counter.decrementCounter()
  counter = counter.decrementCounter()
  println(counter.n)
  counter = counter.incrementCounter(5)
  println(counter.n)
  counter = counter.decrementCounter(100)
  println(counter.n)

  var counter2 = new Counter()
  counter2.incrementCounter.print
  counter2.incRec(4).incRec(3).print
  counter2.decRec(3).incrementCounter().print
}
class Writer(fn: String, ln: String, var year: Int)
{
  def fullName() = s"$fn $ln"
}

class Novel(name: String, year: Int, writer: Writer)
{
  def authorAge = year - writer.year
  def isWrittenBy(name: String) = this.writer.fullName() == name
  def isWrittenBy(writer: Writer) = this.writer == writer
  def copy(year: Int) = new Novel(name, year, writer)
}

class Counter(var n: Int = 0)
{
  def incrementCounter() = {
    println("incrementing")
    new Counter(n+1)
  }
  def decrementCounter() = {
    println("decrementing")
    new Counter(n-1)
  }
  def incrementCounter(x: Int) = new Counter(n+x)
  def decrementCounter(x: Int) = new Counter(n-x)

  def incRec(x: Int): Counter = {
    if (x == 0) this
    else incrementCounter.incRec(x - 1)
  }

  def decRec( x: Int): Counter = {
    if (x == 0) this
    else decrementCounter.decRec(x - 1)
  }
  def print = println(n)
}