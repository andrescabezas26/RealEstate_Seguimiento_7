package model;

public class RealEstate{
    public static final int SIZE_OF_BUILDINGS=5;
    public static final int SIZE_OF_PERSONS=2;
    public static final double MANAGEMENT_CONCEPT=0.10;
    
    private Building[] buildings;
    private Person[] persons;
    private String msj;
    
    public  RealEstate(){
        buildings=new Building[SIZE_OF_BUILDINGS];
        persons= new Person[SIZE_OF_PERSONS];
    }

    public String addBuilding(String address,String buildingId){
        msj="Capacidad Maxima de Edificios Alcanzada";
        Building newBuilding= new Building(address, buildingId);
        if(searchBuildingById(buildingId)==-1 && searchEmptyBuildingPos()!=-1){
            buildings[searchEmptyBuildingPos()]=newBuilding;
            msj="Edificio Agregado Correctamente";
        }else if(searchBuildingById(buildingId)!=-1){
            msj="Este nombre esta repetido";
        }
        return msj;
    }

    public String addApartment(String buildingId,int apartmentNumber,int rooms,int bathrooms, boolean hasBalcony, double rent, String idTenant, String idOwner){
        msj="No se encontro el Edificio " + buildingId;
        int buildingPos=searchBuildingById(buildingId);
        if(buildingPos!=-1){
            msj="El numero del Apartamento se encuentra repetido en la Inmobiliaria";
            if(searchApartmentByNumber(apartmentNumber)==-1){
                if(idTenant!=null){
                    msj="No se encontro al Arrendatario";
                    if(searchPersonById(idTenant)!=-1){
                        msj="El id no pertenece a un Arrendatario";
                        if(persons[searchPersonById(idTenant)] instanceof Tenat){
                            Person newTenant= persons[searchPersonById(idTenant)];
                            if(idOwner!=null){
                                int personPos=searchPersonById(idOwner);
                                msj="El Dueño no fue encontrado";
                                if(personPos!=-1){
                                    msj="El id no pertenece a un Dueño";
                                    if(persons[personPos] instanceof Owner){
                                        int ownerApartmentPos=((Owner) persons[personPos]).ownerHasEmptyPos();
                                        msj="El Dueño a alcanzado la capacidad maxima de Apartamentos";
                                        if(ownerApartmentPos!=-1){
                                            Apartment apartment=new Apartment(apartmentNumber, rooms, bathrooms, hasBalcony, rent, newTenant);
                                            msj="Capacidad maxima de Apartamentos alcanzada en el Edificio " +buildingId;
                                            if(buildings[searchBuildingById(buildingId)].addApartmentWithObject(apartment)==1){
                                                int apartmentPos=searchApartmentByNumber(apartmentNumber);
                                                buildings[buildingPos].getApartments()[apartmentPos].setOwner(persons[personPos]);
                                                ((Owner) persons[personPos]).getApartments()[ownerApartmentPos]=apartment;
                                                msj="Apartamento agregado correctamente al Edificio " + buildingId;
                                            }
                                        }
                                    }
                                }
                            }else{
                                Apartment apartment=new Apartment(apartmentNumber, rooms, bathrooms, hasBalcony, rent, newTenant);
                                msj="Capacidad maxima de Apartamentos alcanzada en el Edificio " + buildingId;
                                if(buildings[searchBuildingById(buildingId)].addApartmentWithObject(apartment)==1){
                                    msj="Apartamento agregado correctamente al Edificio " + buildingId;
                                }
                            }
                        }
                    }
                }else{
                    if(idOwner!=null){
                        int personPos=searchPersonById(idOwner);
                        msj="El Dueño no fue encontrado";
                        if(personPos!=-1){
                            msj="El id no pertenece a un Dueño";
                            if(persons[personPos] instanceof Owner){
                                int ownerApartmentPos=((Owner) persons[personPos]).ownerHasEmptyPos();
                                msj="El Dueño a alcanzado la capacidad maxima de Apartamentos";
                                if(ownerApartmentPos!=-1){
                                    Apartment apartment=new Apartment(apartmentNumber, rooms, bathrooms, hasBalcony, rent, null);
                                    msj="Capacidad maxima de Apartamentos alcanzada en el Edificio " +buildingId;
                                    if(buildings[searchBuildingById(buildingId)].addApartmentWithObject(apartment)==1){
                                        int apartmentPos=searchApartmentByNumber(apartmentNumber);
                                        buildings[buildingPos].getApartments()[apartmentPos].setOwner(persons[personPos]);
                                        ((Owner) persons[personPos]).getApartments()[ownerApartmentPos]=apartment;
                                        msj="Apartamento agregado correctamente al Edificio " + buildingId;
                                    }
                                }
                            }
                        }
                    }else{
                        Apartment apartment=new Apartment(apartmentNumber, rooms, bathrooms, hasBalcony, rent, null);
                        msj="Capacidad maxima de Apartamentos alcanzada en el Edificio " + buildingId;
                        if(buildings[searchBuildingById(buildingId)].addApartmentWithObject(apartment)==1){
                            msj="Apartamento agregado correctamente al Edificio " + buildingId;
                        }
                    }
                }
            }
        }
    
    
        return msj;
    }

