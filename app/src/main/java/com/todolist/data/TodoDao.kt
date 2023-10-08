package com.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//entity클래스를 APP에서 작업할 수 있도록 DAO interface를 생성한다.
@Dao // interface 생성
interface TodoDao {
    @Query("select * from todo order by date DESC")
    fun getAll() : Flow<List<Todo>> // db에서 데이터를 가져오는 메소드. 코틀린껄로 임포트 필요.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Todo) //suspend는 처리 시 시간이 걸리는 비동기 식으로 처리하는 방법을 의미 - 안드로이드앱의 특징

    @Update
    suspend fun update(entity: Todo)

    @Delete
    suspend fun delete(entity: Todo)
}