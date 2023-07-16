import java.util.Scanner;

class Suppliers{
    String id;
    String name;
}

class ItemDetails{
    String itemId; 
    int supplierIndexId; 
    int categoryIndexId; 
    String description; 
    double unitPrice; 
    int qty; 
}

class Category{
    String category;

}

class UnitPrice {
    int indexOfItemDetails;
    int price;
}

public class Main {
    //Tools
    private static Scanner input = new Scanner(System.in);
    //Database
    private static String username = "admin";
    private static String password = "123";
    private static ItemDetails [] itemDetails=new ItemDetails[0];
    private static UnitPrice [] unitPrice=new UnitPrice[0];
    private static Category [] categories=new Category[0];
    private static Suppliers [] suppliers=new Suppliers[0];

    //Additional Methods
    public static String isExistSuppliers(int index){
        if (index==-1){
            return null;
        }
        return suppliers[index].id;
    }
    public static String isExistCategories(int index){
        if (index==-1){
            return  null;
        }
        else {
            return categories[index].category;
        }
    }
    public static void unitPriceSorting(){
        for (int i = 0; i < itemDetails.length; i++){
            extendUnitPrice();
            unitPrice[unitPrice.length-1]= new UnitPrice();
            unitPrice[unitPrice.length-1].indexOfItemDetails = i;
            unitPrice[unitPrice.length-1].price = (int) itemDetails[i].unitPrice;
        }

        for (int i = 0; i < unitPrice.length - 1; i++) {
            for (int j = 0; j < unitPrice.length - (i+1); j++) {
                if (unitPrice[j].price > unitPrice[j + 1].price) {
                    int tempIndex = unitPrice[j].indexOfItemDetails;
                    int tempPrice = unitPrice[j].price;
                    unitPrice[j].indexOfItemDetails = unitPrice[j + 1].indexOfItemDetails;
                    unitPrice[j].price = unitPrice[j + 1].price;
                    unitPrice[j + 1].indexOfItemDetails = tempIndex;
                    unitPrice[j + 1].price = tempPrice;
                }
            }
        }
    }
   public static void searchSupplierTable(int index){
        boolean flag=false;
        for (int i = 0; i < suppliers.length; i++) {
            if ((itemDetails[i].supplierIndexId) == index) {
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
            System.out.printf("| %12s    | %15s    |%13s   |%14s   |%12s    |%n","ITEM CODE" , "DESCRIPTION","UNIT PRICE","QTY ON HAND","CATEGORY");
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
            for (int i = 0; i <itemDetails.length; i++) {
                if (itemDetails[i].supplierIndexId == index) {
                    System.out.printf("| %10s      | %13s      |%13s   |%12s     |%12s    |%n", itemDetails[i].itemId, itemDetails[i].description, itemDetails[i].unitPrice, itemDetails[i].qty, isExistCategories(itemDetails[i].categoryIndexId));
                }
            }
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
        }
        else {
            System.out.println();
            System.out.println("Its seems that the "+suppliers[index].name+" does not provide any items at the moment.");
        }
        System.out.println();
    }

    public static void suppliersTable(){
        System.out.printf("+-----------------+-------------------------+--------------------------+%n");
        System.out.printf("| %8s        | %17s       | %18s       |%n","#" , "SUPPLIER ID","SUPPLIER NAME");
        System.out.printf("+-----------------+-------------------------+--------------------------+%n");
        for (int i = 0; i < suppliers.length; i++) {
            System.out.printf("| %8s        | %14s          |        %-18s|%n",(i+1), suppliers[i].id,suppliers[i].name);
        }

        System.out.printf("+-----------------+-------------------------+--------------------------+%n");

    }
    public static void itemCategoriesTable(){
        System.out.printf("+-------------------------+--------------------------+%n");
        System.out.printf("| %13s           | %18s       |%n", "#","CATEGORY NAME");
        System.out.printf("+-------------------------+--------------------------+%n");
        for (int i = 0; i < categories.length; i++){
            System.out.printf("| %13s           |          %-16s|%n", (i+1), categories[i].category);
        }
        System.out.printf("+-------------------------+--------------------------+%n");
    }
    public static void extendUnitPrice(){
        UnitPrice [] temp=new UnitPrice[unitPrice.length+1];
        for (int i = 0; i < unitPrice.length; i++) {
			temp[i]=unitPrice[i];
        }
        unitPrice = temp;
    }
    public static void extendItemDetails(){
        ItemDetails [] temp = new ItemDetails[itemDetails.length+1];
        for (int i = 0; i < itemDetails.length; i++) {
			temp[i]=itemDetails[i];
        }
        itemDetails=temp;
    }
    public static void extendCategories(){
        Category []temp=new Category[categories.length+1];
        for (int i = 0; i < categories.length; i++) {
            temp[i]=categories[i];
        }
        categories=temp;
    }
    public static void extendSuppliers() {
        Suppliers [] temp=new Suppliers[suppliers.length+1];
        for (int i = 0; i < suppliers.length; i++){
            temp[i]= suppliers[i];
        }
        suppliers = temp;
    }
    public static void narrowCategories(int index) {
        // Delete the index of Categories Array
        Category []temp=new Category[categories.length-1];
        for (int i = 0, j = 0; i < categories.length; i++) {
            if (index == i) {
                continue;
            }
            temp[j] = categories[i];
            j++;
        }
        categories = temp;

        // Update the itemDetails Array
        for (int i = 0; i < itemDetails.length; i++) {
            if (itemDetails[i].categoryIndexId == index) {
                itemDetails[i].categoryIndexId = -1;
            } else if (itemDetails[i].categoryIndexId>index) {
                itemDetails[i].categoryIndexId = itemDetails[i].categoryIndexId-1;
            }
        }
    }
    public static void narrowSuppliers(int index) {

        //Delete the index of suppliers Array

        Suppliers []temp = new Suppliers[suppliers.length - 1];
        for (int i = 0, j = 0; i < suppliers.length; i++) {
            if (index == i) {
                continue;
            }
            temp[j] = suppliers[i];
            j++;
        }
        suppliers = temp;

        //Update the itemDetails Array
        for (int i = 0; i < itemDetails.length; i++) {
            if ( itemDetails[i].supplierIndexId == index){
                itemDetails[i].supplierIndexId=-1;
            } else if (itemDetails[i].supplierIndexId>index) {
                itemDetails[i].supplierIndexId= itemDetails[i].supplierIndexId-1;
            }
        }

    }
    public static void noCategoriesMassage(){
        System.out.println("There are no Categories in the system at the moment.");
        System.out.println();
        String text = "Do You want to go to manage item category ? (Y/N)?";
        char select = validation(text);
        if (select == 'N' || select == 'n') {
            System.exit(0);
        }
    }
    public static void noSupplierMassage(){
        System.out.println("There are no suppliers in the system at the moment.");
        System.out.println();
        String text = "Do You want to go to suppler manage? (Y/N)?";
        char select = validation(text);
        if (select == 'N' || select == 'n') {
            System.exit(0);
        }
    }
    public static int readIntegerInput(String message) {
        //This method is used to make sure input is an integer number
        int value;
        do {
            System.out.print(message);
            if (input.hasNextInt()) {
                value = input.nextInt();
                break;
            } else {
                String invalidInput = input.next();
                System.out.println("Invalid input. Please enter an integer value.");
                System.out.println();
            }
        } while (true);
        return value;
    }
    public static double readDoubleInput(String message) {
        double value;
        do {
            System.out.print(message);
            if (input.hasNextDouble()) {
                value = input.nextDouble();
                break;
            } else {
                String invalidInput = input.next();
                System.out.println("Invalid input. Please enter a double value.");
                System.out.println();
            }
        } while (true);
        return value;
    }
    public static char validation(String text) {
        char select;
        do {
            System.out.print(text);
            select = input.next().charAt(0);
            if ((select == 'Y') || (select == 'y') || (select == 'N') || (select == 'n')) {
                break;
            } else {
                System.out.println();
                System.out.println("Invalid input!!! Try Again");
                System.out.println();
            }
        } while (true);

        return select;
    }
    public static void  printTitle(String text){

        System.out.println("+--------------------------------------------------------------------+");

        System.out.print("|");
        for (int i = 0; i < 34-(text.length()/2); i++){
            System.out.printf("%1s","");
        }
        System.out.print(text);

        for (int i = 0; i < ((text.length()%2==0)?34-(text.length()/2): 34-(text.length()/2)-1); i++){
            System.out.printf("%1s","");
        }
        System.out.println("|");
		
		System.out.println("+--------------------------------------------------------------------+");
        
        System.out.println();
        System.out.println();
    }
    private static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {

            System.err.println(e.getMessage());
        }
    }

