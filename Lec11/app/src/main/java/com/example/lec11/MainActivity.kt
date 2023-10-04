package com.example.lec11

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lec11.ui.theme.Lec11Theme
import com.example.lec11.ForegroundService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        setContent {
          Lec11Theme {
              Column(
                  modifier = Modifier.fillMaxSize(),
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                    Button(
                        onClick = {
                            Intent(applicationContext,ForegroundService::class.java).also {
                                it.action = ForegroundService.Action.START.toString()
                                startService(it)
                            }
                        }
                    ) {
                        Text(text = "Start Service")
                    }
                  Button(
                      onClick = {
                          Intent(applicationContext,ForegroundService::class.java).also {
                              it.action = ForegroundService.Action.STOP.toString()
                              startService(it)
                          }
                      }
                  ) {
                      Text(text = "Stop Service")
                  }

                  Button(
                      onClick = {
                          Intent(applicationContext,CountDownService::class.java).also { 
                              startService(it)
                          }
                          
                      }
                  ) {
                    Text(text = "Start CountDown")
                  }
              }

          }
        }

    }
    @Composable
    fun CountdownScreen(service: CountDownService) {
        var remainingSeconds by remember { mutableStateOf(60) }
        var isCountingDown by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Remaining seconds: $remainingSeconds", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (isCountingDown) {
                        service.stopCountdown()
                    } else {
                        service.startCountdown {
                            remainingSeconds = it
                        }
                    }
                    isCountingDown = !isCountingDown
                }
            ) {
                Text(text = if (isCountingDown) "Stop Countdown" else "Start Countdown")
            }
        }

        // Unbind the service when the composable is disposed
        DisposableEffect(Unit) {
            onDispose {
                service.stopCountdown()
            }
        }
    }


}

