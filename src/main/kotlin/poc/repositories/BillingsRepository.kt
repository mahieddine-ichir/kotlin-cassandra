package poc.repositories

import poc.model.TrackingBilling
import org.springframework.data.repository.CrudRepository
import java.util.*

interface BillingsRepository : CrudRepository<TrackingBilling, UUID> {

    fun findByCodeClient(codeClient: String): MutableIterable<TrackingBilling>
    fun findByCodeClientAndRefUser(codeClient: String, refUser: String): MutableIterable<TrackingBilling>

}