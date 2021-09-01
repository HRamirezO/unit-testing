package junit5;

public class Calculator {

    private int resultado;

    public int add(int number1, int number2) {
        resultado = number1 + number2;
        return resultado;
    }

    public int subtract(int number1, int number2) {
        resultado = number1 - number2;
        return resultado;
    }

    public int divide(int number1, int number2) {
        resultado = number1 / number2;
        return resultado;
    }

    public int divideByZero(int number1, int number2) {
        if (number2 == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        resultado = number1 / number2;
        return resultado;
    }

    public void longTaskOperation(){
        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }

}
