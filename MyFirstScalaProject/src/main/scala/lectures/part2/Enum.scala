package lectures.part2

import java.security.acl.Permission

object EnumExample extends App{
  object Permission extends Enumeration{
    type Permission = Value

    val READ = Value("Read")
    val Write = Value("Write")
    val Delete = Value("Delete")
    val None = Value("None")

    def openDoc(x: Value) = {
      if(x == READ) println("Can Read")
      else println("can NOT read")
    }

  }

  println(Permission.READ)
  Permission.openDoc(Permission.READ)
  Permission.openDoc(Permission.Write)
}
