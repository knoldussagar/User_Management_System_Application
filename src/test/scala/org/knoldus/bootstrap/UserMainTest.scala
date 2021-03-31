package org.knoldus.bootstrap

import org.knoldus.database.dataCollections
import org.knoldus.models.User
import org.knoldus.request.UserRequest
import org.knoldus.validator.UserValidator
import org.scalatest.flatspec.AnyFlatSpec

class UserMainTest extends AnyFlatSpec{
  val userValidator: UserValidator = new UserValidator
  val userRequest: dataCollections[User] = new UserRequest(userValidator)

  "UserMain" should "create the admin" in {
    val result = userRequest.create(User(1,"Sagar","sagar@gmail.com","admin"))
    assert(result)
  }

  it should "create the customer" in {
    val result = userRequest.create(User(2,"Palak","palak@gmail.com","customer"))
    assert(result)
  }

  it should "update the customer" in {
    val result = userRequest.update(2,"Palak")
    assert(result)
  }

  it should "display the list of users" in {
    val result = userRequest.display()
    assertResult(List(User(1,"Sagar","sagar@gmail.com","admin"), User(2,"Palak","palak@gmail.com","customer")))(result)
  }

  it should "delete the user" in {
    val result = userRequest.delete(2)
    assert(result)
  }

  it should "retrieve the desired user" in {
    val result = userRequest.retrieve(1)
    assertResult(User(1,"Sagar","sagar@gmail.com","admin"))(result)
  }

}

