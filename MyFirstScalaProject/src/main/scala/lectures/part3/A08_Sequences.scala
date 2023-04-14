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
}
