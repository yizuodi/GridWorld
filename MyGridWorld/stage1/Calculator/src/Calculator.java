import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double sub(double a, double b) {
        return a - b;
    }

    public static double mul(double a, double b) {
        return a * b;
    }

    public static double div(double a, double b) {
        return a / b;
    }

    public Calculator() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 5));
        // 两个用于输入的文本框
        JTextField input1 = new JTextField("1");
        JTextField input2 = new JTextField("1");
        // 用于显示运算符的文本框
        JTextField operator = new JTextField();
        operator.setEditable(false);
        // 显示等于号的文本框，不可编辑
        JTextField equal = new JTextField("=");
        equal.setEditable(false);
        // 用于显示结果的文本框
        JTextField result = new JTextField();
        result.setEditable(false);
        // 加减乘除
        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton mul = new JButton("*");
        JButton div = new JButton("/");
        // 运算按钮
        JButton calculate = new JButton("OK");
        // 监听按钮，变更运算符
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator.setText("+");
            }
        });
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator.setText("-");
            }
        });
        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator.setText("*");
            }
        });
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator.setText("/");
            }
        });
        // 监听按钮，计算结果
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num1 = Double.parseDouble(input1.getText());
                double num2 = Double.parseDouble(input2.getText());
                String op = operator.getText();
                double res = 0;
                switch (op) {
                    case "+":
                        res = add(num1, num2);
                        break;
                    case "-":
                        res = sub(num1, num2);
                        break;
                    case "*":
                        res = mul(num1, num2);
                        break;
                    case "/":
                        res = div(num1, num2);
                        break;
                    default:
                        res = 0;
                        break;
                }
                result.setText(String.valueOf(res));
            }
        });
        // 将按钮按顺序添加到面板中
        panel.add(input1);
        panel.add(operator);
        panel.add(input2);
        panel.add(equal);
        panel.add(result);
        panel.add(add);
        panel.add(sub);
        panel.add(mul);
        panel.add(div);
        panel.add(calculate);
        // 将面板添加到窗口中
        this.add(panel);
        // 设置窗口的标题
        this.setTitle("Calculator");
        // 设置窗口的大小
        this.setSize(400, 100);
        // 设置窗口的位置
        this.setLocation(200, 200);
        // 显示窗口
        this.setVisible(true);
        // 设置窗口的关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
