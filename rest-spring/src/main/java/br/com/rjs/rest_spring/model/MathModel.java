package br.com.rjs.rest_spring.model;

public class MathModel {

    public static Double convertNumberToDouble(String number) {
        if (number == null || number.isEmpty()) throw new UnsupportedOperationException("Digite apenas números");
        String formatedNumber = number.replace(",", ".");
        return Double.parseDouble(formatedNumber);
    }

    public static boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) return false;
        String formatedNumber = number.replace(",", ".");
        return formatedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double sum(String numberOne, String numberTwo) {
        return convertNumberToDouble(numberOne) + MathModel.convertNumberToDouble(numberTwo);
    }

    public static Double subtraction(String numberOne, String numberTwo) {
        return convertNumberToDouble(numberOne) - MathModel.convertNumberToDouble(numberTwo);
    }

    public static Double multiplication(String numberOne, String numberTwo) {
        return convertNumberToDouble(numberOne) * MathModel.convertNumberToDouble(numberTwo);
    }

    public static Double division(String numberOne, String numberTwo) {
        return convertNumberToDouble(numberOne) / MathModel.convertNumberToDouble(numberTwo);
    }

    public static Double average(String numberOne, String numberTwo) {
        return (convertNumberToDouble(numberOne) + MathModel.convertNumberToDouble(numberTwo)) / 2;
    }

    public static int calculateSquareRoot(String numero){
        double convertedNumber = MathModel.convertNumberToDouble(numero);
        int count = 1;
        boolean flag = false;
        while(!flag) {
            if(count * count == convertedNumber) {
                flag = true;
            } else {
                count = count + 1;
            }
        }
        return (count);
    }
}
