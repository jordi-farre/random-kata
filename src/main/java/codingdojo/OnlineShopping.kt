package codingdojo

import java.util.ArrayList

/**
 * The online shopping company owns a chain of Stores selling
 * makeup and beauty products.
 *
 *
 * Customers using the online shopping website can choose a Store then
 * can put Items available at that store into their Cart.
 *
 *
 * If no store is selected, then items are shipped from
 * a central warehouse.
 */
class OnlineShopping(private val session: Session) {
    /**
     * This method is called when the user changes the
     * store they are shopping at in the online shopping
     * website.
     *
     */
    fun switchStore(storeToSwitchTo: Store?) {
        val cart = session["CART"] as Cart
        var deliveryInformation = session["DELIVERY_INFO"] as DeliveryInformation
        if (storeToSwitchTo == null) {
            if (cart != null) {
                for (item in cart.getItems()) {
                    if ("EVENT" == item.type) {
                        cart.markAsUnavailable(item)
                    }
                }
            }
            if (deliveryInformation != null) {
                deliveryInformation.type = "SHIPPING"
                deliveryInformation.pickupLocation == null
            }
        } else {
            if (cart != null) {
                val newItems = ArrayList<Item>()
                for (item in cart.getItems()) {
                    if ("EVENT" == item.type) {
                        if (storeToSwitchTo.hasItem(item)) {
                            cart.markAsUnavailable(item)
                            val item = storeToSwitchTo.getItem(item.name)
                            if (item != null) {
                                newItems.add(item)
                            }
                        } else {
                            cart.markAsUnavailable(item)
                        }
                    } else if (!storeToSwitchTo.hasItem(item)) {
                        cart.markAsUnavailable(item)
                    }
                }
                val currentStore = session["STORE"] as Store
                if (deliveryInformation != null && deliveryInformation.type != null && "HOME_DELIVERY" == deliveryInformation.type && deliveryInformation.deliveryAddress != null) {
                    deliveryInformation = homeDelivery(storeToSwitchTo, deliveryInformation, currentStore)
                } else {
                    if (deliveryInformation != null
                        && deliveryInformation.deliveryAddress != null
                    ) {
                        if ((session["LOCATION_SERVICE"] as LocationService).isWithinDeliveryRange(
                                storeToSwitchTo,
                                deliveryInformation.deliveryAddress
                            )
                        ) {
                            deliveryInformation.type = "HOME_DELIVERY"
                            deliveryInformation.pickupLocation = storeToSwitchTo
                        }
                    }
                }
                for (item in newItems) {
                    cart.addItem(item)
                }
            }
        }
        deliveryInformation.weight = cart.weight()
        session.put("STORE", storeToSwitchTo)
        session.saveAll()
    }

    private fun homeDelivery(
        storeToSwitchTo: Store?,
        deliveryInformation: DeliveryInformation,
        currentStore: Store
    ): DeliveryInformation {
        return if (isWithinDeliveryRange(storeToSwitchTo, deliveryInformation)) {
            deliveryInformation.copy(pickupLocation = storeToSwitchTo)
        } else {
            deliveryInformation.copy(type = "PICKUP", pickupLocation = currentStore)
        }
    }

    private fun isWithinDeliveryRange(
        storeToSwitchTo: Store?,
        deliveryInformation: DeliveryInformation
    ): Boolean {
        return (session["LOCATION_SERVICE"] as LocationService).isWithinDeliveryRange(
            storeToSwitchTo,
            deliveryInformation.deliveryAddress
        )
    }

    override fun toString(): String {
        return """
            OnlineShopping{
            session=$session
            }
            """.trimIndent()
    }
}