package lectures.part2

object GenericExercise2 extends App{
  abstract class MyGenAbstractList[+A]
  {
    def head: A
    def tail: MyGenAbstractList[A]
    def isEmpty : Boolean
    def add[B >: A](n: B): MyGenAbstractList[B]
    def getStrVal : String
    override def toString: String = "[" + getStrVal + "]"

  }

  object MyEmptyGenList extends MyGenAbstractList
  {
    override def head: Nothing = throw new NotImplementedError()

    override def tail: MyGenAbstractList[Nothing] = throw new NotImplementedError()

    override def isEmpty: Boolean = false

    override def add[A >: Nothing](n: A): MyGenAbstractList[A] = new MyGenListImpl[A](n, MyEmptyGenList)

    override def getStrVal: String = ""
  }
  class MyGenListImpl[+A](value: A, tailList : MyGenAbstractList[A]) extends MyGenAbstractList[A]
  {
    def head : A = value

    def tail: MyGenAbstractList[A] = tailList

    override def isEmpty: Boolean = true

    override def add[B >: A](n: B): MyGenAbstractList[B] = new MyGenListImpl[B](n, this)

    override def getStrVal: String = value.toString() + ", " + tail.getStrVal
  }

  var intList = new MyGenListImpl(1, new MyGenListImpl(2, new MyGenListImpl(3, new MyGenListImpl(4, MyEmptyGenList))) )
  var strList = new MyGenListImpl("venkat", new MyGenListImpl("nandhu", new MyGenListImpl("test", new MyGenListImpl("rest", MyEmptyGenList))) )

  println(intList)
  println(strList)
}
