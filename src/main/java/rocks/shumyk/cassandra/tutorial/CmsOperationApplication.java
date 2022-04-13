package rocks.shumyk.cassandra.tutorial;

import lombok.extern.slf4j.Slf4j;
import rocks.shumyk.cassandra.tutorial.persistence.Connector;

@Slf4j
public class CmsOperationApplication {

    public static final String KEYSPACE_CMS = "cms";
    public static final String COLUMN_FAMILY_LISTINGS = "listings";

    public static final String USE_KEYSPACE = "USE %s";
    public static final String CREATE_KEYSPACE_QUERY_FORMAT = "CREATE KEYSPACE %s WITH replication={'class': 'SimpleStrategy', 'replication_factor':3};";

    public static void main(String[] args) {
//        createKeyspace(KEYSPACE_CMS);
        createColumnFamily(KEYSPACE_CMS, COLUMN_FAMILY_LISTINGS);

        Connector.close();
    }

    private static void createKeyspace(final String keyspace) {
        log.info("Going to create new keyspace - '{}'", keyspace);
        final var createKeyspaceQuery = String.format(CREATE_KEYSPACE_QUERY_FORMAT, keyspace);
        Connector.getSession().execute(createKeyspaceQuery);
        log.info("Keyspace '{}' created", keyspace);
    }

    private static void createColumnFamily(final String keyspace, final String columnFamily) {
        final var session = Connector.getSession();
        log.info("logged keyspace: {}", session.getLoggedKeyspace());

        session.execute(String.format(USE_KEYSPACE, keyspace));
        log.info("logged keyspace: {}", session.getLoggedKeyspace());

        final var createColumnFamilyQuery = "CREATE COLUMNFAMILY " + columnFamily + "(" +
                "listingId varchar," +
                "sellerId varchar," +
                "skuId varchar," +
                "productId varchar," +
                "mrp int," +
                "ssp int," +
                "sla int," +
                "stock int," +
                "title text," +
                "PRIMARY KEY (productId, listingId));";
        session.execute(createColumnFamilyQuery);
        log.info("created column family '{}'", columnFamily);
    }
}
