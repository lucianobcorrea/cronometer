package org.example;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

	private JFrame frame;
	private JTextField textField;
	private JButton btnStart;
    private int hours, minutes, seconds, elapsedTime;
	private boolean running = false;
	Timer timer = new Timer();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stopwatch window = new Stopwatch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stopwatch() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Stopwatch");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 90));
		textField.setText("00:00:00");
		textField.setEditable(false);
		textField.setBounds(10, 11, 364, 83);
		textField.setColumns(10);

		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Arial", Font.BOLD, 12));
		btnStart.setBounds(90, 110, 89, 37);

		Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png"))).getImage();

		frame.setIconImage(icon);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(btnStart);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					timer = new Timer();
					btnStart.setText("Pause");
					btnStart.setBackground(new Color(172, 17, 28));
					btnStart.setForeground(new Color(255,255,255));
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							elapsedTime += 1000;
							hours = elapsedTime / 3600000;
							minutes = (elapsedTime / 60000) % 60;
							seconds = (elapsedTime / 1000) % 60;
							String stringHours = String.format("%02d", hours);
							String stringMinutes = String.format("%02d", minutes);
							String stringSeconds = String.format("%02d", seconds);
							textField.setText(stringHours + ":" + stringMinutes + ":" + stringSeconds);
						}
					}, 0, 1000);
					running = true;
				} else {
					btnStart.setBackground(new JButton().getBackground());
					btnStart.setForeground(new JButton().getForeground());
					timer.cancel();
					btnStart.setText("Start");
					running = false;
				}
			}
		});

        JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Arial", Font.BOLD, 12));
		btnReset.setBounds(200, 110, 89, 37);
		frame.getContentPane().add(btnReset);

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.cancel();
				running = false;
				elapsedTime = 0;
				textField.setText("00:00:00");
				btnStart.setText("Start");
			}
		});
	}
}
