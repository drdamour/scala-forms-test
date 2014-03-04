package controllers

import play.api.Play.current
import play.api.mvc.{Action, Controller}
import play.api.db.slick._
import play.api.db.slick.Config.driver.simple._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.{Invalid, Valid, Constraint}

case class ModelsFormData(selectedA:Int, selectedB:Int)

object ModelsController extends Controller {

  /*
  def typeAExistsInDB: Constraint[Int] = Constraint("constraints.existenceInDBRequired")({
    id =>
      DB.withSession { implicit s: Session =>

        if (Query(Query(TypeATable).filter(_.id === id).exists).first) {
          Valid
        } else {
          //TODO: like to get the type in this message, should it be passed in as param, or could we just use the generic type somehow
          //TODO: could pass in the id to Messages too
          Invalid("constraints.existenceInDBRequired")
        }
      }
  })

  def typeBExistsInDB: Constraint[Int] = Constraint("constraints.existenceInDBRequired")({
    id =>
      DB.withSession { implicit s: Session =>

        if (Query(Query(TypeBTable).filter(_.id === id).exists).first) {
          Valid
        } else {
          //TODO: like to get the type in this message, should it be passed in as param, or could we just use the generic type somehow
          //TODO: could pass in the id to Messages too
          Invalid("constraints.existenceInDBRequired")
        }
      }
  })
  */

  def existsInDB[T <: CommonBaseType](t:Table[T]): Constraint[Int] = Constraint("constraints.existenceInDBRequired")({
    id =>
      DB.withSession { implicit s: Session =>

        if (Query(Query(t).filter(_.id === id).exists).first) {
          Valid
        } else {
          Invalid("constraints.existenceInDBRequired")
        }
      }
  })

  val form = Form(
    mapping(
      "selectedA" -> number.verifying(existsInDB),
      "selectedB" -> number.verifying(existsInDB)
    )(ModelsFormData.apply)(ModelsFormData.unapply)
  )


  def index = DBAction { implicit rs  =>


    Ok(views.html.commonModels(Query(TypeATable).list, Query(TypeBTable).list, form))
  }

  def postback = DBAction { implicit rs =>

    Ok(views.html.commonModels(Query(TypeATable).list, Query(TypeBTable).list, form.bindFromRequest))
  }

}
