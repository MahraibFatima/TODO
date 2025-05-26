import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TodoListApp {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField taskInput;
    private JButton addButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JButton deleteButton;
    private JButton completeButton;

    public TodoListApp() {
        // Create the main frame
        frame = new JFrame("Todo List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);

        // Create components
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskInput = new JTextField(20);
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Selected");
        completeButton = new JButton("Mark Complete");

        // Set up panel layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);

        // Add components to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTask();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTaskComplete();
            }
        });

        // Add main panel to frame and display
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a task", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markTaskComplete() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = listModel.getElementAt(selectedIndex);
            if (!task.startsWith("[Completed] ")) {
                listModel.set(selectedIndex, "[Completed] " + task);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to mark complete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoListApp();
            }
        });
    }
}