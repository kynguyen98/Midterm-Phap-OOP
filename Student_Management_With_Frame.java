import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.util.Vector;
import java.util.Enumeration;
//import javax.xml.soap.Text;
class inputinterface1 extends Frame implements ActionListener{
    Vector list = new Vector();
    Label lb1,lb2,lb3,lb4,lb5;
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
        ////int id = Integer.parseUnsignedInt(tx1.getText());
        
        lb2 = new Label("Name");
        tx2 = new TextField();
        tx2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                String temp = tx2.getText();
            }
        });
        this.add(lb2);
        this.add(tx2);

        lb3 = new Label ("Math");
        tx3 = new TextField();
        tx3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                String temp = tx3.getText();
                math = Float.parseFloat(temp);
            }
        });
        this.add(lb3);
        this.add(tx3);

        lb4= new Label ("Chemistry");
        tx4 = new TextField();
        tx4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                String temp = tx4.getText();
                chem = Float.parseFloat(temp);
            }
        });
        this.add(lb4);
        this.add(tx4);

        lb5 = new Label("Physic");
        tx5 = new TextField();
        tx5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                String temp = tx5.getText();
                phy = Float.parseFloat(temp);
            }
        });
        this.add(lb5);
        this.add(tx5);
        avg = (math+phy+chem)/3;
        a = new Button ("Submit");
        a.addActionListener(this);
        this.add(a);

        b = new Button ("Back");
        b.addActionListener(this);
        this.add(b);

        this.pack();
        this.setSize(300,300);
        this.show();
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==a){
            Student st = new Student(id,name,avg,math,chem,phy);
            list.add(st);
        }
        if (e.getSource()==b){
            this.dispose();
        }
    }

}
class list extends Frame{

}
public class Student_Management_With_Frame extends Frame implements ActionListener{
    Button a,b,c,d,e,f;
    Vector list = new Vector();
    public Student_Management_With_Frame(){
        this.setTitle("Student Management");
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        a = new Button("Input Student");
        a.addActionListener(this);
        this.add(a);
        
        b = new Button("List Student");
        b.addActionListener(this);
        this.add(b);
        
        c = new Button ("Sort by average accending");
        c.addActionListener(this);
        this.add(c);
        
        d = new Button ("Find student name");
        d.addActionListener(this);
        this.add(d);
        
        e = new Button ("Student who have scholarship");
        e.addActionListener(this);
        this.add(e);
        
        f = new Button ("Exit");
        f.addActionListener(this);
        this.add(f);

        this.setSize(300,300);
        this.show();
    }
    public static void main(String[] args) {
        Frame k =new Frame("Student Management");
        Student_Management_With_Frame st = new Student_Management_With_Frame();
        
    }
    public void actionPerformed (ActionEvent e){
        if (e.getSource()==a){
            new inputinterface1();
        }
        else if (e.getSource()==b){
            new list();
        }
        else if (e.getSource()==f){
            System.exit(0);
        }
        }
    }
    class Student implements Comparable
{
	private int id;
	private String name;
	private float avg;
	private float math;
	private float phy;
	private float chem;
	private int schor;
	public Student()
	{
		name = new String("");
		id = 0;
		avg=0;
		math=0;
		phy=0;
		chem=0;
	
	}
	public Student(int i, String n, float a,float b,float c,float d)
	{
		id = i;
		name = n;
		avg=a;
		math=b;
		phy=c;
		chem=d;


	}
	public String getName()
	{
		return name;
	}
	public int getId()
	{
		return id;
	}
	public float getAver()
	{
		return avg;
	}
	public float getchem(){
		return chem;
	}
	public float getmath(){
		return math;
	}
	public float getphy(){
		return phy;
	}
	public float getavg(){
		avg=(math+phy+chem)/3;
		return avg;
	}

	public int compareTo(Object other) 
	   {
	    Student otherRect = (Student)other;
	    return (int)(this.avg-otherRect.avg);
	   }
		}
