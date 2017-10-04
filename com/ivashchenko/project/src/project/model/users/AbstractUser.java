package project.model.users;

import project.model.AbstractEntity;

public abstract class AbstractUser extends AbstractEntity<Long> {
    public abstract boolean isAdministrator();
}
