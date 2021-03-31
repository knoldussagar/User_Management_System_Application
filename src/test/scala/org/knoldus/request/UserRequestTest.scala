package org.knoldus.request

import org.knoldus.models.User
import org.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserRequestTest extends AnyFlatSpec {

  val mockedUserValidator: UserValidator = mock[UserValidator]
  val userRequest = new UserRequest(mockedUserValidator)
  val user1: User = User(1,"Damon","damon@gmail.com","admin")
  val user2: User = User(2,"Niklaus","klaus@gmail.com","customer")
  val user3: User = User(3,"","stefan@gmail.com","customer")
  val user4: User = User(4,"Stefan","stefangmailcom","customer")
  val user5: User = User(5, "Stefan","stefan@gmail.com","c")

  "UserRequest" should "create user successfully" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    val result = userRequest.create(user1)
    assert(result)
  }

  it should "not create user as name is invalid" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    val result = userRequest.create(user3)
    assert(!result)
  }

  it should "not create user as email is invalid" in {
    when(mockedUserValidator.userIsValid(user4)) thenReturn false
    val result = userRequest.create(user4)
    assert(!result)
  }

  it should "not create user as course is invalid" in {
    when(mockedUserValidator.userIsValid(user5)) thenReturn false
    val result = userRequest.create(user5)
    assert(!result)
  }

  it should "update the user successfully" in {
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    userRequest.create(user2)
    val result = userRequest.update(2,"Palak")
    assert(result)
  }


  it should "not update user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    val result = userRequest.update(5,"Rahul")
    assert(!result)
  }

  it should "not update as username is invalid" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    userRequest.create(user3)
    val result = userRequest.update(3,"Rahul")
    assert(!result)
  }

  it should "not update as email is invalid" in {
    when(mockedUserValidator.userIsValid(user4)) thenReturn false
    val result = userRequest.update(4,"Rahul")
    assert(!result)
  }

  it should "not update as course is invalid" in {
    when(mockedUserValidator.userIsValid(user5)) thenReturn false
    val result = userRequest.update(5,"Rahul")
    assert(!result)
  }

  it should "delete the user successfully" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userRequest.create(user1)
    val result = userRequest.delete(1)
    assert(result)
  }

  it should "not delete any user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userRequest.create(user1)
    val result = userRequest.delete(8)
    assert(!result)
  }

  it should "not perform deletion as no username found" in {
    when(mockedUserValidator.userIsValid(user3)) thenReturn false
    val result = userRequest.delete(3)
    assert(!result)
  }

  it should "not perform deletion as no user found" in {
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    val result = userRequest.delete(3)
    assert(!result)
  }


  it should "retrieve the user" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userRequest.create(user1)
    val result = userRequest.retrieve(1)
    assertResult(user1)(result)
  }

  it should "not be able to retrieve the user as key is invalid" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    userRequest.create(user1)
    val result = userRequest.retrieve(8)
    assertResult("Sorry! No Such Key Present")(result)
  }

  it should "return no user found" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn false
    userRequest.create(user1)
    val result = userRequest.retrieve(8)
    assertResult("Sorry! No Such Key Present")(result)
  }


  it should "display all the users" in {
    when(mockedUserValidator.userIsValid(user1)) thenReturn true
    when(mockedUserValidator.userIsValid(user2)) thenReturn true
    userRequest.create(user1)
    userRequest.create(user2)
    val result = userRequest.display()
    assertResult(List(user1,user2))(result)
  }

}
