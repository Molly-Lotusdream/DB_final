package exercise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import student.StudentMain;

public class TestController {
	private Timeline animation;
    private String S ;
    private String S2;
    private int tmp = 0;
    private int tmp2 = 30;
    @FXML
    private Label second;
    @FXML
    private Label minute;

	private String sex;

	// 查询获得
	public void setSex(String sex) {
		this.sex = sex;
	}

	private Stage oldstage;
	private String Sid;
	private String Sname;
	private int IfFinalText;
	private int[] QuestionsNo = new int[25];
	private String answer;
	private int Now_number = 1;
	private int[] Q_Grade = new int[25];
	private int[][] MyAns = new int[20][4];
	private String[] MyBlanksAns = new String[5];

	// a-1000,b-0100,c-0010,d-0001

	String DBDriver = "com.mysql.cj.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	String DBUser = "root";
	String DBPass = "123456";

	@FXML
	private Label lastTime;
	@FXML
	private Button No25;
	@FXML
	private ToggleGroup select;
	@FXML
	private Button No22;
	@FXML
	private Button No21;
	@FXML
	private Button saved;
	@FXML
	private Button No24;
	@FXML
	private Button No23;
	@FXML
	private Pane selectPane;
	@FXML
	private ToggleGroup select1;
	@FXML
	private Button lastQusetion;
	@FXML
	private Button No15;
	@FXML
	private RadioButton selectC;
	@FXML
	private Button No14;
	@FXML
	private RadioButton selectB;
	@FXML
	private Button No17;
	@FXML
	private RadioButton selectA;
	@FXML
	private Button No16;
	@FXML
	private Button No11;
	@FXML
	private Label question;
	@FXML
	private Button No10;
	@FXML
	private Button No13;
	@FXML
	private RadioButton selectTrue;
	@FXML
	private Button No12;
	@FXML
	private Button nextQusetion;
	@FXML
	private Button finishTest;
	@FXML
	private RadioButton selectFalse;
	@FXML
	private Pane blanksPane;
	@FXML
	private Button No19;
	@FXML
	private Button No18;
	@FXML
	private Label label_A;
	@FXML
	private RadioButton selectD;
	@FXML
	private Button No2;
	@FXML
	private Button No1;
	@FXML
	private Button No4;
	@FXML
	private Button No3;
	@FXML
	private Button No6;
	@FXML
	private Button No5;
	@FXML
	private Button No8;
	@FXML
	private Button No7;
	@FXML
	private TextField writeAns;
	@FXML
	private CheckBox MselectB;
	@FXML
	private Button No9;
	@FXML
	private CheckBox MselectA;
	@FXML
	private Pane judgePane;
	@FXML
	private Button No20;
	@FXML
	private CheckBox MselectD;
	@FXML
	private CheckBox MselectC;

	@FXML
	void Click1(ActionEvent event) {
		Now_number = 1;
		searchQusetion(1, QuestionsNo[0], 1);

	}

	@FXML
	void Click2(ActionEvent event) {
		Now_number = 2;
		searchQusetion(1, QuestionsNo[1], 2);

	}

	@FXML
	void Click3(ActionEvent event) {
		Now_number = 3;
		searchQusetion(1, QuestionsNo[2], 3);

	}

	@FXML
	void Click4(ActionEvent event) {
		Now_number = 4;
		searchQusetion(1, QuestionsNo[3], 4);

	}

	@FXML
	void Click5(ActionEvent event) {
		Now_number = 5;
		searchQusetion(1, QuestionsNo[4], 5);

	}

	@FXML
	void Click6(ActionEvent event) {
		Now_number = 6;
		searchQusetion(1, QuestionsNo[5], 6);

	}

	@FXML
	void Click7(ActionEvent event) {
		Now_number = 7;
		searchQusetion(1, QuestionsNo[6], 7);

	}

	@FXML
	void Click8(ActionEvent event) {
		Now_number = 8;
		searchQusetion(1, QuestionsNo[7], 8);

	}

	@FXML
	void Click9(ActionEvent event) {
		Now_number = 9;
		searchQusetion(1, QuestionsNo[8], 9);

	}

	@FXML
	void Click10(ActionEvent event) {
		Now_number = 10;
		searchQusetion(1, QuestionsNo[9], 10);

	}

	@FXML
	void Click11(ActionEvent event) {
		Now_number = 11;
		searchQusetion(2, QuestionsNo[10], 11);

	}

	@FXML
	void Click12(ActionEvent event) {
		Now_number = 12;
		searchQusetion(2, QuestionsNo[11], 12);

	}

