import java.awt.*;
import java.awt.event.*;

public class LabActivity4EmpInfoSystemGUI extends Frame implements ActionListener {

    // GUI Components
    Label lblFirstName, lblLastName, lblAge, lblHoursWorked, lblHourlyRate, lblOutput;
    TextField txtFirstName, txtLastName, txtAge, txtHoursWorked, txtHourlyRate;
    Button btnSubmit;
    TextArea outputArea;

    public LabActivity4EmpInfoSystemGUI() {
        setTitle("Laboratory Activity 4"); // Set the window title
        setSize(450, 420); // Set the size of the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for organizing main sections
        setLocationRelativeTo(null); // Center the window on screen

        // ---------------- INPUT PANEL ----------------
        // Create a panel to hold input fields and labels
        Panel inputPanel = new Panel(new GridLayout(6, 2, 5, 5));

        // Instantiate Labels and TextFields
        lblFirstName = new Label("First Name");
        txtFirstName = new TextField();

        lblLastName = new Label("Last Name");
        txtLastName = new TextField();

        lblAge = new Label("Age");
        txtAge = new TextField();

        lblHoursWorked = new Label("Hours Worked");
        txtHoursWorked = new TextField();

        lblHourlyRate = new Label("Hourly Rate");
        txtHourlyRate = new TextField();

        // Add label and textfield pairs to the input panel
        inputPanel.add(lblFirstName); inputPanel.add(txtFirstName);
        inputPanel.add(lblLastName); inputPanel.add(txtLastName);
        inputPanel.add(lblAge); inputPanel.add(txtAge);
        inputPanel.add(lblHoursWorked); inputPanel.add(txtHoursWorked);
        inputPanel.add(lblHourlyRate); inputPanel.add(txtHourlyRate);

        // Placeholder panel to align the small submit button
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        btnSubmit = new Button("Submit");
        btnSubmit.setPreferredSize(new Dimension(60, 25)); // Set button to a fixed small size
        btnSubmit.addActionListener(this); // Register action listener
        buttonPanel.add(btnSubmit); // Add button to the button panel

        inputPanel.add(new Label("")); // Add empty label to maintain layout symmetry
        inputPanel.add(buttonPanel); // Add the small button panel

        // ---------------- OUTPUT PANEL ----------------
        Panel outputPanel = new Panel(new BorderLayout(5, 5));
        lblOutput = new Label("Output:"); // Label above the text area
        outputArea = new TextArea("", 4, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        outputArea.setEditable(false); // Make output area read-only
        outputArea.setFont(new Font("Dialog", Font.PLAIN, 12)); // Optional: set font

        outputPanel.add(lblOutput, BorderLayout.NORTH);
        outputPanel.add(outputArea, BorderLayout.CENTER);

        // ---------------- ADD PANELS TO FRAME ----------------
        add(inputPanel, BorderLayout.NORTH);  // Inputs at the top
        add(outputPanel, BorderLayout.CENTER); // Output area below

        // Handle window closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window when user clicks the close button
            }
        });

        setVisible(true); // Show the frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        outputArea.setText(""); // Clear previous output

        // Get and trim all input field values
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String ageText = txtAge.getText().trim();
        String hoursText = txtHoursWorked.getText().trim();
        String rateText = txtHourlyRate.getText().trim();

        // ---------------- INPUT VALIDATION ----------------

        // Check if any field is empty
        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty()
                || hoursText.isEmpty() || rateText.isEmpty()) {
            outputArea.setText("Error: All fields must be filled out.");
            return;
        }

        int age;
        double hours, rate;

        // Validate age is an integer
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Age must be a valid integer.");
            return;
        }

        // Validate hours worked and hourly rate are valid numbers
        try {
            hours = Double.parseDouble(hoursText);
            rate = Double.parseDouble(rateText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Hours worked and hourly rate must be valid numbers.");
            return;
        }

        // ---------------- COMPUTATION & OUTPUT ----------------

        String fullName = firstName + " " + lastName; // Concatenate names
        double salary = hours * rate; // Calculate daily wage

        // Display the result
        outputArea.setText("Full Name: " + fullName + "\n"
                         + "Age: " + age + " years old\n"
                         + String.format("Daily Salary: PHP %.2f", salary));
    }

    public static void main(String[] args) {
        new LabActivity4EmpInfoSystemGUI(); // Launch the application
    }
}