    //Stock Manage Methods
   public static void rankedUnitPrice(){
        clearConsole();
        printTitle("RANKED UNIT PRICE");

        if (itemDetails.length==0){
            System.out.println("There are no items in the system at the moment.");
        }
        else {
            unitPriceSorting();
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
            System.out.printf("| %8s      | %8s     |%11s      |%11s     |%9s      |%9s      |%n", "SID", "CODE", "DESC", "PRICE", "QTY","CAT");
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
            for (int i = 0; i < unitPrice.length; i++) {
                System.out.printf("| %8s      | %8s     |%13s    |%11s     |%9s      |%10s     |%n", isExistSuppliers(itemDetails[unitPrice[i].indexOfItemDetails].supplierIndexId), itemDetails[unitPrice[i].indexOfItemDetails].itemId, itemDetails[unitPrice[i].indexOfItemDetails].description, itemDetails[unitPrice[i].indexOfItemDetails].unitPrice, itemDetails[unitPrice[i].indexOfItemDetails].qty, isExistCategories(itemDetails[unitPrice[i].indexOfItemDetails].categoryIndexId));
            }
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
        }


        //Clear up the unit price array
        unitPrice = new UnitPrice[0];
        System.out.println();
        String text="Do you want to go stock manage page (Y/N)?";
        char select=validation(text);
        if ((select == 'N') || (select == 'n')){
            System.exit(0);
        }
    }
    public static void viewItems(){
        clearConsole();
        printTitle("VIEW ITEMS");
        if (categories.length==0){
            System.out.println("There are no categories in the system at the moment! ");
        }

        for (int i = 0; i < categories.length; i++) {
            System.out.println(categories[i].category + ": ");
            System.out.printf("+---------------+--------------+----------------+----------------+---------------+%n");
            System.out.printf("| %8s      | %8s     |%10s      |%11s     |%9s      |%n", "SID", "CODE", "DESC", "PRICE", "QTY");
            System.out.printf("+---------------+--------------+----------------+----------------+---------------+%n");

            boolean flag = false;
            for (int j = 0; j < itemDetails.length; j++) {
                if (itemDetails[j].categoryIndexId == i) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                for (int j = 0; j < itemDetails.length; j++) {
                    if (itemDetails[j].categoryIndexId  == i) {
                        System.out.printf("| %8s      | %8s     |%10s      |%11s     |%9s      |%n", isExistSuppliers(itemDetails[j].supplierIndexId), itemDetails[j].itemId, itemDetails[j].description, itemDetails[j].unitPrice, itemDetails[j].qty);
                    }
                }
            } else {
                System.out.printf("|%30s%s%31s|%n", "", "No items to display", "");
            }
            System.out.printf("+---------------+--------------+----------------+----------------+---------------+%n");
        }
        System.out.println();
        String text="Do you want to go stock manage page (Y/N)?";
        char select=validation(text);
        if ((select == 'N') || (select == 'n')){
            System.exit(0);
        }
    }
    public static void searchSupplier(){
        clearConsole();
        printTitle("SEARCH SUPPLIER");

        if (suppliers.length==0){
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();

                boolean flag = false;
                int index = -1;

                for (int i = 0; i < suppliers.length; i++) {
                    if ((suppliers[i].id).equals(id)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("Supplier Name: " + suppliers[index].name);
                    searchSupplierTable(index);
                    System.out.print("Search Successfully! ");
                    //For checking input validation
                    String text = "Do you want to Find another supplier?(Y/N)?";
                    char select = validation(text);
                    if (select == 'N' || select == 'n') {
                        break;
                    }
                } else {
                    System.out.println("Can't find supplier id. try again!");
                    System.out.println();
                }
                System.out.println();
            } while (true);
        }
    }
    public static void addItem(){
        clearConsole();
        printTitle("ADD ITEM");

        do {
            if (categories.length==0){
                System.out.println("OOPS! It seems that you don't have any item categories in the system.");
                String text="Do You want to add a new item category? (Y/N)?";
                char select=validation(text);
                if (select=='Y'||select=='y'){
                    addItemCategory();
                }
                break;
            } else if (suppliers.length==0) {
                System.out.println("OOPS! It seems that you don't have any Suppliers categories in the system.");
                String text="Do You want to add a new supplier? (Y/N)?";
                char select=validation(text);
                if (select=='Y'||select=='y'){
                    addSupplier();
                }
                break;
            } else {
                System.out.print("Item Code : ");
                String itemCode = input.next();
                boolean flag = false;

                for (int i = 0; i < itemDetails.length; i++) {
                    if ((itemDetails[i].itemId).equals(itemCode)) {
                        System.out.println("Item Code Already Exists. Try another item code!");
                        flag = true;
                        break;
                    }
                }

                if (!flag){
                    extendItemDetails();
                    itemDetails[itemDetails.length-1] = new ItemDetails();
                    itemDetails[itemDetails.length-1].itemId=itemCode;
                    suppliersTable();
                    do {
                        int number=readIntegerInput("Enter the supplier number > ");
                        if ((0<number) && (number <=suppliers.length)){
                            itemDetails[itemDetails.length-1].supplierIndexId= number-1;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid input! ");
                        }
                    }while (true);
                    itemCategoriesTable();
                    do {
                        int number=readIntegerInput("Enter the category number > ");
                        if (( 0< number) && (number <=categories.length)){
                            itemDetails[itemDetails.length-1].categoryIndexId= number-1;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid category number! ");
                        }
                    }while (true);
                    System.out.print("Description : ");
                    itemDetails[itemDetails.length-1].description= input.next();
                    do {
                        double number= readDoubleInput("Unit price : ");
                        if (0<number){
                            itemDetails[itemDetails.length-1].unitPrice= number;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid Unit price! ");
                        }
                    }while (true);
                    do {
                        int number=readIntegerInput("Qty on hand : ");
                        if (0<=number){
                            itemDetails[itemDetails.length-1].qty= number;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid Qty! ");
                        }
                    }while (true);
                    System.out.println("Added successfully!");
                    String text="Do You want to add another item (Y/N)?";
                    char select=validation(text);
                    if (select=='N'||select=='n'){
                        break;
                    }
                }
                System.out.println();
            }
        }while (true);
    }

