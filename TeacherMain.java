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
		System.out.println("1. 메뉴조회\t2. 식재료 신청자 명단 조회\t3. 식재료 소요량 목록 조회\t4. 계좌이체 내역 등재");
		Scanner s=new Scanner(System.in);
		int sel=s.nextInt();

		return sel;
	}

	//1번
	public void InsertMenu(ArrayList <Menu> menus) { this.menus=menus; }
	public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public totalMenus showMenus(int date){
		int dates=0;
		String branch="";
		String[] menuss=new String[3];
		int k=0;
		System.out.println("수업일\t식단명\t강의장소");
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

	//2번
	public void InsertApplications(ArrayList <Applications> applications) { this.applications=applications; }
	public void InsertApplicationsList(ArrayList <ApplicationsList> applicationlists) { this.applicationlists=applicationlists; }
	//public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public ArrayList <totalApplications> showApplicationss() {
		ArrayList <totalApplications> ta=new ArrayList<totalApplications>();
		System.out.println("신청번호\t수업일\t이름\t전화번호\t메뉴명");
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

	//3번
	//public void InsertApplicationsList(ArrayList <ApplicationsList> applicationlists) { this.applicationlists=applicationlists; }
	//public void InsertMenuList(ArrayList <MenuList> menulists) { this.menulists=menulists; }
	public void InsertRecipe(ArrayList <Recipe> recipes) { this.recipes=recipes; }
	public void InsertReserve(ArrayList <Reserve> reserves) { this.reserves=reserves; }
	public ArrayList<totalRequires> showRequires(int date) {
		ArrayList<totalRequires> tr=new ArrayList<totalRequires> ();
		System.out.println("식재료명\t총 소요량\t단위");
		for(int i = 0; i <applications.size(); i++) {	//신청 테이블 확인.
			if(applications.get(i).getDate() == date) {	//신청 테이블>> 입력한 날짜와 같은 수업일 확인.
				int appNum = applications.get(i).getAppNum();	//해당 수업일의 신청번호 확인.
				//System.out.println("신청번호: " + appNum);
				for(int j = 0; j < applicationlists.size(); j++) {	//신청내역 테이블 확인.
					if(applicationlists.get(j).getAppNum() == appNum) {	//신청내역 테이블>> 신청 테이블의 신청번호와 같은 신청번호 확인.
						String menuID = applicationlists.get(j).getMenuID();	//신청내역 테이블>> 식단id 확인.
						double amount = applicationlists.get(j).getAmount();	//신청내역 테이블>> 신청수량 확인.
						//System.out.println("식단id" + menuID);
						//System.out.println(amount);
						for(int k = 0; k < recipes.size(); k++) {	//recipe 테이블 확인.
							if(recipes.get(k).getMenuID().equals(menuID)) {	//recpe 테이블>> 해당 식단id 확인.
								String reserveID = recipes.get(k).getReserveID();		//recipe 테이블>> 식단id의 식재료id 확인.
								//double requires = recipes.get(k).getRequires();		//recipe 테이블>> 식단id의 소요량 확인.
								//System.out.println(reserveID);
								//System.out.println(requires);
								double am=recipes.get(k).getRequires() * amount;		//소요량 계산: 신청수량 * (식단id의 소요량)
								for(int l = 0; l < reserves.size(); l++) {		//식재료 테이블 확인.
									if(reserves.get(l).getReserveID().equals(reserveID)) {	//식재료 테이블에서 식재료id확인.
										String reserveName = reserves.get(l).getReserveName();	//식재료 테이블>> 해당 식재료id의 식재료명 확인.
										String unit = reserves.get(l).getUnit();	//식재료 테이블>> 해당 식재료id의 단위 확인.
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
		System.out.println("식재료명\t총 소요량\t단위");
		for(int i = 0; i <applications.size(); i++) {	//신청 테이블 확인.
			if(applications.get(i).getDate() >= startDate && applications.get(i).getDate() <= endDate) {	//신청 테이블>> 입력한 날짜와 같은 수업일 확인.
				int appNum = applications.get(i).getAppNum();	//해당 수업일의 신청번호 확인.
				//System.out.println("신청번호: " + appNum);
				for(int j = 0; j < applicationlists.size(); j++) {	//신청내역 테이블 확인.
					if(applicationlists.get(j).getAppNum() == appNum) {	//신청내역 테이블>> 신청 테이블의 신청번호와 같은 신청번호 확인.
						String menuID = applicationlists.get(j).getMenuID();	//신청내역 테이블>> 식단id 확인.
						double amount = applicationlists.get(j).getAmount();	//신청내역 테이블>> 신청수량 확인.
						//System.out.println("식단id" + menuID);
						//System.out.println(amount);
						for(int k = 0; k < recipes.size(); k++) {	//recipe 테이블 확인.
							if(recipes.get(k).getMenuID().equals(menuID)) {	//recpe 테이블>> 해당 식단id 확인.
								String reserveID = recipes.get(k).getReserveID();		//recipe 테이블>> 식단id의 식재료id 확인.
								//double requires = recipes.get(k).getRequires();		//recipe 테이블>> 식단id의 소요량 확인.
								//System.out.println(reserveID);
								//System.out.println(requires);
								double am=recipes.get(k).getRequires() * amount;		//소요량 계산: 신청수량 * (식단id의 소요량)
								for(int l = 0; l < reserves.size(); l++) {		//식재료 테이블 확인.
									if(reserves.get(l).getReserveID().equals(reserveID)) {	//식재료 테이블에서 식재료id확인.
										String reserveName = reserves.get(l).getReserveName();	//식재료 테이블>> 해당 식재료id의 식재료명 확인.
										String unit = reserves.get(l).getUnit();	//식재료 테이블>> 해당 식재료id의 단위 확인.
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

	//4번
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
		System.out.print("신청번호를 입력하세요.");
		num[0]=s.nextInt();
		System.out.print("입금액을 입력하세요.");
		num[1]=s.nextInt();
		System.out.print("입금 날짜를 입력하세요.");
		num[2]=s.nextInt();

		return num;
	}
}