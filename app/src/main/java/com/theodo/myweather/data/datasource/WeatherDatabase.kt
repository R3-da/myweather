package com.theodo.myweather.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.theodo.myweather.data.model.Weather
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Provider

@Database(
    entities = [Weather::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    class Callback @Inject constructor(
        private val database: Provider<WeatherDatabase>
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                val weatherDao = database.get().weatherDao()

                // Add seed data
                val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val seedData = listOf(
                    Weather(
                        time = currentTime.format(Date()),
                        temperature = 75.0,
                        windSpeed = 10.0,
                        precipitationIntensity = 0.0
                    ),
                    Weather(
                        time = currentTime.format(Date(System.currentTimeMillis() + 3600000)),
                        temperature = 72.0,
                        windSpeed = 12.0,
                        precipitationIntensity = 0.2
                    ),
                    Weather(
                        time = currentTime.format(Date(System.currentTimeMillis() + 7200000)),
                        temperature = 70.0,
                        windSpeed = 15.0,
                        precipitationIntensity = 0.5
                    )
                )

                seedData.forEach { weather ->
                    weatherDao.insertWeather(weather)
                }
            }
        }
    }
}