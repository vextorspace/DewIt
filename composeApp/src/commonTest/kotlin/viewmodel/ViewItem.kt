package viewmodel

import kotlinx.serialization.Serializable
import model.Item

@Serializable
data class ViewItem(val item: Item = Item()) {

}
