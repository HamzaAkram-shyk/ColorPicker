package com.example.colorscreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorscreens.ui.model.ItemColorEvent
import com.example.colorscreens.ui.model.ItemColorUiStat
import com.example.colorscreens.ui.model.ItemColorViewModel
import com.example.colorscreens.ui.model.ItemState
import com.example.colorscreens.ui.theme.ColorScreensTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ItemColorViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorScreensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val uiState by viewModel.stateFlow.collectAsState()
                    MyApp(uiState, viewModel::onEventTrigger)
                }
            }
        }
    }
}

@Composable
fun ColorSelectorItem(itemState: ItemState, onSelected: (ItemState) -> Unit) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clickable {
                onSelected(itemState)
            },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(itemState.itemColor),
        border = BorderStroke(if (itemState.isSelected) 4.dp else 0.dp, Color.Black)

    ) {

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorScreensTheme {
        val viewModel = viewModel<ItemColorViewModel>()
        val uiState by viewModel.stateFlow.collectAsState()
        MyApp(uiState, viewModel::onEventTrigger)


    }
}


@Composable
fun MyApp(uiState: ItemColorUiStat, onEvent: (ItemColorEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(uiState.selectedColor)),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentPadding = PaddingValues(horizontal = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(6.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        text = "Color Picker",
                        color = Color.Black,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }
            itemsIndexed(uiState.list) { index, rowItem ->
                ColorSelectorItem(itemState = rowItem) { colorItem ->
                    //viewModel.onEventTrigger(ItemColorEvent.ChangeColor(colorItem, index))
                    onEvent(ItemColorEvent.ChangeColor(colorItem, index))
                }
            }
        }
    }
}