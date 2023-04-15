package lectures.part4

object A03_PatternEverywhere extends App{
  //IDEA 1
  try{
    //somethidn
  }catch {
    case exception: Exception => println("matches all exception")
  }

  println("catches are matches")

  //IDEA 1
  var list = List(1,2,3,4,5)
  var evenList = for{
    x  <- list if x%2 == 0
  } yield x

  //Generators are also based on the pattern matching
  var tup = List((1,2), (3,4))
  var filteredTyp = for {
    (f, s) <- tup
  } yield f * s

  //cases classes, ::

  var ytuup = (1, 2, 3)
  val (a,b,c) = ytuup

  var h :: t = list
  println(1)
  println(t)

  //Partial matching
  var mappedList = list.map{
    case v if v%2 == 0 => v
    case 1 => "one"
    case _ => "something else"
  }

  var mappedList2 = list.map {x  => x match{
      case v if v % 2 == 0 => v
      case 1 => "one"
      case _ => "something else"
    }
  }
  println(list)
  println(mappedList)
  println(mappedList2)
}
