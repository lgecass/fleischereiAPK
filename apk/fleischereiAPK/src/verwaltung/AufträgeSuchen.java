// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.EventQueue;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Auftr\u00e4geSuchen
{
    private JFrame frmAuftrgeSuchen;
    JLabel lblNewLabel;
    String string;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Auftr\u00e4geSuchen window = new Auftr\u00e4geSuchen();
                    window.frmAuftrgeSuchen.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Auftr\u00e4geSuchen() {
        this.initialize();
    }
    
    private void initialize() {
        (this.frmAuftrgeSuchen = new JFrame()).setTitle("Auftr\u00e4ge suchen");
        this.frmAuftrgeSuchen.setBounds(100, 100, 450, 300);
        this.frmAuftrgeSuchen.setDefaultCloseOperation(3);
        this.frmAuftrgeSuchen.getContentPane().setLayout(null);
        (this.lblNewLabel = new JLabel(this.string)).setBounds(30, 57, 283, 91);
        this.frmAuftrgeSuchen.getContentPane().add(this.lblNewLabel);
        final JButton btnNewButton = new JButton("zurueck");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                Auftr\u00e4geSuchen.this.frmAuftrgeSuchen.setVisible(false);
                new VerwaltungsGui(true);
            }
        });
        btnNewButton.setBounds(10, 227, 89, 23);
        this.frmAuftrgeSuchen.getContentPane().add(btnNewButton);
    }
}
