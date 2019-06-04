import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JFrame.EXIT_ON_CLOSE; 
public class project extends Frame implements ActionListener{
    JButton a,b,c,d,e,f;
    String[] colums ={"ID","Name","Math","Phys","Chem","Aver"};
	String[][] Data ;
    Vector vData = new Vector();
    Vector vTitle = new Vector();
    DefaultTableModel model;
    JScrollPane tableresult;
    JTable tb = new JTable();
    public void load(){

        try{ 
            //vTitle.clear(); 	  
            //vData.clear();    
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
    public project(){
        this.setTitle("Student Management");
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        a = new JButton("Input Student");
        a.addActionListener(this);
        this.add(a);
        
        b = new JButton("List Student");
        b.addActionListener(this);
        this.add(b);
        
        c = new JButton ("Sort by average accending");
        c.addActionListener(this);
        this.add(c);
        
        d = new JButton ("Find student name");
        d.addActionListener(this);
        this.add(d);
        
        e = new JButton ("Student who have scholarship");
        e.addActionListener(this);
        this.add(e);
        
        f = new JButton ("Exit");
        f.addActionListener(this);
        this.add(f);

        this.setSize(300,300);
        this.show();
    }

    public static void main(String[] args) {
        Frame k =new Frame("Student Management");
        project st = new project();
        
    }
    public void actionPerformed (ActionEvent e){
        if (e.getActionCommand().equals("Input Student")){
            new inputinterface1();
        }
        else if (e.getSource()==b){
            load();
            new list();
        }
        else if (e.getSource()==f){
            System.exit(0);
        }
        }

    class list extends JFrame implements ActionListener{
        JButton exit;
        JPanel p = new JPanel(); 
        public list(){
        
        model = new DefaultTableModel(vData,vTitle);
        tb = new JTable(model);
        tableresult = new JScrollPane(tb);
        this.setSize(300,300);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exit = new JButton ("Exit");
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //exit.ActionListener(this);
        this.getContentPane().add(tableresult,"North");
        p.add(exit,"South");
        this.show();
        }
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("Exit")){
                this.dispose();                
            }
        }
    }
    class inputinterface1 extends Frame implements ActionListener{
        project st;
        Label lb1,lb2,lb3,lb4,lb5,lb6,lb7;
        TextField tx1,tx2,tx3,tx4,tx5;
        Button a,b;
        int id;
        float math,chem,phy,avg;
        String name;
        

        public inputinterface1(){
            this.setTitle("Input Student");
            this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
            
            lb1 = new Label("Id");
            tx1 = new TextField();
            this.add(lb1);
            this.add(tx1);
    
            lb2 = new Label("Name");
            tx2 = new TextField();
    
            this.add(lb2);
            this.add(tx2);
    
            lb3 = new Label ("Math");
            tx3 = new TextField();
    
            this.add(lb3);
            this.add(tx3);
    
            lb4= new Label ("Chemistry");
            tx4 = new TextField();
    
            this.add(lb4);
            this.add(tx4);
    
            lb5 = new Label("Physic");
            tx5 = new TextField();
            this.add(lb5);
            this.add(tx5);

            a = new Button ("Submit");
            a.addActionListener(this);
            this.add(a);
    
            b = new Button ("Back");
            b.addActionListener(this);
            this.add(b);
    
            lb6 = new Label("");
            lb7 = new Label("");
            lb6.setVisible(false);
            lb7.setVisible(false);
            this.add(lb6);
            this.add(lb7);
            


            this.pack();
            this.setSize(300,300);
            this.show();
    
        }
        public void actionPerformed(ActionEvent e){
            if (e.getSource()==a){
                inputdb();
            }
            if (e.getSource()==b){
                this.dispose();
            }
        }
        public void inputdb(){
            if (tx1.getText().equals("")||tx2.getText().equals("")||tx3.getText().equals("")||tx4.getText().equals("")||tx5.getText().equals("")){
                lb6.setText("Error");
                lb7.setText("Empty value");
                lb6.setForeground(Color.RED);
                lb7.setForeground(Color.RED);
                this.setVisible(true);
                this.setVisible(true);
            }
            else {
                try{
                    int id = Integer.parseInt(tx1.getText());
                    String name = tx2.getText();
                    Float m = Float.parseFloat(tx3.getText());
                    Float c = Float.parseFloat(tx4.getText());
                    Float p = Float.parseFloat(tx5.getText());
                    insertlist(id,name,m,c,p,(m+c+p)/3);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        public void insertlist(int id,String name,float math,float Chemistry,float Physic,float avg){
            try {
                Vector row = new Vector();
                row.add(id);
                row.add(name);
                row.add(math);
                row.add(Chemistry);
                row.add(Physic);
                row.add(avg);
                vData.add(row);
                model.fireTableDataChanged();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
            }
    
    }
    
    }
