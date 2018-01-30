
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAreaFrame extends JFrame {

    private JTextArea inputArea;
    private JTextField resultField;
    private JButton calcButton;

    public void initialize() {

        inputArea = new JTextArea();

        resultField = new JTextField(20);
        resultField.setEditable(false);

        calcButton = new JButton("Calculate");
        calcButton.addActionListener(new ButtonListener());

        // add components to content pane

        Container contentPane = getContentPane();
        contentPane.add(inputArea, BorderLayout.CENTER);

        // arrange the label and text field in a panel

        JPanel resultPanel = new JPanel();
        resultPanel.add(new JLabel("Average: "));
        resultPanel.add(resultField);

        // place the button in a panel

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calcButton);

        // stack up these two panels in another panel

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(resultPanel);
        southPanel.add(buttonPanel);

        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Reads numbers from a string that contains a sequence of floating point
     * numbers separated by white space.
     *
     * @param input the string containing the numbers
     * @return the numbers that were found in the string
     */
    public static double[] getData(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        double[] data = new double[tokenizer.countTokens()];
        for (int i = 0; i < data.length; i++) {
            data[i] = Double.parseDouble(tokenizer.nextToken());
        }
        return data;
    }

    /**
     * Computes the average of an array of numbers.
     *
     * @param data the numbers to average
     * @return the average, or 0 if the array was empty
     */
    public static double average(double[] data) {
        if (data.length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }
        return sum / data.length;
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {  // get user input from text area

            double[] data = getData(inputArea.getText());

            // compute average and display in result field

            double avg = average(data);
            resultField.setText("" + avg);
        }
    }
}
