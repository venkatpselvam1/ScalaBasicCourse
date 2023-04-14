package lectures.part3

object A10_TuplesMapsExerciseWrongWay extends App {
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
  var testPhoneBook = Map("venkat" -> 1, "Venkat" -> 2, "VeNKat" -> 3)
  println(testPhoneBook)
  println(testPhoneBook.map(x => x._1.toLowerCase() -> x._2))

  //!!! careful with mapping keys.

  var friends: Map[String, List[String]] = Map()
  var netWorks: Map[String, List[String]] = Map()

  def add(name: String, name2: String) = {
    if(!netWorks.contains(name))
    {
      netWorks = netWorks + ( (name -> List.fill(0)("test") ) )
    }
    if(!netWorks(name).contains(name2))
      netWorks = netWorks + ( (name ->  (netWorks(name) :+ name2) ) )
  }

  def remove(name: String, name2: String) = {
    if (netWorks.contains(name) && netWorks(name).contains(name2)) {
      var d = netWorks(name).dropWhile(x => x == name2)
      netWorks = netWorks + ( (name ->  d ) )
    }
  }
  def addFriend(name: String, name2: String) = {
    if (!friends.contains(name)) {
      friends = friends + ((name -> List.fill(0)("test")))
    }
    if (!friends.contains(name2)) {
      friends = friends + ((name2 -> List.fill(0)("test")))
    }
    if (!friends(name).contains(name2))
    {
      friends = friends + ((name -> (friends(name) :+ name2)))
      friends = friends + ((name2 -> (friends(name2) :+ name)))
    }
  }
  def removeFriend(name: String, name2: String) = {
    if (friends.contains(name) && friends(name).contains(name2)) {
      friends = friends + ((name -> friends(name).dropWhile(x => x == name2)))
      friends = friends + ((name2 -> friends(name2).dropWhile(x => x == name)))
    }
  }
  def noOfFriends(name: String) : Int = {
    if(!friends.contains(name)) 0
    else friends(name).length
  }
  def personWithMostFriend() : String = {
    if(friends.size == 0) return "everyone is single"
    else{
      var ans = ""
      var max = 0
      for(k <- friends.keys)
      {
        var n = noOfFriends(k)
        if(max < n)
        {
          ans = k
          max = n
        }
      }
      ans
    }
  }
  def personWithNoFriend() : Int = {
    var ans = 0
    for (k <- friends.keys) {
      if (noOfFriends(k) == 0) {
        ans+=1
      }
    }
    ans
  }
  def isConnected(name:String, name2: String) : Boolean = {
    var rec = List.fill(0)("test")
    def isConnectedRec(nn: String) : Boolean =
    {
      if(friends(nn).contains(name2)) return true
      if(rec.contains(nn) || !friends.contains(nn) || friends(nn).length == 0) return false
      rec = rec.appended(nn)
      for(friend <- friends(nn))
        {
          if(isConnectedRec(friend))
          {
            return true
          }
        }
      false
    }
    isConnectedRec(name)
  }
  add("venkat", "test")
  add("venkat", "test1")
  add("venkat", "test2")
  println(netWorks)
  remove("venkat", "test")
  println(netWorks)
  remove("venkat", "test")
  println(netWorks)
  remove("venkat1", "test")
  println(netWorks)

  addFriend("venkat", "nandhu")
  addFriend("nandhu", "venkat")
  addFriend("nandhu", "reest")
  addFriend("nandhu", "ram")
  println(friends)
  println(noOfFriends("venkat"))
  println(noOfFriends("nandhu"))
  removeFriend("venkat", "nandhu")
  println(friends)
  println(personWithMostFriend())
  println(personWithNoFriend())
  addFriend("venkat", "nandhu")
  addFriend("nandhu", "asok")
  addFriend("asok", "aadhi")
  addFriend("aadhi", "ikki")
  println(isConnected("venkat", "ikki"))

}
