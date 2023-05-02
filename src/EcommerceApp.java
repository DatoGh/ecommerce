import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EcommerceApp {
    public static List<Product> catalog = new ArrayList<>();
    public static List<Order> orderList = new ArrayList<>();

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String inp = scanner.nextLine();
            if(inp.equals(" ")){
                System.out.println("Incorrect command.");
                continue;
            }
            String[] input = inp.split(" ");
            int res;
            switch(input[0]) { //input[0] is the command
                case "save_product":
                    if(input.length!=4 || !isNumerical(input[3])){
                        System.out.println("Incorrect command");
                        break;
                    }
                    save_product(input[1],input[2],Integer.parseInt(input[3]));
                    break;
                case "purchase_product":
                    if(input.length!=4 || !isNumerical(input[3])){
                        System.out.println("Incorrect command");
                        break;
                    }
                    purchase_product(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));
                    break;
                case "order_product":
                    if(input.length!=3 || !isNumerical(input[2])){
                        System.out.println("Incorrect command");
                        break;
                    }
                    order_product(input[1], Integer.parseInt(input[2]));
                    break;
                case "get_quantity_of_product":
                    if(input.length!=2){
                        System.out.println("Incorrect command");
                        break;
                    }
                    res = get_quantity_of_product(input[1]);
                    System.out.println(res==-1?"Product not found.":res);
                    break;
                case "get_average_price":
                    if(input.length!=2){
                        System.out.println("Incorrect command");
                        break;
                    }
                    res = get_average_price(input[1]);
                    if(res==-2)
                        System.out.println("Product has not purchased yet");
                     else if(res==-1)
                        System.out.println("Product not found.");
                     else
                         System.out.println(res);
                    break;
                case "get_product_profit":
                    if(input.length!=2){
                        System.out.println("Incorrect command");
                        break;
                    }
                    res = get_product_profit(input[1]);
                    System.out.println(res==-1?"Product not found.":res);
                    break;
                case "get_fewest_product":
                    System.out.println(get_fewest_product());
                    break;
                case "get_most_popular_product":
                    System.out.println(get_most_popular_product());
                    break;
                case "get_orders_report":
                    System.out.println(get_orders_report());
                    break;
                case "export_orders_report":
                    if(input.length<2){
                        System.out.println("Path needed");
                    } else {
                        export_orders_report(input[1]);
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command.");
                    break;
            }

        }
    }
    public static boolean isNumerical(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void save_product(String product_id, String product_name, int price){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        if(optProduct.isPresent()){
            optProduct.get().setProduct_price(price);
        } else{
            catalog.add(new Product(product_id, product_name, price));
        }
    }

    private static void purchase_product(String product_id, int quantity, int price){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        if(optProduct.isPresent()){
            optProduct.get().purchaseProduct(quantity, price);
        } else{
            System.out.println("Product not found.");
        }
    }

    private static void order_product(String product_id, int quantity){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        if(optProduct.isPresent()){
            Product product = optProduct.get();
            product.orderProduct(quantity);
            orderList.add(new Order(product.getProduct_id(), product.getProduct_name(), product.getAvgPrice(), product.getProduct_price(), quantity));
        } else{
            System.out.println("Product not found.");
        }
    }

    private static int get_quantity_of_product(String product_id){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        return optProduct.map(Product::getCurrentQuantity).orElse(-1);
    }

    private static int get_average_price(String product_id){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        return optProduct.map(Product::getAvgPrice).orElse(-1);
    }

    private static int get_product_profit(String product_id){
        Optional<Product> optProduct = catalog.stream().filter(product -> product.getProduct_id().equals(product_id)).findAny();
        return optProduct.map(Product::getProductProfit).orElse(-1);
    }

    private static String get_fewest_product(){
        Optional<Product> optProduct = catalog.stream().min(Comparator.comparingInt(Product::getCurrentQuantity));
        return optProduct.map(Product::getProduct_name).orElse("Product catalog is empty.");
    }
    private static String get_most_popular_product(){
        Optional<Product> optProduct = catalog.stream().max(Comparator.comparingInt(Product::getNumOfSold));
        return optProduct.map(Product::getProduct_name).orElse("Product catalog is empty.");
    }

    private static String get_orders_report(){
        StringBuilder result = new StringBuilder("product ID,product name,quantity,price,cost of goods sold (COGS),selling price\n");
        for(Order order : orderList){
            result.append(String.format("%s,%s,%d,%d,%d,%d\n",
                    order.getProduct_id(),
                    order.getProduct_name(),
                    order.getProduct_quantity(),
                    order.getProduct_price(),
                    order.getCOGS(),
                    order.getProduct_selling_price()));
        }
        return result.toString();
    }

    private static void export_orders_report(String path){
        try (FileWriter csvWriter = new FileWriter(path)){
           csvWriter.write("product ID,product name,quantity,price,cost of goods sold (COGS),selling price\n");

           for(Order order : orderList){
               csvWriter.append(String.format("%s,%s,%d,%d,%d,%d\n",
                       order.getProduct_id(),
                       order.getProduct_name(),
                       order.getProduct_quantity(),
                       order.getProduct_price(),
                       order.getCOGS(),
                       order.getProduct_selling_price()));
           }
           csvWriter.flush();
        }catch (IOException e) {
            System.out.println("An error occurred while flushing/closing the CSV file writer: " + e.getMessage());
        }
    }
}