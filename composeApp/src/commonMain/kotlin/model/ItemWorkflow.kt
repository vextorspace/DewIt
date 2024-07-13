package model

import kotlinx.serialization.Serializable

@Serializable
data class ItemWorkflow(val item: Item, val destination: Item, val actionType: ActionType = ActionType.Copy) {
    fun execute() {
        when(actionType) {
            ActionType.Copy -> {
                if(destination.equals(item))
                    return
                destination.add(item)
            }
        }
    }
}
