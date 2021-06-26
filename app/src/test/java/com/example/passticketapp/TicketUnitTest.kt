package com.example.passticketapp

import com.example.passticketapp.ticket.data.Ticket
import org.junit.Test

import org.junit.Assert
import java.util.concurrent.TimeUnit

class TicketUnitTest {

    @Test
    fun testIsUnactivated() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
            6
        )
        Assert.assertEquals(Ticket.Status.UNACTIVATED, ticket.getStatus())
    }

    @Test
    fun testIsActivated() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
            6,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.HOURS)
        )
        Assert.assertTrue(ticket.isActivated())
    }

    @Test
    fun testIsExpired() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
            6,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(12, TimeUnit.HOURS)
        )
        Assert.assertTrue(ticket.isExpired())
    }

    //1624695581211 2021年06月26日16點19分41秒
    @Test
    fun testGetInsertionDate() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            1624695581211,
            6
        )
        Assert.assertEquals("2021/06/26 16:19:41", ticket.getInsertionDate())
    }

    // 1624609181211
    @Test
    fun testGetActivationDate() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            1624609181211,
            6,
            1624695581211
        )
        Assert.assertEquals("2021/06/26 16:19:41", ticket.getActivationDate())
    }

    @Test
    fun testGetExpirationDate() {
        val ticket = Ticket(
            Ticket.Type.HOUR_PASS,
            1624609181211,
            6,
            1624695581211
        )
        Assert.assertEquals("2021/06/26 22:19:41", ticket.getExpirationDate())
    }
}