package project.model;

public abstract class AbstractEntity<K> {
    public abstract void setId(K id);
    public abstract K getId();
}
