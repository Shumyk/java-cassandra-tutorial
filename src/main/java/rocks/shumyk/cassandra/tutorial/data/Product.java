package rocks.shumyk.cassandra.tutorial.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public record Product(String productId, Map<String, Object> attributes) implements AttributeBasedData {

    public static Product of(final String productId, final Map<String, Object> attributes) {
        return new Product(productId, attributes);
    }

    public Map<String, Object> finalizeAttributes() {
        final var copyAttributes = Maps.newHashMap(attributes);
        copyAttributes.put(AttributeName.PRODUCTID.value(), productId);
        return ImmutableMap.copyOf(copyAttributes);
    }
}
