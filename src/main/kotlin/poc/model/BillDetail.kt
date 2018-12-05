package poc.model

data class BillDetail(
    val designation: String,
    val number: Int,
    val price: Double,
    val product: String,
    val unitPrice: Double
)