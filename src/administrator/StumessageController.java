package administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StumessageController {
	private Stage oldstage = null;
    private String Aid=null;
	@FXML
	private Label Aname;
	@FXML
	private Label Adname;
	@FXML
	private TextField Sschool;

	@FXML
	private Button subCK;

	@FXML
	private TextField Sstate;

	@FXML
	private TextField Ssex;

	@FXML
	private TextField Sphone;

	@FXML
	private Button changeCK;

	@FXML
	private Button deleteCK;

	@FXML
	private TextField Sname;

	@FXML
	private Label Sid;

	@FXML
	private Button retCK;
	// 数据库连接
	private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private MyDBConnection myDB2 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn2 = myDB1.getMyConnection();
	private Statement stmt1;
	private Statement stmt2;

	// 修改学生信息
	@FXML
	void changeClick(ActionEvent event) throws SQLException {
		Sname.setEditable(true);
		Ssex.setEditable(true);
		Sschool.setEditable(true);
		Sstate.setEditable(true);
		Sphone.setEditable(true);
	}

	// 删除学生信息
	@FXML
	void deleteClick(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("删除用户信息");
		alert.setHeaderText("将删除学生信息及与之相关的作答记录" + "\n" + "确定要删除吗？删除后信息将不可恢复");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String sql = "delete * from student,shistory where Sid='" + Sid.getText() + "'";
			stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql);
			stmt1.close();
			myDB1.closeMyConnection();// 关闭连接
		} else {
			alert.close();
		}

	}

	public Label getAdname() {
		return Adname;
	}

	// 返回管理员界面
	@FXML
	void retClick(ActionEvent event) throws SQLException {
		Stage adstage = new Stage();
		Admmenu admenu = new Admmenu(adstage, Adname.getText(),Aid);
		Scene adScene = new Scene(admenu);
		adstage.setScene(adScene);
		adstage.setTitle("管理员界面");
		oldstage.hide();
		adstage.show();
	}

	public void setAdname(String Aname,String id) {
		Adname.setText(Aname);
		Aid=id;
	}

	// 建立舞台
	public void setOldStage(Stage oldstage) {
	this.oldstage=oldstage;
	}

	// 将查询的学生账号相关信息显示在页面上
	public void setstuText(String id, String name, String sex, String school, String state, String phone,
			String Adname) {
		Sid.setText(id);
		Sname.setText(name);
		Ssex.setText(sex);
		Sschool.setText(school);
		Sstate.setText(state);
		Sphone.setText(phone);
		Sname.setEditable(false);
		Ssex.setEditable(false);
		Sschool.setEditable(false);
		Sstate.setEditable(false);
		Sphone.setEditable(false);

	}

	// 提交修改信息
	@FXML
	void subClick(ActionEvent event) throws SQLException {
		if (Sname.isEditable() == false) {
			Alert warning = new Alert(AlertType.WARNING, "您暂未修改用户信息，" + "\n" + "请点击修改信息按钮进行修改！");
			warning.setTitle("用户信息未修改 ");
			warning.show();
		} else {
			String sql = "update student set Sname='" + Sname.getText() + "',Ssex='" + Ssex.getText() + "',Sschool='"
					+ Sschool.getText() + "',state='" + Sstate.getText() + "',Sphone='" + Sphone.getText()
					+ "' where Sid='" + Sid.getText() + "'";
			stmt2 = conn2.createStatement();
			stmt2.executeUpdate(sql);
			stmt2.close();
			myDB2.closeMyConnection();
			Alert warning = new Alert(AlertType.INFORMATION, "用户信息修改成功！");
			warning.setTitle("用户信息修改成功 ");
			warning.show();
		}

	}

}
