import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;


public class App {

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    static int getInt(Scanner inputScanner, String prompt) {
        while(true) {
            System.out.print(prompt);
            if (inputScanner.hasNextInt()) {
                return inputScanner.nextInt();
            }

            System.out.println(inputScanner.nextLine() + " is not valid! Enter an integer.");
        }
    }

    static float getFloat(Scanner inputScanner, String prompt) {
        while(true) {
            System.out.print(prompt);
            if (inputScanner.hasNextFloat()) {
                return inputScanner.nextFloat();
            }

            System.out.println(inputScanner.nextLine() + " is not valid! Enter a floating point number.");
        }
    }

    public static void updateBoiler(Scanner inputScanner, Connection connection) throws SQLException {
        inputScanner.nextLine();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM boiler;");

        clearScreen();
        System.out.println("**************************************************************");
        System.out.println("\tBoiler");
        while (resultSet.next()) {
            System.out.println("---------------------------------------------------------");
            System.out.println(
                "boiler id:\t\t\t" + resultSet.getInt("boiler_ID") + "\n"
                + "unit id:\t\t\t" + resultSet.getInt("unit_ID") + "\n"
                + "name:\t\t\t\t" + resultSet.getString("name") + "\n"
                + "purchase date:\t\t\t" + resultSet.getString("purchase_date") + "\n"
                + "purchase price:\t\t\t" + resultSet.getFloat("purchase_price") + "\n"
                + "functionality description:\t" + resultSet.getString("functionality_description") + "\n"
                + "location:\t\t\t" + resultSet.getString("location") + "\n"
                + "boiler status:\t\t\t" + resultSet.getString("boiler_status") + "\n"
                + "production date:\t\t" + resultSet.getString("production_date")
            );
        }
        System.out.println("**************************************************************");
        
        int boilerID = getInt(inputScanner, "boiler id: ");
        int unitID = getInt(inputScanner, "unit id: ");
        
        System.out.print("name: ");
        String name = inputScanner.nextLine();
        inputScanner.nextLine();
        
        System.out.print("purchase date: ");
        String purchaseDate = inputScanner.nextLine();
        inputScanner.nextLine();
        
        float purchasePrice = getFloat(inputScanner, "purchase price: ");
        
        System.out.print("functionality description: ");
        String description = inputScanner.nextLine();
        inputScanner.nextLine();
        
        System.out.print("location: ");
        String location = inputScanner.nextLine();
        inputScanner.nextLine();

        System.out.print("boiler status: ");
        String status = inputScanner.nextLine();
        inputScanner.nextLine();

        System.out.print("production date: ");
        String productionDate = inputScanner.nextLine();
        inputScanner.nextLine();

        String sql = "UPDATE boiler SET "
            + "unit_ID = " + unitID + ", "
            + "name = \"" + name + "\", "
            + "purchase_date = \"" + purchaseDate + "\", "
            + "purchase_price = " + purchasePrice + ", "
            + "functionality_description = \"" + description + "\", "
            + "location = \"" + location + "\", "
            + "boiler_status = \"" + status + "\", "
            + "production_date = \"" + productionDate + "\" "
            + "WHERE boiler_ID = " + boilerID;

        statement.executeUpdate(sql);
        resultSet = statement.executeQuery(
            "SELECT * FROM boiler WHERE boiler_ID = " + boilerID
        );

        System.out.println("**************************************************************");
        System.out.println("\tUpdated Rows:");
        while (resultSet.next()) {
            System.out.println("---------------------------------------------------------");
            System.out.println(
                "boiler id:\t\t\t" + resultSet.getInt("boiler_ID") + "\n"
                + "unit id:\t\t\t" + resultSet.getInt("unit_ID") + "\n"
                + "name:\t\t\t\t" + resultSet.getString("name") + "\n"
                + "purchase date:\t\t\t" + resultSet.getString("purchase_date") + "\n"
                + "purchase price:\t\t\t" + resultSet.getFloat("purchase_price") + "\n"
                + "functionality description:\t" + resultSet.getString("functionality_description") + "\n"
                + "location:\t\t\t" + resultSet.getString("location") + "\n"
                + "boiler status:\t\t\t" + resultSet.getString("boiler_status") + "\n"
                + "production date:\t\t" + resultSet.getString("production_date")
            );
        }
        System.out.println("**************************************************************");
        inputScanner.nextLine();
        System.out.println("Press enter to return to Updates menu...");
        inputScanner.nextLine();
        resultSet.close();  
    }

