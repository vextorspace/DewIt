package viewmodel

import androidx.compose.runtime.MutableState
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import model.Item
import kotlin.test.Test

class DewItViewModelTest {

    @Test
    fun `DewItViewModel provides a mutable state of mutable list`() {
        // Given
        val viewModel = DewItViewModel(item = Item())

        // When
        val itemsState = viewModel.itemsState

        // Then
        itemsState.shouldBeInstanceOf<MutableState<MutableList<Item>>>()
    }

    @Test
    fun `When DewItViewModel is created the mutable list is empty`() {
        // Given
        val viewModel = DewItViewModel(item = Item())

        // When
        val items = viewModel.itemsState.value

        // Then
        items.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel constructed with list gives those items`() {
        // Given
        val item1 = Item("Item 1")
        val item2 = Item("Item 2")
        val items = listOf(item1, item2)

        // When
        val viewModel = DewItViewModel(items)

        // Then
        viewModel.itemsState.value
            .shouldContainExactly(ViewItem(item1, viewModel.item), ViewItem(item2, viewModel.item))
    }

    @Test
    fun `DewItViewModel reconstituted from an empty json string gives empty list`() {
        // Given
        val viewModelJson = "[]"

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        viewModel.itemsState.value.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel reconstituted with one item in list`() {
        // Given
        val viewModelJson = """
            {
            "content": "Root",
            "subItems":[
                {
                    "content":"Inbox"
                    ,"subItems":[]
                }
            ],
            "id":"::ROOT_UUID::"
            }
            """.trimIndent()

        // When
        val viewModel = DewItViewModel.fromJson(viewModelJson)

        // Then
        val items = viewModel.itemsState.value
        items.shouldHaveSize(1)
        items[0].content.shouldBe("Inbox")
        items[0].subItems.shouldBeEmpty()
    }

    @Test
    fun `DewItViewModel reconstituted with two items in list`() {
        val viewModelJson = makeJsonOfDewItModelWithTwoItems()

        val viewModel = DewItViewModel.fromJson(viewModelJson)

        val items = viewModel.itemsState.value

        items.shouldHaveSize(2)

        val expectedItem1 = Item(
            "Inbox",
            mutableListOf(
                Item("Review How You Use This App", id = "::CHILD_UUID::")
            ),
            "::PARENT_UUID::"
        )
        val expectedItem2 = Item("Todo", mutableListOf(), "::OTHER_UUID::")

        items.shouldContainExactly(
            ViewItem(expectedItem1, viewModel.item),
            ViewItem(expectedItem2, viewModel.item)
        )
    }

    @Test
    fun `DewItViewModel stores items in json`() {
        val content1 = "Item 1"
        val uuid1 = "::UUID 1::"
        val content2 = "Item 2"
        val uuid2 = "::UUID 2::"
        val subContent = "Sub Item"
        val subId = "::SUB_UUID::"
        val rootId = "::ROOT_UUID::"
        val viewModel = makeDewItModel(subContent, subId, content1, uuid1, content2, uuid2, rootId)


        // When
        val viewModelJson = viewModel.toJson()

        val expectedJson = expected(content1, uuid1, content2, subContent, subId, uuid2, rootId)

        viewModelJson.shouldEqualJson(expectedJson)
    }

    private fun makeJsonOfDewItModelWithTwoItems(): String {
        val viewModelJson = """
            {
                "content":"Root",
                "subItems":[
                    {
                        "content":"Inbox"
                        ,"subItems":[
                            {
                                "content":"Review How You Use This App"
                                ,"subItems":[]
                                ,"id":"::CHILD_UUID::"
                            }
                            
                        ]
                        ,"id":"::PARENT_UUID::"
                    },
                    {
                        "content":"Todo"
                        ,"subItems":[]
                        ,"id":"::OTHER_UUID::"
                    }
                ]
                ,"id":"::ROOT_UUID::"
            }    
            """.trimIndent()
        return viewModelJson
    }


    private fun makeDewItModel(
        subContent: String,
        subId: String,
        content1: String,
        uuid1: String,
        content2: String,
        uuid2: String,
        rootId: String
    ): DewItViewModel {
        val subItem = Item(subContent, id = subId)
        val item1 = Item(content1, id = uuid1)
        val item2 = Item(
            content = content2,
            subItems = mutableListOf(subItem),
            id = uuid2
        )
        val items = listOf(item1, item2)
        return DewItViewModel(Item("Root", items.toMutableList(), rootId))
    }

    private fun expected(
        content1: String,
        uuid1: String,
        content2: String,
        subContent: String,
        subId: String,
        uuid2: String,
        rootId: String
    ) = """
        {
                "content":"Root",
                "subItems":[
                    {
                        "content":"$content1",
                        "id":"$uuid1"
                    },
                    {
                        "content":"$content2",
                        "subItems":[
                            {
                                "content":"$subContent",
                                "id":"$subId"
                            }
                        ],
                        "id":"$uuid2"
                    }
                ],
                "id":"$rootId"
        }
        """.trimIndent()
}