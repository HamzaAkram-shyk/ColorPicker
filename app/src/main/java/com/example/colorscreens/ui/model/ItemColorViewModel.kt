package com.example.colorscreens.ui.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.colorscreens.Utill
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ItemColorUiStat(
    var list: List<ItemState> = emptyList(), var selectedColor: Long = Utill.whiteColor
)


class ItemColorViewModel : ViewModel() {
    var _dataList = mutableStateListOf<ItemState>()
    var dataList: List<ItemState> = _dataList

    private val uiState = mutableStateOf(ItemColorUiStat())
    val state: State<ItemColorUiStat> = uiState

    private val uiStateFlow = MutableStateFlow(ItemColorUiStat())
    val stateFlow = uiStateFlow.asStateFlow()

    init {
        getItems(20)
    }

    private fun getItems(count: Int) {
        uiStateFlow.update {
            it.copy(list = Utill.generateItems(count))
        }
    }

    fun changeColor(item: ItemState, position: Int) {
        uiState.value = state.value.copy(
            list = state.value.list.mapIndexed { index, iitem ->
                if (position == index && item == iitem) item.copy(
                    isSelected = !item.isSelected
                ) else iitem.copy(isSelected = false)
            },
            selectedColor = if (item.isSelected) Utill.whiteColor else item.itemColor
        )
    }

    fun changeColorState(item: ItemState, position: Int) {
        uiStateFlow.update {
            it.copy(
                list = it.list.mapIndexed { index, itemState ->
                    if (index == position) itemState.copy(isSelected = !itemState.isSelected) else itemState.copy(
                        isSelected = false
                    )
                },
                selectedColor = if (item.isSelected) Utill.whiteColor else item.itemColor
            )
        }
    }

}