package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.util.HashMap

/**
 * Allows the OnlineShopping to access data classes
 * and store them in the database
 */
class Session {
    private val session: MutableMap<String, ModelObject?>
    operator fun get(key: String): ModelObject? {
        return session[key]
    }

    fun put(key: String, value: ModelObject?) {
        session[key] = value
    }

    fun saveAll() {
        for (key in session.keys) {
            val entity = session[key]
            entity?.saveToDatabase()
        }
    }

    override fun toString(): String {
        val sessionContents = StringBuffer("\n")
        for (key in session.keys) {
            sessionContents.append(key)
            sessionContents.append("=")
            val modelObject = session[key]
            sessionContents.append(modelObject)
            sessionContents.append("\n")
        }
        return "Session{" +
                sessionContents +
                "}"
    }

    init {
        session = HashMap()
        session["CART"] = Cart()
        session["LOCATION_SERVICE"] = LocationService()
    }
}