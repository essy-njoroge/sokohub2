package com.serah.sokohub.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun OnBoardingScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter= painterResource(R.drawable.product),
            contentDescription = "product",
            modifier = Modifier.size(300.dp)
            )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Welcome to SokoHub!!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Darkbrown,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Shop Smarter,",
            fontSize = 20.sp,

        )

        Text(
            text = "everywhere you go!",
            fontSize = 20.sp,

            )

        Text(
            text = "E-commerce is growing rapidly globally, with Africa seeing significant growth, particularly in Kenya, which is the third-largest market on the continent.\n",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,

            )

        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Darkbrown),
            modifier = Modifier.width(350.dp),
            onClick = { navController.navigate(ROUT_REGISTER)}


        ) {
         Text(
             text = "Get Started",

         )
        }















    }






















































































}



@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(){
    OnBoardingScreen(rememberNavController())



}
