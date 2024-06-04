package de.theskyscout.shout.data.database.entity

data class UserData(
    val id: String,
    var name: String,
    val phoneNumber: String,
    var bio: String?,
    var profilePictureURI: String?,
    var status: UserStatus,
    val chats: ArrayList<Chat> = arrayListOf(),
    var fcmToken: String,

)

enum class UserStatus {
    ONLINE,
    OFFLINE,
}

class User (
    val data: UserData
) {
    // User functions
}