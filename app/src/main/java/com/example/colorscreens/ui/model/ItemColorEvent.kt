package com.example.colorscreens.ui.model

sealed class ItemColorEvent {
    class ChangeColor(val itemState: ItemState, val itemPosition: Int) : ItemColorEvent()
    object GetColors : ItemColorEvent()
}