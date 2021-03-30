import java.util.ArrayList;

public class currency extends FileOperations {


	private int total = 0;
	private boolean sufficientChangeVar = true;

	public currency(String fileName) {
		super(fileName);
	}
	
	public ArrayList<String[]> list() {
		return list;
	}
	
	public int size() {
		return list.size();
	}
	
	public int currencyUnit(int i) {
		return Integer.parseInt(list.get(i)[0]);
	}
	
	public int qty(int i) {
		return Integer.parseInt(list.get(i)[1]);
	}

	public void sum(int selection) { 
		stockChange(selection, "add", 1);
		total += currencyUnit(selection); 
	}

	public void changeCal(int price) {
		total -= price;
	}

	public int getTotal() {
		return total;
	}

	public String customerChange() {
		int count = 1;
		int change = 0;
		String changeList = "(R" + total + "): ";
		while (count < size()) {
			if (qty(count) > 0 && (change + currencyUnit(count)) <= total) {
				changeList += "R" + currencyUnit(count) + " ";
				stockChange(count, "subtract", 1);
				change += currencyUnit(count);
			}
			else if (change == total) {
				total = 0;
				return changeList;
			}
			else if (change != total && count == list.size() -1) {
				sufficientChangeVar = false;
				total = 0;
				return changeList;
			}
			else count++;
		}
		changeList = "R"+total;
		return changeList;
	}
	
	public boolean sufficientChange() {
		return sufficientChangeVar;
	}
}
