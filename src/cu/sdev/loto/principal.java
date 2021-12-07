/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.sdev.loto;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Yaisel
 */
public class principal extends javax.swing.JFrame {

    // Variables globales
    utiles ut = new utiles();
    String texto = "", nombrelista = "", nombrelistaB = "", html = "", filaTabla = "", totalFilaTablaLista = "", totalFilaTablaBote = "", filasTablaLista = "", filasTablaBote = "";
    double valorVenta = 0, valorComision = 0, valorGanadores = 0, valorNeto = 0, valorVentaB = 0, valorComisionB = 0, valorGanadoresB = 0, valorNetoB = 0;
    double TotalVenta = 0, TotalComision = 0, TotalGanadores = 0, TotalNeto = 0, TotalVentaB = 0, TotalComisionB = 0, TotalGanadoresB = 0, TotalNetoB = 0;
    double pierde = 0.0D;
    double pierdeB = 0.0D;
    double gane = 0.0D;
    double ganeB = 0.0D;

    double TotalPierde = 0.0D;
    double TotalPierdeB = 0.0D;

    double apFB = 0.0D;
    double apFL = 0.0D;
    double apCB = 0.0D;
    double apCL = 0.0D;
    double apCenB = 0.0D;
    double apCenL = 0.0D;
    double apPB = 0.0D;
    double apPL = 0.0D;
    double apCanL = 0.0D;
    double apCanB = 0.0D;
    double apPFl = 0.0D;
    double apPFB = 0.0D;
    double apPKb = 0.0D;
    double apPKL = 0.0D;
    double apMb = 0.0D;
    double apML = 0.0D;

    double apCanNB = 0;
    double apCanNL = 0;

    double brutoBN = 0;
    double brutoLN = 0;

    Directorio dir = new Directorio();
    ArrayList<String> jugada = new ArrayList<>();
    ArrayList<String> apuesta = new ArrayList<>();
    ArrayList<Double> dineroApuesta = new ArrayList<>();
    ArrayList<String> listaBote = new ArrayList<>();
    ArrayList<Integer> preciosLista = new ArrayList<>();
    ArrayList<Integer> preciosBote = new ArrayList<>();
    ArrayList<Integer> preciosLimBote = new ArrayList<>();
    ArrayList<Integer> preciosLimLista = new ArrayList<>();
    ArrayList<String> numerosLimLista = new ArrayList<>();
    ArrayList<String> numerosLimBote = new ArrayList<>();

