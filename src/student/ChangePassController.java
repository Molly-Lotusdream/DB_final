package student;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChangePassController {
private Stage oldStage =null;
private String Sid=null;
private String Ssex=null;
    @FXML
    private PasswordField Oldpassword;

    @FXML
    private Button ChangeCK;

    @FXML
    private Button checkCK;


    public void setText(String Aname,String id,String sex) {
    	name.setText(Aname);
		Sid=id;
		Ssex=sex;
	}

	@FXML
    private Label name;

    @FXML
    private PasswordField Newpassword;

    @FXML
    private Button returnCK;

  //数据库连接
    private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private MyDBConnection myDB2 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private Connection conn2 = myDB2.getMyConnection();


	//返回学生主界面
    @FXML
    void returnClick(ActionEvent event) throws SQLException {
    	Stage adstage = new Stage();
		StudentMain admenu = new StudentMain(adstage,name.getText(),Sid,Ssex);
		Scene adScene = new Scene(admenu);
		adstage.setScene(adScene);
		adstage.setTitle("学生主界面");
		adstage.show();
		oldStage.hide();
    }

    @FXML
    void ChangeClick(ActionEvent event) {//修改框可用
    	Oldpassword.setDisable(false);
    	Newpassword.setDisable(false);
    }

    @FXML
    void checkClick(ActionEvent event) throws SQLException {//修改密码
    	if (Oldpassword.getText().equals("")||Newpassword.getText().equals("")) {
			Alert warning = new Alert(AlertType.WARNING, "请输入旧或新密码");
			warning.setTitle("旧或新密码未输入");
			warning.show();
    	}else{
			if(Oldpassword.getText().matches("[\\da-zA-Z]+") && Oldpassword.getText().length() < 20 &&
				Newpassword.getText().matches("[\\da-zA-Z]+") && Newpassword.getText().length() < 20){
				String sql = "select Spassword from student where Sid="+Sid;
				ResultSet rset1;
			Statement stmt1 = conn1.createStatement();
				rset1 = stmt1.executeQuery(sql);

				//查询到旧密码
		    	if(rset1.next()){
		    		//旧密码与数据库中的一致
		    		if(Oldpassword.getText().equals(rset1.getString("Spassword"))){
                       String sql2="update student set Spassword="+Newpassword.getText()+" where Sid='"+Sid+"'";
                      Statement stmt2 = conn2.createStatement();
                       stmt2.executeUpdate(sql2);
           			stmt1.close();
           			stmt2.close();
           			myDB1.closeMyConnection();
           			myDB2.closeMyConnection();
           			// 关闭连接
       				Alert warning = new Alert(AlertType.WARNING, "已成功修改密码!");
					warning.setTitle("修改密码");
					warning.show();
					Oldpassword.setDisable(true);
			    	Newpassword.setDisable(true);
		    	}else{
		    		Alert warning = new Alert(AlertType.WARNING, "请输入正确的旧密码");
					warning.setTitle("旧密码不正确");
					warning.show();
				}
		    	}
		    }else{
		    	Alert warning = new Alert(AlertType.WARNING, "新/旧密码格式不正确，密码需少于20个字符");
				warning.setTitle("格式错误");
				warning.show();
		    }
			}
    }


	public void setOldStage(Stage stage) {//建立舞台
    	oldStage = stage;
    	Oldpassword.setDisable(true);
    	Newpassword.setDisable(true);
    }
}