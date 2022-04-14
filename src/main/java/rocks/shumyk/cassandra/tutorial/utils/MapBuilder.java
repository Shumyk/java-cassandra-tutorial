package rocks.shumyk.cassandra.tutorial.utils;

import com.google.common.collect.Maps;
import rocks.shumyk.cassandra.tutorial.data.AttributeName;

import java.util.Map;

public class MapBuilder {

    private final Map<String, Object> map;

    private MapBuilder() {
        this.map = Maps.newHashMap();
    }

    public static MapBuilder start() {
        return new MapBuilder();
    }

    public MapBuilder put(final String key, final Object value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder put(final AttributeName attribute, final Object value) {
        map.put(attribute.value(), value);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }
}
