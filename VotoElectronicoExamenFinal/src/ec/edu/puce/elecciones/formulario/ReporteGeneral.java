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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReporteGeneral extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Candidato> candidatos;
	private JButton btnCancelar;
	private JLabel lblGanador;

	public ReporteGeneral(List<Candidato> candidatos) {
		this.candidatos = candidatos;
		
		setTitle("REPORTE GENERAL DE LA UNIDAD EDUCATIVA");
		setBounds(100, 100, 600, 309);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 36, 566, 179);
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

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(234, 227, 117, 25);
		getContentPane().add(btnCancelar);
		
		lblGanador = new JLabel("Ganador: ");
		lblGanador.setBounds(184, 11, 193, 14);
		getContentPane().add(lblGanador);

		model = (DefaultTableModel) table.getModel();
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
			fila[1] = candidato.getVotos();	
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
			if(candidato.getVotos()>mayor ) {
				mayor = candidato.getVotos();
				this.lblGanador.setText("Ganador: "+candidato.getNombre());
			}
		}
	}
	
}