    public static void updateCoal(Scanner inputScanner, Connection connection) throws SQLException {
        inputScanner.nextLine();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM coal;");

        clearScreen();
        System.out.println("**************************************************************");
        System.out.println("\tCoal");
        while (resultSet.next()) {
            System.out.println("---------------------------------------------------------");
            System.out.println(
                "coal_type:\t\t\t" + resultSet.getString("coal_type") + ",\n"
                + "sulfur_content:\t\t\t" + resultSet.getString("sulfur_content") + ",\n"
                + "unit_cost:\t\t\t" + resultSet.getFloat("unit_cost") + ",\n"
                + "price_per_unit_allowance:\t" + resultSet.getFloat("price_per_unit_allowance") + ",\n"
                + "scrubber_rate:\t\t\t" + resultSet.getFloat("scrubber_rate") + ",\n"
                + "heat_rate:\t\t\t" + resultSet.getFloat("heat_rate") + ",\n"
                + "coal_burn_rate:\t\t\t" + resultSet.getFloat("coal_burn_rate") + ",\n"
                + "emittion_rate:\t\t\t" + resultSet.getFloat("emittion_rate")
            );
        }
        System.out.println("**************************************************************");
        
        System.out.print("Select a Coal Type: ");
        String coalType = inputScanner.nextLine();
        System.out.print("sulfur content: ");
        String sulfurContent = inputScanner.nextLine();
        float unitCost = getFloat(inputScanner, "unit cost: ");
        float pricePerUnitAllowance = getFloat(inputScanner, "price per unit allowance: ");
        float scrubberRate = getFloat(inputScanner, "scrubber rate: ");
        float heatRate = getFloat(inputScanner, "heat rate: ");
        float burnRate = getFloat(inputScanner, "burn rate: ");
        float emittionRate = getFloat(inputScanner, "emittion rate: ");

        String sql = "UPDATE coal SET "
            + "sulfur_content = \"" + sulfurContent + "\", "
            + "unit_cost = " + unitCost + ", "
            + "price_per_unit_allowance = " + pricePerUnitAllowance + ", "
            + "scrubber_rate = " + scrubberRate + ", "
            + "heat_rate = " + heatRate + ", "
            + "coal_burn_rate = " + burnRate + ", "
            + "emittion_rate = " + emittionRate + " "
            + "WHERE coal_type = \"" + coalType + "\"";

        statement.executeUpdate(sql);
        resultSet = statement.executeQuery(
            "SELECT * FROM coal WHERE coal_type = \"" + coalType + "\""
        );

        System.out.println("**************************************************************");
        System.out.println("\tUpdated Rows:");
        while (resultSet.next()) {
            System.out.println("---------------------------------------------------------");
            System.out.println(
                "coal_type:\t\t\t" + resultSet.getString("coal_type") + ",\n"
                + "sulfur_content:\t\t\t" + resultSet.getString("sulfur_content") + ",\n"
                + "unit_cost:\t\t\t" + resultSet.getFloat("unit_cost") + ",\n"
                + "price_per_unit_allowance:\t" + resultSet.getFloat("price_per_unit_allowance") + ",\n"
                + "scrubber_rate:\t\t\t" + resultSet.getFloat("scrubber_rate") + ",\n"
                + "heat_rate:\t\t\t" + resultSet.getFloat("heat_rate") + ",\n"
                + "coal_burn_rate:\t\t\t" + resultSet.getFloat("coal_burn_rate") + ",\n"
                + "emittion_rate:\t\t\t" + resultSet.getFloat("emittion_rate")
            );
        }
        System.out.println("**************************************************************");
        inputScanner.nextLine();
        System.out.println("Press enter to return to Updates menu...");
        inputScanner.nextLine();
        resultSet.close();  
    }

