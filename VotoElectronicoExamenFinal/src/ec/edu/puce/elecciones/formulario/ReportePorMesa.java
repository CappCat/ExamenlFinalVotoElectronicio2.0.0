package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

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


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReportePorMesa extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Estudiante> estudiantes;
	private List<Candidato> candidatos;
	private List<Mesa> mesas;
	private List<Curso> cursos;
	private String[] mesasTexto;
	private JButton btnCancelar;
	private JLabel lblNombres;
	private JComboBox comboBox;
	private JLabel lblGanador;

	public ReportePorMesa(List<Estudiante> estudiantes, List<Candidato> candidatos, List<Mesa> mesas, List<Curso> cursos) {
		this.estudiantes = estudiantes;
		this.mesas = mesas;
		this.candidatos = candidatos;
		this.cursos = cursos;
		this.mesasTexto = new String[this.mesas.size()];
		int i = 0;
		for (Mesa mesa : this.mesas) {
			this.mesasTexto[i] = mesa.getNombre();
			i++;
		}
		
		setTitle("REPORTE GENERAL POR PROVINCIA");
		setBounds(100, 100, 600, 309);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 47, 566, 191);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Votos"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(235, 244, 117, 25);
		getContentPane().add(btnCancelar);
		
		lblNombres = new JLabel("Mesa");
		lblNombres.setBounds(12, 21, 70, 15);
		getContentPane().add(lblNombres);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla();
				calcularGanador();
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(this.mesasTexto));
		comboBox.setBounds(79, 12, 231, 24);
		getContentPane().add(comboBox);
		
		lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(385, 21, 193, 14);
		getContentPane().add(lblGanador);
		
		
		llenarTabla();
		if(candidatos != null) {
			calcularGanador();
		}
	}

	
	private void llenarTabla() {
		model.setRowCount(0);
		for (Candidato candidato : candidatos) {
			Object[] fila = new Object[2];
			fila[0] = candidato.getNombre();
			int votos = 0;
			for (Mesa mesa : mesas) {
				if (mesa.getNombre().equals(mesas.get(this.comboBox.getSelectedIndex()).getNombre())) {
					for (Curso curso : mesa.getCursos()) {
						for (Estudiante estudiante : estudiantes) {
							if (estudiante.getVoto().getNombre().equals(candidato.getNombre()) && estudiante.getCurso().equals(curso)) {
								votos++;
							}
						}
					}
					break;
				}
			}
			fila [1] = votos;
			model.addRow(fila);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}
	
	
	public void calcularGanador() {
		int mayor = 0;
		for(Candidato candidato : candidatos) {
			int votosLocales = 0;
			for (Mesa mesa : mesas) {
				if (mesa.getNombre().equals(mesas.get(this.comboBox.getSelectedIndex()).getNombre())) {
					for (Curso curso : mesa.getCursos()) {
						for (Estudiante estudiante : estudiantes) {
							if (estudiante.getVoto().getNombre().equals(candidato.getNombre()) && estudiante.getCurso().equals(curso)) {
								votosLocales++;
							}
						}
					
					}
					break;
				}
			}
			if(votosLocales>mayor ) {
				mayor = votosLocales;
				this.lblGanador.setText("Ganador: "+candidato.getNombre());
			}
		}
	}
}
