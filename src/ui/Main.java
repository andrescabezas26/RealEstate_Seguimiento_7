package ui; 

import java.util.Scanner;

import model.RealEstate;

public class Main{

	private Scanner reader; 
	private RealEstate realEstate;

	public Main(){
		reader = new Scanner(System.in);
		realEstate= new RealEstate();
	}

	public Scanner getReader(){
		return reader;
	}

	public static void main(String[] args){
		Main main = new Main(); 
		int option = 0; 
		do{
			option = main.getOptionShowMenu(); 
			main.executeOption(option);
		}while(option != 0);
		main.getReader().close();
	}
	
	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Bienvenido al Video Juego >>>>>");
		System.out.println(
				"1. Agregar un Edificio\n" +
				"2. Agregar un Apartamento a un Edificio\n" +
				"3. Agregar un Dueño de apartamento\n" +
                "4. Agregar un Arrendatario\n" +
                "5. Consultar cuantos disponibles apartamentos hay en un edificio\n" +
                "6. Consultar el arriendo de todos los apartamentos de un edificio\n" +
                "7. Consultar si un apartamento esta disponible\n" +
                "8. Consultar los apartamentos de un Dueño\n" +
                "9. Consultar el arriendo de todos los apartamentos de un dueño\n" +
				"0. Salir del Programa. ");
		option = validateIntegerOption();
		return option; 
	}

	public void executeOption(int option){
		switch(option){
			case 1:
				addBuilding();
				break; 

			case 2:
				addApartment();
				break; 

			case 3:
				addOwner();
				break;
            
            case 4:
				addTenant();
				break; 
            
            case 5:
				showEmptyApartmentsOfABuilding();
				break;
                
            case 6:
				showApartmentsRentOfABuilding();
				break; 

            case 7:
				checkApartmentAvalibility();
                break;

            case 8:
				showOwnerApartmentsRented();
				break; 

            case 9:
				
				break; 

			case 0: 
				System.out.println("Programa Finalizado.");
				break; 

			default: 
				System.out.println("Opcion Invalida");
				break; 
		}
	}

    public void addBuilding(){
        System.out.println("Escribe el nombre del edificio");
        String buildingId=reader.next();
        System.out.println("Escribe la direccion del edificio");
        String address=reader.next();
        System.out.println(realEstate.addBuilding(address, buildingId));
    }

	public void addApartment(){
		System.out.println("Escribe el nombre del edificio");
		String buildingId=reader.next();
		System.out.println("Escribe el numero del apartamento");
		int apartmentNumber=validateIntegerOption();
		while(apartmentNumber==-1){
			System.out.println("Escribe un numero entero");
			apartmentNumber=validateIntegerOption();
		}
		System.out.println("Escribe el numero de habitaciones");
		int rooms=validateIntegerOption();
		while(rooms==-1){
			System.out.println("Escribe un numero entero");
			rooms=validateIntegerOption();
		}
		System.out.println("Escribe el numero de baños");
		int bathrooms=validateIntegerOption();
		while(bathrooms==-1){
			System.out.println("Escribe un numero entero");
			bathrooms=validateIntegerOption();
		}
		System.out.println("Presiona 0 si el apartamento tiene balcon, de lo contrario presiona 1");
		int optionBalcony=validateIntegerOption();
		while(optionBalcony<0 || optionBalcony>1){
			System.out.println("Escribe 0 o 1");
			optionBalcony=validateIntegerOption();
		}
		boolean hasBalcony=false;
		if(optionBalcony==0){
			hasBalcony=true;
		}
		System.out.println("Escribe la renta mensual del apartamento");
		double rent=validateIntegerOption();
		while(rent==-1){
			System.out.println("Escribe un numero");
			rent=validateIntegerOption();
		}
		System.out.println(realEstate.addApartment(buildingId, apartmentNumber, rooms, bathrooms, hasBalcony, rent));
	}

	public void addOwner(){
		System.out.println("Escribe el id del Dueño");
		String id=reader.next();
		System.out.println("Escribe su nombre completo");
		String fullName=reader.next();
		System.out.println("Escribe su numero de telefono");
		int phoneNumber=validateIntegerOption();
		while(phoneNumber==-1){
			System.out.println("Escribe un numero de telefono");
			phoneNumber=validateIntegerOption();
		}
		System.out.println(realEstate.showTypePhoneList());
		int optionTypePhone=validateIntegerOption();
		while(optionTypePhone<1 || optionTypePhone>5){
			System.out.println("Escribe una opcion valida");
			optionTypePhone=validateIntegerOption();
		}
		System.out.println("Escribe el nombre del banco al que pertenece");
		String bankName=reader.next();
		System.out.println("Escribe el numero de cuenta");
		String bankAccount=reader.next();
		int isAdded=realEstate.addOwner(id, fullName, phoneNumber, optionTypePhone, bankAccount, bankName);
		if(isAdded==1){
			System.out.println("El Dueño fue agregado correctamente");
			int assignApartments=1;
			while(assignApartments==1){
				System.out.println("Si desea asignar apartamentos al dueño escriba 1, de lo contrario escriba 2");
				assignApartments=validateIntegerOption();
				while(assignApartments<1 && assignApartments>2){
					System.out.println("Escriba una opción valida");
					assignApartments=validateIntegerOption();
				}
				if(assignApartments==1){
					System.out.println("Escribe el id del Edificio");
					String buildingId=reader.next();
					System.out.println("Escribe el numero del apartamento");
					int apartmentNumber=validateIntegerOption();
					while(apartmentNumber==-1){
						System.out.println("Escribe un numero entero");
						apartmentNumber=validateIntegerOption();
					}
					System.out.println(realEstate.assignApartment(buildingId, apartmentNumber, id));
				}
			}
		}else if(isAdded==0){
			System.out.println("El id del dueño esta repetido");
		}else if(isAdded==-1){
			System.out.println("Capacidad Maxima de Personas Alcanzada");
		}
	}

	public void addTenant(){
		System.out.println("Escribe el id del Arrendatario");
		String id=reader.next();
		System.out.println("Escribe su nombre completo");
		String fullName=reader.next();
		System.out.println("Escribe su numero de telefono");
		int phoneNumber=validateIntegerOption();
		while(phoneNumber==-1){
			System.out.println("Escribe un numero de telefono");
			phoneNumber=validateIntegerOption();
		}
		System.out.println(realEstate.showTypePhoneList());
		int optionTypePhone=validateIntegerOption();
		while(optionTypePhone<1 || optionTypePhone>5){
			System.out.println("Escribe una opcion valida");
			optionTypePhone=validateIntegerOption();
		}
		System.out.println("Escribe el id del Edificio");
		String buildingId=reader.next();
		System.out.println("Escribe el numero del apartamento");
		int apartmentNumber=validateIntegerOption();
		while(apartmentNumber==-1){
			System.out.println("Escribe un numero entero");
			apartmentNumber=validateIntegerOption();
		}
		System.out.println(realEstate.addTenant(id, fullName, phoneNumber, optionTypePhone, buildingId, apartmentNumber));
	}

	public void showEmptyApartmentsOfABuilding(){
		System.out.println("Escribe el id del Edificio");
		String buildingId=reader.next();
		System.out.println(realEstate.showEmptyApartmentsOfABuilding(buildingId));
	}

	public void showApartmentsRentOfABuilding(){
		System.out.println("Escribe el id del Edificio");
		String buildingId=reader.next();
		System.out.println(realEstate.showApartmentsRentOfABuilding(buildingId));
	}

	public void checkApartmentAvalibility(){
		System.out.println("Escribe el numero del Apartamento");
		int apartmentNumber=validateIntegerOption();
		while(apartmentNumber==-1){
			System.out.println("Escribe un numero entero");
			apartmentNumber=validateIntegerOption();
		}
	}

	public void showOwnerApartmentsRented(){
		System.out.println("Escribe el id del Dueño");
		String id=reader.next();
		System.out.println(realEstate.showOwnerApartmentsRented(id));
	}

	/**validateIntegerOption= Validates if the user input is a integer number
	 * @return option: int = The integer number that the user input
	 */
	public int validateIntegerOption(){
		int option = 0; 
		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}
		return option; 
	}
}