    public static void updatePlant(Scanner inputScanner, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from plant");
        
        int nRows = 0;
        while(resultSet.next()){
            System.out.print(resultSet.getInt(1) + " " + resultSet.getString(2) + ", ");
            nRows++;
        }
        System.out.println();

        int plantId = -1;
        while(true) {
            System.out.print("Select an existing Plant ID: ");
            if (inputScanner.hasNextInt()) {
                plantId = inputScanner.nextInt();
                if (plantId >= 0 && plantId < nRows) {
                    break;
                }
            }
            
            System.out.print(inputScanner.nextLine() + " is not a valid Plant Id!");
        }

        resultSet = statement.executeQuery("select * from plant where plant_ID=" + plantId );
        String address = "";
        String type = "";
        String manager = "";
        String telephone = "";
        String units = "";

        while(resultSet.next()){
            System.out.println("Update id: " + plantId + " for Plant: " + resultSet.getString(2) + ": " );
            for(int i = 3; i < 7; i++){
                System.out.print("Update: " + resultSet.getString(i) + ": " );
                if (i == 3) {
                    Scanner scanner1 = new Scanner(System.in);
                    boolean isAddrNotValid = true;
                    while(isAddrNotValid) {
                        if (scanner1.hasNextLine()) {
                            address = scanner1.nextLine();
                            if(!address.isEmpty()){
                                if(address.equals("same")){
                                    address = "";
                                } else {
                                    String temp = "address='" + address + "',";
                                    address = temp;
                                }
                                isAddrNotValid = false;
                            } else {
                                System.out.println("Enter new address or press Enter if no change");
                            }
                        }
                    }
                }

                if (i == 4) {
                    Scanner scanner2 = new Scanner(System.in);
                    boolean isTypeNotValid = true;
                    while(isTypeNotValid) {
                        if (scanner2.hasNextLine()) {
                            type = scanner2.nextLine();
                            if(!type.isEmpty()){
                                if(type.equals("same")){
                                    type = "";
                                } else {
                                    String temp = "type='" + type + "',";
                                    type = temp;
                                }
                                isTypeNotValid = false;
                            } else {
                                System.out.println("Enter new type or press Enter if no change");
                            }
                        }
                    }
                }

                if (i == 5) {
                    Scanner scanner3 = new Scanner(System.in);
                    boolean isMgrNotValid = true;
                    while(isMgrNotValid) {
                        if (scanner3.hasNextLine()) {
                            manager = scanner3.nextLine();
                            if(!manager.isEmpty()){
                                if(manager.equals("same")){
                                    manager = "";
                                } else {
                                    String temp = "manager_name='" + manager + "',";
                                    manager = temp;
                                }
                                isMgrNotValid = false;
                            } else {
                                System.out.println("Enter new manager or press Enter if no change");
                            }
                        }
                    }
                }

                if (i == 6) {
                    Scanner scanner4 = new Scanner(System.in);
                    boolean isTelNotValid = true;
                    while(isTelNotValid) {
                        if (scanner4.hasNextLine()) {
                            telephone = scanner4.nextLine();
                            if(!telephone.isEmpty()){
                                if(telephone.equals("same")){
                                    telephone = "";
                                } else {
                                    String temp = "manager_contact_number='" + telephone + "',";
                                    telephone = temp;
                                }
                                isTelNotValid = false;
                            } else {
                                System.out.println("Enter new phone number or enter 'same' if no change");
                            }
                        }
                    }
                }
            }

            Scanner scanner5 = new Scanner(System.in);
            boolean isUnitNotValid = true;
            while(isUnitNotValid) {
                if (scanner5.hasNextLine()) {
                    units = scanner5.nextLine();
                    if(!units.isEmpty()){
                        if(units.equals("same")){
                            units = "";
                        } else {
                            String temp = "number_units=" + units + ",";
                            units = temp;
                        }
                        isUnitNotValid = false;
                    } else {
                        System.out.println("Enter new number of units or enter 'same' if no change");
                    }
                }
            }
        }
        String fields = address + type + manager + telephone + units;
        fields = fields.substring(0, fields.length() - 1);
        String cmdString = "update plant Set " + fields + " where plant_ID=" + plantId;
        System.out.println(cmdString);

        statement = connection.createStatement();
        statement.executeUpdate(cmdString);
    }

    public static void updatesMenu(Scanner inputScanner) throws SQLException {
        boolean bPrintMenu = true;
        boolean bCancel = false;
        Connection connection = login(inputScanner, "jdbc:mysql://localhost:3306/groupproject?");

        while (!bCancel) {
            if (bPrintMenu) {
                clearScreen();
                System.out.println("******************************************************************");
                System.out.println("\tUpdates");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("1) Insert New Information");
                System.out.println("2) Delete Existing Information");
                System.out.println("3) Update Existing Information");
                System.out.println("0) Cancel");
                System.out.println("******************************************************************");
                bPrintMenu = false;
            }

            System.out.print("Choose an option: ");
            if (!inputScanner.hasNextInt()) {
                System.out.println(inputScanner.nextLine() + " is not a valid option!");
                continue;
            }

            switch (inputScanner.nextInt()) {
                case 1: // Insert New Information
                    System.out.println("Feature not implemented!");
                    break;
                case 2: // Delete Existing Information
                    System.out.println("Feature not implemented!");
                    break;
                case 3: // Update Existing Information
                    updateInfoMenu(inputScanner, connection);
                    bPrintMenu = true;
                    break;
                case 0: // Cancel
                    bCancel = true;
                    break;
                default:
                    System.out.println("That is not a valid option!");
                    break;
            }
        }

        connection.close();
    }
     
