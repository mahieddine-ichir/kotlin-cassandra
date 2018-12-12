package poc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories
import poc.model.BillDetail
import poc.model.TrackingBilling
import poc.repositories.BillingsRepository
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableCassandraRepositories
open class Application : AbstractCassandraConfiguration() {

    @Autowired
    lateinit var env: Environment

    override fun getContactPoints(): String {
        return env["contactPoints"]
    }

    override fun getKeyspaceName(): String {
        return env["keySpace"]
    }

    override fun getSchemaAction(): SchemaAction {
        return env.getProperty("schemaAction", "none").let { SchemaAction.valueOf(it.toUpperCase()) }
    }

    @Autowired
    lateinit var repo: BillingsRepository

    @PostConstruct
    fun init() {
        (0..100).forEach{
            var codeClient = "codeClient_" + (it % 5)
            var refUser = "user_" + (it % 10)
            var refMaileva = "20181211${it}"
            repo.save(TrackingBilling(UUID.randomUUID(), "campaign1", codeClient, listOf(BillDetail("designation", 2, 1.0, "product", 2.3)), refUser, refMaileva, "refClient${it}", "product${it}"))
        }
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
    //runApplication<Application>(*args)
}


