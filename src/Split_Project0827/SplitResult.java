package Split_Project0827;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SplitResult extends JFrame{
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	
	String suggestString;
	JLabel suggest;
	JButton btnSend;
	
	SplitDBModel model = new SplitDBModel();
	SplitCon_DTO cdto = new SplitCon_DTO();
	
	ArrayList<SplitCon_DTO> conList = new ArrayList<SplitCon_DTO>();
	
	String imageName;
	String name;
	MyCan myCan = new MyCan();
	
	
	
	public SplitResult() {
		super("정산 현황");
		name = JOptionPane.showInputDialog(this, "조회하고자 하는 이름을 입력해주세요.");
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(800, 300, 380, 400);
		this.getContentPane().setBackground(background);
		this.setVisible(true);
		int money = getMoney();
//		conList = model.getNameList(name);
		this.setDesign(money);
	}
	
	private void setDesign(int money) {
		// TODO Auto-generated method stub
		//지출-소비 에 따른 라벨 출력
		if(money<0) {
			suggestString = name + "님, "+ Math.abs(money) +"원 송금해주세요";
			imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\산와머니.png";
		}else if(money>0) {
			suggestString = name + "님, "+money+"원 송금을 받아야 합니다.";
			imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\돈내놔.jpg";
		}else if(money ==0) {
			suggestString = name + "님, 정산이 완료되었습니다.";
			imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\칭찬.jpg";
		}
		//라벨
		suggest = new JLabel(suggestString);
		suggest.setHorizontalAlignment(JLabel.CENTER);
		suggest.setBounds(40, 30, 300, 30);
		suggest.setFont(new Font("a드림고딕4", Font.PLAIN, 16));
		suggest.setForeground(blue);
		this.add(suggest);
		
		myCan.setBounds(40, 60, 300, 210);
		this.add(myCan);
		
		myCan.repaint();
		
		btnSend = new JButton("송금하기");
		btnSend.setBounds(130, 300, 120, 40);
		btnSend.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnSend.setForeground(background);
		btnSend.setBackground(blue);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.add(btnSend);
	}

	class MyCan extends Canvas{
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			Image image = new ImageIcon(imageName).getImage();
			g.drawImage(image, 0, 0, 300, 210, this);
		}
	}
	
	public int getMoney() {
		//선택된 사람의 지출금액 출력
		int payPrice;
		if(model.getsumPriceN(name)==null) {
			payPrice=0;
		} else {
			payPrice = Integer.parseInt(model.getsumPriceN(name));
		}
		
		//선택된 사람의 소비금액 출력
		ArrayList<SplitCon_DTO> conListAll = model.getPriceN(name);
		ArrayList<SplitPerson_DTO> personList = model.getAllPerson();
		int usePrice = 0;
		int n = personList.size();
		for(int i = 0; i < conListAll.size(); i++) {
			for(int j = 0; j < personList.size(); j++) {
				if(conListAll.get(i).getConExcept().contains(personList.get(j).getName())) {
					n -= 1;
				}
			}
			usePrice += Integer.parseInt(conListAll.get(i).getConPrice())/n;
			n = personList.size();
		}
		
		//지출 - 소비
		int money = payPrice - usePrice;
		this.setDesign(money);
		
		return money;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitResult ex = new SplitResult();
	}

}
