package model

import ids.UUID

data class ItemList(val items: List<Item> = listOf(), val label: String = "", val id: String = UUID.generateUUID()) {



}