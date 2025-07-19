package kd.dhyani.quotesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kd.dhyani.quotesapp.QuoteRealtimeViewModel
import kd.dhyani.quotesapp.R

@Composable
fun HomePage(
    viewModel: QuoteRealtimeViewModel = viewModel(),
    onNavigateToMainCard: (title: String, writer: String) -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    val quotes = viewModel.quotes
    val hasError = viewModel.hasError.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.bar))
                .weight(0.06f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 160.dp),
                text = "Quotes",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF003aff)
            )
        }

        // Main content
        Box(
            modifier = Modifier
//                .padding(top = 8.dp)
                .weight(0.77f)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.main_back)),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(color = Color(0xFF003aff))
                }

                hasError -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.interneterror),
                            contentDescription = "Internet Error",
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .padding(16.dp)
                                .alpha(0.5f)
                                .size(120.dp)
                        )
                        Button(
                            modifier = Modifier.alpha(0.85f),
                            onClick = { viewModel.fetchQuotes() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003aff))
                        ) {
                            Text(text = "Refresh", color = Color.White)
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(quotes) { quote ->
                            QuoteCard(
                                quote = quote.title,
                                author = quote.writer,
                                onClick = { onNavigateToMainCard(quote.title, quote.writer) }
                            )
                        }
                    }
                }
            }
        }

        // Bottom Navigation
        Row(
            modifier = Modifier
                .height(50.dp)
                .weight(0.05f)
                .fillMaxWidth()
                .background(colorResource(id = R.color.bar)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { /* Already on Home */ },
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
                    modifier = Modifier.size(30.dp)
                )
            }

            Button(
                onClick = { onNavigateToFavorites() },
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(top = 2.dp, bottom = 3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFa6acaf)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
