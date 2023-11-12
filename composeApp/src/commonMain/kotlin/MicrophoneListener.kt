import kotlinx.coroutines.flow.Flow

interface MicrophoneListener {
    fun startListening(): Flow<Audio>
}

expect fun buildMicrophoneListener(): MicrophoneListener