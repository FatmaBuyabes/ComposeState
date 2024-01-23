package com.joincoded.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joincoded.compose.ui.theme.ComposeTheme
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }

    @Composable
    private fun GameScreen(modifier: Modifier = Modifier) {
        val questions = listOf(
            "penguin has just 200 bones",
            "Oslo is capital of Iceland",
            "Is android an operating system"
        )

        val answers = listOf(false, false, true)

        var currentQuestionIndex by remember { mutableStateOf(0) }
        var showCorrectResult by remember { mutableStateOf(false) }
        var showWrongResult by remember { mutableStateOf(false) }
        var showNextQuestionButton by remember { mutableStateOf(false) }
        //var showAnswer by remember { mutableStateOf(true) }
        var showAnswersOptionsRow by remember { mutableStateOf(true) }


        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = questions[currentQuestionIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                fontSize = 30.sp
            )
            if (showWrongResult)
                AnswerResult(text = stringResource(R.string.wrong_answer), color = Color.Red)

            if (showCorrectResult) {
                AnswerResult(
                    text = stringResource(R.string.correct_answer),
                    color = Color.Green
                )
            }

            if (showNextQuestionButton)
                Column( verticalArrangement = Arrangement.Bottom){
                    Button(modifier = Modifier.width(400.dp),
                        onClick = {
            if (currentQuestionIndex == questions.size - 1)
                currentQuestionIndex = 0
            else
            currentQuestionIndex++
                showCorrectResult = false
                showNextQuestionButton = false
                showAnswersOptionsRow = true
                  }) {
                     Text(text = stringResource(R.string.next_question), fontSize = 20.sp)
                }

            if (showAnswersOptionsRow)
                Row(modifier = Modifier.fillMaxWidth()) {

                    Button(modifier = Modifier.width(200.dp),
                        onClick = {
                            val isAnswerCorrect = true == answers[currentQuestionIndex]
                            if (isAnswerCorrect) {
                                showCorrectResult = true
                                showNextQuestionButton = true
                                showAnswersOptionsRow = false

                            } else {
                                showWrongResult = true

                            }
                        }) {
                        Text(text = "True", fontSize = 20.sp)  }
                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        modifier = Modifier.width(200.dp),
                        onClick = { /*TODO*/ }) {
                        Text(text = "False", fontSize = 20.sp)

                    }

                }



                }
        }
    }

    @Composable
    fun AnswerResult(text: String, color: Color, modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .size(170.dp)
                .clip(CircleShape)
                .background(color = Color.Red)
        )
        {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}