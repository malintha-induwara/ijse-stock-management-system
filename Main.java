import java.util.Scanner;

class Supplier{
    String id;
    String name;
}
class SupplierCollection{
    Supplier [] suppliersList =new Supplier[0];
    public void extendSuppliers() {
        Supplier [] temp=new Supplier[suppliersList.length+1];
        for (int i = 0; i < suppliersList.length; i++){
            temp[i]= suppliersList[i];
        }
        suppliersList = temp;
    }
    public void narrowSupplier(int index){

        Supplier []temp = new Supplier[suppliersList.length - 1];
        for (int i = 0, j = 0; i < suppliersList.length; i++) {
            if (index == i) {
                continue;
            }
            temp[j] = suppliersList[i];
            j++;
        }
        suppliersList = temp;
    }
    public int findIndex(String supID){
        for (int i = 0; i < suppliersList.length; i++) {
            if ((suppliersList[i].id).equals(supID)) {
                return i;
            }
        }
        return -1;
    }
    public boolean isSupplerExists(String supID){
        for (int i = 0; i < suppliersList.length; i++) {
            if ((suppliersList[i].id).equals(supID)) {
                return true;
            }
        }
        return false;
    }
    public void addSupplier(Supplier supplier) {
        extendSuppliers();
        suppliersList[suppliersList.length -1] = supplier;
    }
}
class Category{
    String category;
}
class CategoryCollection{
    Category [] categoryList=new Category[0];
    public void extendCategories(){
        Category []temp=new Category[categoryList.length+1];
        for (int i = 0; i < categoryList.length; i++) {
            temp[i]=categoryList[i];
        }
        categoryList=temp;
    }
    public void narrowCategories(int index){
        Category []temp=new Category[categoryList.length-1];
        for (int i = 0, j = 0; i < categoryList.length; i++) {
            if (index == i) {
                continue;
            }
            temp[j] = categoryList[i];
            j++;
        }
        categoryList = temp;
    }
    public boolean isCategoryExists(String category){
        for (int i = 0; i <categoryList.length; i++) {
            if ((categoryList[i].category).equals(category)) {
                return true;
            }
        }
        return false;
    }
    public int findIndex(String category){
        for (int i = 0; i <categoryList.length; i++) {
            if ((categoryList[i].category).equals(category)) {
                return i;
            }
        }
        return -1;
    }
    public void addCategory(Category category){
        extendCategories();
        categoryList[categoryList.length-1]=category;
    }
}
class ItemDetails{
    String itemId; 
    int supplierIndexId; 
    int categoryIndexId; 
    String description; 
    double unitPrice; 
    int qty; 
}
class UnitPrice {
    int indexOfItemDetails;
    int price;
}
class ItemDetailsCollection{
    ItemDetails [] itemDetailsList=new ItemDetails[0];
    public void extendItemDetails(){
        ItemDetails [] temp=new ItemDetails[itemDetailsList.length+1];
        for (int i = 0; i < itemDetailsList.length; i++) {
            temp[i]=itemDetailsList[i];
        }
        itemDetailsList=temp;
    }
    public boolean isItemExists(String itemCode){
        for (int i = 0; i < itemDetailsList.length; i++) {
            if ((itemDetailsList[i].itemId).equals(itemCode)) {
                return true;
            }
        }
        return false;
    }
    public void addItem(ItemDetails itemDetails){
        extendItemDetails();
        itemDetailsList[itemDetailsList.length-1]=itemDetails;
    }

    //For Sorting
    UnitPrice [] unitPriceList=new UnitPrice[0];
    public void extendUnitPrice(){
        UnitPrice [] temp=new UnitPrice[unitPriceList.length+1];
        for (int i = 0; i < unitPriceList.length; i++) {
            temp[i]=unitPriceList[i];
        }
        unitPriceList = temp;
    }
    public void addUnitPrice(UnitPrice unitPrice){
        extendUnitPrice();
        unitPriceList[unitPriceList.length-1]=unitPrice;
    }
    public  void unitPriceSorting(){
        for (int i = 0; i < itemDetailsList.length; i++){
            UnitPrice unitPrice=new UnitPrice();
            unitPrice.indexOfItemDetails=i;
            unitPrice.price=(int)itemDetailsList[i].unitPrice;
            addUnitPrice(unitPrice);
        }

        for (int i = 0; i < unitPriceList.length; i++) {
            for (int j = 0; j < unitPriceList.length - 1; j++) {
                if (unitPriceList[j].price > unitPriceList[j + 1].price) {
                    UnitPrice temp=unitPriceList[j];
                    unitPriceList[j]=unitPriceList[j+1];
                    unitPriceList[j+1]=temp;
                }
            }
        }
    }

