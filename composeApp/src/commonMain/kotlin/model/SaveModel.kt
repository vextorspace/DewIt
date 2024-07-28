package model

import viewmodel.DewItViewModel

class SaveModel(val viewModel: DewItViewModel) {
    fun items(): List<Item> {
        return viewModel.allItems()
    }

    fun model(): SaveItem {
        return makeModel(viewModel)
    }

    private fun makeModel(viewModel: DewItViewModel): SaveItem {
        return SaveItem.from(viewModel.item)
    }
}
