package lectures.part4

import lectures.part4.A01_PatternMatching.matcher

import scala.util.Random

object A01_PatternMatching extends App{
  var rand = new Random()
  var randValue = rand.nextInt(10)

  var getStatus = randValue match {
    case 1 => "One"
    case 2 => "error"
    case 3 => "try later"
    case _ => "the others - default"//_ matches all
  }
  print(randValue)
  println(getStatus)

  /*
    1. cases are matched in order
    2. what if no cases match? MatchError
    3. type of the PM expressoion? unified type of all the types in all the cases
    4. PM works really well with case classes*
   */

  sealed class Animal
  case class Dog(a: String) extends Animal
  case class Cat(b: Int) extends Animal
  var dog: Animal = new Dog("test")
  dog match {
    case Dog(a) => println(s"I am the dog $a")
  }

  //don't use pattern match for everything
  var isEvenPatter = 5 match {
    case n if n %2 ==0 => true
    case _ => false
  }
  //WHY??? it doesnot make sense to use pattern match for the above use case
  def isEven(x: Int) = if( x %2 == 0) true else false
  def isEvenNormal(x: Int) = x % 2 == 0
  /*
    Exercise
    simple function uses PM
     takes an Expr => human readable form

     Sum(Number(2), Number(3)) => 2 + 3
     Sum(Number(2), Number(3), Number(4)) => 2 + 3 + 4
     Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
     Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */

  trait expr
  case class Number(x: Int) extends expr
  case class Sum(e1: expr, e2: expr) extends expr
  case class Prod(e1: expr, e2: expr) extends expr
  var a = new Number(5)
  var b = new Sum(new Number(1), new Prod(new Number(2), new Number(3)) )
  var c = new Prod(new Number(1), new Sum(new Number(2), new Number(3)) )
  var d = new Prod( new Sum(c, b) , new Prod(b, c) )

  def matcher(exp: expr) : String= exp match {
    case Number(x) => x.toString
    case Sum(x, y) => matcher(x) +" + "+ matcher(y)
    case Prod(x, y) => {
      def showWithParentheses(ex: expr) : String= ex match {
        case Number(x) => x.toString
        case Sum(_, _) => "(" + matcher(ex) + ")"
        case Prod(_, _) =>  matcher(ex)
      }
      showWithParentheses(x) + " * " + showWithParentheses(y)
    }
  }
  println(matcher(a))
  println(matcher(b))
  println(matcher(c))
  println(matcher(d))
}
