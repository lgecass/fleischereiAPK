// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import javax.swing.JLabel;
import java.io.IOException;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFrame;

public class VerwaltungsGui
{
    private JFrame frmFleischereiVerwaltung;
    
    public static void main(final String[] args) {
        final File auftragsdatei = new File("daten/Auftrag.txt");
        final File kundendatei = new File("daten/Kontakte.txt");
        final File sortierdatei = new File("daten/Sortierregeln.txt");
        System.out.println(auftragsdatei.isFile());
        System.out.println(kundendatei.isFile());
        System.out.println(sortierdatei.isFile());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final VerwaltungsGui window = new VerwaltungsGui();
                    window.frmFleischereiVerwaltung.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public VerwaltungsGui() {
        this.initialize();
    }
    
    public VerwaltungsGui(final boolean callback) {
        if (callback) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        final VerwaltungsGui window = new VerwaltungsGui();
                        window.frmFleischereiVerwaltung.setVisible(true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    private void initialize() {
        (this.frmFleischereiVerwaltung = new JFrame()).setTitle("Fleischerei Verwaltung");
        this.frmFleischereiVerwaltung.setBounds(100, 100, 446, 326);
        this.frmFleischereiVerwaltung.setDefaultCloseOperation(3);
        this.frmFleischereiVerwaltung.getContentPane().setLayout(null);
        final JButton auftraegehinzu = new JButton("Sortiervorgaben");
        auftraegehinzu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                new Sortiervorgaben(true);
            }
        });
        auftraegehinzu.setBackground(Color.LIGHT_GRAY);
        auftraegehinzu.setBounds(47, 120, 152, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(auftraegehinzu);
        final JButton btnAuftrgeAnsehen = new JButton("Einstellungen");
        btnAuftrgeAnsehen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                new Dateil\u00f6schung(true);
            }
        });
        btnAuftrgeAnsehen.setBackground(Color.LIGHT_GRAY);
        btnAuftrgeAnsehen.setBounds(221, 37, 164, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(btnAuftrgeAnsehen);
        final JButton auftraegehinzu_1 = new JButton("Auftrag hinzufuegen");
        auftraegehinzu_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                new AuftragsGui(true);
            }
        });
        auftraegehinzu_1.setBackground(Color.LIGHT_GRAY);
        auftraegehinzu_1.setBounds(47, 37, 152, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(auftraegehinzu_1);
        final JButton auftraegehinzu_2 = new JButton("Auftr\u00e4ge Drucken");
        auftraegehinzu_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                try {
                    new Auftraegedrucken(true);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        auftraegehinzu_2.setBackground(Color.LIGHT_GRAY);
        auftraegehinzu_2.setBounds(221, 120, 164, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(auftraegehinzu_2);
        final JLabel lblNewLabel = new JLabel("Men\u00fc");
        lblNewLabel.setBounds(20, 11, 205, 15);
        this.frmFleischereiVerwaltung.getContentPane().add(lblNewLabel);
        final JButton auftraegehinzu_1_1 = new JButton("Kunden hinzuf\u00fcgen");
        auftraegehinzu_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                new KundenGui(true);
            }
        });
        auftraegehinzu_1_1.setBackground(Color.LIGHT_GRAY);
        auftraegehinzu_1_1.setBounds(47, 194, 152, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(auftraegehinzu_1_1);
        final JButton auftraegehinzu_2_1 = new JButton("Auftr\u00e4ge Suchen");
        auftraegehinzu_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                VerwaltungsGui.this.frmFleischereiVerwaltung.setVisible(false);
                try {
                    new AuftragsGui("yoink");
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        auftraegehinzu_2_1.setBackground(Color.LIGHT_GRAY);
        auftraegehinzu_2_1.setBounds(221, 194, 164, 52);
        this.frmFleischereiVerwaltung.getContentPane().add(auftraegehinzu_2_1);
    }
}
