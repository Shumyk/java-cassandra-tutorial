package rocks.shumyk.cassandra.tutorial.data.dummy;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rocks.shumyk.cassandra.tutorial.data.AttributeName;
import rocks.shumyk.cassandra.tutorial.data.Listing;
import rocks.shumyk.cassandra.tutorial.utils.MapBuilder;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyData {

    public static List<Listing> dummyListing() {
        final var listingFabSofa5 = Listing.of(
                "LISTINGFABSOFA5",
                MapBuilder.start()
                        .put(AttributeName.SELLERID, "Fab")
                        .put(AttributeName.SKUID, "SKU2")
                        .put(AttributeName.MRP, 5000)
                        .put(AttributeName.SSP, 4000)
                        .put(AttributeName.SLA, 2)
                        .put(AttributeName.STOCK, 2)
                        .put(AttributeName.PRODUCTID, "SOFA5")
                        .put(AttributeName.TITLE, "Urban Loving Sofa 3 Seater")
                        .build()
        );
        final var listingGulTop1 = Listing.of(
                "LISTINGULTOP1",
                MapBuilder.start()
                        .put(AttributeName.SELLERID, "UrbanLadder")
                        .put(AttributeName.SKUID, "SKU1")
                        .put(AttributeName.MRP, 25000)
                        .put(AttributeName.SSP, 20000)
                        .put(AttributeName.SLA, 2)
                        .put(AttributeName.STOCK, 10)
                        .put(AttributeName.PRODUCTID, "TOP1")
                        .put(AttributeName.TITLE, "Marble Top")
                        .build()
        );
        final var listingGulCha1 = Listing.of(
                "LISTINGULCHA1",
                MapBuilder.start()
                        .put(AttributeName.SELLERID, "UrbanLadder")
                        .put(AttributeName.SKUID, "SKU2")
                        .put(AttributeName.MRP, 23000)
                        .put(AttributeName.SSP, 21000)
                        .put(AttributeName.SLA, 2)
                        .put(AttributeName.STOCK, 15)
                        .put(AttributeName.PRODUCTID, "CHA1")
                        .put(AttributeName.TITLE, "Reclining Chair")
                        .build()
        );
        return Lists.newArrayList(listingFabSofa5, listingGulTop1, listingGulCha1);
    }
}
