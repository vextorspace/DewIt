package model

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainInOrder
import kotlin.test.Test

class SubItemOrderMattersTest {

    @Test
    fun `multiple items can be added to an item`() {
        val item = Item("item")
        val subItem1 = Item("subItem1")
        val subItem2 = Item("subItem2")
        item.add(subItem1)
        item.add(subItem2)
        item.subItems.shouldContainInOrder(subItem1, subItem2)
    }

    @Test
    fun `items can be added removed from an item`() {
        val item = Item("item")
        val subItem1 = Item("subItem1")
        val subItem2 = Item("subItem2")
        val subItem3 = Item("subItem3")
        item.add(subItem3)
        item.add(subItem1)
        item.add(subItem2)
        item.remove(subItem1)
        item.subItems.shouldContainInOrder(subItem3, subItem2)
    }

    @Test
    fun `same item cannot be added twice to an item`() {
        val item = Item("item")
        val subItem = Item("subItem")
        item.add(subItem)
        item.add(subItem)
        item.subItems.shouldContainExactly(subItem)
    }
}