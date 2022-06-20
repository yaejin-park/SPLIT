package Split_Project0827;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class SplitConList extends JFrame implements ActionListener{
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	Color lightBlue = new Color(184, 208, 237);
	
	SplitDBModel model = new SplitDBModel();
	
	JLabel lblTot[] = new JLabel[2];
	String lblPrice= "";
	String lblPay = model.getsumPrice();
	JLabel lblTotPrice, lblTotPay;
	
	JLabel lbltot[] = new JLabel[2];
	JComboBox<String> cbName, cbType;
	JButton btnAll;
	
	JTable table;
	DefaultTableModel tableModel;
	MyCan myCan = new MyCan();
	
	ArrayList<SplitCon_DTO> conList = new ArrayList<SplitCon_DTO>();
	
	String imageName = "D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\영수증\\영수증.jpg";
	
	SplitCon_DTO cdto = new SplitCon_DTO();
	
	public SplitConList(){
		super("지출 내역");
		conList = model.getAllList();
		this.setBounds(510, 300, 960, 430);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(background);
		this.setDesign();
		this.setVisible(true);
	}
	
	
	private void setDesign() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		//라벨
		String lblName[] = {"이름","분류"};
		JLabel lbl[] = new JLabel[2];
		
		int ypos = 40;
		for(int i=0; i<lblName.length; i++) {
			lbl[i] = new JLabel(lblName[i]);
			lbl[i].setBounds(30, ypos, 60, 30);
			lbl[i].setFont(new Font("a드림고딕3", Font.PLAIN, 15));
			lbl[i].setForeground(blue);
			ypos += 45;
			this.add(lbl[i]);
		}
		
		btnAll = new JButton("<html>전체<br/>출력</html>");
		btnAll.setBounds(230, 40, 75, 75);
		btnAll.setFont(new Font("a드림고딕6", Font.PLAIN, 15));
		btnAll.setForeground(background);
		btnAll.setBackground(blue);
		btnAll.addActionListener(this);
		this.add(btnAll);
		
		//이름 콤보박스
		ArrayList<SplitPerson_DTO> list = new ArrayList<SplitPerson_DTO>();
		list = model.getAllPerson();
		String names[] = new String[list.size()];
		int i =0;
		for(SplitPerson_DTO dto : list) {
			names[i] = dto.getName();
			i++;
		}
		cbName = new JComboBox<String>(names);
		cbName.setBackground(lightBlue);
		cbName.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		cbName.setBounds(90, 40, 120, 30);
		cbName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conList = model.getNameList(cbName.getSelectedItem().toString());
				tableWrite();
				
				lbltot[0].setVisible(true);
				
				//선택된 사람의 지출금액 출력
				String lblPrice = model.getsumPriceN(cbName.getSelectedItem().toString());
				lblTotPrice.setText((lblPrice == null) ? "0 원" : lblPrice + " 원");
				
				//선택된 사람의 소비금액 출력
				ArrayList<SplitCon_DTO> conListAll = model.getPriceN(cbName.getSelectedItem().toString());
				ArrayList<SplitPerson_DTO> personList = model.getAllPerson();
				int sum = 0;
				int n = personList.size();
				for(int i = 0; i < conListAll.size(); i++) {
					for(int j = 0; j < personList.size(); j++) {
						if(conListAll.get(i).getConExcept().contains(personList.get(j).getName())) {
							n -= 1;
						}
					}
					sum += Integer.parseInt(conListAll.get(i).getConPrice())/n;
					n = personList.size();
				}
				lblTotPay.setText(sum+" 원");
			}	
		});
		this.add(cbName);
		
		//분류 콤보박스
		String types[] ={"식비","숙박비","술","교통비","기타"};
		cbType = new JComboBox<String>(types);
		cbType.setBackground(lightBlue);
		cbType.setFont(new Font("a드림고딕4",Font.PLAIN, 13));
		cbType.setBounds(90, 85, 120, 30);
		cbType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conList = model.getTypeList(cbType.getSelectedItem().toString());
				tableWrite();
				
				//선택된 타입의 지출금액 안출력
				lbltot[0].setVisible(false);
				lblTotPrice.setText("");
				
				//선택된 타입의 소비금액 출력
				String payTot = model.getsumPriceT(cbType.getSelectedItem().toString());
				lblTotPay.setText(payTot+" 원");
			}
		});
		this.add(cbType);
		
		//테이블
		String []title = {"이름","소비명","분류","금액","시간", "제외명단"};
		tableModel = new DefaultTableModel(title,0);
		table = new JTable(tableModel);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(30, 140, 700, 230);
		
		this.add(js);
		
		this.tableWrite();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String name = table.getValueAt(table.getSelectedRow(), 0).toString();
				String conName = table.getValueAt(table.getSelectedRow(), 1).toString();
				String conPrice = table.getValueAt(table.getSelectedRow(), 3).toString();
				
				imageName = model.getImageName(name, conName, conPrice);
				myCan.repaint();
			}
		});
		
		lbltot[0] = new JLabel("총 지출금액 : ");
		lbltot[0].setFont(new Font("a드림고딕3", Font.PLAIN, 15));
		lbltot[0].setForeground(blue);
		lbltot[0].setBounds(500, 40, 120, 30);
		lbltot[0].setVisible(false);
		this.add(lbltot[0]);
		
		lbltot[1] = new JLabel("총 소비금액 : ");
		lbltot[1].setFont(new Font("a드림고딕3", Font.PLAIN, 15));
		lbltot[1].setForeground(blue);
		lbltot[1].setBounds(500, 85, 120, 30);
		this.add(lbltot[1]);
				
				
		//총 지출금액라벨
		lblTotPrice = new JLabel("", SwingConstants.RIGHT);
		lblTotPrice.setBounds(570, 40, 150, 30);
		lblTotPrice.setFont(new Font("a드림고딕3", Font.PLAIN, 15));
		this.add(lblTotPrice);
		
		//총 소비금액라벨
		lblPay = model.getsumPrice();
		lblTotPay = new JLabel(lblPay+" 원", SwingConstants.RIGHT);
		lblTotPay.setBounds(570, 85, 150, 30);
		lblTotPay.setFont(new Font("a드림고딕3", Font.PLAIN, 15));
		this.add(lblTotPay);
		
		//캔버스
		myCan.setBounds(750, 140, 170, 230);
		this.add(myCan);
	}
	
	//테이블 데이터 입력
	public void tableWrite() {
		tableModel.setRowCount(0);
		
		for(SplitCon_DTO cdto : conList) {
			Vector<String> data = new Vector<String>();
			
			data.add(cdto.getName());
			data.add(cdto.getConName());
			data.add(cdto.getConType());
			data.add(cdto.getConPrice());
			data.add(cdto.getConTime());
			data.add(cdto.getConExcept());
			
			tableModel.addRow(data);
		}
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
//		new SplitConList();
//	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ob = e.getSource();
		if(ob==btnAll) {
			conList = model.getAllList();
			SplitConList.this.tableWrite();
			
			//선택된 타입의 지출금액 안출력
			lbltot[0].setVisible(false);
			lblTotPrice.setText("");
			
			//소비금액
			lblPay = model.getsumPrice();
			lblTotPay.setText(lblPay+" 원");
		}
	}

}
