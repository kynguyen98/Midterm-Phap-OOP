import java.util.Scanner;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Arrays;

public class StudentManagement 
{
 
 Vector list = new Vector();
 
 public StudentManagement()
 {
  while(true)
  {
           
	  System.out.println("*-CHUONG TRINH QUAN LY SINH VIEN-*");
	  System.out.println("*-Chuc nang chinh chuong trinh-*");
	  System.out.println("  1. Nhap danh sach sinh vien   ");
	  System.out.println("  2. Xem danh sach sinh vien   ");
	  System.out.println("  3. Sap xep sach sinh vien tang dan diem trung binh   ");
		System.out.println("  4. Tim sinh vien theo ten   ");
		System.out.println("  5. Tim sinh vien co hoc bong");
	  System.out.println("  6. Thoat   ");
	  System.out.println("  -------------");

	  
	  int num;
	  Scanner keyboard = new Scanner(System.in);
	  System.out.print("  Nhap mot so de chon chuc nang: ");
	  num = keyboard.nextInt();
	  
	  switch(num)
	  {
	  case 1:
		  this.input();
		  break;
	  case 2:
		  this.view();
		  break;
	  case 3:
		  sort();
		  break;
	  case 4:
		  search();
		  break;
		case 5:
			review();
			break;
		case 6:
		System.out.print("----  Chuong trinh ket thuc----- ");
		return ;
	  }
	
  } 	
 }
 

   public void input()
   {
	int num;
	Scanner keyboard = new Scanner(System.in);
	System.out.print("  Nhap so luong sinh vien: ");
	num = keyboard.nextInt();

	for (int i=1;i<=num;i++)
	{
		System.out.println("  Nhap thong tin cho sinh vien thu: "+i); 
		System.out.print("  ID: ");
		int id = Integer.parseInt(keyboard.next());
		
		keyboard.nextLine();//xoa bo dem
		System.out.print("  Ten: ");
		String name = keyboard.nextLine();
		

		System.out.print("  Diem math: ");
		float math = keyboard.nextFloat();

		System.out.print("  Diem chem: ");
		float chem = keyboard.nextFloat();
		
		System.out.print("  Diem phy: ");
		float phy = keyboard.nextFloat();
		float aver=(phy+math+chem)/3;
		
		
		Student st = new Student(id,name,aver,math,chem,phy);
	    list.add(st);
    }
    System.out.println("\n--------\n");
   }
 
   public void view()
   {
	   
	   System.out.println("  Thong tin danh sach sinh vien"); 
	   Enumeration vEnum = list.elements();
	   int i=1;
	   while(vEnum.hasMoreElements()) 
		{
			Student sts = (Student)vEnum.nextElement();
			System.out.println("    "+i+". ID= "+sts.getId()+", Ten= "+sts.getName()+", Diem trung binh= "+sts.getavg()+", Diem math= "+sts.getmath()+", Diem phy= "+sts.getphy()+", Diem chem"+sts.getchem());
		    i++;
		}
	     System.out.println("\n--------\n");
   }
   

   public void sort()
   {

	   Student[] sts = new Student[list.size()];
	   int index=0;
 
	   Enumeration vEnum = list.elements();
	   while(vEnum.hasMoreElements()) 
		{
		 sts[index] = (Student)vEnum.nextElement();
		 index++;
		}

	   for (int i = 0; i<index-1; i++)
		   for (int j =i+1; j<index; j++)
		    if (sts[i].getAver()>sts[j].getAver())
		    {
		    	Student temp = sts[i];
		    	sts[i] = sts[j];
		    	sts[j] =temp;
		    }
	   
		//Arrays.sort(sts);
		
	     System.out.println("\n--Danh sach sinh vien sau khi sap xep--");
     for(index=0; index < sts.length; index++)
        {
    	   System.out.println("    "+(index+1)+". ID="+sts[index].getId()+", Ten="+sts[index].getName()+", Diem trung binh="+sts[index].getavg());
		  }
       System.out.println("\n--------\n");
       
		}
		//review sinhvien co hoc bong
		public void review(){
			Student[] sts = new Student[list.size()];
			int index=0;
			int sholar;
			Enumeration vEnum = list.elements();
			while(vEnum.hasMoreElements()) 
		 {
			sts[index] = (Student)vEnum.nextElement();
			index++;
		 }
	
			for (int i = 0; i<index-1; i++)
				for (int j =i+1; j<index; j++)
				 if (sts[i].getavg()>=8.5)
				 {
					System.out.println("    "+(i+1)+". ID="+sts[i].getId()+", Ten="+sts[i].getName()+", Diem trung binh="+sts[i].getavg()+"Co hoc bong");
				 }
		}	
   public void search()
   {
		Scanner keyboard = new Scanner(System.in);
		System.out.print(" Nhap ten sinh vien can tim: ");
		String name = keyboard.nextLine();
		
	   	Enumeration vEnum = list.elements();
	   	
	    System.out.println("\n--Thong tin tim kiem duoc--");
		while(vEnum.hasMoreElements()) 
		{
		Student sts = (Student)vEnum.nextElement();
		if (sts.getName().indexOf(name)!=-1)
		  System.out.println("ID="+sts.getId()+", Ten="+sts.getName()+", Diem trung binh="+sts.getavg());
		}
	     System.out.println("\n--------\n");	
		
	 }
	
 public static void main(String[] args) 
   {
    new StudentManagement();
   }
}

class Student implements Comparable
{
	private int id;
	private String name;
	private float aver;
	private float math;
	private float phy;
	private float chem;
	private int schor;
	public Student()
	{
		name = new String("");
		id = 0;
		aver=0;
		math=0;
		phy=0;
		chem=0;
	
	}
	public Student(int i, String n, float a,float b,float c,float d)
	{
		id = i;
		name = n;
		aver=a;
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
		return aver;
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
		aver=(math+phy+chem)/3;
		return aver;
	}

	public int compareTo(Object other) 
	   {
	    Student otherRect = (Student)other;
	    return (int)(this.aver-otherRect.aver);
	   }
		}
