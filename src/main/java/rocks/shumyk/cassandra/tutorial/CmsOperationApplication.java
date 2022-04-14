package rocks.shumyk.cassandra.tutorial;

import lombok.extern.slf4j.Slf4j;
import rocks.shumyk.cassandra.tutorial.data.Listing;
import rocks.shumyk.cassandra.tutorial.data.dummy.DummyData;
import rocks.shumyk.cassandra.tutorial.persistence.Connector;
import rocks.shumyk.cassandra.tutorial.persistence.PersistenceOperation;

import java.util.List;
import java.util.Optional;

@Slf4j
public class CmsOperationApplication {

    public static final String KEYSPACE_CMS = "cms";
    public static final String COLUMN_FAMILY_LISTINGS = "listings";

    public static final String USE_KEYSPACE = "USE %s";
    public static final String CREATE_KEYSPACE_QUERY_FORMAT = "CREATE KEYSPACE %s WITH replication={'class': 'SimpleStrategy', 'replication_factor':3};";

    public static void main(String[] args) {
//        createKeyspace(KEYSPACE_CMS);
//        createColumnFamily(KEYSPACE_CMS, COLUMN_FAMILY_LISTINGS);
//        checkColumnFamilyCreated(KEYSPACE_CMS, COLUMN_FAMILY_LISTINGS);
        populateColumnFamilyWithData(KEYSPACE_CMS, COLUMN_FAMILY_LISTINGS, DummyData.dummyListing());

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

    private static void checkColumnFamilyCreated(final String keyspace, final String columnFamilyName) {
        final var keyspaceMetadata = Connector.getCluster().getMetadata().getKeyspace(keyspace);
        Optional.of(keyspaceMetadata)
                .map(k -> k.getTable(columnFamilyName))
                .ifPresentOrElse(
                        value -> log.info("column family '{}' exists in keyspace '{}'", columnFamilyName, keyspace),
                        () -> log.info("column family '{}' doesn't exist.", columnFamilyName)
                );
    }

    // todo: make it more abstract (avoid Listing to more abstract data type)
    private static void populateColumnFamilyWithData(final String keyspace,
                                                     final String columnFamily,
                                                     final List<Listing> data) {
        data.stream()
                .map(Listing::finalizeAttributes)
                .forEach(l -> PersistenceOperation
                                .builder(keyspace, columnFamily)
                                .insert(l));
        log.info("Populated column family '{}.{}' with dummy data", keyspace, columnFamily);
    }
}