	@FXML
	void Click13(ActionEvent event) {
		Now_number = 13;
		searchQusetion(2, QuestionsNo[12], 13);

	}

	@FXML
	void Click14(ActionEvent event) {
		Now_number = 14;
		searchQusetion(2, QuestionsNo[13], 14);

	}

	@FXML
	void Click15(ActionEvent event) {
		Now_number = 15;
		searchQusetion(2, QuestionsNo[14], 15);

	}

	@FXML
	void Click16(ActionEvent event) {
		Now_number = 16;
		searchQusetion(3, QuestionsNo[15], 16);

	}

	@FXML
	void Click17(ActionEvent event) {
		Now_number = 17;
		searchQusetion(3, QuestionsNo[16], 17);

	}

	@FXML
	void Click18(ActionEvent event) {
		Now_number = 18;
		searchQusetion(3, QuestionsNo[17], 18);

	}

	@FXML
	void Click19(ActionEvent event) {
		Now_number = 19;
		searchQusetion(3, QuestionsNo[18], 19);

	}

	@FXML
	void Click20(ActionEvent event) {
		Now_number = 20;
		searchQusetion(3, QuestionsNo[19], 20);

	}

	@FXML
	void Click21(ActionEvent event) {
		Now_number = 21;
		searchQusetion(4, QuestionsNo[20], 21);

	}

	@FXML
	void Click22(ActionEvent event) {
		Now_number = 22;
		searchQusetion(4, QuestionsNo[21], 22);

	}

	@FXML
	void Click23(ActionEvent event) {
		Now_number = 23;
		searchQusetion(4, QuestionsNo[22], 23);

	}

	@FXML
	void Click24(ActionEvent event) {
		Now_number = 24;
		searchQusetion(4, QuestionsNo[23], 24);

	}

	@FXML
	void Click25(ActionEvent event) {
		Now_number = 25;
		searchQusetion(4, QuestionsNo[24], 25);
	}

	// 上一题
	@FXML
	void lastQClick(ActionEvent event) {
		if (Now_number > 1)
			Now_number--;
		int f;
		if (Now_number <= 10)
			f = 1;
		else if (Now_number <= 15)
			f = 2;
		else if (Now_number <= 20)
			f = 3;
		else
			f = 4;
		searchQusetion(f, QuestionsNo[Now_number - 1], Now_number);
	}

	// 下一题
	@FXML
	void nextQClick(ActionEvent event) {
		if (Now_number < 25)
			Now_number++;
		int f;
		if (Now_number <= 10)
			f = 1;
		else if (Now_number <= 15)
			f = 2;
		else if (Now_number <= 20)
			f = 3;
		else
			f = 4;
		searchQusetion(f, QuestionsNo[Now_number - 1], Now_number);
	}

