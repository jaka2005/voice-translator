import kotlinx.coroutines.delay
import kotlin.random.Random

class JVMVoiceRecognizer : VoiceRecognizer {
    override suspend fun recognize(audio: Audio): RecognizingResult {
        delay(Random.nextLong(1, 3) * 1000)
        println("recognized")

        return if(Random.nextBoolean()) RecognizingResult.Recognized("some recognized $audio") else RecognizingResult.NotRecognized
    }

}

actual fun buildRecognizer(): VoiceRecognizer = JVMVoiceRecognizer()