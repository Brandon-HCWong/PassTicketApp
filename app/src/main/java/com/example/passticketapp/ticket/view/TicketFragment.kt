package com.example.passticketapp.ticket.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passticketapp.databinding.FragmentTicketBinding
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.model.TicketRepository
import com.example.passticketapp.ticket.viewmodel.TicketViewModel
import com.example.passticketapp.ticket.viewmodel.TicketViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!
    private val ticketViewModel : TicketViewModel by viewModels {
        TicketViewModelFactory(TicketRepository())
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        binding.viewRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.viewRecycler.adapter = TicketAdaptor(ticketViewModel, viewLifecycleOwner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            AddDialogFragment(ticketViewModel).show(requireActivity().supportFragmentManager,
                AddDialogFragment.TAG
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

