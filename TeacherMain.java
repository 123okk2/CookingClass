package CookingClass.Client;
import CookingClass.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.*;


public class TeacherMain{
	private ArrayList <MenuList> menulists=new ArrayList<MenuList>();
	private ArrayList <Reserve> reserves=new ArrayList<Reserve>();
	private ArrayList <Recipe> recipes=new ArrayList<Recipe> ();
	private ArrayList <Menu> menus=new ArrayList<Menu> ();
	private ArrayList <Applications> applications=new ArrayList<Applications> ();
	private ArrayList <ApplicationsList> applicationlists=new ArrayList<ApplicationsList> ();

	public int SelectMenu() {
		System.out.println("1. �޴���ȸ\t2. ����� ��û�� ��� ��ȸ\t3. ����� �ҿ䷮ ��� ��ȸ\t4. ������ü ���� ����");
		Scanner s=new Scanner(System.in);
		int sel=s.nextInt();

		return sel;
	}

	//1��
	public void InsertMenu(ArrayList <Menu> menus) { this.menus=menus; }
	public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public totalMenus showMenus(int date){
		int dates=0;
		String branch="";
		String[] menuss=new String[3];
		int k=0;
		System.out.println("������\t�Ĵܸ�\t�������");
		for(int i=0;i<menus.size();i++) {
			if(date==menus.get(i).getDate()) {
				for(int j=0;j<menulists.size();j++) {
					if(menulists.get(j).getMenuID().equals(menus.get(i).getMenuID())) {	
						dates=menus.get(i).getDate();
						branch=menus.get(i).getBranch();
						menuss[k]=menulists.get(j).getMenuName();
						k++;
						break;
					}
				}
			}
		}

		totalMenus t = new totalMenus(dates,branch,menuss[0],menuss[1],menuss[2]);
		return t;
	}

