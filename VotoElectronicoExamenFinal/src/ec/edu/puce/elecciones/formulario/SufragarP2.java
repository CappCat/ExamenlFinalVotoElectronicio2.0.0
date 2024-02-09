package ec.edu.puce.elecciones.formulario;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import ec.edu.puce.elecciones.dominio.Candidato;
import ec.edu.puce.elecciones.dominio.Estudiante;

import java.awt.Color;


public class SufragarP2 extends JInternalFrame {
    
    private static final long serialVersionUID = 1L;
    private Estudiante estudiante;
    private List<Candidato> candidatos;
    
    public SufragarP2(Estudiante estudiante,  List<Candidato> candidatos) {

    	setClosable(true);
        this.estudiante = estudiante;
        this.candidatos = candidatos;
        
        setTitle("BIENVENIDO");
        setBounds(100, 100, 626, 389);
        getContentPane().setLayout(null);
        
        JLabel lblHolaEstudiante = new JLabel("Hola "+estudiante.getNombre());
        lblHolaEstudiante.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblHolaEstudiante.setBounds(25, 11, 219, 30);
        getContentPane().add(lblHolaEstudiante);
        
        JLabel lblMesa = new JLabel("Tu mesa es la "+estudiante.getCurso().getMesa().getNombre());
        lblMesa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMesa.setBounds(381, 11, 219, 30);
        getContentPane().add(lblMesa);
        
        JLabel lblEscoga = new JLabel("Escoge tu candidato.");
        lblEscoga.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEscoga.setBounds(25, 39, 219, 30);
        getContentPane().add(lblEscoga);
        
        int y = 80;
        if (!this.estudiante.isHaVotado()) {
        	for (Candidato candidato : candidatos) {
             
                JButton btnCandidato = new JButton(candidato.getNombre());
                btnCandidato.setBounds(25, y, 200, 30);
                getContentPane().add(btnCandidato);
                y += 40;
                
                btnCandidato.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       
                        estudiante.setVoto(candidato);
                        estudiante.setHaVotado(true);

                        candidato.aumentarVotos();

                        
                        dispose();
                        JOptionPane.showMessageDialog(null, "Voto registrado para " + candidato.getNombre() +"\n"+"Gracias por su voto");
                        //sistemaDeVotacion.abrirSufragar3();
                    }
                });
                
               

            } 
        } else {
            JLabel lblYaVoto = new JLabel(""
            		+ "Usted ya ha votado");
            lblYaVoto.setBounds(25, y, 200, 30);
            getContentPane().add(lblYaVoto);
        }
    }
    
    
}




