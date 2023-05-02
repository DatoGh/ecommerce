public class Product {
    private String product_id;
    private String product_name;

    private int product_price;

    private int currentQuantity;
    private int numOfPurchased;
    private int moneySpent;

    private int numOfSold;
    private int moneyGot;

    public Product(String product_id, String product_name, int product_price){
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        currentQuantity = 0;
        moneySpent =0;
    }

    public void purchaseProduct(int num, int price){
        currentQuantity +=num;
        numOfPurchased +=num;
        moneySpent+=num*price;
    }

    public void orderProduct(int num){
        if(num>currentQuantity){
            System.out.println("Quantity of available items is less than the demand");
            return;
        }
        currentQuantity -=num;
        numOfSold +=num;
        moneyGot += num*product_price;
    }

    public int getAvgPrice(){
        if(numOfPurchased==0){
            return -2;
        }

        return moneySpent/numOfPurchased;
    }

    public int getProductProfit(){
        if(moneyGot==0) return 0;
        return moneyGot - numOfSold*(moneySpent/numOfPurchased);
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getNumOfSold() {
        return numOfSold;
    }

    public void setNumOfSold(int numOfSold) {
        this.numOfSold = numOfSold;
    }
}
