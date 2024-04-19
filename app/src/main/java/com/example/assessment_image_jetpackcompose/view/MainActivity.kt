package com.example.assessment_image_jetpackcompose.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.assessment_image_jetpackcompose.ui.theme.AssessmentImageJetpackcomposeTheme
import com.example.assessment_image_jetpackcompose.viewmodel.UnsplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val model: UnsplashViewModel = viewModel()
            AssessmentImageJetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val list = model.unsplashImg.collectAsLazyPagingItems()



                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            verticalItemSpacing = 4.dp,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxSize()
                        )
                        {

                            items(count = list.itemCount,
                                key = list.itemKey { it.id ?: "" },
                                contentType = list.itemContentType { "contentType" }) { index ->
                                val item = list[index]
                                ImageCard(item?.urls?.small ?: "")
                            }

                            list.apply {
                                when {
                                    loadState.refresh is LoadState.Loading -> {
                                        item {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(64.dp),
                                                    color = MaterialTheme.colorScheme.secondary,
                                                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                                )
                                            }

                                        }
                                    }

                                    loadState.refresh is LoadState.Error -> {
                                        val error = list.loadState.refresh as LoadState.Error
                                        item {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                                                Button(onClick = { retry() }) {
                                                    Text(
                                                        text = "Retry",
                                                        color = Color.Black
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    loadState.append is LoadState.Loading -> {
                                        item {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.width(64.dp),
                                                    color = MaterialTheme.colorScheme.secondary,
                                                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                                )
                                            }
                                        }
                                    }

                                    loadState.append is LoadState.Error -> {
                                        val error = list.loadState.append as LoadState.Error
                                        item {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                                                Button(onClick = { retry() }) {
                                                    Text(
                                                        text = "Retry",
                                                        color = Color.Black
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }


                    }


                }

            }
        }
    }
}


//@Preview
@Composable
fun ImageCard(imgUrl: String) {
    var image by remember { mutableStateOf<Bitmap?>(null) }


    LaunchedEffect(Dispatchers.IO) {
        image =
            getBitmapFromURL(imgUrl)

    }

    if (image != null) {

        Image(

            bitmap = image!!.asImageBitmap(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }

}

suspend fun getBitmapFromURL(src: String?): Bitmap? {

    return try {
        withContext(Dispatchers.IO) {
            val url = URL(src)
            val connection =
                url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            BitmapFactory.decodeStream(input)
        }

    } catch (e: IOException) {
        // Log exception
        null
    }
}
