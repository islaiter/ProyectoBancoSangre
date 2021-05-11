package proy3ET6;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SwingBancoSangre {

    /**
     * Inicializamos los componentes que vamos a utilizar incluidos tres objetos
     */

    private JFrame marcoPrincipal;

    private JScrollPane Panel1;
    private JScrollPane Panel2;
    private JScrollPane Panel3;

    private JTextField tfFechaNacimiento;
    private JTextField tfDNI;
    private JTextField tfNombre;
    private JTextField tfDireccion;
    private JTextField tfTelefono;
    private JTextField tfDNIDonante;
    private JTextField tfCodigoPostal;
    private JTextField tfLocalidad;
    private JTextField tfMail;

    private JTable tablaDonantes;
    private JTable tablaDonaciones;
    private JTable tablaCompatibilidades;

    protected ActionListener listenerBotonInsertar;
    protected ActionListener listenerBotonModificar;
    protected ActionListener listenerBotonEliminar;
    protected ActionListener listenerBotonConsultar;
    protected ActionListener listenerBotonAveriguar;
    protected ActionListener listenerBotonConsultarDonaciones;

    protected JLabel labelNombreDonante;
    protected JLabel labelTipoSangre;

    InsertarYConsultar funcionalidad;
    ClaseManejadorFichero claseManejadorFicheros;
    Donaciones datosDonaciones;

    public JLabel getLabelNombreDonante() {
        return labelNombreDonante;
    }

    public JLabel getLabelTipoSangre() {
        return labelTipoSangre;
    }

    /**
     * Lanzamos la aplicacion
     * @param args
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingBancoSangre window = new SwingBancoSangre();
                    window.marcoPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *
     * @param tablaALimpiar
     */


    private void metodoLimpiarTablas(JTable tablaALimpiar) {

        for (int i = 0; i < tablaALimpiar.getRowCount(); i++) {
            for (int numeroFilas = 0; numeroFilas < tablaALimpiar.getColumnCount(); numeroFilas++) {
                tablaALimpiar.setValueAt("", i, numeroFilas);
            }
        }
    }

    /**
     *  Creamos la aplicacion
     */

    public SwingBancoSangre() {
        funcionalidad = new InsertarYConsultar();
        claseManejadorFicheros = new ClaseManejadorFichero();
        initialize();
    }

    /**
     * Inicializamos los contenidos del frame principal
     */

    private void initialize() {

        /**
         * Vamos añadiendo componentes segun sea necesario para construir la ventana grafica,
         * entre ellos los jlabel correspondientes
         */

        marcoPrincipal = new JFrame();
        marcoPrincipal.setTitle("Banco de sangre");
        marcoPrincipal.setBounds(100, 100, 1033, 732);
        marcoPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoPrincipal.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 11, 1017, 682);
        marcoPrincipal.getContentPane().add(tabbedPane);

        JPanel panelDonantes = new JPanel();
        panelDonantes.setName("");
        tabbedPane.addTab("Donantes", null, panelDonantes, "Accede a la pesta\u00F1a Donantes");
        panelDonantes.setLayout(null);

        JButton botonConsultarDNI = new JButton("Consultar DNI");
        botonConsultarDNI.setBounds(125, 593, 115, 30);
        panelDonantes.add(botonConsultarDNI);

        JLabel labelDNI = new JLabel("DNI");
        labelDNI.setBounds(163, 89, 98, 14);
        panelDonantes.add(labelDNI);

        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setBounds(163, 127, 98, 14);
        panelDonantes.add(labelNombre);

        JLabel labelDireccion = new JLabel("Direcci\u00F3n");
        labelDireccion.setBounds(163, 165, 98, 14);
        panelDonantes.add(labelDireccion);

        JLabel labelNumeroTelefono = new JLabel("Tel\u00E9fono");
        labelNumeroTelefono.setBounds(530, 165, 98, 14);
        panelDonantes.add(labelNumeroTelefono);

        JLabel labelFechaNacimiento = new JLabel("Fecha de nacimiento");
        labelFechaNacimiento.setBounds(530, 89, 150, 14);
        panelDonantes.add(labelFechaNacimiento);

        /**
         * Con change alpha lo que cambiamos es la transparencia del color asociado
         */

        tfFechaNacimiento = new JTextField();
        TextPrompt placeholderFechaNac = new TextPrompt("aaaa-mm-dd", tfFechaNacimiento);
        placeholderFechaNac.changeAlpha(0.75f);
        placeholderFechaNac.changeStyle(Font.ITALIC);
        tfFechaNacimiento.setToolTipText("aaaa-mm-dd");
        tfFechaNacimiento.setBounds(690, 84, 166, 25);
        panelDonantes.add(tfFechaNacimiento);
        tfFechaNacimiento.setColumns(10);

        tfDNI = new JTextField();
        TextPrompt placeholderDNI = new TextPrompt("12345678X", tfDNI);
        placeholderDNI.changeAlpha(0.75f);
        placeholderDNI.changeStyle(Font.ITALIC);
        tfDNI.setToolTipText("Formato: 12345678X");
        tfDNI.setBounds(271, 84, 166, 25);
        panelDonantes.add(tfDNI);
        tfDNI.setColumns(10);

        tfNombre = new JTextField();
        tfNombre.setToolTipText("Introduzca el mombre y apellidos");
        tfNombre.setColumns(10);
        tfNombre.setBounds(271, 122, 166, 25);
        panelDonantes.add(tfNombre);

        tfDireccion = new JTextField();
        tfDireccion.setToolTipText("Introduzca la dirección");
        tfDireccion.setColumns(10);
        tfDireccion.setBounds(271, 160, 166, 25);
        panelDonantes.add(tfDireccion);

        tfTelefono = new JTextField();
        TextPrompt placeholderTelf = new TextPrompt("123456789", tfTelefono);
        placeholderTelf.changeAlpha(0.75f);
        placeholderTelf.changeStyle(Font.ITALIC);
        tfTelefono.setToolTipText("Formato: 123456789");
        tfTelefono.setColumns(10);
        tfTelefono.setBounds(690, 160, 166, 25);
        panelDonantes.add(tfTelefono);

        JLabel labelGrupoSanguineo = new JLabel("Grupo Sangu\u00EDneo");
        labelGrupoSanguineo.setBounds(530, 203, 106, 14);
        panelDonantes.add(labelGrupoSanguineo);

        JLabel lblFactorRH = new JLabel("Factor RH");
        lblFactorRH.setBounds(530, 241, 89, 14);
        panelDonantes.add(lblFactorRH);

        /**
         * Los combobox no son mas que la forma que tiene Swing de alojar elementos de listas desplegables
         */

        JComboBox cbxGrupoSanguineo = new JComboBox();
        cbxGrupoSanguineo.setModel(new DefaultComboBoxModel(new String[]{"0", "A", "B", "AB"}));
        cbxGrupoSanguineo.setMaximumRowCount(4);
        cbxGrupoSanguineo.setBounds(690, 198, 166, 25);
        panelDonantes.add(cbxGrupoSanguineo);

        JComboBox cbxFactorRH = new JComboBox();
        cbxFactorRH.setModel(new DefaultComboBoxModel(new String[]{"-", "+"}));
        cbxFactorRH.setMaximumRowCount(2);
        cbxFactorRH.setBounds(690, 236, 166, 25);
        panelDonantes.add(cbxFactorRH);

        /**
         * Evitamos que el usuario pueda reordenar o cambiar el tamaño de los elementos
         * mediante arrastre o click
         */

        /**
         * Creamos a su vez la tabla de donantes, a la que asignamos el valor de null para
         * todos los objetos, ya que se crea de forma distinta a como lo habiamos visto en JavaFX
         * en el modulo de Entorno de Desarrollo. A su vez, para que pueda visualizarse correctamente
         * tenemos que rodearla de tags en HTML
         */

        tablaDonantes = new JTable();
        Panel2 = new JScrollPane(tablaDonantes);
        Panel2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaDonantes.setDefaultRenderer(Object.class, centerRenderer);
        tablaDonantes.setDefaultRenderer(Float.class, centerRenderer);
        tablaDonantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tablaDonantes.setFillsViewportHeight(true);
        tablaDonantes.getTableHeader().setReorderingAllowed(false);
        tablaDonantes.getTableHeader().setResizingAllowed(false);
        tablaDonantes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tablaDonantes.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                },
                new String[]{
                        "<html><b>DNI</b></html>", "<html><b>Nombre</b></html>", "<html><b>Dirección</b></html>", "<html><b>C.P</b></html>", "<html><b>Localidad</b></html>", "<html><b>Fecha Nac</b></html>", "<html><b>E-Mail</b></html>", "<html><b>Teléfono</b></html>", "<html><b>Grupo Sang</b></html>", "<html><b>Factor RH</b></html>"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        /**
         * Añadimos todas las columnas a la tabla, esto es dni donante, nombre ,etcetc
         */

        tablaDonantes.getColumnModel().getColumn(0).setMinWidth(75);
        tablaDonantes.getColumnModel().getColumn(1).setPreferredWidth(100);
        tablaDonantes.getColumnModel().getColumn(1).setMinWidth(100);
        tablaDonantes.getColumnModel().getColumn(2).setPreferredWidth(115);
        tablaDonantes.getColumnModel().getColumn(2).setMinWidth(115);
        tablaDonantes.getColumnModel().getColumn(3).setMinWidth(75);
        tablaDonantes.getColumnModel().getColumn(3).setPreferredWidth(115);
        tablaDonantes.getColumnModel().getColumn(4).setMinWidth(115);
        tablaDonantes.getColumnModel().getColumn(4).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(5).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(5).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(6).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(6).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(7).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(7).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(8).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(8).setPreferredWidth(107);
        tablaDonantes.getColumnModel().getColumn(9).setPreferredWidth(107);

        tablaDonantes.setBounds(32, 311, 948, 247);
        Panel2.setBounds(32, 311, 948, 247);

        /**
         * Seguimos añadiendo elementos
         */

        panelDonantes.add(Panel2);

        JButton botonConsultarTodos = new JButton("Consultar todos");
        botonConsultarTodos.setBounds(279, 593, 150, 30);
        panelDonantes.add(botonConsultarTodos);

        JButton botonInsertar = new JButton("Insertar");
        botonInsertar.setBounds(469, 593, 115, 30);
        panelDonantes.add(botonInsertar);

        JButton botonModificar = new JButton("Modificar");
        botonModificar.setBounds(623, 593, 115, 30);
        panelDonantes.add(botonModificar);

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setBounds(777, 593, 115, 30);
        panelDonantes.add(botonEliminar);

        JLabel labelTituloDonantes = new JLabel("GESTI\u00D3N BANCO DE SANGRE");
        labelTituloDonantes.setFont(new Font("Verdana", Font.BOLD, 30));
        labelTituloDonantes.setHorizontalAlignment(SwingConstants.CENTER);
        labelTituloDonantes.setBounds(10, 11, 992, 62);
        panelDonantes.add(labelTituloDonantes);

        JLabel labelCodigoPostal = new JLabel("C.P.");
        labelCodigoPostal.setBounds(163, 203, 98, 14);
        panelDonantes.add(labelCodigoPostal);

        tfCodigoPostal = new JTextField();
        TextPrompt placeholderCP = new TextPrompt("12345", tfCodigoPostal);
        placeholderCP.changeAlpha(0.75f);
        placeholderCP.changeStyle(Font.ITALIC);
        tfCodigoPostal.setToolTipText("Formato: 12345");
        tfCodigoPostal.setColumns(10);
        tfCodigoPostal.setBounds(271, 198, 166, 25);
        panelDonantes.add(tfCodigoPostal);

        JLabel labelLocalidad = new JLabel("Localidad");
        labelLocalidad.setBounds(163, 241, 98, 14);
        panelDonantes.add(labelLocalidad);

        tfLocalidad = new JTextField();
        tfLocalidad.setToolTipText("Introduzca la localidad");
        tfLocalidad.setColumns(10);
        tfLocalidad.setBounds(271, 236, 166, 25);
        panelDonantes.add(tfLocalidad);

        JLabel labelEmail = new JLabel("E-Mail");
        labelEmail.setBounds(530, 127, 129, 14);
        panelDonantes.add(labelEmail);

        tfMail = new JTextField();
        TextPrompt placeholderMail= new TextPrompt("nombre@organizacion.tipo", tfMail);
        placeholderMail.changeAlpha(0.75f);
        placeholderMail.changeStyle(Font.ITALIC);
        tfMail.setToolTipText("Formato: nombre@organizacion.tipo");
        tfMail.setColumns(10);
        tfMail.setBounds(690, 122, 166, 25);
        panelDonantes.add(tfMail);

        JPanel panelDonaciones = new JPanel();
        tabbedPane.addTab("Donaciones", null, panelDonaciones, "Accede a la pesta\u00F1a Donaciones");
        panelDonaciones.setLayout(null);

        JLabel labelDNIDonante = new JLabel("DNI Donante");
        labelDNIDonante.setBounds(117, 106, 70, 14);
        panelDonaciones.add(labelDNIDonante);

        tfDNIDonante = new JTextField();
        TextPrompt phDNIDonante = new TextPrompt("12345678X", tfDNIDonante);
        phDNIDonante.changeAlpha(0.75f);
        phDNIDonante.changeStyle(Font.ITALIC);
        tfDNIDonante.setToolTipText("Formato: 12345678X");
        tfDNIDonante.setBounds(215, 101, 166, 25);
        panelDonaciones.add(tfDNIDonante);
        tfDNIDonante.setColumns(10);

        JButton botonConsultarDonaciones = new JButton("Consultar donaciones");
        botonConsultarDonaciones.setBounds(158, 152, 166, 30);
        panelDonaciones.add(botonConsultarDonaciones);

        JLabel labelNombreDelDonante = new JLabel("Nombre Donante:");
        labelNombreDelDonante.setBounds(492, 106, 122, 14);
        panelDonaciones.add(labelNombreDelDonante);

        labelNombreDonante = new JLabel("");
        labelNombreDonante.setHorizontalAlignment(SwingConstants.LEFT);
        labelNombreDonante.setBounds(624, 106, 265, 14);
        panelDonaciones.add(labelNombreDonante);

        JLabel lblTipoSangre = new JLabel("Tipo de sangre:");
        lblTipoSangre.setBounds(492, 160, 122, 14);
        panelDonaciones.add(lblTipoSangre);

        labelTipoSangre = new JLabel("");
        labelTipoSangre.setBounds(624, 160, 265, 14);
        panelDonaciones.add(labelTipoSangre);

        tablaDonaciones = new JTable();
        Panel1 = new JScrollPane(tablaDonaciones);

        /**
         * Esta es la segunda tabla, la de donaciones
         */

        Panel1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tablaDonaciones.setDefaultRenderer(Object.class, centerRenderer);
        tablaDonaciones.setDefaultRenderer(Float.class, centerRenderer);
        tablaDonaciones.setName("");
        tablaDonaciones.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tablaDonaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tablaDonaciones.setDefaultRenderer(Object.class, centerRenderer);
        tablaDonaciones.setFillsViewportHeight(true);
        tablaDonaciones.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[]{
                        "<html><b>C\u00F3d. Sanitario<b><html>", "<html><b>Nombre Sanitario<b><html>", "<html><b>Fecha<b><html>", "<html><b>Cantidad (ml)<b><html>", "<html><b>Incidencia<b><html>"
                }
        ) {
            Class[] columnTypes = new Class[]{
                    Object.class, Object.class, String.class, Float.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        /**
         * Seguimos añadiendo elementos
         */

        tablaDonaciones.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaDonaciones.getColumnModel().getColumn(0).setMinWidth(100);
        tablaDonaciones.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaDonaciones.getColumnModel().getColumn(1).setMinWidth(150);
        tablaDonaciones.getColumnModel().getColumn(2).setPreferredWidth(115);
        tablaDonaciones.getColumnModel().getColumn(2).setMinWidth(115);
        tablaDonaciones.setBounds(20, 209, 970, 407);
        Panel1.setBounds(20, 209, 970, 407);
        panelDonaciones.add(Panel1);

        JLabel lblTituloDonaciones = new JLabel("GESTIÓN BANCO DE SANGRE");
        lblTituloDonaciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloDonaciones.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTituloDonaciones.setBounds(10, 11, 992, 62);
        panelDonaciones.add(lblTituloDonaciones);

        JPanel panelCompatibilidad = new JPanel();
        tabbedPane.addTab("Compatibilidad", null, panelCompatibilidad, "Accede a la pestaña Compatibilidad");
        panelCompatibilidad.setLayout(null);

        JLabel lblGrupoSanguineoComp = new JLabel("Grupo Sanguíneo");
        lblGrupoSanguineoComp.setBounds(105, 164, 168, 14);
        panelCompatibilidad.add(lblGrupoSanguineoComp);

        JComboBox cbxGrupoSanguineoComp = new JComboBox();
        cbxGrupoSanguineoComp.setModel(new DefaultComboBoxModel(new String[]{"0", "A", "B", "AB"}));
        cbxGrupoSanguineoComp.setMaximumRowCount(4);
        cbxGrupoSanguineoComp.setBounds(283, 159, 123, 25);
        panelCompatibilidad.add(cbxGrupoSanguineoComp);

        JLabel lblFactorRHcomp = new JLabel("Factor RH");
        lblFactorRHcomp.setBounds(105, 216, 168, 14);
        panelCompatibilidad.add(lblFactorRHcomp);

        JComboBox cbxFactorRHcomp = new JComboBox();
        cbxFactorRHcomp.setModel(new DefaultComboBoxModel(new String[]{"+", "-"}));
        cbxFactorRHcomp.setMaximumRowCount(2);
        cbxFactorRHcomp.setBounds(283, 211, 123, 25);
        panelCompatibilidad.add(cbxFactorRHcomp);

        JLabel lblTituloCompatibilidad = new JLabel("GESTIÓN BANCO DE SANGRE");
        lblTituloCompatibilidad.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloCompatibilidad.setFont(new Font("Verdana", Font.BOLD, 30));
        lblTituloCompatibilidad.setBounds(10, 25, 992, 62);
        panelCompatibilidad.add(lblTituloCompatibilidad);

        JButton btnAveriguarCompatibilidad = new JButton("Averiguar compatibilidad");
        btnAveriguarCompatibilidad.setBounds(136, 279, 211, 30);
        panelCompatibilidad.add(btnAveriguarCompatibilidad);

        /**
         * Por ultimo, la tabla de compatibilidades entre tipos de sangre
         */

        tablaCompatibilidades = new JTable();
        Panel3 = new JScrollPane(tablaCompatibilidades);
        Panel3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        tablaCompatibilidades.setDefaultRenderer(Object.class, centerRenderer);
        tablaCompatibilidades.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tablaCompatibilidades.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tablaCompatibilidades.getTableHeader().setReorderingAllowed(false);
        tablaCompatibilidades.getTableHeader().setResizingAllowed(false);
        tablaCompatibilidades.setFillsViewportHeight(true);
        tablaCompatibilidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCompatibilidades.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                        {null, null},
                },
                new String[]{
                        "<html><b>Puede donar a</b></html>", "<html><b>Puede recibir de</b></html>"
                }
        ) {

            /**
             * Creamos una pequeña clase para poder añadir las columnas y que pasen
             * a poder ser editables, asi conseguiremos cambiar el contenido como es necesario
             */

            Class[] columnTypes = new Class[]{
                    Object.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[]{
                    false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        tablaCompatibilidades.getColumnModel().getColumn(0).setPreferredWidth(115);
        tablaCompatibilidades.getColumnModel().getColumn(0).setMinWidth(115);
        tablaCompatibilidades.getColumnModel().getColumn(1).setPreferredWidth(113);
        tablaCompatibilidades.getColumnModel().getColumn(1).setMinWidth(113);
        tablaCompatibilidades.setBounds(525, 159, 439, 151);
        Panel3.setBounds(525, 159, 439, 151);
        panelCompatibilidad.add(Panel3);

        /**
         * Añadimos la imagenes necesarias
         */

        JLabel lblLogoDonantes = new JLabel("");
        lblLogoDonantes.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoDonantes.setIcon(new ImageIcon("src/logoSwing.png"));
        lblLogoDonantes.setBounds(203, 0, 652, 750);
        panelDonantes.add(lblLogoDonantes);

        JLabel lblLogoDonaciones = new JLabel("");
        lblLogoDonaciones.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoDonaciones.setIcon(new ImageIcon("src/logoSwing.png"));
        lblLogoDonaciones.setBounds(203, 0, 652, 750);
        panelDonaciones.add(lblLogoDonaciones);

        JLabel lblLogoCompatibilidad = new JLabel("");
        lblLogoCompatibilidad.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoCompatibilidad.setIcon(new ImageIcon("src/logoSwing.png"));
        lblLogoCompatibilidad.setBounds(203, 0, 652, 750);
        panelCompatibilidad.add(lblLogoCompatibilidad);

        /**
         * Seccion para añadir todos los listener necesarios junto con su funcionalidad
         */

        listenerBotonInsertar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dni = tfDNI.getText();
                String nomDonante = tfNombre.getText();
                String direccionDonante = tfDireccion.getText();
                String codPostal = tfCodigoPostal.getText();
                String localidad = tfLocalidad.getText();
                String telefono = tfTelefono.getText();
                String fechaNac = tfFechaNacimiento.getText();
                String mail = tfMail.getText();
                String grupoSang = cbxGrupoSanguineo.getSelectedItem().toString();
                String factorRH = cbxFactorRH.getSelectedItem().toString();
                System.out.println(factorRH);

                if (e.getSource() == botonInsertar) {
                    boolean a = funcionalidad.insertar("INSERT INTO DONANTES(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES('" + dni + "', '" + nomDonante + "', '" + direccionDonante + "', '" + codPostal + "', '" + localidad + "', '" + fechaNac + "', '" + mail + "', '" + telefono + "', '" + grupoSang + "', '" + factorRH + "')");
                    if (a) {
                        tfDNI.setText("");
                        tfNombre.setText("");
                        tfDireccion.setText("");
                        tfCodigoPostal.setText("");
                        tfLocalidad.setText("");
                        tfTelefono.setText("");
                        tfFechaNacimiento.setText("");
                        tfMail.setText("");
                        cbxGrupoSanguineo.setSelectedIndex(0);
                        cbxFactorRH.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valores introducidos erróneos", "Insertar registro", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };
        botonInsertar.addActionListener(listenerBotonInsertar);


        listenerBotonModificar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dni = tfDNI.getText();
                String nomDonante = tfNombre.getText();
                String direccionDonante = tfDireccion.getText();
                String codPostal = tfCodigoPostal.getText();
                String localidad = tfLocalidad.getText();
                String telefono = tfTelefono.getText();
                String fechaNac = tfFechaNacimiento.getText();
                String mail = tfMail.getText();
                String grupoSang = cbxGrupoSanguineo.getSelectedItem().toString();
                String factorRH = cbxFactorRH.getSelectedItem().toString();

                if (e.getSource() == botonModificar) {
                    boolean a = funcionalidad.insertar("UPDATE DONANTES SET Nombre ='" + nomDonante + "', Direccion ='" + direccionDonante + "', CodPostal ='" + codPostal + "', Localidad = '" + localidad + "', FechaNac = '" + fechaNac + "', Correo ='" + mail + "', Telefono ='" + telefono + "', GrupoSang ='" + grupoSang + "', FactorRH ='" + factorRH + "' WHERE DNI = '" + dni + "'");
                    if (a) {
                        tfDNI.setText("");
                        tfNombre.setText("");
                        tfDireccion.setText("");
                        tfCodigoPostal.setText("");
                        tfLocalidad.setText("");
                        tfTelefono.setText("");
                        tfFechaNacimiento.setText("");
                        tfMail.setText("");
                        cbxGrupoSanguineo.setSelectedIndex(0);
                        cbxFactorRH.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valores introducidos erróneos", "Modificar registro", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };
        botonModificar.addActionListener(listenerBotonModificar);

        listenerBotonEliminar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dni = tfDNI.getText();

                if (e.getSource() == botonEliminar) {
                    boolean a = funcionalidad.insertar("DELETE FROM DONANTES WHERE DNI = '" + dni + "'");
                    if (a) {
                        tfDNI.setText("");
                        tfNombre.setText("");
                        tfDireccion.setText("");
                        tfCodigoPostal.setText("");
                        tfLocalidad.setText("");
                        tfTelefono.setText("");
                        tfFechaNacimiento.setText("");
                        tfMail.setText("");
                        cbxGrupoSanguineo.setSelectedIndex(0);
                        cbxFactorRH.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Valores introducidos erróneos", "Eliminar registro", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        };
        botonEliminar.addActionListener(listenerBotonEliminar);

        listenerBotonConsultar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResultSet sql;
                String dni = tfDNI.getText();
                String nomDonante = "";
                String direccionDonante = "";
                String codPostal = "";
                String localidad = "";
                String telefono = "";
                String fechaNac = "";
                String mail = "";
                String grupoSang = "";
                String factorRH = "";

                if (e.getSource() == botonConsultarTodos) {
                    sql = funcionalidad.consulta("SELECT * FROM DONANTES");
                    try {
                        metodoLimpiarTablas(tablaDonantes);
                        int i = 0;
                        while (sql.next()) {
                            tablaDonantes.setValueAt(sql.getString(1), i, 0);
                            tablaDonantes.setValueAt(sql.getString(2), i, 1);
                            tablaDonantes.setValueAt(sql.getString(3), i, 2);
                            tablaDonantes.setValueAt(sql.getString(4), i, 3);
                            tablaDonantes.setValueAt(sql.getString(5), i, 4);
                            tablaDonantes.setValueAt(sql.getString(6), i, 5);
                            tablaDonantes.setValueAt(sql.getString(7), i, 6);
                            tablaDonantes.setValueAt(sql.getString(8), i, 7);
                            tablaDonantes.setValueAt(sql.getString(9), i, 8);
                            tablaDonantes.setValueAt(sql.getString(10), i, 9);
                            i++;
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if (e.getSource() == botonConsultarDNI) {
                    sql = funcionalidad.consulta("SELECT * FROM DONANTES WHERE DNI = '" + dni + "'");
                    try {
                        metodoLimpiarTablas(tablaDonantes);
                        if (sql.next()) {
                            nomDonante = sql.getString("Nombre");
                            direccionDonante = sql.getString("Direccion");
                            codPostal = sql.getString("CodPostal");
                            localidad = sql.getString("Localidad");
                            fechaNac = sql.getString("FechaNac");
                            mail = sql.getString("Correo");
                            telefono = sql.getString("Telefono");
                            grupoSang = sql.getString("GrupoSang");
                            factorRH = sql.getString("FactorRH");

                            tablaDonantes.setValueAt(dni, 0, 0);
                            tablaDonantes.setValueAt(nomDonante, 0, 1);
                            tablaDonantes.setValueAt(direccionDonante, 0, 2);
                            tablaDonantes.setValueAt(codPostal, 0, 3);
                            tablaDonantes.setValueAt(localidad, 0, 4);
                            tablaDonantes.setValueAt(fechaNac, 0, 5);
                            tablaDonantes.setValueAt(mail, 0, 6);
                            tablaDonantes.setValueAt(telefono, 0, 7);
                            tablaDonantes.setValueAt(grupoSang, 0, 8);
                            tablaDonantes.setValueAt(factorRH, 0, 9);

                        } else {
                            JOptionPane.showMessageDialog(null, "Valores introducidos erróneos", "Consultar DNI", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception i) {
                        System.out.println(i.getMessage());
                    }
                }
            }
        };
        botonConsultarTodos.addActionListener(listenerBotonConsultar);
        botonConsultarDNI.addActionListener(listenerBotonConsultar);

        listenerBotonAveriguar = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> puedeDonarA = new ArrayList();
                ArrayList<String> puedeRecibirDe = new ArrayList();

                puedeDonarA.clear();
                puedeRecibirDe.clear();
                metodoLimpiarTablas(tablaCompatibilidades);

                if (e.getSource() == btnAveriguarCompatibilidad) {
                    if (cbxFactorRHcomp.getSelectedItem().equals("+")) {
                        switch ((String) cbxGrupoSanguineoComp.getSelectedItem()) {
                            case "0":
                                puedeDonarA.add("0+");
                                puedeDonarA.add("A+");
                                puedeDonarA.add("B+");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("0+");
                                break;
                            case "A":
                                puedeDonarA.add("A+");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("0+");
                                puedeRecibirDe.add("A-");
                                puedeRecibirDe.add("A+");
                                break;
                            case "B":
                                puedeDonarA.add("B+");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("0+");
                                puedeRecibirDe.add("B-");
                                puedeRecibirDe.add("B+");
                                break;
                            case "AB":
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("0+");
                                puedeRecibirDe.add("A-");
                                puedeRecibirDe.add("A+");
                                puedeRecibirDe.add("B-");
                                puedeRecibirDe.add("B+");
                                puedeRecibirDe.add("AB-");
                                puedeRecibirDe.add("AB+");
                                break;
                        }
                    } else {
                        switch ((String) cbxGrupoSanguineoComp.getSelectedItem()) {
                            case "0":
                                puedeRecibirDe.add("0-");
                                puedeDonarA.add("0-");
                                puedeDonarA.add("0+");
                                puedeDonarA.add("A-");
                                puedeDonarA.add("A+");
                                puedeDonarA.add("B-");
                                puedeDonarA.add("B+");
                                puedeDonarA.add("AB-");
                                puedeDonarA.add("AB+");
                                break;
                            case "A":
                                puedeDonarA.add("A-");
                                puedeDonarA.add("A+");
                                puedeDonarA.add("AB-");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("A-");
                                break;
                            case "B":
                                puedeDonarA.add("B-");
                                puedeDonarA.add("B+");
                                puedeDonarA.add("AB-");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("B-");
                                break;
                            case "AB":
                                puedeDonarA.add("AB-");
                                puedeDonarA.add("AB+");
                                puedeRecibirDe.add("0-");
                                puedeRecibirDe.add("A-");
                                puedeRecibirDe.add("B-");
                                puedeRecibirDe.add("AB-");
                                break;
                        }
                    }

                    for (int i = 0; i < puedeDonarA.size(); i++) {
                        tablaCompatibilidades.setValueAt(puedeDonarA.get(i), i, 0);
                    }

                    for (int i = 0; i < puedeRecibirDe.size(); i++) {
                        tablaCompatibilidades.setValueAt(puedeRecibirDe.get(i), i, 1);
                    }
                }
            }
        };
        btnAveriguarCompatibilidad.addActionListener(listenerBotonAveriguar);

        listenerBotonConsultarDonaciones = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String dni = tfDNIDonante.getText();

                if (e.getSource() == botonConsultarDonaciones) {
                    metodoLimpiarTablas(tablaDonaciones);

                    claseManejadorFicheros.leerDatosFicheroDAT(dni, tablaDonaciones, labelNombreDonante, labelTipoSangre);
                    datosDonaciones = claseManejadorFicheros.getDonaciones();
                }
            }
        };
        botonConsultarDonaciones.addActionListener(listenerBotonConsultarDonaciones);

        marcoPrincipal.setLocationRelativeTo(null);
    }
}
