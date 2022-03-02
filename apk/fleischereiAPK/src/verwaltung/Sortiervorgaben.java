// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Sortiervorgaben extends JFrame
{
    ArrayList<Food> foodlist;
    String[] header;
    DefaultTableModel dtm;
    int row;
    int col;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JComboBox<String> jCBFoodLocation;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane4;
    private JTable jTable2;
    private JTextField jTextFoodName;
    private static JTextField jTextFoodPrice;
    
    public Sortiervorgaben() {
        this.header = new String[] { "Name", "", "Zuteilung" };
        this.initComponents();
        this.foodlist = new ArrayList<Food>();
        this.dtm = new DefaultTableModel(this.header, 0);
        this.jTable2.setModel(this.dtm);
        this.setLocationRelativeTo(null);
    }
    
    public Sortiervorgaben(final boolean a) {
        this.header = new String[] { "Name", "", "Zuteilung" };
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo info = installedLookAndFeels[i];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sortiervorgaben().setVisible(true);
                Sortiervorgaben.jTextFoodPrice.setVisible(false);
            }
        });
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jTextFoodName = new JTextField();
        this.jLabel3 = new JLabel();
        Sortiervorgaben.jTextFoodPrice = new JTextField();
        this.jLabel4 = new JLabel();
        this.jCBFoodLocation = new JComboBox<String>();
        this.jPanel2 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jScrollPane4 = new JScrollPane();
        this.jTable2 = new JTable();
        this.jLabel1.setText("jLabel1");
        this.setDefaultCloseOperation(3);
        this.setTitle("Sortiervorgaben");
        this.setResizable(false);
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel2.setText("Produktname");
        this.jLabel3.setText("");
        this.jLabel4.setText("Zuteilung");
        this.jCBFoodLocation.setModel(new DefaultComboBoxModel<String>(new String[] { "Vorbereitung","Wuerste", "Haehnchen/Pute", "Ente/Gans", "Wild", "Schwein", "Rind/Kalb", "Lamm", "Aufschnitt","Kueche", "Salat/Kaese" }));
        this.jCBFoodLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Sortiervorgaben.this.jCBFoodLocationActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel3)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextFoodName).addGroup(jPanel1Layout.createSequentialGroup().addComponent(Sortiervorgaben.jTextFoodPrice, -2, 94, -2).addGap(0, 0, 32767)))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBFoodLocation, -2, 160, -2).addGap(0, 0, 32767))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextFoodName, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(Sortiervorgaben.jTextFoodPrice, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jCBFoodLocation, -2, -1, -2)).addContainerGap(14, 32767)));
        this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
        this.jButton1.setText("hinzuf\u00fcgen");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Sortiervorgaben.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("l\u00f6schen");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Sortiervorgaben.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("aktualisieren");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                Sortiervorgaben.this.jButton3ActionPerformed(evt);
            }
        });
        this.jButton4.setText("speichern");
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    Sortiervorgaben.this.jButton4ActionPerformed(evt);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1, -2, 95, -2).addGap(18, 18, 18).addComponent(this.jButton2, -2, 105, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, 32767).addComponent(this.jButton3, -2, 109, -2).addGap(18, 18, 18).addComponent(this.jButton4, -2, 107, -2).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1, -1, -1, 32767).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton3, -1, -1, 32767).addComponent(this.jButton4, -1, -1, 32767)).addContainerGap()));
        this.jTable2.setModel(new DefaultTableModel(new Object[][] { new Object[4], new Object[4], new Object[4], new Object[4] }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                Sortiervorgaben.this.jTable2MouseClicked(evt);
            }
        });
        this.jScrollPane4.setViewportView(this.jTable2);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jScrollPane4).addComponent(this.jPanel2, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane4, -2, 175, -2).addGap(13, 13, 13)));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        final String foodname = this.jTextFoodName.getText();
        final String foodprice = "";
        final String foodloc = this.jCBFoodLocation.getSelectedItem().toString();
        this.foodlist.add(new Food(foodname, foodloc, foodprice));
        this.dtm.setRowCount(0);
        for (int i = 0; i < this.foodlist.size(); ++i) {
            final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).foodloc };
            this.dtm.addRow(objs);
        }
        this.clearField();
    }
    
    private void clearField() {
        this.jTextFoodName.requestFocus();
        this.jTextFoodName.setText("");
        Sortiervorgaben.jTextFoodPrice.setText("");
        this.jCBFoodLocation.setSelectedIndex(0);
    }
    
    private void jTable2MouseClicked(final MouseEvent evt) {
        this.row = this.jTable2.getSelectedRow();
        this.col = this.jTable2.getColumnCount();
        System.out.println(String.valueOf(this.row) + "," + this.col);
        this.jTextFoodName.setText(this.dtm.getValueAt(this.row, 0).toString());
        final String location = this.dtm.getValueAt(this.row, 2).toString();
        for (int i = 0; i < this.jCBFoodLocation.getItemCount(); ++i) {
            if (this.jCBFoodLocation.getItemAt(i).equalsIgnoreCase(location)) {
                this.jCBFoodLocation.setSelectedIndex(i);
            }
        }
    }
    
    private void jCBFoodLocationActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        final int dialogButton = 0;
        final int dialogResult = JOptionPane.showConfirmDialog(this, "die Ausgew\u00e4hlte Zeile l\u00f6schen?", "l\u00f6schen", dialogButton);
        if (dialogResult == 0) {
            this.dtm.removeRow(this.row);
            this.foodlist.remove(this.row);
            this.dtm.setRowCount(0);
            for (int i = 0; i < this.foodlist.size(); ++i) {
                final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).foodloc };
                this.dtm.addRow(objs);
            }
            this.clearField();
        }
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        final String updatedfoodname = this.jTextFoodName.getText();
        final String updatedfoodprice = "";
        final String updatedfoodloc = this.jCBFoodLocation.getSelectedItem().toString();
        this.foodlist.get(this.row).foodname = updatedfoodname;
        this.foodlist.get(this.row).foodprice = updatedfoodprice;
        this.foodlist.get(this.row).foodloc = updatedfoodloc;
        this.dtm.setRowCount(0);
        for (int i = 0; i < this.foodlist.size(); ++i) {
            final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).foodloc };
            this.dtm.addRow(objs);
        }
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) throws IOException {
        this.setVisible(false);
        final PrintWriter aus = new PrintWriter(new FileWriter("daten/Sortierregeln.txt", true));
        for (int i = 0; i < this.foodlist.size(); ++i) {
            aus.println(String.valueOf(this.foodlist.get(i).foodname) + "$" + this.foodlist.get(i).foodloc);
            System.out.println(String.valueOf(this.foodlist.get(i).foodname) + "$" + this.foodlist.get(i).foodloc);
        }
        aus.close();
        new VerwaltungsGui(true);
    }
    
    public static void main(final String[] args) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo info = installedLookAndFeels[i];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(Sortiervorgaben.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sortiervorgaben().setVisible(true);
                Sortiervorgaben.jTextFoodPrice.setVisible(false);
            }
        });
    }
}
