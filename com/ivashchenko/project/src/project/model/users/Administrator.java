package project.model.users;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Administrator extends AbstractUser {
    @Override
    public boolean isAdministrator() {
        return true;
    }

    @Override
    public void setId(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public Long getId() {
        throw new NotImplementedException();
    }
}
