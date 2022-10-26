package model;

public class Apartment {
    private int apartmentNumber;
    private int rooms;
    private int bathrooms;
    private boolean hasBalcony;
    private Person tenant;
    private Owner owner;
    private double rent;

    public Apartment(int apartmentNumber,int rooms,int bathrooms, boolean hasBalcony, double rent){
        this.apartmentNumber=apartmentNumber;
        this.rooms=rooms;
        this.bathrooms=bathrooms;
        this.hasBalcony=hasBalcony;
        this.rent=rent;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public Owner getOwner() {
        return owner;
    }
    public Person getTenant() {
        return tenant;
    }
    public double getRent() {
        return rent;
    }
}
