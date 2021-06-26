package com.example.passticketapp.ticket.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.passticketapp.R
import com.example.passticketapp.databinding.DialogDetailBinding
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.viewmodel.TicketViewModel

class DetailDialogFragment(private val ticketViewModel: TicketViewModel, private val ticket: Ticket) : DialogFragment() {
    companion object {
        const val TAG = "DetailDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewBinding: DialogDetailBinding = DialogDetailBinding.inflate(LayoutInflater.from(activity))
        viewBinding.textTitle.text = ticket.getTitle()
        when (ticket.getStatus()) {
            Ticket.Status.UNACTIVATED -> {
                viewBinding.textStatus.text = getString(R.string.unactivated)
            }
            Ticket.Status.ACTIVATED -> {
                viewBinding.textStatus.text = getString(R.string.activated)
            }
            Ticket.Status.EXPIRED -> {
                viewBinding.textStatus.text = getString(R.string.expired)
            }
        }
        viewBinding.textInsertionDate.text = ticket.getInsertionDate()
        viewBinding.textActivationDate.text = ticket.getActivationDate()
        viewBinding.textExpirationDate.text = ticket.getExpirationDate()

        val dialog = AlertDialog.Builder(requireActivity())
            .setView(viewBinding.root)
            .setPositiveButton(
                R.string.confirm
            ) { dialog, id ->
                ticketViewModel.notifyObserver()
                dialog.cancel()
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, id ->
                dialog.cancel()
            }
            .setCancelable(false)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
}