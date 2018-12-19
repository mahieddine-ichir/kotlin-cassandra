package poc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.core.cql.CqlTemplate
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

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

    @Bean
    open fun cqlTemplate() = CqlTemplate(sessionFactory())
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


