package project.model.users;

import project.model.AbstractEntity;

public class Address extends AbstractEntity<Long>{
    private long id;
    private String streetName;
    private String buildingNumber;
    private String apartmentsNumber;

    public Address() {

    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentsNumber() {
        return apartmentsNumber;
    }

    public void setApartmentsNumber(String apartmentsNumber) {
        this.apartmentsNumber = apartmentsNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address)obj;
        return (address.id == this.id) &&
                (address.streetName.equals(this.streetName)) &&
                (address.buildingNumber.equals(this.buildingNumber)) &&
                (address.apartmentsNumber.equals(this.apartmentsNumber));
    }
}
