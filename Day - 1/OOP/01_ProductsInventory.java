/*
 * Inventory Management System
 *
 * Create an inventory system to store products category-wise.
 *
 * Each Product should contain:
 *   - Product ID
 *   - Product Name
 *   - Product Price
 *
 * Store the products using:
 *
 * HashMap<String, PriorityQueue<Product>>
 *
 * The PriorityQueue for each category should maintain products
 * in descending order of price (highest-priced product first).
 *
 * Implement the following operations:
 *
 * 1. Add Product
 *    - Add a product to the given category.
 *
 * 2. Display Products by Category
 *    - Given a category, display:
 *        a) Total number of products
 *        b) All products in descending order of price
 *        c) Product with the highest price
 *
 * 3. Display All Products Category-wise
 *    - Display all categories and their products.
 *    - Products within each category should be displayed
 *      in descending order of price.
 *
 * 4. Find Category with Highest-Priced Product
 *    - Find the category whose highest-priced product has
 *      the maximum price among all categories.
 *    - Display the category and product details.
 *
 * 5. Exit
 *
 *
 * Example:
 *
 * Category: Cooldrinks
 * 101 | Thums Up   | 75
 * 102 | 7UP        | 80
 * 103 | Coca Cola  | 90
 * 104 | Pepsi      | 70
 *
 * Display by category:
 *
 * Cooldrinks
 * 103 | Coca Cola | 90
 * 102 | 7UP       | 80
 * 101 | Thums Up  | 75
 * 104 | Pepsi     | 70
 *
 * Highest-priced product:
 * 103 | Coca Cola | 90
 *
 * If Chocolates contains a product priced at 250 and all other
 * categories have products priced below 250:
 *
 * Output:
 * Category with highest priced product: Chocolates
 * Product Details: 305 | Ferrero Rocher | 250
 */


package OOP;

import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Map;

class Product {
    int productId;
    String productName;
    int productPrice;

    Product (int productId, String productName, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return this.productId + " | " + this.productName + " | " + this.productPrice;
    }
}


class Inventory {
    
    static HashMap<String, PriorityQueue<Product>> products = new HashMap<>();

    static void addProduct(String category, Product product) {
        
        products.putIfAbsent(
            category, 
            new PriorityQueue<>(
                (a, b) -> Integer.compare(b.productPrice, a.productPrice)
            )
        );
        
        products.get(category).add(product);
        System.out.println("Product Added.");
    }

    void getProductsByCategory (String category) {
        
        if (!products.containsKey(category)) {
            System.out.println("We are sorry, but products with given categeoty are not available now..!");
            return;
        }

        PriorityQueue<Product> queue = products.get(category);
        PriorityQueue<Product> temp = new PriorityQueue<>(queue);



        int size = queue.size();

        Product highestPriceProduct = queue.peek();

        int i = 1;

        System.out.println("Total number of products in category (" + category + ") is: " + size);
        System.out.println();

        System.out.println("List of products in Category: " + category);
        while (!temp.isEmpty()) {
            Product product = temp.poll();
            System.out.println(i++ + " " + product);
        }

        System.out.println();
        System.out.println("Product with highest price: " + highestPriceProduct);
    }

    void getAllProducts() {

        System.out.println("Displaying All Products by Category");
        System.out.println();

        for (Map.Entry<String, PriorityQueue<Product>> entry : products.entrySet()) {

            String category = entry.getKey();

            PriorityQueue<Product> queue = entry.getValue();
            PriorityQueue<Product> temp = new PriorityQueue<>(queue);

            System.out.println(category);
            for (Product product : queue) {
                System.out.println(product);
            }

            System.out.println();
        }
    }

    void highestPriceProductCategory() {

        Product highestProduct = null;
        String highestCategory = null;

        for (Map.Entry<String, PriorityQueue<Product>> entry : products.entrySet()) {

            Product product = entry.getValue().peek();

            if (highestCategory == null ||
                    product.productPrice > highestProduct.productPrice ) {

                        highestCategory = entry.getKey();
                        highestProduct = product;
                }
        }

        System.out.println("Category with highest priced product is : " + highestCategory);
        System.out.println("Product Details: " + highestProduct.toString());
    }
}


