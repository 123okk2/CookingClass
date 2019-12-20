package CookingClass.Server;

import CookingClass.*;
//import CookingClassServer.*;
//import CookingClassServer.ServerMain.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.lang.*;
import java.net.*;
import java.util.concurrent.Executor;
//import java.util.concurrent.Executor.Service;

public class ManagerServer implements Runnable {
	Scanner sc = new Scanner(System.in);
	ServerSocket serversocket=null;
	int num = 0;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	/*public ManagerServer(Socket socket, ObjectInputStream oiss, ObjectOutputStream ooss) {
		this.socket = socket;
		this.ois=oiss;
		this.oos=ooss;
		
	}*/
	public ManagerServer() {}
	public ManagerServer(Socket socket) { this.socket = socket; }

	public void run() {
		//ArrayList<Menu> menu;
		//ArrayList<MenuList> menulist;
		//ArrayList<Recipe> recipe;
		//ArrayList<Reserve> reserve;
		//ArrayList<Schedule> schedule;

		
		//ServerSocket serversocket=null;
		//Socket socket=null;
		OutputStream os = null;
		//ObjectOutputStream oos = null;
		InputStream is = null;
		//ObjectInputStream ois = null;
		//BufferedReader in = null;
		//BufferedWriter out = null;

		try {
			serversocket=new ServerSocket(5001);
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//os = socket.getOutputStream();
			//oos = new ObjectOutputStream(os);
			//is = socket.getInputStream();
			//ois = new ObjectInputStream(is);

			ArrayList<Menu> m = null;
			ArrayList<Reserve> re = null;
			ArrayList<Recipe> r = null;
			ArrayList<Schedule> s = null;
			ArrayList<MenuList> ml = null;

			while (true) {
				socket=serversocket.accept();
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
				is = socket.getInputStream();
				ois = new ObjectInputStream(is);
				String id = (String) ois.readObject(); // 숫자 받아옴
				System.out.println(id);
				Database b = new Database();
				switch (id) {
				case "R":
					re = b.getReserve();
					
					oos.writeObject(re);
					oos.flush();					

					ml = b.getMenuList();
					oos.writeObject(ml);
					oos.flush();

					r = b.getRecipe();
					oos.writeObject(r);
					oos.flush();

					id = (String) ois.readObject();
					if (id.equals("insert")) {
						// 레시피, 식단, 식재료 받아옴
						try {
							id = (String) ois.readObject();
							if(id.equals("ml")) {
								ml = (ArrayList<MenuList>) ois.readObject();
								b.addMenuList(ml);
							}
							else if(id.equals("rs")) {
								re = (ArrayList<Reserve>) ois.readObject();
								b.addReserve(re);
							}
							else if(id.equals("r")) {
								r = (ArrayList<Recipe>) ois.readObject();
								b.addRecipe(r);
							}
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (id.equals("modify")) {
						try {
							r = (ArrayList<Recipe>) ois.readObject();
							b.updateRecipe(r);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 레시피 받아옴
					} else if (id.equals("delete")) {
						try {
							r = (ArrayList<Recipe>) ois.readObject();
							b.removeRecipe(r);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					continue;
				case "S":
					s = b.getSchedule();
					oos.writeObject(s);
					oos.flush();

					id = (String) ois.readObject();
					if (id.equals("insert")) {    ///////////////////////////////////////////////
						// 레시피, 식단, 식재료 받아옴
						try {
							s = (ArrayList<Schedule>) ois.readObject();
							b.addSchedule(s);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (id.equals("modify")) {
						try {
							s = (ArrayList<Schedule>) ois.readObject();
							b.updateSchedule(s);
							// b.up
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// 레시피 받아옴
					} else if (id.equals("delete")) {
						try {
							s = (ArrayList<Schedule>) ois.readObject();
							b.removeSchedule(s);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					continue;
				case "l":
					m = b.getMenu();
					oos.writeObject(m);
					oos.flush();
					try {
						m = (ArrayList<Menu>) ois.readObject();
						b.addMenu(m);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 메뉴 받아옴
					continue;
				}
				oos.close(); ois.close(); os.close(); is.close();
				socket.close();

			}
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			/*try {
				if (socket != null)
					socket.close(); // 통신용 소켓 닫기
			} catch (IOException e) {
				System.out.println("클라이언트와 채팅 중 오류가 발생했습니다.");
			}*/
			try { serversocket.close(); }
			catch (IOException e) { System.err.println(e); }
		}
	}
}