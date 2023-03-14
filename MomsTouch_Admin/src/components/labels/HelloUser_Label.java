package components.labels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HelloUser_Label extends JLabel {

	public HelloUser_Label(String position) {
		setFont(new Font("굴림", Font.BOLD, 14));
		setHorizontalAlignment(SwingConstants.CENTER);
		setBounds(756, 10, 186, 36);
		setForeground(Color.white);
		setText(String.format("반갑습니다, %s님.", position));
	}
}
