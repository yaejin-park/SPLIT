package Split_Project0827;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Split_Main extends JFrame implements ActionListener{
	JButton btnPerson, btnList, btnAdd, btnResult, btnName;
	JLabel lblName;
	String splitName = "정산 이름 입력";
	Image image;
	MyCan mycan;
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	
	
	public Split_Main() {
		// TODO Auto-generated constructor stub
		super("N/1로!");
		this.setBounds(100, 100, 350, 500);
		this.setDesign();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(background);
		this.setVisible(true);
	}
	
	public void setDesign() {
		//캔버스
		this.setLayout(null);
		mycan = new MyCan();
		mycan.setBounds(18, 30, 300, 200);
		this.add(mycan);
		//이미지
		String imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\산와머니.jpg";
		image = new ImageIcon(imageName).getImage();
		
		//여행이름
		lblName = new JLabel(splitName);
		lblName.setHorizontalAlignment(JLabel.CENTER);
		lblName.setBounds(50, 255, 200, 30);
		lblName.setFont(new Font("a드림고딕3", Font.PLAIN, 15));
		lblName.setForeground(blue);
		this.add(lblName);
		
		//여행이름 변경 버튼
		String checkBox = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\체크박스.png";
		Image Img = new ImageIcon(checkBox).getImage();
		Image newImg = Img.getScaledInstance(23, 23, Image.SCALE_SMOOTH);
		btnName = new JButton(new ImageIcon(newImg));
		btnName.setBounds(250, 255, 23, 23);
		btnName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				splitName = JOptionPane.showInputDialog(lblName,"정산 이름","정산 이름을 입력해주세요");
				lblName.setText(splitName);
			}
		});
		this.add(btnName);
		
		//정산명단 버튼
		btnPerson = new JButton("정산명단");
		btnPerson.setBounds(40, 320, 110, 40);
		btnPerson.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnPerson.setForeground(background);
		btnPerson.setBackground(blue);
		btnPerson.addActionListener(this);
		this.add(btnPerson);
		
		//지출내역 버튼
		btnList = new JButton("지출내역");
		btnList.setBounds(180, 320, 110, 40);
		btnList.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnList.setForeground(background);
		btnList.setBackground(new Color(51, 100, 167));
		btnList.addActionListener(this);
		this.add(btnList);
		
		//지출입력 버튼
		btnAdd = new JButton("지출입력");
		btnAdd.setBounds(40, 370, 110, 40);
		btnAdd.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnAdd.setForeground(background);
		btnAdd.setBackground(blue);
		btnAdd.addActionListener(this);
		this.add(btnAdd);
		
		//정산현황 버튼
		btnResult = new JButton("정산현황");
		btnResult.setBounds(180, 370, 110, 40);
		btnResult.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnResult.setForeground(background);
		btnResult.setBackground(blue);
		btnResult.addActionListener(this);
		this.add(btnResult);
	}
	

	
	public class MyCan extends Canvas{
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(image, 0, 0, 300, 200, this);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Split_Main ex = new Split_Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if (ob == btnPerson) {
			new SplitList();
		} else if(ob == btnList) {
			new SplitConList();
		} else if(ob == btnAdd) {
			new SplitAdd();
		} else if(ob == btnResult) {
			new SplitResult();
		} 
		
		
	}

}
