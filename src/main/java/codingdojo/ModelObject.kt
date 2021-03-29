package codingdojo

interface ModelObject {
    override fun toString(): String
    fun saveToDatabase()
}