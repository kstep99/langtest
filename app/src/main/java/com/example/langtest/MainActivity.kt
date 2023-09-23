package com.example.langtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.langtest.ui.theme.LangtestTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = RetrofitClient.api
        val call = api.translateText("de", "en", "Hallo Welt") // Translating "Hallo Welt" from German to English

        call.enqueue(object: Callback<TranslationResponse> {
            override fun onResponse(call: Call<TranslationResponse>, response: Response<TranslationResponse>) {
                if (response.isSuccessful) {
                    val translatedText = response.body()?.translatedText
                    // Use the translated text, for example update your UI
                    setContent {
                        LangtestTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                Greeting(translatedText ?: "Translation failed")
                            }
                        }
                    }
                } else {
                    // Handle the error scenario. Maybe show an error message to the user.
                }
            }

            override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
                // This is called if the network request failed, for example no internet connection.
                // Handle this scenario.
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LangtestTheme {
        Greeting("Android")
    }
}
