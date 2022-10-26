package model;

public class Owner extends Person {
    public static final int SIZE_OF_APARTMENTS=500;
    private String bankAccount;
    private String bankName;
    private Apartment apartments[];

    public Owner(String id, String fulName,int phoneNumber, int optionTypePhone, String bankAccount, String bankName){
        super(id, fulName, phoneNumber, optionTypePhone);
        this.bankAccount=bankAccount;
        this.bankName=bankName;
        apartments= new Apartment[SIZE_OF_APARTMENTS];
    }

    public Apartment[] getApartments() {
        return apartments;
    }

    public int ownerHasEmptyPos(){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<SIZE_OF_APARTMENTS && !isFound;i++){
            if(apartments[i]==null){
                pos=i;
                isFound=true;
            }
        }
        return pos;
    }

}
