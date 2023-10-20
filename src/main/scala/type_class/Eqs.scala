package dev.khusanjon
package type_class

object Eqs {

  // Eq
  // val aComparison = 2 == "a string" -- wrong, will trigger a compiler warning, will always return false

  //type class import
  import cats.Eq

  //type class instances
  import cats.instances.int._

  //type class API
  val intEquality = Eq[Int]
  val intComparison = intEquality.eqv(3, 4) // false
  //intEquality.eqv("three", 4) -- doesn't compile

  //extension methods
  import cats.syntax.eq._
  val intComparisonWithSyntax = 3 === 4 // false
  val notEqualWithSyntax =  5 =!= 6 // true
  // val invalidComparison = 2 === "a String" -- doesn't compile
  // extension methods are only visible in the presence of the right TC instance

  //compositing types
  import cats.instances.list._ // we bring Eq[List[Int]] in scope
  // val comparisonLists = List("one") === List(2)  -- doesn't compile
  val comparisonSameTypedLists = List(7) === List(8) // false

  //eq instance for a custom type
  case class Volume(width: Int, height: Int)
  implicit val volumeEq: Eq[Volume] = Eq.instance((vol1, vol2)
                              => vol1.width * vol1.height == vol2.width * vol2.height)

  val comparingVolumes = Volume(10, 40) === Volume(20, 20) // true
}
