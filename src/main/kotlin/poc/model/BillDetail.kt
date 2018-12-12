package poc.model

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("bill_details")
data class BillDetail(
    val designation: String,
    val number: Int,
    val price: Double,
    val product: String,
    val unitPrice: Double
)