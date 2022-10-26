package model;

public abstract class Person{
    private String id;
    private String fullName;
    private int phoneNumber;
    private TypePhone typePhone;

    public Person(String id, String fulName,int phoneNumber, int optionTypePhone){
        this.id=id;
        this.fullName=fulName;
        this.phoneNumber=phoneNumber;
        this.typePhone=TypePhone.values()[optionTypePhone-1];
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

}
