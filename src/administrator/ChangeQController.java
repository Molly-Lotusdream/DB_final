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

public class ChangeQController {
	//变量定义
    private Stage oldStage = null;
    private String Adname = null;
    private String Aid = null;
    private int p,t;
    @FXML
    private TextField QA;

    @FXML
    private TextField QB;

    @FXML
    private TextField QC;

    @FXML
    private TextField QD;

    @FXML
    private TextField qcontent;

    @FXML
    private TextField answer;

    @FXML
    private Button deleteCK;

    @FXML
    private Button addCK;

    @FXML
    private TextField qid;

    @FXML
    private Button returnOK;
    @FXML
    private Label Aname;
    //数据库连接
    private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private Statement stmt1;

    @FXML
    void deleteClick(ActionEvent event) throws SQLException {//获取题号后去数据库删除相应的题目
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("删除题目信息");
		alert.setHeaderText("将删除所填题号题目的所有信息" + "\n" + "确定要删除吗？删除后信息将不可恢复");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String sql;
			if(p==1){
				if(t==1){
					sql = "delete  from sselection where q_id='" + qid.getText()+"'";
				}else{
					sql = "delete  from mchoices where q_id='" + qid.getText()+"'";
				}
			}else{
				if(t==1){
					sql = "delete  from judgment where q_id='" + qid.getText()+"'";
				}else{
					sql = "delete  from blanks where q_id='" + qid.getText()+"'";
				}
			}
			stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql);
			stmt1.close();
			myDB1.closeMyConnection();// 关闭连接
		} else {
			alert.close();
		}
    }

    @FXML
    void addClick(ActionEvent event) throws SQLException {//获取相关题目信息去数据库添加
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("添加题目信息");
		alert.setHeaderText("添加不同类型的题目时请看好注意事项" + "\n");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String sql;
			if(p==1){
				if(t==1){
					sql = "insert into sselection values ("+qid.getText()+","+qcontent.getText()+","
							+QA.getText()+","+QB.getText()+","+QC.getText()+","+QD.getText()+","+
							answer.getText()+")";
				}else{
					sql = "insert into mchoices values ("+qid.getText()+","+qcontent.getText()+","
							+QA.getText()+","+QB.getText()+","+QC.getText()+","+QD.getText()+","+
							answer.getText()+")";
				}
			}else{
				if(t==1){
					sql = "insert into judgment values ("+qid.getText()+","+qcontent.getText()+","+
							answer.getText()+")";
				}else{
					sql = "insert into blanks values ("+qid.getText()+","+qcontent.getText()+","+
							answer.getText()+")";
				}
			}
			stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql);
			stmt1.close();
			myDB1.closeMyConnection();// 关闭连接
		} else {
			alert.close();
		}
    }

    @FXML
    void returnClick(ActionEvent event) {//返回题库表界面
    	Stage stage=new Stage();
    	Scene scene = new Scene(new Cquestion(stage,Adname,Aid));
    	scene.getStylesheets().add(getClass().getResource("../css/Cquestion.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("题库界面");
		stage.show();
		oldStage.hide();
    }

    public void setnameText(String name,int m,int n,String id) {
		Adname = name;
		Aid=id;
		p=m;
		t=n;
		if(p==1){
			QA.setDisable(false);
			QB.setDisable(false);
			QC.setDisable(false);
			QD.setDisable(false);
		}else{
			QA.setDisable(true);
			QB.setDisable(true);
			QC.setDisable(true);
			QD.setDisable(true);
		}
	}

    public void setOldStage(Stage stage) {//建立舞台
    	oldStage = stage;
    }
}

