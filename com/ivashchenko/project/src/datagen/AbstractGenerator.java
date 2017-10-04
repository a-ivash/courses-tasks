package datagen;

import project.database.dao.factories.AbstractDAOFactory;

public abstract class AbstractGenerator {
    protected AbstractDAOFactory daoFactory;
    public AbstractGenerator(AbstractDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
