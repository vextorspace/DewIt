package viewmodel

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import model.Item
import kotlin.test.Test

class ViewItemTest {

    @Test
    fun `ViewItem contains an item`() {
        val viewItem = ViewItem()

        viewItem.item
            .shouldNotBeNull()
            .shouldBeInstanceOf<Item>()
    }

    @Test
    fun `ViewItem remembers parent even when in different branches`() {
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        val grandChild = Item("GrandChild")
        root.add(child1)
        root.add(child2)
        child1.add(grandChild)
        child2.add(grandChild)

        val viewModel = DewItViewModel(root)
        val viewItem: ViewItem = viewModel.item

        val gc1 = viewItem.subItems.first().subItems.first()
        val gc2 = viewItem.subItems.last().subItems.first()

        gc1.item.shouldBe(gc2.item)
        gc1.parent!!.item.shouldBe(child1)
        gc2.parent!!.item.shouldBe(child2)
    }

    @Test
    fun `ViewItem equals if item is equal and subItems are equal`() {
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        val grandChild = Item("GrandChild")
        root.add(child1)
        root.add(child2)
        child1.add(grandChild)
        child2.add(grandChild)

        val viewModel = DewItViewModel(root)
        val viewItem: ViewItem = viewModel.item

        val viewModel2 = DewItViewModel(root)
        val viewItem2: ViewItem = viewModel2.item

        viewItem.shouldBeEqual(viewItem2)
    }

    @Test
    fun `ViewItem not equals if item is different`() {
        val child1 = ViewItem(Item("Child1"))
        val child2 = ViewItem(Item("Child2"))


        child1.shouldNotBe(child2)
    }

    @Test
    fun `ViewItem not equals if one of the children is different`() {
        val viewItem1 = ViewItem(Item("::ITEM 1", id = "1"))
        val viewItem2 = ViewItem(Item("::ITEM 1", id = "1"))
        val child = ViewItem(Item("::CHILD", id = "2"))

        viewItem1.add(child)

        viewItem1.shouldNotBe(viewItem2)
    }

    @Test
    fun `can get all items`() {
        val root = Item("Root")
        val child1 = Item("Child1")
        val child2 = Item("Child2")
        val grandChild = Item("GrandChild")
        root.add(child1)
        root.add(child2)
        child1.add(grandChild)
        child2.add(grandChild)

        val viewModel = DewItViewModel(root)
        val viewItem: ViewItem = viewModel.item

        val allItems = viewItem.allItems()

        allItems.shouldContainAll(root, child1, child2, grandChild)
    }

}