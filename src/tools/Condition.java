package tools;

public class Condition {

    private String attributeName;
    private Comparator comparator;
    private String value;

    public Condition(String attributeName, Comparator comparator, String value) {
        this.attributeName = attributeName;
        this.comparator = comparator;
        this.value = value;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getComparator() {
        switch (comparator) {
            case equal:
                return "=";
            case notEqual:
                return "!=";
            case less:
                return "<";
            case more:
                return ">";
            case lessEqual:
                return "<=";
            case moreEqual:
                return ">=";
            default:
                return null;
        }
    }

    public String getValue() {
        return value;
    }

}
