package com.todolist
//4
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.todolist.data.Todo
import com.todolist.data.TodoDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class MainViewModel(application: Application) : AndroidViewModel(application){
    private val db = Room.databaseBuilder(
        application, TodoDatabase::class.java, "todo"
    ).build()

    //DB 결과를 관리할수 있도록
    private val items = MutableStateFlow<List<Todo>>(emptyList())
    val item : StateFlow<List<Todo>> = items

    //데이터를 읽어온다. init 초기화 필요
    init {
        viewModelScope.launch {
            //현재 값(데이터)을 가져올 수 있다.
            db.todoDao().getAll().collect{todos -> items.value = todos}
            //stateflow 객체는 value 속성으로서 현재 상태값을 읽고 쓸수 있다.
        }
    }
    fun addTodo(text:String) {
        viewModelScope.launch {
            db.todoDao().insert(Todo(text))
        }
    }
    //업데이트할 데이터의 id객체를 먼저 찾고 업데이트하기
    fun updateTodo(id : Long, text: String) {
        items.value
            .find { todo -> todo.id == id }
            ?.let { todo -> todo.apply {
            title = text
            date = Calendar.getInstance().timeInMillis
        }
        viewModelScope.launch { db.todoDao().update(todo) }
        }
    }
    fun deleteTodo(id : Long) {
        items.value
            .find { todo -> todo.id == id }
            .let { todo -> viewModelScope.launch { db.todoDao().delete(todo) } }
    }

}