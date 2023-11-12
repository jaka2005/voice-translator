import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class JVMMicrophoneListener : MicrophoneListener {
    override fun startListening() = flow<Audio> {
        var counter = 0
        while (true) {
            delay(Random.nextLong(1, 3)* 1000)
            emit(JVMAudio(++counter))
            println(":emitted")
        }
    }

}

data class JVMAudio(val id: Int) : Audio()

actual fun buildMicrophoneListener(): MicrophoneListener = JVMMicrophoneListener()