    /**
     * Creates new form principal
     *
     * @throws java.lang.ClassNotFoundException
     */
    public principal() throws ClassNotFoundException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        //Para ponerlo en el medio
        setLocationRelativeTo(this);
        // Para bloquear el area de texto
        jTextArea1.setEditable(false);
        //Para cambiarle el icono
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("ic_launcher.png")));

        //Cargo la configuracoin del fichero condig
        //JOptionPane.showMessageDialog(null, dir.get().getPath() + "\\config.txt");
        String[] config = ut.loadConfiguration("config.txt");
        String[] configLim = ut.loadConfiguration("configLim.txt");

        String[] confP = config[0].split(",");
        String[] confT = config[1].split(",");
        String[] confLimP = configLim[0].split("<");
        String[] confLimT = configLim[1].split("<");

        // Config precios App
        tfCPBF.setText(confP[0]);
        tfCPBC.setText(confP[1]);
        tfCPBP.setText(confP[2]);
        tfCPBCent.setText(confP[3]);
        tfPFPB.setText(confP[4]);
        tfPKPB.setText(confP[5]);

        tfCPFF.setText(confP[6]);
        tfCPFC.setText(confP[7]);
        tfCPFP.setText(confP[8]);
        tfCPFCent.setText(confP[9]);
        tfPFPL.setText(confP[10]);
        tfPKPL.setText(confP[11]);

        //Config Topes App
        tfCTBF.setText(confT[0]);
        tfCTBC.setText(confT[1]);
        tfCTBP.setText(confT[2]);
        tfCTBCent.setText(confT[3]);
        tfPFTB.setText(confT[4]);
        tfPKTB.setText(confT[5]);

        tfCTLF.setText(confT[6]);
        tfCTLC.setText(confT[7]);
        tfCTLP.setText(confT[8]);
        tfCTLCent.setText(confT[9]);
        tfPFTL.setText(confT[10]);
        tfPKTL.setText(confT[11]);

        //Conf numeros Limitados
        limFijo.setText(confLimP[0]);
        limCorrido.setText(confLimP[1]);
        limParlet.setText(confLimP[2]);
        limCentena.setText(confLimP[3]);
        limPlayFall.setText(confLimP[4]);
        limPik.setText(confLimP[5]);

        limFijoLista.setText(confLimP[6]);
        limCorridoLista.setText(confLimP[7]);
        limParletLista.setText(confLimP[8]);
        limCentenaLista.setText(confLimP[9]);
        limPlayFLista.setText(confLimP[10]);
        limPikLista.setText(confLimP[11]);

        //Conf Topes Limitados
        tLimFijo.setText(confLimT[0]);
        tLimCorrido.setText(confLimT[1]);
        tLimParlet.setText(confLimT[2]);
        tLimCentena.setText(confLimT[3]);
        tlimPlayFall.setText(confLimT[4]);
        tLimPik.setText(confLimT[5]);

        tLimFijoLista.setText(confLimT[6]);
        tLimCorridolista.setText(confLimT[7]);
        tLimpParletLista.setText(confLimT[8]);
        tLimCentenaLista.setText(confLimT[9]);
        tLimPlayFallLista.setText(confLimT[10]);
        tLimPikLista.setText(confLimT[11]);

        //Config porcientos
        //tfPCB.setText(confT[8]);
        //tfPCL.setText(confT[9]);
        //Lleno los precios del bote y de la lista
        preciosBote.add(Integer.valueOf(tfCPBF.getText()));
        preciosBote.add(Integer.valueOf(tfCPBC.getText()));
        preciosBote.add(Integer.valueOf(tfCPBP.getText()));
        preciosBote.add(Integer.valueOf(tfCPBCent.getText()));
        preciosBote.add(Integer.valueOf(tfPFPB.getText()));
        preciosBote.add(Integer.valueOf(tfPKPB.getText()));

        //Lleno los precios del bote y del bote
        preciosLista.add(Integer.valueOf(tfCPFF.getText()));
        preciosLista.add(Integer.valueOf(tfCPFC.getText()));
        preciosLista.add(Integer.valueOf(tfCPFP.getText()));
        preciosLista.add(Integer.valueOf(tfCPFCent.getText()));
        preciosLista.add(Integer.valueOf(tfPFPL.getText()));
        preciosLista.add(Integer.valueOf(tfPKPL.getText()));

        //Lleno los precios del bote
        preciosLimBote.add(Integer.valueOf(tLimFijo.getText()));
        preciosLimBote.add(Integer.valueOf(tLimCorrido.getText()));
        preciosLimBote.add(Integer.valueOf(tLimParlet.getText()));
        preciosLimBote.add(Integer.valueOf(tLimCentena.getText()));
        preciosLimBote.add(Integer.valueOf(tlimPlayFall.getText()));
        preciosLimBote.add(Integer.valueOf(tLimPik.getText()));

        //Lleno los precios de la lista
        preciosLimLista.add(Integer.valueOf(tLimFijoLista.getText()));
        preciosLimLista.add(Integer.valueOf(tLimCorridolista.getText()));
        preciosLimLista.add(Integer.valueOf(tLimpParletLista.getText()));
        preciosLimLista.add(Integer.valueOf(tLimCentenaLista.getText()));
        preciosLimLista.add(Integer.valueOf(tLimPlayFallLista.getText()));
        preciosLimLista.add(Integer.valueOf(tLimPikLista.getText()));

        //Lleno las listas de los limitados en la lista
        numerosLimLista.add(limFijoLista.getText());
        numerosLimLista.add(limCorridoLista.getText());
        numerosLimLista.add(limParletLista.getText());
        numerosLimLista.add(limCentenaLista.getText());
        numerosLimLista.add(limPlayFLista.getText());
        numerosLimLista.add(limPikLista.getText());

        //Lleno las listas de los limitados en el bote
        numerosLimBote.add(limFijo.getText());
        numerosLimBote.add(limCorrido.getText());
        numerosLimBote.add(limParlet.getText());
        numerosLimBote.add(limCentena.getText());
        numerosLimBote.add(limPlayFall.getText());
        numerosLimBote.add(limPik.getText());

        // Oculto los campos que no son necesarios
        // Labels config
        jLabel6.setVisible(false);
        jLabel28.setVisible(false);
        jLabel19.setVisible(false);
        jLabel55.setVisible(false);
        jLabel27.setVisible(false);
        jLabel64.setVisible(false);
        jLabel20.setVisible(false);
        jLabel65.setVisible(false);
        // textField config
        tfPFPB.setVisible(false);
        tfPFTB.setVisible(false);
        tfPFPL.setVisible(false);
        tfPFTL.setVisible(false);
        tfPKPB.setVisible(false);
        tfPKTB.setVisible(false);
        tfPKPL.setVisible(false);
        tfPKTL.setVisible(false);
        // labels Limitados
        jLabel105.setVisible(false);
        jLabel104.setVisible(false);
        jLabel110.setVisible(false);
        jLabel111.setVisible(false);
        jLabel92.setVisible(false);
        jLabel93.setVisible(false);
        jLabel98.setVisible(false);
        jLabel99.setVisible(false);
        // labels Limitados
        limPlayFall.setVisible(false);
        limPlayFLista.setVisible(false);
        tLimPlayFallLista.setVisible(false);
        tlimPlayFall.setVisible(false);
        limPik.setVisible(false);
        limPikLista.setVisible(false);
        tLimPik.setVisible(false);
        tLimPikLista.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jlBrutoBote = new javax.swing.JLabel();
        jlLimpioBote = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jlBrutoTotal = new javax.swing.JLabel();
        jlLimpioTotal = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlBrutoLista = new javax.swing.JLabel();
        jlLimpioLista = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlNombreLista = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jCh_tipoParlet = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        tfCPBF = new javax.swing.JTextField();
        tfCPBC = new javax.swing.JTextField();
        tfCPBP = new javax.swing.JTextField();
        tfCPBCent = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tfPFPB = new javax.swing.JTextField();
        tfPKPB = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        tfCPFF = new javax.swing.JTextField();
        tfCPFC = new javax.swing.JTextField();
        tfCPFP = new javax.swing.JTextField();
        tfCPFCent = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        tfPFPL = new javax.swing.JTextField();
        tfPKPL = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        tfPCL = new javax.swing.JTextField();
        tfPCB = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        tfCTBF = new javax.swing.JTextField();
        tfCTBC = new javax.swing.JTextField();
        tfCTBP = new javax.swing.JTextField();
        tfCTBCent = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        tfPFTB = new javax.swing.JTextField();
        tfPKTB = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        tfCTLF = new javax.swing.JTextField();
        tfCTLC = new javax.swing.JTextField();
        tfCTLP = new javax.swing.JTextField();
        tfCTLCent = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        tfPFTL = new javax.swing.JTextField();
        tfPKTL = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        tLimFijo = new javax.swing.JTextField();
        tLimCorrido = new javax.swing.JTextField();
        tLimParlet = new javax.swing.JTextField();
        tLimCentena = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        tlimPlayFall = new javax.swing.JTextField();
        tLimPik = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        tLimFijoLista = new javax.swing.JTextField();
        tLimCorridolista = new javax.swing.JTextField();
        tLimpParletLista = new javax.swing.JTextField();
        tLimCentenaLista = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        tLimPlayFallLista = new javax.swing.JTextField();
        tLimPikLista = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        limFijo = new javax.swing.JTextField();
        limCorrido = new javax.swing.JTextField();
        limParlet = new javax.swing.JTextField();
        limCentena = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        limPlayFall = new javax.swing.JTextField();
        limPik = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        limFijoLista = new javax.swing.JTextField();
        limCorridoLista = new javax.swing.JTextField();
        limParletLista = new javax.swing.JTextField();
        limCentenaLista = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        limPlayFLista = new javax.swing.JTextField();
        limPikLista = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("cu/sdev/loto/Bundle"); // NOI18N
        setTitle(bundle.getString("principal.title")); // NOI18N
        setIconImages(null);
        setResizable(false);

        jPanel10.setMaximumSize(new java.awt.Dimension(600, 900));
        jPanel10.setPreferredSize(new java.awt.Dimension(600, 900));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(50, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jlBrutoBote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlBrutoBote.setText(bundle.getString("principal.jlBrutoBote.text")); // NOI18N

        jlLimpioBote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlLimpioBote.setText(bundle.getString("principal.jlLimpioBote.text")); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText(bundle.getString("principal.jLabel7.text")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText(bundle.getString("principal.jLabel8.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText(bundle.getString("principal.jLabel4.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText(bundle.getString("principal.jLabel3.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText(bundle.getString("principal.jLabel2.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText(bundle.getString("principal.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBrutoBote)
                    .addComponent(jlLimpioBote)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlBrutoBote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jlLimpioBote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)))
        );

        jLabel3.getAccessibleContext().setAccessibleName(bundle.getString("principal.jLabel3.AccessibleContext.accessibleName")); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jlBrutoTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlBrutoTotal.setText(bundle.getString("principal.jlBrutoTotal.text")); // NOI18N

        jlLimpioTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlLimpioTotal.setText(bundle.getString("principal.jlLimpioTotal.text")); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText(bundle.getString("principal.jLabel21.text")); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText(bundle.getString("principal.jLabel22.text")); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText(bundle.getString("principal.jLabel23.text")); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText(bundle.getString("principal.jLabel24.text")); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText(bundle.getString("principal.jLabel25.text")); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText(bundle.getString("principal.jLabel26.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBrutoTotal)
                    .addComponent(jlLimpioTotal)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jlBrutoTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jlLimpioTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel5.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jlBrutoLista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlBrutoLista.setText(bundle.getString("principal.jlBrutoLista.text")); // NOI18N

        jlLimpioLista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlLimpioLista.setText(bundle.getString("principal.jlLimpioLista.text")); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText(bundle.getString("principal.jLabel29.text")); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText(bundle.getString("principal.jLabel30.text")); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText(bundle.getString("principal.jLabel31.text")); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText(bundle.getString("principal.jLabel32.text")); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText(bundle.getString("principal.jLabel33.text")); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText(bundle.getString("principal.jLabel34.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBrutoLista)
                    .addComponent(jlLimpioLista)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jlBrutoLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jlLimpioLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel6.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText(bundle.getString("principal.jLabel15.text")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText(bundle.getString("principal.jLabel13.text")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText(bundle.getString("principal.jLabel11.text")); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText(bundle.getString("principal.jLabel12.text")); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText(bundle.getString("principal.jLabel18.text")); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText(bundle.getString("principal.jLabel16.text")); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText(bundle.getString("principal.jLabel9.text")); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText(bundle.getString("principal.jLabel17.text")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText(bundle.getString("principal.jLabel10.text")); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText(bundle.getString("principal.jLabel14.text")); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel7.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText(bundle.getString("principal.jLabel35.text")); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText(bundle.getString("principal.jLabel36.text")); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText(bundle.getString("principal.jLabel37.text")); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText(bundle.getString("principal.jLabel38.text")); // NOI18N

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText(bundle.getString("principal.jLabel39.text")); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText(bundle.getString("principal.jLabel40.text")); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText(bundle.getString("principal.jLabel41.text")); // NOI18N

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText(bundle.getString("principal.jLabel42.text")); // NOI18N

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText(bundle.getString("principal.jLabel43.text")); // NOI18N

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText(bundle.getString("principal.jLabel44.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel44)
                    .addComponent(jLabel35)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel39)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel8.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText(bundle.getString("principal.jLabel45.text")); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText(bundle.getString("principal.jLabel46.text")); // NOI18N

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText(bundle.getString("principal.jLabel47.text")); // NOI18N

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText(bundle.getString("principal.jLabel48.text")); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText(bundle.getString("principal.jLabel49.text")); // NOI18N

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel50.setText(bundle.getString("principal.jLabel50.text")); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText(bundle.getString("principal.jLabel51.text")); // NOI18N

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText(bundle.getString("principal.jLabel52.text")); // NOI18N

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText(bundle.getString("principal.jLabel53.text")); // NOI18N

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText(bundle.getString("principal.jLabel54.text")); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(jLabel54)
                    .addComponent(jLabel45)
                    .addComponent(jLabel50)
                    .addComponent(jLabel49))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel49)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText(bundle.getString("principal.jLabel5.text")); // NOI18N
        jLabel5.setFocusTraversalPolicyProvider(true);

        jlNombreLista.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlNombreLista.setText(bundle.getString("principal.jlNombreLista.text")); // NOI18N

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel66.setText(bundle.getString("principal.jLabel66.text")); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlNombreLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel66)
                .addGap(26, 26, 26))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jlNombreLista)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel9);

        jTextField1.setToolTipText(bundle.getString("principal.toolTipText")); // NOI18N
        jTextField1.setName(""); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton3.setText(bundle.getString("principal.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText(bundle.getString("principal.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("principal.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText(bundle.getString("principal.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText(bundle.getString("principal.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jCh_tipoParlet.setSelected(true);
        jCh_tipoParlet.setText(bundle.getString("principal.jCh_tipoParlet.text")); // NOI18N
        jCh_tipoParlet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCh_tipoParletActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCh_tipoParlet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton7)
                    .addComponent(jCh_tipoParlet))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("principal.jPanel10.TabConstraints.tabTitle"), jPanel10); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel12.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel13.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText(bundle.getString("principal.jLabel56.text")); // NOI18N

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText(bundle.getString("principal.jLabel57.text")); // NOI18N

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText(bundle.getString("principal.jLabel58.text")); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText(bundle.getString("principal.jLabel59.text")); // NOI18N

        tfCPBF.setText(bundle.getString("principal.tfCPBF.text")); // NOI18N
        tfCPBF.setName(""); // NOI18N

        tfCPBC.setText(bundle.getString("principal.tfCPBC.text")); // NOI18N

        tfCPBP.setText(bundle.getString("principal.tfCPBP.text")); // NOI18N

        tfCPBCent.setText(bundle.getString("principal.tfCPBCent.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText(bundle.getString("principal.jLabel6.text")); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText(bundle.getString("principal.jLabel28.text")); // NOI18N

        tfPFPB.setText(bundle.getString("principal.tfPFPB.text")); // NOI18N

        tfPKPB.setText(bundle.getString("principal.tfPKPB.text")); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel6)
                    .addComponent(jLabel57)
                    .addComponent(jLabel56)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCPBF)
                    .addComponent(tfCPBC)
                    .addComponent(tfCPBP, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(tfCPBCent)
                    .addComponent(tfPFPB)
                    .addComponent(tfPKPB))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(tfCPBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(tfCPBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(tfCPBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(tfCPBCent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfPFPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(tfPKPB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        tfCPBF.getAccessibleContext().setAccessibleName(bundle.getString("principal.tfCPBF.AccessibleContext.accessibleName")); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel14.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText(bundle.getString("principal.jLabel60.text")); // NOI18N

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setText(bundle.getString("principal.jLabel62.text")); // NOI18N

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setText(bundle.getString("principal.jLabel63.text")); // NOI18N

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setText(bundle.getString("principal.jLabel61.text")); // NOI18N

        tfCPFF.setText(bundle.getString("principal.tfCPFF.text")); // NOI18N

        tfCPFC.setText(bundle.getString("principal.tfCPFC.text")); // NOI18N

        tfCPFP.setText(bundle.getString("principal.tfCPFP.text")); // NOI18N

        tfCPFCent.setText(bundle.getString("principal.tfCPFCent.text")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText(bundle.getString("principal.jLabel19.text")); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText(bundle.getString("principal.jLabel55.text")); // NOI18N

        tfPFPL.setText(bundle.getString("principal.tfPFPL.text")); // NOI18N

        tfPKPL.setText(bundle.getString("principal.tfPKPL.text")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel19)
                    .addComponent(jLabel61)
                    .addComponent(jLabel60)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCPFF)
                    .addComponent(tfCPFC)
                    .addComponent(tfCPFP, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(tfCPFCent)
                    .addComponent(tfPFPL)
                    .addComponent(tfPKPL))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(tfCPFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(tfCPFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(tfCPFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(tfCPFCent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfPFPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(tfPKPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel20.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText(bundle.getString("principal.jLabel76.text")); // NOI18N

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText(bundle.getString("principal.jLabel77.text")); // NOI18N

        tfPCL.setText(bundle.getString("principal.tfPCL.text")); // NOI18N

        tfPCB.setText(bundle.getString("principal.tfPCB.text")); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPCL))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPCB)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(tfPCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(tfPCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel17.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel18.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText(bundle.getString("principal.jLabel68.text")); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText(bundle.getString("principal.jLabel69.text")); // NOI18N

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel70.setText(bundle.getString("principal.jLabel70.text")); // NOI18N

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setText(bundle.getString("principal.jLabel71.text")); // NOI18N

        tfCTBF.setText(bundle.getString("principal.tfCTBF.text")); // NOI18N

        tfCTBC.setText(bundle.getString("principal.tfCTBC.text")); // NOI18N

        tfCTBP.setText(bundle.getString("principal.tfCTBP.text")); // NOI18N

        tfCTBCent.setText(bundle.getString("principal.tfCTBCent.text")); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText(bundle.getString("principal.jLabel27.text")); // NOI18N

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setText(bundle.getString("principal.jLabel64.text")); // NOI18N

        tfPFTB.setText(bundle.getString("principal.tfPFTB.text")); // NOI18N

        tfPKTB.setText(bundle.getString("principal.tfPKTB.text")); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel64)
                    .addComponent(jLabel27)
                    .addComponent(jLabel69)
                    .addComponent(jLabel68)
                    .addComponent(jLabel70)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCTBF, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCTBC, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCTBP, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCTBCent, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPFTB, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPKTB, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(tfCTBF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(tfCTBC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(tfCTBP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(tfCTBCent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(tfPFTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel64)
                    .addComponent(tfPKTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel19.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel72.setText(bundle.getString("principal.jLabel72.text")); // NOI18N

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel73.setText(bundle.getString("principal.jLabel73.text")); // NOI18N

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText(bundle.getString("principal.jLabel74.text")); // NOI18N

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText(bundle.getString("principal.jLabel75.text")); // NOI18N

        tfCTLF.setText(bundle.getString("principal.tfCTLF.text")); // NOI18N

        tfCTLC.setText(bundle.getString("principal.tfCTLC.text")); // NOI18N

        tfCTLP.setText(bundle.getString("principal.tfCTLP.text")); // NOI18N

        tfCTLCent.setText(bundle.getString("principal.tfCTLCent.text")); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText(bundle.getString("principal.jLabel20.text")); // NOI18N

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText(bundle.getString("principal.jLabel65.text")); // NOI18N

        tfPFTL.setText(bundle.getString("principal.tfPFTL.text")); // NOI18N

        tfPKTL.setText(bundle.getString("principal.tfPKTL.text")); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel20)
                    .addComponent(jLabel75)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfCTLF)
                    .addComponent(tfCTLC)
                    .addComponent(tfCTLP)
                    .addComponent(tfCTLCent)
                    .addComponent(tfPFTL)
                    .addComponent(tfPKTL))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(tfCTLF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(tfCTLC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(tfCTLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(tfCTLCent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tfPFTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(tfPKTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jButton5.setText(bundle.getString("principal.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("principal.jPanel11.TabConstraints.tabTitle"), jPanel11); // NOI18N

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel22.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel23.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel88.setText(bundle.getString("principal.jLabel88.text")); // NOI18N

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel89.setText(bundle.getString("principal.jLabel89.text")); // NOI18N

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel90.setText(bundle.getString("principal.jLabel90.text")); // NOI18N

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel91.setText(bundle.getString("principal.jLabel91.text")); // NOI18N

        tLimFijo.setText(bundle.getString("principal.tLimFijo.text")); // NOI18N

        tLimCorrido.setText(bundle.getString("principal.tLimCorrido.text")); // NOI18N

        tLimParlet.setText(bundle.getString("principal.tLimParlet.text")); // NOI18N

        tLimCentena.setText(bundle.getString("principal.tLimCentena.text")); // NOI18N

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel92.setText(bundle.getString("principal.jLabel92.text")); // NOI18N

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel93.setText(bundle.getString("principal.jLabel93.text")); // NOI18N

        tlimPlayFall.setText(bundle.getString("principal.tlimPlayFall.text")); // NOI18N

        tLimPik.setText(bundle.getString("principal.tLimPik.text")); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel93)
                    .addComponent(jLabel92)
                    .addComponent(jLabel89)
                    .addComponent(jLabel88)
                    .addComponent(jLabel90)
                    .addComponent(jLabel91))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tLimFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tLimCorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tLimParlet, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tLimCentena, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tlimPlayFall, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tLimPik, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(tLimFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(tLimCorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(tLimParlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(tLimCentena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(tlimPlayFall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel93)
                    .addComponent(tLimPik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel24.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel94.setText(bundle.getString("principal.jLabel94.text")); // NOI18N

        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel95.setText(bundle.getString("principal.jLabel95.text")); // NOI18N

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel96.setText(bundle.getString("principal.jLabel96.text")); // NOI18N

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel97.setText(bundle.getString("principal.jLabel97.text")); // NOI18N

        tLimFijoLista.setText(bundle.getString("principal.tLimFijoLista.text")); // NOI18N

        tLimCorridolista.setText(bundle.getString("principal.tLimCorridolista.text")); // NOI18N

        tLimpParletLista.setText(bundle.getString("principal.tLimpParletLista.text")); // NOI18N

        tLimCentenaLista.setText(bundle.getString("principal.tLimCentenaLista.text")); // NOI18N

        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel98.setText(bundle.getString("principal.jLabel98.text")); // NOI18N

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel99.setText(bundle.getString("principal.jLabel99.text")); // NOI18N

        tLimPlayFallLista.setText(bundle.getString("principal.tLimPlayFallLista.text")); // NOI18N

        tLimPikLista.setText(bundle.getString("principal.tLimPikLista.text")); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel99)
                    .addComponent(jLabel98)
                    .addComponent(jLabel97)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95)
                    .addComponent(jLabel96))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tLimFijoLista)
                    .addComponent(tLimCorridolista)
                    .addComponent(tLimpParletLista)
                    .addComponent(tLimCentenaLista)
                    .addComponent(tLimPlayFallLista)
                    .addComponent(tLimPikLista))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(tLimFijoLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(tLimCorridolista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(tLimpParletLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(tLimCentenaLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(tLimPlayFallLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(tLimPikLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel25.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel26.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel100.setText(bundle.getString("principal.jLabel100.text")); // NOI18N

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel101.setText(bundle.getString("principal.jLabel101.text")); // NOI18N

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel102.setText(bundle.getString("principal.jLabel102.text")); // NOI18N

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel103.setText(bundle.getString("principal.jLabel103.text")); // NOI18N

        limFijo.setText(bundle.getString("principal.limFijo.text")); // NOI18N
        limFijo.setToolTipText(bundle.getString("principal.limFijo.toolTipText")); // NOI18N

        limCorrido.setText(bundle.getString("principal.limCorrido.text")); // NOI18N

        limParlet.setText(bundle.getString("principal.limParlet.text")); // NOI18N

        limCentena.setText(bundle.getString("principal.limCentena.text")); // NOI18N

        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel104.setText(bundle.getString("principal.jLabel104.text")); // NOI18N

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel105.setText(bundle.getString("principal.jLabel105.text")); // NOI18N

        limPlayFall.setText(bundle.getString("principal.limPlayFall.text")); // NOI18N

        limPik.setText(bundle.getString("principal.limPik.text")); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel105)
                    .addComponent(jLabel104)
                    .addComponent(jLabel101)
                    .addComponent(jLabel100)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limFijo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limCorrido, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limParlet, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limCentena, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limPlayFall, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limPik, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(limFijo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(limCorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102)
                    .addComponent(limParlet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(limCentena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(limPlayFall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel105)
                    .addComponent(limPik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("principal.jPanel27.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel106.setText(bundle.getString("principal.jLabel106.text")); // NOI18N

        jLabel107.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel107.setText(bundle.getString("principal.jLabel107.text")); // NOI18N

        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel108.setText(bundle.getString("principal.jLabel108.text")); // NOI18N

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel109.setText(bundle.getString("principal.jLabel109.text")); // NOI18N

        limFijoLista.setText(bundle.getString("principal.limFijoLista.text")); // NOI18N

        limCorridoLista.setText(bundle.getString("principal.limCorridoLista.text")); // NOI18N

        limParletLista.setText(bundle.getString("principal.limParletLista.text")); // NOI18N

        limCentenaLista.setText(bundle.getString("principal.limCentenaLista.text")); // NOI18N

        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel110.setText(bundle.getString("principal.jLabel110.text")); // NOI18N

        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel111.setText(bundle.getString("principal.jLabel111.text")); // NOI18N

        limPlayFLista.setText(bundle.getString("principal.limPlayFLista.text")); // NOI18N

        limPikLista.setText(bundle.getString("principal.limPikLista.text")); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel111)
                    .addComponent(jLabel110)
                    .addComponent(jLabel109)
                    .addComponent(jLabel106)
                    .addComponent(jLabel107)
                    .addComponent(jLabel108))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limFijoLista, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(limCorridoLista)
                    .addComponent(limParletLista)
                    .addComponent(limCentenaLista)
                    .addComponent(limPlayFLista)
                    .addComponent(limPikLista))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(limFijoLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(limCorridoLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(limParletLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(limCentenaLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(limPlayFLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(limPikLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jButton6.setText(bundle.getString("principal.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        jPanel25.getAccessibleContext().setAccessibleName(bundle.getString("principal.jPanel25.border.title")); // NOI18N

        jTabbedPane1.addTab(bundle.getString("principal.jPanel15.TabConstraints.tabTitle"), jPanel15); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gTiro() {
        // TODO add your handling code here:
        if (!jTextField1.getText().equals("")) {
            //JOptionPane.showMessageDialog(null, "Se generará el tiro pasandole los números (" + jTextField1.getText() + ")", "Loto-Desktop Generar Tiro", WIDTH);

            Boolean tipoParlet = jCh_tipoParlet.isSelected();
            double[] tiro;
            tiro = ut.generarTiro(jTextField1.getText().split(" "), ut.jugadaLimpia(jugada, dineroApuesta, apuesta), apuesta, dineroApuesta, listaBote, preciosLista, preciosBote, preciosLimLista, preciosLimBote, numerosLimLista, numerosLimBote, tipoParlet);

            // Pongo el resultado del tiro en la lista y el bote
            jLabel7.setText(String.valueOf(tiro[0]));
            jLabel29.setText(String.valueOf(tiro[1]));

            //Pongo el neto en la lista y el bote y le cambio los colores
            jLabel8.setText("" + (Double.valueOf(jlLimpioBote.getText()) - tiro[0]));
            if (Double.valueOf(jLabel8.getText()) < 0) {
                jLabel8.setForeground(Color.red);
            } else {
                jLabel8.setForeground(Color.green);
            }

            jLabel30.setText("" + (Double.valueOf(jlLimpioLista.getText()) - tiro[1]));
            if (Double.valueOf(jLabel30.getText()) < 0) {
                jLabel30.setForeground(Color.red);
            } else {
                jLabel30.setForeground(Color.green);
            }

            jLabel22.setText("" + (Double.valueOf(jlLimpioTotal.getText()) - (tiro[1]) + tiro[0]));

            // Pongo el total del ganador y el total del neto
            jLabel21.setText(String.valueOf(tiro[0] + tiro[1]));
            jLabel22.setText("" + (Math.rint(Double.valueOf(jLabel8.getText())) + Math.rint(Double.valueOf(jLabel30.getText()))));

            if (Double.valueOf(jLabel22.getText()) < 0) {
                jLabel22.setForeground(Color.red);
            } else {
                jLabel22.setForeground(Color.green);
            }
            System.out.println("" + (Double.valueOf(jLabel8.getText()) + Double.valueOf(jLabel30.getText())));

        } else {
            JOptionPane.showMessageDialog(null, "Recuerde que debe introducir los números para generar el tiro", "Loto-Desktop Generar Tiro", WIDTH);

        }
    }


  private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      // TODO add your handling code here:
      int tipo = JOptionPane.showConfirmDialog(null, "Se van a actializar los topes, está seguro de continuar", "Alerta", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

      if (tipo == 0) {
          // para Escribir el fichero de texto
          String texto
                  = tfCPBF.getText() + "," + tfCPBC.getText() + "," + tfCPBP.getText() + "," + tfCPBCent.getText() + "," + tfPFPB.getText() + "," + tfPKPB.getText() + ","
                  + tfCPFF.getText() + "," + tfCPFC.getText() + "," + tfCPFP.getText() + "," + tfCPFCent.getText() + "," + tfPFPL.getText() + "," + tfPKPL.getText() + ";"
                  + tfCTBF.getText() + "," + tfCTBC.getText() + "," + tfCTBP.getText() + "," + tfCTBCent.getText() + "," + tfPFTB.getText() + "," + tfPKTB.getText() + ","
                  + tfCTLF.getText() + "," + tfCTLC.getText() + "," + tfCTLP.getText() + "," + tfCTLCent.getText() + "," + tfPFTL.getText() + "," + tfPKTL.getText() + ";"
                  + tfPCB.getText() + "," + tfPCL.getText() + ";";

          try {

              File f = new File("config.txt");

              BufferedWriter fin
                      = new BufferedWriter(
                              new OutputStreamWriter(
                                      new FileOutputStream(f)));

              fin.write(texto);
              fin.close();

          } catch (Exception ex) {
              System.out.println(ex);
              //Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
          }

      } else {
          System.out.println("NO");
      }


    }//GEN-LAST:event_jButton5ActionPerformed


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int tipo = JOptionPane.showConfirmDialog(null, "Se van a actializar los números limitados y sus topes, está seguro de continuar", "Alerta", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);

        if (tipo == 0) {
            // para Escribir el fichero de texto
            String texto
                    = limFijo.getText() + "<" + limCorrido.getText() + "<" + limParlet.getText() + "<" + limCentena.getText() + "<" + limPlayFall.getText() + "<" + limPik.getText() + "<"
                    + limFijoLista.getText() + "<" + limCorridoLista.getText() + "<" + limParletLista.getText() + "<" + limCentenaLista.getText() + "<" + limPlayFLista.getText() + "<" + limPikLista.getText() + ";"
                    + tLimFijo.getText() + "<" + tLimCorrido.getText() + "<" + tLimParlet.getText() + "<" + tLimCentena.getText() + "<" + tlimPlayFall.getText() + "<" + tLimPik.getText() + "<"
                    + tLimFijoLista.getText() + "<" + tLimCorridolista.getText() + "<" + tLimpParletLista.getText() + "<" + tLimCentenaLista.getText() + "<" + tLimPlayFallLista.getText() + "<" + tLimPikLista.getText() + ";";

            try {

                File f = new File("configLim.txt");

                BufferedWriter fin
                        = new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(f)));

                fin.write(texto);
                fin.close();

            } catch (Exception ex) {
                System.out.println(ex);
                //Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
            }

        } else {
            System.out.println("NO");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        // TODO add your handling code here:
        // Creamos selector de aperture
        // Direccion de la carpeta donde deben estar las listas
        String rutaListas = dir.get().getPath() + "/Listas/";
        String rutaReportes = dir.get().getPath() + "/Reporte/";
        Boolean tipoParlet = jCh_tipoParlet.isSelected();

        ut.eliminarPorExtension(rutaReportes, "txt");

        // obtern listado de ficheros
        ArrayList<String> listas = ut.getRutaFicheros(rutaListas);

        // para leer el fichero de texto
        for (int z = 0; z < listas.size(); z++) {
            String text;
            text = "";

            // Limpio los valores de los label y los pongo en cero antes de llenarlos con los otros
            jlBrutoBote.setText("000");
            jlBrutoLista.setText("000");
            jlBrutoTotal.setText("000");
            jlLimpioBote.setText("000");

            jlLimpioLista.setText("000");
            jlLimpioTotal.setText("000");
            jLabel7.setText("000");
            jLabel8.setText("000");

            jLabel21.setText("000");
            jLabel22.setText("000");
            jLabel29.setText("000");
            jLabel30.setText("000");

            jLabel13.setText("000");
            jLabel14.setText("000");
            jLabel15.setText("000");
            jLabel16.setText("000");
            jLabel18.setText("000");

            jLabel46.setText("000");
            jLabel45.setText("000");
            jLabel49.setText("000");
            jLabel50.setText("000");
            jLabel54.setText("000");

            jLabel36.setText("000");
            jLabel44.setText("000");
            jLabel40.setText("000");
            jLabel39.setText("000");
            jLabel35.setText("000");

            System.out.println("Lista: " + listas.get(z).toString());
            try {

                File f = new File(listas.get(z)/*selectedFile.toString()*/);

                BufferedReader fin
                        = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(f)));

                text = fin.readLine();

            } catch (IOException ex) {
                System.out.println(ex);
                //Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
            }

            // Sutituyo en el texto el ; por \n y cuento la cantidad de jugadas
            int cJLista = 0;
            int cJBote = 0;
            int cJTotal = 0;
            this.apFB = 0.0D;
            this.apFL = 0.0D;
            this.apCB = 0.0D;
            this.apCL = 0.0D;
            this.apCenB = 0.0D;
            this.apCenL = 0.0D;
            this.apPB = 0.0D;
            this.apPL = 0.0D;
            this.apCanL = 0.0D;
            this.apCanB = 0.0D;
            this.apPFl = 0.0D;
            this.apPFB = 0.0D;
            this.apPKb = 0.0D;
            this.apPKL = 0.0D;
            this.apMb = 0.0D;
            this.apML = 0.0D;

            String[] parts = text.split("<<");
            // JOptionPane.showMessageDialog(null, parts[0].replace(";", "\n").replace(">>", ""), "Trazas del Listero - " + parts[1].split(",")[0], JOptionPane.ERROR_MESSAGE);
            String txtLimpio = parts[1];

            for (int i = 0; i < txtLimpio.length(); i++) {
                if (txtLimpio.charAt(i) == ';') {
                    cJTotal++;
                }
            }

            /// Nueva varianta para carcular el bruto de la lista y del bote //
            String spl1[] = txtLimpio.split(";");

            for (String spl11 : spl1) {
                if (spl11.split(",")[4].equals("0")) {
                    if (spl11.split(",")[3].equals("Candado")) {
                        int aux = spl11.split(",")[1].split("-").length;

                        if (spl11.split(",")[1].contains("(")) {
                            aux -= 1;
                        }
                        aux = aux - 1;
                        if (aux == 1) {
                            //apCanB += Double.valueOf(spl11.split(",")[2]);
                        } else if (aux == 2) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 3; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 3;
                        } else if (aux == 3) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 6; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 6;
                        } else if (aux == 4) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 10; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 10;
                        } else if (aux == 5) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 15; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 15;
                        } else if (aux == 6) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 21; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 21;
                        } else if (aux == 7) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 28; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 28;
                        } else if (aux == 8) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 36; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 36;
                        } else if (aux == 9) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 45; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 45;
                        } else if (aux == 10) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 55; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 55;
                        } else if (aux == 11) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 66; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 66;
                        } else if (aux == 12) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 78; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 78;
                        } else if (aux == 13) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 91; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 91;
                        } else if (aux == 14) {
                            apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 105; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 105;
                        }

                    } else {
                        brutoBN += Double.valueOf(spl11.split(",")[2]);
                    }

                } else {
                    if (spl11.split(",")[3].equals("Candado")) {
                        int aux = spl11.split(",")[1].split("-").length;

                        if (spl11.split(",")[1].contains("(")) {
                            aux -= 1;
                        }
                        aux = aux - 1;
                        if (aux == 1) {
                            //apCanB += Double.valueOf(spl11.split(",")[2]);
                        } else if (aux == 2) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 3; //Double.valueOf(spl11.split(",")[2]) * 3;
                        } else if (aux == 3) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 6; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 6;
                        } else if (aux == 4) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 10; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 10;
                        } else if (aux == 5) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 15; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 15;
                        } else if (aux == 6) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 21; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 21;
                        } else if (aux == 7) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 28; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 28;
                        } else if (aux == 8) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 36; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 36;
                        } else if (aux == 9) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 45; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 45;
                        } else if (aux == 10) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 55; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 55;
                        } else if (aux == 11) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 66; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 66;
                        } else if (aux == 12) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 78; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 78;
                        } else if (aux == 13) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 91; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 91;
                        } else if (aux == 14) {
                            apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 105; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 105;
                        }

                    } else {
                        brutoLN += Double.valueOf(spl11.split(",")[2]);
                    }
                }
            }

            double brutoB = brutoBN + apCanNB;
            double brutoL = brutoLN + apCanNL;
            double brutoTotalN = brutoB + brutoL;

            ////
            String[] listJug = txtLimpio.split(";");
            LinkedList<String[]> listJugInd = new LinkedList();
            for (int i = 0; i < listJug.length; i++) {
                listJugInd.add(i, listJug[i].split(","));
            }
            this.jlNombreLista.setText(((String[]) listJugInd.get(0))[0]);
            for (int i = 0; i < listJugInd.size(); i++) {
                if (((String[]) listJugInd.get(i))[4].equals("1")) {
                    cJLista++;
                    switch (((String[]) listJugInd.get(i))[3]) {
                        case "Fijo":
                            String[] aux = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux1 : aux) {
                                this.apFL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Fijo");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "FijoCorridoF":
                            String[] aux0 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux01 : aux0) {
                                this.apFL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("FijoCorridoF");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Corrido":
                            String[] auxx = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String auxx1 : auxx) {
                                this.apCL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                                System.out.println("corrido " + this.apCL);
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Corrido");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "FijoCorridoC":
                            String[] aux2 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux21 : aux2) {
                                this.apCL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("FijoCorridoC");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Parlet":
                            this.apPL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Parlet");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "PlayF":
                            this.apPFl += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("PlayF");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Pik":
                            this.apPKL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Pik");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Mill":
                            this.apML += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Mill");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Candado":
                            int contNum = 0;
                            String aux1 = ((String[]) listJugInd.get(i))[1];
                            for (int k = 0; k < aux1.length(); k++) {
                                if (aux1.charAt(k) == '-') {
                                    contNum++;
                                }
                            }
                            if (aux1.contains("(")) {
                                contNum -= 1;
                            }
                            if (contNum == 1) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            } else if (contNum == 2) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 3.0D;
                            } else if (contNum == 3) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 6.0D;
                            } else if (contNum == 4) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 10.0D;
                            } else if (contNum == 5) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 15.0D;
                            } else if (contNum == 6) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 21.0D;
                            } else if (contNum == 7) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 28.0D;
                            } else if (contNum == 8) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 36.0D;
                            } else if (contNum == 9) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 45.0D;
                            } else if (contNum == 10) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 55.0D;
                            } else if (contNum == 11) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 66.0D;
                            } else if (contNum == 12) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 78.0D;
                            } else if (contNum == 13) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 91.0D;
                            } else if (contNum == 14) {
                                this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 105.0D;
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Candado");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        default:
                            String[] aux3 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux31 : aux3) {
                                this.apCenL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Centena");
                            this.listaBote.add("1");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                    }
                } else {
                    cJBote++;
                    switch (((String[]) listJugInd.get(i))[3]) {
                        case "Fijo":
                            String[] aux = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux1 : aux) {
                                this.apFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Fijo");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "FijoCorridoF":
                            String[] aux1 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux11 : aux1) {
                                this.apFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("FijoCorridoF");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Corrido":
                            String[] aux0 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux01 : aux0) {
                                this.apCB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                                System.out.println("Corrido " + this.apCB);
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Corrido");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "FijoCorridoC":
                            String[] aux3 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux31 : aux3) {
                                this.apCB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("FijoCorridoC");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Parlet":
                            this.apPB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Parlet");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "PlayF":
                            this.apPFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("PlayF");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Pik":
                            this.apPKb += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Pik");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Mill":
                            this.apMb += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Mill");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        case "Candado":
                            int contNum = 0;
                            String auxx = ((String[]) listJugInd.get(i))[1];
                            for (int k = 0; k < auxx.length(); k++) {
                                if (auxx.charAt(k) == '-') {
                                    contNum++;
                                }
                            }
                            if (auxx.contains("(")) {
                                contNum -= 1;
                            }
                            if (contNum == 1) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            } else if (contNum == 2) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 3.0D;
                            } else if (contNum == 3) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 6.0D;
                            } else if (contNum == 4) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 10.0D;
                            } else if (contNum == 5) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 15.0D;
                            } else if (contNum == 6) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 21.0D;
                            } else if (contNum == 7) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 28.0D;
                            } else if (contNum == 8) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 36.0D;
                            } else if (contNum == 9) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 45.0D;
                            } else if (contNum == 10) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 55.0D;
                            } else if (contNum == 11) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 66.0D;
                            } else if (contNum == 12) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 78.0D;
                            } else if (contNum == 13) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 91.0D;
                            } else if (contNum == 14) {
                                this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 105.0D;
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Candado");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                            break;
                        default:
                            String[] aux4 = ((String[]) listJugInd.get(i))[1].split("-");
                            for (String aux41 : aux4) {
                                this.apCenB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            }
                            this.jugada.add(((String[]) listJugInd.get(i))[1]);
                            this.apuesta.add("Centena");
                            this.listaBote.add("0");
                            this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                    }
                }
            }
            String a = txtLimpio.replace(";", "\n");
            System.out.println(a);

            this.jLabel36.setText("" + cJTotal);
            this.jLabel13.setText("" + cJLista);
            this.jLabel46.setText("" + cJBote);

            this.jLabel14.setText("" + this.apFL + this.apCanL);
            this.jLabel54.setText("" + this.apFB + this.apCanB);

            this.jLabel15.setText("" + this.apCL);
            this.jLabel45.setText("" + this.apCB);

            this.jLabel16.setText("" + this.apPL + this.apCanL);
            this.jLabel50.setText("" + this.apPB + this.apCanB);

            this.jLabel18.setText("" + this.apCenL);
            this.jLabel49.setText("" + this.apCenB);

            this.jLabel44.setText("" + (this.apFL + this.apFB + this.apCanB + this.apCanL));
            this.jLabel35.setText("" + (this.apCL + this.apCB));
            this.jLabel40.setText("" + (this.apPL + this.apPB));
            this.jLabel39.setText("" + (this.apCenL + this.apCenB));

            this.jlBrutoBote.setText("" + brutoB/*(this.apFB + this.apCB + this.apPB + this.apCanB + this.apCenB + this.apPFB + this.apPKb + this.apMb)*/);
            this.jlBrutoLista.setText("" + brutoL/*(this.apFL + this.apCL + this.apPL + this.apCanL + this.apCenL + this.apPFl + this.apPKL + this.apML)*/);

            this.jlLimpioBote.setText("" + (brutoB - brutoB * (Double.valueOf(this.tfPCB.getText()) / 100)));
            this.jlLimpioLista.setText("" + (brutoL - ((((this.apFL + this.apCL + this.apPFl) * 20) / 100) + (((this.apCenL + this.apPL + this.apCanL) * 30) / 100))));
            // this.jlBrutoBote.setText("" + (this.apFB + this.apCB + this.apPB + this.apCanB + this.apCenB + this.apPFB + this.apPKb + this.apMb));
            // this.jlBrutoLista.setText("" + (this.apFL + this.apCL + this.apPL + this.apCanL + this.apCenL + this.apPFl + this.apPKL + this.apML));

            // this.jlLimpioBote.setText("" + (Double.valueOf(this.jlBrutoBote.getText()).doubleValue() - (this.apFB + this.apCB + this.apPB + this.apCanB + this.apCenB + this.apPFB + this.apPKb + this.apMb) * (Double.valueOf(this.tfPCB.getText()).doubleValue() / 100.0D)));
            // this.jlLimpioLista.setText("" + (Double.valueOf(this.jlBrutoLista.getText()).doubleValue() - ((this.apFL + this.apCL + this.apPFl) * 20.0D / 100.0D + (this.apCenL + this.apPL + this.apCanL) * 30.0D / 100.0D)));
            System.out.println("Listaaaaa/////// " + (this.apFL + this.apCL + this.apPFl) * 20.0D / 100.0D);
            System.out.println("Listaaaaa/////// " + (this.apCenL + this.apPL) * 30.0D / 100.0D);

            this.jlBrutoTotal.setText("" + (Double.valueOf(this.jlBrutoBote.getText()).doubleValue() + Double.valueOf(this.jlBrutoLista.getText()).doubleValue()));
            this.jlLimpioTotal.setText("" + (Double.valueOf(this.jlLimpioBote.getText()).doubleValue() + Double.valueOf(this.jlLimpioLista.getText()).doubleValue()));

            gTiro();
            jButton2ActionPerformed(evt);

            jugada = new ArrayList<>();
            apuesta = new ArrayList<>();
            dineroApuesta = new ArrayList<>();
            listaBote = new ArrayList<>();

            brutoBN = 0;
            apCanNB = 0;
            brutoLN = 0;
            apCanNL = 0;

        }

        //  jTextArea1.setText(a);
        jButton4ActionPerformed(evt);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        boolean bol = true;
        String rutaListas = dir.get().getPath() + "/Listas/";
        if (bol == true) {
            Directorio dir = new Directorio();

            ArrayList<String> rutasLista = ut.getRutaFicheros(dir.get().getPath() + "\\Reporte\\");
            //JOptionPane.showMessageDialog(null, "Reportes: " + dir.get().getPath() + "\\Reporte\\");
            ArrayList<String> fichero = ut.leerFichero(rutasLista);

            // Contruyo la tabla de la lista
            for (int i = 0; i < fichero.size(); i++) {
                String lista = (String) fichero.get(i);
                String[] partesLista = lista.split(";");

                this.nombrelista = partesLista[0];
                this.valorVenta = Double.parseDouble(partesLista[1]);
                this.valorComision = Double.parseDouble(partesLista[2]);
                this.valorGanadores = Double.parseDouble(partesLista[3]);
                this.pierde = Double.parseDouble(partesLista[4]);
                this.gane = Double.parseDouble(partesLista[5]);

                this.filaTabla = (" <tr>\n    <td style=\"padding-right: 30px\"> " + this.nombrelista + " </td>\n" + "    <td> " + this.valorVenta + " </td>\n" + "    <td> " + this.valorComision + " </td>\n" + "    <td style=\"background:green; color: white;font-style: oblique\"><strong>  " + this.valorGanadores + " </strong></td>\n" + "    <td> " + this.gane + " </td>\n" + "    <td> " + this.pierde + " </td>\n" + "  </tr>");

                this.filasTablaLista = (this.filasTablaLista + "\n" + this.filaTabla + "\n");

                this.TotalVenta += this.valorVenta;
                this.TotalComision += this.valorComision;
                this.TotalGanadores += this.valorGanadores;
                this.TotalNeto += this.gane;
                this.TotalPierde += this.pierde;
            }
            this.totalFilaTablaLista = ("<tr>\n    <th scope=\"row\" style=\"padding-right: 30px\">TOTAL</th>\n    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalVenta + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalComision + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalGanadores + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalNeto + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalPierde + " </strong></td>\n" + "  </tr>");

            this.pierde = 0.0D;
            this.gane = 0.0D;
            this.valorGanadores = 0.0D;
            this.valorComision = 0.0D;
            this.valorVenta = 0.0D;
            this.TotalComision = 0.0D;
            this.TotalNeto = 0.0D;
            this.TotalPierde = 0.0D;
            this.TotalGanadores = 0.0D;
            this.TotalVenta = 0.0D;
            for (int i = 0; i < fichero.size(); i++) {
                String lista = (String) fichero.get(i);
                String[] partesLista = lista.split(";");

                this.nombrelistaB = partesLista[0];
                this.valorVentaB = Double.parseDouble(partesLista[6]);
                this.valorComisionB = Double.parseDouble(partesLista[7]);
                this.valorGanadoresB = Double.parseDouble(partesLista[8]);
                this.pierdeB = Double.parseDouble(partesLista[9]);
                this.ganeB = Double.parseDouble(partesLista[10]);

                this.filaTabla = (" <tr>\n    <td style=\"padding-right: 30px\"> " + this.nombrelistaB + " </td>\n" + "    <td> " + this.valorVentaB + " </td>\n" + "    <td> " + this.valorComisionB + " </td>\n" + "    <td style=\"background:green; color: white;font-style: oblique\"><strong>  " + this.valorGanadoresB + " </strong></td>\n" + "    <td> " + this.ganeB + " </td>\n" + "    <td> " + this.pierdeB + " </td>\n" + "  </tr>");

                this.filasTablaBote = (this.filasTablaBote + "\n" + this.filaTabla + "\n");

                this.TotalVentaB += this.valorVentaB;
                this.TotalComisionB += this.valorComisionB;
                this.TotalGanadoresB += this.valorGanadoresB;
                this.TotalNetoB += this.ganeB;
                this.TotalPierdeB += this.pierdeB;
            }
            this.totalFilaTablaBote = ("<tr>\n    <th scope=\"row\" style=\"padding-right: 30px\">TOTAL</th>\n    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalVentaB + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalComisionB + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalGanadoresB + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalNetoB + " </strong></td>\n" + "    <td scope=\"row\" style=\"padding-right: 30px\"><strong> " + this.TotalPierdeB + " </strong></td>\n" + "  </tr>");

            this.pierdeB = 0.0D;
            this.ganeB = 0.0D;
            this.valorGanadoresB = 0.0D;
            this.valorComisionB = 0.0D;
            this.valorVentaB = 0.0D;
            this.TotalComisionB = 0.0D;
            this.TotalNetoB = 0.0D;
            this.TotalPierdeB = 0.0D;
            this.TotalGanadoresB = 0.0D;
            this.TotalVentaB = 0.0D;

            utiles.eliminarPorExtension(Directorio.get().getPath() + "\\ReporteCompleto\\", "html");

            this.ut.generarReporteHtml(this.ut.getHtmlReporte(this.filasTablaLista, this.totalFilaTablaLista, this.filasTablaBote, this.totalFilaTablaBote));

            this.texto = "";
            this.nombrelista = "";
            this.nombrelistaB = "";
            this.html = "";
            this.filaTabla = "";
            this.totalFilaTablaLista = "";
            this.totalFilaTablaBote = "";
            this.filasTablaLista = "";
            this.filasTablaBote = "";
            this.valorVenta = 0.0D;
            this.valorComision = 0.0D;
            this.valorGanadores = 0.0D;
            this.valorNeto = 0.0D;
            this.valorVentaB = 0.0D;
            this.valorComisionB = 0.0D;
            this.valorGanadoresB = 0.0D;
            this.valorNetoB = 0.0D;
            this.TotalVenta = 0.0D;
            this.TotalComision = 0.0D;
            this.TotalGanadores = 0.0D;
            this.TotalNeto = 0.0D;
            this.TotalVentaB = 0.0D;
            this.TotalComisionB = 0.0D;
            this.TotalGanadoresB = 0.0D;
            this.TotalNetoB = 0.0D;

            utiles.eliminarPorExtension(rutaListas, "txt");
            openURL(Directorio.get().getPath() + "/ReporteCompleto/index.html");
        } else {
            System.out.println("cancelada");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String ruta = dir.get().getPath() + "/Reporte/";
        Calendar c = Calendar.getInstance();
        int hora, minutos, segundos, mili;
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        segundos = c.get(Calendar.SECOND);
        mili = c.get(Calendar.MILLISECOND);

        File archivo = new File(ruta + "" + hora + "_" + minutos + "_" + segundos + "_" + mili + ".txt");

        double comisionB = Double.valueOf(this.jlBrutoBote.getText()).doubleValue() * Double.valueOf(this.tfPCB.getText()).doubleValue();

        double LimpioL = Double.valueOf(this.jlBrutoLista.getText()).doubleValue() - ((this.apFL + this.apCL + this.apPFl) * 20.0D / 100.0D + (this.apCenL + this.apPL + this.apCanL) * 30.0D / 100.0D);
        double LimpioB = Double.valueOf(this.jlBrutoBote.getText()).doubleValue() - comisionB / 100.0D;

        double ganeL = 0.0D;
        double ganeB = 0.0D;
        double pierdeL = 0.0D;
        double pierdeB = 0.0D;
        if ((Double.valueOf(this.jlLimpioLista.getText()).doubleValue() > 0.0D)
                && (Double.valueOf(this.jLabel29.getText()).doubleValue() < Double.valueOf(this.jlLimpioLista.getText()).doubleValue())) {
            double pierd = Double.valueOf(this.jlLimpioLista.getText()).doubleValue() - Double.valueOf(this.jLabel29.getText()).doubleValue();
            ganeL = Double.valueOf(pierd).doubleValue();
            pierdeL = 0.0D;
        } else if ((Double.valueOf(this.jlLimpioLista.getText()).doubleValue() > 0.0D)
                && (Double.valueOf(this.jLabel29.getText()).doubleValue() > Double.valueOf(this.jlLimpioLista.getText()).doubleValue())) {
            double pierd = Double.valueOf(this.jLabel29.getText()).doubleValue() - Double.valueOf(this.jlLimpioLista.getText()).doubleValue();
            ganeL = 0.0D;
            pierdeL = pierd;
        }
        if ((Double.valueOf(this.jlLimpioBote.getText()).doubleValue() > 0.0D)
                && (Double.valueOf(this.jLabel7.getText()).doubleValue() < Double.valueOf(this.jlLimpioBote.getText()).doubleValue())) {
            double pierd = Double.valueOf(this.jlLimpioBote.getText()).doubleValue() - Double.valueOf(this.jLabel7.getText()).doubleValue();
            ganeB = Double.valueOf(pierd).doubleValue();
            pierdeB = 0.0D;
        } else if ((Double.valueOf(this.jlLimpioBote.getText()).doubleValue() > 0.0D)
                && (Double.valueOf(this.jLabel7.getText()).doubleValue() > Double.valueOf(this.jlLimpioBote.getText()).doubleValue())) {
            double pierd = Double.valueOf(this.jLabel7.getText()).doubleValue() - Double.valueOf(this.jlLimpioBote.getText()).doubleValue();
            ganeB = 0.0D;
            pierdeB = pierd;
        }
        String text = this.jlNombreLista.getText() + ";" + this.jlBrutoLista.getText() + ";" + LimpioL + ";" + this.jLabel29.getText() + ";" + ganeL + ";" + pierdeL + ";" + this.jlBrutoBote.getText() + ";" + LimpioB + ";" + this.jLabel7.getText() + ";" + ganeB + ";" + pierdeB + ";";

        try {
            BufferedWriter bw;
            if (archivo.exists()) {
                archivo = new File(ruta + "" + hora + "_" + minutos + "_" + segundos + "_" + mili + "_" + segundos + ".txt");
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(text);

            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write(text);
            }
            bw.close();
            System.out.println("SI");

            JOptionPane.showMessageDialog(null, "Reporte creado correctamente...");
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        jugada = new ArrayList<>();
        apuesta = new ArrayList<>();
        dineroApuesta = new ArrayList<>();
        listaBote = new ArrayList<>();
        Boolean tipoParlet = jCh_tipoParlet.isSelected();

        this.jlBrutoBote.setText("000");
        this.jlBrutoLista.setText("000");
        this.jlBrutoTotal.setText("000");
        this.jlLimpioBote.setText("000");

        this.jlLimpioLista.setText("000");
        this.jlLimpioTotal.setText("000");
        this.jLabel7.setText("000");
        this.jLabel8.setText("000");

        this.jLabel21.setText("000");
        this.jLabel22.setText("000");
        this.jLabel29.setText("000");
        this.jLabel30.setText("000");

        this.jLabel13.setText("000");
        this.jLabel14.setText("000");
        this.jLabel15.setText("000");
        this.jLabel16.setText("000");
        this.jLabel18.setText("000");

        this.jLabel46.setText("000");
        this.jLabel45.setText("000");
        this.jLabel49.setText("000");
        this.jLabel50.setText("000");
        this.jLabel54.setText("000");

        this.jLabel36.setText("000");
        this.jLabel44.setText("000");
        this.jLabel40.setText("000");
        this.jLabel39.setText("000");
        this.jLabel35.setText("000");

        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser("");
        fileChooser.setDialogTitle("Loto-Desktop Seleccionar Lista");
        int status = fileChooser.showOpenDialog(null);
        if (status == 0) {
            selectedFile = fileChooser.getSelectedFile();
        } else if (status != 1) {
        }
        String texto = "";
        try {
            File f = new File(selectedFile.toString());

            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            texto = fin.readLine();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        int cJLista = 0;
        int cJBote = 0;
        int cJTotal = 0;
        this.apFB = 0.0D;
        this.apFL = 0.0D;
        this.apCB = 0.0D;
        this.apCL = 0.0D;
        this.apCenB = 0.0D;
        this.apCenL = 0.0D;
        this.apPB = 0.0D;
        this.apPL = 0.0D;
        this.apCanL = 0.0D;
        this.apCanB = 0.0D;
        this.apPFl = 0.0D;
        this.apPFB = 0.0D;
        this.apPKb = 0.0D;
        this.apPKL = 0.0D;
        this.apMb = 0.0D;
        this.apML = 0.0D;

        String[] parts = texto.split("<<");
        //JOptionPane.showMessageDialog(null, parts[0].replace(";", "\n").replace(">>", ""), "Trazas del Listero - " + parts[1].split(",")[0], JOptionPane.ERROR_MESSAGE);
        String txtLimpio = parts[1];

        for (int i = 0; i < txtLimpio.length(); i++) {
            if (txtLimpio.charAt(i) == ';') {
                cJTotal++;
            }
        }

        /// Nueva varianta para carcular el bruto de la lista y del bote //
        String spl1[] = txtLimpio.split(";");

        for (String spl11 : spl1) {
            if (spl11.split(",")[4].equals("0")) {
                if (spl11.split(",")[3].equals("Candado")) {
                    int aux = spl11.split(",")[1].split("-").length;

                    if (spl11.split(",")[1].contains("(")) {
                        aux -= 1;
                    }
                    aux = aux - 1;
                    if (aux == 1) {
                        //apCanB += Double.valueOf(spl11.split(",")[2]);
                    } else if (aux == 2) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 3; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 3;
                    } else if (aux == 3) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 6; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 6;
                    } else if (aux == 4) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 10; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 10;
                    } else if (aux == 5) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 15; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 15;
                    } else if (aux == 6) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 21; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 21;
                    } else if (aux == 7) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 28; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 28;
                    } else if (aux == 8) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 36; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 36;
                    } else if (aux == 9) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 45; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 45;
                    } else if (aux == 10) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 55; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 55;
                    } else if (aux == 11) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 66; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 66;
                    } else if (aux == 12) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 78; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 78;
                    } else if (aux == 13) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 91; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 91;
                    } else if (aux == 14) {
                        apCanNB += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 105; //apCanNB += Double.valueOf(spl11.split(",")[2]) * 105;
                    }

                } else {
                    brutoBN += Double.valueOf(spl11.split(",")[2]);
                }

            } else {
                if (spl11.split(",")[3].equals("Candado")) {
                    int aux = spl11.split(",")[1].split("-").length;

                    if (spl11.split(",")[1].contains("(")) {
                        aux -= 1;
                    }
                    aux = aux - 1;
                    if (aux == 1) {
                        //apCanB += Double.valueOf(spl11.split(",")[2]);
                    } else if (aux == 2) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 3; //Double.valueOf(spl11.split(",")[2]) * 3;
                    } else if (aux == 3) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 6; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 6;
                    } else if (aux == 4) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 10; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 10;
                    } else if (aux == 5) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 15; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 15;
                    } else if (aux == 6) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 21; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 21;
                    } else if (aux == 7) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 28; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 28;
                    } else if (aux == 8) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 36; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 36;
                    } else if (aux == 9) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 45; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 45;
                    } else if (aux == 10) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 55; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 55;
                    } else if (aux == 11) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 66; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 66;
                    } else if (aux == 12) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 78; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 78;
                    } else if (aux == 13) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 91; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 91;
                    } else if (aux == 14) {
                        apCanNL += tipoParlet ? Double.valueOf(spl11.split(",")[2]) : Double.valueOf(spl11.split(",")[2]) * 105; //apCanNL += Double.valueOf(spl11.split(",")[2]) * 105;
                    }

                } else {
                    brutoLN += Double.valueOf(spl11.split(",")[2]);
                }
            }
        }

        double brutoB = brutoBN + apCanNB;
        double brutoL = brutoLN + apCanNL;
        double brutoTotalN = brutoB + brutoL;

        ////
        String[] listJug = txtLimpio.split(";");
        LinkedList<String[]> listJugInd = new LinkedList();
        for (int i = 0; i < listJug.length; i++) {
            listJugInd.add(i, listJug[i].split(","));
        }
        this.jlNombreLista.setText(((String[]) listJugInd.get(0))[0]);
        for (int i = 0; i < listJugInd.size(); i++) {
            if (((String[]) listJugInd.get(i))[4].equals("1")) {
                cJLista++;
                switch (((String[]) listJugInd.get(i))[3]) {
                    case "Fijo":
                        String[] aux = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux.length; k++) {
                            this.apFL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Fijo");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "FijoCorridoF":
                        String[] aux0 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux0.length; k++) {
                            this.apFL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("FijoCorridoF");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Corrido":
                        String[] auxx = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < auxx.length; k++) {
                            this.apCL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            System.out.println("corrido " + this.apCL);
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Corrido");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "FijoCorridoC":
                        String[] aux2 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux2.length; k++) {
                            this.apCL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("FijoCorridoC");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Parlet":
                        this.apPL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Parlet");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "PlayF":
                        this.apPFl += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("PlayF");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Pik":
                        this.apPKL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Pik");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Mill":
                        this.apML += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Mill");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Candado":
                        int contNum = 0;
                        String aux1 = ((String[]) listJugInd.get(i))[1];
                        for (int k = 0; k < aux1.length(); k++) {
                            if (aux1.charAt(k) == '-') {
                                contNum++;
                            }
                        }
                        if (aux1.contains("(")) {
                            contNum -= 1;
                        }
                        if (contNum == 1) {
                            //this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        } else if (contNum == 2) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 3.0D;
                        } else if (contNum == 3) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 6.0D;
                        } else if (contNum == 4) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 10.0D;
                        } else if (contNum == 5) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 15.0D;
                        } else if (contNum == 6) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 21.0D;
                        } else if (contNum == 7) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 28.0D;
                        } else if (contNum == 8) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 36.0D;
                        } else if (contNum == 9) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 45.0D;
                        } else if (contNum == 10) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 55.0D;
                        } else if (contNum == 11) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 66.0D;
                        } else if (contNum == 12) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 78.0D;
                        } else if (contNum == 13) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 91.0D;
                        } else if (contNum == 14) {
                            this.apCanL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 105.0D;
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Candado");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    default:
                        String[] aux3 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux3.length; k++) {
                            this.apCenL += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Centena");
                        this.listaBote.add("1");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                }
            } else {
                cJBote++;
                switch (((String[]) listJugInd.get(i))[3]) {
                    case "Fijo":
                        String[] aux = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux.length; k++) {
                            this.apFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Fijo");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "FijoCorridoF":
                        String[] aux1 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux1.length; k++) {
                            this.apFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("FijoCorridoF");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Corrido":
                        String[] aux0 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux0.length; k++) {
                            this.apCB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                            System.out.println("Corrido " + this.apCB);
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Corrido");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "FijoCorridoC":
                        String[] aux3 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux3.length; k++) {
                            this.apCB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("FijoCorridoC");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Parlet":
                        this.apPB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Parlet");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "PlayF":
                        this.apPFB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("PlayF");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Pik":
                        this.apPKb += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Pik");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Mill":
                        this.apMb += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Mill");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    case "Candado":
                        int contNum = 0;
                        String auxx = ((String[]) listJugInd.get(i))[1];
                        for (int k = 0; k < auxx.length(); k++) {
                            if (auxx.charAt(k) == '-') {
                                contNum++;
                            }
                        }
                        if (auxx.contains("(")) {
                            contNum -= 1;
                        }
                        if (contNum == 1) {
                            //this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        } else if (contNum == 2) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 3.0D;
                        } else if (contNum == 3) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 6.0D;
                        } else if (contNum == 4) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 10.0D;
                        } else if (contNum == 5) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 15.0D;
                        } else if (contNum == 6) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 21.0D;
                        } else if (contNum == 7) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 28.0D;
                        } else if (contNum == 8) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 36.0D;
                        } else if (contNum == 9) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 45.0D;
                        } else if (contNum == 10) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 55.0D;
                        } else if (contNum == 11) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 66.0D;
                        } else if (contNum == 12) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 78.0D;
                        } else if (contNum == 13) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 91.0D;
                        } else if (contNum == 14) {
                            this.apCanB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue() * 105.0D;
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Candado");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                        break;
                    default:
                        String[] aux4 = ((String[]) listJugInd.get(i))[1].split("-");
                        for (int k = 0; k < aux4.length; k++) {
                            this.apCenB += Double.valueOf(((String[]) listJugInd.get(i))[2]).doubleValue();
                        }
                        this.jugada.add(((String[]) listJugInd.get(i))[1]);
                        this.apuesta.add("Centena");
                        this.listaBote.add("0");
                        this.dineroApuesta.add(Double.valueOf(((String[]) listJugInd.get(i))[2]));
                }
            }
        }
        String a = txtLimpio.replace(";", "\n");
        System.out.println(a);

        this.jLabel36.setText("" + cJTotal);
        this.jLabel13.setText("" + cJLista);
        this.jLabel46.setText("" + cJBote);

        this.jLabel14.setText("" + this.apFL + this.apCanL);
        this.jLabel54.setText("" + this.apFB + this.apCanB);

        this.jLabel15.setText("" + this.apCL);
        this.jLabel45.setText("" + this.apCB);

        this.jLabel16.setText("" + this.apPL + this.apCanL);
        this.jLabel50.setText("" + this.apPB + this.apCanB);

        this.jLabel18.setText("" + this.apCenL);
        this.jLabel49.setText("" + this.apCenB);

        this.jLabel44.setText("" + (this.apFL + this.apFB + this.apCanB + this.apCanL));
        this.jLabel35.setText("" + (this.apCL + this.apCB));
        this.jLabel40.setText("" + (this.apPL + this.apPB));
        this.jLabel39.setText("" + (this.apCenL + this.apCenB));

        this.jlBrutoBote.setText("" + brutoB/*(this.apFB + this.apCB + this.apPB + this.apCanB + this.apCenB + this.apPFB + this.apPKb + this.apMb)*/);
        this.jlBrutoLista.setText("" + brutoL/*(this.apFL + this.apCL + this.apPL + this.apCanL + this.apCenL + this.apPFl + this.apPKL + this.apML)*/);

        this.jlLimpioBote.setText("" + (brutoB - brutoB * (Double.valueOf(this.tfPCB.getText()) / 100)));
        this.jlLimpioLista.setText("" + (brutoL - ((((this.apFL + this.apCL + this.apPFl) * 20) / 100) + (((this.apCenL + this.apPL + this.apCanL) * 30) / 100))));
        //this.jlLimpioBote.setText("" + (Double.valueOf(this.jlBrutoBote.getText()).doubleValue() - (this.apFB + this.apCB + this.apPB + this.apCanB + this.apCenB + this.apPFB + this.apPKb + this.apMb) * (Double.valueOf(this.tfPCB.getText()).doubleValue() / 100.0D)));
        //this.jlLimpioLista.setText("" + (Double.valueOf(this.jlBrutoLista.getText()).doubleValue() - ((this.apFL + this.apCL + this.apPFl) * 20.0D / 100.0D + (this.apCenL + this.apPL + this.apCanL) * 30.0D / 100.0D)));

        System.out.println("Listaaaaa/////// " + (this.apFL + this.apCL + this.apPFl) * 20.0D / 100.0D);
        System.out.println("Listaaaaa/////// " + (this.apCenL + this.apPL) * 30.0D / 100.0D);

        this.jlBrutoTotal.setText("" + (Double.valueOf(this.jlBrutoBote.getText()).doubleValue() + Double.valueOf(this.jlBrutoLista.getText()).doubleValue()));
        this.jlLimpioTotal.setText("" + (Double.valueOf(this.jlLimpioBote.getText()).doubleValue() + Double.valueOf(this.jlLimpioLista.getText()).doubleValue()));

        this.jTextArea1.setText(a);

        brutoBN = 0;
        apCanNB = 0;
        brutoLN = 0;
        apCanNL = 0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (!this.jTextField1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Se generará el tiro pasandole los números (" + this.jTextField1.getText() + ")", "Loto-Desktop Generar Tiro", 1);

            Boolean tipoParlet = jCh_tipoParlet.isSelected();

            double[] tiro;
            tiro = ut.generarTiro(jTextField1.getText().split(" "), ut.jugadaLimpia(jugada, dineroApuesta, apuesta), apuesta, dineroApuesta, listaBote, preciosLista, preciosBote, preciosLimLista, preciosLimBote, numerosLimLista, numerosLimBote, tipoParlet);

            // Pongo el resultado del tiro en la lista y el bote
            jLabel7.setText(String.valueOf(tiro[0]));
            jLabel29.setText(String.valueOf(tiro[1]));

            //Pongo el neto en la lista y el bote y le cambio los colores
            jLabel8.setText("" + (Double.valueOf(jlLimpioBote.getText()) - tiro[0]));
            if (Double.valueOf(jLabel8.getText()) < 0) {
                jLabel8.setForeground(Color.red);
            } else {
                jLabel8.setForeground(Color.green);
            }

            jLabel30.setText("" + (Double.valueOf(jlLimpioLista.getText()) - tiro[1]));
            if (Double.valueOf(jLabel30.getText()) < 0) {
                jLabel30.setForeground(Color.red);
            } else {
                jLabel30.setForeground(Color.green);
            }

            jLabel22.setText("" + (Double.valueOf(jlLimpioTotal.getText()) - (tiro[1]) + tiro[0]));

            // Pongo el total del ganador y el total del neto
            jLabel21.setText(String.valueOf(tiro[0] + tiro[1]));
            jLabel22.setText("" + (Math.rint(Double.valueOf(jLabel8.getText())) + Math.rint(Double.valueOf(jLabel30.getText()))));

            if (Double.valueOf(jLabel22.getText()) < 0) {
                jLabel22.setForeground(Color.red);
            } else {
                jLabel22.setForeground(Color.green);
            }
            System.out.println("" + (Double.valueOf(jLabel8.getText()) + Double.valueOf(jLabel30.getText())));

        } else {
            JOptionPane.showMessageDialog(null, "Recuerde que debe introducir los números para generar el tiro", "Loto-Desktop Generar Tiro", WIDTH);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:

        if (this.jTextField1.getText().length() >= 9) {
            JOptionPane.showMessageDialog(null, "No puedo poner mas n?meros, lo correcto es (123 25 54).", "Error", 0);
            evt.consume();
        } else if ((this.jTextField1.getText().length() == 3) || (this.jTextField1.getText().length() == 6)) {
            this.jTextField1.setText(this.jTextField1.getText() + " ");
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jCh_tipoParletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCh_tipoParletActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCh_tipoParletActionPerformed

    public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (osName.startsWith("Mac OS X")) {
                // Runtime.getRuntime().exec("open -a safari " + url);
                // Runtime.getRuntime().exec("open " + url + "/index.html");
                Runtime.getRuntime().exec("open " + url);
            } else {
                System.out.println("Please open a browser and go to " + url);
            }
        } catch (IOException e) {
            System.out.println("Failed to start a browser to open the url " + url);
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new principal().setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCh_tipoParlet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlBrutoBote;
    private javax.swing.JLabel jlBrutoLista;
    private javax.swing.JLabel jlBrutoTotal;
    private javax.swing.JLabel jlLimpioBote;
    private javax.swing.JLabel jlLimpioLista;
    private javax.swing.JLabel jlLimpioTotal;
    private javax.swing.JLabel jlNombreLista;
    private javax.swing.JTextField limCentena;
    private javax.swing.JTextField limCentenaLista;
    private javax.swing.JTextField limCorrido;
    private javax.swing.JTextField limCorridoLista;
    private javax.swing.JTextField limFijo;
    private javax.swing.JTextField limFijoLista;
    private javax.swing.JTextField limParlet;
    private javax.swing.JTextField limParletLista;
    private javax.swing.JTextField limPik;
    private javax.swing.JTextField limPikLista;
    private javax.swing.JTextField limPlayFLista;
    private javax.swing.JTextField limPlayFall;
    private javax.swing.JTextField tLimCentena;
    private javax.swing.JTextField tLimCentenaLista;
    private javax.swing.JTextField tLimCorrido;
    private javax.swing.JTextField tLimCorridolista;
    private javax.swing.JTextField tLimFijo;
    private javax.swing.JTextField tLimFijoLista;
    private javax.swing.JTextField tLimParlet;
    private javax.swing.JTextField tLimPik;
    private javax.swing.JTextField tLimPikLista;
    private javax.swing.JTextField tLimPlayFallLista;
    private javax.swing.JTextField tLimpParletLista;
    private javax.swing.JTextField tfCPBC;
    private javax.swing.JTextField tfCPBCent;
    private javax.swing.JTextField tfCPBF;
    private javax.swing.JTextField tfCPBP;
    private javax.swing.JTextField tfCPFC;
    private javax.swing.JTextField tfCPFCent;
    private javax.swing.JTextField tfCPFF;
    private javax.swing.JTextField tfCPFP;
    private javax.swing.JTextField tfCTBC;
    private javax.swing.JTextField tfCTBCent;
    private javax.swing.JTextField tfCTBF;
    private javax.swing.JTextField tfCTBP;
    private javax.swing.JTextField tfCTLC;
    private javax.swing.JTextField tfCTLCent;
    private javax.swing.JTextField tfCTLF;
    private javax.swing.JTextField tfCTLP;
    private javax.swing.JTextField tfPCB;
    private javax.swing.JTextField tfPCL;
    private javax.swing.JTextField tfPFPB;
    private javax.swing.JTextField tfPFPL;
    private javax.swing.JTextField tfPFTB;
    private javax.swing.JTextField tfPFTL;
    private javax.swing.JTextField tfPKPB;
    private javax.swing.JTextField tfPKPL;
    private javax.swing.JTextField tfPKTB;
    private javax.swing.JTextField tfPKTL;
    private javax.swing.JTextField tlimPlayFall;
    // End of variables declaration//GEN-END:variables
}
