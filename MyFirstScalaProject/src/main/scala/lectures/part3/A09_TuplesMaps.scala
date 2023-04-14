package lectures.part3

object A09_TuplesMaps extends App {
  //Tuples
  println("Tuples are finite length of List")
  println("Maximum length of tuple is 22 which is equal no of Function parm defined in the scala")
  var tup1 = new Tuple1("Venkat", 12)
  var tup2 = Tuple1(12, "Venkat")
  var tup3 = (12, "venakt")
  println("the above are the variouse ways to define the tuple")
  println(tup1)
  println(tup1._1)
  println("tup1._3 will not compile as it only have 2 param")
  println(tup3.copy(_1 = "12"))
  var tup4 = (12, "venakt", 13, "nandh")
  println(tup3.swap)
  println("Swap is not a member of " + tup4)
}
