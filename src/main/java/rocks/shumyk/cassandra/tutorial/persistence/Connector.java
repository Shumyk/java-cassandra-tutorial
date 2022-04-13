package rocks.shumyk.cassandra.tutorial.persistence;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import lombok.extern.slf4j.Slf4j;

/**
 * This class creates a singleton session object.
 * It's shared by all classes.
 */
@Slf4j
public class Connector {

    private static final String host = "localhost";
    private static final int port = 9042;
    private static final String username = "shumyk";
    private static final String password = "ebec1love";
    private static final String clusterName = "easybuy";
    private static final int MAX_CONNECTIONS = 100;
    private static final int CORE_CONNECTIONS = 25;

    private static Cluster cluster;

    static {
        final PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, MAX_CONNECTIONS);
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, CORE_CONNECTIONS);

        Connector.cluster = Cluster.builder()
                .addContactPoint(host)
                .withPort(port)
                .withCredentials(username, password)
                .withPoolingOptions(poolingOptions)
                .withClusterName(clusterName)
                .build();
        log.info("Connected to cluster: {}", cluster.getMetadata().getClusterName());
    }

    /**
     * Returns session object
     */
    public static Session getSession() {
        return cluster.connect();
    }

    /**
     * Closes connection
     */
    public static void close() {
        if (!cluster.isClosed()) {
            cluster.close();
        }
    }
}
