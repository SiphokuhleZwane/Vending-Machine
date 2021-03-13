import java.util.ArrayList;

public class stock extends FileOperations {

	public stock(String fileName) {
		super(fileName);
	}
	
	public ArrayList<String[]> list() {
		return list;
	}

	public String insufficientFunds(int pick, int total) {
		int amountDue = Integer.parseInt(list.get(pick)[1]);
		if (amountDue > total) {
			return "Insufficient Funds. You still owe R" + (amountDue - total);
		}
		return "";
	}

	public String prodAvail(int pick) throws IndexOutOfBoundsException {
		if (Integer.parseInt(list.get(pick)[2]) == 0) {
			return "Out of stock";
		} else {
			stockChange(pick, "subtract", 2);
			return list.get(pick)[0];
		}
	}
	
	public int prodPrice(int pick) {
		return Integer.parseInt(list.get(pick)[1]);
	}
}