    public static void updateInfoMenu(Scanner inputScanner, Connection connection) {
        boolean bPrintMenu = true;
        boolean bCancel = false;

        while (!bCancel) {
            if (bPrintMenu) {
                clearScreen();
                System.out.println("******************************************************************");
                System.out.println("\tUpdate Existing Information");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("1) Update a specific Plant");
                System.out.println("2) Update a specific Boiler");
                System.out.println("3) Update a specific Coal");
                System.out.println("0) Cancel");
                System.out.println("******************************************************************");
                bPrintMenu = false;
            }

            System.out.print("Choose an option: ");
            if (!inputScanner.hasNextInt()) {
                System.out.println(inputScanner.nextLine() + " is not a valid option!");
                continue;
            }

            switch (inputScanner.nextInt()) {
                case 1: // Update a scpecific Plant
                    try {
                        updatePlant(inputScanner, connection);
                        bPrintMenu = true;
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                    }
                    break;
                case 2: // Update a scpecific Boiler
                    try {
                        updateBoiler(inputScanner, connection);
                        bPrintMenu = true;
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                    }
                    break;
                case 3: // Update a scpecific Coal
                    try {
                        updateCoal(inputScanner, connection);
                        bPrintMenu = true;
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                    }
                    break;
                case 0: // Cancel
                    bCancel = true;
                    break;
                default:
                    System.out.println("That is not a valid option!");
                    break;
            }
        }
    }

    public static void mainMenu() {
        Scanner inputScanner = new Scanner(System.in);
        boolean bPrintMenu = true;
        boolean bCancel = false;

        while (!bCancel) {
            if (bPrintMenu) {
                clearScreen();
                System.out.println("*****************************************************************************");
                System.out.println("                                ************                                 ");
                System.out.println("          Welcome to the DSS for Electric Utilities Company: Compliance");
                System.out.println("                            with Clean Air Act");
                System.out.println("                                ************                                 ");
                System.out.println("*****************************************************************************");
                System.out.println("                         1. Plants, Units & Boilers");
                System.out.println("                                   2. Coal");
                System.out.println("                        3. Statistics and Data Analysis");
                System.out.println("                                  4. Updates");
                System.out.println("                                    0. Quit");
                System.out.println("*****************************************************************************");
                bPrintMenu = false;
            }

            System.out.print("Choose an option: ");
            if (!inputScanner.hasNextInt()) {
                System.out.println(inputScanner.nextLine() + " is not a valid option!");
                continue;
            }

            switch (inputScanner.nextInt()) {
                case 1: // Plants, Units & Boilers
                    System.out.println("Feature not implemented!");    
                    // plantsUnitsBoilersMenu();
                    // bPrintMenu = true;
                    break;
                case 2: // Coal
                    System.out.println("Feature not implemented!");    
                    // coalMenu();
                    // bPrintMenu = true;
                    break;
                case 3: // Statistics and Data Analysis
                    System.out.println("Feature not implemented!");
                    // statisticsMenu();
                    // bPrintMenu = true;
                    break;
                case 4: // Updates
                    try {
                        updatesMenu(inputScanner);
                        bPrintMenu = true;
                    } catch (SQLException e) {
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: " + e.getSQLState());
                        System.out.println("VendorError: " + e.getErrorCode());
                    }
                    break;
                case 0: // Quit
                    bCancel = true;
                    break;
                default:
                    System.out.println("That is not a valid option!");
                    break;
            }
        }

        inputScanner.close();
    }

    public static Connection login(Scanner inputScanner, String url) throws SQLException {
        inputScanner.nextLine();
        System.out.print("Enter User Id: ");
        String user = inputScanner.nextLine();
        System.out.print("Enter password: ");
        String password = inputScanner.nextLine();
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.out.println("ClassNotFoundException: " + e.getMessage());
            System.exit(1);
        }

        mainMenu();
    }
}
