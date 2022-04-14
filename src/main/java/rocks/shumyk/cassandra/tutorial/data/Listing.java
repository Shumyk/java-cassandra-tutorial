package rocks.shumyk.cassandra.tutorial.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

@Data(staticConstructor = "of")
public class Listing {
    private final String listingId;
    private final Map<String, Object> attributes;

    public Map<String, Object> finalizeAttributes() {
        final var copyAttributes = Maps.newHashMap(attributes);
        copyAttributes.put(AttributeName.LISTINGID.value(), listingId);
        return ImmutableMap.copyOf(copyAttributes);
    }
}
