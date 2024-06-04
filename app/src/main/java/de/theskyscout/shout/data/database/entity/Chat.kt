package de.theskyscout.shout.data.database.entity

data class ChatData(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val messages: ArrayList<Message> = arrayListOf(),
    var pinnedMessage: Message?,
)

class Chat (
    val data: ChatData
) {
    // Chat functions

    fun getLatestMessage(): Message? {
        return data.messages.lastOrNull()
    }

    fun unreadMessagesCount(): Int {
        return data.messages.count { it.data.status == MessageStatus.SENT }
    }
}