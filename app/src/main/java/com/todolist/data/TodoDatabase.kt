package com.todolist.data
//3
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1) //버전의 숫자는 의미 없다. 객체 받아서 todo클래스에 넘겨주겠다라는 의미
abstract class TodoDatabase : RoomDatabase(){ //roomdatabase는 추상클래스고, 상속받으면 그 클래스도 추상클래라서 추상클래스 선언해주야함.
    abstract fun todoDao() : TodoDao
}