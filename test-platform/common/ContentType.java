package common;

public enum ContentType {
    JSON("application/json"),
    XML("application/xml");

    private final String value;
    ContentType(String v) { value = v; }
    public String value() { return value; }
}