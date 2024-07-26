package viewmodel

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
}