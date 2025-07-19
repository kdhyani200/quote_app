package kd.dhyani.quotesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import kd.dhyani.quotesapp.R
import kd.dhyani.quotesapp.room.FavoriteQuoteViewModel

@Composable
fun FavScreen(
    onNavigateBack: () -> Unit,
    viewModel: FavoriteQuoteViewModel = viewModel(),
    onNavigateToMainCard: (title: String, writer: String) -> Unit
) {
    val favoriteQuotes = viewModel.favorites.collectAsState().value

    Column(modifier = Modifier.fillMaxSize(), Arrangement.SpaceBetween) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFe5e7e9))
                .weight(0.06f)
                .background(colorResource(id = R.color.bar)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onNavigateBack() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = colorResource(id = R.color.back_arrow)
            )
            Text(
                modifier = Modifier.padding(start = 125.dp),
                text = "Quotes",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF003aff)
            )
        }

        Box(modifier = Modifier
            .weight(0.77f)
            .fillMaxSize()
            .background(color = colorResource(id = R.color.main_back))) {
            if (favoriteQuotes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.empty),
                        contentDescription = "No favorites found",
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(16.dp)
                            .alpha(0.5f)
                    )
                }
            }
            else {
                LazyColumn {
                    items(favoriteQuotes) { quote ->
                        QuoteCard(
                            quote = quote.title,
                            author = quote.writer,
                            onClick = {
                                onNavigateToMainCard(quote.title, quote.writer)
                            },
                            isFavorite = true,
                            onFavoriteToggle = {
                                viewModel.toggleFavorite(quote.title, quote.writer)
                            }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .height(50.dp)
                .weight(0.05f)
                .fillMaxWidth()
                .background(Color(0xFFf2f3f4))
                .background(colorResource(id = R.color.bar)),
            Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = { onNavigateBack() },
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
