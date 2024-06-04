package de.theskyscout.shout.data.database.entity

import java.sql.Timestamp
import java.util.Calendar
import java.util.Date

data class MessageData(
    val id: String,
    val chatId: String,
    val senderId: String,
    val receiverId: String,
    val message: String,
    val timestamp: Timestamp,
    val status: MessageStatus,
    val edit: MessageEditData?,
)

class Message (
    val data: MessageData
) {
    // Message functions

    fun isFrom(user: User): Boolean {
        return data.senderId == user.data.id
    }

    fun isIn(chat: Chat): Boolean {
        return data.chatId == chat.data.id
    }

    fun getTimeString(): String {
        val date = Date(data.timestamp.time)
        val calender = Calendar.getInstance()
        calender.time = date
        val hour = if (calender.get(Calendar.HOUR_OF_DAY) < 10) "0${calender.get(Calendar.HOUR_OF_DAY)}" else calender.get(Calendar.HOUR_OF_DAY).toString()
        val minute = if (calender.get(Calendar.MINUTE) < 10) "0${calender.get(Calendar.MINUTE)}" else calender.get(Calendar.MINUTE).toString()
        val time = "$hour:$minute"
        return time
    }
}

data class MessageEditData(
    val edited: Boolean,
    val editedMessage: String,
    val editedTimestamp: Timestamp,
)

enum class MessageStatus (val text: String) {
    SENT("Sent"),
    DELIVERED("Delivered"),
    READ("Read"),
    ERROR("Error")
}