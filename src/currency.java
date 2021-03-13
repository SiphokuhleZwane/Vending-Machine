import java.util.ArrayList;

public class currency extends FileOperations {


	private int total = 0;

	public currency(String fileName) {
		super(fileName);
	}

	private Boolean currencyCheck(int amount) {
		int count = 0;
		for (String[] attributes: list) {
			if (Integer.parseInt(attributes[0]) == amount) {
				stockChange(count, "add", 1);
				return true;
			}
			count++;
		}
		return false;
	}
	
	public ArrayList<String[]> list() {
		return list;
	}

	public void sum(int amount) { 
		currencyCheck(amount);
		total += amount; 
	}

	public void changeCal(int price) {
		total -= price;
		System.out.println("\nTotal left after deduction of R" + price + ": R" + total);
	}

	public int getTotal() {
		return total;
	}

	public void finalChange() {
		int count = 0;
		int change = 0;
		System.out.println("\nTotal change owed: R" + total);
		String changeList = "Change: ";
		while (count < list.size()) {
			String[] attributes = list.get(count); 
			int rand =  Integer.parseInt(attributes[0]);
			int stock = Integer.parseInt(attributes[1]); 
			if (stock > 0 && (change + rand) <= total) {
				changeList += "R" + rand + " ";
				stockChange(count, "subtract", 1);
				change += rand;
			}
			else if (change == total) {
				System.out.println(changeList);
				System.out.println("\nThank you for shopping at Sipho's Vending Machine");
				break;
			}
			else if (change != total && count == list.size() -1) {
				System.out.println(changeList);
				System.out.println("\nInsufficient funds for full change"
						+ "\nPlease contact Sipho at 'vm@gmail.com' for assistance");
				break;
			}
			else
				count++;
		}
	}
}
