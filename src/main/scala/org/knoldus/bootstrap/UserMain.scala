package org.knoldus.bootstrap

import org.knoldus.database.dataCollections
import org.knoldus.models.User
import org.knoldus.request.UserRequest
import org.knoldus.validator.UserValidator

object UserMain {
  def main(args: Array[String]): Unit = {
    val userValidator: UserValidator = new UserValidator
    val userRequest: dataCollections[User] = new UserRequest(userValidator)
    userRequest.create(User(1, "Sagar", "sagar@gmail.com", "admin"))
    userRequest.create(User(2, "Palak", "palak@gmail.com", "customer"))
    userRequest.update(2, "Palak")
    userRequest.display()
    userRequest.delete(2)
    userRequest.retrieve(1)
  }
}

