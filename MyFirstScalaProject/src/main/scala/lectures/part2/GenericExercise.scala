package lectures.part2

object GenericExercise extends App{
  class MyGenericLinkedList[A](var Value: A, var tail: MyGenericLinkedList[A])
  {
    def add(value: A) = new MyGenericLinkedList[A](value, this)
    def isEmpty = tail == null

    override def toString: String = {
      if(tail == null)  Value.toString
      else Value + ", "+tail.toString()
    }

  }

  var intList = new MyGenericLinkedList[Int](5, new MyGenericLinkedList(4, new MyGenericLinkedList(3, null) ))
  println(intList)
  var strList = new MyGenericLinkedList[String]("venkat", new MyGenericLinkedList("nandhu", new MyGenericLinkedList("viyan", new MyGenericLinkedList("nivi", null) ) ))
  println(strList)
}
