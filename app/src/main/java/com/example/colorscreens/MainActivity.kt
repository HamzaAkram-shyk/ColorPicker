package com.example.colorscreens

import android.annotation.SuppressLint
import android.os.Bundle
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorscreens.ui.model.ItemColorEvent
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
                    //val viewModel = viewModel<ItemColorViewModel>()
                    MyApp(viewModel = viewModel)
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
        MyApp(viewModel = viewModel)

    }
}


@Composable
fun MyApp(viewModel: ItemColorViewModel) {
    val flowUiState by viewModel.stateFlow.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(flowUiState.selectedColor)),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(flowUiState.list) { index, rowItem ->
                ColorSelectorItem(itemState = rowItem) { colorItem ->
                    viewModel.onEventTrigger(ItemColorEvent.ChangeColor(colorItem, index))
                }
            }
        }
    }
}