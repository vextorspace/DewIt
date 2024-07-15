package model

import kotlinx.serialization.Serializable
import viewmodel.DewItViewModel

@Serializable
data class ItemWorkflow(
    val source: String,
    val destination: String,
    val actionType: ActionType = ActionType.COPY
) {
    fun execute(item: Item, model: DewItViewModel) {
        when(actionType) {
            ActionType.COPY -> {
                if(destination == item.id)
                    return
                model.findItemById(destination)?.add(item)
            }
            ActionType.MOVE -> {
                if(destination == item.id)
                    return
                if(destination == source)
                    return
                model.findItemById(destination)?.add(item)
                model.findItemById(source)?.remove(item)
            }
        }
    }
}
