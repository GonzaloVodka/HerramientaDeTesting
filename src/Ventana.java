import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ventana {

    private JFrame frmHerramientaDeTesting;
    private List<File> listaCodigosFuente = new ArrayList<>();
    private List<String> clasesArchivo;
    private List<String> metodosArchivo;
    private File fuenteAct;
    private AnalisisDeMetodo analizador = new AnalisisDeMetodo();
    private FilenameFilter fuenteJava = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".java");
        }
    };

    /**
     * Create the application.
     */
    public Ventana() {
        initialize();
    }

    /**
     * Launch the application.
     *
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ventana window = new Ventana();
                    window.frmHerramientaDeTesting.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmHerramientaDeTesting = new JFrame();
        frmHerramientaDeTesting.setType(Type.UTILITY);
        frmHerramientaDeTesting.setResizable(false);
        frmHerramientaDeTesting.setTitle("Herramienta de Testing");
        frmHerramientaDeTesting.setBounds(100, 100, 800, 600);
        frmHerramientaDeTesting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmHerramientaDeTesting.getContentPane().setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setBounds(10, 56, 774, 504);
        frmHerramientaDeTesting.getContentPane().add(panel2);
        panel2.setLayout(null);

        JPanel PanelFuente = new JPanel();
        PanelFuente.setBorder(
                new TitledBorder(null, "C\u00F3digo Fuente", TitledBorder.TRAILING, TitledBorder.TOP, null, null));
        PanelFuente.setBounds(10, 11, 535, 482);
        panel2.add(PanelFuente);
        PanelFuente.setLayout(null);
        JTextPane textoFuente = new JTextPane();
        PanelFuente.add(textoFuente);
        textoFuente.setEditable(false);
        textoFuente.setBounds(1, 11, 513, 438);
        // PanelFuente.add(textoFuente);
        JScrollPane sp = new JScrollPane(textoFuente);
        sp.setBounds(10, 16, 515, 450);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        PanelFuente.add(sp);

        JPanel PanelAnalisis = new JPanel();
        PanelAnalisis.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
                "An\u00E1lisis del M\u00E9todo", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        PanelAnalisis.setBounds(555, 11, 209, 482);
        panel2.add(PanelAnalisis);
        PanelAnalisis.setLayout(null);

        JLabel lblCantidadDeLneas = new JLabel("Cantidad de l\u00EDneas de c\u00F3digo:");
        lblCantidadDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
        lblCantidadDeLneas.setBounds(10, 22, 189, 20);
        PanelAnalisis.add(lblCantidadDeLneas);

        JLabel nroLineas = new JLabel("");
        nroLineas.setHorizontalAlignment(SwingConstants.CENTER);
        nroLineas.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroLineas.setBounds(10, 43, 189, 25);
        PanelAnalisis.add(nroLineas);

        JLabel nroLineasComentadas = new JLabel("");
        nroLineasComentadas.setHorizontalAlignment(SwingConstants.CENTER);
        nroLineasComentadas.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroLineasComentadas.setBounds(10, 100, 189, 25);
        PanelAnalisis.add(nroLineasComentadas);

        JLabel lblCantidadComentarios = new JLabel("Cantidad de l\u00EDneas con comentarios:");
        lblCantidadComentarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblCantidadComentarios.setBounds(10, 79, 189, 20);
        PanelAnalisis.add(lblCantidadComentarios);

        JLabel porcentajeComentarios = new JLabel("");
        porcentajeComentarios.setHorizontalAlignment(SwingConstants.CENTER);
        porcentajeComentarios.setFont(new Font("Tahoma", Font.BOLD, 15));
        porcentajeComentarios.setBounds(10, 157, 189, 25);
        PanelAnalisis.add(porcentajeComentarios);

        JLabel lblPorcentajeDeLneas = new JLabel("Porcentaje de l\u00EDneas comentadas:");
        lblPorcentajeDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
        lblPorcentajeDeLneas.setBounds(10, 136, 189, 20);
        PanelAnalisis.add(lblPorcentajeDeLneas);

        JLabel nroComplejidad = new JLabel("");
        nroComplejidad.setHorizontalAlignment(SwingConstants.CENTER);
        nroComplejidad.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroComplejidad.setBounds(10, 214, 189, 25);
        PanelAnalisis.add(nroComplejidad);

        JLabel lblComplejidadCiclomtica = new JLabel("Complejidad Ciclom\u00E1tica:");
        lblComplejidadCiclomtica.setHorizontalAlignment(SwingConstants.CENTER);
        lblComplejidadCiclomtica.setBounds(10, 193, 189, 20);
        PanelAnalisis.add(lblComplejidadCiclomtica);
        
        JLabel lblFanIn = new JLabel("Fan In:");
        lblFanIn.setHorizontalAlignment(SwingConstants.CENTER);
        lblFanIn.setBounds(10, 244, 94, 20);
        PanelAnalisis.add(lblFanIn);
        
        JLabel nroFanIn = new JLabel("");
        nroFanIn.setHorizontalAlignment(SwingConstants.CENTER);
        nroFanIn.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroFanIn.setBounds(10, 275, 94, 25);
        PanelAnalisis.add(nroFanIn);
        
        JLabel nroFanOut = new JLabel("");
        nroFanOut.setHorizontalAlignment(SwingConstants.CENTER);
        nroFanOut.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroFanOut.setBounds(105, 275, 94, 25);
        PanelAnalisis.add(nroFanOut);
        
        JLabel lblFanOut = new JLabel("Fan Out:");
        lblFanOut.setHorizontalAlignment(SwingConstants.CENTER);
        lblFanOut.setBounds(105, 244, 94, 20);
        PanelAnalisis.add(lblFanOut);
        
        JLabel lblLongitudDeHalstead = new JLabel("Longitud de Halstead:");
        lblLongitudDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
        lblLongitudDeHalstead.setBounds(10, 311, 189, 20);
        PanelAnalisis.add(lblLongitudDeHalstead);
        
        JLabel nroLongitud = new JLabel("");
        nroLongitud.setHorizontalAlignment(SwingConstants.CENTER);
        nroLongitud.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroLongitud.setBounds(10, 342, 189, 25);
        PanelAnalisis.add(nroLongitud);
        
        JLabel lblVolumenDeHalstead = new JLabel("Volumen de Halstead:");
        lblVolumenDeHalstead.setHorizontalAlignment(SwingConstants.CENTER);
        lblVolumenDeHalstead.setBounds(10, 378, 189, 20);
        PanelAnalisis.add(lblVolumenDeHalstead);
        
        JLabel nroVolumen = new JLabel("");
        nroVolumen.setHorizontalAlignment(SwingConstants.CENTER);
        nroVolumen.setFont(new Font("Tahoma", Font.BOLD, 15));
        nroVolumen.setBounds(10, 409, 189, 25);
        PanelAnalisis.add(nroVolumen);
        
        JButton btnVerOperadoresY = new JButton("Ver operadores y operandos");
        btnVerOperadoresY.setEnabled(false);
        btnVerOperadoresY.setBounds(20, 448, 169, 23);
        PanelAnalisis.add(btnVerOperadoresY);

        JLabel lblArchivo = new JLabel("Archivo:");
        lblArchivo.setBounds(159, 11, 46, 14);
        frmHerramientaDeTesting.getContentPane().add(lblArchivo);

        JLabel lblClase = new JLabel("Clase:");
        lblClase.setBounds(304, 11, 46, 14);
        frmHerramientaDeTesting.getContentPane().add(lblClase);

        JLabel lblMtodo = new JLabel("M\u00E9todo:");
        lblMtodo.setBounds(449, 11, 46, 14);
        frmHerramientaDeTesting.getContentPane().add(lblMtodo);

        JComboBox<String> listaMetodos = new JComboBox<String>();
        listaMetodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (listaMetodos.getSelectedIndex() == -1)
                    return;

                Metodo metodo = analizador.getMetodo(listaMetodos.getSelectedIndex());
                btnVerOperadoresY.setEnabled(true);
                textoFuente.setContentType("text/html");
                textoFuente.setText("<html><pre><code>" + metodo.getCodigo() + "</code></pre></html>");
                nroLineas.setText(metodo.getLineas());
                nroLineasComentadas.setText(metodo.getComentarios());
                porcentajeComentarios.setText(metodo.getPorcentaje());
                nroComplejidad.setText(metodo.getComplejidad());
                nroFanIn.setText(analizador.getFanIn(listaMetodos.getSelectedIndex()));
                nroFanOut.setText(analizador.getFanOut(listaMetodos.getSelectedIndex()));
                // TODO actualizar aqui los tags del analisis del metodo (cant lineas,
                // complejidad, etc.)
            }
        });
        listaMetodos.setMaximumRowCount(8);
        listaMetodos.setBounds(449, 25, 135, 20);
        listaMetodos.setEnabled(false);
        listaMetodos.setEditable(false);
        frmHerramientaDeTesting.getContentPane().add(listaMetodos);

        JComboBox<String> listaClases = new JComboBox<String>();
        listaClases.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (listaClases.getSelectedIndex() == -1)
                    return;
                listaMetodos.removeAllItems();
                metodosArchivo = analizador.encontrarMetodos(listaClases.getSelectedIndex());
                for (String string : metodosArchivo) {
                    listaMetodos.addItem(string);
                }
                listaMetodos.setEnabled(true);
            }
        });
        listaClases.setMaximumRowCount(8);
        listaClases.setEnabled(false);
        listaClases.setEditable(false);
        listaClases.setBounds(304, 25, 135, 20);
        frmHerramientaDeTesting.getContentPane().add(listaClases);

        JComboBox<String> listaArchivos = new JComboBox<String>();
        listaArchivos.setMaximumRowCount(8);
        listaArchivos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listaArchivos.getSelectedIndex() == -1)
                    return;
                fuenteAct = listaCodigosFuente.get(listaArchivos.getSelectedIndex());
                listaClases.removeAllItems();
                try {
                    clasesArchivo = analizador.encontrarClases(fuenteAct);
                    for (String string : clasesArchivo) {
                        listaClases.addItem(string);
                    }
                    listaClases.setEnabled(true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        listaArchivos.setBounds(159, 25, 135, 20);
        listaArchivos.setEnabled(false);
        listaArchivos.setEditable(false);

        frmHerramientaDeTesting.getContentPane().add(listaArchivos);

        JButton btnSeleccionarCarpeta = new JButton("Seleccionar Carpeta");
        btnSeleccionarCarpeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser archivos = new JFileChooser();
                archivos.setCurrentDirectory(null);
                archivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                archivos.setDialogTitle("Seleccione carpeta del c�digo fuente");
                archivos.setAcceptAllFileFilterUsed(false);
                int ret = archivos.showOpenDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    listaArchivos.removeAllItems();
                    listaCodigosFuente.clear();
                    frmHerramientaDeTesting
                            .setTitle("Herramienta de Testing - " + archivos.getSelectedFile().getPath());

                    for (File f : archivos.getSelectedFile().listFiles(fuenteJava)) {
                        listaCodigosFuente.add(f);
                        listaArchivos.addItem(f.getName());
                    }
                    listaArchivos.setEnabled(true);
                } // TODO agregar un popup si no se encontraron archivos .java

            }
        });
        btnSeleccionarCarpeta.setBounds(10, 25, 139, 20);
        frmHerramientaDeTesting.getContentPane().add(btnSeleccionarCarpeta);

    }
}
