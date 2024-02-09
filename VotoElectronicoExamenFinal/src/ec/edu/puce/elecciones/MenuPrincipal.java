package ec.edu.puce.elecciones;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import ec.edu.puce.elecciones.dominio.Candidato;

import ec.edu.puce.elecciones.dominio.Curso;
import ec.edu.puce.elecciones.dominio.Estudiante;
import ec.edu.puce.elecciones.dominio.Mesa;

import ec.edu.puce.elecciones.formulario.CrearCandidato;
import ec.edu.puce.elecciones.formulario.CrearCurso;
import ec.edu.puce.elecciones.formulario.CrearEstudiante;
import ec.edu.puce.elecciones.formulario.CrearMesa;
import ec.edu.puce.elecciones.formulario.ReporteGeneral;
import ec.edu.puce.elecciones.formulario.ReportePadronElectoral;
import ec.edu.puce.elecciones.formulario.ReportePorMesa;
import ec.edu.puce.elecciones.formulario.Sufragar;

import javax.swing.JMenuBar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

public class MenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contenedor;
	private JDesktopPane desktopPane;
	private JMenuItem mntmSalir;
	private JMenuItem mntmMesas;
	private JMenuItem mntmSufragar;

	public List<Candidato> candidatos = new ArrayList<>();
	public List<Mesa> mesas = new ArrayList<Mesa>();
	public List<Estudiante> estudiantes = new ArrayList<Estudiante>();
	public List<Curso> cursos = new ArrayList<Curso>();
	public String[] nombresMesas = {"Mesa #1", "Mesa #2", "Mesa #3"};
	public String[] cursosMesa1 = {"Primero A", "Segundo A","Tercero A","Cuarto A","Quinto A"};
	public String[] cursosMesa2 = {"Sexto A", "Séptimo A", "Octavo A", "Noveno A", "Décimo A"};
	public String[] cursosMesa3 = {"Noveno B","Décimo B","1 BGU A", "2 BGU A","3 BGU A"};
	public int idCandidato = 1;
	public int idEstudiantes = 1;
	private JMenuItem mntmResultadosBarras;
	private JMenuItem mntmResultadosPastel;
	private JMenuItem mntmPadronElectoral;
	private JMenuItem mntmCandidatos;
	private JMenuItem mntmResultadosGenerales;
	private JMenuItem mntmResultadosMesas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		
		
		for (String mesa: nombresMesas) {
			Mesa mesaE = new Mesa(mesa);
			mesas.add(mesaE);
			if (mesaE.getNombre() == "Mesa #1") {
				for (String curso : cursosMesa1) {
					Curso cursoE = new Curso(curso, mesaE);
					mesaE.getCursos().add(cursoE);
					cursos.add(cursoE);
				}
				continue;
			} else if (mesaE.getNombre() == "Mesa #2") {
				for (String curso : cursosMesa2) {
					Curso cursoE = new Curso(curso, mesaE);
					mesaE.getCursos().add(cursoE);
					cursos.add(cursoE);
				}
				continue;
			} else if (mesaE.getNombre() == "Mesa #3") {
				for (String curso : cursosMesa3) {
					Curso cursoE = new Curso(curso, mesaE);
					mesaE.getCursos().add(cursoE);
					cursos.add(cursoE);
				}
				continue;
			} 
		}
		setTitle("SISTEMA DE VOTO ELECTRÓNICO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 191, 188));
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnArchivo);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setFont(new Font("Dialog", Font.BOLD, 16));
		mnArchivo.add(mntmSalir);

		JMenu mnAdministracin = new JMenu("Administración");
		mnAdministracin.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnAdministracin);

		mntmMesas = new JMenuItem("Mesas");
		mntmMesas.setFont(new Font("Dialog", Font.BOLD, 16));
		mntmMesas.addActionListener(this);
		mnAdministracin.add(mntmMesas);
		
		JMenuItem mntmCursos = new JMenuItem("Cursos");
		mntmCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cursoVentana();
			}
		});
		mntmCursos.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmCursos);
		
		JMenuItem mntmEstudiantes = new JMenuItem("Estudiantes");
		mntmEstudiantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estudianteVentana();
			}
		});
		mntmEstudiantes.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmEstudiantes);
		
		mntmCandidatos = new JMenuItem("Candidatos");
		mntmCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				candidatoVentana();
			}
		});
		mntmCandidatos.setFont(new Font("Dialog", Font.BOLD, 16));
		mnAdministracin.add(mntmCandidatos);
				//mnReportes.add(mntmResultadosPorCantn);

		JMenu mnProceso = new JMenu("Proceso");
		mnProceso.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnProceso);
		
				mntmSufragar = new JMenuItem("Sufragar");
				mntmSufragar.setFont(new Font("Dialog", Font.BOLD, 16));
				mntmSufragar.addActionListener(this);
				mnProceso.add(mntmSufragar);
		
				JMenu mnReportes = new JMenu("Reportes");
				mnReportes.setFont(new Font("Dialog", Font.BOLD, 16));
				menuBar.add(mnReportes);
				
						mntmPadronElectoral = new JMenuItem("Consultar Padrón Electoral");
						mntmPadronElectoral.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ReportePadronElectoral rpe = new ReportePadronElectoral(mesas, cursos, estudiantes);
								desktopPane.add(rpe);
								rpe.setVisible(true);
							}
						});
						mntmPadronElectoral.setFont(new Font("Dialog", Font.BOLD, 16));
						mnReportes.add(mntmPadronElectoral);
						
								JMenuItem mntmResultadosPorCantn = new JMenuItem("Resultados por cantón o ciudad");
								mntmResultadosPorCantn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										//ReportePorCiudad rc = new ReportePorCiudad(prefectos, provincias);
										//desktopPane.add(rc);
										//rc.setVisible(true);
									}
								});
								mntmResultadosPorCantn.setFont(new Font("Dialog", Font.BOLD, 16));

		JMenu mnGrficos = new JMenu("Gráficos");
		mnGrficos.setFont(new Font("Dialog", Font.BOLD, 16));
		
		mntmResultadosGenerales = new JMenuItem("Resultados Generales");
		mntmResultadosGenerales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteGeneral rg = new ReporteGeneral(candidatos);
				desktopPane.add(rg);
				rg.setVisible(true);
			}
		});
		mntmResultadosGenerales.setFont(new Font("Dialog", Font.BOLD, 16));
		mnReportes.add(mntmResultadosGenerales);
		
		mntmResultadosMesas = new JMenuItem("Resultados por mesas");
		mntmResultadosMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePorMesa rm = new ReportePorMesa(estudiantes, candidatos, mesas, cursos);
				desktopPane.add(rm);
				rm.setVisible(true);
				
			}
		});
		mntmResultadosMesas.setFont(new Font("Dialog", Font.BOLD, 16));
		mnReportes.add(mntmResultadosMesas);
		//menuBar.add(mnGrficos);

		mntmResultadosBarras = new JMenuItem("Resultados en gráfico de Barras");
		mnReportes.add(mntmResultadosBarras);
		mntmResultadosBarras.addActionListener(this);
		mntmResultadosBarras.setFont(new Font("Dialog", Font.BOLD, 16));

		mntmResultadosPastel = new JMenuItem("Resultados Pastel");
		mnGrficos.add(mntmResultadosPastel);
		mntmResultadosPastel.addActionListener(this);
		mntmResultadosPastel.setFont(new Font("Dialog", Font.BOLD, 16));
		contenedor = new JPanel();
		contenedor.setBackground(new Color(255, 255, 255));
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenedor);
		contenedor.setLayout(new CardLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		contenedor.add(desktopPane, "name_35522358088801");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmSalir) {
			dispose();
		} else if (e.getSource() == mntmMesas) {
			CrearMesa crearMesa = new CrearMesa(mesas);
			desktopPane.add(crearMesa);
			crearMesa.setVisible(true);

		} else if (e.getSource() == mntmSufragar) {
			Sufragar sufragar = new Sufragar(estudiantes, candidatos);
			desktopPane.add(sufragar);
			sufragar.setVisible(true);
		}  else if (e.getSource() == mntmResultadosBarras) {
			crearResultadosEnBarras();
		} 

	}
	
	public void candidatoVentana() {
		CrearCandidato crearCandidato = new CrearCandidato(candidatos, idCandidato);
		desktopPane.add(crearCandidato);
		crearCandidato.setVisible(true);
	}
	
	public void estudianteVentana() {
		CrearEstudiante crearEstudiante = new CrearEstudiante(cursos, estudiantes, idEstudiantes);
		desktopPane.add(crearEstudiante);
		crearEstudiante.setVisible(true);
	}
	
	public void cursoVentana() {
		CrearCurso crearCurso = new CrearCurso(mesas, cursos);
		desktopPane.add(crearCurso);
		crearCurso.setVisible(true);
	}
	
	private void crearResultadosEnBarras() {
		final JInternalFrame frame = new JInternalFrame("Resultado en Barras", true);

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Candidato candidato : candidatos) {
			dataset.addValue(candidato.getVotos(), "Candidato", candidato.getNombre());
		}
		final JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "Category", "Series", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(600, 400));

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		panel.setLayout(new BorderLayout());
		panel.add(chartPanel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});

		panel.add(btnCancelar, BorderLayout.SOUTH);
		frame.getContentPane().add(panel);
		desktopPane.add(frame);
		frame.pack();
		frame.setVisible(true);
	}
}
