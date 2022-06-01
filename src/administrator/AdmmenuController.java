package administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import login.Login;

public class AdmmenuController {
	// 变量定义
	private Stage oldStage = null;
	private String Aid = null;
	@FXML
	private TextField idCK;
	@FXML
	private Button outCK;
	@FXML
	private Button passwordCK;
	@FXML
	private Label Aname;
	@FXML
	private Button questionCK;
	@FXML
	private Button inquireCK;
	@FXML
	private Button testCK;
	@FXML
	private Button checkCK;
	@FXML
	private Button gradeCK;
	// 数据库连接
	String DBDriver = "com.mysql.cj.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
	String DBUser = "root";
	String DBPass = "123456";

	// 查询学生账号信息
	@FXML
	void inquireClick(ActionEvent event) throws SQLException {
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();
		Statement stmt;
		stmt = conn.createStatement();
		String querystu = "select * from student where Sid='" + idCK.getText() + "'";
		ResultSet rset;
		rset = stmt.executeQuery(querystu);
		if (rset.next()) {
			String Sname = rset.getString("Sname");
			String Ssex = rset.getString("Ssex");
			String Sschool = rset.getString("Sschool");
			String Sstate = rset.getString("state");
			String Sphone = rset.getString("Sphone");
			Stage stu = new Stage();
			Stumessage stumsg = new Stumessage(stu, idCK.getText(), Sname, Ssex, Sschool, Sstate, Sphone,
					Aname.getText(), Aid);
			Scene stumsgscene = new Scene(stumsg);
			stu.setScene(stumsgscene);
			stu.setTitle("学生信息管理");
			stu.show();
			oldStage.hide();
			stmt.close();
			myDB.closeMyConnection();
		} else {
			Alert warning = new Alert(AlertType.WARNING, "未查询到该学生信息" + "请检查账号输入是否有误");
			warning.setTitle("查询错误");
			warning.show();
		}
	}

	// 查看个人资料
	@FXML
	void checkClick(ActionEvent event) {

		try {
			MyDBConnection myDB2 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
			Connection conn2 = myDB2.getMyConnection();
			Statement stmt2;
			stmt2 = conn2.createStatement();
			String sql2 = "select Aid,Asex,Aphone from administrator where Aname='" + Aname.getText() + "'";
			ResultSet rset2 = stmt2.executeQuery(sql2);
			String id = null, sex = null, phone = null;
			if (rset2.next()) {
				id = rset2.getString("Aid");
				sex = rset2.getString("Asex");
				phone = rset2.getString("Aphone");
				Stage admsgstage = new Stage();
				Admmessage adm = new Admmessage(admsgstage, id, Aname.getText(), sex, phone);
				Scene adscene = new Scene(adm);
				admsgstage.setTitle("查看个人信息");
				admsgstage.setScene(adscene);
				admsgstage.show();
				oldStage.hide();
				stmt2.close();
				myDB2.closeMyConnection();// 关闭连接
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	// 修改密码
	@FXML
	void passwordClick(ActionEvent event) {
		Stage cpassStage = new Stage();
		Cpassword cpass = new Cpassword(cpassStage, Aname.getText(), Aid);
		Scene cpassScene = new Scene(cpass);
		cpassStage.setScene(cpassScene);
		cpassStage.setTitle("修改密码");
		cpassStage.show();
		oldStage.hide();
	}

	// 退出登录
	@FXML
	void outClick(ActionEvent event) {
		Login login = new Login();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		;

		// 隐藏之前的窗体
		oldStage.hide();
	}

	@FXML
	void questionCK(ActionEvent event) {// 修改题目
		Stage quesStage = new Stage();
		Scene quesScene = new Scene(new Cquestion(quesStage, Aname.getText(), Aid));
		quesScene.getStylesheets().add(getClass().getResource("../css/Cquestion.css").toExternalForm());
		quesStage.setScene(quesScene);
		quesStage.setTitle("题库管理");
		quesStage.show();
		oldStage.hide();
	}
	public void setTestCK() throws SQLException  {
		MyDBConnection myDB3 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn3 = myDB3.getMyConnection();

		Statement stmt3;
		stmt3 = conn3.createStatement();
		String teststate = "select test from student";
		ResultSet rset2 = stmt3.executeQuery(teststate);
		if (rset2.next() && rset2.getInt("test") == 0) {
			testCK.setText("发布考试");


		} else if (rset2.next() && rset2.getInt("test") == 1) {
			testCK.setText("结束考试");

		}

		stmt3.close();
		myDB3.closeMyConnection();
	}

	@FXML
	void testClick(ActionEvent event) throws SQLException {
		MyDBConnection myDB4 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB4.getMyConnection();
		Statement stmt4;
		stmt4 = conn.createStatement();// 发布考试或结束考试
if(testCK.getText().equals("发布考试")){
	String test = "update student set test=1";
	stmt4.executeUpdate(test);
	testCK.setText("结束考试");
	Alert warning = new Alert(AlertType.INFORMATION, "考试已发布，" + "请通知学生进入考试！");
	warning.setTitle("考试发布成功");
	warning.show();
}
else{
	String test = "update student set test=0";
	stmt4.executeUpdate(test);
	testCK.setText("发布考试");
	Alert warning = new Alert(AlertType.INFORMATION, "考试已结束");
	warning.setTitle("考试结束");
	warning.show();
}
stmt4.close();
myDB4.closeMyConnection();
	}



	@FXML
	void gradeClick(ActionEvent event) {// 查看成绩
		Stage gradeStage = new Stage();
		Admgrade grade = new Admgrade(gradeStage, Aname.getText(), Aid);
		Scene gradeScene = new Scene(grade);
		gradeStage.setScene(gradeScene);
		gradeStage.setTitle("学生成绩查询");
		gradeStage.show();
		oldStage.hide();
	}

	public void setAname(String name, String id) {// 获取管理员的名字显示到界面上
		Aname.setText(name);
		Aid = id;
	}

	public void setOldStage(Stage stage) {// 建立舞台
		oldStage = stage;
	}

}