    //Manage Item Category
    public static void updateItemCategory(){
        clearConsole();
        printTitle("UPDATE ITEM CATEGORY");

        if (categories.length==0){
            noCategoriesMassage();
        }
        else {
            do {
                System.out.print("Enter the name of the item category  : ");
                String category = input.next();
                boolean flag = false;
                int index = -1;

                for (int i = 0; i < categories.length; i++) {
                    if ((categories[i].category).equals(category)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }
                if (flag) {
                    System.out.print("Enter the new item name: ");
                    categories[index].category = input.next();
                    System.out.print("Updated successfully!");
                    //For checking input validation
                    String text = "Do You want to Update another category (Y/N)?";
                    char select = validation(text);
                    if (select == 'N' || select == 'n') {
                        break;
                    }
                } else {
                    System.out.println("Can't find category. try again!");
                    System.out.println();
                }
            } while (true);
        }
    }
    public static void deleteItemCategory(){
        clearConsole();
        printTitle("DELETE ITEM CATEGORY");

        do {
            if (categories.length==0){
                noCategoriesMassage();
                break;
            }
            else {
                System.out.print("Enter the name of the item category : ");
                String category = input.next();
                boolean flag = false;
                int index = -1;

                for (int i = 0; i < categories.length; i++) {
                    if ((categories[i].category).equals(category)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }

                if (flag) {
                    narrowCategories(index);
                    System.out.print("deleted successfully!");
                    //For checking input validation
                    String text = "Do You want to delete another category (Y/N)?";
                    char select = validation(text);
                    if (select == 'N' || select == 'n') {
                        break;
                    }
                } else {
                    System.out.println("Can't find category. try again!");
                }
                System.out.println();
            }
        }while (true);
    }
    public static void addItemCategory(){
        clearConsole();
        printTitle("ADD ITEM CATEGORY");

        do {
            System.out.print("Add the new item category  : ");
            String category = input.next();
            boolean flag = false;

            for (int i = 0; i <categories.length; i++) {
                if ((categories[i].category).equals(category)) {
                    System.out.println("Category Already Exists. Try another category!");
                    flag = true;
                    break;
                }
            }

            if (!flag){
                extendCategories();
                categories[categories.length-1] = new Category();
                categories[categories.length-1].category = category;
                System.out.print("added successfully!");

                //For checking input validation
                String text="Do You want to add another category (Y/N)?";
                char select=validation(text);
                if (select=='N'||select=='n'){
                    break;
                }
            }
            System.out.println();
        }while (true);
    }
    public static void itemCategories(){

        do {
            clearConsole();
            printTitle("MANAGE ITEM CATEGORY");
            System.out.printf("%-45s%10s%n", "[1] Add New Item Category", "[2] Delete Item Category");
            System.out.printf("%-45s%10s%n", "[3] Update Item Category", "[4] Stock Management");
            System.out.println();

            int option;
            do {
                option = readIntegerInput("Enter an option to continue > ");
                if ((0<option) && (option<5)) {
                    break;
                } else {
                    System.out.println("Invalid input.Try Again");
                }
            } while (true);

            switch (option) {
                case 1:
                    addItemCategory();
                    break;
                case 2:
                    deleteItemCategory();
                    break;
                case 3:
                    updateItemCategory();
                    break;
                case 4:
                    return;
            }
        }while (true);
    }

    //Supplier Manage Methods
    public static void searchSuppliers(){
        clearConsole();
        printTitle("SEARCH SUPPLIERS");

        if (suppliers.length==0){
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();

                boolean flag = false;
                int index=-1;
                for (int i = 0; i < suppliers.length; i++) {
                    if ((suppliers[i].id).equals(id)) {
                        flag = true;
                        index=i;
                        break;
                    }
                }

                if (flag){
                    System.out.println("Supplier Name: "+suppliers[index].name);
                    System.out.println();
                    System.out.print("Found Successfully ");
                    //For checking input validation
                    String text="Do you want to Find another supplier?(Y/N)?";
                    char select=validation(text);
                    if (select=='N'||select=='n'){
                        break;
                    }
                }
                else {
                    System.out.println("Can't find supplier id. try again!");
                }
                System.out.println();
            }while (true);
        }
    }
    public static void viewSuppliers(){
        clearConsole();
        printTitle("VIEW SUPPLIERS");

        if (suppliers.length==0){
            System.out.println("There are no suppliers in the system at the moment.");
        }
        else{
            System.out.printf("+-------------------------+--------------------------+%n");
            System.out.printf("| %17s       | %18s       |%n", "SUPPLIER ID","SUPPLIER NAME");
            System.out.printf("+-------------------------+--------------------------+%n");
            for (int i = 0; i < suppliers.length; i++) {
                System.out.printf("| %14s          |          %-16s|%n", suppliers[i].id, suppliers[i].name);
            }
            System.out.printf("+-------------------------+--------------------------+%n");
        }

        System.out.println();
        String text="Do you want to go supplier manage page?(Y/N)?";
        char select=validation(text);
        if (select=='N'||select=='n'){
            System.exit(0);
        }
    }
    public static void deleteSupplier(){
        clearConsole();
        printTitle("DELETE SUPPLIERS");

        do {
            if (suppliers.length==0){
                noSupplierMassage();
                break;
            }else {
                System.out.print("Supplier ID : ");
                String id = input.next();

                boolean flag = false;
                int index = -1;

                for (int i = 0; i < suppliers.length; i++) {
                    if ((suppliers[i].id).equals(id)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }

                if (flag) {
                    narrowSuppliers(index);
                    System.out.print("Deleted Successfully ");

                    //For checking input validation
                    String text = "Do you want to Delete another supplier?(Y/N)?";
                    char select = validation(text);
                    if (select == 'N' || select == 'n') {
                        break;
                    }
                } else {
                    System.out.println("Can't find supplier id. try again!");
                }
                System.out.println();
            }
        }while (true);
    }
   public static void updateSupplier(){
        clearConsole();
        printTitle("UPDATE SUPPLIERS");

        if (suppliers.length==0) {
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();

                boolean flag = false;
                int index = -1;
                for (int i = 0; i < suppliers.length; i++) {
                    if ((suppliers[i].id).equals(id)) {
                        flag = true;
                        index = i;
                        break;
                    }
                }

                if (flag) {
                    System.out.println("Supplier Name: " + suppliers[index].name);
                    System.out.println();
                    System.out.print("Enter the new supplier name: ");
                    suppliers[index].name = input.next();
                    System.out.print("Updated Successfully! ");

                    //For checking input validation
                    String text = "Do you want to update another supplier?(Y/N)?";
                    char select = validation(text);
                    if (select == 'N' || select == 'n') {
                        break;
                    }
                } else {
                    System.out.println("Can't find supplier id. try again!");
                }
                System.out.println();
            } while (true);
        }
    }
    public static void addSupplier(){
        clearConsole();
        printTitle("ADD SUPPLIERS");

        do {
            System.out.print("Supplier ID : ");
            String id = input.next();
            boolean flag = false;


            for (int i = 0; i <suppliers.length; i++) {
                if ((suppliers[i].id).equals(id)) {
                    System.out.println("ID Already Exists. Try another supplier id!");
                    flag = true;
                    break;
                }
            }

            if (!flag){
                extendSuppliers();
                suppliers[suppliers.length - 1] = new Suppliers();

                suppliers[suppliers.length - 1].id = id;
                System.out.print("Enter Supplier Name: ");
                suppliers[suppliers.length - 1].name = input.next();

                System.out.print("added successfully!");

                //For checking input validation
                String text="Do You want to add another supplier (Y/N)?";
                char select=validation(text);
                if (select=='N'||select=='n'){
                    break;
                }
            }
            System.out.println();
        }while (true);
    }

    //Home Methods
    public static void stockMange(){

        do {
            clearConsole();
            printTitle("STOCK MANAGEMENT");
            System.out.printf("%-45s%10s%n", "[1] Manage Item Categories", "[2] Add Item");
            System.out.printf("%-45s%10s%n", "[3] Get Items Supplier Wise", "[4] View Items");
            System.out.printf("%-45s%10s%n", "[5] Rank Items Per Unit Price", "[6] Home Page");
            System.out.println();

            int option;
            do {
                option = readIntegerInput("Enter an option to continue > ");
                if ((0<option) && (option<7)) {
                    break;
                } else {
                    System.out.println("Invalid input.Try Again");
                }
            } while (true);

            switch (option) {
                case 1:
                    itemCategories();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    searchSupplier();
                    break;
                case 4:
                    viewItems();
                    break;
                case 5:
                    rankedUnitPrice();
                    break;
                case 6:
                    return;
            }
        }while (true);
    }
    public static void supplierManage(){

        do {
            clearConsole();
            printTitle("SUPPLIER MANAGE");
            System.out.printf("%-45s%15s%n", "[1] Add Supplier", "[2] Update Supplier");
            System.out.printf("%-45s%10s%n", "[3] Delete Supplier", "[4] View Suppliers");
            System.out.printf("%-45s%10s%n", "[5] Search Supplier", "[6] Home Page");
            System.out.println();

            int option;
            do {
                option = readIntegerInput("Enter an option to continue > ");
                if ((0<option) && (option<7)) {
                    break;
                } else {
                    System.out.println("Invalid input.Try Again");
                }
            } while (true);

            switch (option) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    updateSupplier();
                    break;
                case 3:
                    deleteSupplier();
                    break;
                case 4:
                    viewSuppliers();
                    break;
                case 5:
                    searchSuppliers();
                    break;
                case 6:
                    return;
            }
        }while (true);
    }
    public static void changeCredentials(){
        clearConsole();
        printTitle("CREDENTIAL MANAGE");

        do {
            System.out.print("Please enter the User name to verify it's you: ");
            if (input.next().equals(username)){
                System.out.println("Hey "+username);
                break;
            }
            else {
                System.out.println("User name is invalid. please try again! ");
                System.out.println();
            }
        }while (true);

        do {
            System.out.print("Enter your current password: ");
            if (input.next().equals(password)){
                break;
            }
            else {
                System.out.println("Incorrect password. try again! ");
                System.out.println();
            }
        }while (true);

        System.out.print("Enter your new password: ");
        password=input.next();
        System.out.println();
        System.out.print("Password changed successfully!");

        //For checking input validation

        String text="Do you want to go home page (Y/N):?";
        char select=validation(text);
        if ((select == 'N') || (select == 'n')){
            System.exit(0);
        }
    }