    public int addOwner(String typeId,String id, String fulName,int phoneNumber, int optionTypePhone, int bankAccount, String bankName){
        int confirmation=-1;
        Owner newOwner= new Owner(typeId,id, fulName, phoneNumber, optionTypePhone, bankAccount, bankName);
        if(searchPersonById(id)==-1){
            boolean isAdded=false;
            for(int i=0;i<SIZE_OF_PERSONS && !isAdded;i++){
                if(persons[i]==null){
                    persons[i]=newOwner;
                    isAdded=true;
                    confirmation=1;
                }
            }
        }else{
            confirmation=0;
        }
        return confirmation;
    }

    public String addTenant(String typeId ,String id, String fulName,int phoneNumber, int optionTypePhone, String buildingId, int apartmentNumber){
        boolean isAdded=false;
        Person newTenant= new Tenat(typeId,id, fulName, phoneNumber, optionTypePhone);
        int buildingPos=searchBuildingById(buildingId);
        if(buildingPos!=-1){
            int apartmentPos=buildings[buildingPos].searchApartmentByNumber(apartmentNumber);
            msj="No se encontró el apartamento " +apartmentNumber + " en el edificio " + buildingId;
            if(apartmentPos!=-1){
                msj="El apartamento " + apartmentNumber + " ya tiene un arrendatario";
                if(buildings[buildingPos].getApartments()[apartmentPos].getTenant()==null){
                    if(searchPersonById(id)==-1){
                        msj="Capacidad Maxima de Personas Alcanzada";
                        for(int i=0;i<SIZE_OF_PERSONS && !isAdded;i++){
                            if(persons[i]==null){
                                persons[i]=newTenant;
                                buildings[buildingPos].getApartments()[apartmentPos].setTenant(newTenant);
                                isAdded=true;
                                msj="Arrendatario Agregado Correctamente al Apartamento " + apartmentNumber + " en el Edificio "+ buildingId;
                            }
                        }
                    }
                }
            }
        }
        return msj;
    }

    public String showEmptyApartmentsOfABuilding(String buildingId){
        msj="No se encontro al Edificio " + buildingId;
        int emptyApartments=0;
        int buildingPos=searchBuildingById(buildingId);
        if(buildingPos!=-1){
            for(int i=0;i<10;i++){
                if(buildings[buildingPos].getApartments()[i]!=null){
                    if(buildings[buildingPos].getApartments()[i].getTenant()==null){
                        emptyApartments++;
                    }
                }
            }
            msj="Hay " + emptyApartments+ " Apartamentos disponibles en el Edificio " + buildingId;
        }
        return msj;
    }

    public String showApartmentsRentOfABuilding(String buildingId){
        double apartmentsRent=0;
        double realEstateRent=0;
        msj="El edificio " + buildingId + " no existe";
        int buildingPos=searchBuildingById(buildingId);
        if(buildingPos!=-1){
            for(int i=0;i<10;i++){
                if(buildings[buildingPos].getApartments()[i]!=null){
                    if(buildings[buildingPos].getApartments()[i].getTenant()!=null){
                        apartmentsRent+=buildings[buildingPos].getApartments()[i].getRent();
                        realEstateRent+=apartmentsRent*MANAGEMENT_CONCEPT;
                    }
                }
            }
            msj="El valos mensual a recibir por los apartamentos del Edificio " + buildingId + " es  $" + realEstateRent;
        }
        return msj;
    }

    public String checkApartmentAvalibility(int apartmentNumber){
        msj="No se encontro el apartamento";
        boolean isFound=false;
        if(searchApartmentByNumber(apartmentNumber)!=-1){
            for(int i=0;i<SIZE_OF_BUILDINGS && !isFound;i++){
                if(buildings[i]!=null){
                    if(buildings[i].getApartments()[searchApartmentByNumber(apartmentNumber)].getApartmentNumber()==apartmentNumber){
                        isFound=true;
                        msj="El apartamento esta ocupado";
                        if(buildings[i].getApartments()[searchApartmentByNumber(apartmentNumber)].getTenant()==null){
                            msj="El apartamento esta disponible";
                        }
                    }
                }
            }
        }
        return msj;    
    }

