package kd.dhyani.quotesapp.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kd.dhyani.quotesapp.R
import kd.dhyani.quotesapp.room.FavoriteQuoteViewModel
import kotlinx.coroutines.launch

@Composable
fun MaxCard(
    title: String,
    writer: String,
    onNavigateBack: () -> Unit,
    viewModel: FavoriteQuoteViewModel = viewModel()
) {
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val coroutineScope = rememberCoroutineScope()
    var isFav by remember { mutableStateOf(false) }

    // Check if already favorited on load
    LaunchedEffect(Unit) {
        isFav = viewModel.isFavorite(title, writer)
    }

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

        Box(
            modifier = Modifier
                .weight(0.77f)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.main_back))
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.bar)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(modifier = Modifier.padding(16.dp)) {
                    // Top-right icons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder ,
                            contentDescription = "Favorite",
                            tint = if (isFav) Color(0xFF003aff) else Color.Gray,
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
                                        viewModel.toggleFavorite(title, writer)
                                        isFav = !isFav
                                    }
                                }
                                .padding(end = 12.dp)
                                .size(18.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.copy),
                            contentDescription = "Copy",
                            modifier = Modifier
                                .clickable {
                                    val quoteText = "\"$title\" - $writer"
                                    val clip = ClipData.newPlainText("Quote", quoteText)
                                    clipboardManager.setPrimaryClip(clip)
                                    Toast.makeText(context, "Quote copied!", Toast.LENGTH_SHORT).show()
                                }
                                .padding(end = 12.dp)
                                .size(16.dp)
                                .padding(top = 2.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title,
                            color = colorResource(id = R.color.title),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "- $writer",
                            color = colorResource(id = R.color.writer),
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(end = 4.dp, bottom = 25.dp),
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MaxPre() {
    MaxCard(
        title = "Life is what happens when you're busy making other plans.",
        writer = "John Lennon",
        onNavigateBack = {}
    )
}
