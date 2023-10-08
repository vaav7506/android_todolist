package com.todolist.data
//1
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

//android에서 package 이름이 data인게 존재하면 데이터베이스가 존재한다라고 이해하면 된다.

@Entity
class Todo( //생성자 초기화 위한 매개변수 입력
    val title : String, val date : Long = Calendar.getInstance().timeInMillis //database의 필드명이 객체다.
) { //key를 잡아줘야해.
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}
//room은 필드명을 추가할 때 여기다가 추가하면 되니깐 사용이 편하다.
//room은 라이브러리로 가져와서 쓰는거라 플러그인 필요. 인터넷 필요.