package Split_Project0827;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SplitList extends JFrame implements ActionListener{
	JTextField tfName, tfBankCount;
	JButton []button = new JButton[3];
	JComboBox<String> cbBank;

	JTable table;
	DefaultTableModel tableModel;
	JButton btnRefresh;

	SplitDBModel dbModel = new SplitDBModel();
	SplitPerson_DTO dto = new SplitPerson_DTO();
	Color background = new Color(255, 249, 225);
	Color blue = new Color(44, 102, 176);
	Color lightBlue = new Color(184, 208, 237);

	ArrayList<SplitPerson_DTO> list = new ArrayList<SplitPerson_DTO>();
	int row;

	public SplitList(){
		super("������");
		list = dbModel.getAllPerson();
		this.setBounds(460, 100, 650, 300);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(background);
		this.setDesign();
		this.setVisible(true);
	}

	private void setDesign() {
		// TODO Auto-generated method stub
		this.setLayout(null);

		//�̸� ��
		JLabel []label = new JLabel[3];
		String []lblText = {"�̸�", "����", "���¹�ȣ"};
		int ypos = 40;
		for (int i=0; i<lblText.length; i++) {
			label[i] = new JLabel(lblText[i]);
			label[i].setFont(new Font("a�帲���4", Font.PLAIN, 15));
			label[i].setForeground(blue);
			label[i].setBounds(40, ypos, 70, 30);
			ypos+= 45;
			this.add(label[i]);
		}

		//��ư
		String btnName[] = {"�߰�","����","����"};
		int xpos = 40;
		for(int i=0; i<btnName.length; i++) {
			button[i] = new JButton(btnName[i]);
			button[i].setBounds(xpos, 190, 65, 40);
			xpos+= 80;
			button[i].setFont(new Font("a�帲���6", Font.PLAIN, 14));
			button[i].setForeground(background);
			button[i].addActionListener(this);
			button[i].setBackground(blue);
			this.add(button[i]);
		}

		//�̸� �ؽ�Ʈ
		tfName = new JTextField();
		tfName.setBackground(lightBlue);
		tfName.setFont(new Font("a�帲���4",Font.PLAIN, 13));
		tfName.setBounds(115, 40, 100, 30);
		this.add(tfName);

		//���� �޺��ڽ�
		String bank[] = {"����","����","īī����ũ","����","�ϳ�","�츮","IBK���","SC����"};
		cbBank = new JComboBox<String>(bank);
		cbBank.setBackground(lightBlue);
		tfName.setFont(new Font("a�帲���4",Font.PLAIN, 13));
		cbBank.setBounds(115, 85, 100, 30);
		this.add(cbBank);

		//���� ��ȣ
		tfBankCount = new JTextField();
		tfBankCount.setBackground(lightBlue);
		tfBankCount.setFont(new Font("a�帲���4",Font.PLAIN, 13));
		tfBankCount.setBounds(115, 130, 140, 30);
		this.add(tfBankCount);

		String []title = {"�̸�","����","����"};
		tableModel = new DefaultTableModel(title, 0);
		table = new JTable(tableModel);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(310, 40, 300, 200);
		
		this.add(js);

		this.tableWrite();


		//���̺� Ŭ���� splitList�� �ش� ���� �� �ֱ�
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				row = table.getSelectedRow();

				tfName.setText(table.getValueAt(row, 0).toString());
				cbBank.setSelectedItem(table.getValueAt(row, 1));
				tfBankCount.setText(table.getValueAt(row, 2).toString());
			}
		});

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

	public void refresh() {
		list = dbModel.getAllPerson();

		tableWrite();
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SplitList ex = new SplitList();
//	}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object ob = e.getSource();

	//�߰���ư ������
	if(ob == button[0]) {
		dto.setName(tfName.getText());
		dto.setBank(cbBank.getSelectedItem().toString());
		if(Pattern.matches("^[0-9]+$",tfBankCount.getText())) {
			dto.setCount(tfBankCount.getText());
			SplitDBModel dbModel = new SplitDBModel();
			dbModel.personInsert(dto);

			JOptionPane.showConfirmDialog(tfBankCount, "DB�� "+ tfName.getText() +"���� �߰��Ǿ����ϴ�");

			tfName.setText("");
			cbBank.setSelectedIndex(0);
			tfBankCount.setText("");
			refresh();
		}
		else {
			JOptionPane.showMessageDialog(cbBank, "���ڸ� �Է����ּ���");
		}

		//���� ��ư ������
	} else if (ob == button[1]) {
		dto.setName(tfName.getText());
		dto.setBank(cbBank.getSelectedItem().toString());
		if(Pattern.matches("^[0-9]+$",tfBankCount.getText())) {
			dto.setCount(tfBankCount.getText());
			SplitDBModel dbModel = new SplitDBModel();
			dbModel.personUpdate(dto);

			JOptionPane.showConfirmDialog(tfBankCount, "DB�� "+ tfName.getText() +"���� ������ ����Ǿ����ϴ�");

			tfName.setText("");
			cbBank.setSelectedIndex(0);
			tfBankCount.setText("");
			refresh();
		}
		else {
			JOptionPane.showMessageDialog(cbBank, "���ڸ� �Է����ּ���");
		}
	} else if (ob == button[2]) {
		dto.setName(tfName.getText());
		dto.setBank(cbBank.getSelectedItem().toString());
		if(Pattern.matches("^[0-9]+$",tfBankCount.getText())) {
			dto.setCount(tfBankCount.getText());
			SplitDBModel dbModel = new SplitDBModel();
			dbModel.personDelte(dto);

			JOptionPane.showConfirmDialog(tfBankCount, "DB�� "+ tfName.getText() +"���� ������ �����Ǿ����ϴ�");

			tfName.setText("");
			cbBank.setSelectedIndex(0);
			tfBankCount.setText("");
			refresh();
		}
	}
}

}
