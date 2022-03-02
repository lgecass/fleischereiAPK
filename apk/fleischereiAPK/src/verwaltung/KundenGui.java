// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.table.DefaultTableModel;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class KundenGui
{
    private JFrame frmKundenHinzufgen;
    private JTextField textField;
    private JTextField textField_3;
    private JTextField textField_4;
    JButton btnNewButton;
    JLabel lblNewLabel_2;
    JButton btnNewButton_1;
    public static int set;
    
    static {
        KundenGui.set = 1;
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final KundenGui window = new KundenGui();
                    window.frmKundenHinzufgen.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public KundenGui() {
        this.btnNewButton = new JButton("Kunden speichern");
        this.initialize();
    }
    
    public KundenGui(final boolean n) {
        System.out.println(n);
        if (n) {
            this.btnNewButton = new JButton("Kunden speichern");
            this.btnNewButton_1 = new JButton("zurueck");
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        KundenGui.this.initialize();
                        KundenGui.this.frmKundenHinzufgen.setVisible(true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else {
            System.out.println("bin in else");
            this.btnNewButton = new JButton("speichern und schlie\u00dfen");
            this.btnNewButton_1 = new JButton("schlie\u00dfen");
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        KundenGui.this.initialize();
                        KundenGui.this.frmKundenHinzufgen.setVisible(true);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    public KundenGui(final boolean b, final DefaultTableModel dtm) {
    }
    
    private void initialize() {
        (this.frmKundenHinzufgen = new JFrame()).setTitle("kunden hinzuf\u00fcgen");
        this.frmKundenHinzufgen.setBounds(100, 100, 340, 402);
        this.frmKundenHinzufgen.setDefaultCloseOperation(3);
        this.frmKundenHinzufgen.getContentPane().setLayout(null);
        final JLabel lblNewLabel = new JLabel("Kunden hinzuf\u00fcgen");
        lblNewLabel.setBounds(10, 11, 155, 14);
        this.frmKundenHinzufgen.getContentPane().add(lblNewLabel);
        (this.textField = new JTextField()).setBounds(112, 76, 200, 28);
        this.frmKundenHinzufgen.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        final JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setBounds(56, 83, 46, 14);
        this.frmKundenHinzufgen.getContentPane().add(lblNewLabel_1);
        (this.textField_3 = new JTextField()).setColumns(10);
        this.textField_3.setBounds(112, 115, 200, 28);
        this.frmKundenHinzufgen.getContentPane().add(this.textField_3);
        final JLabel lblNewLabel_1_3 = new JLabel("Adresse");
        lblNewLabel_1_3.setBounds(40, 122, 62, 14);
        this.frmKundenHinzufgen.getContentPane().add(lblNewLabel_1_3);
        (this.textField_4 = new JTextField()).setColumns(10);
        this.textField_4.setBounds(112, 154, 200, 28);
        this.frmKundenHinzufgen.getContentPane().add(this.textField_4);
        final JLabel lblNewLabel_1_4 = new JLabel("Telefonnummer");
        lblNewLabel_1_4.setBounds(10, 154, 102, 21);
        this.frmKundenHinzufgen.getContentPane().add(lblNewLabel_1_4);
        this.btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (KundenGui.this.textField.getText().length() == 0) {
                    KundenGui.this.lblNewLabel_2.setVisible(true);
                    KundenGui.this.lblNewLabel_2.setText("Der Kunde muss einen Namen haben");
                }
                else {
                    KundenGui.this.lblNewLabel_2.setVisible(true);
                    KundenGui.this.lblNewLabel_2.setText(String.valueOf(KundenGui.this.textField.getText()) + " wurde Hinzugef\u00fcgt");
                    final File kunden = new File("daten/Kontakte.txt");
                    try {
                        final PrintWriter aus = new PrintWriter(new FileWriter("daten/Kontakte.txt", true));
                        aus.println(String.valueOf(KundenGui.this.textField.getText()) + "$" + KundenGui.this.textField_3.getText() + "$" + KundenGui.this.textField_4.getText());
                        aus.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    KundenGui.this.textField.setText("");
                    KundenGui.this.textField_3.setText("");
                    KundenGui.this.textField_4.setText("");
                    if (KundenGui.this.btnNewButton.getText().equals("speichern und schlie\u00dfen")) {
                        KundenGui.this.frmKundenHinzufgen.setVisible(false);
                    }
                    KundenGui.set = 0;
                }
            }
        });
        this.btnNewButton.setBounds(112, 211, 202, 28);
        this.frmKundenHinzufgen.getContentPane().add(this.btnNewButton);
        this.btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (KundenGui.this.btnNewButton.getText().equals("speichern und schlie\u00dfen")) {
                    KundenGui.this.frmKundenHinzufgen.setVisible(false);
                }
                else {
                    KundenGui.this.frmKundenHinzufgen.setVisible(false);
                    new VerwaltungsGui(true);
                }
            }
        });
        this.btnNewButton_1.setBounds(10, 329, 97, 23);
        this.frmKundenHinzufgen.getContentPane().add(this.btnNewButton_1);
        (this.lblNewLabel_2 = new JLabel("")).setBounds(83, 258, 215, 45);
        this.frmKundenHinzufgen.getContentPane().add(this.lblNewLabel_2);
        this.lblNewLabel_2.setVisible(false);
    }
}
