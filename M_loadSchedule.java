package CookingClass;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class M_loadSchedule extends JFrame {

   private JPanel contentPane;
   private DefaultTableModel dtm;
   private DefaultTableModel dtm2;
   private DefaultTableModel dtm3;
   private JTable table;
   private JTextField place;
   private JTextField year;
   private JTextField ID;
   private JTextField day;
   private JTextField from;
   private JTextField to;
   private JTextField d_place;
   private JTextField d_year;
   private JTextField d_ID;
   private JTextField m_place;
   private JTextField m_year;
   private JTextField m_ID;
   private JTextField m_day;
   private JTextField m_from;
   private JTextField m_to;
   private JTable table_1;
   private JTable table_2;
   Socket socket=null;
   OutputStream os=null;
   ObjectOutputStream oos=null;
   InputStream is=null;
   ObjectInputStream ois=null;
   ArrayList <Menu> menu = new ArrayList <Menu> ();
   ArrayList <MenuList> menulist = new ArrayList <MenuList> ();
   ArrayList <Applications> applications = new ArrayList <Applications> ();
   ArrayList <ApplicationsList> applicationslist = new ArrayList <ApplicationsList> ();
   ArrayList <Recipe> recipe = new ArrayList <Recipe> ();
   ArrayList <Reserve> reserve = new ArrayList <Reserve> ();
   ArrayList <Schedule> schedule = new ArrayList <Schedule> ();
   ArrayList<Schedule> s = new ArrayList<Schedule>();
   ArrayList<Schedule> emptyS = new ArrayList<Schedule>();
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               M_loadSchedule frame = new M_loadSchedule();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public M_loadSchedule() {
      try {
         System.out.println("C");
      socket=new Socket("192.168.208.148",5001);
      os=socket.getOutputStream();
      oos=new ObjectOutputStream(os);
      is=socket.getInputStream();
      ois=new ObjectInputStream(is);
      setTitle("\uAC15\uC758\uC77C\uC815\uD45C \uB4F1\uB85D");
      
      setBounds(100, 100, 709, 496);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      dtm=new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "\uAC15\uC758\uC7A5\uC18C", "\uB144\uB3C4", "\uAC15\uC0ACID", "\uC694\uC77C", "FROM", "TO"
            });
      contentPane.setLayout(null);
      
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(0, 0, 691, 449);
      contentPane.add(tabbedPane);
      
      JPanel panel = new JPanel();
      tabbedPane.addTab(" 등 록 ", null, panel, null);
      panel.setLayout(null);
      
      Panel panel_1 = new Panel();
      panel_1.setBounds(10, 10, 666, 265);
      panel.add(panel_1);
      panel_1.setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane = new JScrollPane();
      panel_1.add(scrollPane, BorderLayout.CENTER);
      
      table = new JTable(dtm);
      table.setModel(dtm);
      scrollPane.setViewportView(table);
      
      JLabel lblNewLabel = new JLabel("\uAC15\uC758\uC7A5\uC18C");
      lblNewLabel.setBounds(10, 290, 62, 18);
      panel.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("\uB144\uB3C4");
      lblNewLabel_1.setBounds(10, 340, 62, 18);
      panel.add(lblNewLabel_1);
      
      JLabel lblNewLabel_2 = new JLabel("\uAC15\uC0ACID");
      lblNewLabel_2.setBounds(10, 387, 62, 18);
      panel.add(lblNewLabel_2);
      
      place = new JTextField();
      place.setBounds(86, 287, 116, 24);
      panel.add(place);
      place.setColumns(10);
      
      year = new JTextField();
      year.setBounds(86, 337, 116, 24);
      panel.add(year);
      year.setColumns(10);
      
      ID = new JTextField();
      ID.setBounds(86, 384, 116, 24);
      panel.add(ID);
      ID.setColumns(10);
      
      JLabel 요일 = new JLabel("\uC694\uC77C");
      요일.setBounds(262, 290, 62, 18);
      panel.add(요일);
      
      JLabel From = new JLabel("From");
      From.setBounds(262, 340, 62, 18);
      panel.add(From);
      
      JLabel TO = new JLabel("To");
      TO.setBounds(262, 387, 62, 18);
      panel.add(TO);
      
      day = new JTextField();
      day.setBounds(354, 287, 116, 24);
      panel.add(day);
      day.setColumns(10);
      
      from = new JTextField();
      from.setBounds(354, 337, 116, 24);
      panel.add(from);
      from.setColumns(10);
      
      to = new JTextField();
      to.setBounds(354, 384, 116, 24);
      panel.add(to);
      to.setColumns(10);
      
      JButton btnNewButton = new JButton("\uB4F1 \uB85D");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //등록
            //ArrayList<Schedule> s = new ArrayList<Schedule>();
            try {
               String str="S";
               oos.writeObject(str);
                               oos.flush();
                                schedule = (ArrayList<Schedule>) ois.readObject();
               oos.writeObject("insert");
               oos.flush();
               Schedule tmp = new Schedule(place.getText(), Integer.parseInt(year.getText()), ID.getText(), day.getText(), Integer.parseInt(from.getText()), Integer.parseInt(to.getText()));
               s.add(tmp);
               for( Schedule d : s)
               {
                  System.out.println(d.getBranch()+d.getTeacherID());
               }
               oos.writeObject(s);
               oos.flush();
               int i=0;
               while(true) {
                  dtm.addRow(new Object[] {s.get(i).getBranch(),s.get(i).getYear(),s.get(i).getTeacherID(),s.get(i).getDay(),s.get(i).getFrom(),s.get(i).getTo()});
                  i++;
                  if(i>=s.size()) {break;}
               }
               dispose();
            }
            catch(UnknownHostException e1) { System.err.println(e1); }
            catch(IOException e1) { System.err.println(e1); }
            catch(Exception e1) { System.err.println(e1); }
            
         }
      });
      btnNewButton.setBounds(554, 367, 105, 27);
      panel.add(btnNewButton);
      
      JPanel panel_4 = new JPanel();
      panel_4.setLayout(null);
      tabbedPane.addTab(" 수 정 ", null, panel_4, null);
      
      Panel panel_5 = new Panel();
      panel_5.setBounds(10, 10, 666, 265);
      panel_4.add(panel_5);
      panel_5.setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane_2 = new JScrollPane();
      panel_5.add(scrollPane_2, BorderLayout.CENTER);
      
      dtm2=new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "\uAC15\uC758\uC7A5\uC18C", "\uB144\uB3C4", "\uAC15\uC0ACID", "\uC694\uC77C", "From", "TO"
            });
      table_2 = new JTable(dtm2);
      table_2.setModel(dtm2);
      scrollPane_2.setViewportView(table_2);
      
      JLabel label_6 = new JLabel("\uAC15\uC758\uC7A5\uC18C");
      label_6.setBounds(10, 290, 62, 18);
      panel_4.add(label_6);
      
      JLabel label_7 = new JLabel("\uB144\uB3C4");
      label_7.setBounds(10, 340, 62, 18);
      panel_4.add(label_7);
      
      JLabel label_8 = new JLabel("\uAC15\uC0ACID");
      label_8.setBounds(10, 387, 62, 18);
      panel_4.add(label_8);
      
      m_place = new JTextField();
      m_place.setColumns(10);
      m_place.setBounds(86, 287, 116, 24);
      panel_4.add(m_place);
      
      m_year = new JTextField();
      m_year.setColumns(10);
      m_year.setBounds(86, 337, 116, 24);
      panel_4.add(m_year);
      
      m_ID = new JTextField();
      m_ID.setColumns(10);
      m_ID.setBounds(86, 384, 116, 24);
      panel_4.add(m_ID);
      
      JLabel label_9 = new JLabel("\uC694\uC77C");
      label_9.setBounds(262, 290, 62, 18);
      panel_4.add(label_9);
      
      JLabel label_10 = new JLabel("From");
      label_10.setBounds(262, 340, 62, 18);
      panel_4.add(label_10);
      
      JLabel label_11 = new JLabel("To");
      label_11.setBounds(262, 387, 62, 18);
      panel_4.add(label_11);
      
      m_day = new JTextField();
      m_day.setColumns(10);
      m_day.setBounds(354, 287, 116, 24);
      panel_4.add(m_day);
      
      m_from = new JTextField();
      m_from.setColumns(10);
      m_from.setBounds(354, 337, 116, 24);
      panel_4.add(m_from);
      
      m_to = new JTextField();
      m_to.setColumns(10);
      m_to.setBounds(354, 384, 116, 24);
      panel_4.add(m_to);
      
      JButton button_1 = new JButton("\uC218 \uC815");
      button_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            //수정
            try {
               String str="S";
               oos.writeObject(str);
               oos.flush();
               s = (ArrayList<Schedule>) ois.readObject();
               oos.writeObject("modify");
               oos.flush();
               for (Schedule tmp : s) {
                  if (m_place.getText().equals(tmp.getBranch()) && (Integer.parseInt(m_year.getText()) == tmp.getYear()) && (m_ID.getText().equals(tmp.getTeacherID()))) {
                     //s.clear(); // 빈 arrayList
                     
                     Schedule tmp1 = new Schedule(m_place.getText(), Integer.parseInt(m_year.getText()), m_ID.getText(), m_day.getText(), Integer.parseInt(m_from.getText()), Integer.parseInt(m_to.getText()));
                     emptyS.add(tmp1);
                     
                     oos.writeObject(emptyS);
                     oos.flush();
                     
                     break;
                  }
               int i=0;
               while(true) {
                  dtm.addRow(new Object[] {s.get(i).getBranch(),s.get(i).getYear(),s.get(i).getTeacherID(),s.get(i).getDay(),s.get(i).getFrom(),s.get(i).getTo()});
                  i++;
                  if(i>=s.size()) {break;}
                  }
               }
               dispose();
            }
            catch(UnknownHostException e1) { System.err.println(e1); }
            catch(IOException e1) { System.err.println(e1); }
            catch(Exception e1) { System.err.println(e1); }
            
         }
      });
      button_1.setBounds(554, 367, 105, 27);
      panel_4.add(button_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setLayout(null);
      tabbedPane.addTab(" 삭 제 ", null, panel_2, null);
      
      Panel panel_3 = new Panel();
      panel_3.setBounds(10, 10, 666, 265);
      panel_2.add(panel_3);
      panel_3.setLayout(new BorderLayout(0, 0));
      
      JScrollPane scrollPane_1 = new JScrollPane();
      panel_3.add(scrollPane_1, BorderLayout.CENTER);
      
      dtm3=new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
               "\uAC15\uC758\uC7A5\uC18C", "\uB144\uB3C4", "\uAC15\uC0ACID", "\uC694\uC77C", "From", "TO"
            });
      table_1 = new JTable(dtm3);
      table_1.setModel(dtm3);
      scrollPane_1.setViewportView(table_1);
      
      JLabel label = new JLabel("\uAC15\uC758\uC7A5\uC18C");
      label.setBounds(10, 290, 62, 18);
      panel_2.add(label);
      
      JLabel label_1 = new JLabel("\uB144\uB3C4");
      label_1.setBounds(10, 340, 62, 18);
      panel_2.add(label_1);
      
      JLabel label_2 = new JLabel("\uAC15\uC0ACID");
      label_2.setBounds(10, 387, 62, 18);
      panel_2.add(label_2);
      
      d_place = new JTextField();
      d_place.setColumns(10);
      d_place.setBounds(86, 287, 116, 24);
      panel_2.add(d_place);
      
      d_year = new JTextField();
      d_year.setColumns(10);
      d_year.setBounds(86, 337, 116, 24);
      panel_2.add(d_year);
      
      d_ID = new JTextField();
      d_ID.setColumns(10);
      d_ID.setBounds(86, 384, 116, 24);
      panel_2.add(d_ID);
      
      JButton button = new JButton("\uC0AD \uC81C");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //삭제
               try {
                  String str = "S";
                  oos.writeObject(str);
                  oos.flush();
                  s = (ArrayList<Schedule>) ois.readObject();
                  oos.writeObject("delete");
                  oos.flush();
                  for (Schedule tmp : s) {
                     if (d_place.getText().equals(tmp.getBranch())
                           && Integer.parseInt(d_year.getText()) == tmp.getYear()
                           && d_ID.getText().equals(tmp.getTeacherID())) {
                        s.remove(tmp);
                        Schedule sendS = new Schedule(d_place.getText(), Integer.parseInt(d_year.getText()),
                              d_ID.getText(), tmp.getDay(), tmp.getFrom(), tmp.getTo());
                        emptyS.add(sendS);
                        break;
                     }
                  }
                  schedule = emptyS;
                  oos.writeObject(schedule);
                  oos.flush();
                  /*int i = 0;
                  while (true) {
                     dtm.addRow(new Object[] { s.get(i).getBranch(), s.get(i).getYear(), s.get(i).getTeacherID(),
                           s.get(i).getDay(), s.get(i).getFrom(), s.get(i).getTo() });
                     i++;
                     if (i >= s.size()) {
                        break;
                     }
                  }*/
                  dispose();
               }
            catch(UnknownHostException e1) { System.err.println(e1); }
            catch(IOException e1) { System.err.println(e1); }
            catch(Exception e1) { System.err.println(e1); }
         }
         
      });
      button.setBounds(554, 367, 105, 27);
      panel_2.add(button);
   }
      catch(UnknownHostException e) { System.err.println(e); }
      catch(IOException e) { System.err.println(e); }
      catch(Exception e) { System.err.println(e); }
   }
}