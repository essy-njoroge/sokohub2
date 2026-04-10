package com.serah.sokohub.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.navigation.ROUT_HOME
import com.navigation.ROUT_REGISTER
import com.serah.sokohub.R
import com.serah.sokohub.ui.theme.Darkbrown

@Composable
fun LoginScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.background), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,



    ) {

        Image(
            painter= painterResource(R.drawable.register),
            contentDescription = "register",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))



        Text(
            text = "Welcome Back!!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,

            )
        Spacer(modifier = Modifier.height(10.dp))

        //variables

        var email by remember { mutableStateOf("") }

        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = {email=it },
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            label = {Text(text = "Email Address")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Darkbrown,
                focusedBorderColor = Color.DarkGray,
                unfocusedLeadingIconColor = Darkbrown,

                )



        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password=it },
            modifier = Modifier.width(350.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            label = {Text(text = "Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Darkbrown,
                focusedBorderColor = Color.DarkGray,
                unfocusedLeadingIconColor = Darkbrown,

                ),
            visualTransformation = PasswordVisualTransformation()



        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Darkbrown),
            modifier = Modifier.width(350.dp),
            onClick = {}


        ) {
            Text(
                text = "Login",

                )
        }

        Spacer(modifier = Modifier.height(10.dp))


        TextButton(onClick = {navController.navigate(ROUT_REGISTER)}) {


            Text(
                text = "Don't have an account? Register.",
                fontSize = 15.sp,

                )

        }


        TextButton(onClick = {navController.navigate(ROUT_HOME)}) {


            Text(
                text = "Go To Home",
                fontSize = 15.sp,

                )

        }




















    }



}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())



}