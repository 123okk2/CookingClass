package CookingClass.Client;

import CookingClass.*;
//import CookingClass.Manager.*;
//import CookingClass.Student.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class ProgramMain {

	final static int loadRecipe=1, loadSchedule=2, enrollMenu=3;
	final static int showMenu=1, showApplicants=2, showRequires=3, EnrollAccount=4;
	final static int applyRecipe=2, cancleRecipe=3;
	final static int Exit=5;

	public static void main(String[] args) {
		Socket socket=null;
		OutputStream os=null;
		ObjectOutputStream oos=null;
		InputStream is=null;
		ObjectInputStream ois=null;

		try {
			//소켓 연결
			
			socket=new Socket("192.168.208.148",5000);
			os=socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			is=socket.getInputStream();
			ois=new ObjectInputStream(is);

			//LogIn
			Scanner s=new Scanner(System.in);
			System.out.print("ID 입력 : ");
			String id=s.next();
			System.out.print("Password 입력 : ");
			String password=s.next();
			//LogInInfo li=new LogInInfo(id, passowrd);
			//oos.writeObject(li);
			oos.writeObject(id);
			oos.flush();
			oos.writeObject(password);
			oos.flush();
			String type=(String) ois.readObject();

			os.close(); is.close(); oos.close(); ois.close();
			socket.close();

			//필요한 것들 선언
			int Sel_Menu=1;
			ArrayList <Menu> menu = new ArrayList <Menu> ();
			ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
			ArrayList <Applications> applications = new ArrayList <Applications> ();
			ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();
			ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
			ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
			ArrayList <Schedule> schedule = new ArrayList <Schedule> ();

			String str="";


			//시작
			switch(type) {
			case "Manager":
				while(Sel_Menu!=Exit) {
					socket=new Socket("192.168.208.148",5001);
					os=socket.getOutputStream();
					oos=new ObjectOutputStream(os);
					is=socket.getInputStream();
					ois=new ObjectInputStream(is);
					Manager man=new Manager();
                				Sel_Menu=Manager.SelectMenu();
 					int num;
					switch(Sel_Menu) {
					case loadRecipe:
						str="R";
						oos.writeObject(str);
                  					oos.flush();
                  					// 레시피, 식단, 식재료 받아옴
                 					reserve = (ArrayList<Reserve>) ois.readObject();
                					menulist = (ArrayList<MenuList>) ois.readObject();
                  					recipe = (ArrayList<Recipe>) ois.readObject();
                  					man.loadRecipe(recipe, menulist, reserve); 
                  
                  					System.out.println("1 : 식단, 식재료, 레시피 추가  2 : 레시피 수정  3 : 레시피 삭제 "); // 식재료 or 레시피만 추가할 경우...?
                  					num = s.nextInt(); // 1or 2 보내기
                  					if(num ==1) {
                     						oos.writeObject("insert");
                     						oos.flush();
                     						
                     						System.out.println("1 : 식단 추가  2 : 식재료 추가  3 : 레시피 추가");
                     						num = s.nextInt();
                     						if (num == 1) {
                     							oos.writeObject("ml");
                         						oos.flush();
                     							menulist = man.insertMenuList();
                     							oos.writeObject(menulist);
                         						oos.flush();
                     						}
                     						else if (num == 2) {
                     							oos.writeObject("rs");
                         						oos.flush();
                     							reserve = man.insertReserve();
                     							oos.writeObject(reserve);
                         						oos.flush();
                     						}
                     						else if (num == 3) {
                     							oos.writeObject("r");
                         						oos.flush();
                     							recipe = man.insertRecipe();
                     							oos.writeObject(recipe);
                         						oos.flush();
                     						}
                        					// 레시피, 식단, 식재료 보내기
                   					}
                   					else if(num == 2) {
                      						oos.writeObject("modify");
                      						oos.flush();
                      
                      						recipe = man.modifyRecipe();
                      						oos.writeObject(recipe);
                      						oos.flush();
                      						// 레시피 보내기
                  					}
                   					else if(num == 3) {System.out.println("cc");
                   						oos.writeObject("delete"); System.out.println("cc");
                  						oos.flush();System.out.println("ca");
                  
                  						recipe = man.deleteRecipe();System.out.println("c");
                  						oos.writeObject(recipe);System.out.println("ca");
                  						oos.flush();
                   					}
                   					break;
					case loadSchedule:
						str="S";
						oos.writeObject(str);
                       				oos.flush();
                        				schedule = (ArrayList<Schedule>) ois.readObject(); // 일정표 받아옴
                        				man.loadSchedule(schedule);

						System.out.println("1 : 일정표 추가   2 : 일정표 수정  3 : 일정 삭제"); 
						num = s.nextInt(); // 1or 2 보내기
						if (num == 1) {
							oos.writeObject("insert");
							oos.flush();
							schedule = man.insertSchdule();
							oos.writeObject(schedule);
							oos.flush();

						} else if (num == 2) {
							oos.writeObject("modify");
							oos.flush();
							schedule = man.modifySchedule();
							oos.writeObject(schedule);
							oos.flush();
						} else if (num == 3) {
							oos.writeObject("delete");
							oos.flush();
							schedule = man.deleteSchedule();
							oos.writeObject(schedule);
							oos.flush();
						}
						break;
					case enrollMenu:
						str="l";
						oos.writeObject(str);
                        				oos.flush();

                        				menu = (ArrayList<Menu>) ois.readObject();
                        				menu = man.enrollMenu();
                        
                        				oos.writeObject(menu);
                        				oos.flush();
                        				//메뉴 보내기
                         				break;
					}
					oos.close(); ois.close(); os.close(); is.close();
					socket.close();
				}
				break;
			case "Teacher":
				while(Sel_Menu!=Exit) {
					socket=new Socket("192.168.208.148",5002);
					os=socket.getOutputStream();
					oos=new ObjectOutputStream(os);
					is=socket.getInputStream();
					ois=new ObjectInputStream(is);
					TeacherMain tea=new TeacherMain();
					Sel_Menu=tea.SelectMenu();
					switch(Sel_Menu) {
					case showMenu :
						str="M";
						oos.writeObject(str);
						oos.flush();
						menu=(ArrayList <Menu>) ois.readObject();
						tea.InsertMenu(menu);
						menulist=(ArrayList <MenuList>) ois.readObject();
						tea.InsertMenuList(menulist);
						System.out.print("날짜를 입력하세요 : ");
						int date=s.nextInt();
						tea.showMenus(date);
						break;
					case showApplicants:
						str="A";
						oos.writeObject(str);
						oos.flush();
						applications=(ArrayList <Applications>) ois.readObject();
						tea.InsertApplications(applications);
						applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
						tea.InsertApplicationsList(applicationslist);
						menulist=(ArrayList <MenuList>) ois.readObject();
						tea.InsertMenuList(menulist);
						tea.showApplicationss();
						break;
					case showRequires:
						System.out.println("a");
						str="R";
						oos.writeObject(str);
						oos.flush();
						System.out.println("a");
						System.out.println("a");
						applications=(ArrayList <Applications>) ois.readObject();
						tea.InsertApplications(applications);
						menulist=(ArrayList <MenuList>) ois.readObject();
						tea.InsertMenuList(menulist);
						System.out.println("a");
						recipe=(ArrayList <Recipe>) ois.readObject();
						tea.InsertRecipe(recipe);
						System.out.println("a");
						reserve=(ArrayList <Reserve>) ois.readObject();
						System.out.println("a");
						tea.InsertReserve(reserve);
						System.out.println("a");
						applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
						tea.InsertApplicationsList(applicationslist);
						boolean how=true; //gui 분들이 이거 건드려서 방식 선택.
						int startdate, enddate;
						System.out.println("a");
						if(how==true) {
							System.out.print("날짜 입력 : ");
							startdate=s.nextInt();
							tea.showRequires(startdate);
						}
						else {
							System.out.print("시작 날짜 입력 : ");
							startdate=s.nextInt();
							System.out.print("끝나는 날짜 입력 : ");
							enddate=s.nextInt();
							tea.showRequires(startdate,enddate);
						}
						break;
					case EnrollAccount:
						str="E";
						oos.writeObject(str);
						oos.flush();
						applications=(ArrayList <Applications>) ois.readObject();
						tea.InsertApplications(applications);
						tea.showDepositedApplicationss();
						int[] num=tea.CheckDeposit();
						oos.writeObject(num);
						oos.flush();
						break;
					}
					oos.close(); ois.close(); os.close(); is.close();
					socket.close();
				}
				break;
			case "Student":
            			while(Sel_Menu!=Exit) {
					socket=new Socket("192.168.208.148",5003);
					os=socket.getOutputStream();
					oos=new ObjectOutputStream(os);
					is=socket.getInputStream();
					ois=new ObjectInputStream(is);
               				Sel_Menu=1;
                  				StudentMain stu=new StudentMain();
                  				Sel_Menu=stu.SelectMenu();
                  				switch(Sel_Menu) {
                  				case showMenu: 
						str="M";
						oos.writeObject(str);
                         				oos.flush();
                        				menu=(ArrayList <Menu>) ois.readObject();
                     					stu.InsertMenu(menu);
                     					menulist=(ArrayList <MenuList>) ois.readObject();
                     					stu.InsertMenuList(menulist);
                     
                     					stu.Show_Menu();
                     					break;
                  	case applyRecipe:
						str="A";
						oos.writeObject(str);
						oos.flush();
						applications = (ArrayList<Applications>) ois.readObject();
						stu.InsertApplications(applications);
						applicationslist = (ArrayList<ApplicationsList>) ois.readObject();
						stu.InsertApplicationsList(applicationslist);
						menu = (ArrayList<Menu>) ois.readObject();
						stu.InsertMenu(menu);
						menulist = (ArrayList<MenuList>) ois.readObject();
						for (int i = 0; i < menulist.size(); i++) {
							System.out.println(menulist.get(i).getMenuName());
						}
						stu.InsertMenuList(menulist);

						String studentName = s.next();//이름
						String phoneNum = s.next();//전화번호
						int date = s.nextInt();//수업일
						String branch = s.next();//강의장소
						
						Applications app = stu.Apply(studentName,phoneNum,date,branch);//신청
						oos.writeObject(app);
						oos.flush();

						String menuName = s.next();//식단명

						String menuID = stu.MenuID(menuName);//식단ID변환
						double amount = s.nextDouble();//수량

						double total_Price = stu.TotalPrice(menuID,amount);//총액

						ApplicationsList app2 = stu.Apply_List(app,menuID,amount,total_Price);//신청내역

						oos.writeObject(app2);
						oos.flush();

						

						break;
					case cancleRecipe:
						str = "C";
						oos.writeObject(str);
                          				oos.flush();
                     					
                     	menulist=(ArrayList <MenuList>) ois.readObject();
                     					stu.InsertMenuList(menulist);
						applications=(ArrayList <Applications>) ois.readObject();
                    	 				stu.InsertApplications(applications);
						applicationslist=(ArrayList <ApplicationsList>) ois.readObject();
                     					stu.InsertApplicationsList(applicationslist);
                     
                     					System.out.print("이름:");
                     					String sName = s.next();
                     					
                     					System.out.print("전화번호:");
                     					String pNum = s.next();
                     					
                     					System.out.print("수업일:");
                     					int d = s.nextInt();
                     					
                     					System.out.print("강의장소:");
                     					String b = s.next();
                     					
                     					System.out.print("식단명:");
                     					String mName = s.next();
                     		            
                     					System.out.print("취소일:");
                     					int cancelDay = s.nextInt();
                     					
                     					int[] cancle = stu.Cancle(sName,pNum,d,b,mName,cancelDay);
                     					oos.writeObject(cancle);
                     					oos.flush();
                     					//TCP로 취소할 레시피 전송
                     					break;
                 				}
					oos.close(); ois.close(); os.close(); is.close();
					socket.close();
            			}
				break;
			}
			System.out.println("종료합니다!");
		}
		catch(UnknownHostException e) { System.err.println(e); }
		catch(IOException e) { System.err.println(e); }
		catch(Exception e) { System.err.println(e); }
		finally{
			try {
				oos.close();
				oos.close();
				socket.close();
				ois.close();
				is.close();
			}
			catch(IOException e) { System.err.println(e); }
		}
		
	}
}
