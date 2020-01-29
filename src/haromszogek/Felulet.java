package haromszogek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.ConditionalFeature;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Felulet extends JFrame{
    
    private Container ablak; 
    private JButton btnAdatokBetoltese;
    private JPanel pnlHibak, pnlHaromszog, pnlAdatok;
    private JList lstHibak, lstHaromszog;
    private DefaultListModel dlmHibak,dlmHaromszog;
    
    private JLabel lblKerulet, lblTerulet;
    
    private ArrayList<DHaromszog> hlist;
    
    private JFileChooser fcFajl;
    
    public Felulet(){
        initComponets();
    }
    
    public void initComponets(){
        this.setTitle("Derékszögű háromszögek");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.ablak = this.getContentPane();
        this.ablak.setLayout(null);
        
        /* --- --- --- BEGIN btnAdadtokBetoltese --- --- --- */
        this.btnAdatokBetoltese = new JButton();
        this.btnAdatokBetoltese.setSize(300, 30);
        this.btnAdatokBetoltese.setLocation(20, 20);
        this.btnAdatokBetoltese.setText("Adatok Betöltése");
        
        this.btnAdatokBetoltese.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnAdatokBetolteseKattintas(ae);
            }
        });
        
        this.ablak.add(this.btnAdatokBetoltese);
        /* --- --- --- END btnAdadtokBetoltese --- --- --- */
        
        /* --- --- --- BEGIN pnlHibak --- --- --- */
        this.pnlHibak = new JPanel();
        this.pnlHibak.setLayout(null);
        this.pnlHibak.setSize(700, 150);
        this.pnlHibak.setLocation(20, 70);
        this.pnlHibak.setBorder(new TitledBorder("Hibák a kiválasztott állományban"));
        this.ablak.add(this.pnlHibak);
        /* --- --- --- END pnlHibak --- --- --- */
        
        /* --- --- --- BEGIN lstHibak --- --- --- */
        this.dlmHibak =  new DefaultListModel();
        this.lstHibak = new JList(dlmHibak);
        JScrollPane listScroller = new JScrollPane(this.lstHibak);
        listScroller.setPreferredSize(new Dimension(650,100));
        this.lstHibak.setSize(650, 100);
        this.lstHibak.setLocation(20,30);
        this.pnlHibak.add(this.lstHibak);
        /* --- --- --- END lstHibak --- --- --- */
        
        /* --- --- --- BEGIN pnlHaromszog --- --- --- */
        this.pnlHaromszog = new JPanel();
        this.pnlHaromszog.setLayout(null);
        this.pnlHaromszog.setSize(340, 150);
        this.pnlHaromszog.setLocation(20, 240);
        this.pnlHaromszog.setBorder(new TitledBorder("Derékszögű háromszögek"));
        this.ablak.add(this.pnlHaromszog);
        /* --- --- --- END pnlHaromszog --- --- --- */

        /* --- --- --- BEGIN lstHaromszog --- --- --- */
        this.dlmHaromszog =  new DefaultListModel();
        this.lstHaromszog = new JList(dlmHaromszog);
        JScrollPane listScrollerHaromszog = new JScrollPane(this.lstHaromszog);
        listScrollerHaromszog.setPreferredSize(new Dimension(650,100));
        this.lstHaromszog.setSize(300, 100);
        this.lstHaromszog.setLocation(20,30);
        this.pnlHaromszog.add(this.lstHaromszog);
        
        this.lstHaromszog.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                lstHaromszogValueChanged(lse);
            }
        });
        
        /* --- --- --- END lstHaromszog --- --- --- */
        
        /* --- --- --- BEGIN pnlAdatok --- --- --- */
        this.pnlAdatok = new JPanel();
        this.pnlAdatok.setLayout(null);
        this.pnlAdatok.setSize(340, 150);
        this.pnlAdatok.setLocation(380, 240);
        this.pnlAdatok.setBorder(new TitledBorder("Kiválasztott derékszögű háromszög adatai"));
        this.ablak.add(this.pnlAdatok);

        this.lblKerulet = new JLabel();
        this.lblKerulet.setText("Kerület: ");
        this.lblKerulet.setSize(300,30);
        this.lblKerulet.setLocation(20, 20);
        this.pnlAdatok.add(this.lblKerulet);

        this.lblTerulet = new JLabel();
        this.lblTerulet.setText("Terület: ");
        this.lblTerulet.setSize(300,30);
        this.lblTerulet.setLocation(20, 50);
        this.pnlAdatok.add(this.lblTerulet);
        
        /* --- --- --- END pnlAdatok --- --- --- */

        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void btnAdatokBetolteseKattintas(ActionEvent ae){
        this.fcFajl = new JFileChooser();
        if (fcFajl.showDialog(this, "Fájl megnyitása") != -1){
            FajlOlvas(fcFajl.getSelectedFile().toString());
        }
    }
    
    public void lstHaromszogValueChanged(ListSelectionEvent lse){
        this.lblKerulet.setText(String.format("Kerület: %.2f", hlist.get(lstHaromszog.getSelectedIndex() ).kerulet()) );
        this.lblTerulet.setText(String.format("Terület: %.2f", hlist.get(lstHaromszog.getSelectedIndex() ).terulet()) );
    }
    
    private void FajlOlvas(String fajlNev){
        dlmHaromszog.clear();
        dlmHibak.clear();
        hlist = new ArrayList<>();
        
        try{
            Scanner sc = new Scanner(new File(fajlNev));
            int sorszam = 1;
            while (sc.hasNext()){
                try{
                    DHaromszog h = new DHaromszog(sc.nextLine(),sorszam++);
                    hlist.add(h);
                    
                    dlmHaromszog.addElement(h.toString());
                }catch (Exception e){
                    dlmHibak.addElement(e.getMessage());
                }
            }
            
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("Hiba: " + e);
        }
    }
    
    
}
