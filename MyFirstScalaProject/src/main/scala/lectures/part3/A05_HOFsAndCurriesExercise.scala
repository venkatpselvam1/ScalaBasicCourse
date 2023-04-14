package lectures.part3

import lectures.part2.AbstractExercise.MyList

object A05_HOFsAndCurriesExercise extends App{
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

    def forEach(func: A => Unit): Unit = {
      if(this != EmptyList){
        func(head)
        tail.forEach(func)
      }
    }
    def zip[B >: A, C](newList: VenkatList[B] , func: (B, B) => C ): VenkatList[C] = {
      if(this == EmptyList || newList == EmptyList) EmptyList
      else new VenkatListImpl[C]( func(this.head, newList.head ) , tail.zip(newList.tail, func) )
    }

    def zipCurry[B >: A, C](newList: VenkatList[B])(func: (B, B) => C ) : VenkatList[C] = {
      if(this == EmptyList || newList == EmptyList) EmptyList
      else new VenkatListImpl[C]( func(head, newList.head), tail.zipCurry(newList.tail)(func) )
    }

    def fold[C](start: C, func: (C , A )=> C): C = {
      if(this == EmptyList) start
      else tail.fold(func(start, head), func)
    }
    def foldCurry[C](start: C)(func: (C, A) => C): C ={
      if(this == EmptyList) start
      else tail.foldCurry(func(start, head))(func)
    }

    def sort(compare: (A, A) => Int ) : VenkatList[A] = {
      if(this.isEmpty) this
      else {
        var sortedTail = tail.sort(compare)
        def insert(ele: A, sortedList: VenkatList[A]) : VenkatList[A] = {
          if(sortedList.isEmpty) new VenkatListImpl(ele, EmptyList)
          else{
            var value = compare(ele, sortedList.head)
            if(value <= 0) new VenkatListImpl(ele, sortedList)
            else new VenkatListImpl(sortedList.head, insert(ele, sortedList.tail) )
          }
        }
        insert(head, sortedTail)
      }
    }
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
  intList.forEach(x => {
    var temp = x;
    while(temp >= 0){
      print(temp+", ")
      temp-=1
    }
    println()
  })

  var func : (Int, Int) => String = (x,y) => x+"-"+y
  var strList = intList.zipCurry(intList)(func);
  println(strList)

  var func2 : (String, Int) => String = (x,y) =>  y +"," + x
  println( intList.fold("", func2) )
  println( intList.foldCurry("")(func2) )

  var sortedIntList = intList.sort((x, y) => x-y )
  println(sortedIntList)
}
