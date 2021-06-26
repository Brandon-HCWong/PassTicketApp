package com.example.passticketapp.ticket.model

import com.example.passticketapp.ticket.data.Ticket
import java.util.concurrent.TimeUnit

class TicketRepository {
    private val defaultDayPassTicketList = arrayListOf(
        // Day Pass (New Added)
        Ticket(
            Ticket.Type.DAY_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
            TimeUnit.HOURS.convert(3, TimeUnit.DAYS)
        ),
        // Day Pass (Activated)
        Ticket(
            Ticket.Type.DAY_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
            TimeUnit.HOURS.convert(3, TimeUnit.DAYS),
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)
        ),
        // Day Pass (Expired)
        Ticket(
            Ticket.Type.DAY_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
            TimeUnit.HOURS.convert(1, TimeUnit.DAYS),
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)
        ),
    )

    private val defaultHourPassTicketList = arrayListOf(
        // Hour Pass (New Added)
        Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS),
            6
        ),
        // Hour Pass (Activated)
        Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
            6,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.HOURS)
        ),
        // Hour Pass (Expired)
        Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
            6,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(12, TimeUnit.HOURS)
        ),
    )

    val dayPassTicketList = defaultDayPassTicketList
    val hourPassTicketList = defaultHourPassTicketList

    fun addDayPassTicket(ticket: Ticket) {
        dayPassTicketList.add(ticket)
    }

    fun addHourPassTicket(ticket: Ticket) {
        hourPassTicketList.add(ticket)
    }
}