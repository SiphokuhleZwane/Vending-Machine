import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VendingMachine {
	
	static String productsFile = "products.csv";

	// checks if amount inputed is acceptable 
	public static boolean currency(String amount) {	
		try (BufferedReader br = new BufferedReader(new FileReader("currency.csv"))) {
			String line = br.readLine(); 
			while (line != null) {
				if (amount == line)
					return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("SIPHO'S VEDNING MACHINE");
		
		System.out.println("Acceptable amounts (R50, R20, R10, R5, R2, R1)"
				+ "\n\nPlease enter a amount: ");
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();			

		// removing "R" and converting to int
		int amount =  Integer.parseInt(input.substring(1)); 
		
		stock st = new stock(productsFile);
		st.Menu();
		int input2 = -1;
		
		do {
			System.out.println("\nPlease pick number (enter zero to exit):");
			input2 = scan.nextInt();
			int result = st.availability(input2, amount);
			switch (result) {
				case -3:
					System.out.println("Number doesn't exist");
					break;
				case -2:
					System.out.println("Exiting Vending Machine");
					break;
				case -1:
					System.out.println("Insufficient Funds");
					break;
				case 0:
					System.out.println("Out of stock");
					break;
				default:
					st.stockChange(input2);
					
			}
			
		} while (input2 != 0);
		scan.close();
	}

}
