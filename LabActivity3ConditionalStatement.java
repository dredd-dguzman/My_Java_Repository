import java.util.Scanner;

public class LabActivity3ConditionalStatement {
 
    public static void main(String[] args) {
        // Defines the Scanner Module for User Inputs
        Scanner scanner = new Scanner(System.in);

        // Prompts the User to Enter First Name (String Input)
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        // Prompts the User to Enter Last Name (String Input)
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        // Prompts the User to Enter Age (Integer Input)
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Age Validation
        if (age < 18) {
            System.out.println("Minors are not allowed");
            return;
        } else if (age >= 65) {
            System.out.println("Senior Citizens are not allowed");
            return;
        }
        
        // Prompts the User to Enter Hours Worked (Float Input)
        System.out.print("Enter hours worked per day: ");
        float hoursWorked = scanner.nextFloat();

        // Work Hours Validation
        if (hoursWorked > 24) {
            System.out.println("Number of hours worked cannot exceed 24 hours");
            return;
        } else if (hoursWorked <= 0) {
            System.out.println("Wrong input on daily work hours");
            return;
        }
        
        // Prompts the User to Enter Hourly Wage (Float Input)
        System.out.print("Enter hourly wage: ");
        float hourlyWage = scanner.nextFloat();

        System.out.print("Enter role code (1-Manager, 2-Supervisor, 3-Staff, 4-Intern): ");
        int roleCode = scanner.nextInt();
        
        // Assign Role Based on Switch Case
        String role;
        switch (roleCode) {
            case 1: role = "Manager"; break;
            case 2: role = "Supervisor"; break;
            case 3: role = "Staff"; break;
            case 4: role = "Intern"; break;
            default: role = "Undefined"; break;
        }

        // Salary Computations
        // Calculates the Daily Salary by Multiplying the Number of Hours Worked by the Hourly Wage
        // The Value is Rounded to the Nearest Whole Number
        float dailySalary = Math.round(hoursWorked * hourlyWage);
        // Calculates the Weekly Salary by Multiplying the Daily Salary by 5 (Assuming a 5-day Workweek)
        float weeklySalary = dailySalary * 5;
        // Calculates the Monthly Salary by Multiplying the Weekly Salary by 4 (4 Weeks per Month Assumption)
        float monthlySalary = weeklySalary * 4;
        // Calculates the Gross Yearly Salary by Multiplying the Monthly Salary by 12 (12 Months Per Year)
        float grossYearlySalary = monthlySalary * 12;

        // Computes the Total Deductions, Which Includes:
        // - 32% Tax of the Gross Yearly Salary (if gross salary > 250,000)
        // - Government-mandated Benefits Amounting to Php 1,500.00
        // - No Tax Applied if Gross Yearly Salary is 250,000 or Below
        // I Used a Ternary Operator for Better Readability
        float deductions = (float) ((grossYearlySalary > 250000) ? (grossYearlySalary * 0.32 + 1500) : 1500);
        float netYearlySalary = grossYearlySalary - deductions;

        // Contains, Processes, and Prints the Collected Information from User
        System.out.println("\nEmployee Information");
        System.out.println("---------------------");
        System.out.println("Full Name:                " + lastName.toUpperCase() + ", " + firstName.toUpperCase());
        System.out.println("Age:                      " + age + " years old");
        System.out.println("Position:                 " + role);
        
        // Computes years to retirement (assuming retirement at 65)
        System.out.println("Years to Retirement:      " + (65 - age) + " years");
        
        // Displays the Salaries Formatted to Two Decimal Places
        System.out.printf("Daily Salary:             PHP %.2f\n", dailySalary);
        System.out.printf("Weekly Salary:            PHP %.2f\n", weeklySalary);
        System.out.printf("Monthly Salary:           PHP %.2f\n", monthlySalary);
        System.out.printf("Gross Yearly Salary:      PHP %.2f\n", grossYearlySalary);
        System.out.printf("Net Yearly Salary:        PHP %.2f\n", netYearlySalary);
        
        // Closes the Scanner to Prevent Resource or Data Leak
        scanner.close();
    }
}
