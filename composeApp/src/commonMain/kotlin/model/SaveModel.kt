package model

import viewmodel.DewItViewModel

class SaveModel(viewModel: DewItViewModel) {
    val items = viewModel.allItems()
    val model = makeModel(viewModel)

    private fun makeModel(viewModel: DewItViewModel): SaveItem {
        return SaveItem.from(viewModel.item.item)
    }
}
