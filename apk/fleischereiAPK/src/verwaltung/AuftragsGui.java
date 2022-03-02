// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.LayoutStyle;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JFrame;

public class AuftragsGui extends JFrame
{
	static String AbholdatumBeiSuche="";
	static String AuftragsnummerBeiSuche="";
    ArrayList<Food> foodlist;
    ArrayList<String> kundenliste;
    String[] header;
    DefaultTableModel dtm;
    ArrayList<Regel> regelliste;
    ArrayList<String> autofillListe;
    public boolean visible;
    Autocomplete autoComplete;
    private static final String COMMIT_ACTION = "commit";
    int row;
    int col;
    KundenGui a;
    JComboBox comboBoxZuteilung;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton refreshButton;
    private JButton kontaktbutton;
    private JComboBox<String> jCBFoodLocation;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel9;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane4;
    private JTable jTable2;
    private JTextField jTextFoodName;
    private JTextField jTextFoodPrice;
    private JTextField jTextZubereitung;
    
    public AuftragsGui() {
        this.header = new String[] { "Produktname", "Anzahl", "Zubereitung" };
        this.visible = false;
        this.InitialisiereSortierregeln();
        this.initComponents();
        this.foodlist = new ArrayList<Food>();
        this.dtm = new DefaultTableModel(this.header, 0);
        this.jTable2.setModel(this.dtm);
        this.setLocationRelativeTo(null);
        System.out.println("kundenreload");
        this.kundenmethode();
    }
    
