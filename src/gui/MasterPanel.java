package gui;

import calculator.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MasterPanel extends JPanel {

    public MasterPanel(){
        this.setLayout(new BorderLayout());
        this.add(createNorthPanel(), BorderLayout.NORTH);
        this.add(createCenterPanel(), BorderLayout.CENTER);
    }

    protected JPanel createNorthPanel(){
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,0));
        northPanel.setPreferredSize(new Dimension(325, 150));
        JLabel label = new JLabel("0");
        JLabel label2 = new JLabel("");
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        label2.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label2.setHorizontalAlignment(JLabel.LEFT);
        northPanel.add(label2, 0);
        northPanel.add(label, 1);

        northPanel.setBackground(Color.BLACK);


        return northPanel;
    }

    private JPanel createCenterPanel(){
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,4));
        centerPanel.setBackground(Color.DARK_GRAY);
        centerPanel.setPreferredSize(new Dimension(325, 400));

        JButton buttonC =  createButton("C");
        buttonC.addMouseListener(OperatorMouseListener('C'));

        JButton buttonPARO =  createButton("(");
        buttonPARO.addMouseListener(OperatorMouseListener('('));

        JButton buttonPARC =  createButton(")");
        buttonPARC.addMouseListener(OperatorMouseListener(')'));

        JButton buttonSQR =  createButton("x^2");
        buttonSQR.addMouseListener(OperatorMouseListener('^'));

        JButton button7 =  createButton("7");
        button7.addMouseListener(NumberMouseListener('7'));

        JButton button8 =  createButton("8");
        button8.addMouseListener(NumberMouseListener('8'));

        JButton button9 =  createButton("9");
        button9.addMouseListener(NumberMouseListener('9'));

        JButton buttonDIV =  createButton("/");
        buttonDIV.addMouseListener(OperatorMouseListener('/'));

        JButton button4 =  createButton("4");
        button4.addMouseListener(NumberMouseListener('4'));

        JButton button5 =  createButton("5");
        button5.addMouseListener(NumberMouseListener('5'));

        JButton button6 =  createButton("6");
        button6.addMouseListener(NumberMouseListener('6'));

        JButton buttonMUL =  createButton("*");
        buttonMUL.addMouseListener(OperatorMouseListener('*'));

        JButton button1 =  createButton("1");
        button1.addMouseListener(NumberMouseListener('1'));

        JButton button2 =  createButton("2");
        button2.addMouseListener(NumberMouseListener('2'));

        JButton button3 =  createButton("3");
        button3.addMouseListener(NumberMouseListener('3'));

        JButton buttonRED =  createButton("-");
        buttonRED.addMouseListener(OperatorMouseListener('-'));

        JButton button0 =  createButton("0");
        button0.addMouseListener(NumberMouseListener('0'));

        JButton buttonPNT =  createButton("!");
        buttonPNT.addMouseListener(NumberMouseListener('!'));

        JButton buttonEQ =  createButton("=");
        buttonEQ.addMouseListener(OperatorMouseListener('='));

        JButton buttonADD =  createButton("+");
        buttonADD.addMouseListener(OperatorMouseListener('+'));

        centerPanel.add(buttonC);
        centerPanel.add(buttonPARO);
        centerPanel.add(buttonPARC);
        centerPanel.add(buttonDIV);

        centerPanel.add(button7);
        centerPanel.add(button8);
        centerPanel.add(button9);
        centerPanel.add(buttonMUL);

        centerPanel.add(button4);
        centerPanel.add(button5);
        centerPanel.add(button6);
        centerPanel.add(buttonRED);

        centerPanel.add(button1);
        centerPanel.add(button2);
        centerPanel.add(button3);
        centerPanel.add(buttonADD);

        centerPanel.add(buttonSQR);
        centerPanel.add(button0);
        centerPanel.add(buttonPNT);
        centerPanel.add(buttonEQ);





        return centerPanel;
    }

    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        if(text.equals("=")){
            button.setBackground(Color.ORANGE);
        }
        else {
            button.setBackground(Color.DARK_GRAY);
        }
        button.setForeground(Color.WHITE);
        return button;
    }


        private MouseAdapter NumberMouseListener(final char character) {
            return new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateNorthPanel(character);
                }
            };
        }
        private MouseAdapter OperatorMouseListener(final char character) {
            return new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateNorthPanel(character);
                }
            };
        }

        private void updateNorthPanel(Character character){
            String eq;
            JPanel northPanel = (JPanel) this.getComponent(0);
            JLabel label = (JLabel) northPanel.getComponent(1);
            JLabel label2 = (JLabel) northPanel.getComponent(0);
            String text = label.getText();

            if(text.equals("0") && !character.equals('C') && !calculator.isOperator(String.valueOf(character))){
                label.setText(String.valueOf(character));
            }
            else if (calculator.isOperator(String.valueOf(character))){
                if(text.equals("0")){
                    label.setText(" " + character + " ");
                }else {
                    label.setText(text + " " + character + " ");
                }

            } else if ( character.equals('!')) {
                String number = label.getText();
                label.setText(number + "!");
                label2.setText(number + "!");
                label.setText(String.valueOf(calculator.fact(Integer.parseInt(number))));
            }
            else if(character.equals('=')){
                eq = label.getText();
                System.out.println(eq);
                label2.setText(eq);
                String expression = calculator.postFixConverter(eq);
                label.setText(String.valueOf(calculator.evaluatePostFix(expression)) );
            } else if (character.equals('C')) {
                    label.setText("0");
                eq = null;
            } else if (!label2.equals("") ) {

                label.setText(String.valueOf(character));
                label2.setText("");


            } else{
                label.setText(text + character);
            }
        }

}
