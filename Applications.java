package CookingClass;

import java.io.*;

public class Applications implements Serializable {
   private int appNum;
   private String branch;
   private int date;
   private String studentName;
   private String phoneNum;
   private int depositDay;
   private int depositMoney;
   private int cancleDay;
   private int refundDay;
   private int refundMoney;

   public Applications(String branch,int date ,String studentName, String phoneNum)
      {
            this.branch = branch;
            this.date = date;
           this.studentName = studentName;
            this.phoneNum = phoneNum;
      
      }

   public Applications(int appNum, int cancleDay) {      //(추가) 식재료 취소시 취소일 등록.
      this.appNum = appNum;
      this.cancleDay = cancleDay;
   }

   public Applications(int appNum, String branch, int date, String studentName, String phoneNum, int depositDay,
      int depositMoney, int cancleDay, int refundDay, int refundMoney) {
      this.appNum = appNum;
      this.branch = branch;
      this.date = date;
      this.studentName = studentName;
      this.phoneNum = phoneNum;
      this.depositDay = depositDay;
      this.depositMoney = depositMoney;
      this.cancleDay = cancleDay;
      this.refundDay = refundDay;
      this.refundMoney = refundMoney;
   }

   public int getAppNum() { return appNum; }
   public String getBranch() { return branch; }
   public int getDate() { return date; }
   public String getStudentName() { return studentName; }
   public String getPhoneNum() { return phoneNum; }
   public int getDepositDay() { return depositDay; }
   public int getDepositMoney() { return depositMoney; }
   public int getCancleDay() { return cancleDay; }
   public int getRefundDay() { return refundDay; }
   public int getRefundMoney() { return refundMoney; }
}