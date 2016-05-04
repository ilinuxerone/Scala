package main.scala.io

object Serialize {
  def main(args : Array[String])
  {
    val fred = new Person
    import java.io._
    val out = new ObjectOutputStream(new FileOutputStream("src/main/scala/io/output.txt"))
    out.writeObject(fred)
    out.close()
    
    val in = new ObjectInputStream(new FileInputStream("src/main/scala/io/output.txt"))
    val savedFred = in.readObject().asInstanceOf[Person]
    println(savedFred)
  }
}

@SerialVersionUID(42L) class Person extends Serializable {
  var age : Int = 100
  var name : String = "david"
  override  def toString   =  "[age: "+age+"  name: " + name +"]"
}