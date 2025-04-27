import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextArea textArea;
    private JTextField inputField;
    private JTextField searchField;

    public SortedListGUI() {
        sortedList = new SortedList();

        setTitle("Sorted List Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        inputField = new JTextField();
        JButton addButton = new JButton("Add");

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        panel.add(new JLabel("Enter item to add:"));
        panel.add(inputField);
        panel.add(addButton);
        panel.add(new JLabel("Search for item:"));
        panel.add(searchField);
        panel.add(searchButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText().trim();
                if (!text.isEmpty()) {
                    sortedList.add(text);
                    updateTextArea();
                    inputField.setText("");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText().trim();
                if (!text.isEmpty()) {
                    int result = sortedList.search(text);
                    if (result >= 0) {
                        textArea.append("\nFound \"" + text + "\" at position: " + result);
                    } else {
                        int insertionPoint = -result - 1;
                        textArea.append("\n\"" + text + "\" not found. Would be at position: " + insertionPoint);
                    }
                    searchField.setText("");
                }
            }
        });
    }

    private void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current List:\n");
        for (String s : sortedList.getList()) {
            sb.append(s).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SortedListGUI().setVisible(true);
        });
    }
}
