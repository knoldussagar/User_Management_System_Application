package org.knoldus.request

import org.knoldus.database.dataCollections
import org.knoldus.models.User
import org.knoldus.validator.UserValidator

import scala.collection.mutable
import scala.util.{Failure, Success, Try}


class UserRequest(userValidator: UserValidator) extends dataCollections[User]{

  val input: mutable.Map[Int, User] = mutable.Map()

  override def create(user: User): Boolean={
    if (userValidator.userIsValid(user)) {
      input += user.id -> user
      true
    }
    else {
      false
    }
  }

  override def update(id: Int, newName: String): Boolean= {
    Try {
      val newUser = User(id, newName, input(id).branch, input(id).course)
      if (userValidator.userIsValid(newUser)) {
        input += id -> newUser
      }
    } match {
      case Success(input) => true
      case Failure(exception) => false
    }
  }

  override def delete(id: Int): Boolean = {
    Try{
      if(input(id).name.nonEmpty) {
        input -= id
      }
    } match {
      case Success(input) => true
      case Failure(exception) => false
    }
  }

  override def retrieve(id: Int): Any = {
    Try{
      input(id)
    } match {
      case Success(value) => input(id)
      case Failure(exception) => "Sorry! No Such Key Present"
    }
  }

  override def display(): List[User] ={
    input.values.toList
  }

}
