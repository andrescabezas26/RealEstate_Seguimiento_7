package model;

public class Building{
    public static final int SIZE_OF_APARTMENTS=10;
    private Apartment[] apartments;
    private String buildingId;
    private String address;

    public Building(String address,String buildingId){
        apartments= new Apartment[SIZE_OF_APARTMENTS];
        this.buildingId= buildingId;
        this.address=address;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public String getAddress() {
        return address;
    }

    public Apartment[] getApartments() {
        return apartments;
    }

    public int addApartmentWithObject(Apartment apartment){
        int confirmation=-1;
        if(buildingHasEmptyPos()!=-1){
            apartments[buildingHasEmptyPos()]=apartment;
            confirmation=1;
        }
        return confirmation;
    }

    public int searchApartmentByNumber(int apartmentNumber){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<SIZE_OF_APARTMENTS && !isFound;i++){
            if(apartments[i]!=null){
                if(apartmentNumber==apartments[i].getApartmentNumber()){
                    pos=i;
                    isFound=true;
                }
            }
        }
        return pos;
    }

    public int buildingHasEmptyPos(){
        int pos=-1;
        boolean isEmpty=false;
        for(int i=0;i<SIZE_OF_APARTMENTS && !isEmpty;i++){
            if(apartments[i]==null){
                pos=i;
                isEmpty=true;
            }
        }
        return pos;
    }
}