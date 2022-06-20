package Split_Project0827;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.management.ListenerNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SplitPerson extends JFrame {
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	Color lightBlue = new Color(184, 208, 237);

	JTable table;
	DefaultTableModel tableModel;
	JButton btnRefresh;
	
	SplitDBModel dbModel = new SplitDBModel();
	ArrayList<SplitPerson_DTO> list = new ArrayList<SplitPerson_DTO>();
	
	int row;
	
	public SplitPerson(){
		super("정산명단");
		list = dbModel.getAllPerson();
		
		this.setBounds(600, 100, 500, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setDesign();
		this.getContentPane().setBackground(background);
		this.setVisible(true);
	}
	
	private void setDesign() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		String []title = {"이름","은행","계좌"};
		tableModel = new DefaultTableModel(title, 0);
		table = new JTable(tableModel);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(0, 100, 500, 300);
		this.add(js);
		
		this.tableWrite();
		
		//테이블 클릭시 splitList에 해당 행의 값 넣기
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				row = table.getSelectedRow();
				SplitList listForm = new SplitList();
				
				listForm.tfName.setText(table.getValueAt(row, 0).toString());
				listForm.cbBank.setSelectedItem(table.getValueAt(row, 1));
				listForm.tfBankCount.setText(table.getValueAt(row, 2).toString());
			}
		});
		
		//새로고침 버튼
		Image refresh = new ImageIcon("D:\\bitjava0719\\프로젝트\\개인프로젝트_0827\\image\\새로고침.jpg").getImage();
		Image newRefresh = refresh.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		btnRefresh = new JButton(new ImageIcon(newRefresh));
		btnRefresh.setBounds(400,35, 40, 40);
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list = dbModel.getAllPerson();
				
				tableWrite();
			}
		});
		this.add(btnRefresh);
	}
	

	public void tableWrite() {
		tableModel.setRowCount(0);
		
		for(SplitPerson_DTO dto : list) {
			Vector<String> data =  new Vector<String>();
			
			data.add(dto.getName());
			data.add(dto.getBank());
			data.add(dto.getCount());
			
			tableModel.addRow(data);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitPerson ex = new SplitPerson();
	}

}
