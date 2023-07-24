package org.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

public class Cronometro {

	private JFrame frmCronometro;
	private JTextField textField;
	private JButton btnIniciar, btnResetar;
	private int horas, minutos, segundos, tempoPassado;
	private boolean rodando = false;
	Timer timer = new Timer();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cronometro window = new Cronometro();
					window.frmCronometro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cronometro() {
		initialize();
	}

	private void initialize() {
		frmCronometro = new JFrame();
		frmCronometro.setTitle("Cronometro");
		frmCronometro.setResizable(false);
		frmCronometro.getContentPane().setBackground(Color.WHITE);
		frmCronometro.setBounds(100, 100, 400, 200);
		frmCronometro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCronometro.getContentPane().setLayout(null);
		frmCronometro.setLocationRelativeTo(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 90));
		textField.setText("00:00:00");
		textField.setEditable(false);
		textField.setBounds(10, 11, 364, 83);
		frmCronometro.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setFont(new Font("Arial", Font.BOLD, 12));
		btnIniciar.setBounds(10, 112, 89, 37);
		btnIniciar.setLocation(90, 110);
		frmCronometro.getContentPane().add(btnIniciar);
		
		btnIniciar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!rodando) {
					timer = new Timer();
					btnIniciar.setText("Pausar");
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							tempoPassado += 1000;
							horas = tempoPassado / 3600000;
							minutos = (tempoPassado / 60000) % 60;
							segundos = (tempoPassado / 1000) % 60;
							String stringHoras = String.format("%02d", horas);
							String stringMinutos = String.format("%02d", minutos);
							String stringSegundos = String.format("%02d", segundos);
							textField.setText(stringHoras+":"+stringMinutos+":"+stringSegundos);		
						};
					}, 0, 1000);
					rodando = true;
				}else if(rodando) {
					timer.cancel();
					btnIniciar.setText("Iniciar");
					rodando = false;
				}
			} });
		
		btnResetar = new JButton("Resetar");
		btnResetar.setFont(new Font("Arial", Font.BOLD, 12));
		btnResetar.setBounds(285, 112, 89, 37);
		btnResetar.setLocation(200, 110);
		frmCronometro.getContentPane().add(btnResetar);
		
		btnResetar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!rodando) {
					timer.cancel();
					rodando = false;
					tempoPassado = 0;
					textField.setText("00:00:00");
				}else if(rodando) {
					btnIniciar.setText("Iniciar");
					timer.cancel();
					tempoPassado = 0;
					rodando = false;
					textField.setText("00:00:00");
				}
			} });
	}
}
