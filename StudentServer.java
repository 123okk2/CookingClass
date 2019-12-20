package CookingClass.Server;

import CookingClass.*;
//import CookingClassServer.*;

import java.io.*;
import java.sql.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class StudentServer implements Runnable {
	Database DB = new Database();
      	Scanner sc = new Scanner(System.in);
      	int num = 0;
       	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ServerSocket serversocket=null;
       	public StudentServer() {  }
	public StudentServer(Socket socket) { this.socket = socket; }
       
       	public void run() {
          		

          		OutputStream os=null;
          		//ObjectOutputStream oos=null;
          		InputStream is=null;
          		//ObjectInputStream ois=null;
          		//BufferedReader in = null;
          		//BufferedWriter out = null;
          		
          		try 
          		{
			serversocket=new ServerSocket(5003);
			ArrayList <Menu> menu = null;
           			ArrayList <MenuList> menulist = null;
           			ArrayList <Recipe> recipe = null;
           			ArrayList <Reserve> reserve = null;
           			ArrayList <Schedule> schedule = null;
			ArrayList <Applications> application = null;
			ArrayList <ApplicationsList> applicationlist = null;
			//serversocket=new ServerSocket(5003);
			//socket=serversocket.accept();
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//os = socket.getOutputStream();
			//oos = new ObjectOutputStream(os);
			//is = socket.getInputStream();
			//ois = new ObjectInputStream(is);

                		// 디비에서 받아온 리스트
	          /*   	ArrayList<Menu> m= new ArrayList<Menu>();
	             	ArrayList<Reserve> re= new ArrayList<Reserve>();
	             	ArrayList<Recipe> r= new ArrayList<Recipe>();
	             	ArrayList <Schedule> s = new ArrayList<Schedule>();
	             	ArrayList <MenuList> ml = new ArrayList<MenuList>();
	             	ArrayList <Applications> a =new ArrayList<Applications>();
	             	ArrayList <ApplicationsList> al = new ArrayList<ApplicationsList>();*/
             
                		while(true) 
            		{
			
			socket=serversocket.accept();
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
             			String id = (String) ois.readObject(); //애매
             			switch(id) {
             			case "M":
	    		   		menu= (ArrayList <Menu>) DB.getMenu();
                  				oos.writeObject(menu);
                  				oos.flush();
                  				menulist=(ArrayList<MenuList>) DB.getMenuList();
	           	    			oos.writeObject(menulist);
	               			oos.flush();
                   				continue;
             			case "A": 
              				application=(ArrayList <Applications>) DB.getApplications();
               				oos.writeObject(application);
               				oos.flush();
               				applicationlist=(ArrayList <ApplicationsList>) DB.getApplicationsList();
               				oos.writeObject(applicationlist);
               				oos.flush();
             				menu=(ArrayList <Menu>) DB.getMenu();
               				oos.writeObject(menu);
               				oos.flush();
               				menulist=(ArrayList <MenuList>) DB.getMenuList();
               				oos.writeObject(menulist);
               				oos.flush();
                     

               

                  				try
                  				{
                  					Applications app = (Applications) ois.readObject();
                  					ApplicationsList applist = (ApplicationsList) ois.readObject();

                  					DB.addApplications(app);
                  					DB.addApplicationsList(applist);		//추가 

                  				} catch (ClassNotFoundException e)
                  				{
                     					// TODO Auto-generated catch block
                     					e.printStackTrace();
                  				}
               				continue;
             			case "C":
                 				menulist=(ArrayList <MenuList>) DB.getMenuList();
                  				oos.writeObject(menulist);
                  				oos.flush();
	                     
	               			application=(ArrayList <Applications>) DB.getApplications();
	               			oos.writeObject(application);
	               			oos.flush();
	              			applicationlist=(ArrayList <ApplicationsList>) DB.getApplicationsList();
	              			oos.writeObject(applicationlist);
                      				try 
                      				{
	                	   			
						int cancle =  (int) ois.readObject();
	           	            
	           				           	DB.removeApplicationsList(cancle);
                      				} catch (ClassNotFoundException e) 
                      				{
                         				// TODO Auto-generated catch block
                         				e.printStackTrace();
                      				}
                      				// 레시피 받아옴
                  				continue;
             			}
			oos.close(); ois.close(); os.close(); is.close();
			socket.close();
             
          			}
		}
		catch (IOException e) { System.err.println(e); }
		catch (SQLException e) { System.err.println(e); }
		catch(ClassNotFoundException e) { System.err.println(e); }
          		finally 
           		{
               		/*try 
               		{
                  			if(socket != null) socket.close(); // 통신용 소켓 닫기
               		} catch (IOException e) 
               		{
                  			System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
               		}*/
			try { serversocket.close(); }
			catch (IOException e) { System.err.println(e); }
            	}
    	}
}
