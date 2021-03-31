package org.knoldus.validator

import org.knoldus.models.User
import org.scalatest.flatspec.AnyFlatSpec

class UserValidatorTest extends AnyFlatSpec {
  val userValidator = new UserValidator

  "UserValidator" should "be valid" in{
    val result = userValidator.userIsValid(User(20,"Simran","simran@gmail.com","admin"))
    assert(result)
  }

  it should "have proper name" in{
    val result = userValidator.userIsValid(User(20,"","simran@gmail.com","admin"))
    assert(!result)
  }

  it should "have proper email" in{
    val result = userValidator.userIsValid(User(20,"Simran","simrangmail.com","admin"))
    assert(!result)
  }

  it should "have proper user type" in{
    val result = userValidator.userIsValid(User(20,"Simran","simran@gmail.com","a"))
    assert(!result)
  }

}

