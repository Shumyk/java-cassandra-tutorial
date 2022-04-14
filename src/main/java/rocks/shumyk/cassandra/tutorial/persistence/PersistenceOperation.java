package rocks.shumyk.cassandra.tutorial.persistence;

import com.datastax.driver.core.ThreadLocalMonotonicTimestampGenerator;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(staticName = "builder")
public class PersistenceOperation {

    private final String keyspace;
    private final String columnFamily;

    public void insert(final Map<String, Object> values) {
        final var insertStatement = QueryBuilder.insertInto(keyspace, columnFamily);
        values.forEach(insertStatement::value);
        insertStatement.setDefaultTimestamp(new ThreadLocalMonotonicTimestampGenerator().next());

        Connector.getSession().execute(insertStatement);
    }

    public void delete(final String columnDeleteBy, final List<String> ids) {
        final var deleteStatement = QueryBuilder.delete()
                .from(keyspace, columnFamily)
                .where(QueryBuilder.in(columnDeleteBy, ids));

        Connector.getSession().execute(deleteStatement);
    }
}
