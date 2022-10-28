import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class ReadFromTxt {
    public String word (int p) {
        Scanner scanner;
        String line = null;
        int counter = 0;
        try {
            scanner = new Scanner(new File("hangman.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNextLine() && counter <= p) {
            line = scanner.nextLine();
            ++counter;
        }
        return line;
    }
}
class GetRNGWord {
    Random rng = new Random();
    int number = rng.nextInt(66);
    ReadFromTxt rngWord = new ReadFromTxt();
    String word = rngWord.word(number);
    public String getWord() {
        return word;
    }
}
class BreakWord {
    GetRNGWord rngword = new GetRNGWord();
    String word = rngword.getWord();
    String[] wordChar = word.split("");
    int numberOfLetters = wordChar.length;
    public int getNumberOfLetters() {
        return numberOfLetters;
    }
    public String[] getWordChar () {
        return wordChar;
    }
}

public class MyFrame extends JFrame implements ActionListener {
    JButton textFieldButton;
    JButton startGame;
    JTextField textField;
    JLabel label;
    int ok = 0,length,counter = 0;
    String[] letters = new String[100];

    MyFrame() {
        startGame = new JButton("Start Game");
        startGame.addActionListener(this);
        startGame.setBounds(240,20,120,25);
        textFieldButton = new JButton("Submit");
        textFieldButton.addActionListener(this);
        textFieldButton.setBounds(260,120,80,25);
        textField = new JTextField();
        textField.setBounds(275,90,50,25);
        this.add(textField);
        this.add(textFieldButton);
        this.add(startGame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(600, 600);
        this.setVisible(true);
        label = new JLabel();
        label.setIcon(new ImageIcon("h1.png"));
        label.setBounds(300,300,300,300);
        this.add(label);
        this.validate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea[] textAreas = new JTextArea[100];
        BreakWord breakword = new BreakWord();
        if(ok == 0) {
            letters = breakword.getWordChar();
            length = breakword.getNumberOfLetters();
            ok = 1;
        }
        if (e.getSource() == startGame) {
            for (int i = 0 ; i < length; ++i) {
                textAreas[i] = new JTextArea();
                textAreas[i].setBounds(25 + 25 * i, 160,20,20);
                textAreas[i].setText("");
                this.add(textAreas[i]);
            }
            this.validate();
            this.repaint();
        }
        int ok_find;
        if(e.getSource() == textFieldButton) {
            ok_find = 0;
            for (int i = 0; i < length; ++i) {
                if(textField.getText().equals(letters[i])) {
                    textAreas[i] = new JTextArea();
                    textAreas[i].setBounds(25 + 25 * i, 160,20,20);
                    textAreas[i].setText("  " + letters[i]);
                    this.add(textAreas[i]);
                    ok_find = 1;
                }
            }
            if (ok_find == 0) {
                ++counter;
                switch (counter) {
                    case 1 :
                        label.setIcon(new ImageIcon("h2.png"));
                        break;
                    case 2 :
                        label.setIcon(new ImageIcon("h3.png"));
                        break;
                    case 3 :
                        label.setIcon(new ImageIcon("h4.png"));
                        break;
                    case 4 :
                        label.setIcon(new ImageIcon("h5.png"));
                        break;
                    case 5 :
                        label.setIcon(new ImageIcon("h6.png"));
                        break;
                    case 6 :
                        label.setIcon(new ImageIcon("h7.png"));
                        break;
                }
            }
        }
    }
}
