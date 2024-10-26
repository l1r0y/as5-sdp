import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;

        this.view.addOperationListener(new OperationListener());
    }

    class OperationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double firstNumber, secondNumber;
            try {
                firstNumber = view.getFirstNumber();
                secondNumber = view.getSecondNumber();

                String command = e.getActionCommand();
                double result = 0;

                switch (command) {
                    case "+":
                        result = model.add(firstNumber, secondNumber);
                        break;
                    case "-":
                        result = model.subtract(firstNumber, secondNumber);
                        break;
                    case "*":
                        result = model.multiply(firstNumber, secondNumber);
                        break;
                    case "/":
                        result = model.divide(firstNumber, secondNumber);
                        break;
                }

                view.setResult(String.valueOf(result));
            } catch (ArithmeticException ex) {
                view.setResult(ex.getMessage());
            } catch (NumberFormatException ex) {
                view.setResult("Ошибка: " + ex.getMessage());
            }
        }
    }
}