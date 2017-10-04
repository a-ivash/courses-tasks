package project.model.users;

import project.model.AbstractEntity;

public class Phone extends AbstractEntity<Long> {
    private long phoneId;
    private String phoneNumber;
    private boolean isUsed;

    @Override
    public Long getId() {
        return phoneId;
    }

    @Override
    public void setId(Long id) {
        this.phoneId = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

}
