package poc

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

@SpringBootApplication
@EnableCassandraRepositories
open class Application {

    @Value("\${cassandra.contactpoints}")
    lateinit var contactPoints: String

    @Value("\${cassandra.keyspace}")
    lateinit var keyspace: String

    @Value("\${cassandra.schema_action:none}")
    lateinit var schemaAction: String

    @Bean
    open fun session() = Cluster.builder().addContactPoint(contactPoints).build()
        .let { cluster -> cluster.connect(keyspace) }

    @Bean
    open fun schemaAction() = SchemaAction.valueOf(schemaAction.toUpperCase())
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

