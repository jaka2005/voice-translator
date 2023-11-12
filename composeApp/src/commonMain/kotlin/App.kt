import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun App(
    microphoneListener: MicrophoneListener = buildMicrophoneListener(),
    voiceRecognizer: VoiceRecognizer = buildRecognizer()
) {
    val listState = rememberLazyListState()
    val composableScope = rememberCoroutineScope()
    val recognizingResults = remember { mutableStateListOf<RecognizingResult>() }

    composableScope.launch {
        microphoneListener.startListening().collect {
            composableScope.launch {
                recognizingResults.add(voiceRecognizer.recognize(it))
                listState.animateScrollToItem(recognizingResults.lastIndex)
            }
        }
    }

    MaterialTheme {
        LazyColumn(Modifier.fillMaxWidth(), state = listState) {
            items(recognizingResults) {
                Text(
                    when(it) {
                        RecognizingResult.NotRecognized -> "*noize*"
                        is RecognizingResult.Recognized -> it.text
                    }
                )
            }
        }
    }
}