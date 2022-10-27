package model;

public class Apartment {
    private int apartmentNumber;
    private int rooms;
    private int bathrooms;
    private boolean hasBalcony;
    private Person tenant;
    private Person owner;
    private double rent;

    public Apartment(int apartmentNumber,int rooms,int bathrooms, boolean hasBalcony, double rent, Person tenat ){
        this.apartmentNumber=apartmentNumber;
        this.rooms=rooms;
        this.bathrooms=bathrooms;
        this.hasBalcony=hasBalcony;
        this.rent=rent;
        this.tenant=tenat;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public Person getOwner() {
        return owner;
    }
    public Person getTenant() {
        return tenant;
    }
    public double getRent() {
        return rent;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
