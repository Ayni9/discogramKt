import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import dev.kord.core.Kord
import dev.kord.core.entity.Sticker
import dev.kord.core.event.user.PresenceUpdateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main() {
    val kord = Kord("YOUR_TOKEN")
    val bot = bot {
        token = "YOUR_TOKEN"
    }
    kord.on<PresenceUpdateEvent> {
        println(this.user.id)
        val rpcvalue = this.presence.activities.map {
            "${it.name}\n${it.details}\n${it.state}\nsince: ${it.data.createdAt}"
        }
        bot.editMessageText(
            chatId = ChatId.fromId(123456),
            text = rpcvalue.firstOrNull() ?: "nothing here yet",
            messageId = 1234
        )
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.GuildPresences
    }
}