
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Νίκος
 */
public class Panel extends JFrame {

    //arxikopoihsh metavlitwn
    String name, level;
    JMenuBar mb; 
    JMenu game,info,edit;
    JMenuItem start,stop,editt,about,rules,exit,score;
    GridLayout grid ;
    Generator gen;
    int moves=0;
    static File myObj= new File("scores.txt");;
    StringBuilder str;
    JTextField textarea;
    
    ArrayList<JToggleButton> buttons = new ArrayList<>();

    public Panel(String name) {//constructor gia tin dimiourgia grafikwn
        super("Memory Game");
        this.name = name;//perasma stin topiki metabliti name to onoma tou xristei pou exei dwthei stin Main
        //dimiourgia tou parathirou 
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        //dimiourgia tou menou epilogwn
        mb = new JMenuBar();
        game = new JMenu("Παιχνίδι");
        edit = new JMenu("Κατάταξη");
        info = new JMenu("Πληροφορίες");
        start = new JMenuItem("Έναρξη");
        stop = new JMenuItem("Τερμάτισμος");
        editt = new JMenuItem("Επεξεργασία Στοιχείων Παίκτη");
        about = new JMenuItem("About");
        rules = new JMenuItem("Κανόνες Παιχνιδιού");
        exit = new JMenuItem("Έξοδος");
        score = new JMenuItem("Πίνακας Κατάταξης");
        
        this.add(mb);
        mb.add(game);
        mb.add(edit);
        mb.add(info);
        
        game.add(start);
        game.add(stop);
        game.add(editt);
        game.add(exit);
        info.add(rules);
        info.add(about);
        edit.add(score);
        
        this.setJMenuBar(mb);
        //dimiourgia Listeners gia tin diamorgosi twn leitourgiwn kathe epilogis tou menou
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGame();
            }
        });
        
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopGame();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Υπάρχουν 3 επίπεδα δυσκολίας.\nTo εύκολο το οποίο περιέχει 25 κάρτες με ενα τζόκερ και πρεπει να λυθεί σε 50 κινήσεις αλλιώς ο παίκτης χάνει.\nTo μέτριο το οποίο περιέχει 64 κάρτες με δύο τζόκερ και πρεπει να λυθεί σε 128 κινήσεις αλλιώς ο παίκτης χάνει.\nΤέλος το δύσκολο το οποίο περιέχει 100 κάρτες με δύο τζόκερ και πρεπει να λυθεί σε 200 κινήσεις αλλιώς ο παίκτης χάνει.\nΣκοπος του παιχνιδιου ειναι ο παίκτης να βρεί ολους τους συνδιασμους σε λιγότερες απο τις κινήσεις που επιτρέπει κάθε επίπεδο.\nΟ παίκτης με τις λιγότερες κινήσεις κερδίζει.\n", "Κανόνες", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ευχαρισούμε που παιζεται το παιχνιδι μας.\nΝίκος Πόταρης - icsd15173\nΘάνος Καψάλης - icsd15088", "Σχετικά με εμάς", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str = new StringBuilder(); //dimiourgia enos StringBuilder gia tin emfanisei twn katagrafwn apo to arxeio
                ScoreBoard();
                System.out.println(str.toString());
                JOptionPane.showMessageDialog(null, str.toString(), "Πίνακας Κατάταξης", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
    }
    //sinartisi gia ton termatismo tou paixnidiou
    public void StopGame()
    {
        Panel panel = new Panel(name);
        gen.tile.clear();
        close();
    }
    //sinartisi gia tin enarksi tou paixnidiou
    public void StartGame()
    {
        String[] epilogi = {"εύκολο", "μέτριο", "δύσκολο"};
        //emfanisi minimatos ston xristi gia tin epilogi epipedou duskolias gia to paixnidi
        level = (String) JOptionPane.showInputDialog(null, "Επιλέξτε τον βαθμό δυκολίας που επιθυμείτε ", "Επίπεδο Δυσκολίας", JOptionPane.PLAIN_MESSAGE, null, epilogi, epilogi[2]);
        gen = new Generator(level);//kaloume tin klasi Generator gia tin dimiourgia twn kartwn pros emfanisi
        //anakatema twn kartwn pros emfanisi
        Collections.shuffle(gen.tile);
        //diamorfosi tou pinaka gia tin enarksi tou paixnidiou
        if (level.equals("εύκολο")) {
            grid = new GridLayout(6, 6);
            this.setLayout(grid);
            Create(gen);

        } else if (level.equals("μέτριο")) {
            grid = new GridLayout(9, 9);
            this.setLayout(grid);
            Create(gen);
        } else if (level.equals("δύσκολο")) {
            grid = new GridLayout(11, 11);
            this.setLayout(grid);
            Create(gen);
        }
        for (int i = 0; i < gen.tile.size(); i++) {
            System.out.println(gen.tile.get(i).GetColor());
        }
        JLabel score = new JLabel("Σκόρ: ");
        textarea = new JTextField();
        this.add(score);
        textarea.setEditable(false);
        textarea.setText(String.valueOf(moves));
        this.add(textarea);
    }
    //sinartisi gia ton elenxo twn kartwn
    public void verify(Generator gen) {
        ArrayList<JToggleButton> selection = new ArrayList<>();
        
        for (JToggleButton button : buttons) {
            if (button.isSelected() && button.isEnabled()) {
                selection.add(button);
            }
        }
        //elenxos gia ton entopismo tis kartas joker
        if (gen.tile.get(Integer.parseInt(selection.get(0).getName())).GetColor().equals(Color.BLACK)) {
                for (JToggleButton but : buttons) {
                    if (gen.tile.get(Integer.parseInt(selection.get(0).getName())).GetShape().equals(gen.tile.get(Integer.parseInt(but.getName())).GetShape())) {
                        but.setIcon(gen.tile.get(Integer.parseInt(but.getName())).GetIcon());
                        but.setEnabled(false);
                    }
                }
            }
        //elenxos gia tin epikirwsi swstis euresis zeugariou kartwn
        if (selection.size() == 2) {

            if (gen.tile.get(Integer.parseInt(selection.get(0).getName())).GetShape().equals(gen.tile.get(Integer.parseInt(selection.get(1).getName())).GetShape())
                    && gen.tile.get(Integer.parseInt(selection.get(0).getName())).GetColor().equals(gen.tile.get(Integer.parseInt(selection.get(1).getName())).GetColor())) {
                System.out.println(gen.tile.get(Integer.parseInt(selection.get(0).getName())));
                selection.get(0).setEnabled(false);
                selection.get(1).setEnabled(false);

            }
            
        }
        moves++;//metritis gia to scor tou paikti
        textarea.setText(String.valueOf(moves));
        //elenxos gia to an enas paiktis kerdise h exase
        if(moves==gen.tile.size()*2)
        {
            JOptionPane.showMessageDialog(null, "Αν δεν το καταλαβατε ακομα.....\n\n ΧΑΣΑΤΕ!!!!!!!!!", "Χασατε", JOptionPane.PLAIN_MESSAGE);
            StopGame();
        }
        if(countRemainingButtons()==0)
        {
            LeaderBoard();
            JOptionPane.showMessageDialog(null, "Αν δεν το καταλαβατε ακομα.....\n\n ΚΕΡΔΙΣΑΤΕ!!!!!!!!!", "Κερδισατε", JOptionPane.PLAIN_MESSAGE);
            StopGame();
        }
    }

    //sinartiasi gia dimiourgia tou pinaka katataksis twn paiktwn gia tin emfanisi tou
    public void ScoreBoard()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myObj));
            String currentLine;
            while ((currentLine = reader.readLine()) !=null ) {
                str.append(currentLine+"\n");
                System.out.println(str.toString());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            
        }
    }
    //sinartiasi gia kataxosisi ston pinaka katataksis twn paiktwn pou kerdizoun sto paixnidi
    public void LeaderBoard()
    {
        try {
            
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("scores.txt", true);
            BufferedWriter write = new BufferedWriter(myWriter);
            write.write("Επίπεδο Δυσκολίας: "+level+" Όνομα: "+name+" Σκόρ: "+moves);
            write.newLine();
            write.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            }
    }
    //sinartisi gia tin dimiourgia tis leitourgias epilogis twn kartwn
    public void Create(Generator gen) {
        for (int i = 0; i < gen.tile.size(); i++) {
            buttons.add(new JToggleButton());
            buttons.get(i).setName("" + i);

            this.add(buttons.get(i));

        }
        for (JToggleButton button : buttons) {
            button.addActionListener((ActionEvent ev) -> {
                button.setIcon(gen.tile.get(Integer.parseInt(button.getName())).GetIcon());

                if (countSelectedButtons() == 3) {

                    unSelectAllButtons();

                    button.setSelected(true);
                    button.setIcon(gen.tile.get(Integer.parseInt(button.getName())).GetIcon());
                }
                verify(gen);

            });
        }
    }
    
    
    //sinartiasi gia tin katametrisi twn kartwn pou exoun epilextei apo ton xristi 
    //etsi wste na ginetai swsta o elenxos gia tin eggirotita tis epilogis tou
    public int countSelectedButtons() {

        int count = 0;
        for (JToggleButton button : buttons) {
            if (button.isSelected() && button.isEnabled()) {
                count++;
            }
        }

        return count;
    }
    //sinartiasi gia ton elenxo twn upolipomenwn kartwn
    public int countRemainingButtons()
    {
        int count = 0;

        for (JToggleButton button : buttons)
        {
            if (!button.isSelected() && button.isEnabled())
            {
                count++;
            }
        }

        return count;
    }
    //sinartiasi gia na kriftoun kai pali oi kartes se periptwsi pou den einai swsti h epilogi tou xristi
    public void unSelectAllButtons() {
        for (JToggleButton button : buttons) {
            if (button.isSelected() && button.isEnabled()) {
                button.setSelected(false);
                button.setIcon(null);
            }

        }
    }

    //sinartiasi gia to kleisimo tou parathirou
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

}
