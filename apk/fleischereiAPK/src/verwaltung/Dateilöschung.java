// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Dateil\u00f6schung
{
    private JFrame frmEinstellungen;
    JLabel lblNewLabel;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Dateil\u00f6schung window = new Dateil\u00f6schung();
                    window.frmEinstellungen.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Dateil\u00f6schung() {
        this.initialize();
    }
    
    public Dateil\u00f6schung(final boolean n) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Dateil\u00f6schung window = new Dateil\u00f6schung();
                    window.frmEinstellungen.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void initialize() {
        (this.frmEinstellungen = new JFrame()).setTitle("Einstellungen");
        this.frmEinstellungen.setBounds(100, 100, 450, 300);
        this.frmEinstellungen.setDefaultCloseOperation(3);
        this.frmEinstellungen.getContentPane().setLayout(null);
        final JButton btnNewButton = new JButton("Alle Auftr\u00e4ge L\u00f6schen");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrintWriter aus = new PrintWriter(new FileWriter("daten/Auftrag.txt"));
                    aus.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Dateil\u00f6schung.this.lblNewLabel.setVisible(true);
                Dateil\u00f6schung.this.lblNewLabel.setText("Auftr\u00e4ge wurden gel\u00f6scht");
            }
        });
        btnNewButton.setBounds(45, 25, 344, 31);
        this.frmEinstellungen.getContentPane().add(btnNewButton);
        final JButton btnAlleKontakteLschen = new JButton("Alle Kontakte L\u00f6schen");
        btnAlleKontakteLschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrintWriter aus = new PrintWriter(new FileWriter("daten/Kontakte.txt"));
                    aus.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Dateil\u00f6schung.this.lblNewLabel.setVisible(true);
                Dateil\u00f6schung.this.lblNewLabel.setText("Kontakte wurden gel\u00f6scht");
            }
        });
        btnAlleKontakteLschen.setBounds(45, 67, 344, 31);
        this.frmEinstellungen.getContentPane().add(btnAlleKontakteLschen);
        final JButton btnAlleSortierregelnLschen = new JButton("Alle Sortierregeln Loeschen");
        btnAlleSortierregelnLschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final PrintWriter aus = new PrintWriter(new FileWriter("daten/Sortierregeln.txt"));
                    aus.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Dateil\u00f6schung.this.lblNewLabel.setVisible(true);
                Dateil\u00f6schung.this.lblNewLabel.setText("Sortierregeln wurden gel\u00f6scht");
            }
        });
        btnAlleSortierregelnLschen.setBounds(45, 109, 344, 31);
        this.frmEinstellungen.getContentPane().add(btnAlleSortierregelnLschen);
        final JButton btnNewButton_1 = new JButton("zurueck");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Dateil\u00f6schung.this.frmEinstellungen.setVisible(false);
                new VerwaltungsGui(true);
            }
        });
        btnNewButton_1.setBounds(10, 227, 89, 23);
        this.frmEinstellungen.getContentPane().add(btnNewButton_1);
        (this.lblNewLabel = new JLabel("New label")).setBounds(45, 174, 344, 23);
        this.frmEinstellungen.getContentPane().add(this.lblNewLabel);
        this.lblNewLabel.setVisible(false);
    }
}
