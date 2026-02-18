import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class GameFrame extends JFrame{
    public GameFrame(int num){
        // Fenstereigenschaften
        setTitle("Kreisspiel - unregistered trial version");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menüzeile erstellen
        JMenuBar menuezeile = new JMenuBar();
        // menuezeile.setFont(new Font("serif", Font.PLAIN, 24));
        setJMenuBar(menuezeile);

        JMenu menueDatei= new JMenu("Einstellungen");
        menuezeile.add(menueDatei);        

        // Hilfe Eintrag
        JMenuItem menueHelp = new JMenuItem("Bitte helfen Sie mir...");
        menueDatei.add(menueHelp);
        menueHelp.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "Drücken Sie die Schalter (Kreise) so, dass alle grün leuchten!\nDurch Drücken auf einen Kreis ändern dieser selber und die beiden Nachbarn ihren Zustand.\nSind sie aus gewesen, sind sie danach an; waren sie an, so sind sie dann aus.", "Hilfe", JOptionPane.QUESTION_MESSAGE);
                }
            });

        // Neues Spiel Eintrag
        JMenuItem menueNewGame = new JMenuItem("Neues Spiel");
        menueDatei.add(menueNewGame);
        menueNewGame.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new Launcher(num);
                    dispose();
                }
            });

        // Credits Eintrag
        JMenuItem menueCredits = new JMenuItem("Über");
        menueDatei.add(menueCredits);
        menueCredits.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String message =
                          "Folgende Personen waren an dem OOP-Projekt bei Prof. Dr. Vendl beteiligt:\n\n"
                        + "Daniel Wilhelm\n"
                        + "Jade Vu\n"
                        + "Yazan Sawalha\n\n"
                        + "Dieses Projekt ist auch auf GitHub!\n"
                        + "https://github.com/Yazaaan/oop_wise2526";

                    JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Über dieses Spiel", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
        
        // Beenden Eintrag
        JMenuItem menueBeenden = new JMenuItem("Beenden");
        menueDatei.add(menueBeenden);
        menueBeenden.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    System.exit(0);
                }
            });

        // Panel beschreiben und Elemente einfügen
        JPanel panel_root = new JPanel();
        panel_root.setLayout(new BorderLayout());
        
        // Obere Zeile
        JPanel panel_header = new JPanel();
        panel_header.setLayout(new FlowLayout());
        panel_root.add(panel_header, BorderLayout.NORTH);
        
        // Schalteranzahl Anzeige
        JLabel lbl_difficulty = new JLabel("Schalter: " + num);
        lbl_difficulty.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 250));
        panel_header.add(lbl_difficulty);
        
        // Zügezähler
        JLabel lbl_turns = new JLabel("Züge: 0");
        panel_header.add(lbl_turns);

        // Spielfläche
        GamePanel game_panel = new GamePanel(num, this, lbl_turns);
        panel_root.add(game_panel, BorderLayout.CENTER);

        this.add(panel_root);
        setVisible(true);
    }

    public void gameWonDialog(int num, int turns){
        int minimumTurns = (num % 3 == 0)? num/3 : num;

        Object[] options = {"Neues Spiel", "Beenden"};
        
        String message = String.format(
                          "Sie haben in %s Zügen gewonnen!\n"
                        + "Tipp: Das bestmögliche sind %s Züge.\n\n"
                        + "Wollen Sie ein neues Spiel starten?",
                        turns, minimumTurns);
                        
        int decision = JOptionPane.showOptionDialog(
                this,
                message,
                "GEWONNEN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]);

        if(decision == 1) System.exit(0);
        
        // Wird nich ausgeführt, wenn Programm beendet wird
        new Launcher(num);
        dispose();
    }
}