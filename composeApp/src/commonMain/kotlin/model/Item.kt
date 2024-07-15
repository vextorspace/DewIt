package model

import ids.UUID
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import model.visitors.FindItemByIdAction
import model.visitors.ItemVisitor

@Serializable
data class Item(
    var content: String = "New Item",
    val subItems: MutableList<Item> = mutableListOf(),
    val id: String = UUID.generateUUID(),
    val workflow: MutableList<ItemWorkflow> = mutableListOf()
) {
    constructor(content: String) : this(content, mutableListOf())
    constructor(content: String, subItems: Collection<Item>) : this(content, subItems.toMutableList())

    val workflows: MutableList<ItemWorkflow> = mutableListOf()

    fun add(subItem: Item) {
        if(subItems.contains(subItem))
            return
        subItems.add(subItem)
    }

    fun remove(subItem: Item) {
        subItems.remove(subItem)
    }

    fun toJson(): String {
        return encoder
            .encodeToString(serializer(), this)
    }

    fun findItemById(idToFind: String): Item? {
        val findItemByIdAction = FindItemByIdAction(idToFind)
        ItemVisitor(findItemByIdAction).visit(this)
        return findItemByIdAction.item
    }

    companion object {
        val encoder = Json { ignoreUnknownKeys = true }

        fun fromJson(itemJson: String): Item? {
            return encoder.decodeFromString<Item?>(itemJson)
        }
    }

}