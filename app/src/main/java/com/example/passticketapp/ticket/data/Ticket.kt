package com.example.passticketapp.ticket.data

import android.util.Log
import com.example.passticketapp.R
import com.example.passticketapp.TicketApp
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Ticket(
    var type: Type,
    var insertionTimeMillis: Long,
    var durationTimeHours: Long,
    var activatedTimeMillis: Long = -1,
    ) {
    enum class Type {
        DAY_PASS,
        HOUR_PASS
    }
    enum class Status {
        UNACTIVATED,
        ACTIVATED,
        EXPIRED
    }

    private val dateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault())

    fun isActivated() = activatedTimeMillis > 0

    fun isExpired(): Boolean {
        if (isActivated()) {
            val currentTimeMillis = System.currentTimeMillis()
            val activatedDuration = currentTimeMillis - activatedTimeMillis
            return when (type) {
                Type.DAY_PASS -> {
                    val validDuration = TimeUnit.DAYS.convert(durationTimeHours, TimeUnit.HOURS)
                    Log.d("BrandonDebug", "[isExpired] DAY_PASS day diff = ${TimeUnit.DAYS.convert(activatedDuration, TimeUnit.MILLISECONDS)}")
                    TimeUnit.DAYS.convert(activatedDuration, TimeUnit.MILLISECONDS) > validDuration
                }
                Type.HOUR_PASS -> {
                    val validDuration = TimeUnit.SECONDS.convert(durationTimeHours, TimeUnit.HOURS)
                    Log.d("BrandonDebug", "[isExpired] HOUR_PASS sec diff = ${TimeUnit.SECONDS.convert(activatedDuration, TimeUnit.MILLISECONDS)}")
                    TimeUnit.SECONDS.convert(activatedDuration, TimeUnit.MILLISECONDS) > validDuration
                }
            }
        }
        return false
    }

    fun getTitle(): String {
        if (durationTimeHours <= 0) {
            throw IllegalStateException("Should initialize durationTimeHours first");
        }

        return when (type) {
            Type.DAY_PASS -> {
                val dayDuration = TimeUnit.DAYS.convert(durationTimeHours, TimeUnit.HOURS)
                TicketApp.getContext().getString(R.string.day_pass, dayDuration)
            }
            Type.HOUR_PASS -> {
                TicketApp.getContext().getString(R.string.hour_pass, durationTimeHours)
            }
        }
    }

    fun getStatus(): Status {
        if (isExpired()) {
            return Status.EXPIRED
        }
        return when (isActivated()) {
            true -> {
                Status.ACTIVATED
            }
            false -> {
                Status.UNACTIVATED
            }
        }
    }

    fun getInsertionDate(): String {
        return dateFormat.format(insertionTimeMillis)
    }

    fun getActivationDate(): String {
        return when (isActivated()) {
            true -> dateFormat.format(activatedTimeMillis)
            false -> TicketApp.getContext().getString(R.string.unknown)
        }
    }

    fun getExpirationDate(): String {
        if (isActivated()) {
            val expirationTimeMillis = activatedTimeMillis + TimeUnit.MILLISECONDS.convert(durationTimeHours, TimeUnit.HOURS)
            val simpleDateFormat =  when (type) {
                Type.DAY_PASS -> SimpleDateFormat("yyyy/MM/dd 00:00:00", Locale.getDefault())
                Type.HOUR_PASS -> SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault())
            }
            return simpleDateFormat.format(expirationTimeMillis)
        }
        return TicketApp.getContext().getString(R.string.unknown)
    }

    override fun toString(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd hh:mm:ss", Locale.getDefault())
        val duration = when (type) {
            Type.DAY_PASS -> TimeUnit.DAYS.convert(durationTimeHours, TimeUnit.HOURS)
            Type.HOUR_PASS -> durationTimeHours
        }
        val stringBuilder = StringBuilder()
        stringBuilder
            .append("Type : $type\n")
            .append("Insertion Date : ${simpleDateFormat.format(insertionTimeMillis)}\n")
            .append("Activated Date : ${simpleDateFormat.format(activatedTimeMillis)}\n")
            .append("Duration : ${duration}\n")
            .append("isActivated : ${isActivated()}\n")
            .append("isExpired : ${isExpired()}\n")
            .append("Title : ${getTitle()}\n")
            .append("Expiration Date : ${getExpirationDate()}\n")

        return stringBuilder.toString()
    }
}

