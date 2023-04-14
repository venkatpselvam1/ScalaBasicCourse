package lectures.part3


object A08_Sequences extends App{
  //Sequence
  var seq = Seq(1, 2, 3, 4)
  println(seq)
  println(seq.reverse)
  println(seq(2))
  println(seq ++ Seq(9,7,6))
  println( (seq.reverse ++ Seq(9,7,6,6,5)).sorted )

  //Ranges
  var aRange = 1 to 10
  aRange.foreach(x => print(x+" sep "))
  println()
  var bRange = 1 until 10
  bRange.foreach(x => print(x + " sep "))
  println()

  //List
  var list = List(1,2,3,4,5)
  println( 0 :: list)
  println(0 +: list :+ 9)
  println(0 +: list :+ 11 )
  println(list.mkString(":,:") )
  var apples5 = List.fill(5)("appple")
  println(apples5)

  //Arrays - Mutable continuose block of address allocated
  var arr = Array(1,2,3,4,5)
  var emptyArr = Array.ofDim[Int](3)
  println("Array of dim will create array with default value of the specified type")
  emptyArr.foreach(println)
  println(arr.foreach(x => print(x+", ")))
  println()
  println(arr(2))
  arr(2) = 100
  println(arr.mkString("<|>"))

  //Array and seq
  var seq2: Seq[Int] = Array(1,2,3,4, 5)
  println(seq2)//
  println("seq2: Seq[Int] = Array(1,2,3,4, 5) is of type ArraySeq. ArraySeq is advanced topic Implicit conversion")


}
