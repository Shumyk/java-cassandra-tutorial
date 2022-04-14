package rocks.shumyk.cassandra.tutorial.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AttributeName {
    SELLERID("sellerid"),
    PRODUCTID("productid"),
    LISTINGID("listingid"),
    SKUID("skuid"),
    MRP("mrp"),
    SSP("ssp"),
    SLA("sla"),
    TITLE("title"),
    BRAND("brand"),
    KEYFEATURES("keyfeatures"),
    LENGTH("length"),
    BREADTH("breadth"),
    HEIGHT("height"),
    PUBLISHER("publisher"),
    CATEGORY("category"),
    STOCK("stock");

    private final String value;

    public String value() {
        return value;
    }
}
