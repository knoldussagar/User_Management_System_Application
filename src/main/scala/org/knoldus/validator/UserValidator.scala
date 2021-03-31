package org.knoldus.validator

import org.knoldus.models.User

class UserValidator {
  def userIsValid(user: User): Boolean = {

    if (user.name.matches("^[a-zA-Z\\\\s]+$")  &&
      user.branch.matches("^\\S+@\\S+\\.(com|net|org|in)$") &&
      user.course.matches("(customer|admin)") &&
      user.id.isValidInt) {
      true
    } else{
      false
    }
  }
}

