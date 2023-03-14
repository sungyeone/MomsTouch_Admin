package components.labels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import components.table.SupplyOrderListTable;
import frame.LoginPage;

public class ProfileImage_Label extends JLabel {

	public ProfileImage_Label(JFrame parent) {
		
		setOpaque(true);
		setBackground(new Color(240, 240, 240));
		setBounds(938, 0, 46, 50);
		
		BufferedImage bufferedImage;
		
		String image = "Images/user.png";
		
		try {
			bufferedImage = ImageIO.read(new File(image));
			Image scaledImage =
					bufferedImage.getScaledInstance(46, 50, Image.SCALE_DEFAULT);
			setIcon(new ImageIcon(scaledImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new LoginPage();
				parent.setVisible(false);
				SupplyOrderListTable.removeTableRow();
			}
		});
	}
}
