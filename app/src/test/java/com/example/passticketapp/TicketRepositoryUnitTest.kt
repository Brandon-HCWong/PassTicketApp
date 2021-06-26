package com.example.passticketapp

import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.model.TicketRepository
import org.junit.Assert
import org.junit.Test
import java.util.concurrent.TimeUnit

class TicketRepositoryUnitTest {

    private val ticketRepository = TicketRepository()

    @Test
    fun testAddDayPassTicket() {
        ticketRepository.addDayPassTicket(
            Ticket(
                Ticket.Type.DAY_PASS,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
                TimeUnit.HOURS.convert(3, TimeUnit.DAYS),
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)
            )
        )
        Assert.assertTrue(ticketRepository.dayPassTicketList.size == 4)
    }

    @Test
    fun testAddHourPassTicket() {
        ticketRepository.addHourPassTicket(
            Ticket(
                Ticket.Type.HOUR_PASS,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
                6,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.HOURS)
            )
        )
        Assert.assertTrue(ticketRepository.hourPassTicketList.size == 4)
    }
}