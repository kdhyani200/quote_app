package kd.dhyani.quotesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kd.dhyani.quotesapp.R

@Composable
fun Try() {

    Column(modifier = Modifier.fillMaxSize(),Arrangement.SpaceBetween) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF18181a))
//                .height(50.dp)
                .weight(0.06f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(modifier = Modifier.padding(start = 10.dp),
                tint = Color(0xFFffffff),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null)
            Text(modifier = Modifier.padding(start = 125.dp),
                text = "Quotes",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF003aff)
            )
        }

        Box(modifier = Modifier
            .weight(0.77f)
            .fillMaxSize().background(color = Color(0xFF000000))){

        }

        Row (modifier = Modifier
            .height(50.dp)
            .weight(0.05f)
            .fillMaxWidth()
            .background(Color(0xFF18181a)),
            Arrangement.SpaceEvenly){

            Button(
                onClick = { /* your logic */ },
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(top = 2.dp, bottom = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF003aff)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp) // optional: control size
                )
            }

            Button(
                onClick = { /* your logic */ },
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(top = 2.dp, bottom = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFF003aff)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp) // optional: control size
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun TryPre(){
    Try()
}