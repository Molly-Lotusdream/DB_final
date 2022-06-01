package exercise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentMain;

public class ExerciseController {
private String sex;

	public void setSex(String sex) {
	this.sex = sex;
}

	private Stage oldstage;
	private String Sid;
	private String Sname;
	private int Lastno_S=1;
	private int Lastno_M=1;
	private int Lastno_B=1;
	private int Lastno_J=1;
	private int maxq=10;

	private int flag=1;//当前题型，1——单选，2——多选，3——填空，4——判断

	String DBDriver = "com.mysql.cj.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	String DBUser = "root";
	String DBPass = "123456";


	@FXML
    private Button Qblanks;

    @FXML
    private Button saved;

    @FXML
    private Label numbers;

    @FXML
    private Button back;

    @FXML
    private Label label_A;

    @FXML
    private Label result;

    @FXML
    private Pane exercisePane;

    @FXML
    private Button Last_q;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton selectC;

    @FXML
    private RadioButton selectB;

    @FXML
    private Button Qjudge;

    @FXML
    private RadioButton selectA;

    @FXML
    private Button Next_q;

    @FXML
    private RadioButton selectTrue;

    @FXML
    private Pane paneMain;

    @FXML
    private RadioButton selectFalse;

    @FXML
    private Label qusetion;

    @FXML
    private Button Qselection;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton selectD;

    @FXML
    private Label answer;

    @FXML
    private ImageView background;

    @FXML
    private TextField writeAns;

    @FXML
    private CheckBox MselectB;

    @FXML
    private CheckBox MselectA;

    @FXML
    private CheckBox MselectD;

    @FXML
    private CheckBox MselectC;

    @FXML
    private Button Qselection_M;

    //选择题
    @FXML
    void QclickS(ActionEvent event) {
    	searchQusetion(1,Lastno_S);
    	flag=1;
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
    	answer.setVisible(false);
    	result.setVisible(false);
    }

    //多选题
    @FXML
    void QclickS_M(ActionEvent event) {
    	searchQusetion(2,Lastno_M);
    	flag=2;
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
    	answer.setVisible(false);
    	result.setVisible(false);
    }

    //填空题
    @FXML
    void QclickB(ActionEvent event) {
    	searchQusetion(3,Lastno_B);
    	flag=3;
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
    	label_A.setVisible(true);
    	writeAns.setVisible(true);
    	answer.setVisible(false);
    	result.setVisible(false);
    }

    //判断题

    //判断题
    @FXML
    void QclickJ(ActionEvent event) {
    	searchQusetion(4,Lastno_J);
    	flag=4;
    	selectA.setVisible(false);
    	selectB.setVisible(false);
    	selectC.setVisible(false);
    	selectD.setVisible(false);
    	MselectA.setVisible(false);
    	MselectB.setVisible(false);
    	MselectC.setVisible(false);
    	MselectD.setVisible(false);
    	selectTrue.setVisible(true);
    	selectFalse.setVisible(true);
    	label_A.setVisible(false);
    	writeAns.setVisible(false);
    	answer.setVisible(false);
    	result.setVisible(false);
    }

    //上一题
    @FXML
    void NextClick(ActionEvent event) {
    	int no;
    	if(flag==1) {
    		if(Lastno_S>1) Lastno_S--;
    		no=this.Lastno_S;
    	}
    	else if(flag==2) {
    		if(Lastno_M>1) Lastno_M--;
    		no=this.Lastno_M;
    	}
    	else if(flag==3) {
    		if(Lastno_B>1) Lastno_B--;
    		no=this.Lastno_B;
    	}
    	else {
    		if(Lastno_J>1) Lastno_J--;
    		no=this.Lastno_J;
    	}
    	searchQusetion(flag,no);
    	answer.setVisible(false);
    	result.setVisible(false);
    	selectA.setSelected(false);
    	selectB.setSelected(false);
    	selectC.setSelected(false);
    	selectD.setSelected(false);
    	MselectA.setSelected(false);
    	MselectB.setSelected(false);
    	MselectC.setSelected(false);
    	MselectD.setSelected(false);
    	selectTrue.setSelected(false);
    	selectFalse.setSelected(false);
    }

