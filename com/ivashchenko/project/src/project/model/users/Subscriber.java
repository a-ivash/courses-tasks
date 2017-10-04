package project.model.users;

import java.util.Date;

public class Subscriber extends AbstractUser {
    private long id;
    private String email;
    private String rawPassword;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private Address address;
    private Phone phone;
    private Date joinDate;

    private Date lastPaymentDate;

    public Subscriber() {

    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return isActive && phone != null;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isBlocked() {
        return !isActive && phone != null;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return String.format("[%s %s]", getFirstName(), getLastName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    @Override
    public boolean isAdministrator() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Subscriber)){
            return false;
        }
        Subscriber subscriber = (Subscriber)obj;
        return (this.id == subscriber.id) && (this.firstName.equals(subscriber.firstName)) &&
                (this.lastName.equals(subscriber.lastName)) && (this.email.equals(subscriber.email));
    }
}
