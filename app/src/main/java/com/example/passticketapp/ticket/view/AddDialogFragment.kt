package com.example.passticketapp.ticket.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.passticketapp.R
import com.example.passticketapp.databinding.DialogAddBinding
import com.example.passticketapp.ticket.data.Ticket
import com.example.passticketapp.ticket.model.TicketRepository
import com.example.passticketapp.ticket.viewmodel.TicketViewModel
import java.util.concurrent.TimeUnit

class AddDialogFragment(private val ticketViewModel: TicketViewModel) : DialogFragment() {
    companion object {
        const val TAG = "AddDialogFragment"
        private const val DEFAULT_VALUE = 1
        private const val MAX_HOUR_PASS_VALUE = 23
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewBinding: DialogAddBinding = DialogAddBinding.inflate(LayoutInflater.from(activity))
        viewBinding.radioGroupType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_day -> {
                    Log.d("BrandonDebug", "Dialog Radio = radio_button_day")
                }
                R.id.radio_button_hour -> {
                    Log.d("BrandonDebug", "Dialog Radio = radio_button_hour")
                }
            }
        }

        viewBinding.editTextPeriod.setText(DEFAULT_VALUE.toString())
        viewBinding.editTextPeriod.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.d("BrandonDebug", "Dialog beforeTextChanged = $s")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("BrandonDebug", "Dialog onTextChanged = $s")
                val currentValue = s.toString().toInt()
                if (viewBinding.radioGroupType.checkedRadioButtonId == R.id.radio_button_hour &&
                    currentValue > MAX_HOUR_PASS_VALUE) {
                    viewBinding.editTextPeriod.setText(MAX_HOUR_PASS_VALUE.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {
                Log.d("BrandonDebug", "Dialog afterTextChanged = $s")
                if (s.isEmpty() || s.toString().toInt() < DEFAULT_VALUE) {
                    viewBinding.editTextPeriod.setText(DEFAULT_VALUE.toString())
                }
            }
        })

        // Use the Builder class for convenient dialog construction
        val dialog = AlertDialog.Builder(requireActivity())
            .setView(viewBinding.root)
            .setPositiveButton(
                R.string.add
            ) { dialog, id ->
                when (viewBinding.radioGroupType.checkedRadioButtonId) {
                    R.id.radio_button_day -> {
                        Log.d("BrandonDebug", "Dialog Radio = radio_button_day")
                        ticketViewModel.addDayPassTicket(
                            Ticket(
                                Ticket.Type.DAY_PASS,
                                System.currentTimeMillis(),
                                TimeUnit.HOURS.convert(viewBinding.editTextPeriod.text.toString().toLong(), TimeUnit.DAYS)
                            )
                        )
                    }
                    R.id.radio_button_hour -> {
                        Log.d("BrandonDebug", "Dialog Radio = radio_button_hour")
                        ticketViewModel.addHourPassTicket(
                            Ticket(
                                Ticket.Type.HOUR_PASS,
                                System.currentTimeMillis(),
                                viewBinding.editTextPeriod.text.toString().toLong()
                            )
                        )
                    }
                }
                Log.d("BrandonDebug", "Dialog Add : Period = ${viewBinding.editTextPeriod.text}")
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