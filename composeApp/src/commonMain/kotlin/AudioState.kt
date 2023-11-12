sealed class RecognizingResult {
    data class Recognized(val text: String) : RecognizingResult()
    data object NotRecognized : RecognizingResult()
}