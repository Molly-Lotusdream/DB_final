package student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.Login;

public class StudentMainController {

	@FXML
	private ImageView view;
	private String Sid;
	private String Ssex;
	private Stage oldstage;
//private Label lastTime;
	@FXML
	private Button exit;

	@FXML
	private Button Test;

	@FXML
	private Label grade;


	@FXML
	private Button ChangePass;

	@FXML
	private Button exercise;

	@FXML
	private Label testTime;

	@FXML
	private Label Sname;

	@FXML
	private Button preTest;

	@FXML
	private Button GetInfor;

	@FXML
	private Pane stuPane;
//数据库连接
	String DBDriver = "com.mysql.cj.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	String DBUser = "root";
	String DBPass = "123456";
	MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);

	// 修改密码
	@FXML
	void ChangePass_Click(ActionEvent event) {
		Stage stage = new Stage();
		Scene scene = new Scene(new ChangePass(stage,Sname.getText(),Sid,Ssex));
		stage.setScene(scene);
		stage.setTitle("修改密码");
		stage.show();
		oldstage.close();
	}

	// 进入练习界面
	@FXML
	void exercise_Click(ActionEvent event) {
		Stage stage = new Stage();
		Scene scene = new Scene(new exercise.Exercise(stage, Sid, Sname.getText(), Ssex));
		stage.setScene(scene);
		stage.setTitle("练习");
		stage.show();
		oldstage.close();
	}

	//退出登录
	@FXML
	void exit_Click(ActionEvent event) {
		Login login = new Login();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		;

		// 隐藏之前的窗体
		oldstage.hide();
	}
// 查看学生资料
	@FXML
	void GetInfor_Click(ActionEvent event) throws SQLException {
		Stage stage = new Stage();
		Scene scene = new Scene(new Information(stage,Sname.getText()));
		stage.setScene(scene);
		stage.setTitle("学生资料");
		stage.show();
		oldstage.close();
	}


	// 进入模拟考试界面
	@FXML
	void preTest_Click(ActionEvent event) throws InterruptedException, SQLException {
		Stage stage = new Stage();
		exercise.Test test =new exercise.Test(stage, Sid, Sname.getText(), Ssex, 0);
		Scene scene = new Scene(test);
		stage.setScene(scene);
		stage.setTitle("模拟考试");
		stage.show();


		oldstage.close();
	}

	void setID(String ID) {
		Sid = ID;
	}

//设置name
	void setName(String str) {
		Sname.setText(str);
	}

	@FXML
	void setOldStage(Stage stage) {
		oldstage = stage;
	}

	public void setView(String sex) {
		Ssex=sex;
		if (sex.equals("F")) {
			view.setImage( new Image("img/女用户.png"));
		} else if (sex.equals("M")) {
			view.setImage(new Image("img/男用户.png"));
		}
	}
//进入正式考试
	@FXML
	void Test_Click(ActionEvent event) throws InterruptedException, SQLException {
	if(Test.isDisable()==true){
		Alert warning = new Alert(AlertType.WARNING, "只有一次作答机会" + "交卷后不可再进入考试！");
		warning.setTitle("考试暂未发布！");
		warning.show();
	}
	else{
		MyDBConnection myDB3 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn3 = myDB3.getMyConnection();
		Statement stmt3;
		stmt3 = conn3.createStatement();
		String teststate = "select test from student";
		ResultSet rset2 = stmt3.executeQuery(teststate);
		if (rset2.next() && rset2.getInt("test") == 0) {
			Alert warning = new Alert(AlertType.WARNING, "管理员暂未发布考试" + "不可进入正式考试！");
			warning.setTitle("考试暂未发布！");
			warning.show();
		} else if (rset2.next() && rset2.getInt("test") == 1) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("考前注意事项");
			alert.setHeaderText("1.本次考试限时30分钟，请在规定时间内作答" + "\n" + "2.若超时未交卷，系统将自动收卷"+ "\n" + "3.每人只有一次作答机会，且试卷提交后不允许查看，请谨慎提交");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				alert.close();
				Stage stage = new Stage();
				Scene scene = new Scene(new exercise.Test(stage, Sid, Sname.getText(), Ssex, 1));
				stage.setScene(scene);
				stage.setTitle("正式考试");
				stage.show();
				oldstage.close();
			} else {
				alert.close();
			}

		}
	}
	}
	public Button getTest() {
		return Test;
	}

		public void setTest(boolean bool) {
			Test.setDisable(bool);


	}

		//成绩显示
		public void setGrade(String id) throws SQLException {
			Connection conn1 = myDB.getMyConnection();
			String sql1 = "select Sgrade from student where Sid="+Sid;
			ResultSet rset1;
			Statement stmt1;
			stmt1 = conn1.createStatement();
			rset1 = stmt1.executeQuery(sql1);
			rset1.next();
			String Grade=rset1.getString("Sgrade");
			grade.setText(Grade);
		}





}
