package model

import kotlinx.serialization.Serializable

@Serializable
data class ItemWorkflow(val destination: Item, val actionType: ActionType = ActionType.Copy) {



}
