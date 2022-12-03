package CS143;

import javax.naming.BinaryRefAddr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import java.lang.String;
import java.lang.*;


public class Main {

    public static void UpdatePlant() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/groupproject?" + "user=root&password=manager");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from plant");
            int rows = 0;
            while(resultSet.next()){
                System.out.print(resultSet.getInt(1) + " " + resultSet.getString(2) + ", ");
                rows++;
            }
            System.out.println();

            Scanner inputObj = new Scanner(System.in);
            System.out.print("Choose the plant ID: ");
            boolean isNumNotValid = true;
            int input = 0;
            int plantId = -1;
            while(isNumNotValid) {
                if (inputObj.hasNextInt()) {
                    plantId = inputObj.nextInt();
                    if (plantId >= 0 && plantId < rows) {
                        isNumNotValid = false;
                        break;
                    }
                    System.out.print("Please enter valid Plant Id: ");
                } else {
                    System.out.print("Input is non-numeric. Press Enter a valid Plant Id to try again: ");
                }
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

            statement = conn.createStatement();
            statement.executeUpdate(cmdString);
            conn.close();
        }catch(SQLException sqlEx) {
            System.out.println("SQLException: " + sqlEx.getMessage());
            System.out.println("SQLState: " + sqlEx.getSQLState());
            System.out.println("VendorError: " + sqlEx.getErrorCode());
        }
    }

    public static int chooseTable(){
        Scanner inputObj = new Scanner(System.in);
        System.out.print("Choose 1, 2, or 3: ");
        boolean isNumNotValid = true;
        String input = "";
        int choice = 0;
        while(isNumNotValid) {
            if (inputObj.hasNextInt()) {
                int digit = inputObj.nextInt();
                if (digit >= 0 && digit < 4) {
                    isNumNotValid = false;
                    return digit;
                }
                System.out.print("Please enter 1 - 3 or 0 to return to main menu: ");
            } else {
                System.out.print("Input is non-numeric. Press Enter to try again: ");
                return -4;
            }
        }
        return -3;
    }

    public static int updateData() {
        System.out.println("*****************************************************************");
        System.out.println("Option A: Insert new information which includes 4 new sub options");
        System.out.println("1) Add a new Plants");
        System.out.println("2) Add a new Boiler");
        System.out.println("3) Add a new Coal");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Option B: Insert new information which includes 4 new sub options");
        System.out.println("1) Delete specific Plant");
        System.out.println("2) Delete a specific Boiler");
        System.out.println("3) Delete specific Coal");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Option C: Insert new information which includes 4 new sub options");
        System.out.println("1) Update a specific Plant");
        System.out.println("2) Update a specific Boiler");
        System.out.println("3) Update a specific Coal");
        System.out.println("******************************************************************");

        Scanner inputObj = new Scanner(System.in);
        System.out.print("Choose A, B, or C: ");
        boolean isOptionNotValid = true;
        String input = "";
        int choice = 0;
        while(isOptionNotValid) {
            if(inputObj.hasNextLine()){
                input = inputObj.nextLine();
                if(input.equals("A")) {
                    System.out.println("Insert data");
                    choice = 10;
                    choice += chooseTable();
                    isOptionNotValid = false;
                    break;
                } else if(input.equals("B")) {
                    System.out.println("Delete data");
                    choice = 20;
                    choice += chooseTable();
                    isOptionNotValid = false;
                    break;
                } else if(input.equals("C")) {
                    System.out.println("Update data");
                    choice = 30;
                    choice += chooseTable();
                    if (choice == 26){
                        continue;
                    }else {
                        isOptionNotValid = false;
                        break;
                    }
                } else if(input.equals("Q")) {
                    System.out.println("Quiting...");
                    choice = 0;
                    isOptionNotValid = false;
                    break;
                } else {
                    System.out.print("Choose A, B, C or Q to quit: ");
                }
            }
//            System.out.print("Please enter int 1 -5: ");
        }
        return choice;
    }

    public static int mainMenu(){
        System.out.println("*****************************************************************************");
        System.out.println("                                ************                                 ");
        System.out.println("          Welcome to the DSS for Electric Utilities Company: Compliance");
        System.out.println("                            with Clean Air Act");
        System.out.println("                                ************                                 ");
        System.out.println("*****************************************************************************");
        System.out.println("                          1.Plants, Units & Boilers");
        System.out.println("                                    2.Coal");
        System.out.println("                         3.Statistics and Data Analysis");
        System.out.println("                                   4.Updates");
        System.out.println("                                     5.Quit");
        Scanner inputObj = new Scanner(System.in);

        System.out.print("Type in your option: ");
        boolean isOptionNotValid = true;
        String input = "";
        int choice = 0;
        while(isOptionNotValid) {
            if(inputObj.hasNextLine()){
                input = inputObj.nextLine();
                boolean isDigit = input.chars().allMatch( Character::isDigit );
                if(isDigit) {
                    int digit = Integer.parseInt(input);
                    if(digit > 0 && digit < 6) {
                        if (digit == 5){
                            System.out.println("Quiting...");
                            System.exit(0);
                        }
                        choice = digit;
                        break;
                    }
                }
            }
            System.out.print("Please enter int 1 -5: ");
        }

        boolean isUserIdNotValid = true;
        String userid = "";
        System.out.print("Enter User Id: ");
        while(isUserIdNotValid){
            if(inputObj.hasNextLine()){
                userid = inputObj.nextLine();
                if (userid.equals("root") || userid.equals("admin")) {
                    isUserIdNotValid = false;
                } else if (userid.equals("Go Back")) {
                    return -1;
                } else {
                    System.out.print("Enter a valid User Id or 'Go Back' to return to main menu: ");
                }
            }
        }

        boolean isPasswdNotValid = true;
        String password = "";
        System.out.print("Enter password: ");
        while(isPasswdNotValid){
            if(inputObj.hasNextLine()){
                password = inputObj.nextLine();
                if (password.equals("manager")) {
                    isPasswdNotValid = false;
                    break;
                } else if (password.equals("Go Back")) {
                    return -1;
                } else {
                    System.out.print("Enter a valid password or 'Go Back' to return to main menu: ");
                }
            }
        }

        return choice;
    }

    public static void main(String[] args) {
        int choice = mainMenu();
        if (choice == -2) {
            return;
        }
        while (choice == -1){
            choice = mainMenu();
        }

        System.out.println("******************************* proceed *************************************");

        switch (choice){
            case 1:
                System.out.println("Plants, Units & Boilers");
                break;
            case 2:
                System.out.println("Coal");
                break;
            case 3:
                System.out.println("Statistics and Data Analysis");
                break;
            case 4:
                System.out.println("Updates");
                int subOption = updateData();
                if (subOption == 31)
                    System.out.println("Update a specific Plant");
                    UpdatePlant();
                if (subOption == 32)
                    System.out.println("Update a specific Boiler");
                if (subOption == 33)
                    System.out.println("Update a specific Coal");
                break;
            case 5:
                System.out.println("Quit");
                break;
            default:
                System.out.println("Quiting");
                System.exit(0);
        }

    }
}
