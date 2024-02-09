package ec.edu.puce.elecciones.formulario;
import javax.swing.*;

import ec.edu.puce.elecciones.dominio.Candidato;
import ec.edu.puce.elecciones.dominio.Estudiante;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Sufragar extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldCedula;
    private List<Estudiante> estudiantes;
    private List<Candidato> candidatos;
    private JTextField txtContra;

    public Sufragar(List<Estudiante> estudiantes, List<Candidato> candidatos) {
    	
    	setClosable(true);
        this.estudiantes = estudiantes;
        this.candidatos = candidatos;

        setTitle("INGRESE CEDULA");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblIngreseCedula = new JLabel("Estudiante, ingrese su cédula");
        lblIngreseCedula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIngreseCedula.setBounds(111, 36, 240, 22);
        getContentPane().add(lblIngreseCedula);

        textFieldCedula = new JTextField();
        textFieldCedula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldCedula.setBounds(93, 61, 258, 36);
        getContentPane().add(textFieldCedula);
        textFieldCedula.setColumns(10);

        JButton btnENTRAR = new JButton("ENTRAR");
        btnENTRAR.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    ingresar();
        	}

        });
        btnENTRAR.setFont(new Font("Tahoma", Font.PLAIN, 19));
        btnENTRAR.setBounds(153, 210, 133, 23);
        getContentPane().add(btnENTRAR);
        
        txtContra = new JTextField();
        txtContra.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtContra.setColumns(10);
        txtContra.setBounds(93, 135, 258, 36);
        getContentPane().add(txtContra);
        
        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblContra.setBounds(170, 108, 240, 22);
        getContentPane().add(lblContra);
    }
    
    public void ingresar() {
    	String cedulaIngresada = textFieldCedula.getText();
    	String contraIngresada = txtContra.getText();
    	boolean encontrado = false;
	    Estudiante estudianteEncontrado = null;
	    for (Estudiante estudiante : estudiantes) {
	        if (estudiante.getCedula().equals(cedulaIngresada)) {
	        	encontrado = true;
	        	if (estudiante.getClavePersonal().equals(contraIngresada)) {
		            estudianteEncontrado = estudiante;
		            break;
		        } else {
		        	JOptionPane.showMessageDialog(null, "ERROR, LA CLAVE ES INCORRECTA", "Error", JOptionPane.ERROR_MESSAGE);
	
		        }
	            break;
	        }
	    }
	    if (estudianteEncontrado != null) {
	        abrirSufragar2(estudianteEncontrado);
	    }
	    if (!encontrado) {
	    	JOptionPane.showMessageDialog(null, "ERROR, CEDULA NO ENCONTRADA", "Error", JOptionPane.ERROR_MESSAGE);
	    }
    }

    public void abrirSufragar2(Estudiante estudianteEncontrado) {
    	Container menuPrincipal = this.getParent();
    	dispose();
    	SufragarP2 s2 = new SufragarP2(estudianteEncontrado, candidatos);
    	s2.setVisible(true);
    	menuPrincipal.add(s2);
    }
}
