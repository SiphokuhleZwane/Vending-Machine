public class VendingMachine {

	static String productsFile = "./excel files/products.csv";
	static String currencyStockFile = "./excel files/currency_stock.csv";

	public static void main(String[] args) {
		currency cur = new currency(currencyStockFile);
		stock st = new stock(productsFile);
		new mainFrame(st, cur);
	}
}
