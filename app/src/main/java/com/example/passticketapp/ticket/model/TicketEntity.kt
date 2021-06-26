package com.example.passticketapp.ticket.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_entity")
data class TicketEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "type", defaultValue = "")
    val type: String,

    @ColumnInfo(name = "created_time_millis", defaultValue = "-1")
    val createdTimeMillis: Long,

    @ColumnInfo(name = "activated_time_millis", defaultValue = "-1")
    val activatedTimeMillis: Long,

    @ColumnInfo(name = "duration_time_hours", defaultValue = "-1")
    val durationTimeHours: Long,
)