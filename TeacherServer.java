package CookingClass.Server;

import CookingClass.*;
//import CookingClassServer.*;
//import CookingClassServer.Database;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
import java.sql.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TeacherServer implements Runnable {
	Database db=new Database();
	ServerSocket serversocket=null;
	int num = 0;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	public TeacherServer() {  }
	public TeacherServer(Socket socket) { this.socket = socket; }

	public void run() {
		//ServerSocket serversocket=null;
		//Socket socket=null;
		OutputStream os = null;
		//ObjectOutputStream oos = null;
		InputStream is = null;
		//ObjectInputStream ois = null;
		//BufferedReader in = null;
		//BufferedWriter out = null;
		try { 	
			serversocket=new ServerSocket(5002);

			ArrayList <Menu> menu = null;
			ArrayList <MenuList> menulist = null;
			ArrayList <Applications> application = null;
			ArrayList <ApplicationsList> applicationlist = null;
			ArrayList <Recipe> recipe = null;
			ArrayList <Reserve> reserve = null;

			String menus="";
			 while(true) {
				socket=serversocket.accept();
				//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
				is = socket.getInputStream();
				ois = new ObjectInputStream(is);
				menus=(String) ois.readObject();
				switch(menus) {
				case "M":
					menu=(ArrayList <Menu>) db.getMenu();
					oos.writeObject(menu);
					oos.flush();
					menulist=(ArrayList <MenuList>) db.getMenuList();
					oos.writeObject(menulist);
					oos.flush();
					continue;
				case "A":
					application=(ArrayList <Applications>) db.getApplications();
					oos.writeObject(application);
					oos.flush();
					applicationlist=(ArrayList <ApplicationsList>) db.getApplicationsList();
					oos.writeObject(applicationlist);
					oos.flush();
					menulist=(ArrayList <MenuList>) db.getMenuList();
					oos.writeObject(menulist);
					oos.flush();
					continue;
				case "R":
					application=(ArrayList <Applications>) db.getApplications();
					oos.writeObject(application);
					menulist=(ArrayList <MenuList>) db.getMenuList();
					oos.writeObject(menulist);
					oos.flush();
					recipe=(ArrayList <Recipe>) db.getRecipe();
					oos.writeObject(recipe);
					oos.flush();
					reserve=(ArrayList <Reserve>) db.getReserve();
					oos.writeObject(reserve);
					oos.flush();
					applicationlist=(ArrayList <ApplicationsList>) db.getApplicationsList();
					oos.writeObject(applicationlist);
					oos.flush();
					continue;
				case "E":
					application=(ArrayList <Applications>) db.getApplications();
					oos.writeObject(application);
					oos.flush();
					int[] num=(int[]) ois.readObject();
					db.depositApplications(num[0],num[1],num[2]);
					continue;

				}
				oos.close(); ois.close(); os.close(); is.close();
				socket.close();
			}// while(menus.equals("exit"));
			//oos.close(); ois.close(); os.close(); is.close();
			//socket.close();
		}
		catch (IOException e) { System.err.println(e); }
		catch (SQLException e) { System.err.println(e); }
		catch (ClassNotFoundException e) { System.err.println(e); }
		finally {
			try { serversocket.close(); }
			catch (IOException e) { System.err.println(e); }
		}
	}
}