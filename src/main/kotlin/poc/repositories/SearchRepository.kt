package poc.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.core.cql.CqlTemplate
import org.springframework.data.cassandra.core.query.Criteria.where
import org.springframework.data.cassandra.core.query.Query.query
import org.springframework.stereotype.Service
import poc.model.TrackingBilling

@Service
class SearchRepository {

    @Autowired
    lateinit var cqlTemplate: CqlTemplate

    @Autowired
    lateinit var cassandraTemplate: CassandraTemplate

    fun searchByRefMaileva(codeClient: String, refUser: String, dateRealisation: String, refMaileva: String): MutableList<TrackingBilling> {

        return cassandraTemplate.select(
            query(where("codeClient").`is`(codeClient))
                .and(where("refUser").`is`(refUser))
                .and(where("dateRealisation").`is`(dateRealisation))
                .and(where("refMaileva").like(refMaileva.replace("*", "%")))
                .withAllowFiltering(), TrackingBilling::class.java)
/*
        return cqlTemplate
            .queryForList("SELECT * FROM Tracking_Billing WHERE codeClient = '$codeClient' AND refUser = '$refUser'" +
                    " AND dateRealisation = '$dateRealisation' AND refMaileva LIKE '$refMaileva' Allow filtering", TrackingBilling::class.java)
*/
    }

    fun searchAny(q: String): MutableList<TrackingBilling> {

        var criterias = q.split("&")
            .map {
                var split = it.split("=")
                var k = split[0]
                var v = split[1]
                println("$k, $v")
                    if (v.contains("*")) {
                        where(k).like(v.replace("*", "%"))
                    } else {
                        where(k).`is`(v)
                    }
            }
        return cassandraTemplate.select(query(criterias).withAllowFiltering(), TrackingBilling::class.java)
    }

}