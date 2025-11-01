package com.alonso.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.R
import com.alonso.lab.ui.theme.ChallengeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeComposeTheme {
                Scaffold(
                    topBar = {
                        SearchBar(
                            textChange = {},
                            textValue = "",
                            onSearch = {}
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        DecoratedTextField()
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchBar(
    textChange: (String) -> Unit,
    textValue: String,
    autofocus: Boolean = false,
    onSearch: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = textValue,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onSearch() }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            disabledIndicatorColor = Color.Transparent,
            disabledLeadingIconColor = Color.Black,
            disabledPlaceholderColor = Color.Black


        ),

        onValueChange = { textChange(it) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                Modifier.size(22.dp)
            )
        },
        placeholder = {
            Text(
                "Search...",
                style = TextStyle(fontSize = 14.sp)
            )
        },
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 6.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .background(Color.LightGray, RoundedCornerShape(12.dp))

    )

    LaunchedEffect(Unit) {
        if (autofocus) {
            focusRequester.requestFocus()
        }
    }
}


@Composable
fun DecoratedTextField() {
    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 50.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "Email",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = "Enter email",
                            style = TextStyle(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(
                            painterResource(id = R.drawable.ic_search),
                            contentDescription = "Clear text",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    ChallengeComposeTheme {

    }
}