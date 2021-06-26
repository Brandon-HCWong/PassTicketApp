package com.example.passticketapp.ticket.model

import androidx.room.*

@Dao
interface TicketDao {
    @Query("SELECT * FROM ticket_entity")
    fun getAll(): List<TicketEntity>

    fun update(ticketEntity: TicketEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ticketEntity: TicketEntity)

    @Delete
    fun delete(ticketEntity: TicketEntity)
}