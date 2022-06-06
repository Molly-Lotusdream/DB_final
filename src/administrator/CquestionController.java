package administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CquestionController {
	private Stage oldStage = null;
	private String Adname = null;
	private String Aid = null;
	private int m, n,l,r;
	private final ObservableList<Questiontable> cellData = FXCollections.observableArrayList();

	@FXML
	private TableColumn<Questiontable, String> qanswer1;


	@FXML
	private TableColumn<Questiontable, String> qanswer2;

	@FXML
	private Label Aname;

	@FXML
	private Button MchoiceCK;

	@FXML
	private Button returnCK;

	@FXML
	private Button BlankCK;

	@FXML
	private TableColumn<Questiontable, String> qcontent2;

	@FXML
	private TableColumn<Questiontable, String> qA;

	@FXML
	private ScrollPane pane1;

	@FXML
	private TableColumn<Questiontable, Integer> qid1;

	@FXML
	private TableColumn<Questiontable, String> qB;

	@FXML
	private TableView<Questiontable> selectQ;

	@FXML
	private TableColumn<Questiontable, String> qC;

	@FXML
	private ScrollPane pane2;

	@FXML
	private TableColumn<Questiontable, Integer> qid2;

	@FXML
	private TableColumn<Questiontable, String> qD;

	@FXML
	private TableColumn<Questiontable, String> qcontent1;

	@FXML
	private Button selectionCK;

	@FXML
	private Button changeCK;//

	@FXML
	private Button addCK;//

	@FXML
	private Button deleteCK;//

	@FXML
	private Button JudgeCK;

	@FXML
	private TableView<Questiontable> otherQ;

	 //数据库连接
    private String DBDriver = "com.mysql.cj.jdbc.Driver";
	private String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
	private String DBUser = "root";
	private String DBPass = "123456";
	private MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
	private Connection conn1 = myDB1.getMyConnection();
	private Statement stmt1;

	@FXML
	void returnClick(ActionEvent event) throws SQLException {// 返回管理员界面
		Stage stage = new Stage();
		Scene scene = new Scene(new Admmenu(stage, Adname,Aid));
		stage.setScene(scene);
		stage.setTitle("管理员界面");
		stage.show();
		oldStage.hide();
		stmt1.close();
		myDB1.closeMyConnection();// 关闭连接
	}

	@FXML
	void changeClick(ActionEvent event) {// 点击后去修改题目
		if(selectQ.getSelectionModel().getSelectedIndex()==-1&&otherQ.getSelectionModel().getSelectedIndex()==-1){
			Alert warning = new Alert(AlertType.INFORMATION, "请选择要修改的题目！");
			warning.setTitle("未选中题目");
			warning.show();
		}else{
		int qqid=0;
		String qqcontent=null,qqa=null,qqb=null,qqc=null,qqd=null,qqanswer=null;
		l=2;
		if(m==1){
			qqid=selectQ.getSelectionModel().getSelectedItem().getQid();
			qqcontent=selectQ.getSelectionModel().getSelectedItem().getQcontent();
			qqa=selectQ.getSelectionModel().getSelectedItem().getQA();
			qqb=selectQ.getSelectionModel().getSelectedItem().getQB();
			qqc=selectQ.getSelectionModel().getSelectedItem().getQC();
			qqd=selectQ.getSelectionModel().getSelectedItem().getQD();
			qqanswer=selectQ.getSelectionModel().getSelectedItem().getAnswer();
		}else{
			qqid=otherQ.getSelectionModel().getSelectedItem().getQid();
			qqcontent=otherQ.getSelectionModel().getSelectedItem().getQcontent();
			qqa=otherQ.getSelectionModel().getSelectedItem().getQA();
			qqb=otherQ.getSelectionModel().getSelectedItem().getQB();
			qqc=otherQ.getSelectionModel().getSelectedItem().getQC();
			qqd=otherQ.getSelectionModel().getSelectedItem().getQD();
			qqanswer=otherQ.getSelectionModel().getSelectedItem().getAnswer();
		}
		Stage stage = new Stage();
		Scene scene = new Scene(new ChangeQ(stage, Adname,m,n,l,Aid,qqid,qqcontent,qqa,qqb,qqc,qqd,qqanswer));
		stage.setScene(scene);
		stage.setTitle("管理员修改题目界面");
		stage.show();
		oldStage.hide();
		}
	}//

	@FXML
	void addClick(ActionEvent event) {// 点击后去添加题目
		l=1;
		Stage stage = new Stage();
		Scene scene = new Scene(new ChangeQ(stage, Adname,m,n,l,Aid,0,"","","","","",""));
		stage.setScene(scene);
		stage.setTitle("管理员添加题目界面");
		stage.show();
		oldStage.hide();
	}//

	@FXML
	void deleteClick(ActionEvent event) throws SQLException {// 点击后去删除题目
		if(selectQ.getSelectionModel().getSelectedIndex()==-1&&otherQ.getSelectionModel().getSelectedIndex()==-1){
			Alert warning = new Alert(AlertType.INFORMATION, "请选择要删除的题目！");
			warning.setTitle("未选中题目");
			warning.show();
		}else{
		if(m==1){
			r=selectQ.getSelectionModel().getSelectedItem().getQid();
		}else{
			r=otherQ.getSelectionModel().getSelectedItem().getQid();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("删除题目信息");
		alert.setHeaderText("将删除所填题号题目的所有信息" + "\n" + "确定要删除吗？删除后信息将不可恢复");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String sql;
			String sql1;
			if(m==1){
				if(n==1){
					sql1 = "delete from s_lastpractice where LastChose='"+ r+"'";
					sql = "delete  from sselection where q_id='" + r+"'";

				}else{
					sql1 = "delete from s_lastpractice where LastMchose='"+ r+"'";
					sql = "delete  from mchoices where q_id='" + r+"'";
				}
			}else{
				if(n==1){
					sql1 = "delete from s_lastpractice where LastJudge='"+ r+"'";
					sql = "delete  from judgment where q_id='" + r+"'";
				}else{
					sql1 = "delete from s_lastpractice where LastBlanks='"+ r+"'";
					sql = "delete  from blanks where q_id='" + r+"'";
				}
			}
			stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql1);
			stmt1.executeUpdate(sql);



		} else {
			alert.close();
		}
		cellData.clear();
		showAllquestion(m, n);
//		stmt1.close();
//		myDB1.closeMyConnection();// 关闭连接
		init();
		}
	}//

	@FXML
	void selectionClick(ActionEvent event) {// 点击后表格内容显示为单选题的内容
		pane1.setVisible(true);
		pane2.setVisible(false);
		cellData.clear();
		m = 1;
		n = 1;
		showAllquestion(m, n);
	}

	@FXML
	void MchoiceClick(ActionEvent event) {// 点击后表格内容显示为多选题的内容
		pane1.setVisible(true);
		pane2.setVisible(false);
		cellData.clear();
		m = 1;
		n = 2;
		showAllquestion(m, n);
	}

	@FXML
	void JudgeClick(ActionEvent event) {// 点击后表格内容显示为判断题的内容
		pane1.setVisible(false);
		pane2.setVisible(true);
		cellData.clear();
		m = 2;
		n = 1;
		showAllquestion(m, n);
	}

	@FXML
	void BankClick(ActionEvent event) {// 点击后表格内容显示为填空题的内容
		pane1.setVisible(false);
		pane2.setVisible(true);
		cellData.clear();
		m = 2;
		n = 2;
		showAllquestion(m, n);
	}

	public void showAllquestion(int m1, int m2) {
		List<Questiontable> allquestion = Questiontable.getAllquestion(m1, m2);
		for (Questiontable question : allquestion) {
			cellData.add(question);
		}

		if (m == 1) {
			qid1.setCellValueFactory(new PropertyValueFactory<Questiontable, Integer>("qid"));
			qcontent1.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qcontent"));
			qA.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qA"));
			qB.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qB"));
			qC.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qC"));
			qD.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qD"));
			qanswer1.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("answer"));

			selectQ.setItems(cellData);

		} else {
			qid2.setCellValueFactory(new PropertyValueFactory<Questiontable, Integer>("qid"));
			qcontent2.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("qcontent"));
			qanswer2.setCellValueFactory(new PropertyValueFactory<Questiontable, String>("answer"));

			otherQ.setItems(cellData);
		}
	}

	public void init() {// 初始化让有选项的表格可见
		pane1.setVisible(true);
		pane2.setVisible(false);
	}

	public void setnameText(String name,String id) {
		Adname = name;
		Aid=id;
	}

	public void setOldStage(Stage stage) {// 建立舞台
		oldStage = stage;
	}
}