package lectures.part2

object AbstractExercise extends App{
  abstract class MyList
  {
    def head : Int
    def tail : MyList
    def isEmpty : Boolean
    def add(value: Int): MyList
    def printElement : String
    def toStringRec: String =
    {
       def toStringRec(myList: MyList, acc: String): String =
      {
        if(myList == Empty) acc
        else toStringRec(myList.tail, acc+myList.head+", ")
      }

      toStringRec(this, "")
    }

    override def toString: String = {
      "[ " + printElement + "]"
    }
  }
  object Empty extends MyList{
    override def head: Int = 0

    override def tail: MyList = null

    override def isEmpty: Boolean = true

    override def add(value: Int): MyList = new Cons(value, this)

    override def printElement: String = ""
  }
  class Cons(value: Int, tailList: MyList) extends MyList
  {
    override def head: Int = value

    override def tail: MyList = tailList

    override def isEmpty: Boolean = false

    override def add(value: Int): MyList = new Cons(value, this)

    override def printElement: String = {
      head+", "+tail.printElement
    }
  }

  var list = new Cons(0, new Cons(1, new Cons(2, Empty)))
  println(list)
}
