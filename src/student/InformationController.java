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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InformationController {

	private Stage OldStage = null;

	@FXML
	private TextField Sschool = null;

	@FXML
	private TextField Ssex = null;

	@FXML
	private Button changeCK;

	@FXML
	private ImageView background;

	@FXML
	private TextField Sphone = null;

	@FXML
	private Button returnCK;

	@FXML
	private Label ID = null;

	@FXML
	private TextField Sname = null;

	@FXML
	private Button checkCK;
	// 数据库连接
	String DBDriver = "com.mysql.cj.jdbc.Driver";
	String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	String DBUser = "root";
	String DBPass = "123456";
	MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	MyDBConnection myDB2 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);

	@FXML
	void returnClick(ActionEvent event) throws SQLException {
		myDB2.closeMyConnection();

		Stage stustage = new Stage();
		StudentMain stumain = new StudentMain(stustage, Sname.getText(), ID.getText(), Ssex.getText(),false);
		Scene adScene = new Scene(stumain);
		stustage.setScene(adScene);
		stustage.setTitle("学生主界面");
		stustage.show();
		OldStage.close();
	}

	@FXML
	void changeClick(ActionEvent event) {// 信息的几个框可用状态
		Sname.setDisable(false);
		Ssex.setDisable(false);
		Sschool.setDisable(false);
		Sphone.setDisable(false);
		Sname.setEditable(true);
		Ssex.setEditable(true);
		Sschool.setEditable(true);
		Sphone.setEditable(true);
	}

	@FXML
	void checkClick(ActionEvent event) throws SQLException {// 修改信息提交到数据库
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("修改个人信息");
		alert.setHeaderText("您的个人信息将被修改");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (Ssex.getText().equals("男")) {
				Ssex.setText("M");
			} else {
				Ssex.setText("F");
			}
			Connection conn2 = myDB2.getMyConnection();
			Statement stmt2 = conn2.createStatement();
			String sql = "update student set Sname='" + Sname.getText() + "'," + "Ssex='" + Ssex.getText() + "'," + "Sphone='"
					+ Sphone.getText() + "' where Sid='" + ID.getText() + "'";
			stmt2.executeUpdate(sql);
			stmt2.close();

		} else {

			alert.close();

		}
		Sname.setDisable(true);
		Ssex.setDisable(true);
		Sphone.setDisable(true);
		Sschool.setDisable(true);
//		Sname.setEditable(false);
//		Ssex.setEditable(false);
//		Sschool.setEditable(false);
//		Sphone.setEditable(false);

	}

	public void setInfo(String name) throws SQLException {
		Connection conn1 = myDB1.getMyConnection();
		String sql1 = "select Sid,Ssex,Sschool,Sphone from student where Sname='" + name + "'";
		ResultSet rset1;
		Statement stmt1;
		stmt1 = conn1.createStatement();
		rset1 = stmt1.executeQuery(sql1);
		rset1.next();
		Sname.setText(name);
		ID.setText(rset1.getString("Sid"));
		Ssex.setText(rset1.getString("Ssex"));
		Sschool.setText(rset1.getString("Sschool"));
		Sphone.setText(rset1.getString("Sphone"));
		stmt1.close();
		myDB1.closeMyConnection();
	}

	public void setOldStage(Stage stage) {
		OldStage = stage;
		Sname.setDisable(true);// 信息的几个框不可用状态
		Ssex.setDisable(true);
		Sschool.setDisable(true);
		Sphone.setDisable(true);
	}

}