class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        
        //Cooldrinks
        Product cProduct1 = new Product(101, "Thumbs Up", 75);
        Product cProduct2 = new Product(102, "7UP", 80);
        Product cProduct3 = new Product(103, "Coca Cola", 90);
        Product cProduct4 = new Product(104, "Pepsi", 70);
        Product cProduct5 = new Product(105, "Sprite", 85);

        //Snaks
        Product sProduct1 = new Product(201, "Lays", 50);
        Product sProduct2 = new Product(202, "Kurkure", 40);
        Product sProduct3 = new Product(203, "Bingo", 60);
        Product sProduct4 = new Product(204, "Doritos", 90);
        Product sProduct5 = new Product(205, "Uncle Chips", 45);

        //Chocolates
        Product chProduct1 = new Product(301, "Dairy Milk", 120);
        Product chProduct2 = new Product(302, "KitKat", 100);
        Product chProduct3 = new Product(303, "5 Star", 80);
        Product chProduct4 = new Product(304, "Perk", 60);
        Product chProduct5 = new Product(305, "Ferrero Rocher", 250);

        //Personal Care
        Product pProduct1 = new Product(401, "Dove Soap", 65);
        Product pProduct2 = new Product(402, "Pears Soap", 55);
        Product pProduct3 = new Product(403, "Lux Soap", 45);
        Product pProduct4 = new Product(404, "Nivea Cream", 180);
        Product pProduct5 = new Product(405, "Colgate", 110);

        //Biscuits
        Product bProduct1 = new Product(501, "Oreo", 40);
        Product bProduct2 = new Product(502, "Good Day", 50);
        Product bProduct3 = new Product(503, "Parle-G", 30);
        Product bProduct4 = new Product(504, "Dark Fantasy", 90);
        Product bProduct5 = new Product(505, "Hide & Seek", 60);

        // Cooldrinks
        Inventory.addProduct("Cooldrinks", cProduct1);
        Inventory.addProduct("Cooldrinks", cProduct2);
        Inventory.addProduct("Cooldrinks", cProduct3);
        Inventory.addProduct("Cooldrinks", cProduct4);
        Inventory.addProduct("Cooldrinks", cProduct5);

        // Snacks
        Inventory.addProduct("Snacks", sProduct1);
        Inventory.addProduct("Snacks", sProduct2);
        Inventory.addProduct("Snacks", sProduct3);
        Inventory.addProduct("Snacks", sProduct4);
        Inventory.addProduct("Snacks", sProduct5);

        // Chocolates
        Inventory.addProduct("Chocolates", chProduct1);
        Inventory.addProduct("Chocolates", chProduct2);
        Inventory.addProduct("Chocolates", chProduct3);
        Inventory.addProduct("Chocolates", chProduct4);
        Inventory.addProduct("Chocolates", chProduct5);

        // Personal Care
        Inventory.addProduct("Personal Care", pProduct1);
        Inventory.addProduct("Personal Care", pProduct2);
        Inventory.addProduct("Personal Care", pProduct3);
        Inventory.addProduct("Personal Care", pProduct4);
        Inventory.addProduct("Personal Care", pProduct5);

        // Biscuits
        Inventory.addProduct("Biscuits", bProduct1);
        Inventory.addProduct("Biscuits", bProduct2);
        Inventory.addProduct("Biscuits", bProduct3);
        Inventory.addProduct("Biscuits", bProduct4);
        Inventory.addProduct("Biscuits", bProduct5);

        Inventory inventory = new Inventory();

        while (true) {

            System.out.println("========================= QUERIES =========================");

            System.out.println("1. Add Product");
            System.out.println("2. Display Products by Category");
            System.out.println("3. Display all products by Category wise");
            System.out.println("4. Find Category that has highest price product");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 5) {
                System.out.println("Exiting from the inventory system...");
                break;
            }

            if (choice == 1) {
                System.out.println("Adding Product");

                System.out.println("Enter Product Details");

                System.out.print("Enter Product ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Product Price: ");
                int price = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter category of this product: ");
                String category = sc.nextLine();

                Product product = new Product(id, name, price);
                Inventory.addProduct(category, product);
            }

            else if (choice == 2) {
                System.out.println("Enter category to display products: ");
                String category = sc.nextLine();
                inventory.getProductsByCategory(category);
            } 

            else if (choice == 3) {
                inventory.getAllProducts();
            }

            else if (choice == 4) {
                inventory.highestPriceProductCategory();
            }

            else {
                System.out.println("Please enter a valid choice!");
            }
        }
        
    }
}