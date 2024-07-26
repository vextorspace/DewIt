package viewmodel

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
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
    fun `ViewModel creates parallel view item`() {
        val viewModel = GtdModel.createModel()

        val viewItem: ViewItem = viewModel.viewRoot()

        viewItem.subItems.size.shouldBeEqual(viewModel.item.subItems.size)
        viewItem.subItems
            .map { it.item }
            .shouldContainAll(viewModel.item.subItems)
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
        val viewItem: ViewItem = viewModel.viewRoot()

        val gc1 = viewItem.subItems.first().subItems.first()
        val gc2 = viewItem.subItems.last().subItems.first()

        gc1.item.shouldBe(gc2.item)
        gc1.parent!!.item.shouldBe(child1)
        gc2.parent!!.item.shouldBe(child2)
    }


}