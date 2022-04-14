package rocks.shumyk.cassandra.tutorial.data.dummy;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rocks.shumyk.cassandra.tutorial.data.AttributeName;
import rocks.shumyk.cassandra.tutorial.data.Listing;
import rocks.shumyk.cassandra.tutorial.data.Product;
import rocks.shumyk.cassandra.tutorial.utils.MapBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static rocks.shumyk.cassandra.tutorial.data.AttributeName.*;

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

    public static List<Product> dummyProducts() {
        final var sofa1 = Product.of(
                "SOFA1",
                MapBuilder.start()
                        .put(CATEGORY.value(), "sofa")
                        .put(BRAND.value(), "Fab")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 500)
                        .put(TITLE.value(), "Urban Living Derby")
                        .build()
        );
        final var sofa10 = Product.of(
                "SOFA10",
                MapBuilder.start()
                        .put(CATEGORY.value(), "sofa")
                        .put(BRAND.value(), "Fab")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 700)
                        .put(TITLE.value(), "Urban 5 seater")
                        .put(KEYFEATURES.value(), asList("Good design", "Elegant"))
                        .build()
        );
        final var sofa9 = Product.of(
                "SOFA9",
                MapBuilder.start()
                        .put(CATEGORY.value(), "sofa")
                        .put(BRAND.value(), "Decor")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 500)
                        .put(TITLE.value(), "Urban 4 seater")
                        .build()
        );
        final var sofa5 = Product.of(
                "SOFA5",
                MapBuilder.start()
                        .put(CATEGORY.value(), "sofa")
                        .put(BRAND.value(), "Fab")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 500)
                        .put(TITLE.value(), "Urban Living Sofa 3 Seater")
                        .build()
        );
        final var sofa2 = Product.of(
                "SOFA2",
                MapBuilder.start()
                        .put(CATEGORY.value(), "sofa")
                        .put(BRAND.value(), "Fab")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 700)
                        .put(TITLE.value(), "Urban Decor 2 seater")
                        .build()
        );
        final var top1 = Product.of(
                "TOP1",
                MapBuilder.start()
                        .put(CATEGORY.value(), "top")
                        .put(BRAND.value(), "Shine")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 300)
                        .put(LENGTH.value(), 100)
                        .put(TITLE.value(), "Marble Top")
                        .build()
        );
        final var cha1 = Product.of(
                "CHA1",
                MapBuilder.start()
                        .put(CATEGORY.value(), "chair")
                        .put(BRAND.value(), "Relaxo")
                        .put(BREADTH.value(), 100)
                        .put(HEIGHT.value(), 200)
                        .put(LENGTH.value(), 200)
                        .put(TITLE.value(), "Reclining Chair")
                        .build()
        );

        return asList(sofa1, sofa2, sofa5, sofa9, sofa10, top1, cha1);
    }
}
