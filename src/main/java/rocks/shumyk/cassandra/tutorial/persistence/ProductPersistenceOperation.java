package rocks.shumyk.cassandra.tutorial.persistence;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.collect.Maps;
import rocks.shumyk.cassandra.tutorial.data.AttributeName;
import rocks.shumyk.cassandra.tutorial.data.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public record ProductPersistenceOperation(String keyspace, String columnFamily) {

    public List<Product> getProductsForCategoriesAndBrand(final List<String> categories, final String brand) {
        final var selectStatement = QueryBuilder.select().all()
                .from(keyspace, columnFamily)
                .where(QueryBuilder.in(AttributeName.CATEGORY.value(), categories))
                    .and(QueryBuilder.eq(AttributeName.BRAND.value(), brand));

        final var session = Connector.getSession();
        final var resultSet = session.execute(selectStatement);
        session.close();

        return resultSet.all()
                .stream()
                .map(this::covertRowToProduct)
                .collect(Collectors.toList());
    }

    // todo: use hibernate to avoid such mapping functions
    private Product covertRowToProduct(final Row row) {
        final AtomicReference<String> productId = new AtomicReference<>();
        final Map<String, Object> attributes = Maps.newHashMap();

        Optional.of(row)
                .map(Row::getColumnDefinitions)
                .map(ColumnDefinitions::asList)
                .stream()
                .flatMap(Collection::stream)
                .map(d -> Maps.immutableEntry(d.getName(), row.getObject(d.getName())))
                .forEach(e -> {
                    if (AttributeName.PRODUCTID.value().equals(e.getKey()))
                        productId.set(e.getValue().toString());
                    else
                        attributes.put(e.getKey(), e.getValue());
                });

        return Product.of(productId.get(), attributes);
    }
}
