package com.example.colorscreens.ui.model
import androidx.lifecycle.ViewModel
import com.example.colorscreens.Utill
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ItemColorUiStat(
    var list: List<ItemState> = emptyList(), var selectedColor: Long = Utill.whiteColor
)


class ItemColorViewModel : ViewModel() {
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

//    fun changeColor(item: ItemState, position: Int) {
//        uiState.value = state.value.copy(
//            list = state.value.list.mapIndexed { index, iitem ->
//                if (position == index && item == iitem) item.copy(
//                    isSelected = !item.isSelected
//                ) else iitem.copy(isSelected = false)
//            },
//            selectedColor = if (item.isSelected) Utill.whiteColor else item.itemColor
//        )
//    }

    private fun changeColorState(item: ItemState, position: Int) {
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


    fun onEventTrigger(event: ItemColorEvent) {
        when (event) {
            is ItemColorEvent.ChangeColor -> {
                changeColorState(event.itemState, event.itemPosition)
            }
            ItemColorEvent.GetColors -> {
                getItems(20)
            }
        }
    }

}