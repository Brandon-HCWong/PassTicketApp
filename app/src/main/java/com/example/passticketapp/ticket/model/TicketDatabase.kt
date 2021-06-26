package com.example.passticketapp.ticket.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passticketapp.TicketApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

object TicketDatabase : CoroutineScope by MainScope() {
    private var database: InternalDatabase? = null
        get() {
            if (field == null) {
                field = InternalDatabase.getDatabase()
            }
            return field
        }

    @Database(entities = [TicketEntity::class], version = 1)
    private abstract class InternalDatabase : RoomDatabase(){
        companion object {
            // Singleton prevents multiple instances of database opening at the same time.
            @Volatile
            private var instance: InternalDatabase? = null

            fun getDatabase(): InternalDatabase {
                instance?.takeIf { it.isOpen }?.let {
                    return it
                }
                synchronized(this) {
                    val db = Room.databaseBuilder(
                        TicketApp.getContext(),
                        InternalDatabase::class.java,
                        "ticket_database").build()
                    instance = db
                    return db
                }
            }
        }
        abstract fun ticketDao(): TicketDao
    }
}
