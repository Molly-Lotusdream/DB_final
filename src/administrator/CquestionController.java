package administrator;

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CquestionController {
	private Stage oldStage = null;
	private String Adname = null;
	private String Aid = null;
	private int m, n;
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
	private Button changeCK;

	@FXML
	private Button JudgeCK;

	@FXML
	private TableView<Questiontable> otherQ;

	@FXML
	void returnClick(ActionEvent event) throws SQLException {// 返回管理员界面
		Stage stage = new Stage();
		Scene scene = new Scene(new Admmenu(stage, Adname,Aid));
		stage.setScene(scene);
		stage.setTitle("管理员界面");
		stage.show();
		oldStage.hide();
	}

	@FXML
	void changeClick(ActionEvent event) {// 进入修改题目界面
		Stage stage = new Stage();
		Scene scene = new Scene(new ChangeQ(stage, Adname,m,n,Aid));
		stage.setScene(scene);
		stage.setTitle("管理员增加删除题目界面");
		stage.show();
		oldStage.hide();
	}

	@FXML
	void selectionClick(ActionEvent event) {// 点击后表格内容显示为单选题的内容
		cellData.clear();
		m = 1;
		n = 1;
		showAllquestion(m, n);
	}

	@FXML
	void MchoiceClick(ActionEvent event) {// 点击后表格内容显示为多选题的内容
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