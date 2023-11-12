interface VoiceRecognizer {
    suspend fun recognize(audio: Audio): RecognizingResult
}

expect fun buildRecognizer(): VoiceRecognizer