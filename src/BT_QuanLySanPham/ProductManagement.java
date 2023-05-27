package BT_QuanLySanPham;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    private static final String FILE_PATH = "products.dat";

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        // Đọc dữ liệu từ file nếu file tồn tại.
        if (isFileExists(FILE_PATH)){
            products = readProductsFromFile(FILE_PATH);
        }

        while (running){
            System.out.println("---- Quản Lý Sản Phẩm ----");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Tìm kiếm sản phẩm");
            System.out.println("4. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    Product product = createProduct(scanner);
                    products.add(product);
                    System.out.println("Sản phẩm đã được thêm.");
                    break;
                case 2:
                    if (products.isEmpty()){
                        System.out.println("Danh sách sản phẩm rỗng.");
                    } else {
                        displayProducts(products);
                    }
                    break;
                case 3:
                    System.out.println("Nhập mã sản phẩm cần tìm: ");
                    String searchCode = scanner.nextLine();
                    Product foundProduct = findProductByCode(products, searchCode);
                    if (foundProduct != null){
                        System.out.println("Tìm thấy sản phẩm: ");
                        displayProduct(foundProduct);
                    } else {
                        System.out.println("Không tìm thấy sản phẩm với mã " + searchCode);
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }

        // Ghi danh sách sản phẩm vào file.
        writeProductsToFile(FILE_PATH, products);
        System.out.println("Chương trình đã kết thúc.");
    }

    private static boolean isFileExists(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    private static List<Product> readProductsFromFile(String filePath){
        List<Product> products = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            products = (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return products;
    }

    private static void writeProductsToFile(String filePath, List<Product> products){
        try (FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(products);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Product createProduct(Scanner scanner){
        System.out.println("Nhập mã sản phẩm: ");
        String code = scanner.nextLine();

        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();

        System.out.println("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Nhập hàng sản xuất: ");
        String manufaturer = scanner.nextLine();

        System.out.println("Nhập mô tả: ");
        String description = scanner.nextLine();

        return new Product(code, name, price, manufaturer, description);
    }

    private static void displayProducts(List<Product> products){
        System.out.println("Danh sách sản phẩm: ");
        for (Product product : products){
            displayProduct(product);
        }
    }

    private static void displayProduct(Product product){
        System.out.println("Mã sản phẩm: " + product.getCode());
        System.out.println("Tên sản phẩm: " + product.getName());
        System.out.println("Giá sản phẩm: " + product.getPrice());
        System.out.println("Hãng sản xuất: " + product.getManufacturer());
        System.out.println("Mô tả: " + product.getDescription());
        System.out.println("------------------");
    }

    private static Product findProductByCode(List<Product> products, String code){
        for (Product product : products){
            if (product.getCode().equals(code)){
                return product;
            }
        }
        return null;
    }
}
