package models

import play.api.db.slick.Config.driver.simple._


trait CommonBaseType {
  val id: Option[Int]
}

trait CommonBaseTypeTable[T <: CommonBaseType] {
  this: Table[T] =>
  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
}


case class TypeA(
  id: Option[Int] = None,
  name: String
) extends CommonBaseType

object TypeATable extends Table[TypeA]("TYPEA") with CommonBaseTypeTable[TypeA] {
  def name = column[String]("NAME")
  def * = id.? ~ name <> (TypeA, TypeA.unapply _)
}



case class TypeB(
  id: Option[Int] = None,
  prop2: String
) extends CommonBaseType

object TypeBTable extends Table[TypeB]("TYPEB") with CommonBaseTypeTable[TypeB] {
  def prop2 = column[String]("PROP2")
  def * = id.? ~ prop2 <> (TypeB, TypeB.unapply _)
}


