package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme (useDarkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

class PainterResourceWrapper (
    var resourceID: Int,
    var titleStringId: Int,
    var infoStringId: Int
) {

}

@Composable
@Preview
fun ArtSpacePreview () {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArtSpaceLayout()
        }
    }
}

@Composable
fun ArtSpaceLayout (modifier: Modifier = Modifier) {

    var i by remember { mutableStateOf(0) }

    val artList = listOf(
        PainterResourceWrapper(
            R.drawable.goku_by_edu_souza,
            R.string.title_1,
            R.string.info_1),
        PainterResourceWrapper(
            R.drawable.doggust2023_by_jonathan_wesslund,
            R.string.title_2,
            R.string.info_2),
        PainterResourceWrapper(
            R.drawable.lichenbackfalls_by_asanee_srikijvilaikul,
            R.string.title_3,
            R.string.info_3),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedCard (
            modifier = Modifier.padding(4.dp),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            border = BorderStroke(1.dp, Color.Transparent),
        ) {
            ArtRow(
                imageId = artList[i].resourceID,
                modifier = Modifier
            )

            ArtCaption(
                artTitleId = artList[i].titleStringId,
                artInfoId = artList[i].infoStringId,
                modifier = Modifier
            )
        }
        ArtButtonRow(
            modifier = Modifier
        )
    }
}


@Composable
fun ArtRow (imageId: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        ElevatedCard (
            elevation = CardDefaults
                 .cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .height(550.dp)
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = "Artwork",
                contentScale = ContentScale.Inside,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ArtCaption (artTitleId: Int, artInfoId: Int, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .padding(12.dp),
    ) {

        Text(
            text = stringResource(artTitleId),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = stringResource(artInfoId)
        )
    }
}

@Composable
fun ArtButtonRow (modifier: Modifier = Modifier) {

    var number by remember { mutableStateOf(0) }
    val numOfArtworks = 3
    Text("Current Number: $number")

    Row (
         modifier = Modifier
             .fillMaxSize()
             .padding(8.dp),
         horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(onClick = { },
            Modifier
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Previous")
        }
        OutlinedButton(
            onClick = {
                      number = (number + 1) % numOfArtworks
            },
            Modifier
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Next")
        }
    }
}


