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
    private int p,t,w,qqid;
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
    private Button checkCK;//

    @FXML
    private TextField qid;

    @FXML
    private Button returnOK;

    @FXML
    private Label Aname;

    @FXML
    private Label question;
    //数据库连接
    private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private Statement stmt1;

    @FXML
    void checkClick(ActionEvent event) throws SQLException {//确认修改题目并提交至数据库
    	String sql = null;
    	if(w==1){//添加题目
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("添加题目信息");
    		alert.setHeaderText("添加不同类型的题目时请看好注意事项" + "\n");
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
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

    		} else {
    			alert.close();
    		}
    	}else{//修改题目
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("修改题目信息");
    		alert.setHeaderText("将修改你所填的题目信息" + "\n" + "确定要修改吗？修改后信息将不可恢复");
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
                  if(p==1){
                	  if(t==1){
                		  sql="update sselection set q_content='"+qcontent.getText()+"',q_A='"
                		+QA.getText()+"',q_B='"+QB.getText()+"',q_C='"+QC.getText()+"',q_D='"+QD.getText()+"',q_answer='"
                		+answer.getText()+"' where q_id='"+qid.getText()+"'";
                	  }else{
                		  sql="update mchoices set q_content='"+qcontent.getText()+"',q_A='"
                          		+QA.getText()+"',q_B='"+QB.getText()+"',q_C='"+QC.getText()+"',q_D='"+QD.getText()+"',q_answer='"
                          		+answer.getText()+"' where q_id='"+qid.getText()+"'";
                	  }
                  }else{
                	  if(t==1){
                		  sql="update judgment set q_content='"+qcontent.getText()+"',q_answer='"
                		+answer.getText()+"' where q_id='"+qid.getText()+"'";
                	  }else{
                		  sql="update blanks set q_content='"+qcontent.getText()+"',q_answer='"
                          		+answer.getText()+"' where q_id='"+qid.getText()+"'";
                	  }
                  }
    		} else {
    			alert.close();
    		}
    	}
    	stmt1 = conn1.createStatement();
		stmt1.executeUpdate(sql);
		//stmt1.close();
		//myDB1.closeMyConnection();// 关闭连接
    }//

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

    public void setnameText(String name,int m,int n,int l,String id,int qqid,String qqcontent,String qqA,
    		String qqB,String qqC,String qqD,String qqanswer) {
		Adname = name;
		Aid=id;
		p=m;
		t=n;
		w=l;
		if(w==2){
			qid.setText(String.valueOf(qqid));
			qid.setDisable(true);
    		qcontent.setText(qqcontent);
    		QA.setText(qqA);
    		QB.setText(qqB);
    		QC.setText(qqC);
    		QD.setText(qqD);
    		answer.setText(qqanswer);
		}
		if(p==1){
			if(t==1){
				question.setText("单选题");
			}else{
				question.setText("多选题");
			}
		}else{
			QA.setDisable(true);
			QB.setDisable(true);
			QC.setDisable(true);
			QD.setDisable(true);
			if(t==1){
				question.setText("判断题");
			}else{
				question.setText("填空题");
			}
		}
	}

    public void setOldStage(Stage stage) {//建立舞台
    	oldStage = stage;
    }
}

