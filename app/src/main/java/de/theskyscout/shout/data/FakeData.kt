package de.theskyscout.shout.data

import de.theskyscout.shout.data.database.entity.Chat
import de.theskyscout.shout.data.database.entity.ChatData
import de.theskyscout.shout.data.database.entity.Message
import de.theskyscout.shout.data.database.entity.MessageData
import de.theskyscout.shout.data.database.entity.MessageStatus
import de.theskyscout.shout.data.database.entity.User
import de.theskyscout.shout.data.database.entity.UserData
import de.theskyscout.shout.data.database.entity.UserStatus
import java.sql.Timestamp


val fakeMessages = arrayListOf(
    Message(
        data = MessageData(
            id = "test1",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "Hello",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test2",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "Hi",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test3",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "How are you?",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test4",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "I'm fine",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test5",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "Good to hear",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test6",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "How about you?",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test7",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "I'm good",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test8",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "That's great",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test9",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "I have to go now",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test10",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "Bye",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test11",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "Bye",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test12",
            chatId = "chat1",
            senderId = "you",
            receiverId = "me",
            message = "Bye",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
    Message(
        data = MessageData(
            id = "test13",
            chatId = "chat1",
            senderId = "me",
            receiverId = "you",
            message = "Bye",
            timestamp = Timestamp(System.currentTimeMillis()),
            status = MessageStatus.SENT,
            edit = null
        )
    ),
)

val fakeChats = arrayListOf(
    Chat(
        data = ChatData(
            id = "chat1",
            senderId = "me",
            receiverId = "you",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat2",
            senderId = "me",
            receiverId = "someone1",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat3",
            senderId = "me",
            receiverId = "someone2",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat1",
            senderId = "me",
            receiverId = "you",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat2",
            senderId = "me",
            receiverId = "someone1",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat3",
            senderId = "me",
            receiverId = "someone2",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat1",
            senderId = "me",
            receiverId = "you",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat2",
            senderId = "me",
            receiverId = "someone1",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat3",
            senderId = "me",
            receiverId = "someone2",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat1",
            senderId = "me",
            receiverId = "you",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat2",
            senderId = "me",
            receiverId = "someone1",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
    Chat(
        data = ChatData(
            id = "chat3",
            senderId = "me",
            receiverId = "someone2",
            messages = fakeMessages,
            pinnedMessage = null
        )
    ),
)

val fakeUsers = listOf(
    User(
        data = UserData(
            id = "me",
            name = "Me",
            phoneNumber = "1234567890",
            bio = "Hello",
            profilePictureURI = null,
            status = UserStatus.ONLINE,
            chats = fakeChats,
            fcmToken = "fcmToken"
        )
    ),
    User(
        data = UserData(
            id = "you",
            name = "You",
            phoneNumber = "0987654321",
            bio = "Hi",
            profilePictureURI = "https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=1200&h=992&fl=progressive&q=70&fm=jpg",
            status = UserStatus.ONLINE,
            chats = arrayListOf(),
            fcmToken = "fcmToken"
        )
    ),

    User(
        data = UserData(
            id = "someone1",
            name = "Someone1",
            phoneNumber = "1234567890",
            bio = "Hello",
            profilePictureURI = "https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
            status = UserStatus.ONLINE,
            chats = arrayListOf(),
            fcmToken = "fcmToken"
        )
    ),
    User(
        data = UserData(
            id = "someone2",
            name = "Someone2",
            phoneNumber = "1234567890",
            bio = "Hello",
            profilePictureURI = "https://i.pinimg.com/736x/25/78/61/25786134576ce0344893b33a051160b1.jpg",
            status = UserStatus.ONLINE,
            chats = arrayListOf(),
            fcmToken = "fcmToken"
        )
    ),
)

fun getFakeUserById(id: String): User? {
    return fakeUsers.find { it.data.id == id }
}

fun getFakeChatById(id: String): Chat? {
    return fakeChats.find { it.data.id == id }
}