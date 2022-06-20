package Split_Project0827;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SplitAdd extends JFrame implements ActionListener{
	JButton btnReceipt, btnExcept;
	JButton []btn;
	JTextField tfConsumName, tfPrice, tfTime;
	JComboBox<String> cbName, cbType, cbExcept;
	
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	Color lightBlue = new Color(184, 208, 237);
	
	SplitDBModel model = new SplitDBModel();
	SplitPerson_DTO dto = new SplitPerson_DTO();
	SplitCon_DTO cdto = new SplitCon_DTO();
	MyCan myCan = new MyCan();
	String []names;
	
	//제외한 명단
	ArrayList<String> exceptList = new ArrayList<String>();
	
	String imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\영수증\\영수증.jpg";
	
	public SplitAdd(){
		super("지출입력");
		this.setBounds(460, 100, 550, 430);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(background);
		this.setDesign();
		this.setVisible(true);
	}
	
	private void setDesign() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		//라벨
		JLabel[] lbl = new JLabel[5];
		
		String lblName[] = {"이름","지출명","지출 유형","금액","참여 명단"};
		int ypos = 40;
		
		for(int i =0; i<lblName.length; i++) {
			lbl[i] = new JLabel(lblName[i]);
			lbl[i].setFont(new Font("a드림고딕3", Font.PLAIN, 15));
			lbl[i].setForeground(blue);
			lbl[i].setBounds(40, ypos, 70, 30);
			ypos += 50;
			this.add(lbl[i]);
		}
		
		//버튼
		String btnName[] = {"입력","수정"};
		btn = new JButton[2];
		int xpos = 60;
		for(int i=0; i<btn.length; i++) {
			btn[i] = new JButton(btnName[i]);
			btn[i].setBounds(xpos, 310, 80, 40);
			xpos += 115;
			btn[i].setFont(new Font("a드림고딕6", Font.PLAIN, 15));
			btn[i].setForeground(background);
			btn[i].setBackground(blue);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
		//영수증 버튼
		btnReceipt = new JButton("영수증 추가");
		btnReceipt.setBounds(350, 310, 120, 40);
		btnReceipt.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnReceipt.setForeground(background);
		btnReceipt.setBackground(blue);
		btnReceipt.addActionListener(this);
		this.add(btnReceipt);
		

		names = nameSet();
		cbName = new JComboBox<String>(names);
		cbName.setBackground(lightBlue);
		cbName.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		cbName.setBounds(140, 40, 130, 30);
		this.add(cbName);
		
		//소비내역
		tfConsumName = new JTextField();
		tfConsumName.setBounds(140, 90, 130, 30);
		tfConsumName.setBackground(lightBlue);
		tfConsumName.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		this.add(tfConsumName);
		
		//소비유형 콤보박스
		String type[] = {"식비","숙박비","술","교통비","기타"};
		cbType = new JComboBox<String>(type);
		cbType.setBackground(lightBlue);
		cbType.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		cbType.setBounds(140, 140, 130, 30);
		this.add(cbType);
		
		//금액
		tfPrice = new JTextField();
		tfPrice.setBounds(140, 190, 130, 30);
		tfPrice.setBackground(lightBlue);
		tfPrice.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		this.add(tfPrice);
		
		//참여명단
		cbExcept = new JComboBox<String>(names);
		cbExcept.setBounds(140, 240, 100, 30);
		cbExcept.setBackground(lightBlue);
		cbExcept.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		this.add(cbExcept);
		
		//제외버튼
		String delName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\delete1.jpg";
		Image image = new ImageIcon(delName).getImage();
		Image newImg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		btnExcept = new JButton(new ImageIcon(newImg));
		btnExcept.setBounds(240, 240, 30, 30);
		btnExcept.addActionListener(this);
		btnExcept.setFont(new Font("a드림고딕4",Font.BOLD, 13));
		this.add(btnExcept);
		
		//캔버스(영수증)
		myCan.setBounds(320, 40, 170, 230);
		this.add(myCan);
	}
	
	public String[] nameSet() {
		ArrayList<SplitPerson_DTO> list = new ArrayList<SplitPerson_DTO>();
		list = model.getAllPerson();
		String names[] = new String[list.size()];
		int i =0;
		for(SplitPerson_DTO dto : list) {
			names[i] = dto.getName();
			i++;
		}
		return names;
	}
	
	class MyCan extends Canvas{
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			Image image = new ImageIcon(imageName).getImage();
			g.drawImage(image, 0, 0, 170, 230, this);
		}
	}

	

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SplitAdd ex = new SplitAdd();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		
		//추가버튼
		if(ob==btn[0]) {
			cdto.setName(cbName.getSelectedItem().toString());
			cdto.setConName(tfConsumName.getText());
			cdto.setConType(cbType.getSelectedItem().toString());
			cdto.setConPrice(tfPrice.getText());
			cdto.setConExcept(exceptList.toString());
			
			//영수증 사진이 첨부 되어 있지 않을 경우
			if(imageName == "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\영수증\\영수증.jpg"||imageName ==null) {
				JOptionPane.showMessageDialog(myCan, "영수증 사진을 첨부해주세요");
//				cdto.setImageName("");
			}else {
				cdto.setImageName(imageName);
				model.consumInsert(cdto);
				JOptionPane.showConfirmDialog(this, cbName.getSelectedItem().toString()+"님의 지출 추가 완료");
				
				SplitAdd.this.dispose();
				
//				//추가 후 초깃값으로 다시 세팅
//				cbName.setSelectedIndex(0);
//				tfConsumName.setText("");
//				cbType.setSelectedIndex(0);
//				tfPrice.setText("");
//				cbExcept.setSelectedIndex(0);
//				names = nameSet();
//				cbExcept = new JComboBox<String>(names);
			}
		}
		else if(ob==btn[1]){ 
			cdto.setName(cbName.getSelectedItem().toString());
			cdto.setConName(tfConsumName.getText());
			cdto.setConType(cbType.getSelectedItem().toString());
			cdto.setConPrice(tfPrice.getText());
			
			model.consumInsert(cdto);
			JOptionPane.showConfirmDialog(this, cbName.getSelectedItem().toString()+"님의 지출 수정 완료");
			
			SplitAdd.this.dispose();
//			cbName.setSelectedIndex(0);
//			tfConsumName.setText("");
//			cbType.setSelectedIndex(0);
//			tfPrice.setText("");
//			cbExcept.setSelectedIndex(0);
//			names = nameSet();
//			cbExcept = new JComboBox<String>(names);
		} else if (ob == btnExcept) {
			try {
				
				if(cbExcept.getItemCount() == 1) {
					JOptionPane.showMessageDialog(btnExcept, "1명 이상의 인원이 필요합니다");
				} else {
					exceptList.add(cbExcept.getSelectedItem().toString()+" ");
					cbExcept.removeItemAt(cbExcept.getSelectedIndex());
				}
				
			} catch(ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showConfirmDialog(this, "더이상 삭제할 목록이 없습니다");
			}
			
		} else if (ob == btnReceipt) {
			FileDialog dlg = new FileDialog(this, "영수증 첨부", FileDialog.LOAD);
			dlg.setDirectory("D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\영수증"); //왜? 무엇을 위해쓰는것?
			dlg.setVisible(true);
			
			//취소 누르면 메서드 종료
			if(dlg.getDirectory() == null) {
				return;
			}
			imageName = dlg.getDirectory() + dlg.getFile();
			
			myCan.repaint();
		}
	}

}