    public AuftragsGui(final boolean n) {
        this.header = new String[] { "Produktname", "Anzahl", "Zubereitung" };
        this.visible = false;
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
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuftragsGui().setVisible(true);
            }
        });
    }
    
    public void filllist(final ArrayList<Food> liste) {
        this.foodlist = liste;
        for (int i = 0; i < this.foodlist.size(); ++i) {
            final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).zubereitung };
            this.dtm.addRow(objs);
            this.clearField();
        }
    }
    
    public AuftragsGui(final String suche) throws IOException {
        this.header = new String[] { "Produktname", "Anzahl", "Zubereitung" };
        this.visible = false;
        final int dialogButton = 0;
        final int dialogResult = JOptionPane.showConfirmDialog(this, "Nach Auftragsnummer suchen(Yes),Nach Namen Suchen(No)", "Suche", dialogButton);
        if (dialogResult == 0) {
            final String suchfeld = JOptionPane.showInputDialog(this, "Auftragsnummer");
            if (suchfeld == null) {
                new VerwaltungsGui(true);
                return;
            }
            final ArrayList<String> suchkriterium = new ArrayList<String>();
            final File auftragsdatei = new File("daten/Auftrag.txt");
            String zeile = "";
            final BufferedReader ein = new BufferedReader(new FileReader(auftragsdatei));
            final String anzahl = "";
            while ((zeile = ein.readLine()) != null) {
                if (this.proof(zeile)) {
                    int jnameA = 0;
                    int jnameE = 0;
                    int jnummer = 0;
                    int counter = 0;
                    for (int i = 0; i < zeile.length(); ++i) {
                        if (zeile.charAt(i) == '|') {
                            if (++counter == 1) {
                                jnameA = i + 2;
                            }
                            if (counter == 2) {
                                jnameE = i - 1;
                            }
                            if (counter == 4) {
                                jnummer = i + 18;
                            }
                        }
                    }
                    if (zeile.substring(jnummer, zeile.length()).equals(suchfeld)) {
                        suchkriterium.add(zeile);
                    }
                    System.out.println("Name:" + zeile.substring(jnameA, jnameE) + "Nummer:" + zeile.substring(jnummer, zeile.length()));
                }
            }
            System.out.println("Gr\u00f6\u00dfe " + suchkriterium.size());
            if (suchkriterium.size() == 0) {
                JOptionPane.showMessageDialog(this.getContentPane(), "Es wurde nichts gefunden");
                new VerwaltungsGui(true);
            }
            if (suchkriterium.size() == 1) {
            	System.out.println(suchkriterium.get(0));
            	String auftragsinfo=suchkriterium.get(0);
            	String datum="";
            	String auftragsnummer="";
            	for (int i=0;i<auftragsinfo.length();i++){
            		if(auftragsinfo.charAt(i)=='|') {
            			this.AbholdatumBeiSuche=auftragsinfo.substring(12,i);
            			System.out.println("datum:"+datum);
            					break;
            		}
            	}
            	for(int j=auftragsinfo.length()-1;j>0;j--) {
            		System.out.println(auftragsinfo.charAt(j));
            		if(auftragsinfo.charAt(j)==':') {
            			this.AuftragsnummerBeiSuche=auftragsinfo.substring(j+2,auftragsinfo.length());
            			System.out.println("Auftragsnummer:"+auftragsnummer);
            			break;
            		}
            	}
            	
            	System.out.println("BIN HIER TEST TEST -------------");
                this.InitialisiereSortierregeln();
                this.initComponents();
                this.foodlist = new ArrayList<Food>();
                this.dtm = new DefaultTableModel(this.header, 0);
                this.jTable2.setModel(this.dtm);
                this.setLocationRelativeTo(null);
                //Hier Problem KundeSelected
                this.kundenmethode();
                this.setVisible(true);
                final String auftrag = suchkriterium.get(0);
                System.out.println("SUCHEKRITERIUM: "+suchkriterium.get(0));
                String kundeeee=suchkriterium.get(0);
                int countereins=0;
                int counterzwei=0;
                for(int i=0;i<kundeeee.length();i++) {
                 if(kundeeee.charAt(i)=='|') {
                	 if(countereins > 0 ) {
                	 counterzwei=i; 
                	 break;
                	 }
                	 else {
                	 countereins=i;
                	 }
           
                 } 
                }
                System.out.println("c1:"+countereins+"c2:"+counterzwei);
                kundeeee=kundeeee.substring(countereins+2,counterzwei-1);
                System.out.println("HIER:"+kundeeee+":HIER");
                this.kundenmethode(kundeeee);
                
                final File auftragg = new File("daten/Auftrag.txt");
                final ArrayList<Food> foodliste = new ArrayList<Food>();
                String zeilee = "";
                final BufferedReader einn = new BufferedReader(new FileReader(auftragg));
                final String anzahll = "";
                int counterr = 0;
                while ((zeilee = einn.readLine()) != null) {
                	System.out.println("DEBUG: "+zeilee);
                    if (counterr > 0) {
                        if (this.proof(zeilee)) {
                            break;
                        }
                        int counterm = 0;
                        int nameEnde = 0;
                        int anzahlEnde = 0;
                        final int headercounter = 0;
                        for (int j = 0; j < zeilee.length(); ++j) {
                            if (zeilee.charAt(j) == '$' || zeilee.charAt(j) == '§') {
                                if (++counterm == 1) {
                                    nameEnde = j;
                                }
                                if (counterm == 2) {
                                    anzahlEnde = j;
                                    break;
                                }
                            }
                        }
                        final String name = new StringBuilder(String.valueOf(zeilee.substring(0, nameEnde))).toString();
                        final String anzahl2 = new StringBuilder(String.valueOf(zeilee.substring(nameEnde + 1, anzahlEnde))).toString();
                        final String zubereitung = new StringBuilder(String.valueOf(zeilee.substring(anzahlEnde + 1, zeilee.length()))).toString();
                        this.foodlist.add(new Food(name, zubereitung, anzahl2, true));
                        final Object[] objs = { name, anzahl2, zubereitung };
                        this.dtm.addRow(objs);
                    }
                    if (zeilee.equals(auftrag)) {
                        ++counterr;
                    }
                }
                this.deleteunnessesary(auftrag);
            }
            if (suchkriterium.size() > 1) {
                JOptionPane.showMessageDialog(this.getContentPane(), "Es wurden mehrere gefunden");
                new VerwaltungsGui(true);
            }
        }
        if (dialogResult == 1) {
            final String suchfeldName = JOptionPane.showInputDialog(this, "Name");
            if (suchfeldName == null) {
                new VerwaltungsGui(true);
                return;
            }
            final ArrayList<String> suchkriterium = new ArrayList<String>();
            final File auftragsdatei = new File("daten/Auftrag.txt");
            String zeile = "";
            final BufferedReader ein = new BufferedReader(new FileReader(auftragsdatei));
            final String anzahl = "";
            while ((zeile = ein.readLine()) != null) {
                if (this.proof(zeile)) {
                    int jnameA = 0;
                    int jnameE = 0;
                    int jnummer = 0;
                    int counter = 0;
                    for (int i = 0; i < zeile.length(); ++i) {
                        if (zeile.charAt(i) == '|') {
                            if (++counter == 1) {
                                jnameA = i + 2;
                            }
                            if (counter == 2) {
                                jnameE = i - 1;
                            }
                            if (counter == 4) {
                                jnummer = i + 18;
                            }
                        }
                    }
                    System.out.println(zeile.substring(jnameA, jnameE).toUpperCase());
                    System.out.println(suchfeldName.toUpperCase());
                    if (!zeile.substring(jnameA, jnameE).toUpperCase().contains(suchfeldName.toUpperCase())) {
                        continue;
                    }
                    System.out.println("bin hier HALLO");
                    suchkriterium.add(zeile);
                }
            }
            if (suchkriterium.size() == 0) {
                JOptionPane.showMessageDialog(this.getContentPane(), "Es wurde nichts gefunden");
                new VerwaltungsGui(true);
            }
            if (suchkriterium.size() == 1) {
            	System.out.println("bin hier SUCHKRITERIUM 2");
                this.InitialisiereSortierregeln();
                this.initComponents();
                this.foodlist = new ArrayList<Food>();
                this.dtm = new DefaultTableModel(this.header, 0);
                this.jTable2.setModel(this.dtm);
                this.setLocationRelativeTo(null);
                this.kundenmethode();
                this.setVisible(true);
                final String auftrag = suchkriterium.get(0);
                System.out.println("auftrag :"+auftrag);
                for (int i=0;i<auftrag.length();i++){
            		if(auftrag.charAt(i)=='|') {
            			this.AbholdatumBeiSuche=auftrag.substring(12,i);
            			//System.out.println("datum:"+datum);
            					break;
            		}
            	}
            	for(int j=auftrag.length()-1;j>0;j--) {
            		System.out.println(auftrag.charAt(j));
            		if(auftrag.charAt(j)==':') {
            			this.AuftragsnummerBeiSuche=auftrag.substring(j+2,auftrag.length());
            			//System.out.println("Auftragsnummer:"+auftragsnummer);
            			break;
            		}
            	}
                final ArrayList<String> neuerAuftragdoc = new ArrayList<String>();
                final File auftragg = new File("daten/Auftrag.txt");
                final ArrayList<Food> foodliste = new ArrayList<Food>();
                String zeilee = "";
                final BufferedReader einn = new BufferedReader(new FileReader(auftragg));
                final String anzahll = "";
                int counterr = 0;
                while ((zeilee = einn.readLine()) != null) {
                    if (counterr > 0) {
                        if (this.proof(zeilee)) {
                            break;
                        }
                        int counterm = 0;
                        int nameEnde = 0;
                        int anzahlEnde = 0;
                        final int headercounter = 0;
                        for (int j = 0; j < zeilee.length(); ++j) {
                            if (zeilee.charAt(j) == '$' || zeilee.charAt(j) == '§') {
                                if (++counterm == 1) {
                                    nameEnde = j;
                                }
                                if (counterm == 2) {
                                    anzahlEnde = j;
                                    break;
                                }
                            }
                        }
                        final String name = new StringBuilder(String.valueOf(zeilee.substring(0, nameEnde))).toString();
                        final String anzahl2 = new StringBuilder(String.valueOf(zeilee.substring(nameEnde + 1, anzahlEnde))).toString();
                        final String zubereitung = new StringBuilder(String.valueOf(zeilee.substring(anzahlEnde + 1, zeilee.length()))).toString();
                        this.foodlist.add(new Food(name, zubereitung, anzahl2, true));
                        final Object[] objs = { name, anzahl2, zubereitung };
                        this.dtm.addRow(objs);
                    }
                    if (zeilee.equals(auftrag)) {
                        ++counterr;
                    }
                }
                this.deleteunnessesary(auftrag);
            }
            if (suchkriterium.size() > 1) {
                JOptionPane.showMessageDialog(this.getContentPane(), "Es wurde mehr als ein ergebnis gefunden");
                new VerwaltungsGui(true);
            }
        }
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, k = 0; k < length; ++k) {
                final UIManager.LookAndFeelInfo info = installedLookAndFeels[k];
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex4);
        }
    }
    
    private void deleteunnessesary(final String auftrag) throws IOException {
        final ArrayList<String> neuerAuftragdoc = new ArrayList<String>();
        final File auftragg = new File("daten/Auftrag.txt");
        String zeilee = "";
        final BufferedReader einn = new BufferedReader(new FileReader(auftragg));
        final String anzahll = "";
        int counterr = 0;
        while ((zeilee = einn.readLine()) != null) {
            if (counterr == 2) {
                neuerAuftragdoc.add(zeilee);
            }
            if (this.proof(zeilee) && counterr == 1) {
                counterr = 2;
                neuerAuftragdoc.add(zeilee);
            }
            if (counterr < 1) {
                if (zeilee.equals(auftrag)) {
                    counterr = 1;
                }
                else {
                    neuerAuftragdoc.add(zeilee);
                }
            }
        }
        System.out.println("Auftrag:" + auftrag);
        final PrintWriter aus = new PrintWriter(new FileWriter("daten/Auftrag.txt"));
        aus.close();
        final PrintWriter aus2 = new PrintWriter(new FileWriter("daten/Auftrag.txt", true));
        for (final String a : neuerAuftragdoc) {
            System.out.println(a);
            aus2.println(a);
        }
        aus2.close();
    }
    
    private ArrayList<Auftrag> auftr\u00e4geextract() throws IOException {
        final File auftrag = new File("daten/Auftrag.txt");
        final ArrayList<Food> foodliste = new ArrayList<Food>();
        String zeile = "";
        final BufferedReader ein = new BufferedReader(new FileReader(auftrag));
        final String anzahl = "";
        final int countAuftrag = 0;
        while ((zeile = ein.readLine()) != null) {
            if (this.proof(zeile)) {
                final Auftrag auf = new Auftrag();
                int jnameA = 0;
                int jnameE = 0;
                int jnummer = 0;
                int endeAdresse = 0;
                int counter = 0;
                for (int i = 0; i < zeile.length(); ++i) {
                    if (zeile.charAt(i) == '|') {
                        if (++counter == 1) {
                            jnameA = i + 2;
                        }
                        if (counter == 3) {
                            endeAdresse = i - 1;
                        }
                        if (counter == 2) {
                            jnameE = i - 1;
                        }
                        if (counter == 4) {
                            jnummer = i + 18;
                        }
                    }
                }
                auf.k = new Kunde(zeile.substring(jnameA, jnameE), zeile.substring(jnameE + 3, endeAdresse), zeile.substring(endeAdresse + 3, jnummer - 19));
                System.out.println(auf.k.name);
                System.out.println(auf.k.adresse);
                System.out.println(auf.k.telefonnummer);
                System.out.println(auf.auftragsnummer);
            }
            else {
                int counter2 = 0;
                int nameEnde = 0;
                int anzahlEnde = 0;
                final int headercounter = 0;
                for (int j = 0; j < zeile.length(); ++j) {
                    if (zeile.charAt(j) == '$' || zeile.charAt(j) == '§') {
                        if (++counter2 == 1) {
                            nameEnde = j;
                        }
                        if (counter2 == 2) {
                            anzahlEnde = j;
                            break;
                        }
                    }
                }
                foodliste.add(new Food(zeile.substring(0, nameEnde), zeile.substring(nameEnde + 1, anzahlEnde), zeile.substring(anzahlEnde + 1, zeile.length())));
                System.out.println(zeile.substring(0, nameEnde));
            }
        }
        return null;
    }
    
    private void InitialisiereSortierregeln() {
        this.regelliste = new ArrayList<Regel>();
        this.autofillListe = new ArrayList<String>();
        String zeile = "";
        try {
            final BufferedReader ein = new BufferedReader(new FileReader("daten/Sortierregeln.txt"));
            while ((zeile = ein.readLine()) != null) {
                for (int i = 0; i < zeile.length(); ++i) {
                    if (zeile.charAt(i) == '$') {
                        this.regelliste.add(new Regel(zeile.substring(0, i), zeile.substring(i, zeile.length())));
                        this.autofillListe.add(zeile.substring(0, i));
                        break;
                    }
                }
            }
            ein.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    private void kundenmethode() {
        this.kundenliste = new ArrayList<String>();
        String zeile = "";
        
        try {
            final BufferedReader ein = new BufferedReader(new FileReader("daten/Kontakte.txt"));
           
            while ((zeile = ein.readLine()) != null) {
            	int counter=0;
            	String kontakt="";
                for (int i = 0; i < zeile.length(); ++i) {
                	System.out.println("zeile"+zeile);
                    if(counter==1 && zeile.charAt(i)=='$') {
                    	kontakt+=zeile.substring(i+1,zeile.length());
                    	System.out.println("zweiter Kontakt"+kontakt);
                    	this.kundenliste.add(kontakt);
                    	counter=0;
                    	
                    }
                    if (zeile.charAt(i) == '$' && counter==0) {
                        kontakt=zeile.substring(0,i)+":";
                        System.out.println("erster Kontakt"+kontakt);
                    	//this.kundenliste.add(zeile.substring(0, i)+"HIER");
                        System.out.println(zeile.substring(0, i));
                        counter=1;
                        
                    }
                    	
                }
                System.out.println(this.kundenliste);
            }
            ein.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        Collections.sort(this.kundenliste, new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        final String[] meineKunden = new String[this.kundenliste.size()];
        for (int j = 0; j < this.kundenliste.size(); ++j) {
            meineKunden[j] = this.kundenliste.get(j);
        }
        //HIER GEÄNDERT
        DefaultComboBoxModel<String> test= new DefaultComboBoxModel<String>();
        
        this.jCBFoodLocation.setModel(new DefaultComboBoxModel<String>(meineKunden));
        //this.jCBFoodLocation.setModel(new JComboBox<String>(meineKunden));
    }
    private void kundenmethode(String selected) {
        this.kundenliste = new ArrayList<String>();
        String zeile = "";
        try {
            final BufferedReader ein = new BufferedReader(new FileReader("daten/Kontakte.txt"));
            while ((zeile = ein.readLine()) != null) {
                for (int i = 0; i < zeile.length(); ++i) {
                    if (zeile.charAt(i) == '$') {
                        this.kundenliste.add(zeile.substring(0, i));
                        System.out.println(zeile.substring(0, i));
                        break;
                    }
                  
                }
            }
            ein.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        Collections.sort(this.kundenliste, new Comparator<String>() {
            @Override
            public int compare(final String s1, final String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        final String[] meineKunden = new String[this.kundenliste.size()];
        for (int j = 0; j < this.kundenliste.size(); ++j) {
            meineKunden[j] = this.kundenliste.get(j);
        }
        DefaultComboBoxModel<String> listeKunden=new DefaultComboBoxModel<String>(meineKunden);
        listeKunden.setSelectedItem(selected);
       
        this.jCBFoodLocation.setModel(listeKunden);
    }
    
    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jPanel1 = new JPanel();
        this.jLabel2 = new JLabel();
        this.jLabel9 = new JLabel();
        this.jTextFoodName = new JTextField();
        final ArrayList<String> keywords = this.autofillListe;
        this.autoComplete = new Autocomplete(this.jTextFoodName, keywords);
        this.jTextFoodName.getDocument().addDocumentListener(this.autoComplete);
        this.jTextZubereitung = new JTextField();
        this.jLabel3 = new JLabel();
        this.jTextFoodPrice = new JTextField();
        this.jLabel4 = new JLabel();
        this.jCBFoodLocation = new JComboBox<String>();
        (this.comboBoxZuteilung = new JComboBox()).setVisible(false);
        this.jPanel2 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jScrollPane4 = new JScrollPane();
        this.kontaktbutton = new JButton();
        this.jTable2 = new JTable();
        this.refreshButton = new JButton();
        this.jLabel1.setText("jLabel1");
        this.setDefaultCloseOperation(3);
        this.setTitle("Auftrag hinzufuegen");
        this.setResizable(false);
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel2.setText("Produktname");
        this.jLabel9.setText("Zubereitung");
        this.jLabel3.setText("Anzahl");
        this.jLabel4.setText("Kunde");
        this.comboBoxZuteilung.setModel(new DefaultComboBoxModel<String>(new String[] { "Vorbereitung","Wuerste", "Haehnchen/Pute", "Ente/Gans", "Wild", "Schwein", "Rind/Kalb", "Lamm", "Aufschnitt","Kueche", "Salat/Kaese" }));
        this.comboBoxZuteilung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AuftragsGui.this.jCBFoodLocationActionPerformed(evt);
            }
        });
        this.jCBFoodLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AuftragsGui.this.jCBFoodLocationActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addComponent(this.jLabel3).addComponent(this.jLabel9)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTextFoodName).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextFoodPrice, -2, 94, -2).addComponent(this.jTextZubereitung, -2, 94, -2).addGap(0, 0, 32767)))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jLabel4).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jCBFoodLocation, -2, 230, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.comboBoxZuteilung, -2, 160, -2).addGap(0, 0, 32767))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextFoodName, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel9).addComponent(this.jTextZubereitung, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel3).addComponent(this.jTextFoodPrice, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel4).addComponent(this.jCBFoodLocation, -2, -1, -2)).addComponent(this.comboBoxZuteilung, -2, -1, -2).addContainerGap(14, 32767)));
        this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
        this.jButton1.setText("Add");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    AuftragsGui.this.jButton1ActionPerformed(evt);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.jButton2.setText("Delete");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AuftragsGui.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("Update");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                AuftragsGui.this.jButton3ActionPerformed(evt);
            }
        });
        this.refreshButton.setText("refresh");
        this.refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                this.refreshActionPerformed(evt);
            }
            
            private void refreshActionPerformed(final ActionEvent evt) {
                AuftragsGui.this.kundenmethode();
            }
        });
        this.jButton4.setText("Abgabe");
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    AuftragsGui.this.jButton4ActionPerformed(evt);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.kontaktbutton.setText("+ kontakt");
        this.kontaktbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                this.kontaktbuttonActionPerformed(evt);
            }
            
            private void kontaktbuttonActionPerformed(final ActionEvent evt) {
                AuftragsGui.this.a = new KundenGui(false);
            }
        });
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jButton1, -2, 95, -2).addGap(18, 18, 18).addComponent(this.jButton2, -2, 105, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, 32767).addComponent(this.jButton3, -2, 109, -2).addGap(18, 18, 18).addComponent(this.jButton4, -2, 107, -2).addGap(18, 18, 18).addComponent(this.kontaktbutton, -2, 107, -2).addGap(18, 18, 18).addComponent(this.refreshButton, -2, 107, -2).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1, -1, -1, 32767).addComponent(this.jButton2, -1, -1, 32767).addComponent(this.jButton3, -1, -1, 32767).addComponent(this.jButton4, -1, -1, 32767).addComponent(this.kontaktbutton, -1, -1, 32767).addComponent(this.refreshButton, -1, -1, 32767)).addContainerGap()));
        this.jTable2.setModel(new DefaultTableModel(new Object[][] { new Object[4], new Object[4], new Object[4], new Object[4] }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                AuftragsGui.this.jTable2MouseClicked(evt);
            }
        });
        this.jScrollPane4.setViewportView(this.jTable2);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addComponent(this.jScrollPane4).addComponent(this.jPanel2, -1, -1, 32767)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jPanel1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel2, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane4, -2, 175, -2).addGap(13, 13, 13)));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) throws IOException {
        final String foodname = this.jTextFoodName.getText();
        if (!this.visible) {
            for (final String elem : this.autofillListe) {
                if (foodname.toUpperCase().equals(elem.toUpperCase())) {
                    final String foodprice = this.jTextFoodPrice.getText();
                    final String zubereitung = this.jTextZubereitung.getText();
                    this.foodlist.add(new Food(foodname, zubereitung, foodprice, true));
                    this.dtm.setRowCount(0);
                    for (int i = 0; i < this.foodlist.size(); ++i) {
                        final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).zubereitung };
                        this.dtm.addRow(objs);
                        this.clearField();
                    }
                    return;
                }
            }
            this.comboBoxZuteilung.setVisible(true);
            this.comboBoxZuteilung.showPopup();
            this.visible = true;
            return;
        }
        System.out.println("Bin hier");
        this.comboBoxZuteilung.setVisible(false);
        this.visible = false;
        this.autofillListe.add(this.comboBoxZuteilung.getSelectedItem().toString());
        final PrintWriter aus = new PrintWriter(new FileWriter("daten/Sortierregeln.txt", true));
        aus.println(String.valueOf(foodname) + "$" + this.comboBoxZuteilung.getSelectedItem().toString());
        aus.close();
        final String foodprice2 = this.jTextFoodPrice.getText();
        final String zubereitung2 = this.jTextZubereitung.getText();
        this.autoComplete.addElem(this.comboBoxZuteilung.getSelectedItem().toString());
        this.foodlist.add(new Food(foodname, zubereitung2, foodprice2, true));
        this.dtm.setRowCount(0);
        for (int j = 0; j < this.foodlist.size(); ++j) {
            final Object[] objs2 = { this.foodlist.get(j).foodname, this.foodlist.get(j).foodprice, this.foodlist.get(j).zubereitung };
            this.dtm.addRow(objs2);
            this.clearField();
        }
    }
    
    private void clearField() {
        this.jTextFoodName.requestFocus();
        this.jTextFoodName.setText("");
        this.jTextFoodPrice.setText("");
        this.jTextZubereitung.setText("");
    }
    
    private void jTable2MouseClicked(final MouseEvent evt) {
        this.row = this.jTable2.getSelectedRow();
        this.col = this.jTable2.getColumnCount();
        System.out.println(String.valueOf(this.row) + "," + this.col);
        this.jTextFoodName.setText(this.dtm.getValueAt(this.row, 0).toString());
        this.jTextFoodPrice.setText(this.dtm.getValueAt(this.row, 1).toString());
        this.jTextZubereitung.setText(this.dtm.getValueAt(this.row, 2).toString());
    }
    
    private void jCBFoodLocationActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        final int dialogButton = 0;
        final int dialogResult = JOptionPane.showConfirmDialog(this, "Delete this data", "Delete", dialogButton);
        if (dialogResult == 0) {
            this.dtm.removeRow(this.row);
            this.foodlist.remove(this.row);
            this.dtm.setRowCount(0);
            for (int i = 0; i < this.foodlist.size(); ++i) {
                final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).zubereitung };
                this.dtm.addRow(objs);
            }
            this.clearField();
        }
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        final String updatedfoodname = this.jTextFoodName.getText();
        final String updatedfoodprice = this.jTextFoodPrice.getText();
        final String updatedfoodloc = this.jCBFoodLocation.getSelectedItem().toString();
        final String updatedzubereitung = this.jTextZubereitung.getText();
        this.foodlist.get(this.row).foodname = updatedfoodname;
        this.foodlist.get(this.row).foodprice = updatedfoodprice;
        this.foodlist.get(this.row).zubereitung = updatedzubereitung;
        this.dtm.setRowCount(0);
        for (int i = 0; i < this.foodlist.size(); ++i) {
            final Object[] objs = { this.foodlist.get(i).foodname, this.foodlist.get(i).foodprice, this.foodlist.get(i).zubereitung };
            this.dtm.addRow(objs);
        }
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) throws IOException {
        final BufferedReader ein = new BufferedReader(new FileReader("daten/Kontakte.txt"));
        String zeile = "";
        String kontakt = "";
        try {
            while ((zeile = ein.readLine()) != null) {
                for (int i = 0; i < zeile.length(); ++i) {
                	if (zeile.charAt(i) == '$' && zeile.substring(0, i).equals(this.jCBFoodLocation.getSelectedItem().toString().split(":")[0])) {
                        kontakt = zeile;
                        break;
                    }
                }
            }
            ein.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        kontakt = kontakt.replace("$", " | ");
        System.out.println(kontakt);
        String abholDatum="";
        if(AbholdatumBeiSuche.length() > 0) {
        abholDatum = JOptionPane.showInputDialog(this, "Abholdatum im Format DD.MM.YYYY:\nletztes Datum: "+AbholdatumBeiSuche);
        }
        else {
        abholDatum = JOptionPane.showInputDialog(this, "Abholdatum im Format DD.MM.YYYY:");	
        }
        if (abholDatum.length() != 10 || abholDatum.charAt(2) != '.' || abholDatum.charAt(5) != '.') {
            JOptionPane.showMessageDialog(this.getContentPane(), "Bitte im DD.MM.YYYY Format: z.B 18.11.2000");
            return;
        }
        if (abholDatum == null) {
            return;
        }
        String auftragsNummer="";
        if(AbholdatumBeiSuche.length() > 0) {
        auftragsNummer = JOptionPane.showInputDialog(this, "Auftragsnummer\n letzte Nummer: "+AuftragsnummerBeiSuche);
        }
        else {
        auftragsNummer = JOptionPane.showInputDialog(this, "Auftragsnummer");	
        }

        //Hier
        final File auftragsdatei = new File("daten/Auftrag.txt");
        final BufferedReader einn = new BufferedReader(new FileReader(auftragsdatei));
        final String anzahl = "";
        ArrayList<Integer> auftragsnummern=new ArrayList<>();
        while ((zeile = einn.readLine()) != null) {
            if (this.proof(zeile)) {
                int jnameA = 0;
                int jnameE = 0;
                int jnummer = 0;
                int counter = 0;
                for (int i = 0; i < zeile.length(); ++i) {
                    if (zeile.charAt(i) == '|') {
                        if (++counter == 1) {
                            jnameA = i + 2;
                        }
                        if (counter == 2) {
                            jnameE = i - 1;
                        }
                        if (counter == 4) {
                            jnummer = i + 18;
                        }
                    }
                }
                System.out.println("Nummer:" + zeile.substring(jnummer, zeile.length()));
                auftragsnummern.add(Integer.parseInt(zeile.substring(jnummer, zeile.length())));
             
            }}
        if(auftragsnummern.contains(Integer.parseInt(auftragsNummer))) {
          System.out.println("Auftragsnummer existiert bereits");
          JOptionPane.showMessageDialog(this.jButton4, "Auftragsnummer existiert bereits", "Auftragsnummer Fehler", 1);
          einn.close();
          return;
        }
        //Hier
        
        
        if (auftragsNummer == null) {
            return;
        }
        
        JOptionPane.showMessageDialog(this.jButton4, "Auftrag wurde hinzugefuegt", "Auftrag wurde hinzugefuegt", 1);
        final PrintWriter aus = new PrintWriter(new FileWriter("daten/Auftrag.txt", true));
        aus.println("Auftrag zum " + abholDatum + " | " + kontakt + " | Auftragsnummer: " + auftragsNummer);
        for (int j = 0; j < this.foodlist.size(); ++j) {
            aus.println(String.valueOf(this.foodlist.get(j).foodname) + "$" + this.foodlist.get(j).foodprice + "§" + this.foodlist.get(j).zubereitung);
        }
        aus.close();
        this.setVisible(false);
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
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(AuftragsGui.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuftragsGui().setVisible(true);
            }
        });
    }
    
    public boolean proof(final String a) {
        int counter = 0;
        if (a.charAt(0) != 'A') {
            return false;
        }
        ++counter;
        if (a.charAt(1) != 'u') {
            return false;
        }
        ++counter;
        if (a.charAt(2) != 'f') {
            return false;
        }
        ++counter;
        if (a.charAt(3) != 't') {
            return false;
        }
        ++counter;
        if (a.charAt(4) != 'r') {
            return false;
        }
        ++counter;
        if (a.charAt(5) != 'a') {
            return false;
        }
        ++counter;
        if (a.charAt(6) != 'g') {
            return false;
        }
        ++counter;
        if (a.charAt(7) != ' ') {
            return false;
        }
        ++counter;
        if (a.charAt(8) != 'z') {
            return false;
        }
        ++counter;
        if (a.charAt(9) != 'u') {
            return false;
        }
        ++counter;
        if (a.charAt(10) == 'm') {
            ++counter;
            return counter == 11;
        }
        return false;
    }
}
