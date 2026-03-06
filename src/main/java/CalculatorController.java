
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML private TextField number1Field;
    @FXML private TextField number2Field;
    @FXML private Label resultLabel;

    @FXML
    private void onCalculateClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());

            double sum = num1 + num2;
            double product = num1 * num2;

            double difference = num2 - num1;
            double division = num2/ num1;

            String result = String.format("Sum: %s, Product: %s, Difference: %s, Division: %s ", sum,product,difference, division);

            resultLabel.setText(result);

            // Save to DB
            ResultService.saveResult(num1, num2, sum, product,difference,division);

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }
}