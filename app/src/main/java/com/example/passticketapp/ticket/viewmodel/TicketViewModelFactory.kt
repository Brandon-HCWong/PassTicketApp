package com.example.passticketapp.ticket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.passticketapp.ticket.model.TicketRepository

class TicketViewModelFactory(val repository: TicketRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TicketViewModel(repository) as T
    }
}