	//2��
	public void InsertApplications(ArrayList <Applications> applications) { this.applications=applications; }
	public void InsertApplicationsList(ArrayList <ApplicationsList> applicationlists) { this.applicationlists=applicationlists; }
	//public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public ArrayList <totalApplications> showApplicationss() {
		ArrayList <totalApplications> ta=new ArrayList<totalApplications>();
		System.out.println("��û��ȣ\t������\t�̸�\t��ȭ��ȣ\t�޴���");
		for(int i=0;i<applications.size();i++) {
			for(int j=0;j<applicationlists.size();j++) {
				if(applications.get(i).getAppNum() == applicationlists.get(j).getAppNum()){
					for(int k=0;k<menulists.size();k++) {
						if(applicationlists.get(j).getMenuID().equals(menulists.get(k).getMenuID())) {
							//System.out.print("\t" + applications.get(i).getAppNum()+"\t"+applications.get(i).getDate()+"\t"+applications.get(i).getStudentName()+"\t"+applications.get(i).getPhoneNum());
							//System.out.println(menulists.get(k).getMenuName());
							ta.add(new totalApplications(applications.get(i).getAppNum(), applications.get(i).getDate(), applications.get(i).getStudentName(), applications.get(i).getPhoneNum(), menulists.get(k).getMenuName(), applications.get(i).getDepositDay()));
							break;
						}
					}
					break;
				}
			}
		}
		return ta;
	}

	//3��
	//public void InsertApplicationsList(ArrayList <ApplicationsList> applicationlists) { this.applicationlists=applicationlists; }
	//public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public void InsertRecipe(ArrayList <Recipe> recipes) { this.recipes=recipes; }
	public void InsertReserve(ArrayList <Reserve> reserves) { this.reserves=reserves; }
	public ArrayList<totalRequires> showRequires(int date) {
		ArrayList<totalRequires> tr=new ArrayList<totalRequires> ();
		System.out.println("������\t�� �ҿ䷮\t����");
		for(int i = 0; i <applications.size(); i++) {	//��û ���̺� Ȯ��.
			if(applications.get(i).getDate() == date) {	//��û ���̺�>> �Է��� ��¥�� ���� ������ Ȯ��.
				int appNum = applications.get(i).getAppNum();	//�ش� �������� ��û��ȣ Ȯ��.
				//System.out.println("��û��ȣ: " + appNum);
				for(int j = 0; j < applicationlists.size(); j++) {	//��û���� ���̺� Ȯ��.
					if(applicationlists.get(j).getAppNum() == appNum) {	//��û���� ���̺�>> ��û ���̺��� ��û��ȣ�� ���� ��û��ȣ Ȯ��.
						String menuID = applicationlists.get(j).getMenuID();	//��û���� ���̺�>> �Ĵ�id Ȯ��.
						double amount = applicationlists.get(j).getAmount();	//��û���� ���̺�>> ��û���� Ȯ��.
						//System.out.println("�Ĵ�id" + menuID);
						//System.out.println(amount);
						for(int k = 0; k < recipes.size(); k++) {	//recipe ���̺� Ȯ��.
							if(recipes.get(k).getMenuID().equals(menuID)) {	//recpe ���̺�>> �ش� �Ĵ�id Ȯ��.
								String reserveID = recipes.get(k).getReserveID();		//recipe ���̺�>> �Ĵ�id�� �����id Ȯ��.
								//double requires = recipes.get(k).getRequires();		//recipe ���̺�>> �Ĵ�id�� �ҿ䷮ Ȯ��.
								//System.out.println(reserveID);
								//System.out.println(requires);
								double am=recipes.get(k).getRequires() * amount;		//�ҿ䷮ ���: ��û���� * (�Ĵ�id�� �ҿ䷮)
								for(int l = 0; l < reserves.size(); l++) {		//����� ���̺� Ȯ��.
									if(reserves.get(l).getReserveID().equals(reserveID)) {	//����� ���̺��� �����idȮ��.
										String reserveName = reserves.get(l).getReserveName();	//����� ���̺�>> �ش� �����id�� ������ Ȯ��.
										String unit = reserves.get(l).getUnit();	//����� ���̺�>> �ش� �����id�� ���� Ȯ��.
										//System.out.println(reserveName);
										//System.out.println(requires * applicationlists.get(l).getAmount());
										//System.out.println(unit);
										tr.add(new totalRequires(reserveName, am, unit));
									}
								}				
							}
						}
					}
				}
			}

		}
		for(int i=0;i<tr.size();i++) {
			for(int j=i+1;j<tr.size();j++) {
				if(tr.get(i).getName().equals(tr.get(j).getName())) {
					tr.get(i).addAmount(tr.get(j).getAmount());
					tr.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<tr.size();i++) { System.out.println(tr.get(i)); }
		return tr;
	}
	public ArrayList<totalRequires> showRequires(int startDate, int endDate){
		ArrayList<totalRequires> tr=new ArrayList<totalRequires> ();
		System.out.println("������\t�� �ҿ䷮\t����");
		for(int i = 0; i <applications.size(); i++) {	//��û ���̺� Ȯ��.
			if(applications.get(i).getDate() >= startDate && applications.get(i).getDate() <= endDate) {	//��û ���̺�>> �Է��� ��¥�� ���� ������ Ȯ��.
				int appNum = applications.get(i).getAppNum();	//�ش� �������� ��û��ȣ Ȯ��.
				//System.out.println("��û��ȣ: " + appNum);
				for(int j = 0; j < applicationlists.size(); j++) {	//��û���� ���̺� Ȯ��.
					if(applicationlists.get(j).getAppNum() == appNum) {	//��û���� ���̺�>> ��û ���̺��� ��û��ȣ�� ���� ��û��ȣ Ȯ��.
						String menuID = applicationlists.get(j).getMenuID();	//��û���� ���̺�>> �Ĵ�id Ȯ��.
						double amount = applicationlists.get(j).getAmount();	//��û���� ���̺�>> ��û���� Ȯ��.
						//System.out.println("�Ĵ�id" + menuID);
						//System.out.println(amount);
						for(int k = 0; k < recipes.size(); k++) {	//recipe ���̺� Ȯ��.
							if(recipes.get(k).getMenuID().equals(menuID)) {	//recpe ���̺�>> �ش� �Ĵ�id Ȯ��.
								String reserveID = recipes.get(k).getReserveID();		//recipe ���̺�>> �Ĵ�id�� �����id Ȯ��.
								//double requires = recipes.get(k).getRequires();		//recipe ���̺�>> �Ĵ�id�� �ҿ䷮ Ȯ��.
								//System.out.println(reserveID);
								//System.out.println(requires);
								double am=recipes.get(k).getRequires() * amount;		//�ҿ䷮ ���: ��û���� * (�Ĵ�id�� �ҿ䷮)
								for(int l = 0; l < reserves.size(); l++) {		//����� ���̺� Ȯ��.
									if(reserves.get(l).getReserveID().equals(reserveID)) {	//����� ���̺��� �����idȮ��.
										String reserveName = reserves.get(l).getReserveName();	//����� ���̺�>> �ش� �����id�� ������ Ȯ��.
										String unit = reserves.get(l).getUnit();	//����� ���̺�>> �ش� �����id�� ���� Ȯ��.
										//System.out.println(reserveName);
										//System.out.println(requires * applicationlists.get(l).getAmount());
										//System.out.println(unit);
										tr.add(new totalRequires(reserveName, am, unit));
									}
								}				
							}
						}
					}
				}
			}

		}
		System.out.println("a");
		for(int i=0;i<tr.size();i++) {
			for(int j=i+1;j<tr.size();j++) {
				if(tr.get(i).getName().equals(tr.get(j).getName())) {
					tr.get(i).addAmount(tr.get(j).getAmount());
					tr.remove(j);
					j--;
				}
			}
		}
		for(int i=0;i<tr.size();i++) { System.out.println(tr.get(i)); }
		return tr;
		
	}

	//4��
	//public void InsertApplications(ArrayList <Applications> applications) { this.applications=applications; }
	public ArrayList<Applications> showDepositedApplicationss() {
		ArrayList <Applications> app=new ArrayList <Applications> ();
		for(int i=0;i<applications.size();i++) {
			if(applications.get(i).getDepositMoney()>0)
				app.add(applications.get(i));
		}
		return app;
	}
	public int[] CheckDeposit() {
		int[] num=new int[3];
		Scanner s=new Scanner(System.in);
		System.out.print("��û��ȣ�� �Է��ϼ���.");
		num[0]=s.nextInt();
		System.out.print("�Աݾ��� �Է��ϼ���.");
		num[1]=s.nextInt();
		System.out.print("�Ա� ��¥�� �Է��ϼ���.");
		num[2]=s.nextInt();

		return num;
	}
}