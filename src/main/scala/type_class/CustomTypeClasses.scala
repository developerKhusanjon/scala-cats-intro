package dev.khusanjon
package type_class

object CustomTypeClasses {

  case class Person(name: String, age: Int)

  //type class definition
  trait Serializer[T] {
    def toJson(value: T): String
    def toXml(value: T): String
  }

  //implicit type class instances
  implicit object StringSerializer extends Serializer[String] {
    override def toJson(value: String): String = "\"" + value + "\""

    override def toXml(value: String): String = "<string>" + value + "</string>"
  }

  implicit object IntSerializer extends Serializer[Int] {
    override def toJson(value: Int): String = value.toString

    override def toXml(value: Int): String = "<int>" + value + "</int>"
  }

  implicit object PersonSerializer extends Serializer[Person] {
    override def toJson(value: Person): String =
      s"""
         |{"name" : ${value.name}, "age" : ${value.age} }
         |""".stripMargin.trim

    override def toXml(value: Person): String =
      s"""
         |<person>
         |<name>${value.name}</name>
         |<age>${value.age}</age>
         |</person>
         |""".stripMargin.trim
  }

  //API
  def serializeListToJson[D](list: List[D])(implicit serializer: Serializer[D]): String =
    list.map(v => serializer.toJson(v)).mkString("[", ",", "]")

  def serializeListToXml[D](list: List[D])(implicit serializer: Serializer[D]): String =
    list.map(v => serializer.toXml(v)).mkString("<list>", ",", "</list>")

  //extending the existing type via extension methods
  object TypeSyntax {
    implicit class Serializable[C](value: C)(implicit serializer: Serializer[C]) {
      def toJson: String = serializer.toJson(value)
      def toXml: String = serializer.toXml(value)
    }
  }

  def main(args: Array[String]): Unit = {
    println(serializeListToJson(List(Person("Khusanjon", 24), Person("Khabib", 34))))
    println(serializeListToXml(List(Person("Khusanjon", 24), Person("Khabib", 34))))
    val mesut = Person("Mesut", 35)
    import TypeSyntax._
    println(mesut.toJson)
    println(mesut.toXml)
  }

}
