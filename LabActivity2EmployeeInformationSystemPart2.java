// Java Scanner Importer for User Input
import java.util.Scanner;

public class LabActivity2EmployeeInformationSystemPart2 {
    
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
        
        // Formulas for the Computation of the Salaries  
        // Calculates the Daily Salary by Multiplying the Number of Hours Worked by the Hourly Wage
        // The value is rounded to the Nearest whole number
        float DailySalary = Math.round(HoursWorked * HourlyWage);
            
        // Calculates the Weekly Salary by Multiplying the Daily Salary by 5 (Assuming a 5-day Workweek)
        float WeeklySalary = DailySalary * 5;
            
        // Calculates the Monthly Salary by Multiplying the Weekly Salary by 4 (4 Weeks per Month Assumption)
        float MonthlySalary = WeeklySalary * 4;
            
        // Calculates the Gross Yearly Salary by Multiplying the Monthly Salary by 12 (12 Months Per Year)
        float GrossYearlySalary = MonthlySalary * 12;
        
        // Computes the Total Deductions, Which Includes:
        // - 32% Tax of the Gross Yearly Salary
        // - Government-mandated Benefits Amounting to Php 1,500.00
        float Deductions = (GrossYearlySalary * 0.32f) + 1500.00f;
        // Rounded Off the Value to the First Decimal Place to Match the Given Output
        float NetYearlySalary = Math.round((GrossYearlySalary - Deductions) * 10) /10.0f;

        // Contains, Processes, and Prints the Collected Information from User       
        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        
        // Prints the Full Name by Concatenating the First and Last Name
        System.out.println("Full Name:                " + (LastName + ", " + FirstName).toUpperCase());
        System.out.println("Age:                      " + Age + " years old");
        
        // Computes years to retirement (assuming retirement at 65)
        System.out.println("Years to Retirement:      " + Math.abs(Age - 65) + " years old");

        // Displays the Salaries Formatted to Two Decimal Places
        System.out.printf("Daily Salary:             PHP %.2f\n", DailySalary);
        System.out.printf("Weekly Salary:            PHP %.2f\n", WeeklySalary);
        System.out.printf("Monthly Salary:           PHP %.2f\n", MonthlySalary);
        System.out.printf("Gross Yearly Salary:      PHP %.2f\n", GrossYearlySalary);
        System.out.printf("Net Yearly Salary:        PHP %.2f\n", NetYearlySalary);
        
        // Closes the Scanner to Prevent Resource or Data Leak
        Scanner.close();
    }
}
