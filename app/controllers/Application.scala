package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

case class FormData(i:Int, j:Int)

object Application extends Controller {

  val simpleForm = Form(
    mapping(
      "i" -> number,
      "j" -> number
    )
      (FormData.apply)(FormData.unapply)
  )

  def simple = Action { implicit request =>
    val f = simpleForm.bindFromRequest
    Ok(views.html.view(f, routes.Application.simple()))
  }



  val defaultsForm = Form(
    mapping(
      "i" -> default(number, 4),
      "j" -> default(number, 7)
    )
    (FormData.apply)(FormData.unapply)
  )

  def defaults = Action { implicit request =>
    val f = defaultsForm.bindFromRequest
    Ok(views.html.view(f, routes.Application.defaults()))
  }


  def constructorDefaultsInvalid = Action { implicit request =>
    val f = simpleForm.bindFromRequest
    Ok(views.html.view(f, routes.Application.constructorDefaultsInvalid(), Seq('default -> "invalid"), Seq('_default -> "invalid")))
  }

  def constructorDefaultsValid = Action { implicit request =>
    val f = simpleForm.bindFromRequest
    Ok(views.html.view(f, routes.Application.constructorDefaultsValid(), Seq('default -> 1), Seq('_default -> "one")))
  }

  def invalidData = Action { implicit request =>
    val f = simpleForm.bind(Map(
      "i" -> "invalid",
      "j" -> "invalid"
    ))

    Ok(views.html.view(f, routes.Application.invalidData()))
  }


  def validNotPresent = Action { implicit request =>
    val f = simpleForm.bind(Map(
      "i" -> "1",
      "j" -> "4"
    ))

    Ok(views.html.view(f, routes.Application.validNotPresent()))
  }

}