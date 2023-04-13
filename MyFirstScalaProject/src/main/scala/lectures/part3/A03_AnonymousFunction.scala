package lectures.part3

import scala.annotation.tailrec

object A03_AnonymousFunction extends App{
  var adder = new ( (Int , Int) => Int ){
    override def apply(a: Int, b: Int) = a + b
  }
  println("In the above adder method, which takes two number and adding them, still we are using the new Keyword and creating anonymous class")
  println("We can avoid this using anonymous function a.k.a lambda")
  var adder2  = (a: Int, b: Int) => a + b
  println("Notice the tpe of adder and adder2 both are same. Function2[Int, Int, Int] in simple with synthetic sugar ((Int, Int) => Int) ")
  var valueGiver = () => 5
  println("Notice the above lambda is not taking any param but returning int")
  var adder3: ( (Int, Int) => Int) = (a,b) => a+b
  println("In the above adder3, we didn't specify the types in the right handed side. But they types are clearly mentioned in the left")
  var adder4: (Int, Int) => Int = _ + _
  println("We can use the underscore _ like mentioned above")
  var addSix: (Int) => Int = _ + 6

  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
   */
  var functionChooser2 : Int => Int => Int =  { (value: Int) =>
    if (value == 0) _ * 0
    else if (value > 0)  _ * 5 * value
    else _ * 3 * value
  }
  var functionChooser3 : Int => Int => Int = x => y => x + y
  println(functionChooser2(21)(3))
  println(functionChooser2(-10)(3))
  println(functionChooser3(3)(4))

  abstract class VenkatList[+A] {
    def head: A

    def tail: VenkatList[A]

    def add[B >: A](value: B): VenkatList[B] = new VenkatListImpl[B](value, this)

    def isEmpty: Boolean

    def getValue: String

    override def toString: String = "[" + getValue + "]"

    def map[B](function1: (A => B)): VenkatList[B]

    def filter(func: (A => Boolean)): VenkatList[A]

    def flatMap[B](func: (A => VenkatList[B])): VenkatList[B]

    def ++[B >: A](list1: VenkatList[B]): VenkatList[B]
  }

  object EmptyList extends VenkatList {
    override def head: Nothing = throw new NotImplementedError()

    override def tail: VenkatList[Nothing] = throw new NotImplementedError()


    override def isEmpty: Boolean = true

    override def getValue: String = ""

    override def map[B](function1: Nothing => B): VenkatList[B] = EmptyList

    override def filter(func: Nothing => Boolean): VenkatList[Nothing] = EmptyList

    override def flatMap[B >: Nothing](func: Nothing => VenkatList[B]): VenkatList[B] = EmptyList

    override def ++[B](list1: VenkatList[B]): VenkatList[B] = list1
  }

  class VenkatListImpl[+A](value: A, tailList: VenkatList[A]) extends VenkatList[A] {
    override def head: A = value

    override def tail: VenkatList[A] = tailList

    override def isEmpty: Boolean = false

    override def getValue: String = value + ", " + tailList.getValue

    override def map[B](function1: A => B): VenkatList[B] = new VenkatListImpl[B](function1(value), tailList.map(function1))

    override def filter(func: A => Boolean): VenkatList[A] = {
      if (func(head)) new VenkatListImpl(head, tailList.filter(func))
      else tailList.filter(func)
    }

    override def flatMap[B](func: A => VenkatList[B]): VenkatList[B] = func(head) ++ tailList.flatMap(func)

    override def ++[B >: A](list1: VenkatList[B]): VenkatList[B] = new VenkatListImpl[B](head, tail ++ list1)
  }

  var intList = new VenkatListImpl(4, new VenkatListImpl(3, new VenkatListImpl(2, new VenkatListImpl(1, new VenkatListImpl(0, EmptyList)))))
  println(intList)

  var doubleList = intList.map({ (n: Int) =>
      @tailrec
      def add(curr: Int, acc: String): String = {
        if (curr == 0) acc
        else add(curr - 1, acc + "-" + curr)
      }

      if (n == 0) "0"
      else add(n - 1, n.toString)
  })

  println(doubleList)
  var evenList = intList.filter(x => x % 2 == 0)

  println(evenList)
  var flatList = intList.flatMap({ (v1: Int) =>

      def add(value: Int): VenkatList[Int] = {
        if (value == 0) EmptyList
        else new VenkatListImpl(value, add(value - 1))
      }
      if (v1 == 0) new VenkatListImpl(0, EmptyList)
      else add(v1)
  })

  println(flatList)
}
