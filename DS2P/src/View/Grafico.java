package View;

import Conexion.Connect;
import Registro.Registros;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;


public class Grafico {

    Registros r= new Registros();
    File manager = new File("C:\\Registros\\[Manager]");
    File admin = new File("C:\\Registros\\[Administrador]");
    File user = new File("C:\\Registros\\[Usuario]");
    boolean bool=false;
    String id;
    public void inicio(){

    JFrame frame = new JFrame("Origen");
    frame.setLocation(500,0);
    JPanel panel = new JPanel(null);
    panel.setBackground(Color.DARK_GRAY);
    frame.add(panel);

    ImageIcon Imagen = new ImageIcon("carrito-de-compras.png");
    JLabel Img = new JLabel(Imagen);
    Img.setSize((Imagen.getIconWidth()),(Imagen.getIconHeight()));
    panel.add(Img);

    ImageIcon texto = new ImageIcon("Proyecto nuevo.png");
    JLabel marca = new JLabel(texto);
    marca.setSize((texto.getIconWidth()),(texto.getIconHeight()));
    marca.setLocation(-15, 490);
    panel.add(marca);

    JButton B_iniciarSesion = new JButton("Iniciar Sesión");
    B_iniciarSesion.setSize(125,25);
    B_iniciarSesion.setLocation(180,650);
    panel.add(B_iniciarSesion);

    ActionListener alR = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (r.coderole(bool)){
                frame.dispose();
                inicioSesion();
            }else{
                frame.dispose();
                setCodes();
            }
        }
    };

    B_iniciarSesion.addActionListener(alR);


    JButton B_registrarse = new JButton("Registrarse");
    B_registrarse.setSize(125,25);
    B_registrarse.setLocation(180,690);
    panel.add(B_registrarse);

    ActionListener alS = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (r.coderole(bool)){
                frame.dispose();
                registro();
            }else{
                frame.dispose();
                setCodes();
            }
        }
    };

    B_registrarse.addActionListener(alS);
        JButton B_cerrar = new JButton("Salir");
        B_cerrar.setSize(125,25);
        B_cerrar.setLocation(180,730);
        panel.add(B_cerrar);

        ActionListener alC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        };

        B_cerrar.addActionListener(alC);

    frame.setSize(Imagen.getIconWidth()+20,Imagen.getIconHeight()+350);
    frame.setResizable(false);
    Image icono = Toolkit.getDefaultToolkit().getImage("carrito-de-compras.png");
    frame.setIconImage(icono);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    }

    public void setCodes(){
        JFrame frame = new JFrame("Set-Up");
        frame.setLocation(500,0);
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.DARK_GRAY);
        frame.add(panel);

        ImageIcon texto = new ImageIcon("welcome.png");
        JLabel welcome = new JLabel(texto);
        welcome.setSize((texto.getIconWidth()),(texto.getIconHeight()));
        welcome.setLocation(-10, 0);
        panel.add(welcome);

        JLabel begin= new JLabel();
        begin.setText("<html><center>¡Bienvenido a QuickStop, tu supermercado de confianza! " +
                "<P>Para poder comenzar a utilizar el programa rellena los siguientes recuadros con la información solicitada." +
                "<p>Una vez completado, dale a el botón 'Aceptar' para asignar las claves. " +
                "<p><p>¡Recuerdalas muy bien! ");
        begin.setVerticalAlignment(JLabel.TOP);
        begin.setSize(450,100);
        begin.setLocation(20,230);
        begin.setForeground(new Color(246,241,241));
        begin.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
        panel.add(begin);

        JTextField managerCode = new JTextField(12);
        panel.add(managerCode);
        managerCode.setSize(225,25);
        managerCode.setLocation(130,375);

        TextPrompt managerhold = new TextPrompt("Clave del Manager", managerCode);
        managerhold.changeAlpha(0.75f);
        managerhold.changeStyle(Font.ITALIC);

        JTextField adminCode = new JTextField(12);
        panel.add(adminCode);
        adminCode.setSize(225,25);
        adminCode.setLocation(130,425);

        TextPrompt adminhold = new TextPrompt("Clave del Administrador", adminCode);
        adminhold.changeAlpha(0.75f);
        adminhold.changeStyle(Font.ITALIC);

        JLabel confirmado = new JLabel();
        panel.add(confirmado);
        confirmado.setVisible(false);
        confirmado.setForeground(Color.red);
        confirmado.setSize(325,25);
        confirmado.setLocation(130,475);

        JButton B_asignar = new JButton("Aceptar");
        B_asignar.setSize(125,25);
        B_asignar.setLocation(180,525);
        panel.add(B_asignar);

        ActionListener alOK = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((managerCode.getText()).isEmpty()) || !((adminCode.getText()).isEmpty())) {
                    if (!((managerCode.getText()).equals(adminCode.getText()))) {
                        confirmado.setVisible(false);
                        frame.dispose();
                        inicio();
                        r.codewrite((adminCode.getText()),(managerCode.getText()));
                        JOptionPane.showMessageDialog(null, "Configuracion Correcta", "Set-Up", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        confirmado.setVisible(true);
                        confirmado.setText("LAS CLAVES NO PUEDEN SER IGUALES");
                    }
                } else {
                    confirmado.setVisible(true);
                    confirmado.setText("LAS CLAVES NO PUEDE ESTAR VACIAS");
                }
            }
        };

        B_asignar.addActionListener(alOK);

        JButton B_regresar = new JButton("Cancelar");
        B_regresar.setSize(125,25);
        B_regresar.setLocation(15,625);
        panel.add(B_regresar);

        ActionListener alR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                inicio();
            }
        };

        B_regresar.addActionListener(alR);

        frame.setSize(500,750);
        frame.setResizable(false);
        Image icono = Toolkit.getDefaultToolkit().getImage("carrito-de-compras.png");
        frame.setIconImage(icono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void registro() {
    JFrame frame = new JFrame("Sing-up");
    frame.setLocation(500,0);
    JPanel panel = new JPanel(null);
    panel.setBackground(Color.DARK_GRAY);
    frame.add(panel);


    ImageIcon userimg = new ImageIcon("usuario.png");
    JLabel userlabel = new JLabel(userimg);
    userlabel.setSize((userimg.getIconWidth()),(userimg.getIconHeight()));
    userlabel.setLocation(115, 80);
    panel.add(userlabel);

    JTextField txtuser = new JTextField(12);
    panel.add(txtuser);
    txtuser.setSize(225,25);
    txtuser.setLocation(130,350);

    TextPrompt userholder = new TextPrompt("Username", txtuser);
    userholder.changeAlpha(0.75f);
    userholder.changeStyle(Font.ITALIC);

    ImageIcon usericon = new ImageIcon("icon-usuario.png");
    JLabel iconlabel = new JLabel(usericon);
    iconlabel.setSize((usericon.getIconWidth()),(usericon.getIconHeight()));
    iconlabel.setLocation(90, 350);
    panel.add(iconlabel);

    JPasswordField txtpassword = new JPasswordField(12);
    panel.add(txtpassword);
    txtpassword.setSize(225,25);
    txtpassword.setLocation(130,400);

    TextPrompt passholder = new TextPrompt("Contraseña", txtpassword);
    passholder.changeAlpha(0.75f);
    passholder.changeStyle(Font.ITALIC);

    JPasswordField txtpassConfirm = new JPasswordField(12);
    panel.add(txtpassConfirm);
    txtpassConfirm.setSize(225,25);
    txtpassConfirm.setLocation(130,435);

    TextPrompt confpassholder = new TextPrompt("Confirmar contraseña", txtpassConfirm);
    confpassholder.changeAlpha(0.75f);
    confpassholder.changeStyle(Font.ITALIC);


    ImageIcon passicon = new ImageIcon("cerrar.png");
    JLabel passlabel = new JLabel(passicon);
    passlabel.setSize((passicon.getIconWidth()),(passicon.getIconHeight()));
    passlabel.setLocation(90, 400);
    panel.add(passlabel);

    JLabel confirmado = new JLabel();
    panel.add(confirmado);
    confirmado.setVisible(false);
    confirmado.setForeground(Color.red);
    confirmado.setSize(325,25);
    confirmado.setLocation(130,475);

    JTextField code = new JTextField(12);
    code.setVisible(false);
    panel.add(code);
    code.setSize(225,25);
    code.setLocation(130,535);

    TextPrompt codeholder = new TextPrompt("Code", code);
    codeholder.changeAlpha(0.75f);
    codeholder.changeStyle(Font.ITALIC);

    String [] roles={"Usuario","Manager","Administrador"};
    final String[] rol = new String[1];
    JComboBox combo_rol = new JComboBox(roles);
    panel.add(combo_rol);
    combo_rol.setLocation(125,500);
    combo_rol.setSize(250,25);

    combo_rol.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (String.valueOf(combo_rol.getSelectedItem())) {
            case "Usuario" -> code.setVisible(false);

            case "Manager" -> {
                code.setVisible(true);
                codeholder.setText("Codigo Manager");
            }

            case "Administrador" -> {
                code.setVisible(true);
                codeholder.setText("Codigo Administrador");
            }
        }
            }
        });

    JButton B_registro = new JButton("Registrarse");
    B_registro.setSize(125,25);
    B_registro.setLocation(180,575);
    panel.add(B_registro);


    ActionListener alRe = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            rol[0] =(String.valueOf(combo_rol.getSelectedItem()));
            char[] password1 = txtpassword.getPassword();
            char[] password2 = txtpassConfirm.getPassword();
            String input1 = new String(password1);
            String input2 = new String(password2);
            String roluser = (Arrays.toString(rol));
            String hashcode = (String.valueOf(code.getText().hashCode()));
            File comp= new File("C:\\Registros\\"+roluser+"\\"+txtuser.getText()+".txt");

            if(code.isVisible() && code.getText().isEmpty()) {
                confirmado.setVisible(true);
                confirmado.setText("NO PUEDES DEJAR EL CODIGO VACIO");
            }else{
                if (comp.exists()) {
                    confirmado.setVisible(true);
                    confirmado.setText("EL NOMBRE DE USUARIO YA ESTA SIENDO UTILIZADO");
                } else {
                    if (!(input1.isEmpty()) || !(input2.isEmpty())) {
                        if (input1.equals(input2)) {
                            if(!roluser.equals("[Usuario]")) {
                                if (r.coderead(roluser).equals(hashcode)) {
                                    System.out.println(roluser.toUpperCase());
                                    confirmado.setVisible(false);
                                    r.escribir(txtuser.getText(), input2, roluser);
                                    frame.dispose();
                                    inicio();
                                    JOptionPane.showMessageDialog(null, "Registro Correcto", "Sing-in", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    confirmado.setLocation(120, 475);
                                    confirmado.setVisible(true);
                                    confirmado.setText("EL CODIGO DE: " + roluser.toUpperCase() + " ES INCORRECTO");
                                }
                            }else{
                                System.out.println(roluser.toUpperCase());
                                confirmado.setVisible(false);
                                r.escribir(txtuser.getText(), input2, roluser);
                                frame.dispose();
                                inicio();
                                JOptionPane.showMessageDialog(null, "Registro Correcto", "Sing-in", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            confirmado.setVisible(true);
                            confirmado.setText("LAS CONTRASEÑAS NO SON IGUALES");
                        }
                    } else {
                        confirmado.setVisible(true);
                        confirmado.setText("LA CONTRASEÑA NO PUEDE ESTAR VACIA");
                    }
                }
            }
        }
    };
    B_registro.addActionListener(alRe);

    JButton B_regresar = new JButton("Atras");
    B_regresar.setSize(125,25);
    B_regresar.setLocation(15,675);
    panel.add(B_regresar);

    ActionListener alR = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            inicio();
        }
    };
    B_regresar.addActionListener(alR);

    frame.setSize(500,750);
    frame.setResizable(false);
    Image icono = Toolkit.getDefaultToolkit().getImage("carrito-de-compras.png");
    frame.setIconImage(icono);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
        txtuser.getText();
    }

    public void inicioSesion(){
    JFrame frame = new JFrame("Log-in");
    frame.setLocation(500,0);
    JPanel panel = new JPanel(null);
    panel.setBackground(Color.DARK_GRAY);
    frame.add(panel);

    ImageIcon userimg = new ImageIcon("usuario.png");
    JLabel userlabel = new JLabel(userimg);
    userlabel.setSize((userimg.getIconWidth()),(userimg.getIconHeight()));
    userlabel.setLocation(115, 80);
    panel.add(userlabel);

    JTextField txtuser = new JTextField(12);
    panel.add(txtuser);
    txtuser.setSize(225,25);
    txtuser.setLocation(130,350);

    TextPrompt userholder = new TextPrompt("Username", txtuser);
    userholder.changeAlpha(0.75f);
    userholder.changeStyle(Font.ITALIC);

    ImageIcon usericon = new ImageIcon("icon-usuario.png");
    JLabel iconlabel = new JLabel(usericon);
    iconlabel.setSize((usericon.getIconWidth()),(usericon.getIconHeight()));
    iconlabel.setLocation(90, 350);
    panel.add(iconlabel);

    JPasswordField txtpassword = new JPasswordField(12);
    panel.add(txtpassword);
    txtpassword.setSize(225,25);
    txtpassword.setLocation(130,400);

    TextPrompt passholder = new TextPrompt("Contraseña", txtpassword);
    passholder.changeAlpha(0.75f);
    passholder.changeStyle(Font.ITALIC);

    ImageIcon passicon = new ImageIcon("cerrar.png");
    JLabel passlabel = new JLabel(passicon);
    passlabel.setSize((passicon.getIconWidth()),(passicon.getIconHeight()));
    passlabel.setLocation(90, 400);
    panel.add(passlabel);

    String [] roles={"Usuario","Manager","Administrador"};
    final String[] rol = new String[1];
    JComboBox combo_rol = new JComboBox(roles);
    panel.add(combo_rol);
    combo_rol.setLocation(125,460);
    combo_rol.setSize(250,25);

    JLabel confirmado = new JLabel();
    confirmado.setSize(320,25);
    panel.add(confirmado);
    confirmado.setVisible(false);
    confirmado.setForeground(Color.red);
    confirmado.setLocation(100,430);

    JButton B_inicio = new JButton("Iniciar Sesión");
    B_inicio.setSize(125,25);
    B_inicio.setLocation(180,500);
    panel.add(B_inicio);

    ActionListener alI = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            rol[0] =(String.valueOf(combo_rol.getSelectedItem()));
            char[] password = txtpassword.getPassword();
            String input = new String(password);
            int hashpass = input.hashCode();
            String rev = (String.valueOf(hashpass));
            String diruser = Arrays.toString(rol);
            //System.out.println(diruser);
            String tipo = (diruser.substring(1, diruser.lastIndexOf(']')));
            File comp= new File("C:\\Registros\\"+diruser+"\\"+txtuser.getText()+".txt");
            //System.out.println("Input: "+input +" Hash password: "+ hashpass +" Rev:"+ rev);
            if(comp.exists()) {
               // System.out.println("TXTUSER: "+r.leer(txtuser.getText(),diruser));
               // System.out.println("REV: "+rev);
                if(r.leer(txtuser.getText(),diruser).equals(rev)) {
                    frame.dispose();
                    home(tipo);
                }else{
                    confirmado.setVisible(true);
                    confirmado.setText("LA CONTRASEÑA ES INCORRECTA");
                }
            }else{
                confirmado.setVisible(true);
                confirmado.setText("EL USUARIO NO EXISTE O NO ESTA REGISTRADO");
            }
        }
    };
    B_inicio.addActionListener(alI);

    JButton B_regresar = new JButton("Atras");
    B_regresar.setSize(125,25);
    B_regresar.setLocation(15,625);
    panel.add(B_regresar);

        ActionListener alR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                inicio();
            }
        };

        B_regresar.addActionListener(alR);

    frame.setSize(500,750);
    frame.setResizable(false);
    Image icono = Toolkit.getDefaultToolkit().getImage("carrito-de-compras.png");
    frame.setIconImage(icono);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    }
    TableRowSorter <DefaultTableModel> sorter;
    public void home(String rol){
    JFrame frame = new JFrame("Home");
    frame.setLocation(450,0);
    JPanel panel = new JPanel(null);
    panel.setBackground(Color.DARK_GRAY);
    frame.add(panel);


        String[] columnNames = {"Id", "Nombre", "Tipo", "Fecha de creación"};
        DefaultTableModel modelo1 = new DefaultTableModel();
        modelo1.setColumnIdentifiers(columnNames);
        JScrollPane js = new JScrollPane();
        JTable tablaU = new JTable(modelo1);
        tablaU.setSize(700, 450);
        tablaU.setLocation(15, 50);
        sorter = new TableRowSorter<>(modelo1);
        tablaU.setRowSorter(sorter);
        js.setBounds(15, 50, 700, 450);
        js.setViewportView(tablaU);
        js.setVisible(false);
        panel.add(js);

        FileFilter filtro = new FileFilter() {
            @Override
            public boolean accept(File arch) {
                return arch.isFile();
            }
        };

        File[] archivosU;
        archivosU = user.listFiles(filtro);
        if (archivosU == null || archivosU.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual" + user);

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < archivosU.length; i++) {
                File archivoU = archivosU[i];
                String fileName = archivoU.getName();
                String name = fileName.substring(0, fileName.lastIndexOf('.'));
                String carpeta = archivoU.getParent();
                String tipo = carpeta.substring(14, carpeta.lastIndexOf(']'));
                modelo1.addRow(new Object[]{"00" + (i + 1), name, tipo, sdf.format(archivoU.lastModified())});

            }
        }

        File[] archivosM;
        archivosM = manager.listFiles(filtro);
        if(rol.equals("Administrador")) {
            if (archivosM == null || archivosM.length == 0) {
                System.out.println("No hay elementos dentro de la carpeta actual" + manager);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < archivosM.length; i++) {
                    File archivoM = archivosM[i];
                    String fileName = archivoM.getName();
                    String name = fileName.substring(0, fileName.lastIndexOf('.'));
                    String carpeta = archivoM.getParent();
                    String tipo = carpeta.substring(14, carpeta.lastIndexOf(']'));
                    modelo1.addRow(new Object[]{"01" + (i + 1), name, tipo, sdf.format(archivoM.lastModified())});

                }
            }
        }

        File[] archivosA;
        archivosA = admin.listFiles(filtro);
        if(rol.equals("Administrador")) {
            if (archivosA == null || archivosA.length == 0) {
                System.out.println("No hay elementos dentro de la carpeta actual" + admin);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (int i = 0; i < archivosA.length; i++) {
                    File archivoA = archivosA[i];
                    String fileName = archivoA.getName();
                    String name = fileName.substring(0, fileName.lastIndexOf('.'));
                    String carpeta = archivoA.getParent();
                    String tipo = carpeta.substring(14, carpeta.lastIndexOf(']'));
                    modelo1.addRow(new Object[]{"02" + (i + 1), name, tipo, sdf.format(archivoA.lastModified())});

                }
            }
        }

        JTextField txtrol = new JTextField();
        panel.add(txtrol);
        txtrol.setSize(225, 25);
        txtrol.setLocation(725, 20);
        txtrol.setEditable(false);
        txtrol.setVisible(false);
        TextPrompt rolholder = new TextPrompt("Rol", txtrol);
        rolholder.changeAlpha(0.75f);
        rolholder.changeStyle(Font.ITALIC);

        JTextField txtname = new JTextField();
        panel.add(txtname);
        txtname.setSize(225, 25);
        txtname.setLocation(725, 70);
        txtname.setEditable(false);
        txtname.setVisible(false);
        TextPrompt nameholder = new TextPrompt("Nombre del Usuario", txtname);
        nameholder.changeAlpha(0.75f);
        nameholder.changeStyle(Font.ITALIC);

        JTextField txtNrol = new JTextField();
        panel.add(txtNrol);
        txtNrol.setSize(225, 25);
        txtNrol.setLocation(725, 120);
        txtNrol.setVisible(false);
        TextPrompt Nrolholder = new TextPrompt("Nuevo Rol", txtNrol);
        Nrolholder.changeAlpha(0.75f);
        Nrolholder.changeStyle(Font.ITALIC);

        JTextField txtNname = new JTextField();
        panel.add(txtNname);
        txtNname.setSize(225, 25);
        txtNname.setLocation(725, 170);
        txtNname.setVisible(false);
        TextPrompt Nnameholder = new TextPrompt("Nuevo nombre del Usuario", txtNname);
        Nnameholder.changeAlpha(0.75f);
        Nnameholder.changeStyle(Font.ITALIC);

        tablaU.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int fila = tablaU.getSelectedRow();
                if (fila != -1) {
                    txtname.setText(tablaU.getValueAt(fila, 1).toString());
                    txtrol.setText(tablaU.getValueAt(fila, 2).toString());
                    txtNname.setVisible(true);
                    txtNrol.setVisible(true);
                    id = tablaU.getValueAt(fila, 0).toString();
                }else {
                    JOptionPane.showMessageDialog(null,"Selecciona una fila","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton B_editar = new JButton("Cambiar");
        B_editar.setSize(125, 25);
        B_editar.setLocation(775, 270);
        B_editar.setVisible(false);
        panel.add(B_editar);

        JTextField txtsearch = new JTextField();
        panel.add(txtsearch);
        txtsearch.setSize(225, 25);
        txtsearch.setLocation(725, 320);
        txtsearch.setVisible(false);
        TextPrompt searchholder = new TextPrompt("Ingresa el texto a buscar", txtsearch);
        searchholder.changeAlpha(0.75f);
        searchholder.changeStyle(Font.ITALIC);

        ActionListener altxtS = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtsearch.getText().equals("") || txtsearch.getText() == null) {
                    mostrarUsuarios(txtsearch.getText());
                }
            }
        };
        txtsearch.addActionListener(altxtS);

        JButton B_search = new JButton("Buscar");
        B_search.setSize(125, 25);
        B_search.setLocation(775, 370);
        B_search.setVisible(false);
        panel.add(B_search);

        ActionListener alS = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUsuarios(txtsearch.getText());
            }
        };
        B_search.addActionListener(alS);

        JButton B_borrar = new JButton("Eliminar cuenta");
        B_borrar.setSize(135, 25);
        B_borrar.setLocation(770, 420);
        B_borrar.setVisible(false);
        panel.add(B_borrar);

        ActionListener alBo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila=tablaU.getSelectedRow();
                if (fila != -1){
                    String user=tablaU.getValueAt(fila,1).toString();
                    String rolC=tablaU.getValueAt(fila,2).toString();
                    String role = "["+rolC+"]";
                    modelo1.removeRow(fila);
                    r.Borrar(user,role);
                }
            }
        };
        B_borrar.addActionListener(alBo);

        ActionListener alEd = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dir="C:\\Registros";
                File Iarchivo=new File(dir+"\\["+txtrol.getText()+"]\\"+txtname.getText()+".txt");
                File Narchivo=new File(dir+"\\["+txtNrol.getText()+"]\\"+txtNname.getText()+".txt");
                int fila = tablaU.getSelectedRow();
                if (fila >=0){
                    try {
                        r.cambiotipo(Iarchivo,Narchivo);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    r.renombrar(txtname.getText(),txtrol.getText(), txtNname.getText());
                    frame.dispose();
                    home(rol);
                    mostrarUsuarios("");
                }else{
                    JOptionPane.showMessageDialog(null,"Selecciona una fila","Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        B_editar.addActionListener(alEd);

        JButton B_tablaU = new JButton("Tabla de Usuarios");
        B_tablaU.setSize(250, 120);
        B_tablaU.setLocation(100, 520);
        panel.add(B_tablaU);

//------------------------------------------------------------------------------------------------------------------//

        JScrollPane sp = new JScrollPane();
        JTable tablaP = new JTable();
        tablaP.setSize(700, 450);
        tablaP.setLocation(15, 50);
        sp.setBounds(15, 50, 700, 450);
        sp.setViewportView(tablaP);
        sp.setVisible(false);
        panel.add(sp);


        JTextField txtid = new JTextField();
        panel.add(txtid);
        txtid.setSize(225, 25);
        txtid.setLocation(725, 20);
        txtid.setVisible(false);
        TextPrompt idholder = new TextPrompt("Id", txtid);
        idholder.changeAlpha(0.75f);
        idholder.changeStyle(Font.ITALIC);

        JTextField txtnombre = new JTextField();
        panel.add(txtnombre);
        txtnombre.setSize(225, 25);
        txtnombre.setLocation(725, 70);
        txtnombre.setVisible(false);
        TextPrompt productoholder = new TextPrompt("Nombre del Producto", txtnombre);
        productoholder.changeAlpha(0.75f);
        productoholder.changeStyle(Font.ITALIC);

        JTextField txtcantidad = new JTextField();
        panel.add(txtcantidad);
        txtcantidad.setSize(225, 25);
        txtcantidad.setLocation(725, 120);
        txtcantidad.setVisible(false);
        TextPrompt cantidadholder = new TextPrompt("Cantidad", txtcantidad);
        cantidadholder.changeAlpha(0.75f);
        cantidadholder.changeStyle(Font.ITALIC);

        JTextField txtdepartamento = new JTextField();
        panel.add(txtdepartamento);
        txtdepartamento.setSize(225, 25);
        txtdepartamento.setLocation(725, 170);
        txtdepartamento.setVisible(false);
        TextPrompt deptholder = new TextPrompt("Departamento", txtdepartamento);
        deptholder.changeAlpha(0.75f);
        deptholder.changeStyle(Font.ITALIC);

        tablaP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int fila = tablaP.getSelectedRow();
                if (fila != -1) {
                    txtid.setText(tablaP.getValueAt(fila, 0).toString());
                    txtnombre.setText(tablaP.getValueAt(fila, 1).toString());
                    txtcantidad.setText(tablaP.getValueAt(fila, 2).toString());
                    txtdepartamento.setText(tablaP.getValueAt(fila, 3).toString());
                    id = tablaP.getValueAt(fila, 0).toString();
                }
            }
        });

        JButton B_insertar = new JButton("Insertar");
        B_insertar.setSize(125, 25);
        B_insertar.setLocation(775, 220);
        B_insertar.setVisible(false);
        panel.add(B_insertar);

        ActionListener alI = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect cc = new Connect();
                Connection cn = cc.conectar();
                String value;
                String id = txtid.getText();
                String name = txtnombre.getText();
                boolean siID = false;
                boolean siName = false;
                for (int i = 0; i < tablaP.getRowCount(); i++) {
                    value = tablaP.getValueAt(i, 0).toString();
                    if (value.equals(id)) {
                        siID = true;
                    }
                }
                for (int i = 0; i < tablaP.getRowCount(); i++) {
                    value = tablaP.getValueAt(i, 1).toString();
                    if (value.equals(name)) {
                        siName = true;
                    }
                }
                if (siName) {
                    JOptionPane.showMessageDialog(null, "El producto ya esta en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (siID) {
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con ese Id.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            PreparedStatement pst;
                            pst = cn.prepareStatement("INSERT INTO productos(Id,Nombre,Cantidad,Departamento) VALUES(?,?,?,?)");
                            pst.setString(1, txtid.getText());
                            pst.setString(2, txtnombre.getText());
                            pst.setString(3, txtcantidad.getText());
                            pst.setString(4, txtdepartamento.getText());

                            int a = pst.executeUpdate();
                            if (a > 0) {
                                JOptionPane.showMessageDialog(null, "Añadido Correctamente", "Productos", JOptionPane.INFORMATION_MESSAGE);
                                txtid.setText("");
                                txtnombre.setText("");
                                txtcantidad.setText("");
                                txtdepartamento.setText("");
                                mostrarProductos("", tablaP);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al agregar", "Productos", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            //
                        }
                    }
                }
            }
        };
        B_insertar.addActionListener(alI);

        JButton B_cambiar = new JButton("Cambiar");
        B_cambiar.setSize(125, 25);
        B_cambiar.setLocation(775, 270);
        B_cambiar.setVisible(false);
        panel.add(B_cambiar);

        ActionListener alC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect cc = new Connect();
                Connection cn = cc.conectar();
                String idC = txtid.getText();
                String name = txtnombre.getText();
                int fila = tablaP.getSelectedRow();
                String Rnombre = tablaP.getValueAt(fila, 1).toString();
                String Rid = tablaP.getValueAt(fila, 0).toString();
                String value;
                boolean siID = false;
                boolean siName = false;
                for (int i = 0; i < tablaP.getRowCount(); i++) {
                    value = tablaP.getValueAt(i, 0).toString();
                    if (value.equals(idC) && !value.equals(Rid)) {
                        siID = true;
                    }
                }
                for (int i = 0; i < tablaP.getRowCount(); i++) {
                    value = tablaP.getValueAt(i, 1).toString();
                    if (value.equals(name) && !value.equals(Rnombre)) {
                        siName = true;
                    }
                }
                if (siID) {
                    JOptionPane.showMessageDialog(null, "Ya hay un producto con ese Id.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (siName) {
                        JOptionPane.showMessageDialog(null, "Ya hay un producto con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            PreparedStatement pst = cn.prepareStatement("UPDATE productos SET Nombre='" + txtnombre.getText() + "', Id='" + txtid.getText() + "', Cantidad='" + txtcantidad.getText() + "', Departamento='" + txtdepartamento.getText() + "' WHERE Id='" + id + "' ");
                            id = txtid.getText();

                            txtid.setText("");
                            txtnombre.setText("");
                            txtcantidad.setText("");
                            txtdepartamento.setText("");
                            pst.executeUpdate();
                            mostrarProductos("", tablaP);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: \n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        };
        B_cambiar.addActionListener(alC);

        JTextField txtbuscar = new JTextField();
        panel.add(txtbuscar);
        txtbuscar.setSize(225, 25);
        txtbuscar.setLocation(725, 320);
        txtbuscar.setVisible(false);
        TextPrompt buscaholder = new TextPrompt("Ingresa el texto a buscar", txtbuscar);
        buscaholder.changeAlpha(0.75f);
        buscaholder.changeStyle(Font.ITALIC);

        ActionListener altxtB = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtbuscar.getText().equals("") || txtbuscar.getText() == null) {
                    mostrarProductos(txtbuscar.getText(), tablaP);

                }
            }
        };
        txtbuscar.addActionListener(altxtB);

        JButton B_buscar = new JButton("Buscar");
        B_buscar.setSize(125, 25);
        B_buscar.setLocation(775, 370);
        B_buscar.setVisible(false);
        panel.add(B_buscar);

        ActionListener alB = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductos(txtbuscar.getText(), tablaP);
            }
        };
        B_buscar.addActionListener(alB);

        JButton B_delete = new JButton("Eliminar Producto");
        B_delete.setSize(135, 25);
        B_delete.setLocation(770, 420);
        B_delete.setVisible(false);
        panel.add(B_delete);

        Icon warning=new ImageIcon(Objects.requireNonNull(getClass().getResource("advertencia.png")));

        ActionListener alE = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect cc = new Connect();
                Connection cn = cc.conectar();
                int fila = tablaP.getSelectedRow();
                String cod = "";
                if (fila < 0) {
                    JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    cod = tablaP.getValueAt(fila, 1).toString();
                    int sure = JOptionPane.showConfirmDialog(null,"¿Estas seguro que deseas eliminiar: "+cod+" de la tabla?","ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);
                    if(sure==0) {
                        try {
                            PreparedStatement pst = cn.prepareStatement("DELETE FROM productos WHERE Nombre='" + cod + "'");
                            pst.executeUpdate();
                            txtid.setText("");
                            txtnombre.setText("");
                            txtcantidad.setText("");
                            txtdepartamento.setText("");
                            mostrarProductos("", tablaP);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "No has seleccionado ninguna fila", "Error", JOptionPane.ERROR_MESSAGE,warning);
                        }
                    }
                }
            }
        };
        B_delete.addActionListener(alE);

        JButton B_tablaP = new JButton("Tabla de Productos");
        B_tablaP.setSize(250, 120);
        B_tablaP.setLocation(400, 520);
        panel.add(B_tablaP);

        ActionListener alP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sp.setVisible(true);
                mostrarProductos("", tablaP);
              js.setVisible(false);
              if (rol.equals("Administrador") || rol.equals("Manager")) {
                  txtid.setVisible(true);
                  txtnombre.setVisible(true);
                  txtcantidad.setVisible(true);
                  txtdepartamento.setVisible(true);
                  txtbuscar.setVisible(true);
                  B_buscar.setVisible(true);
                  B_delete.setVisible(true);
                  B_insertar.setVisible(true);
                  B_cambiar.setVisible(true);
                  B_borrar.setVisible(false);
                  B_search.setVisible(false);
                  B_editar.setVisible(false);
                  txtsearch.setVisible(false);
              }
              if (rol.equals("Usuario")){
                  txtid.setVisible(false);
                  txtnombre.setVisible(false);
                  txtcantidad.setVisible(false);
                  txtdepartamento.setVisible(false);
                  B_delete.setVisible(false);
                  B_insertar.setVisible(false);
                  B_cambiar.setVisible(false);
                  txtbuscar.setVisible(true);
                  B_buscar.setVisible(true);
              }
            }
        };
        B_tablaP.addActionListener(alP);

        ActionListener alTU = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtid.setVisible(false);
                txtnombre.setVisible(false);
                txtcantidad.setVisible(false);
                txtdepartamento.setVisible(false);
                txtbuscar.setVisible(false);
                B_buscar.setVisible(false);
                B_insertar.setVisible(false);
                B_delete.setVisible(false);
                B_cambiar.setVisible(false);

                js.setVisible(true);
                B_borrar.setVisible(true);
                B_search.setVisible(true);
                B_editar.setVisible(true);
                txtsearch.setVisible(true);
                txtname.setVisible(true);
                txtrol.setVisible(true);
            }
        };

        B_tablaU.addActionListener(alTU);

        JButton B_regresar = new JButton("Atras");
        B_regresar.setSize(125, 25);
        B_regresar.setLocation(15, 675);
        panel.add(B_regresar);

        ActionListener alR = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                inicio();
            }
        };
        B_regresar.addActionListener(alR);

        if (rol.equals("Usuario")){
            B_tablaU.setVisible(false);
            tablaP.setVisible(true);
        }

    frame.setSize(975,750);
    frame.setResizable(false);
    Image icono = Toolkit.getDefaultToolkit().getImage("carrito-de-compras.png");
    frame.setIconImage(icono);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    }

    void mostrarProductos(String valor, JTable tabla){

        Connect cc=new Connect();
        Connection cn=cc.conectar();
        DefaultTableModel modelo=new DefaultTableModel();

        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Departamento");
        tabla .setModel(modelo);

        String sql="";
        if (valor.equals(""))
        {
            sql="SELECT * FROM productos";
        }
        else{
            sql="SELECT * FROM productos WHERE (Id='"+valor+"' OR Nombre='"+valor+"' OR Departamento='"+valor+"')";
        }

        String []datos=new String [4];
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);

                modelo.addRow(datos);
            }
            tabla.setModel(modelo);
        }catch(SQLException ex){
            //Logger.getLogger(datos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    void mostrarUsuarios(String busqueda){
        try {
            sorter.setRowFilter(RowFilter.regexFilter(busqueda));
        }catch (Exception e){ //
        }
    }
}