package com.example.passticketapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.model.TicketRepository
import com.example.passticketapp.ticket.viewmodel.TicketViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class TicketViewModelUnitTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    lateinit var ticketRepository: TicketRepository

    lateinit var tickViewModel: TicketViewModel

    private val dayPassTicketList = arrayListOf(
        Ticket(
            Ticket.Type.DAY_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
            TimeUnit.HOURS.convert(3, TimeUnit.DAYS)
        )
    )

    private val hourPassTicketList = arrayListOf(
        Ticket(
            Ticket.Type.HOUR_PASS,
            System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS),
            6
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val ticketSlot = slot<Ticket>()
        every { ticketRepository.dayPassTicketList } returns dayPassTicketList
        every { ticketRepository.hourPassTicketList } returns hourPassTicketList
        every { ticketRepository.addDayPassTicket(capture(ticketSlot)) } answers {
            dayPassTicketList.add(ticketSlot.captured)
        }
        every { ticketRepository.addHourPassTicket(capture(ticketSlot)) } answers {
            hourPassTicketList.add(ticketSlot.captured)
        }

        tickViewModel = TicketViewModel(ticketRepository)
    }

    @Test
    fun testAddDayPassTicket() {
        tickViewModel.addDayPassTicket(
            Ticket(
                Ticket.Type.DAY_PASS,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS),
                TimeUnit.HOURS.convert(3, TimeUnit.DAYS),
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)
            )
        )
        verify {
            ticketRepository.addDayPassTicket(any())
        }
        Assert.assertTrue(tickViewModel.dayPassTicketList.value?.size == 2)
    }

    @Test
    fun testAddHourPassTicket() {
        tickViewModel.addHourPassTicket(
            Ticket(
                Ticket.Type.HOUR_PASS,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS),
                6,
                System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.HOURS)
            )
        )
        verify {
            ticketRepository.addHourPassTicket(any())
        }
        Assert.assertTrue(tickViewModel.hourPassTicketList.value?.size == 2)
    }
}