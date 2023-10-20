package com.example.loginscreen.ui.login
import android.widget.Toast
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginscreen.ui.theme.LoginScreenTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val toast = remember { mutableStateOf(Toast(context)) }
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text ="Welcome",
            modifier = Modifier.fillMaxWidth().padding(36.dp),
            fontSize = 30.sp,
            textAlign = TextAlign.Center)

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester)
        )

        Button(
            onClick = {
                toast.value.cancel() // To prevent making toast stack
                coroutineScope.launch {
                if(username.value.text == "admin" && password.value.text == "password") {
                        toast.value = showToast("Login Successful", context)
                } else {
                        toast.value = showToast("Invalid credentials", context)
                    }
                    toast.value.show()
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(36.dp)
        ) {
            Text("Login")
        }
    }
}


fun showToast(message: String, context: Context) : Toast {
  return Toast.makeText(context, message, Toast.LENGTH_SHORT)
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreenTheme {
        Login()
    }
}