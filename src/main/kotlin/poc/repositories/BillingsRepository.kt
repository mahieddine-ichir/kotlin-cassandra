package poc.repositories

import org.springframework.data.cassandra.repository.AllowFiltering
import org.springframework.data.repository.CrudRepository
import poc.model.TrackingBilling
import java.util.*

interface BillingsRepository : CrudRepository<TrackingBilling, UUID> {

    fun findByCodeClient(codeClient: String): MutableIterable<TrackingBilling>
    fun findByCodeClientAndRefUser(codeClient: String, refUser: String): MutableIterable<TrackingBilling>

    @AllowFiltering
    fun findByCodeClientAndRefUserAndRefMaileva(codeClient: String, refUser: String, refMaileva: String): MutableIterable<TrackingBilling>

//    @Query("SELECT * FROM Tracking_Billing WHERE codeClient = ? AND refUser = ? AND dateRealisation = ? AND refMaileva LIKE ? Allow filtering")
//    fun searchByRefMaileva(codeClient: String, refUser: String, dateRealisation: String, refMaileva: String)

}