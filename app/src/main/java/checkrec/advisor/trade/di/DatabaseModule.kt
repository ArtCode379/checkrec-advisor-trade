package checkrec.advisor.trade.di

import androidx.room.Room
import checkrec.advisor.trade.data.database.ERSIJDatabase
import org.koin.dsl.module

private const val DB_NAME = "ersij_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = ERSIJDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<ERSIJDatabase>().bookingDao()}

}