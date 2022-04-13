package rocks.shumyk.cassandra.tutorial;

import lombok.extern.slf4j.Slf4j;
import rocks.shumyk.cassandra.tutorial.persistence.Connector;

@Slf4j
public class CreateKeySpaceMain {

    public static final String NEW_KEYSPACE = "cms";
    public static final String CREATE_KEYSPACE_QUERY_FORMAT = "CREATE KEYSPACE %s WITH replication={'class': 'SimpleStrategy', 'replication_factor':3};";

    public static void main(String[] args) {
        log.info("Going to create new keyspace - '{}'", NEW_KEYSPACE);
        final var createKeyspaceQuery = String.format(CREATE_KEYSPACE_QUERY_FORMAT, NEW_KEYSPACE);
        Connector.getSession().execute(createKeyspaceQuery);
        log.info("Keyspace '{}' created", NEW_KEYSPACE);

        log.info("Initiate shutdown of cluster instance");
        Connector.close();
        log.info("Cluster instance shutdown finished");
    }

}
