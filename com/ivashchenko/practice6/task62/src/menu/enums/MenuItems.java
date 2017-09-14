package menu.enums;

/**
 * This enum represents elements of menu.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public enum MenuItems{
    PRINT_CARS("Print cars"),
    MODEL_AND_OLDER_THAN("Query cars of model and older than"),
    OF_YEAR_EXPENSIVE_THAN("Query cars of year and expensive than"),
    SORT_BY_MANUFACTURER("Sort by manufacturer"),
    SORT_BY_PRICE("Sort by price"),
    SORT_BY_PRODUCTION_YEAR("Sort by production year"),
    WRITE_TO_FILE("Write to file"),
    READ_FROM_FILE("Read from file"),
    EXIT("Exit");

    private String description;

    private MenuItems(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
