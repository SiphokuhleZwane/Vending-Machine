import java.util.ArrayList;

public class stock extends FileOperations {
	
	private String[] stockImages = { "./Products/Simba Fruit Chutney.png", "./Products/Doritos Cajun.png", 
			"./Products/Snickers.png", "./Products\\Lunch Bar.png",
			"./Products/Peanuts & Raisins.png", "./Products/Powerade.png" };
	
	protected ArrayList<String[]> purchaseCart =  new ArrayList<>();

	public stock(String fileName) {
		super(fileName);
	}
	
	public String[] labels() {
		String[] label = {list.get(0)[0], list.get(0)[1], list.get(0)[2]};
		return label;
	}
	
	public int stockSize() {
		return stockImages.length;
	}
	
	public String stockItem(int i) {
		return list.get(i)[0];
	}
	
	public int stockPrice(int i) {
		return Integer.parseInt(list.get(i)[1]);
	}
	
	public int stockQty(int i) {
		return Integer.parseInt(list.get(i)[2]);
	}
	
	public String stockImage(int i) {
		return stockImages[i];
	}
	
	public Boolean sufficientFunds(int selection, int total) {
		if (stockPrice(selection) > total) {
			return false;
		}
		else return true;
	}

	public Boolean stockAvail(int selection) {
		if (stockQty(selection) == 0) {
			return false;
		} else {
			cartItems(selection);
			stockChange(selection, "subtract", 2);
			return true;
		}
	}
	
	
	public void cartItems(int selection) {
		String[] newItem = {stockItem(selection),""+stockPrice(selection),""+1};
		for (String[] purchaseItem : purchaseCart) {
			if (purchaseItem[0].equals(stockItem(selection))) {
				purchaseItem[2] = ""+ (Integer.parseInt(purchaseItem[2]) + 1);
				purchaseItem[1] = ""+ (Integer.parseInt(purchaseItem[1]) + stockPrice(selection));
				
				return;
			}
		}
		purchaseCart.add(newItem);
	}
}


