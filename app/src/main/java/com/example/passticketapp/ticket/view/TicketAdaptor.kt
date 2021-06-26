package com.example.passticketapp.ticket.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.passticketapp.R
import com.example.passticketapp.TicketApp
import com.example.passticketapp.databinding.ItemGroupBinding
import com.example.passticketapp.databinding.ItemTicketBinding
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.viewmodel.TicketViewModel

class TicketAdaptor(private val ticketViewModel: TicketViewModel, viewLifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class ViewType {
        GROUP,
        TICKET
    }
    private val groupSize = 2
    private val dayPassTicketList: ArrayList<Ticket> = ArrayList()
    private val hourPassTicketList: ArrayList<Ticket> = ArrayList()

    init {
        ticketViewModel.dayPassTicketList.observe(viewLifecycleOwner, Observer {
            Log.d("BrandonDebug", "dayPassTicketListObserver notified")
            dayPassTicketList.clear()
            dayPassTicketList.addAll(it)
            notifyDataSetChanged()
        })
        ticketViewModel.hourPassTicketList.observe(viewLifecycleOwner, Observer {
            Log.d("BrandonDebug", "hourPassTicketListObserver notified")
            hourPassTicketList.clear()
            hourPassTicketList.addAll(it)
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.GROUP.ordinal -> GroupViewHolder(
                ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> TicketViewHolder(
                ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                (holder as GroupViewHolder).apply {
                    viewBinding.textTitle.text = itemView.context.getString(R.string.group_day_pass)
                }
            }
            in 1..dayPassTicketList.size -> {
                val actualPosition = position - 1
                val ticket = dayPassTicketList[actualPosition]
                onBindTicketViewHolder(holder as TicketViewHolder, ticket)
            }
            dayPassTicketList.size + 1 -> {
                (holder as GroupViewHolder).apply {
                    viewBinding.textTitle.text = itemView.context.getString(R.string.group_hour_pass)
                }
            }
            else -> {
                val actualPosition = position - dayPassTicketList.size - groupSize
                val ticket = hourPassTicketList[actualPosition]
                onBindTicketViewHolder(holder as TicketViewHolder, ticket)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0, dayPassTicketList.size + 1 -> ViewType.GROUP.ordinal
            else -> ViewType.TICKET.ordinal
        }
    }

    override fun getItemCount(): Int = dayPassTicketList.size + hourPassTicketList.size + groupSize

    private fun onBindTicketViewHolder(holder: TicketViewHolder, ticket: Ticket) {
        holder.apply {
            viewBinding.textTitle.text = ticket.getTitle()
            when (ticket.getStatus()) {
                Ticket.Status.UNACTIVATED -> {
                    viewBinding.buttonActivate.isEnabled = true
                    viewBinding.buttonActivate.text = itemView.context.getString(R.string.activate)
                }
                Ticket.Status.ACTIVATED -> {
                    viewBinding.buttonActivate.isEnabled = false
                    viewBinding.buttonActivate.text = itemView.context.getString(R.string.activated)
                }
                Ticket.Status.EXPIRED -> {
                    viewBinding.buttonActivate.isEnabled = false
                    viewBinding.buttonActivate.text = itemView.context.getString(R.string.expired)
                }
            }
            viewBinding.buttonActivate.setOnClickListener {
                ticket.activatedTimeMillis = System.currentTimeMillis()
                viewBinding.buttonActivate.isEnabled = false
                viewBinding.buttonActivate.text = itemView.context.getString(R.string.activated)
                Log.d("BrandonDebug", "[Click] DAY_PASS ticket = $ticket")
            }
            viewBinding.root.setOnClickListener {
                TicketApp.currentActivity?.let {
                    DetailDialogFragment(ticketViewModel, ticket).show(it.supportFragmentManager, DetailDialogFragment.TAG)
                }
            }
        }
    }

    private class GroupViewHolder(val viewBinding: ItemGroupBinding) : RecyclerView.ViewHolder(viewBinding.root)
    private class TicketViewHolder(val viewBinding: ItemTicketBinding) : RecyclerView.ViewHolder(viewBinding.root)
}