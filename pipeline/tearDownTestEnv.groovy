def tearDownTestEnv() {
    sh "cqlsh -e \"CREATE KEYSPACE IF NOT EXISTS ${env.MONGODB_DATABASE} WITH replication = {'class':'SimpleStrategy', 'replication_factor':2};\" node-0-server.cassandra.autoip.dcos.thisdcos.directory"
}

return this;
