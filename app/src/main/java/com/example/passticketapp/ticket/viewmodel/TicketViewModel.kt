package com.example.passticketapp.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.model.TicketRepository

class TicketViewModel(private val repository: TicketRepository) : ViewModel() {
    val dayPassTicketList: MutableLiveData<List<Ticket>> = MutableLiveData<List<Ticket>>().apply {
        value = repository.dayPassTicketList
    }
    val hourPassTicketList: MutableLiveData<List<Ticket>> = MutableLiveData<List<Ticket>>().apply {
        value = repository.hourPassTicketList
    }

    fun notifyObserver() {
        notifyDayPassTicketObserver()
        notifyHourPassTicketObserver()
    }

    fun addDayPassTicket(ticket: Ticket) {
        repository.addDayPassTicket(ticket)
        notifyDayPassTicketObserver()
    }

    fun addHourPassTicket(ticket: Ticket) {
        repository.addHourPassTicket(ticket)
        notifyHourPassTicketObserver()
    }

    private fun notifyDayPassTicketObserver() {
        dayPassTicketList.value = dayPassTicketList.value
    }

    private fun notifyHourPassTicketObserver() {
        hourPassTicketList.value = hourPassTicketList.value
    }
}