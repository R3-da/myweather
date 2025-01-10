package com.theodo.myweather.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(weather: Weather)

    @Delete
    suspend fun deleteTodo(weather: Weather)

    @Query("SELECT * FROM weather WHERE id = :id")
    suspend fun getTodoById(id: Int): Weather?

    @Query("SELECT * FROM weather")
    fun getTodos(): Flow<List<Weather>>
}