def mapConfig(properties) {
    properties['CONTACTPOINTS'] = 'node-0-server.cassandra.autoip.dcos.thisdcos.directory'
    properties['PORT'] = '9042'
    properties['KEYSPACE'] = 'billings'
}

return this;