	// 交卷
	@FXML
	void FinishClick(ActionEvent event) throws SQLException {
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();
		int grade = 0;
		for (int i = 0; i < 25; i++) {
			grade += Q_Grade[i];
		}
		// System.out.println(grade);

		Stage stage = new Stage();
		Scene scene = new Scene(new StudentMain(stage, Sname, Sid, sex));
		stage.setScene(scene);
		stage.setTitle("学生主界面");
		stage.show();
		oldstage.hide();

		// 模拟考试
		if (IfFinalText == 0) {
			try {
				Statement stmt = conn.createStatement();
				int maxgrade;
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
				System.out.println(dateFormat.format(date));
				String sql = "select Max_grade from shistory where Sid=" + Sid;
				ResultSet rset = stmt.executeQuery(sql);
				if (rset.next()) {
					maxgrade = rset.getInt("Max_grade");
					if (grade > maxgrade) {
						sql = "update shistory set Max_grade='" + grade + "'where Sid='" + Sid + "'";
						stmt.executeUpdate(sql);
					}
					sql = "insert into shistory values('" + Sid + "','" + dateFormat.format(date) + "','" + grade
							+ "','" + maxgrade + "')";
					stmt.executeUpdate(sql);

					Alert ending = new Alert(AlertType.INFORMATION,
							"模拟考试已经结束！你本次的成绩为：" + grade + "分!\n" + "你的历史最高分为：" + maxgrade + "分！");
					ending.setTitle("考试结束");
					ending.show();
					stmt.close();

				} else {
					sql = "insert into shistory values('" + Sid + "','" + dateFormat.format(date) + "','" + grade
							+ "','" + grade + "')";
					stmt.executeUpdate(sql);

					Alert ending = new Alert(AlertType.INFORMATION,
							"模拟考试已经结束！你本次的成绩为：" + grade + "分！你的历史最高分为：" + grade + "分！");
					ending.setTitle("考试结束");
					ending.show();
					stmt.close();

				}
				stmt.close();
				myDB.closeMyConnection();// 关闭连接

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 正式考试
		else {
			try {
				String sql = "update student set Sgrade='" + grade + "'where Sid='" + Sid + "'";
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);

				Alert ending = new Alert(AlertType.INFORMATION, "考试已经结束！你的成绩为：" + grade + "分！");
				ending.setTitle("考试结束");
				ending.show();
				stmt.close();
				myDB.closeMyConnection();// 关闭连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 保存积分
	@FXML
	void saveClick(ActionEvent event) {
		String myAns = "";
		int f;
		if (Now_number <= 10)
			f = 1;
		else if (Now_number <= 15)
			f = 2;
		else if (Now_number <= 20)
			f = 3;
		else
			f = 4;
		if (f == 1) {
			if (selectA.isSelected() == true) {
				myAns = "A";
				MyAns[Now_number - 1][0] = 1;
			}
			if (selectB.isSelected() == true) {
				myAns = "B";
				MyAns[Now_number - 1][1] = 1;
			}
			if (selectC.isSelected() == true) {
				myAns = "C";
				MyAns[Now_number - 1][2] = 1;
			}
			if (selectD.isSelected() == true) {
				myAns = "D";
				MyAns[Now_number - 1][3] = 1;
			}
		} else if (f == 2) {
			if (MselectA.isSelected() == true) {
				myAns = myAns + "A";
				MyAns[Now_number - 1][0] = 1;
			}
			if (MselectB.isSelected() == true) {
				myAns = myAns + "B";
				MyAns[Now_number - 1][1] = 1;
			}
			if (MselectC.isSelected() == true) {
				myAns = myAns + "C";
				MyAns[Now_number - 1][2] = 1;
			}
			if (MselectD.isSelected() == true) {
				myAns = myAns + "D";
				MyAns[Now_number - 1][3] = 1;
			}
		} else if (f == 4) {
			myAns = writeAns.getText();
			MyBlanksAns[Now_number - 21] = myAns;
		} else if (f == 3) {
			if (selectTrue.isSelected() == true) {
				myAns = "正确";
				MyAns[Now_number - 1][0] = 1;
			}
			if (selectFalse.isSelected() == true) {
				myAns = "错误";
				MyAns[Now_number - 1][1] = 1;
			}
		}
		if (myAns.equals(answer))
			Q_Grade[Now_number - 1] = 4;
		else
			Q_Grade[Now_number - 1] = 0;

		if (Now_number == 1)
			No1.setText("第1题(已完成)");
		if (Now_number == 2)
			No2.setText("第2题(已完成)");
		if (Now_number == 3)
			No3.setText("第3题(已完成)");
		if (Now_number == 4)
			No4.setText("第4题(已完成)");
		if (Now_number == 5)
			No5.setText("第5题(已完成)");
		if (Now_number == 6)
			No6.setText("第6题(已完成)");
		if (Now_number == 7)
			No7.setText("第7题(已完成)");
		if (Now_number == 8)
			No8.setText("第8题(已完成)");
		if (Now_number == 9)
			No9.setText("第9题(已完成)");
		if (Now_number == 10)
			No10.setText("第10题(已完成)");
		if (Now_number == 11)
			No11.setText("第11题(已完成)");
		if (Now_number == 12)
			No12.setText("第12题(已完成)");
		if (Now_number == 13)
			No13.setText("第13题(已完成)");
		if (Now_number == 14)
			No14.setText("第14题(已完成)");
		if (Now_number == 15)
			No15.setText("第15题(已完成)");
		if (Now_number == 16)
			No16.setText("第16题(已完成)");
		if (Now_number == 17)
			No17.setText("第17题(已完成)");
		if (Now_number == 18)
			No18.setText("第18题(已完成)");
		if (Now_number == 19)
			No19.setText("第19题(已完成)");
		if (Now_number == 20)
			No20.setText("第20题(已完成)");
		if (Now_number == 21)
			No21.setText("第21题(已完成)");
		if (Now_number == 22)
			No22.setText("第22题(已完成)");
		if (Now_number == 23)
			No23.setText("第23题(已完成)");
		if (Now_number == 24)
			No24.setText("第24题(已完成)");
		if (Now_number == 25)
			No25.setText("第25题(已完成)");
	}

	@FXML
	void setOldStage(Stage stage) {
		oldstage = stage;

	}

	void getSid(String Sid, String Sname, int flag) {
		this.Sid = Sid;
		this.IfFinalText = flag;
		this.Sname = Sname;
	}

	// 根据题型和题号查找更新题目
	void searchQusetion(int flag, int No, int number) {
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();
		try {
			// 单选
			if (flag == 1) {
				String sql1 = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from sselection where q_id=" + No;
				Statement stmt1 = conn.createStatement();
				ResultSet rset1 = stmt1.executeQuery(sql1);
				if (rset1.next()) {
					question.setText("(第" + number + "题)" + rset1.getString("q_content"));
					selectA.setText("A:" + rset1.getString("q_A"));
					selectB.setText("B:" + rset1.getString("q_B"));
					selectC.setText("C:" + rset1.getString("q_C"));
					selectD.setText("D:" + rset1.getString("q_D"));
					answer = rset1.getString("q_answer");
				}
				stmt1.close();
				myDB.closeMyConnection();// 关闭连接
			}
			// 多选
			else if (flag == 2) {
				String sql1 = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from mchoices where q_id=" + No;
				Statement stmt1 = conn.createStatement();
				ResultSet rset1 = stmt1.executeQuery(sql1);
				if (rset1.next()) {
					question.setText("(第" + number + "题)" + rset1.getString("q_content"));
					MselectA.setText("A:" + rset1.getString("q_A"));
					MselectB.setText("B:" + rset1.getString("q_B"));
					MselectC.setText("C:" + rset1.getString("q_C"));
					MselectD.setText("D:" + rset1.getString("q_D"));
					answer = rset1.getString("q_answer");
				}
				stmt1.close();
				myDB.closeMyConnection();// 关闭连接
			}
			// 填空
			else if (flag == 4) {
				String sql1 = "select q_id,q_content,q_answer from blanks where q_id=" + No;
				Statement stmt1 = conn.createStatement();
				ResultSet rset1 = stmt1.executeQuery(sql1);
				if (rset1.next()) {
					question.setText("(第" + number + "题)" + rset1.getString("q_content"));
					answer = rset1.getString("q_answer");
				}
				stmt1.close();
				myDB.closeMyConnection();// 关闭连接
			}
			// 判断
			else if (flag == 3) {
				String sql1 = "select q_id,q_content,q_answer from judgment where q_id=" + No;
				Statement stmt1 = conn.createStatement();
				ResultSet rset1 = stmt1.executeQuery(sql1);
				if (rset1.next()) {
					question.setText("(第" + number + "题)" + rset1.getString("q_content"));
					answer = rset1.getString("q_answer");
				}
				stmt1.close();
				myDB.closeMyConnection();// 关闭连接
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 答案变化
		if (Now_number < 21) {
			if (MyAns[Now_number - 1][0] == 1) {
				selectA.setSelected(true);
				MselectA.setSelected(true);
				selectTrue.setSelected(true);
			} else {
				selectA.setSelected(false);
				MselectA.setSelected(false);
				selectTrue.setSelected(false);
			}

			if (MyAns[Now_number - 1][1] == 1) {
				selectB.setSelected(true);
				MselectB.setSelected(true);
				selectFalse.setSelected(true);
			} else {
				selectB.setSelected(false);
				MselectB.setSelected(false);
				selectFalse.setSelected(false);
			}

			if (MyAns[Now_number - 1][2] == 1) {
				selectC.setSelected(true);
				MselectC.setSelected(true);
			} else {
				selectC.setSelected(false);
				MselectC.setSelected(false);
			}

			if (MyAns[Now_number - 1][3] == 1) {
				selectD.setSelected(true);
				MselectD.setSelected(true);
			} else {
				selectD.setSelected(false);
				MselectD.setSelected(false);
			}
		} else {
			writeAns.setText(MyBlanksAns[Now_number - 21]);
		}

		changeQuestion(flag);

	}

	// 选项状态变化
	void changeQuestion(int flag) {
		if (flag == 1) {
			blanksPane.setVisible(false);
			selectA.setVisible(true);
			selectB.setVisible(true);
			selectC.setVisible(true);
			selectD.setVisible(true);
			MselectA.setVisible(false);
			MselectB.setVisible(false);
			MselectC.setVisible(false);
			MselectD.setVisible(false);
			selectTrue.setVisible(false);
			selectFalse.setVisible(false);
			label_A.setVisible(false);
			writeAns.setVisible(false);
			judgePane.setVisible(false);
			selectPane.setVisible(true);
		} else if (flag == 2) {
			blanksPane.setVisible(false);
			selectA.setVisible(false);
			selectB.setVisible(false);
			selectC.setVisible(false);
			selectD.setVisible(false);
			MselectA.setVisible(true);
			MselectB.setVisible(true);
			MselectC.setVisible(true);
			MselectD.setVisible(true);
			selectTrue.setVisible(false);
			selectFalse.setVisible(false);
			label_A.setVisible(false);
			writeAns.setVisible(false);
			judgePane.setVisible(false);
			selectPane.setVisible(true);
		} else if (flag == 4) {
			selectA.setVisible(false);
			selectB.setVisible(false);
			selectC.setVisible(false);
			selectD.setVisible(false);
			MselectA.setVisible(false);
			MselectB.setVisible(false);
			MselectC.setVisible(false);
			MselectD.setVisible(false);
			selectTrue.setVisible(false);
			selectFalse.setVisible(false);
			blanksPane.setVisible(true);
			label_A.setVisible(true);
			writeAns.setVisible(true);
			judgePane.setVisible(false);
			selectPane.setVisible(false);
		} else if (flag == 3) {
			blanksPane.setVisible(false);
			selectA.setVisible(false);
			selectB.setVisible(false);
			selectC.setVisible(false);
			selectD.setVisible(false);
			MselectA.setVisible(false);
			MselectB.setVisible(false);
			MselectC.setVisible(false);
			MselectD.setVisible(false);
			judgePane.setVisible(true);
			selectTrue.setVisible(true);
			selectFalse.setVisible(true);
			label_A.setVisible(false);
			writeAns.setVisible(false);
			selectPane.setVisible(false);
		}
	}

	// 初始化
	void Init() {
		Random random = new Random();
		int i, j, number;

		// 答案初始化
		for (i = 0; i < 20; i++) {
			for (j = 0; j < 4; j++)
				MyAns[i][j] = 0;
		}
		for (i = 0; i < 5; i++) {
			MyBlanksAns[i] = "";
		}

		// 随机抽题号
		for (i = 0; i < 10; i++) {
			QuestionsNo[i] = 0;
			number = random.nextInt(10) + 1;
			for (j = 0; j < 10; j++) {
				if (QuestionsNo[j] == number)
					break;
			}
			if (j == 10)
				QuestionsNo[i] = number;
			else
				i--;
		}
		for (i = 10; i < 14; i++) {
			number = random.nextInt(10) + 1;
			for (j = 10; j < 14; j++) {
				if (QuestionsNo[j] == number)
					break;
			}
			if (j == 14)
				QuestionsNo[i] = number;
			else
				i--;
		}
		for (i = 15; i < 20; i++) {
			number = random.nextInt(10) + 1;
			for (j = 15; j < 20; j++) {
				if (QuestionsNo[j] == number)
					break;
			}
			if (j == 20)
				QuestionsNo[i] = number;
			else
				i--;
		}
		for (i = 20; i < 24; i++) {
			number = random.nextInt(10) + 1;
			for (j = 20; j < 24; j++) {
				if (QuestionsNo[j] == number)
					break;
			}
			if (j == 24)
				QuestionsNo[i] = number;
			else
				i--;
		}
		searchQusetion(1, QuestionsNo[0], 1);
	}
	//倒计时
    public void Clock() {
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			try {
				timelabel();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public void timelabel() throws SQLException {
      if(tmp==0&&tmp2==0){
        animation.stop();

        MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
        Connection conn = myDB.getMyConnection();
        int grade=0;
        for(int i=0;i<25;i++) {
            grade+=Q_Grade[i];
          }

          Stage stage = new Stage();
        Scene scene = new Scene(new StudentMain(stage,Sname,Sid,sex));
        stage.setScene(scene);
        stage.setTitle("学生主界面");
        stage.show();
          oldstage.hide();

          try {
          String sql = "update student set Sgrade='"+grade+"'where Sid='"+Sid+"'";
        Statement stmt=conn.createStatement();
        stmt.executeUpdate(sql);

        Alert ending = new Alert(AlertType.INFORMATION, "考试已经结束！你的成绩为："+grade+"分！");
        ending.setTitle("考试结束");
        ending.show();
        stmt.close();
        myDB.closeMyConnection();//关闭连接
        }catch(Exception e){
          e.printStackTrace();
        }
      }
      second.setText("00");
      if(tmp==0){
        second.setText("00");
        minute.setText("30 :");
        tmp=59;
        S = tmp + "";
            second.setText(S);
            tmp2--;
            if(tmp2<10){
              S2="0"+tmp2+" :";
              minute.setText(S2);
            }else{
              S2=tmp2+" :";
              minute.setText(S2);
            }
      }else{
        tmp--;
        if(tmp<10){
          S="0"+tmp+"";
          second.setText(S);
        }else{
          S = tmp + "";
          second.setText(S);
        }
      }
    }
	}
