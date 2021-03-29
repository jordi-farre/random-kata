package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.lang.UnsupportedOperationException

/**
 * This class contains the information about how the customer would like to
 * have the contents of their shopping cart delivered to them.
 */
data class DeliveryInformation(
    var type: String, var pickupLocation: Store?,
    var weight: Long
) : ModelObject {

    var deliveryAddress: String? = null

    fun setTotalWeight(weight: Long) {
        this.weight = weight
    }

    override fun toString(): String {
        return """
            DeliveryInformation{
            type='$type'
            deliveryAddress='$deliveryAddress'
            pickupLocation=$pickupLocation
            weight=$weight
            }
            """.trimIndent()
    }

    override fun saveToDatabase() {
        throw UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test")
    }
}