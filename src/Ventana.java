import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.util.List;

public class Ventana {

	private JFrame frmHerramientaDeTesting;
	private List<File> ListaCodigosFuente = new ArrayList<>();
	private List<String> ClasesArchivo;
	private List<String> MetodosArchivo;
	private File FuenteAct;
	private AnalisisDeMetodo analizador = new AnalisisDeMetodo();
	private FilenameFilter fuenteJava = new FilenameFilter() {

		@Override

		public boolean accept(File dir, String name) {

			return name.toLowerCase().endsWith(".java");
		}
	};

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
	 * Create the application.
	 */
	public Ventana() {
		initialize();
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
		JTextPane TextoFuente = new JTextPane();
		PanelFuente.add(TextoFuente);
		TextoFuente.setEditable(false);
		TextoFuente.setBounds(1, 11, 513, 438);
		// PanelFuente.add(TextoFuente);
		JScrollPane sp = new JScrollPane(TextoFuente);
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

		JLabel NroLineas = new JLabel("");
		NroLineas.setHorizontalAlignment(SwingConstants.CENTER);
		NroLineas.setFont(new Font("Tahoma", Font.BOLD, 15));
		NroLineas.setBounds(10, 43, 189, 25);
		PanelAnalisis.add(NroLineas);

		JLabel NroLineasComentadas = new JLabel("");
		NroLineasComentadas.setHorizontalAlignment(SwingConstants.CENTER);
		NroLineasComentadas.setFont(new Font("Tahoma", Font.BOLD, 15));
		NroLineasComentadas.setBounds(10, 100, 189, 25);
		PanelAnalisis.add(NroLineasComentadas);

		JLabel lblCantidadComentarios = new JLabel("Cantidad de l\u00EDneas comentadas:");
		lblCantidadComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadComentarios.setBounds(10, 79, 189, 20);
		PanelAnalisis.add(lblCantidadComentarios);

		JLabel PorcentajeComentarios = new JLabel("");
		PorcentajeComentarios.setHorizontalAlignment(SwingConstants.CENTER);
		PorcentajeComentarios.setFont(new Font("Tahoma", Font.BOLD, 15));
		PorcentajeComentarios.setBounds(10, 157, 189, 25);
		PanelAnalisis.add(PorcentajeComentarios);

		JLabel lblPorcentajeDeLneas = new JLabel("Porcentaje de l\u00EDneas comentadas:");
		lblPorcentajeDeLneas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeDeLneas.setBounds(10, 136, 189, 20);
		PanelAnalisis.add(lblPorcentajeDeLneas);

		JLabel NroComplejidad = new JLabel("");
		NroComplejidad.setHorizontalAlignment(SwingConstants.CENTER);
		NroComplejidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		NroComplejidad.setBounds(10, 214, 189, 25);
		PanelAnalisis.add(NroComplejidad);

		JLabel lblComplejidadCiclomtica = new JLabel("Complejidad Ciclom\u00E1tica:");
		lblComplejidadCiclomtica.setHorizontalAlignment(SwingConstants.CENTER);
		lblComplejidadCiclomtica.setBounds(10, 193, 189, 20);
		PanelAnalisis.add(lblComplejidadCiclomtica);

		JLabel lblArchivo = new JLabel("Archivo:");
		lblArchivo.setBounds(159, 11, 46, 14);
		frmHerramientaDeTesting.getContentPane().add(lblArchivo);

		JLabel lblClase = new JLabel("Clase:");
		lblClase.setBounds(304, 11, 46, 14);
		frmHerramientaDeTesting.getContentPane().add(lblClase);

		JLabel lblMtodo = new JLabel("M\u00E9todo:");
		lblMtodo.setBounds(449, 11, 46, 14);
		frmHerramientaDeTesting.getContentPane().add(lblMtodo);

		JComboBox<String> ListaMetodos = new JComboBox<String>();
		ListaMetodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ListaMetodos.getSelectedIndex() == -1)
					return;
				TextoFuente.setContentType("text/html");
				TextoFuente.setText("<html><pre><code>" + analizador.getMetodo(ListaMetodos.getSelectedIndex()).codigo
						+ "</code></pre></html>");
				NroLineas.setText(analizador.getMetodo(ListaMetodos.getSelectedIndex()).getLineas());
				NroLineasComentadas.setText(analizador.getMetodo(ListaMetodos.getSelectedIndex()).getComentarios());
				PorcentajeComentarios.setText(analizador.getMetodo(ListaMetodos.getSelectedIndex()).getPorcentaje());
				NroComplejidad.setText(analizador.getMetodo(ListaMetodos.getSelectedIndex()).getComplejidad());
				// TODO actualizar aqui los tags del analisis del metodo (cant lineas,
				// complejidad, etc.)

			}
		});
		ListaMetodos.setMaximumRowCount(8);
		ListaMetodos.setBounds(449, 25, 135, 20);
		ListaMetodos.setEnabled(false);
		ListaMetodos.setEditable(false);
		frmHerramientaDeTesting.getContentPane().add(ListaMetodos);

		JComboBox<String> ListaClases = new JComboBox<String>();
		ListaClases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ListaClases.getSelectedIndex() == -1)
					return;
				ListaMetodos.removeAllItems();
				MetodosArchivo = analizador.encontrarMetodos(ListaClases.getSelectedIndex());
				for (String string : MetodosArchivo) {
					ListaMetodos.addItem(string);
				}
				ListaMetodos.setEnabled(true);
			}
		});
		ListaClases.setMaximumRowCount(8);
		ListaClases.setEnabled(false);
		ListaClases.setEditable(false);
		ListaClases.setBounds(304, 25, 135, 20);
		frmHerramientaDeTesting.getContentPane().add(ListaClases);

		JComboBox<String> ListaArchivos = new JComboBox<String>();
		ListaArchivos.setMaximumRowCount(8);
		ListaArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ListaArchivos.getSelectedIndex() == -1)
					return;
				FuenteAct = ListaCodigosFuente.get(ListaArchivos.getSelectedIndex());
				ListaClases.removeAllItems();
				try {
					ClasesArchivo = analizador.encontrarClases(FuenteAct);
					for (String string : ClasesArchivo) {
						ListaClases.addItem(string);
					}
					ListaClases.setEnabled(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		ListaArchivos.setBounds(159, 25, 135, 20);
		ListaArchivos.setEnabled(false);
		ListaArchivos.setEditable(false);

		frmHerramientaDeTesting.getContentPane().add(ListaArchivos);

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
					ListaArchivos.removeAllItems();
					ListaCodigosFuente.clear();
					frmHerramientaDeTesting
							.setTitle("Herramienta de Testing - " + archivos.getSelectedFile().getPath());

					for (File f : archivos.getSelectedFile().listFiles(fuenteJava)) {
						ListaCodigosFuente.add(f);
						ListaArchivos.addItem(f.getName());
					}
					ListaArchivos.setEnabled(true);
				} // TODO agregar un popup si no se encontraron archivos .java

			}
		});
		btnSeleccionarCarpeta.setBounds(10, 25, 139, 20);
		frmHerramientaDeTesting.getContentPane().add(btnSeleccionarCarpeta);

	}
}
