import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;

public class MEDGUI extends JFrame {
    private JTextField inputFieldPart1;
    private JButton findAlternativesButton;
    private JTextArea resultAreaPart1;

    private JTextField inputFieldWord1;
    private JTextField inputFieldWord2;
    private JButton findMEDButton;
    private JTextArea resultAreaPart2;

    public MEDGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("MED Algorithm UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelPart1 = new JPanel();
        inputFieldPart1 = new JTextField(20);
        findAlternativesButton = new JButton("Find Alternatives");
        resultAreaPart1 = new JTextArea(10, 30);
        resultAreaPart1.setEditable(false);

        findAlternativesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputWord = inputFieldPart1.getText();
                if (!inputWord.isEmpty()) {
                    try {
                        findAlternatives(inputWord);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    resultAreaPart1.setText("Please enter a word.");
                }
            }
        });

        panelPart1.add(new JLabel("Enter a word: "));
        panelPart1.add(inputFieldPart1);
        panelPart1.add(findAlternativesButton);
        panelPart1.add(new JScrollPane(resultAreaPart1));

        tabbedPane.addTab("Part 1", panelPart1);

        JPanel panelPart2 = new JPanel();
        inputFieldWord1 = new JTextField(15);
        inputFieldWord2 = new JTextField(15);
        findMEDButton = new JButton("Find MED");
        resultAreaPart2 = new JTextArea(15, 40);
        resultAreaPart2.setEditable(false);

        findMEDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word1 = inputFieldWord1.getText();
                String word2 = inputFieldWord2.getText();
                if (!word1.isEmpty() && !word2.isEmpty()) {
                    findMED(word1, word2);
                } else {
                    resultAreaPart2.setText("Please enter both words.");
                }
            }
        });

        panelPart2.add(new JLabel("Enter Word 1: "));
        panelPart2.add(inputFieldWord1);
        panelPart2.add(new JLabel("Enter Word 2: "));
        panelPart2.add(inputFieldWord2);
        panelPart2.add(findMEDButton);
        panelPart2.add(new JScrollPane(resultAreaPart2));

        tabbedPane.addTab("Part 2", panelPart2);

        getContentPane().add(tabbedPane);
        setLocationRelativeTo(null);
    }

    private void findAlternatives(String word) throws IOException {
    	resultAreaPart1.setText("");
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                MED med = new MED();
                med.alternative_correct_words(word, resultAreaPart1);
                return null;
            }
        };

        worker.execute();
    }

    private void findMED(String word1, String word2) {
    	resultAreaPart2.setText("");
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                MED med = new MED();
                med.first_to_sec(word1, word2, resultAreaPart2);
                return null;
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MEDGUI().setVisible(true);
            }
        });
    }
}