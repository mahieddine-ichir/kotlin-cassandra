package poc.model

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*
@Table("tracking_billing")
data class TrackingBilling(

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    val codeClient: String,

    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    val refUser: String,

    @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    val dateRealisation: String, // au format yyyyMMdd

    val refClient: String,

    @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    val refMaileva: String,

    val campaignName: String,

    @PrimaryKeyColumn(ordinal = 4, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    val designation: String,

    @CassandraType(type=DataType.Name.LIST, userTypeName = "bill_details")
    val details: List<BillDetail>

) {
    @Column
    val id: UUID = UUID.randomUUID()
}