    public String showOwnerApartmentsRented(String id){
        msj="La persona no fue encontrada";
        int apartmentsRented=0;
        if(searchPersonById(id)!=-1){
            msj="El id no pertenece a un Dueño";
            if(persons[searchPersonById(id)] instanceof Owner){
                for(int i=0;i<500;i++){
                    if(((Owner)persons[searchPersonById(id)]).getApartments()[i]!=null){
                        if(((Owner)persons[searchPersonById(id)]).getApartments()[i].getTenant()!=null){
                            apartmentsRented++;
                        }
                    }
                }
                msj="El Dueño posee " +apartmentsRented+" Apartamentos arrendados";
            }
        }
        return msj;
    }

    public String showOwnerApartmentsRent(String id){
        msj="La persona no fue encontrada";
        int apartmentsRent=0;
        double ownerPay=0;
        if(searchPersonById(id)!=-1){
            msj="El id no pertenece a un Dueño";
            if(persons[searchPersonById(id)] instanceof Owner){
                for(int i=0;i<500;i++){
                    if(((Owner)persons[searchPersonById(id)]).getApartments()[i]!=null){
                        if(((Owner)persons[searchPersonById(id)]).getApartments()[i].getTenant()!=null){
                            apartmentsRent+=((Owner)persons[searchPersonById(id)]).getApartments()[i].getRent();
                            ownerPay+=apartmentsRent-(apartmentsRent*MANAGEMENT_CONCEPT);
                        }
                    }
                }
                msj="El Dueño recibe una renta mensual de " +ownerPay+" por sus Apartamentos arrendados";
            }
        }
        return msj;
    }

    public String assignApartment(String buildingId, int apartmentNumber, String id){
        msj="No se encontro el Edificio " + buildingId;
        int buildingPos=searchBuildingById(buildingId);
        if(buildingPos!=-1){
            int apartmentPos=buildings[buildingPos].searchApartmentByNumber(apartmentNumber);
            msj="No se encontro el Apartamento " +apartmentNumber + " en el Edificio " + buildingId;
            if(apartmentPos!=-1){
                msj="El Apartamento " + apartmentNumber + " ya tiene Dueño";
                if(buildings[buildingPos].getApartments()[apartmentPos].getOwner()==null){
                    int personPos=searchPersonById(id);
                    if(persons[personPos] instanceof Owner){
                        int ownerApartmentPos=((Owner) persons[personPos]).ownerHasEmptyPos();
                        msj="El Dueño a alcanzado la capacidad maxima de Apartamentos";
                        if(ownerApartmentPos!=-1){
                            buildings[buildingPos].getApartments()[apartmentPos].setOwner(persons[personPos]);
                            ((Owner) persons[personPos]).getApartments()[ownerApartmentPos]=buildings[buildingPos].getApartments()[apartmentPos];
                            msj="Apartamento asignado correctamente al Dueño";
                        }
                    }
                }
            }
        }
        return msj;
    }

    public int searchEmptyBuildingPos(){
        int pos=-1;
        boolean isEmpty=false;
        for(int i=0;i<SIZE_OF_BUILDINGS && !isEmpty;i++){
            if(buildings[i]==null){
                pos=i;
                isEmpty=true;
            }
        }
        return pos;
    }

    public int searchBuildingById(String buildingId){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<SIZE_OF_BUILDINGS && !isFound;i++){
            if(buildings[i]!=null){
                if(buildingId.equalsIgnoreCase(buildings[i].getBuildingId())){
                    pos=i;
                    isFound=true;
                }
            }
        }
        return pos;
    }

    public int searchApartmentByNumber(int apartmentNumber){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<SIZE_OF_BUILDINGS && !isFound;i++){
            for(int j=0;j<10;j++){
                if(buildings[i]!=null){
                    if(buildings[i].getApartments()[j]!=null){
                        if(buildings[i].getApartments()[j].getApartmentNumber()==apartmentNumber){
                            pos=j;
                            isFound=true;
                        }
                    }
                }
            }
        }
        return pos;
    }

    public int searchPersonById(String id){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<SIZE_OF_PERSONS && !isFound;i++){
            if(persons[i]!=null){
                if(id.equals(persons[i].getId())){
                    pos=i;
                    isFound=true;
                }
            }
        }
        return pos;
    }

    public String showTypePhoneList(){
        TypePhone typePhone[]=TypePhone.values();
        msj= "Tipo de telefonos: ";
        for(int i=0;i< typePhone.length;i++){
            msj += "\n" + (i+1) + " " + typePhone[i];
        }
        return msj;
    }
}
