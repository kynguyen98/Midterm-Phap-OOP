import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JFrame.EXIT_ON_CLOSE; 
public class project extends JFrame implements ActionListener
{
	/*tạo bảng để chứa thông tin truy vấn từ csdl*/
    Vector vData=new Vector();
    Vector vTitle=new Vector();	
    JScrollPane tableResult;
    DefaultTableModel model;
    JTable tb= new JTable(); 
    JPanel p1 ;
	JButton input, exit, list;
	

	
	/*Du lieu khoi tao	  */
	String[] colums ={"ID","Name","Math","Phys","Chem","Aver"};
	String[][] Data ;
	
	
	public  project(String s)
	{
		super(s);
  

JFrame f = new JFrame("Student Management");
p1 = new JPanel();
p1.setBounds(10,150,100,100);
p1.setBackground(Color.white);
input = new JButton("Input");
input.addActionListener(this);
list = new JButton("List");
list.addActionListener(this);
exit = new JButton("Exit");
exit.addActionListener(this);

p1.add(input);
p1.add(list);
p1.add(exit);
load();
f.add(p1);
f.setSize(400,300);
f.setLayout(null);    
f.setVisible(true);
f.getContentPane().setBackground(Color.gray);

	}
	public void load()
	{
		  try{
			
			  
		/*Xóa hết dữ liệu hiện có trong 2 vector*/
		 vTitle.clear(); 	  
	        vData.clear();  
	       

	       int num_column = colums.length;

	       /*Chuẩn bị dữ liệu để tạo bảng (JTable)
	       Tạo các tên cột cho bảng*/
	       for (int i=0; i<num_column;i++) 
	       {	    	   
	       	vTitle.add(colums[i]);
	       }
	       
	        /*Tạo dữ liệu các hàng cho bảng: 
	         * mỗi phần tử của Vector vData là một Vector */
	       for (int i=0; i<Data.length;i++) 
	       {
	       	Vector row = new Vector(num_column);
	       	for (int j=0; j<num_column;j++)  
	       	  		row.add(Data[i][j]);
	        vData.add(row);
	       }
	       

	       }catch(Exception e)
	        {
	       	System.out.println(e.getMessage());
	       }
	
	}

    /*Chen dữ liệu vào danh sách từ cửa sổ nhập và soạn thảo */
	public void insertList(String id, String name, float math, float phys, float chem, float aver)
	{
	    try{
	    	Vector row = new Vector();
	    	row.add(id);
	    	row.add(name);
	    	row.add(math+"");
	    	row.add(phys+"");
	    	row.add(chem+"");
	    	row.add(aver+"");
		/*them doi tuong vao vData*/
		 vData.add(row);
		 
	/*Cập nhật lại nội dung bảng hiển thị trên màn hình*/
		 model.fireTableDataChanged();
		
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
/*Cập nhật dữ liệu vào danh sách từ cửa sổ nhập và soạn thảo */
	public void editList(String id, String name, float math, float phys, float chem, float aver)
	{
	    try{
	    	
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
		/*Xóa nội dung hàng tương ứng trong vData*/
		 vData.add(i,row);
		 
		 /*Cập nhật lại nội dung bảng hiển thị trên màn hình*/
		 model.fireTableDataChanged();
		
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	/* Xóa dữ liệu khi người dùng chọn hàng và ấn nút Delete */


/*Xử lý khi người dùng ấn các nút trên cửa số  */
public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("Input"))
	{
		new UpdateForm("Insert form",this,"","","0","0","0");
    }	
    if (e.getActionCommand().equals("List")){
        new list();
    }
    if (e.getActionCommand().equals("Exit")){
        System.exit(0);
    }

}


	public static void main(String[] args) {
    	new project("Student Management");
    }
    class list extends JFrame implements ActionListener{
        JButton back;
        public list(){
    model = new DefaultTableModel(vData,vTitle);		  
    tb=new JTable(model); 
    
    tableResult = new JScrollPane(tb);
    back = new JButton("Back");
    back.addActionListener(this);
    this.add(back,"South");
    this.getContentPane().add(tableResult,"North");    
    this.setSize(400,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    }
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("Back")){
            this.dispose();
        }
    }
    }
    class UpdateForm extends JFrame implements ActionListener
{
   //các thành phần trên giao diện
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



public UpdateForm(String title,  project mf, String id, String na, String m, String ph, String ch)
{
	super(title);
    mfr =mf;
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

public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("Ok"))
	{
        //insertDB();
        	    String id = Id.getText();		 
        String na = name.getText();
		float m =Float.parseFloat(math.getText());
		float ph =Float.parseFloat(phys.getText());
		float ch =Float.parseFloat(chem.getText());	
        mfr.insertList(id,na,m,ph,ch,(m+ph+ch)/3);
    }
	else this.dispose();
	
	
}
public void insertDB()
{
if ( Id.getText().equals("")||name.getText().equals("")||math.getText().equals("")||phys.getText().equals("")||chem.getText().equals(""))
	{
		/*Tạo nội dung lỗi */
		errorlb.setText("Error");
		errordetails.setText("empty value");			
		errorlb.setForeground(Color.RED);
		errordetails.setForeground(Color.RED);
		
		/*Hiển thị lỗi*/
		errorlb.setVisible(true);
		errordetails.setVisible(true);
	}
	else
	{
		
	 try{
		//Lấy nội dung đã nhập ở giao diện
	    String id = Id.getText();		 
        String na = name.getText();
		float m =Float.parseFloat(math.getText());
		float ph =Float.parseFloat(phys.getText());
		float ch =Float.parseFloat(chem.getText());	
		
		//cập nhật giao diện cửa sổ chính	
		if (this.getTitle().equals("Insert form"))
			mfr.insertList(id,na,m,ph,ch,(m+ph+ch)/3);
		else
        mfr.editList(id,na,m,ph,ch,(m+ph+ch)/3);
	
		

	}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
}

}





////////////////////////////////////////////////////////////////////////////////////////////////////////////



