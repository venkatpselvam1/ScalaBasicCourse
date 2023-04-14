package lectures.part3

import lectures.part3.A09_TuplesMaps.phonebook

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

  //map
  var aMap: Map[Int, String] = Map()

  var phonebook = Map((1, "venkat"), (2, "nandhu"), (3, "test"))
  println(phonebook)
  var phonebook2 = Map((1, "venakt"), 2 -> "nanme" , 3 -> "tester" )
  println(phonebook2)

  //Basic map operation
  println(phonebook.contains(1))
  println(phonebook.contains(12))
  println(phonebook(1))
  var newPhoneBook = phonebook + (11 -> "ajlj")
  var newPhoneBook2 = newPhoneBook + ((13 , "ajlj"))
  println(newPhoneBook2)

  //map, filter, flatmap
  var phoneBook3 = phonebook.map(x => x._1 -> x._2 + x._2)
  println(phoneBook3)
  println(phonebook.view.filterKeys(x => x%2 == 0))

  for (x <- phonebook.view.filterKeys(x => x%2 == 0)) {
    println(x)
  }
  println(phonebook.view.mapValues(x => x+"aaa"))
  for (x <- phonebook.view.mapValues(x => x+"aaa"))
  {
    println(x)
  }

  //Conversions
  println(phonebook)
  println(phonebook.toList)
  println(phonebook.toList.toMap)
  var names = List("vnek", "re", "rer", "resttt", "vvive", "vivek", "aplple", "zoo")
  println(names.groupBy(x => x.charAt(0)))

  var testPhoneBook = Map("venkat" -> 1, "Venkat" -> 2, "VeNKat" -> 3)
  println(testPhoneBook)
  println(testPhoneBook.map(x => x._1.toLowerCase() -> x._2))
}
