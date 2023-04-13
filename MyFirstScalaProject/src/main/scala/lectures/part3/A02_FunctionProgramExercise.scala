package lectures.part3

import scala.annotation.tailrec

object A02_FunctionProgramExercise extends App{

  /*
    1.  a function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define a function which takes an int and returns another function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */
  var functionChooser = new ( Int => ( Int => Int ) )
  {
    override def apply(value: Int): (Int => Int) = {
      if(value == 0) new (Int => Int){
        override def apply(v1: Int): Int = 0
      }
      else if (value > 0) new(Int => Int) {
        override def apply(v1: Int): Int = v1 * 5 * value
      }else new(Int => Int) {
        override def apply(v1: Int): Int = v1 * 3 * value
      }
    }
  }
  println(functionChooser(21)(3))
  println(functionChooser(-10)(3))
  var strAdder = new ((String, String) => String)
  {
    override def apply(a: String, b: String) : String = a + b
  }
  println(strAdder("venkat", "nandhu"))

  abstract class VenkatList[+A]
  {
    def head : A
    def tail : VenkatList[A]
    def add[B >: A](value: B) : VenkatList[B] = new VenkatListImpl[B](value, this)
    def isEmpty : Boolean
    def getValue : String
    override def toString: String = "[" + getValue + "]"
    def map[B](function1: ( A => B) ) : VenkatList[B]
    def filter(func: (A => Boolean)) : VenkatList[A]
    def flatMap[B](func: (A => VenkatList[B]) ) : VenkatList[B]
    def ++[B >: A](list1: VenkatList[B]) : VenkatList[B]
  }

  object EmptyList extends VenkatList
  {
    override def head: Nothing = throw new NotImplementedError()

    override def tail: VenkatList[Nothing] = throw new NotImplementedError()


    override def isEmpty: Boolean = true

    override def getValue: String = ""

    override def map[B](function1: Nothing => B): VenkatList[B] = EmptyList

    override def filter(func: Nothing => Boolean): VenkatList[Nothing] = EmptyList

    override def flatMap[B >: Nothing](func: Nothing => VenkatList[B]): VenkatList[B] = EmptyList

    override def ++[B](list1: VenkatList[B]): VenkatList[B] = list1
  }

  class VenkatListImpl[+A](value: A, tailList: VenkatList[A]) extends VenkatList[A]
  {
    override def head: A = value

    override def tail: VenkatList[A] = tailList

    override def isEmpty: Boolean = false

    override def getValue: String = value +", " + tailList.getValue

    override def map[B](function1: A => B): VenkatList[B] = new VenkatListImpl[B]( function1(value), tailList.map(function1) )

    override def filter(func: A => Boolean): VenkatList[A] = {
      if(func(head)) new VenkatListImpl(head, tailList.filter(func))
      else tailList.filter(func)
    }

    override def flatMap[B](func: A => VenkatList[B]): VenkatList[B] = func(head) ++ tailList.flatMap(func)

    override def ++[B >: A](list1: VenkatList[B]): VenkatList[B] = new VenkatListImpl[B](head, tail ++ list1 )
  }
  var intList = new VenkatListImpl(4, new VenkatListImpl(3, new VenkatListImpl(2, new VenkatListImpl(1, new VenkatListImpl(0, EmptyList)))))
  println(intList)

  var doubleList = intList.map(new (Int => String) {

    override def apply(n: Int) : String = {
      @tailrec
      def add(curr: Int, acc: String): String = {
        if(curr == 0) acc
        else add(curr-1, acc+"-"+curr)
      }
      if(n == 0) "0"
      else add(n-1, n.toString)
    }
  })

  println(doubleList)
  var evenList = intList.filter(new (Int => Boolean){
    override def apply(value: Int) : Boolean = value %2 == 0
  })

  println(evenList)
  var flatList = intList.flatMap(new (Int => VenkatList[Int]){
    override def apply(v1: Int): VenkatList[Int] = {
      def add(value: Int): VenkatList[Int] ={
        if(value == 0) EmptyList
        else new VenkatListImpl(value, add(value-1))
      }
      if(v1 == 0) new VenkatListImpl(0, EmptyList)
      else add(v1)
    }
  })

  println(flatList)
}
