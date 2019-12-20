package CookingClass.Client;
import CookingClass.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	ArrayList<Menu> m = new ArrayList<Menu>(); // ���� ���ں� �ǽ��޴�
	ArrayList<Schedule> s = new ArrayList<Schedule>(); // ��������
	ArrayList<Recipe> r = new ArrayList<Recipe>(); // ������
	ArrayList<MenuList> ml = new ArrayList<MenuList>(); // �Ĵ�
	ArrayList<Reserve> rs = new ArrayList<Reserve>(); // �����
	ArrayList<Recipe> emptyR = new ArrayList<Recipe>(); // ������ ���� �� ������ ��� ����Ʈ
	ArrayList<Schedule> emptyS = new ArrayList<Schedule>(); // ������ ���� �� ����ǥ ����Ʈ
	
	int num = 0;
	static Scanner sc = new Scanner(System.in);

	public void loadRecipe(ArrayList<Recipe> recipe, ArrayList<MenuList> menuList, ArrayList<Reserve> reserve) {
		r = recipe;
		ml = menuList;
		rs = reserve;

		System.out.println("----- �Ĵ� ���̺�-------------------------------------------");
		for (MenuList tmp : ml) {
			System.out.println(
					"�Ĵ�ID : " + tmp.getMenuID() + "\t�Ĵܸ� : " + tmp.getMenuName() + "\t������ : " + tmp.getPrice());
		}
		System.out.println("----- ����� ���̺�------------------------------------------");
		for (Reserve tmp : rs) {
			System.out.println(
					"�����ID : " + tmp.getReserveID() + "\t������ : " + tmp.getReserveName() + "\t���� : " + tmp.getUnit());
		}
		System.out.println("----- ������ ���̺�------------------------------------------");
		for (Recipe tmp : r) {
			System.out.println(
					"�Ĵ�ID : " + tmp.getMenuID() + "\t�����ID : " + tmp.getReserveID() + "\t�ҿ䷮ : " + tmp.getRequires());
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
		System.out.println("**�ǽ��޴� ���****************************");
		System.out.println("������� : ");
		b = sc.next();
		System.out.println("������ : ");
		d = sc.nextInt();
		for (int i = 0; i < 3; i++) {
			System.out.println( "������� : " + b + "������ : "+ d );
			System.out.println(i + "��° �Ĵ� ID : ");
			mID = sc.next();

			Menu tmp = new Menu(b, d, mID);
			m.add(tmp);
		}
		return m;
	}

	// insert
	// ----------------------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Recipe> insertRecipe() { // ������ �߰�
		String mID;
		String g;
		double q;
		int num;

		//r.clear(); // �� arrayList
		System.out.println("**������ ���****************************");
		while (true) {
			System.out.println("�Ĵ�ID �Է� : "); // ������
			mID = sc.next();
			int cnt=-1;
			//for(Recipe tmp : r) {
			for(int i=0;i<ml.size();i++) {
				 if(!ml.get(i).getMenuID().equals(mID) && i==ml.size()-1) {
					System.out.println("�������� �ʴ� �Ĵ�ID�Դϴ�."); 
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
				System.out.println("�����ID �Է� : ");
				g = sc.next();
				//for(Recipe tmp : r) { 
				for(int i=0;i<rs.size();i++) {
					if(!g.equals(rs.get(i).getReserveID()) && i==rs.size()-1) {
						System.out.println("�������� �ʴ� �����ID�Դϴ�."); continue;
					}
					if(g.equals(rs.get(i).getReserveID())) {
						cnt=i;
					}
				}
				if(cnt==-1) break;
	
				System.out.println("�ҿ䷮ �Է� :");
				q = sc.nextLong();
				Recipe tmp = new Recipe(mID, g, q);
				emptyR.add(tmp);
				System.out.println("[�Ĵ�ID " + mID + "�� ����� �߰��Է�?]");
				System.out.println("1 : ��, 2: �ƴϿ�");
				num = sc.nextInt();
				if (num == 1) continue;
				else break;
			}
			System.out.println("[���ο� �Ĵ� ID����Ͻðڽ��ϱ� ? ]");
			System.out.println("1 : ���, 2 : �ݱ�");
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
		System.out.println("**�Ĵ� ���****************************");
		while (true) {
			System.out.println("�Ĵ�ID : "); // �Ĵ�
			mID = sc.next();
			for(MenuList tmp : ml) { 
				if(mID.equals(tmp.getMenuID())) {
					System.out.println("�̹� �����ϴ� �Ĵ�ID�Դϴ�."); 
					continue;
					}
				}
			System.out.println("�Ĵܸ� : ");
			mName = sc.next();
			System.out.println("�ǽ����� : ");
			p = sc.nextInt();
			MenuList tmp = new MenuList(mID, mName, p);
			ml.add(tmp);
			System.out.println("[���ο� �Ĵ��� �߰��Ͻðڽ��ϱ�? ] ");
			System.out.println("1: �߰�, 2: ����");
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
		System.out.println("**����� ���***************************");
		while (true) {
			System.out.println("�����ID, ������, ����"); // �����
			rID = sc.next();
			rName = sc.next();
			u = sc.next();
			Reserve tmp = new Reserve(rID, rName, u);
			rs.add(tmp);
			
			System.out.println("[���ο� ����� �߰��Ͻðڽ��ϱ�?]");
			System.out.println("1: �߰� , 2: ����");
			y = sc.nextInt();
			if (y == 1)
				continue;
			else
				break;
		}
		return rs;
	}

	public ArrayList<Schedule> insertSchdule() { // ���� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		String b, tID, d;
		int f, t, y;
		s.clear();
		System.out.println("**����ǥ ���***************************");
		while (true) {
			System.out.println("���� : ");
			b = sc.next();
			System.out.println("�⵵ : ");
			y = sc.nextInt();
			System.out.println("���� ID : ");
			tID = sc.next();
			System.out.println("���� : ");
			d = sc.next();
			System.out.println("���۽ð� : ");
			f = sc.nextInt();
			System.out.println("����ð� : ");
			t = sc.nextInt();

			Schedule tmp = new Schedule(b, y, tID, d, f, t);
			s.add(tmp);
			System.out.println("[ �� ���� ������ �߰��Ͻðڽ��ϱ�? ]");
			System.out.println("1: �߰� , 2: ����");
			y = sc.nextInt();
			if (y == 1)
				continue;
			return s;
		}
	}

	// modifyRecipe-------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<Recipe> modifyRecipe() { // ������ ����
		String m;
		String g;
		double q;
	
		System.out.println("������ �Ĵ�ID : ");
		m = sc.next();
		System.out.println("�����ID : ");
		g = sc.next();
		for (Recipe tmp : r) {
			if (m.equals(tmp.getMenuID()) && g.equals(tmp.getReserveID())) {
				r.clear(); // �� arrayList
				System.out.println("���� �ҿ䷮�� : ");
				q = sc.nextDouble();

				Recipe tmp1 = new Recipe(m, g, q);
				r.add(tmp1);
				return r;
			}
		}
		return null;
	}

	public ArrayList<Schedule> modifySchedule() { // ���� @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		String branch;
		int year;
		String teacherID;
		String day;
		int from;
		int to;

		System.out.println("������ ���� ������� : ");
		branch = sc.next();
		System.out.println("������ ���� �⵵: ");
		year = sc.nextInt();
		System.out.println("������ ���� ����id : ");
		teacherID = sc.next();
		for (Schedule tmp : s) {
			if (branch.equals(tmp.getBranch()) && (year == tmp.getYear()) && (teacherID.equals(tmp.getTeacherID()))) {
				s.clear(); // �� arrayList
				System.out.println("������ ���� : ");
				day = sc.next();
				System.out.println("������ ���۽ð�  : ");
				from = sc.nextInt();
				System.out.println("������ ����ð� : ");
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
		public ArrayList<Recipe> deleteRecipe() { // ������ ����
		String mID; 
		String rID;

		while(true) {
			System.out.println("������ �Ĵ�ID, �����ID");
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
			System.out.println("[������ ������ �� �����մϱ�?]");
			System.out.println("1: ��, 2: ����");
			num = sc.nextInt();
			if (num == 1)
				continue;
			else 
				return emptyR;
		}
	}
	public ArrayList<Schedule> deleteSchedule() { //���� ����  // �������,�⵵,  ���� ID �ѱ�
		String b, tID, d, f, t;
		int y;
		while(true) {
		System.out.println("������  �������, �⵵, ����id"); // ������ ���� �⺻Ű
		b = sc.next(); //���
		y = sc.nextInt(); // �⵵ 
		tID = sc.next(); //id
		for(Schedule tmp: s) {
			if(b.equals(tmp.getBranch())&&y == tmp.getYear()&&tID.equals(tmp.getTeacherID())) {
				s.remove(tmp);
				Schedule sendS = new Schedule(b,y,tID,tmp.getDay(),tmp.getFrom(),tmp.getTo());
				emptyS.add(sendS);
				break;
			}
		}
		System.out.println("[������ ������ �� �����մϱ�?]");
		System.out.println("1: ��, 2: ����");
		num = sc.nextInt();
		if (num == 1)
			continue;
		else 
			return emptyS;
		}
	}
}
