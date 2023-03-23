package fetchData;

import java.util.List;
import java.util.Scanner;

import datastorage.DatabaseHandler;
import dataretrieval.APIHandler;

public class UniversityApp {
    private static final Scanner inputScanner = new Scanner(System.in);
    
    private static final DatabaseHandler dbHandler = new DatabaseHandler();
    private static final APIHandler apiHandler = new APIHandler(dbHandler);

    public static void main(String[] args) {
        boolean exitApp = false;
        while (!exitApp) {
            displayMenu();
            String userOption = inputScanner.nextLine();
            switch (userOption) {
                case "1":
                    setupDatabase();
                    break;
                case "2":
                    createDatabaseBackup();
                    break;
                case "3":
                    clearTables();
                    break;
                case "4":
                    retrieveDataFromAPI();
                    break;
                case "5":
                    loadDataFromDatabase();
                    break;
                case "6":
                    exportDataToFile();
                    break;
                case "7":
                    showUniversitiesByCountry();
                    break;
                case "8":
                    exitApp = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Setup Database");
        System.out.println("2. Create Database Backup");
        System.out.println("3. Clear Tables");
        System.out.println("4. Retrieve Data from API");
        System.out.println("5. Load Data from Database");
        System.out.println("6. Export Data to File");
        System.out.println("7. Show Universities by Country");
        System.out.println("8. Exit");
        System.out.print("Please enter your option: ");
    }

    private static void setupDatabase() {
        System.out.print("Please enter the database name: ");
        String databaseName = inputScanner.nextLine();
        dbHandler.createDatabase(databaseName);
    }

    private static void createDatabaseBackup() {
        System.out.print("Please enter the backup file name: ");
        String backupFileName = inputScanner.nextLine();
        dbHandler.saveDatabaseBackup(backupFileName);
    }

    private static void clearTables() {
        dbHandler.deleteAllTables();
    }

    private static void retrieveDataFromAPI() {
        System.out.print("Please enter the country name: ");
        String country = inputScanner.nextLine();
        apiHandler.getUniversitiesByCountry(country);
    }

    private static void loadDataFromDatabase() {
        System.out.print("Please enter the country name: ");
        String country = inputScanner.nextLine();
        dbHandler.displayUniversitiesByCountry(country);
    }

    private static void exportDataToFile() {
        System.out.print("Please enter the file name: ");
        String outputFileName = inputScanner.nextLine();
        dbHandler.exportDataToFile(outputFileName);
    }

    private static void showUniversitiesByCountry() {
        System.out.print("Please enter the country name: ");
        String country = inputScanner.nextLine();
        dbHandler.displayUniversitiesByCountry(country);
    }

    private static void displayAllUniversities() {
        List<University> universityList = dbHandler.fetchAllUniversities();
        for (University uni : universityList) {
            System.out.println(uni);
        }
    }
}