    //For Updateing
    public void updateItemDetailsListSupplier(int index){
        for (int i = 0; i < itemDetailsList.length; i++) {
            if (itemDetailsList[i].supplierIndexId == index){
                itemDetailsList[i].supplierIndexId=-1;
            } else if (itemDetailsList[i].supplierIndexId>index) {
                itemDetailsList[i].supplierIndexId= itemDetailsList[i].supplierIndexId-1;
            }
        }
    }
    public void updateItemDetailsListCategory(int index){
        for (int i = 0; i < itemDetailsList.length; i++) {
            if (itemDetailsList[i].categoryIndexId == index) {
                itemDetailsList[i].categoryIndexId = -1;
            } else if (itemDetailsList[i].categoryIndexId>index) {
                itemDetailsList[i].categoryIndexId= itemDetailsList[i].categoryIndexId-1;
            }
        }
    }

}

public class Main {

    private static Scanner input = new Scanner(System.in);

    //Password
    private static String username = "admin";
    private static String password = "123";

    //Objects

    private static SupplierCollection supCollection = new SupplierCollection();
    private static CategoryCollection catCollection = new CategoryCollection();
    private static ItemDetailsCollection itdCollection = new ItemDetailsCollection();

    public static String isExistSuppliers(int index){
        if (index==-1){
            return null;
        }
        return supCollection.suppliersList[index].id;
    }
    public static String isExistCategories(int index){
        if (index==-1){
            return  null;
        }
        else {
            return catCollection.categoryList[index].category;
        }
    }
    public static void searchSupplierTable(int index){
        boolean flag=false;
        for (int i = 0; i < supCollection.suppliersList.length; i++) {
            if ((itdCollection.itemDetailsList[i].supplierIndexId) == index) {
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
            System.out.printf("| %12s    | %15s    |%13s   |%14s   |%12s    |%n","ITEM CODE" , "DESCRIPTION","UNIT PRICE","QTY ON HAND","CATEGORY");
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
            for (int i = 0; i <itdCollection.itemDetailsList.length; i++) {
                if (itdCollection.itemDetailsList[i].supplierIndexId == index) {
                    System.out.printf("| %10s      | %13s      |%13s   |%12s     |%12s    |%n", itdCollection.itemDetailsList[i].itemId, itdCollection.itemDetailsList[i].description, itdCollection.itemDetailsList[i].unitPrice, itdCollection.itemDetailsList[i].qty, isExistCategories(itdCollection.itemDetailsList[i].categoryIndexId));
                }
            }
            System.out.printf("+-----------------+--------------------+----------------+-----------------+----------------+%n");
        }
        else {
            System.out.println();
            System.out.println("Its seems that the "+ supCollection.suppliersList[index].name+" does not provide any items at the moment.");
        }
        System.out.println();
    }
    public static void itemCategoriesTable(){
        System.out.printf("+-------------------------+--------------------------+%n");
        System.out.printf("| %13s           | %18s       |%n", "#","CATEGORY NAME");
        System.out.printf("+-------------------------+--------------------------+%n");
        for (int i = 0; i < catCollection.categoryList.length; i++){
            System.out.printf("| %13s           |          %-16s|%n", (i+1), catCollection.categoryList[i].category);
        }
        System.out.printf("+-------------------------+--------------------------+%n");
    }
    public static void suppliersTable(){
        System.out.printf("+-----------------+-------------------------+--------------------------+%n");
        System.out.printf("| %8s        | %17s       | %18s       |%n","#" , "SUPPLIER ID","SUPPLIER NAME");
        System.out.printf("+-----------------+-------------------------+--------------------------+%n");
        for (int i = 0; i < supCollection.suppliersList.length; i++) {
            System.out.printf("| %8s        | %14s          |        %-18s|%n",(i+1),supCollection.suppliersList[i].id,supCollection.suppliersList[i].name);
        }
        System.out.printf("+-----------------+-------------------------+--------------------------+%n");

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

    //Stock Manage methods
    public static void rankedUnitPrice(){
        clearConsole();
        printTitle("RANKED UNIT PRICE");

        if (itdCollection.itemDetailsList.length==0){
            System.out.println("There are no items in the system at the moment.");
        }
        else {
            itdCollection.unitPriceSorting();
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
            System.out.printf("| %8s      | %8s     |%11s      |%11s     |%9s      |%9s      |%n", "SID", "CODE", "DESC", "PRICE", "QTY","CAT");
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
            for (int i = 0; i < itdCollection.unitPriceList.length; i++) {
                System.out.printf("| %8s      | %8s     |%13s    |%11s     |%9s      |%10s     |%n", isExistSuppliers(itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].supplierIndexId), itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].itemId, itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].description, itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].unitPrice, itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].qty, isExistCategories(itdCollection.itemDetailsList[itdCollection.unitPriceList[i].indexOfItemDetails].categoryIndexId));
            }
            System.out.printf("+---------------+--------------+-----------------+----------------+---------------+---------------+%n");
        }

        //Clear up the unit price array
        itdCollection.unitPriceList = new UnitPrice[0];
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
        if (catCollection.categoryList.length==0){
            System.out.println("There are no categories in the system at the moment! ");
        }

        for (int i = 0; i < catCollection.categoryList.length; i++) {
            System.out.println(catCollection.categoryList[i].category + ": ");
            System.out.printf("+---------------+--------------+----------------+----------------+---------------+%n");
            System.out.printf("| %8s      | %8s     |%10s      |%11s     |%9s      |%n", "SID", "CODE", "DESC", "PRICE", "QTY");
            System.out.printf("+---------------+--------------+----------------+----------------+---------------+%n");

            boolean flag = false;
            for (int j = 0; j < itdCollection.itemDetailsList.length; j++) {
                if (itdCollection.itemDetailsList[j].categoryIndexId == i) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                for (int j = 0; j < itdCollection.itemDetailsList.length; j++) {
                    if (itdCollection.itemDetailsList[j].categoryIndexId  == i) {
                        System.out.printf("| %8s      | %8s     |%10s      |%11s     |%9s      |%n", isExistSuppliers(itdCollection.itemDetailsList[j].supplierIndexId), itdCollection.itemDetailsList[j].itemId, itdCollection.itemDetailsList[j].description, itdCollection.itemDetailsList[j].unitPrice, itdCollection.itemDetailsList[j].qty);
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
    public static void searchSuppliers(){
        clearConsole();
        printTitle("SEARCH SUPPLIER");

        if (supCollection.suppliersList.length==0){
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();

                if (supCollection.isSupplerExists(id)) {
                    int index= supCollection.findIndex(id);
                    System.out.println("Supplier Name: " + supCollection.suppliersList[index].name);
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
            if (catCollection.categoryList.length==0){
                System.out.println("OOPS! It seems that you don't have any item categories in the system.");
                String text="Do You want to add a new item category? (Y/N)?";
                char select=validation(text);
                if (select=='Y'||select=='y'){
                    addItemCategory();
                }
                break;
            } else if (supCollection.suppliersList.length==0) {
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

                if (!itdCollection.isItemExists(itemCode)){
                    ItemDetails itemDetail=new ItemDetails();
                    itemDetail.itemId=itemCode;
                    suppliersTable();
                    do {
                        int number=readIntegerInput("Enter the supplier number > ");
                        if ((0<number) && (number <= supCollection.suppliersList.length)){
                            itemDetail.supplierIndexId= number-1;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid input! ");
                        }
                    }while (true);
                    itemCategoriesTable();
                    do {
                        int number=readIntegerInput("Enter the category number > ");
                        if (( 0< number) && (number <=catCollection.categoryList.length)){
                            itemDetail.categoryIndexId= number-1;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid category number! ");
                        }
                    }while (true);
                    System.out.print("Description : ");
                    itemDetail.description= input.next();
                    do {
                        double number= readDoubleInput("Unit price : ");
                        if (0<number){
                            itemDetail.unitPrice= number;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid Unit price! ");
                        }
                    }while (true);
                    do {
                        int number=readIntegerInput("Qty on hand : ");
                        if (0<=number){
                            itemDetail.qty= number;
                            break;
                        }
                        else {
                            System.out.println("Enter A valid Qty! ");
                        }
                    }while (true);
                    itdCollection.addItem(itemDetail);
                    System.out.println("Added successfully!");
                    String text="Do You want to add another item (Y/N)?";
                    char select=validation(text);
                    if (select=='N'||select=='n'){
                        break;
                    }
                }
                else {
                    System.out.println("Item Code Already Exists. Try another item code!");
                }
                System.out.println();
            }
        }while (true);
    }

    //Manage Item Category

    public static void deleteItemCategory(){
        clearConsole();
        printTitle("DELETE ITEM CATEGORY");

        do {
            if (catCollection.categoryList.length==0){
                noCategoriesMassage();
                break;
            }
            else {
                System.out.print("Enter the name of the item category : ");
                String category = input.next();
                if (catCollection.isCategoryExists(category)) {
                    int index=catCollection.findIndex(category);
                    catCollection.narrowCategories(index);
                    itdCollection.updateItemDetailsListCategory(index);
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
    public static void updateItemCategory(){
        clearConsole();
        printTitle("UPDATE ITEM CATEGORY");

        if (catCollection.categoryList.length==0){
            noCategoriesMassage();
        }
        else {
            do {
                System.out.print("Enter the name of the item category  : ");
                String category = input.next();

                if (catCollection.isCategoryExists(category)) {
                    int index=catCollection.findIndex(category);
                    System.out.print("Enter the new item name: ");
                    catCollection.categoryList[index].category = input.next();
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
    public static void addItemCategory(){
        clearConsole();
        printTitle("ADD ITEM CATEGORY");

        do {
            System.out.print("Add the new item category  : ");
            String categoryName = input.next();

            if (!catCollection.isCategoryExists(categoryName)){
                Category categories= new Category();
                categories.category = categoryName;
                catCollection.addCategory(categories);
                System.out.print("added successfully!");
                //For checking input validation
                String text="Do You want to add another category (Y/N)?";
                char select=validation(text);
                if (select=='N'||select=='n'){
                    break;
                }
            }
            else {
                System.out.println("Category Already Exists. Try another category!");
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
    public static void searchSupplier(){
        clearConsole();
        printTitle("SEARCH SUPPLIERS");

        if (supCollection.suppliersList.length==0){
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();
                if (supCollection.isSupplerExists(id)){
                    int index=supCollection.findIndex(id);
                    System.out.println("Supplier Name: "+ supCollection.suppliersList[index].name);
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

        if (supCollection.suppliersList.length==0){
            System.out.println("There are no suppliers in the system at the moment.");
        }
        else{
            System.out.printf("+-------------------------+--------------------------+%n");
            System.out.printf("| %17s       | %18s       |%n", "SUPPLIER ID","SUPPLIER NAME");
            System.out.printf("+-------------------------+--------------------------+%n");
            for (int i = 0; i < supCollection.suppliersList.length; i++) {
                System.out.printf("| %14s          |          %-16s|%n", supCollection.suppliersList[i].id, supCollection.suppliersList[i].name);
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
            if (supCollection.suppliersList.length==0){
                noSupplierMassage();
                break;
            }else {
                System.out.print("Supplier ID : ");
                String id = input.next();

                if (supCollection.isSupplerExists(id)) {
                    int index=supCollection.findIndex(id);
                    supCollection.narrowSupplier(index);
                    itdCollection.updateItemDetailsListSupplier(index);
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

        if (supCollection.suppliersList.length==0) {
            noSupplierMassage();
        }
        else {
            do {
                System.out.print("Supplier ID : ");
                String id = input.next();
                if (supCollection.isSupplerExists(id)) {
                    int index=supCollection.findIndex(id);
                    System.out.println("Supplier Name: " + supCollection.suppliersList[index].name);
                    System.out.println();
                    System.out.print("Enter the new supplier name: ");
                    supCollection.suppliersList[index].name = input.next();
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

            if (!supCollection.isSupplerExists(id)){
                Supplier supplier = new Supplier();
                supplier.id=id;
                System.out.print("Enter Supplier Name: ");
                supplier.name= input.next();
                supCollection.addSupplier(supplier);
                System.out.print("added successfully!");

                //For checking input validation
                String text="Do You want to add another supplier (Y/N)?";
                char select=validation(text);
                if (select=='N'||select=='n'){
                    break;
                }
            }
            else {
                System.out.println("ID Already Exists. Try another supplier id!");
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
                    searchSuppliers();
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
                    searchSupplier();
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
