package administrator;

import java.sql.Connection;
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
import javafx.stage.Stage;

public class AdmmessageController {
	//变量定义
    private Stage oldStage = null;
    private String AAsex;
    @FXML
    private TextField Asex;

    @FXML
    private TextField Aname;

    @FXML
    private TextField Aphone;

    @FXML
    private Button returnCK;

    @FXML
    private Label Aid;

    @FXML
    private Button CmessageCK;

    @FXML
    private Button checkCK;
  //数据库连接
    private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private Statement stmt1;

//修改个人信息
    @FXML
    void CmessageClick(ActionEvent event) {
    	Aname.setDisable(false);
    	Asex.setDisable(false);
    	Aphone.setDisable(false);
    }
//返回管理员界面
    @FXML
    void returnClick(ActionEvent event) throws SQLException {
    	Stage adstage = new Stage();
		Admmenu admenu = new Admmenu(adstage, Aname.getText(),Aid.getText());
		Scene adScene = new Scene(admenu);
		adstage.setScene(adScene);
		adstage.setTitle("管理员界面");
		adstage.show();
		oldStage.hide();
    }

    @FXML
    void checkClick(ActionEvent event) throws SQLException {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("修改个人信息");
		alert.setHeaderText("您的个人信息将被修改");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if(Asex.getText().equals("男")){
                    AAsex="M";
			}else{
				AAsex="F";
			}
			 String   sql = "update administrator set Aname='"+Aname.getText()+"',"+"Asex='"+AAsex+"',"+
		     "Aphone='"+Aphone.getText()+"' where Aid='"+Aid.getText()+"'";
			stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql);
			stmt1.close();
			myDB1.closeMyConnection();// 关闭连接
		} else {
			alert.close();
		}
		Aname.setDisable(true);
    	Asex.setDisable(true);
    	Aphone.setDisable(true);
    }

    public void setOldStage(Stage stage) {//建立舞台
    	oldStage = stage;
    }
    //获取信息传到界面
    public void setText(String id,String name,String sex,String phone){
    	Aid.setText(id);
    	Aname.setText(name);
    	if(sex.equals("M")){
    		Asex.setText("男");
    	}
    	else{
    		Asex.setText("女");
    	}
    	Aphone.setText(phone);
    	Aname.setDisable(true);
    	Asex.setDisable(true);
    	Aphone.setDisable(true);
    }

}




