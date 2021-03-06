package rocks.shumyk.cassandra.tutorial.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public record Listing(String listingId, Map<String, Object> attributes) implements AttributeBasedData {

    public static Listing of(String listingId, Map<String, Object> attributes) {
        return new Listing(listingId, attributes);
    }

    public Map<String, Object> finalizeAttributes() {
        final var copyAttributes = Maps.newHashMap(attributes);
        copyAttributes.put(AttributeName.LISTINGID.value(), listingId);
        return ImmutableMap.copyOf(copyAttributes);
    }
}
