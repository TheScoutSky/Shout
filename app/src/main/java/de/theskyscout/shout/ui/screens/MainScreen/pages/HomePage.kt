package de.theskyscout.shout.ui.screens.MainScreen.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import de.theskyscout.shout.data.database.entity.Chat
import de.theskyscout.shout.data.getFakeUserById
import de.theskyscout.shout.ui.screens.ChatScreen.ChatScreen
import de.theskyscout.shout.viewModel
import java.util.Calendar
import java.util.Date

@Composable
fun HomePage(chats: List<Chat>) {
    Box (
        modifier = Modifier
            .fillMaxSize()
    ) {
        ChatList(chats = chats)
    }
}

@Composable
fun ChatList(chats: List<Chat>) {
    LazyColumn {
        item { Spacer(modifier = Modifier.height(100.dp)) } //TODO: Height should be dynamic
        items(chats.size) { index ->
            ChatItem(chat = chats[index])
        }
        item { Spacer(modifier = Modifier.height(70.dp)) } //TODO: Height should be dynamic
    }
}

@Composable
fun ChatItem(chat: Chat) {
    val chatUser = getFakeUserById(chat.data.receiverId) ?: return
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .focusable()
            .clickable {
                // Open Chat
                viewModel.navController.navigate(ChatScreen(chatId = chat.data.id))
            }
            .padding(16.dp)
    ) {
        Row {
            // Chat Image
            val imageUrl = chatUser.data.profilePictureURI
            if (imageUrl == null) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Chat Image",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp)
                )
            } else {
                val imagePainter = rememberImagePainter(
                    data = imageUrl,
                    builder = {
                        val sizes = with(LocalDensity.current) { 50.dp.toPx().toInt() }
                        size( sizes, sizes)
                    }
                )
                Image(
                    painter = imagePainter,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }

            val latestMessage = chat.getLatestMessage() ?: return@Row

            // Chat Info
            Column (
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = chatUser.data.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = latestMessage.data.message,
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column (
                horizontalAlignment = Alignment.End

            ) {
                Text(
                    text = latestMessage.getTimeString(),
                    style = MaterialTheme.typography.bodySmall
                )
                if (chat.unreadMessagesCount() != 0) {
                    Badge (
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                    {
                        Text(
                            text = chat.unreadMessagesCount().toString(),
                            fontSize = 8.sp,
                        )
                    }
                }
            }
        }
    }
}