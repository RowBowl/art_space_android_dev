package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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
    var infoStringId: Int,
    var captionStringId: Int
)

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtSpaceLayout (modifier: Modifier = Modifier) {

    var i by remember { mutableStateOf(0) }
    var openDialog by remember { mutableStateOf(false) }
    //var number by remember { mutableStateOf(0) }
    val numOfArtworks = 3

    val artList = listOf(
        PainterResourceWrapper(
            R.drawable.goku_by_edu_souza,
            R.string.title_1,
            R.string.info_1,
            R.string.caption_1),
        PainterResourceWrapper(
            R.drawable.doggust2023_by_jonathan_wesslund,
            R.string.title_2,
            R.string.info_2,
            R.string.caption_2),
        PainterResourceWrapper(
            R.drawable.lichenbackfalls_by_asanee_srikijvilaikul,
            R.string.title_3,
            R.string.info_3,
            R.string.caption_3),
    )

    if(openDialog) {
        Dialog(
            onDismissRequest = { openDialog = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Surface (
                onClick = { openDialog = false },
                modifier = Modifier.background(Color.Red)
            ) {
                Image(
                    painter = painterResource(id = artList[i].resourceID),
                    contentDescription = "Fullscreen Artwork",
                    contentScale = ContentScale.Inside
                )
            }

        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Transparent),
    ) {
        OutlinedCard (
            modifier = Modifier
                .padding(4.dp)
                .weight(2f),
            colors = CardDefaults
                .cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            border = BorderStroke(1.dp, Color.Transparent),
        ) {
            ArtRow(
                imageId = artList[i].resourceID,
                cardOnClick = {openDialog = true},
                modifier = Modifier
            )

            ArtCaption(
                artTitleId = artList[i].titleStringId,
                artInfoId = artList[i].infoStringId,
                artCaptionId = artList[i].captionStringId,
                modifier = Modifier
            )
        }
        ArtButtonRow(
            number = i,
            onPreviousClick = {
                if(i <= 0)
                    i = numOfArtworks - 1
                else
                    i -= 1

            },
            onNextClick = {i = (i + 1) % numOfArtworks},
            modifier = Modifier
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtRow (imageId: Int, cardOnClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp, bottom = 8.dp, start = 32.dp, end = 32.dp)
            .fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        ElevatedCard (
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.size(300.dp),
            onClick = cardOnClick
        ) {
            Box (
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(imageId),
                    contentDescription = "Artwork",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                )

                Icon(
                    painterResource(id = R.drawable.ic_fullscreen_24px),
                    contentDescription = "Fullscreen Icon",
                    modifier = Modifier.alpha(0.5F)
                )
            }
        }
    }
}

@Composable
fun ArtCaption (artTitleId: Int, artInfoId: Int, artCaptionId: Int, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp),
    ) {

        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(artTitleId),
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(artInfoId)
            )
        }
        Text (
            text = stringResource(artCaptionId),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(top = 8.dp)
                .verticalScroll(ScrollState(0))
        )
    }
}

@Composable
fun ArtButtonRow (
    number: Int,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier) {

    Row (
         modifier = Modifier
             .fillMaxWidth()
             .padding(8.dp),
         horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OutlinedButton(onClick = onPreviousClick,
            Modifier
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Previous")
        }
        OutlinedButton(
            onClick = onNextClick,
            Modifier
                .size(height = 50.dp, width = 150.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Next")
        }
    }
}


