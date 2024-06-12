package model

import ids.UUID

data class Item(var content: String, val id: String = UUID.generateUUID()) {
}