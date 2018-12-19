package poc.model

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("bill_details")
data class BillDetail(
    val number: Int,
    val price: Double,
    val unitPrice: Double
)