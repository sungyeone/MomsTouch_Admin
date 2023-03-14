package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import dao.LoginDAO;
import frame.AdminPage;

public class LoginPage extends JFrame {

	JFrame parent = this;
	public LoginPage() {

		setTitle("Login");

		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0, 0, 1000, 800);
		ImageIcon background = new ImageIcon("Momstouch_ui/바탕.png");
		backgroundImage.setIcon(background);
		backgroundImage.setVisible(true);

		JLabel id_label = new JLabel("ID");
		id_label.setText("ID");
		id_label.setFont(new Font("굴림", Font.BOLD, 20));
		id_label.setBounds(175, 360, 57, 38);

		JLabel pw_label = new JLabel("PW");
		pw_label.setText("PW");
		pw_label.setFont(new Font("굴림", Font.BOLD, 20));
		pw_label.setBounds(175, 460, 105, 24);

		JButton login_btn = new JButton("로그인");
		login_btn.setBackground(new Color(70, 50, 41));
		login_btn.setBounds(455, 390, 113, 81);
		login_btn.setForeground(Color.white);
		login_btn.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Border border1 = BorderFactory.createLineBorder(new Color(243, 156, 18));
		login_btn.setBorder(border1);

		JTextField id_textField = new JTextField();
		id_textField.setBounds(250, 350, 168, 53);
		id_textField.setColumns(10);

		JPasswordField pw_textField = new JPasswordField();
		pw_textField.setBounds(250, 450, 168, 53);
		pw_textField.setColumns(10);

		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input_pw = "";
				for(int i = 0; i < pw_textField.getPassword().length; i++) {
					input_pw += String.valueOf(pw_textField.getPassword()[i]);
				}
				if(LoginDAO.getLoginAccess(id_textField.getText(), input_pw) == true) {
					new AdminPage(LoginDAO.getPosition(id_textField.getText()));
					parent.setVisible(false);
				}
			}
		});

		JLabel logo_image = new JLabel("");
		logo_image.setBounds(150, 65, 400, 200);

		try {
			BufferedImage bufferedImage = ImageIO.read(new File("Momstouch_ui/로고2.png"));
			Image scaledImage = bufferedImage.getScaledInstance(400, 200, Image.SCALE_DEFAULT);
			logo_image.setIcon(new ImageIcon(scaledImage));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		add(id_label);
		add(pw_label);
		add(id_textField);
		add(pw_textField);
		add(login_btn);
		add(logo_image);
		add(backgroundImage);

		getContentPane().setBackground(Color.white);
		setBounds(50, 50, 700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LoginPage();
	}

}
