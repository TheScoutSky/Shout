package de.theskyscout.shout

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.theskyscout.shout.data.database.entity.Chat
import de.theskyscout.shout.data.database.entity.User
import de.theskyscout.shout.data.fakeChats
import de.theskyscout.shout.data.fakeUsers

class MainViewModel : ViewModel() {

    lateinit var navController: NavController
        private set

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    // Test
    fun getChatById(id: String) : Chat? {
        return fakeChats.find { it.data.id == id }
    }

    fun getUserById(id: String) : User? {
        return fakeUsers.find { it.data.id == id }
    }
}