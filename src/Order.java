public class Order {
    private String product_id;
    private String product_name;
    private int product_price;

    private int product_selling_price;
    private int product_quantity;
    private int COGS;

    public Order(String product_id, String product_name, int product_price, int product_selling_price, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_selling_price = product_selling_price;
        this.product_quantity = product_quantity;
        COGS = product_price * product_quantity;
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

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_selling_price() {
        return product_selling_price;
    }

    public void setProduct_selling_price(int product_selling_price) {
        this.product_selling_price = product_selling_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getCOGS() {
        return COGS;
    }

    public void setCOGS(int COGS) {
        this.COGS = COGS;
    }
}
