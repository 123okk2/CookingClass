package CookingClass.Client;
import CookingClass.*;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner; 

public class StudentMain {
   
   	private ArrayList <MenuList> menuList=new ArrayList<MenuList>(); 
   	private ArrayList <Reserve> reserves=new ArrayList<Reserve>();
   	private ArrayList <Recipe> recipes=new ArrayList<Recipe> ();
   	private ArrayList <Menu> menu=new ArrayList<Menu> ();
   	private ArrayList <Applications> application=new ArrayList<Applications> ();
   	private ArrayList <ApplicationsList> applicationList=new ArrayList<ApplicationsList> ();
   
      
      	Scanner sc = new Scanner(System.in);
      
      	public int SelectMenu()//메뉴선택
      	{
         		System.out.println("1.메뉴 확인\n 2.식재료 신청\n 3.식재료 취소\n");
         		int sel = sc.nextInt();
         
         		return sel;
      	}
      
      
      
      
      	//1번
      	public void InsertMenu(ArrayList <Menu> menus) 
      	{
        		this.menu=menus;
      	}
      
      	public void InsertMenuList(ArrayList <MenuList> menulists) 
      	{ 
         		this.menuList=menulists; 
         
      	}
      
     	public void Show_Menu() // 식단조회
      	{
         		System.out.print("일자:");
         		int date = sc.nextInt();

		int check = 0;
         
         		System.out.println("수업일"+"\t\t"+"강의장소"+"\t"+"식단명1"+"\t\t"+"가격"+"\t\t"+"식단명2"+"\t\t"+"가격"+"\t\t"+"식단명3"+"\t\t"+"가격");
         
         		for (int i = 0; i < menu.size();i++ ){
			if(menu.get(i).getDate() == date){
				if(check == 0) {
					System.out.print(menu.get(i).getDate()+"\t"+menu.get(i).getBranch()+"\t");
					check++;				
				}
               			for(int j = 0; j < menuList.size();j++){
                  				if(menuList.get(j).getMenuID().equals(menu.get(i).getMenuID())){
                     					System.out.print( menuList.get(j).getMenuName()+"\t"+menuList.get(j).getPrice()+"\t" );
						break;
                  				}
               			}
            		}
         		}
		System.out.println();
      	}
      
      //2번
      	public void InsertApplications(ArrayList <Applications> applications) 
      	{
         		this.application=applications; 
      	}
      	
      	public String MenuID(String menuName)
      	{
      		String menuID = "";

      		for (int i=0;i<menuList.size();i++){
      	         		if(menuList.get(i).getMenuName().equals(menuName)) {
      	         					menuID = menuList.get(i).getMenuID();
      	         					System.out.println(menuID);
      	         		}
      	    }
      		
      		System.out.println(menuID);

      		return menuID;
      	}
      
      	public void InsertApplicationsList(ArrayList <ApplicationsList> applicationlists) 
      	{
         		this.applicationList=applicationlists; 
      	}
      
      	public Applications Apply(String studentName,String phoneNum,int date,String branch) //신청테이블 반환
      	{
      	         		Applications tmp = new Applications(branch,date,studentName,phoneNum);
      	         
      	         		return tmp;
      	}
      
      	public ApplicationsList Apply_List(Applications app,String menuID,double amount,double total_Price)
      	{
      		int appNum = 0;

      		for(int i=0;i<application.size();i++){
      	            		if( application.get(i).getPhoneNum().equals(app.getPhoneNum()) && app.getDate() == application.get(i).getDate() && application.get(i).getBranch().equals(app.getBranch())){
      	               		for(int k=0;k<applicationList.size();k++){
      	                           		appNum = applicationList.get(k).getAppNum();
      	                        		}		
      	               	}
      	           	}

      		ApplicationsList tmp = new ApplicationsList(appNum,menuID,amount,total_Price);
      	         
      		return tmp;
      	}

      	public double TotalPrice(String menuID, double amount)
      	{
      		double totalPrice =0 ;
      		
      		for(int j=0;j<menuList.size();j++) {
      	            		if(menuID == menuList.get(j).getMenuID()) {
      	            			totalPrice = menuList.get(j).getPrice()*amount;
      	            		}
      	         	}

      		return totalPrice;
      	}
      
    	public void Show_Menu_Apply() // 신청- 메뉴 조회
      	{
         		System.out.println("수업일"+"\t"+"강의장소"+"\t"+"식단명1"+"\t"+"식단ID"+"\t"+"가격"+"\t"+"식단명2"+"\t"+"식단ID"+"\t"+"가격"+"\t"+"식단명3"+"식단ID"+"\t"+"가격");
         
         		for(int i=0;i<menu.size();i++) {
         			System.out.print(menu.get(i).getDate()+"\t"+menu.get(i).getBranch()+"\t");
         
         			for(int j = 0; j < menuList.size();j++){
            			if(menu.get(i).getMenuID() == menuList.get(j).getMenuID()){
               				System.out.print( menuList.get(j).getMenuName()+"\t"+menuList.get(j).getMenuID()+"\t"+menuList.get(j).getPrice()+"\t" );
            			}
         			}break;
       		}   
      	}
      
      	//3번
    	public int[] Cancle(String sName,String pNum,int d,String b,String mName,int cancelDay) // 취소

        {

              int app_Name = 0;

              for(int i=0;i<application.size();i++){      //신청 테이블 확인.

                 if(((application.get(i).getPhoneNum().equals(pNum)) && (d == application.get(i).getDate()) && (application.get(i).getBranch().equals(b)))){   //신청자 전화번호 및 날짜, 지점 비교.

                       for(int j=0;j<menuList.size();j++){   //식단 테이블 확인.

                             if(menuList.get(j).getMenuName().equals(mName)){   //메뉴명 비교.

                                   for(int k=0;k<applicationList.size();k++){   //신청자 내역 테이블 확인.

                                      if(menuList.get(j).getMenuID().equals(applicationList.get(k).getMenuID())){   //식단id 비교.

                                            app_Name = applicationList.get(i).getAppNum();   //신청번호 가져옴.

                                      }

                                   }

                             }

                       }

                   }

              }



     //Applications tmp = new Applications(app_Name, cancelDay);   //신청번호 및 취소일 반환.

     int[] tmp=new int[2];

     tmp[0]=app_Name;

     tmp[1]=cancelDay;

        

              return tmp;

       }
}
