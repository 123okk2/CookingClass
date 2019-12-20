package CookingClass.Client;
import CookingClass.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	ArrayList<Menu> m = new ArrayList<Menu>(); // 강의 일자별 실습메뉴
	ArrayList<Schedule> s = new ArrayList<Schedule>(); // 강의일정
	ArrayList<Recipe> r = new ArrayList<Recipe>(); // 레시피
	ArrayList<MenuList> ml = new ArrayList<MenuList>(); // 식단
	ArrayList<Reserve> rs = new ArrayList<Reserve>(); // 식재료
	ArrayList<Recipe> emptyR = new ArrayList<Recipe>(); // 삭제를 위한 빈 레시피 어레이 리스트
	ArrayList<Schedule> emptyS = new ArrayList<Schedule>(); // 삭제를 위한 빈 일정표 리스트
	
	int num = 0;
	static Scanner sc = new Scanner(System.in);

	public void loadRecipe(ArrayList<Recipe> recipe, ArrayList<MenuList> menuList, ArrayList<Reserve> reserve) {
		r = recipe;
		ml = menuList;
		rs = reserve;

		System.out.println("----- 식단 테이블-------------------------------------------");
		for (MenuList tmp : ml) {
			System.out.println(
					"식단ID : " + tmp.getMenuID() + "\t식단명 : " + tmp.getMenuName() + "\t식재료비 : " + tmp.getPrice());
		}
		System.out.println("----- 식재료 테이블------------------------------------------");
		for (Reserve tmp : rs) {
			System.out.println(
					"식재료ID : " + tmp.getReserveID() + "\t식재료명 : " + tmp.getReserveName() + "\t단위 : " + tmp.getUnit());
		}
		System.out.println("----- 레시피 테이블------------------------------------------");
		for (Recipe tmp : r) {
			System.out.println(
					"식단ID : " + tmp.getMenuID() + "\t식재료ID : " + tmp.getReserveID() + "\t소요량 : " + tmp.getRequires());
		}

	}

	public ArrayList<Schedule> loadSchedule(ArrayList<Schedule> Schedule) {
		s = Schedule;
		return s;
	}

	public ArrayList<Menu> enrollMenu() {
		String b;
		String mID;
		int d;
		System.out.println("**실습메뉴 등록****************************");
		System.out.println("강의장소 : ");
		b = sc.next();
		System.out.println("수업일 : ");
		d = sc.nextInt();
		for (int i = 0; i < 3; i++) {
			System.out.println( "강의장소 : " + b + "수업일 : "+ d );
			System.out.println(i + "번째 식단 ID : ");
			mID = sc.next();

			Menu tmp = new Menu(b, d, mID);
			m.add(tmp);
		}
		return m;
	}

	// insert
	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Recipe> insertRecipe() { // 레시피 추가
		String mID;
		String g;
		double q;
		int num;

		//r.clear(); // 빈 arrayList
		System.out.println("**레시피 등록****************************");
		while (true) {
			System.out.println("식단ID 입력 : "); // 레시피
			mID = sc.next();
			int cnt=-1;
			//for(Recipe tmp : r) {
			for(int i=0;i<ml.size();i++) {
				 if(!ml.get(i).getMenuID().equals(mID) && i==ml.size()-1) {
					System.out.println("존재하지 않는 식단ID입니다."); 
					break;
				}
				if(ml.get(i).getMenuID().equals(mID)) {
					cnt=i;
					break;
				}
			}

			if(cnt==-1) break;
			cnt=-1;

			while (true) {
				System.out.println("식재료ID 입력 : ");
				g = sc.next();
				//for(Recipe tmp : r) { 
				for(int i=0;i<rs.size();i++) {
					if(!g.equals(rs.get(i).getReserveID()) && i==rs.size()-1) {
						System.out.println("존재하지 않는 식재료ID입니다."); continue;
					}
					if(g.equals(rs.get(i).getReserveID())) {
						cnt=i;
					}
				}
				if(cnt==-1) break;
	
				System.out.println("소요량 입력 :");
				q = sc.nextLong();
				Recipe tmp = new Recipe(mID, g, q);
				emptyR.add(tmp);
				System.out.println("[식단ID " + mID + "의 식재료 추가입력?]");
				System.out.println("1 : 네, 2: 아니요");
				num = sc.nextInt();
				if (num == 1) continue;
				else break;
			}
			System.out.println("[새로운 식단 ID등록하시겠습니까 ? ]");
			System.out.println("1 : 등록, 2 : 닫기");
			num = sc.nextInt();
			if (num == 1)
				continue;
			else
				break;
		}
		return r;
	}

	public ArrayList<MenuList> insertMenuList() {
		String mID;
		String mName;
		int p;

		ml.clear();
		System.out.println("**식단 등록****************************");
		while (true) {
			System.out.println("식단ID : "); // 식단
			mID = sc.next();
			for(MenuList tmp : ml) { 
				if(mID.equals(tmp.getMenuID())) {
					System.out.println("이미 존재하는 식단ID입니다."); 
					continue;
					}
				}
			System.out.println("식단명 : ");
			mName = sc.next();
			System.out.println("실습재료비 : ");
			p = sc.nextInt();
			MenuList tmp = new MenuList(mID, mName, p);
			ml.add(tmp);
			System.out.println("[새로운 식단을 추가하시겠습니까? ] ");
			System.out.println("1: 추가, 2: 종료");
			p = sc.nextInt();
			if (p == 1)
				continue;
			else
				break;
		}
		return ml;
	}

	public ArrayList<Reserve> insertReserve() {
		String rID;
		String rName;
		String u;
		int y;
		
		rs.clear();
		System.out.println("**식재료 등록***************************");
		while (true) {
			System.out.println("식재료ID, 식재료명, 단위"); // 식재료
			rID = sc.next();
			rName = sc.next();
			u = sc.next();
			Reserve tmp = new Reserve(rID, rName, u);
			rs.add(tmp);
			
			System.out.println("[새로운 식재료 추가하시겠습니까?]");
			System.out.println("1: 추가 , 2: 종료");
			y = sc.nextInt();
			if (y == 1)
				continue;
			else
				break;
		}
		return rs;
	}

	public ArrayList<Schedule> insertSchdule() { // 수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		String b, tID, d;
		int f, t, y;
		s.clear();
		System.out.println("**일정표 등록***************************");
		while (true) {
			System.out.println("지점 : ");
			b = sc.next();
			System.out.println("년도 : ");
			y = sc.nextInt();
			System.out.println("강사 ID : ");
			tID = sc.next();
			System.out.println("요일 : ");
			d = sc.next();
			System.out.println("시작시간 : ");
			f = sc.nextInt();
			System.out.println("종료시간 : ");
			t = sc.nextInt();

			Schedule tmp = new Schedule(b, y, tID, d, f, t);
			s.add(tmp);
			System.out.println("[ 새 강의 일정을 추가하시겠습니까? ]");
			System.out.println("1: 추가 , 2: 종료");
			y = sc.nextInt();
			if (y == 1)
				continue;
			return s;
		}
	}

	// modifyRecipe-------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Recipe> modifyRecipe() { // 레시피 수정
		String m;
		String g;
		double q;
	
		System.out.println("수정할 식단ID : ");
		m = sc.next();
		System.out.println("식재료ID : ");
		g = sc.next();
		for (Recipe tmp : r) {
			if (m.equals(tmp.getMenuID()) && g.equals(tmp.getReserveID())) {
				r.clear(); // 빈 arrayList
				System.out.println("수정 소요량값 : ");
				q = sc.nextDouble();

				Recipe tmp1 = new Recipe(m, g, q);
				r.add(tmp1);
				return r;
			}
		}
		return null;
	}

	public ArrayList<Schedule> modifySchedule() { // 수정 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		String branch;
		int year;
		String teacherID;
		String day;
		int from;
		int to;

		System.out.println("수정할 행의 강의장소 : ");
		branch = sc.next();
		System.out.println("수정할 행의 년도: ");
		year = sc.nextInt();
		System.out.println("수정할 행의 강사id : ");
		teacherID = sc.next();
		for (Schedule tmp : s) {
			if (branch.equals(tmp.getBranch()) && (year == tmp.getYear()) && (teacherID.equals(tmp.getTeacherID()))) {
				s.clear(); // 빈 arrayList
				System.out.println("수정된 요일 : ");
				day = sc.next();
				System.out.println("수정된 시작시간  : ");
				from = sc.nextInt();
				System.out.println("수정된 종료시간 : ");
				to = sc.nextInt();

				Schedule tmp1 = new Schedule(branch, year, teacherID, day, from, to);
				s.add(tmp1);
				return s;
			}
		}
		return null;
	}

	public static int SelectMenu() {
		System.out.println("1. loadRecipr\n 2. loadSchedule\n 3. enrollMenu\n 4. Exit");

		Scanner s = new Scanner(System.in);
		int sel = s.nextInt();

		return sel;
	}
		public ArrayList<Recipe> deleteRecipe() { // 레시피 삭제
		String mID; 
		String rID;

		while(true) {
			System.out.println("삭제할 식단ID, 식재료ID");
			mID = sc.next();
			rID = sc.next();
	
			for(Recipe tmp: r) {
				if(mID.equals(tmp.getMenuID())&&rID.equals(tmp.getReserveID())) {
					r.remove(tmp);
					Recipe sendR = new Recipe(mID, rID, tmp.getRequires());
					emptyR.add(sendR);
					break;
				}
			}
			System.out.println("[삭제할 정보가 더 존재합니까?]");
			System.out.println("1: 예, 2: 종료");
			num = sc.nextInt();
			if (num == 1)
				continue;
			else 
				return emptyR;
		}
	}
	public ArrayList<Schedule> deleteSchedule() { //일정 삭제  // 강의장소,년도,  강사 ID 넘김
		String b, tID, d, f, t;
		int y;
		while(true) {
		System.out.println("삭제할  강의장소, 년도, 강사id"); // 삭제를 위한 기본키
		b = sc.next(); //장소
		y = sc.nextInt(); // 년도 
		tID = sc.next(); //id
		for(Schedule tmp: s) {
			if(b.equals(tmp.getBranch())&&y == tmp.getYear()&&tID.equals(tmp.getTeacherID())) {
				s.remove(tmp);
				Schedule sendS = new Schedule(b,y,tID,tmp.getDay(),tmp.getFrom(),tmp.getTo());
				emptyS.add(sendS);
				break;
			}
		}
		System.out.println("[삭제할 정보가 더 존재합니까?]");
		System.out.println("1: 예, 2: 종료");
		num = sc.nextInt();
		if (num == 1)
			continue;
		else 
			return emptyS;
		}
	}
}
