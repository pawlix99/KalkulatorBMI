import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class App extends JFrame implements ActionListener {
    private JButton button1, button2;
    private JTextField pole1, pole2, pole3;
    private JRadioButton r1, r2;
    private JPanel cards;
    private CardLayout cl;
    private double BMI;
    private int BMR;

    public App() {
        firstScreen();
    }

    public void firstScreen(){
        setTitle("Kalkulator BMI, BMR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 100, 300, 500);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel tekst = new JLabel("Witaj w kalkulatorze BMI, BMR!");
        tekst.setBounds(50,75,200,25);
        panel.add(tekst);

        r1 = new JRadioButton("Kobieta");
        r2 = new JRadioButton("Mê¿czyzna");
        r1.setBounds(50,175,100,30);
        r2.setBounds(150,175,100,30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        panel.add(r1);
        panel.add(r2);

        pole1 = new HintTextField("Wpisz swoj¹ masê cia³a");
        pole2 = new HintTextField("Wpisz swój wzrost");
        pole3 = new HintTextField("Wpisz swój wiek");
        pole1.setBounds(75,225,150,20);
        pole2.setBounds(75,275,150,20);
        pole3.setBounds(75,325,150,20);
        panel.add(pole1);
        panel.add(pole2);
        panel.add(pole3);

        JLabel tekst1 = new JLabel("Masa:");
        JLabel tekst2 = new JLabel("kg");
        JLabel tekst3 = new JLabel("Wzrost:");
        JLabel tekst4 = new JLabel("cm");
        JLabel tekst5 = new JLabel("Wiek:");
        JLabel tekst6 = new JLabel("lat(a)");
        tekst1.setBounds(25,225,50,20);
        tekst2.setBounds(230,225,20,20);
        tekst3.setBounds(25,275,50,20);
        tekst4.setBounds(230,275,20,20);
        tekst5.setBounds(25,325,50,20);
        tekst6.setBounds(230,325,30,20);
        panel.add(tekst1);
        panel.add(tekst2);
        panel.add(tekst3);
        panel.add(tekst4);
        panel.add(tekst5);
        panel.add(tekst6);

        button1 = new JButton("Oblicz!");
        button1.setBounds(75,400,150,20);
        button1.addActionListener(this);
        panel.add(button1);

        cards = new JPanel();
        cl = new CardLayout();
        cards.setLayout(cl);
        cards.add(panel, "1");

        cl.show(cards,"1");
        add(cards);

        setVisible(true);
    }

    public void secondScreen(){
        Font font = new Font("Dialog", Font.BOLD,20);
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel wynikBMI = new JLabel("Twoje BMI: "+BMI);
        JLabel wynikBMR = new JLabel("Twoje BMR: "+BMR);

        wynikBMI.setBounds(50,100,200,100);
        wynikBMR.setBounds(50,150,200,100);
        wynikBMI.setFont(font);
        wynikBMR.setFont(font);
        wynikBMI.setHorizontalAlignment(JLabel.CENTER);
        wynikBMR.setHorizontalAlignment(JLabel.CENTER);

        button2 = new JButton("Jeszcze raz!");
        button2.setBounds(75,310,150,40);
        button2.addActionListener(this);
        panel2.add(wynikBMI);
        panel2.add(wynikBMR);
        panel2.add(button2);

        cards.add(panel2,"2");
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button1) {
            if ((pole1.getText().equals("Wpisz swoj¹ masê cia³a")) || (pole2.getText().equals("Wpisz swój wzrost")) || (pole3.getText().equals("Wpisz swój wiek")) || (!r1.isSelected() && !r2.isSelected()))
                JOptionPane.showMessageDialog(null, "Uzupeï¿½nij wszystkie pola.");

            else if (r1.isSelected()) {
                if ((isInt(pole1.getText())) && (isInt(pole2.getText())) && (isInt(pole3.getText()))) {
                    int masa = Integer.parseInt(pole1.getText());
                    int wzrost = Integer.parseInt(pole2.getText());
                    int wiek = Integer.parseInt(pole3.getText());

                    if((isNumberPositive(masa)) && (isNumberPositive(wzrost)) && (isNumberPositive(wiek))) {
                        BMI = BMI(masa,wzrost);
                        BMR = BMRwoman(masa,wzrost,wiek);
                        secondScreen();
                        cl.show(cards, "2");
                    } else
                        JOptionPane.showMessageDialog(null, "Dane zosta³y wpisane nie poprawnie. Spróbuj jeszcze raz.");

                } else
                    JOptionPane.showMessageDialog(null, "Dane zosta³y wpisane nie poprawnie. Spróbuj jeszcze raz.");
            }
            else if (r2.isSelected()) {
                if ((isInt(pole1.getText())) && (isInt(pole2.getText())) && (isInt(pole3.getText()))) {
                    int masa = Integer.parseInt(pole1.getText());
                    int wzrost = Integer.parseInt(pole2.getText());
                    int wiek = Integer.parseInt(pole3.getText());

                    if((isNumberPositive(masa)) && (isNumberPositive(wzrost)) && (isNumberPositive(wiek))) {
                    	BMI = (BMI(masa,wzrost));
                    	BMR = (BMRman(masa,wzrost,wiek));
                    	secondScreen();
                    	cl.show(cards, "2");
                    } else
                        JOptionPane.showMessageDialog(null, "Dane zosta³y wpisane nie poprawnie. Spróbuj jeszcze raz.");
                    
                } else
                    JOptionPane.showMessageDialog(null, "Dane zosta³y wpisane nie poprawnie. Spróbuj jeszcze raz.");
            }
        }
        if (source == button2){
            repaint();
        }
    }

    public void repaint(){
        super.getContentPane().removeAll();
        firstScreen();
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean isNumberPositive(int x) {
        return x > 0;
    }

    public double BMI(double m, double w){
        double BMI = m/(w*w)*10000;
        BMI*= 100;
        BMI = Math.round(BMI);
        BMI/=100;
        return BMI;
    }

    public int BMRwoman(double m, double w, double l){
        double BMR = Math.round(655.1 + (9.567 * m) + (1.85 * w) - (4.68 * l));
        return (int)BMR;
    }

    public int BMRman(double m, double w, double l){
        double BMR = Math.round(66.47 + (13.7 * m) + (5 * w) - (6.76 * l));
        return (int)BMR;
    }

    public static void main(String[] args) {
        new App();
    }
}

class HintTextField extends JTextField {

    Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
    Font lostFont = new Font("Tahoma", Font.ITALIC, 11);

    public HintTextField(final String hint) {

        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                } else {
                    setText(getText());
                }
                setFont(gainFont);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
    }
}