    //下一题
    @FXML
    void Last_Click(ActionEvent event) {
    	int no;
    	if(flag==1) {
    		if(Lastno_S<maxq) Lastno_S++;
    		no=this.Lastno_S;
    	}
    	else if(flag==2) {
    		if(Lastno_M<maxq) Lastno_M++;
    		no=this.Lastno_M;
    	}
    	else if(flag==3) {
    		if(Lastno_B<maxq) Lastno_B++;
    		no=this.Lastno_B;
    		writeAns.clear();
    	}
    	else {
    		if(Lastno_J<maxq) Lastno_J++;
    		no=this.Lastno_J;
    	}
    	searchQusetion(flag,no);
    	answer.setVisible(false);
    	result.setVisible(false);
    	selectA.setSelected(false);
    	selectB.setSelected(false);
    	selectC.setSelected(false);
    	selectD.setSelected(false);
    	MselectA.setSelected(false);
    	MselectB.setSelected(false);
    	MselectC.setSelected(false);
    	MselectD.setSelected(false);
    	selectTrue.setSelected(false);
    	selectFalse.setSelected(false);
    }

    //保存
    @FXML
    void SaveClick(ActionEvent event) {
    	String myAns="";
    	if(flag==1) {
    		if(selectA.isSelected()==true)
    			myAns="A";
    		if(selectB.isSelected()==true)
    			myAns="B";
    		if(selectC.isSelected()==true)
    			myAns="C";
    		if(selectD.isSelected()==true)
    			myAns="D";
    	}
    	else if(flag==2) {
    		if(MselectA.isSelected()==true)
    			myAns=myAns+"A";
    		if(MselectB.isSelected()==true)
    			myAns=myAns+"B";
    		if(MselectC.isSelected()==true)
    			myAns=myAns+"C";
    		if(MselectD.isSelected()==true)
    			myAns=myAns+"D";
    	}
    	else if(flag==3) {
    		myAns=writeAns.getText();
    	}
    	else {
    		if(selectTrue.isSelected()==true)
    			myAns="正确";
    		if(selectFalse.isSelected()==true)
    			myAns="错误";
    	}
    	myAns="参考答案："+myAns;
    	if(myAns.equals(answer.getText()))
    		result.setText("√");
    	else
    		result.setText("×");
    	answer.setVisible(true);
    	result.setVisible(true);
    }

    //返回
    @FXML
    void QclickBack(ActionEvent event) throws SQLException {
    	MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
    	Connection conn = myDB.getMyConnection();
    	try {
	    	 Statement stmt = conn.createStatement();

	    	 String sql="update s_Lastpractice set LastChose='"+this.Lastno_S+"'where Sid='"+Sid+"'";
			 stmt.executeUpdate(sql);

			 sql="update s_Lastpractice set LastMchose='"+this.Lastno_M+"'where Sid='"+Sid+"'";
			 stmt.executeUpdate(sql);


			 sql="update s_Lastpractice set LastJudge='"+this.Lastno_J+"'where Sid='"+Sid+"'";
			 stmt.executeUpdate(sql);

			 sql="update s_Lastpractice set LastBlanks='"+this.Lastno_B+"'where Sid='"+Sid+"'";
			 stmt.executeUpdate(sql);

			 stmt.close();
			 myDB.closeMyConnection();//关闭连接
		    } catch (SQLException sqle)
		  {
			System.out.println("SQLException：" + sqle);
		  }
    	Stage stage = new Stage();
		Scene scene = new Scene(new StudentMain(stage,Sname,Sid,sex));
		stage.setScene(scene);
		stage.setTitle("学生主界面");
		stage.show();
    	oldstage.hide();
    }


    @FXML
    void setOldStage(Stage stage) {
    	oldstage=stage;
    }

    void getSid(String Sid,String Sname) {
    	this.Sid=Sid;
    	this.Sname=Sname;

    }

