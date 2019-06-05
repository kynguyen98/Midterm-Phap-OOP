import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
public class project extends JFrame implements ActionListener,MouseListener{
	private int selectedrow=0;
	private String[] colums ={"ID","Name","Math","Phys","Chem","Aver"};
	private String[][] Data ={};
	private Vector vData=new Vector();
    private Vector vTitle=new Vector();	
    private JScrollPane tableResult;
    private DefaultTableModel model;
	private JTable tb= new JTable(); 
	private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tb.getModel());
	private JButton input,edit,exit,sort,delete;
	private JPanel p1,p2;
	public project(String s){
		super(s);

		try{
			load();		

			model = new DefaultTableModel(vData,vTitle);
			JFrame f = new JFrame();
			p1 = new JPanel();
			p2 = new JPanel();
			p1.setBounds(0,80,150,100);
			p2.setBounds(550,80,150,100);
			//p2.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "List Student", TitledBorder.CENTER, TitledBorder.TOP));
			input = new JButton("Input");
			delete = new JButton("Delete");
			edit = new JButton("Edit");
			exit = new JButton ("Exit");
			exit.addActionListener(this);
			input.addActionListener(this);
			edit.addActionListener(this);
			delete.addActionListener(this);
			//this.setLayout(new BorderLayout());
			p1.add(input);
			p1.add(edit);
			p1.add(exit);
			p1.add(delete);
			p1.setBackground(Color.gray);
			p2.add(new JScrollPane(tb));
			f.add(p1);
			f.add(p2);
			tb = new JTable(model);
			f.setSize(550,500);
			f.setLayout(null);
			f.setVisible(true);



		}catch(Exception e)
		 {
			System.out.println(e.getMessage());
			}
	}
	public static void main(String[] args) {
		new project("Student management");
	}
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Exit"))
			System.exit(0);
		if (e.getActionCommand().equals("Input"))
			new form("Insert form",this,"","","0","0","0");
		if (e.getActionCommand().equals("Edit")){
			Vector st = (Vector)vData.elementAt(selectedrow);
			new form("Edit form",this,(String)st.elementAt(0),(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4));
		}
		if (e.getActionCommand().equals("Delete"))
			delete();
	}
	public void mouseClicked(MouseEvent e)
{
	selectedrow = tb.getSelectedRow();
}
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e)
	{}
	public void mousePressed(MouseEvent e)
	{}
	public void mouseReleased(MouseEvent e)
	{}
	public void load(){
		vTitle.clear(); 	  
		vData.clear();  
		int num_column = colums.length;
		for (int i=0; i<num_column;i++) 
		{	    	   
			vTitle.add(colums[i]);
		}
		for (int i=0; i<Data.length;i++) 
		{
			Vector row = new Vector(num_column);
			for (int j=0; j<num_column;j++)  
					  row.add(Data[i][j]);
		 vData.add(row);
		}
	}
	
	public void delete(){
		Vector st = (Vector)vData.elementAt(selectedrow);
		vData.remove(selectedrow);
		model.fireTableDataChanged();
	}

	public void insertList(String id, String name, float math, float phys, float chem, float aver){
		Vector row = new Vector();
		row.add(id);
		row.add(name);
		row.add(math+"");
		row.add(phys+"");
		row.add(chem+"");
		row.add(aver+"");
	 vData.add(row);
	 model.fireTableDataChanged();
	}


	public void editList(String id, String name, float math, float phys, float chem, float aver){
		Enumeration e = vData.elements();
		int i=0;
			    	
		while (e.hasMoreElements())
		{
			Vector st = (Vector)e.nextElement();
			if (st.elementAt(0).equals(id)) 
			{
				vData.remove(i);
				break;
			}
				i++;
		}
		Vector row = new Vector();
		row.add(id);
		row.add(name);
		row.add(math+"");
		row.add(phys+"");
		row.add(chem+"");
		row.add(aver+"");
	 vData.add(i,row);
	 model.fireTableDataChanged();
	}

	class form extends JFrame implements ActionListener{
		JLabel Idlb; 
		JTextField Id; 
		JLabel namelb; 
		JTextField name; 
		JLabel mathlb;
		JTextField math;
		JLabel physlb;
		JTextField phys;
		JLabel chemlb;
		JTextField chem;
		
		JLabel errorlb;
		JLabel errordetails;	
		
	
		JButton ok;
		JButton cancel;	
		
		project mfr;
		public form(String title,project proj,String id, String na, String m, String ph, String ch){
			super(title);
			mfr=proj;

			Container cont = this.getContentPane();
			cont.setLayout(new GridLayout(7,2));
		
			Idlb = new JLabel("Id");
			Id = new JTextField(id);
			cont.add(Idlb);
			cont.add(Id);	
			if (title.equals("Edit form")) Id.setEditable(false);
			
			namelb = new JLabel("Name");
			name = new JTextField(na);
			cont.add(namelb);
			cont.add(name);
			
			mathlb= new JLabel("Math");
			math= new JTextField(m);
			cont.add(mathlb);
			cont.add(math);
			
			physlb= new JLabel("Physics");
			phys= new JTextField(ph);
			cont.add(physlb);
			cont.add(phys);
			
			chemlb= new JLabel("Chemistry");
			chem= new JTextField(ch);	
			cont.add(chemlb);
			cont.add(chem);
		
			errorlb= new JLabel("");
			errordetails= new JLabel("");	
			errorlb.setVisible(false);
			errordetails.setVisible(false);
			cont.add(errorlb);
			cont.add(errordetails);
			
			JButton ok = new JButton("Ok");
			JButton cancel = new JButton("Cancel");	
			cont.add(ok);
			cont.add(cancel);	
			ok.addActionListener(this);
			cancel.addActionListener(this);
		
			this.setSize(230,200);
			this.setLocation(250, 100);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			  
		

		}
		
		public void actionPerformed(ActionEvent e){
			if (e.getActionCommand().equals("Ok")){
				insertDB();
			}
			else this.dispose();
		}


		public void insertDB(){
			if ( Id.getText().equals("")||name.getText().equals("")||math.getText().equals("")||phys.getText().equals("")||chem.getText().equals("")){
			errorlb.setText("Error");
			errordetails.setText("empty value");			
			errorlb.setForeground(Color.RED);
			errordetails.setForeground(Color.RED);
			errorlb.setVisible(true);
			errordetails.setVisible(true);
			}
			else {
				String id = Id.getText();		 
				String na = name.getText();
				float m =Float.parseFloat(math.getText());
				float ph =Float.parseFloat(phys.getText());
				float ch =Float.parseFloat(chem.getText());	
				if (this.getTitle().equals("Insert form"))
				mfr.insertList(id,na,m,ph,ch,(m+ph+ch)/3);
			else
			mfr.editList(id,na,m,ph,ch,(m+ph+ch)/3);
			}
		}

	}
}