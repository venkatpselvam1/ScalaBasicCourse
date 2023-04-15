package lectures.part3

import scala.annotation.tailrec

object A11_TuplesMapsExercise extends App{
  /*
     1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

         !!! careful with mapping keys.

     2.  Overly simplified social network based on maps
         Person = String
         - add a person to the network
         - remove
         - friend (mutual)
         - unfriend

         - number of friends of a person
         - person with most friends
         - how many people have NO friends
         - if there is a social connection between two people (direct or not)
    */

  class FaceBook
  {
    var network: Map[String, Set[String]] = Map()
    def add(name: String): Unit = {
      network = network + ( name -> Set())
    }
    def remove(name: String): Unit = {
      for (fried <- network(name)) {
        network = network + (fried -> (network(fried) - name))
      }
      network = network - name
    }

    def remove1(name: String): Unit = {
      def removeRec(friends: Set[String]) : Unit = {
        if(friends.isEmpty) return
        network = network + ( friends.head -> (network(friends.head) - name ) )
        removeRec(friends.tail)
      }
      network = network - name
    }
    def friend(name: String, name2: String): Unit = {
      network = network + ( name -> ( network(name) + name2) ) + ( name2 -> ( network(name2) + name) )
    }
    def removeFriend(name: String, name2: String): Unit = {
      network = network + ( name -> ( network(name) - name2) ) + ( name2 -> ( network(name2) - name) )
    }

    def numberOfFriends(name: String) : Int = network(name).size
    def personWithMostFriends(): String = {
      def personWithMostFriendsRec(set: Set[String], max: Int, maxPerson: String ) : String = {
        if(set.isEmpty) maxPerson
        else if(network(set.head).size > max){
          personWithMostFriendsRec(set.tail, network(set.head).size, set.head )
        } else {
          personWithMostFriendsRec(set.tail, max, maxPerson)
        }
      }
      personWithMostFriendsRec(network.keySet, 0, "")
    }
    def personWithNoFriends(): Int = {
      def personWithNoFriendsRec(set: Set[String], acc: Int) : Int = {
        if(set.isEmpty) acc
        else if( network(set.head).isEmpty ) personWithNoFriendsRec(set.tail, acc + 1)
        else personWithNoFriendsRec(set.tail, acc)
      }
      personWithNoFriendsRec(network.keySet, 0)
    }
    def connected(name: String, name2: String) : Boolean = {
      var rec = Set[String]()
      @tailrec
      def bfs(q: Set[String]) : Boolean = {
        if(q.isEmpty) false
        else if(q.head == name2) true
        else if(rec.contains(q.head)) bfs(q.tail)
        else {
          rec = rec + q.head
          bfs(q.tail ++ network(q.head))
        }
      }
      bfs(Set[String]() + name)
    }

    override def toString: String = {

      var l = for{
        person <- network.keys
      } yield person + ": " + network(person).mkString("|")
      l.mkString("\n")
    }
  }

  var fb = new FaceBook()
  fb.add("venkat")
  fb.add("nandhu")
  fb.add("sugan")
  fb.add("murali")
  fb.add("neelu")
  fb.friend("venkat", "nandhu")
  fb.friend("nandhu", "sugan")
  fb.friend("sugan", "murali")
  fb.friend("murali", "neelu")
  println(fb)
  println(fb.personWithNoFriends())
  println(fb.personWithMostFriends())
  println(fb.numberOfFriends("venkat"))
  println(fb.connected("venkat", "neelu"))
}