    //Home
    public static void homePage(){

        do {
            clearConsole();
            printTitle("WELCOME TO IJSE STOCK MANAGEMENT SYSTEM");
            System.out.printf("%-45s%15s%n", "[1] Change the Credentials", "[2] Supplier Manage");
            System.out.printf("%-45s%10s%n", "[3] Stock Manage", "[4] Log out");
            System.out.printf("%-45s%n", "[5] Exit the system");
            System.out.println();

            System.out.println();
            int option;
            do {
                option=readIntegerInput("Enter an option to continue > ");
                if ((0<option) && (option<6)){
                    break;
                }
                else {
                    System.out.println("Invalid input.Try Again");
                }
            }while(true);

            switch (option) {
                case 1:
                    changeCredentials();
                    break;
                case 2:
                    supplierManage();
                    break;
                case 3:
                    stockMange();
                    break;
                case 4:
                    return;
                case 5:
                    System.exit(0);
            }
        }while (true);
    }

    //Login
    public static void login(){
        clearConsole();
        printTitle("LOGIN PAGE");

        //loops will only break after correct input.
        //Username
        do {
            System.out.print("User Name : ");
            if (input.next().equals(username)){
                break;
            }
            System.out.println("User name is invalid. please try again! ");
            System.out.println();
        }while (true);
		System.out.println();
        //Password
        do {
            System.out.print("Password : ");
            if (input.next().equals(password)){
                break;
            }
            System.out.println("Password is incorrect. please try again! ");
            System.out.println();
        }while (true);
        homePage();
    }

    public static void main(String[] args) {
        while (true) {
            login();
        }
    }
}
