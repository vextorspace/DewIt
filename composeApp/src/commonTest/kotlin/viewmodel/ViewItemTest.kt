package viewmodel

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
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




}