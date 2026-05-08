package checkrec.advisor.trade.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import checkrec.advisor.trade.data.dao.BookingDao
import checkrec.advisor.trade.data.database.converter.Converters
import checkrec.advisor.trade.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ERSIJDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

