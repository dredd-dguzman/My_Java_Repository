import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LabActivity6SwingToDoList {
    public static void main(String[] args) {
        // Start the app on the Swing thread (basically to avoid UI bugs)
        SwingUtilities.invokeLater(ToDoListViewer::new);
    }
}

// This is the main window that shows the task list and the "Add Task" button
class ToDoListViewer extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private ToDoListForm formWindow; // This will be the pop-up window for adding tasks

    public ToDoListViewer() {
        setTitle("To-Do List Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Centers the window

        // Add Task button setup
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> openForm());

        // Create table with 3 columns
        tableModel = new DefaultTableModel(new String[]{"Task Name", "Task Description", "Status"}, 0);
        table = new JTable(tableModel);

        // For layout 
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    // Opens the task form window
    private void openForm() {
        // If the form is not open or was closed, open a new one
        if (formWindow == null || !formWindow.isDisplayable()) {
            formWindow = new ToDoListForm(this);
        } else {
            // If it's already open, just bring it to front
            formWindow.toFront();
        }
    }

    // Called from the form to actually add the task to the table
    public void addTask(String name, String description, String status) {
        tableModel.addRow(new Object[]{name, description, status});
    }

    // This gets called when the Add Task window is closed (to reset the reference)
    public void notifyFormClosed() {
        formWindow = null;
    }
}

// This is the pop-up form for adding a new task
class ToDoListForm extends JFrame {
    private JTextField taskNameField;
    private JTextArea taskDescriptionArea;
    private JComboBox<String> statusComboBox;
    private ToDoListViewer parent; // Reference to the main window

    public ToDoListForm(ToDoListViewer parent) {
        this.parent = parent;

        // This makes sure that the form window actually closes (and not just hides)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // This is needed to tell the main window that this form is now closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.notifyFormClosed();
            }
        });

        setTitle("Add New Task");
        setSize(400, 300);
        setLocationRelativeTo(parent); // Opens near the main window
        setLayout(new GridBagLayout());

        // Layout manager (used to space out the inputs)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Task Name
        gbc.gridy = 0;
        add(new JLabel("Task Name:"), gbc);
        gbc.gridy++;
        taskNameField = new JTextField(25);
        add(taskNameField, gbc);

        // Task Description
        gbc.gridy++;
        add(new JLabel("Task Description:"), gbc);
        gbc.gridy++;
        taskDescriptionArea = new JTextArea(1, 25);
        taskDescriptionArea.setLineWrap(true);
        taskDescriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(taskDescriptionArea);
        descScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descScroll.setPreferredSize(new Dimension(0, 25));
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        add(descScroll, gbc);

        // Status Dropdown
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        add(new JLabel("Status:"), gbc);
        gbc.gridy++;
        statusComboBox = new JComboBox<>(new String[]{"Not Started", "Ongoing", "Completed"});
        add(statusComboBox, gbc);

        // Save Button
        gbc.gridy++;
        gbc.insets = new Insets(15, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.weighty = 1;
        JButton saveButton = new JButton("Save Task");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        add(buttonPanel, gbc);

        // When Save is clicked
        saveButton.addActionListener(e -> saveTask());

        setVisible(true);
    }

    // Called when the save button is pressed
    private void saveTask() {
        String name = taskNameField.getText().trim();
        String desc = taskDescriptionArea.getText().trim();
        String status = (String) statusComboBox.getSelectedItem();

        // Simple input validation
        if (name.isEmpty() || status == null || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Task Name and Status.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add task to the main window and close this form
        parent.addTask(name, desc, status);
        dispose(); // Triggers windowClosed() which resets the formWindow
    }
}
