package org.knoldus.database

  trait dataCollections[B] {
    def create(b: B) : Boolean
    def retrieve(id:Int): Any
    def update(id: Int, newName: String): Boolean
    def delete(id: Int): Boolean
    def display(): List[B]
  }



