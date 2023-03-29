package com.example.colorscreens

import com.example.colorscreens.ui.model.ItemState
import kotlin.random.Random

object Utill {

    val whiteColor = 0xFFFFFFFF
    private val colors = listOf(
        0xFFFFC107, // Yellow
        0xFF2196F3, // Blue
        0xFF4CAF50, // Green
        0xFFE91E63, // Pink
        0xFF673AB7, // Purple
        0xFF9C27B0, // Deep Purple
        0xFF3F51B5, // Indigo
        0xFF00BCD4, // Cyan
        0xFF607D8B, // Blue Gray
        0xFF795548, // Brown
        0xFF9E9E9E, // Gray
    )

    private val titles = listOf(
        "Lorem Ipsum",
        "Dolor Sit",
        "Amet Consectetur",
        "Adipiscing Elit",
        "Sed Do Eiusmod",
        "Tempor Incididunt",
        "Ut Labore Et",
        "Dolore Magna Aliqua",
        "Ut Enim Ad Minim",
        "Veniam, Quis Nostrud",
    )

    private val descriptions = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    )

    fun generateItems(count: Int): List<ItemState> {
        val items = mutableListOf<ItemState>()
        repeat(count) { it ->
            val colorInt = colors[Random.nextInt(colors.size)]
            val title = titles[Random.nextInt(titles.size)]
            val description = descriptions[Random.nextInt(descriptions.size)]
            val item = ItemState(itemColor = colorInt, false)
            items.add(item)
        }
        return items
    }
}