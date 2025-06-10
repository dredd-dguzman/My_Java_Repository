// Import necessary AWT packages for GUI and event handling
import java.awt.*;
import java.awt.event.*;

// Main class that extends Frame (GUI window) and implements ActionListener for handling button clicks
public class LabActivity5QuizAppAWT extends Frame implements ActionListener {

    // Array of quiz questions
    String[] questions = {
        "What is the capital of France?",
        "Which language is used for Android development?",
        "What is the result of 2 + 2 * 2?"
    };

    // 2D array of options corresponding to each question
    String[][] options = {
        {"Paris", "Rome", "Berlin", "Madrid"},
        {"Swift", "Java", "Python", "Kotlin"},
        {"6", "8", "4", "10"}
    };

    // Array of correct answers
    String[] answers = {"Paris", "Java", "6"};

    // UI Components
    Label questionLabel;         // Displays the question
    Label errorLabel;            // Displays validation or error messages
    CheckboxGroup optionsGroup;  // Groups radio-style checkboxes
    Checkbox[] optionButtons;    // Array of option checkboxes
    Button nextButton;           // Button to move to the next question

    // State variables
    int currentQuestion = 0;     // Tracks the current question index
    int score = 0;               // Tracks the user's score

    // Constructor: sets up the UI
    public LabActivity5QuizAppAWT() {
        // Set window title, size, layout, and prevent resizing
        setTitle("Quiz App");
        setSize(500, 300);
        setLayout(new BorderLayout());
        setResizable(false);

        // Create the panel for the question
        Panel questionPanel = new Panel();
        questionLabel = new Label(questions[currentQuestion], Label.CENTER); // Set first question
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Custom font
        questionPanel.setLayout(new BorderLayout());
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH); // Add to top

        // Create panel for options (choices)
        Panel optionsPanel = new Panel(new GridLayout(2, 2)); // 2x2 grid
        optionsGroup = new CheckboxGroup(); // Create exclusive selection group
        optionButtons = new Checkbox[4];   // 4 choices per question

        // Create and style each checkbox
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new Checkbox("", optionsGroup, false); // Initialize as unselected
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16)); // Custom font
            optionButtons[i].setForeground(Color.BLUE); // Custom text color
            optionsPanel.add(optionButtons[i]); // Add to panel
        }

        add(optionsPanel, BorderLayout.CENTER); // Add options to center

        // Create bottom panel with error message and "Next" button
        Panel bottomPanel = new Panel(new BorderLayout());
        errorLabel = new Label("", Label.CENTER); // For messages like "Please select an answer"
        errorLabel.setForeground(Color.RED); // Red text for errors

        nextButton = new Button("Next"); // Button to proceed
        nextButton.setFont(new Font("Arial", Font.BOLD, 16)); // Custom font
        nextButton.addActionListener(this); // Register click listener
        nextButton.setBackground(Color.LIGHT_GRAY); // Button background color
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover

        bottomPanel.add(errorLabel, BorderLayout.NORTH); // Add error label
        bottomPanel.add(nextButton, BorderLayout.SOUTH); // Add button
        add(bottomPanel, BorderLayout.SOUTH); // Add bottom panel to frame

        updateQuestion(); // Initialize first question and options

        // Add window close behavior
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose(); // Close the window and release resources
            }
        });

        setVisible(true); // Show the window
    }

    // Updates the UI with the current question and options
    public void updateQuestion() {
        questionLabel.setText(questions[currentQuestion]); // Set new question
        errorLabel.setText(""); // Clear any error messages

        // Update option labels (e.g., A. Paris, B. Rome...)
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setLabel((char)('A' + i) + ". " + options[currentQuestion][i]);
            optionButtons[i].setState(false); // Uncheck all options
        }
    }

    // Handles button click events
    public void actionPerformed(ActionEvent e) {
        String selected = null;

        // Check which option is selected
        for (Checkbox cb : optionButtons) {
            if (cb.getState()) {
                selected = cb.getLabel().substring(3); // Get text after "A. ", "B. ", etc.
                break;
            }
        }

        // If no option selected, show error
        if (selected == null) {
            errorLabel.setText("Please select an answer.");
            return;
        }

        // Check if selected answer is correct
        if (selected.equals(answers[currentQuestion])) {
            score++; // Increment score
        }

        currentQuestion++; // Move to next question

        // If more questions remain, load next question
        if (currentQuestion < questions.length) {
            updateQuestion();
        } else {
            // Quiz is done: show final score
            questionLabel.setText("Quiz Completed! Your score: " + score + " out of " + questions.length);
            for (Checkbox cb : optionButtons) {
                cb.setEnabled(false); // Disable all options
            }
            nextButton.setEnabled(false); // Disable button
            errorLabel.setText(""); // Clear any message
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new LabActivity5QuizAppAWT(); // Create instance and show GUI
    }
}
