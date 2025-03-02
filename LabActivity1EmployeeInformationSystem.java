// Java Scanner Importer for User Input
import java.util.Scanner;

public class LabActivity1EmployeeInformationSystem {
    
    public static void main(String[] args) {
        // Defines the Scanner Module for User Inputs      
        Scanner Scanner = new Scanner(System.in);
        
        // Prompts the User to Enter First Name (String Input)
        System.out.print("Enter your first name: ");
        String FirstName = Scanner.nextLine();
        
        // Prompts the User to Enter Last Name (String Input)
        System.out.print("Enter your last name: ");
        String LastName = Scanner.nextLine();
        
        // Prompts the User to Enter Age (Integer Input)
        System.out.print("Enter your age: ");
        int Age = Scanner.nextInt(); 
        
        // Prompts the User to Enter Hours Worked (Float Input)
        System.out.print("Enter hours worked: ");
        float HoursWorked = Scanner.nextFloat();
        
        // Prompts the User to Enter Hourly Wage (Float Input)
        System.out.print("Enter hourly wage: ");
        float HourlyWage = Scanner.nextFloat();
        
        // Contains, Processes, and Prints the Collected Information from User       
        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        
        // Prints the Full Name by Concatenating the First and Last Name
        System.out.println("Full Name   : " + FirstName + " " + LastName);
        System.out.println("Age         : " + Age + " years old");
        
        // Calculates the Daily Salary Based on the Hours Worked and Hourly Wage
        Float DailySalary = HoursWorked * HourlyWage;
        
        // Displays the Daily Salary Formatted to Two Decimal Places
        System.out.printf("Daily Salary: PHP %.2f\n", DailySalary);
        
        // Closes the Scanner to Prevent Resource or Data Leak
        Scanner.close();
    }
}
