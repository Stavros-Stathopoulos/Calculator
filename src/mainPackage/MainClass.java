package mainPackage;
import gui.gui;
import calculator.calculator;
public class MainClass {
    public static void main(String[] args) {
       gui g = new gui();
        System.out.println(calculator.fact(5));
       /* String exp = calculator.postFixConverter("( 8 + 2 ) * 8");
        System.out.println(exp);
        System.out.println(calculator.evaluatePostFix(exp));*/
    }
}