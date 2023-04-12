package lectures.part2

import scala.annotation.tailrec

object GenericExercise3 extends App{
  trait MyPredicate[-A]
  {
    def valid(value: A) : Boolean
  }
  trait MyTransform[-A, B]
  {
    def transformMethod(value: A) : B
  }

  abstract class GenList[+A]{
    def head: A
    def tail: GenList[A]
    def isEmpty: Boolean
    def add[B >: A](value: B) : GenList[B]
    def getValue : String
    def filter(pred: MyPredicate[A]) : GenList[A]
    def transform[B](transform: MyTransform[A, B]) : GenList[B]
    def flatMap[B](transform: MyTransform[A, GenList[B]]) : GenList[B]
    def flatMap2[B](transform: MyTransform[A, GenList[B]]) : GenList[B]
    override def toString: String = "[" + getValue +"]"
    def ++[B >: A](list: GenList[B]) : GenList[B]
  }
  class GenListImpl[+A](value: A, tailList: GenList[A]) extends GenList[A]
  {
    override def head: A = value

    override def tail: GenList[A] = tailList

    override def isEmpty: Boolean = true

    override def add[B >: A](value: B): GenList[B] = new GenListImpl[B](value, this)

    override def filter(pred: MyPredicate[A]): GenList[A] = {
      val tailList = this.tail.filter(pred)
      if (pred.valid(head)) new GenListImpl(head, tailList)
      else tailList
    }
    override def getValue: String = value.toString() +", " + tailList.getValue

    override def transform[B](transform: MyTransform[A, B]): GenList[B] =
      {
        val nHead = transform.transformMethod(head)
        val nTail = tail.transform(transform)
        new GenListImpl[B](nHead, nTail)
      }

    override def flatMap[B](transform: MyTransform[A, GenList[B]]): GenList[B] = {
      var listB = transform.transformMethod(head)
      var tailListB = tail.flatMap(transform)
      def flat(a: GenList[B], b: GenList[B]) : GenList[B] = {
        if(a == Empty) b
        else new GenListImpl[B](a.head, flat(a.tail, b) )
      }
      flat(listB, tailListB)
    }

    override def flatMap2[B](transform: MyTransform[A, GenList[B]]): GenList[B] = transform.transformMethod(head) ++ tail.flatMap2(transform)

    override def ++[B >: A](list: GenList[B]): GenList[B] = new GenListImpl[B](head, tail ++ list)
  }
  object Empty extends GenList{
    override def head: Nothing = throw new NotImplementedError()

    override def tail: GenList[Nothing] = throw new NotImplementedError()

    override def isEmpty: Boolean = false

    override def add[B >: Nothing](value: B): GenList[B] = new GenListImpl[B](value, Empty)

    override def getValue: String = ""

    override def filter(pred: MyPredicate[Nothing]): GenList[Nothing] = Empty

    override def transform[C](transform: MyTransform[Nothing, C]): GenList[C] = Empty

    override def flatMap[B](transform: MyTransform[Nothing, GenList[B]]): GenList[B] = Empty

    override def flatMap2[B](transform: MyTransform[Nothing, GenList[B]]): GenList[B] = Empty

    override def ++[B >: Nothing](list: GenList[B]): GenList[B] = list
  }
  var intList = new GenListImpl(1, new GenListImpl(2, new GenListImpl(3, new GenListImpl(4, Empty))) )
  var strList = new GenListImpl("venkat", new GenListImpl("rest", new GenListImpl("test", new GenListImpl("best", Empty))) )
  println(intList)
  println(strList)


  var evenIntList = intList.filter(new MyPredicate[Int] {
    override def valid(value: Int): Boolean = value %2 == 0
  })
  var primeIntList = intList.filter(new MyPredicate[Int] {
    override def valid(value: Int): Boolean = prime(value)
    def prime(n: Int):Boolean = {
      def primeRec(div: Int) :Boolean =
      {
        if (n == div) true
        else if(n % div == 0) false
        else primeRec(div+1)
      }
      if (n == 1) false
      else primeRec(2)
    }
  })
  println(evenIntList)
  println(primeIntList)
  var lenList = strList.transform(new MyTransform[String, Int] {
    override def transformMethod(value: String): Int = value.length
  })
  println(lenList)
  var doubleList = lenList.transform(new MyTransform[Int, Int] {
    override def transformMethod(value: Int): Int = value * 15
  })
  println(doubleList)
  var mulList = intList.flatMap2(new MyTransform[Int, GenList[Int]] {
    override def transformMethod(value: Int): GenList[Int] = {
      if(value <= 0) new GenListImpl[Int](0, Empty)
      else{
        @tailrec
        def add(value: Int, tail: GenList[Int]) : GenList[Int] ={
          if(value == 0) tail
          else add(value-1, new GenListImpl[Int](value, tail) )
        }
        add(value, Empty)
      }
    }
  })
  println(mulList)
  var eList = mulList.flatMap2(new MyTransform[Int, GenList[Int]] {
    override def transformMethod(value: Int): GenList[Int] = {
      new GenListImpl[Int](value, new GenListImpl[Int](value+1, Empty))
    }
  })
  println(eList)
}