    //初始化
    void init() {
    	MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
    	Connection conn = myDB.getMyConnection();
    	String sql = "select Sid,LastChose,LastMchose,LastJudge,LastBlanks from s_Lastpractice where Sid=" + Sid;
		ResultSet rset;
		try {
			Statement stmt;
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			// 查询到数据
			if (rset.next()) {
				Lastno_S=rset.getInt("LastChose");
				Lastno_M=rset.getInt("LastMchose");
				Lastno_B=rset.getInt("LastBlanks");
				Lastno_J=rset.getInt("LastJudge");
			}
			// 未查询到数据
			else {
				sql="insert into s_Lastpractice values("+Sid+",1,1,1,1)";
				stmt.executeUpdate(sql);
			}
			stmt.close();

			String sql1 = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from sselection where q_id=" + Lastno_S;
			Statement stmt1=conn.createStatement();
			ResultSet rset1=stmt1.executeQuery(sql1);
			if (rset1.next()) {
				qusetion.setText(rset1.getString("q_content"));
				numbers.setText(rset1.getString("q_id"));
				selectA.setText("A:"+rset1.getString("q_A"));
				selectB.setText("B:"+rset1.getString("q_B"));
				selectC.setText("C:"+rset1.getString("q_C"));
				selectD.setText("D:"+rset1.getString("q_D"));
				answer.setText("参考答案："+rset1.getString("q_answer"));
			}
			stmt1.close();


			myDB.closeMyConnection();//关闭连接
		}catch(Exception e){
			e.printStackTrace();
		}

    }

    //根据题型和题号查找更新题目
    void searchQusetion(int flag,int No) {
    	MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
    	Connection conn = myDB.getMyConnection();
    	try {
    		//单选
			if(flag==1) {
				String sql1 = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from sselection where q_id=" + Lastno_S;
				Statement stmt1=conn.createStatement();
				ResultSet rset1=stmt1.executeQuery(sql1);
				if (rset1.next()) {
					qusetion.setText(rset1.getString("q_content"));
					numbers.setText(rset1.getString("q_id"));
					selectA.setText("A:"+rset1.getString("q_A"));
					selectB.setText("B:"+rset1.getString("q_B"));
					selectC.setText("C:"+rset1.getString("q_C"));
					selectD.setText("D:"+rset1.getString("q_D"));
					answer.setText("参考答案："+rset1.getString("q_answer"));
				}
				stmt1.close();
				myDB.closeMyConnection();//关闭连接
			}
			//多选
			else if(flag==2) {
				String sql1 = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from mchoices where q_id=" + Lastno_M;
				Statement stmt1=conn.createStatement();
				ResultSet rset1=stmt1.executeQuery(sql1);
				if (rset1.next()) {
					qusetion.setText(rset1.getString("q_content"));
					numbers.setText(rset1.getString("q_id"));
					MselectA.setText("A:"+rset1.getString("q_A"));
					MselectB.setText("B:"+rset1.getString("q_B"));
					MselectC.setText("C:"+rset1.getString("q_C"));
					MselectD.setText("D:"+rset1.getString("q_D"));
					answer.setText("参考答案："+rset1.getString("q_answer"));
				}
				stmt1.close();
				myDB.closeMyConnection();//关闭连接
			}
			//填空
			else if(flag==3) {
				String sql1 = "select q_id,q_content,q_answer from blanks where q_id=" + Lastno_B;
				Statement stmt1=conn.createStatement();
				ResultSet rset1=stmt1.executeQuery(sql1);
				if (rset1.next()) {
					qusetion.setText(rset1.getString("q_content"));
					numbers.setText(rset1.getString("q_id"));
					answer.setText("参考答案："+rset1.getString("q_answer"));
				}
				stmt1.close();
				myDB.closeMyConnection();//关闭连接
			}
			//判断
			else if(flag==4) {
				String sql1 = "select q_id,q_content,q_answer from judgment where q_id=" + Lastno_J;
				Statement stmt1=conn.createStatement();
				ResultSet rset1=stmt1.executeQuery(sql1);
				if (rset1.next()) {
					qusetion.setText(rset1.getString("q_content"));
					numbers.setText(rset1.getString("q_id"));
					answer.setText("参考答案："+rset1.getString("q_answer"));
				}
				stmt1.close();
				myDB.closeMyConnection();//关闭连接
			}

		}catch(Exception e){
			e.printStackTrace();
		}

    }

}
