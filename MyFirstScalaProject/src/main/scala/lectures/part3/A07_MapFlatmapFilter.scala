package lectures.part3

import lectures.part2.GenericExercise3.Empty
import lectures.part3.A05_HOFsAndCurriesExercise.{EmptyList, VenkatListImpl}

object A07_MapFlatmapFilter extends App{
  var list = List(1, 2, 3, 4, 5, 6)
  println(list)
  println("here the List is apply applied on the List object companion")

  //Map
  println (list.map(_+4))
  //filter
  println(list.filter(_%2==0))
  //flatmap
  println(list.flatMap(x => List(x, x+1, x * 10)))
  //foreach
  println(list.foreach(println))
  //Exercise: Print all the combination

  var l1 = List(1, 2, 3)
  var l2 = List('A', 'B', 'C', 'D')
  var l3 = List("Red", "Black")

  println("The below example of two for loops is what I was using so far")
  //e.g. 1A, 2A, 3A, ...., 8G, 8H
  for (i <- 0 to l1.length-1 )
  {
    for (j <- 0 to l2.length - 1)
      print("[" + l1(i) + "," + l2(j) + "]\t\t")
    println()
  }

  println("The below shows how to use map and flat map")

  var combination1 = l1.flatMap(x => l2.map( ch => x+""+ch))
  println(combination1)
  var combination2 = l1.flatMap(x => l2.flatMap(ch => l3.map(color => x +"" + ch + color) ) )
  println(combination2)

  println("scala have one more synthetical sugar to the flatmap and map combination: For-Comprehension")

  var combination3 = for {
    x <- l1
    ch <- l2
    color <- l3
  }yield x+""+ch+color
  println(combination3)

  println("we can use the filter also with For-comprehension")
  for {
    x <- l1
    y <- l1
    color <- l3 if (y%2 ==0 && color == "Red") || (y %2 ==1 && color == "Black")
  } println(x+""+y+color)

  /*
    1.  MyList supports for comprehensions?
    As we have all the below 3 req, our VenkatListImpl will work with For-Comprehension
        map(f: A => B) => MyList[B]
        filter(p: A => Boolean) => MyList[A]
        flatMap(f: A => MyList[B]) => MyList[B]
    2.  A small collection of at most ONE element - Maybe[+T]
        - map, flatMap, filter
   */

  var mylist = new VenkatListImpl(4, new VenkatListImpl(3, new VenkatListImpl(2, new VenkatListImpl(1, EmptyList))) )
  println(mylist)
  for{
    x <- mylist
    y <- mylist
    z <- mylist if (x %2 == 0 && y %2 != 0) || (x %2 == 1 && y %2 == 0)
  } print(x+","+y+","+z+"\t\t")

  abstract class CollectionWithOne[+A]
  {
    def head : A
    def map[C](func: A => C) : CollectionWithOne[C]
    def filter(func: A => Boolean) : CollectionWithOne[A]
    def flatMap[B >: A](func: B => CollectionWithOne[B]) : CollectionWithOne[B]

    override def toString: String = {
      if(this != EmptyCollection) head.toString()
      else ""
    }
  }
  object EmptyCollection extends CollectionWithOne
  {
    override def head: Nothing = throw new NotImplementedError()


    override def map[C](func: Nothing => C): CollectionWithOne[C] = EmptyCollection

    override def filter(func: Nothing => Boolean): CollectionWithOne[Nothing] = EmptyCollection

    override def flatMap[B >: Nothing](func: B => CollectionWithOne[B]): CollectionWithOne[B] = EmptyCollection
  }
  class CollectionWithOneImpl[+A](value: A) extends CollectionWithOne[A]
  {
    override def head: A = value


    override def map[C](func: A => C): CollectionWithOne[C] = new CollectionWithOneImpl(func(head))

    override def filter(func: A => Boolean): CollectionWithOne[A] =
      {
        if(func(head)) this
        else EmptyCollection
      }

    override def flatMap[B >: A](func: B => CollectionWithOne[B]): CollectionWithOne[B] = func(head)
  }

  var just3 = new CollectionWithOneImpl(3)
  println(just3)
  println(just3.map(x => x * 19))
  println(just3.filter(x => x % 3 == 0))
  println(just3.flatMap(x => new CollectionWithOneImpl(x*12) ))
}
