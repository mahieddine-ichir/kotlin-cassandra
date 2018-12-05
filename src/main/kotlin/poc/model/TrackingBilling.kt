package poc.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("tracking_billing")
data class TrackingBilling(

    @PrimaryKey
    val id: UUID,
    val campaignName: String,
    val codeClient: String,
    val details: List<BillDetail>,
    val refUser: String,
    val refMaileva: String,
    val refClient: String,
    val product: String
)