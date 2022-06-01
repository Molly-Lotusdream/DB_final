package administrator;

import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdmgradeController {
    //变量定义
	private Stage oldStage=null;
    private String Adname=null;
    private String Aid=null;
    private final ObservableList<Stugrade> cellData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Stugrade, String> snamecol;

    @FXML
    private Button sortCK;

    @FXML
    private TableView<Stugrade> gradeview;

    @FXML
    private TableColumn<Stugrade, String> sschoolcol;

    @FXML
    private TableColumn<Stugrade, Integer> sgradecol;

    @FXML
    private Button returnCK;

    @FXML
    private TableColumn<Stugrade, String> sidcol;

    @FXML
    void returnClick(ActionEvent event) throws SQLException {//返回管理员界面
    	Stage stage=new Stage();
    	Scene scene = new Scene(new Admmenu(stage,Adname,Aid));
		stage.setScene(scene);
		stage.setTitle("管理员界面");
		stage.show();
		oldStage.hide();
    }

    @FXML
    void sortClick(ActionEvent event) {
         cellData.clear();
         showAllgrade(0);
    }
    //显示所有学生成绩
    public void showAllgrade(int t){
    	List<Stugrade> allgrade=Stugrade.getAllStugrade(t);
    	for(Stugrade grade : allgrade){
    		cellData.add(grade);
    	}

    	sidcol.setCellValueFactory(new PropertyValueFactory<Stugrade, String>("sid"));
    	snamecol.setCellValueFactory(new PropertyValueFactory<Stugrade, String>("sname"));
    	sschoolcol.setCellValueFactory(new PropertyValueFactory<Stugrade, String>("sschool"));
    	sgradecol.setCellValueFactory(new PropertyValueFactory<Stugrade, Integer>("sgrade"));

    	gradeview.setItems(cellData);
    }
    public void setnameText(String name,String id){
    	Adname=name;
    	Aid=id;
    }

    public void setOldStage(Stage stage) {//建立舞台
    	oldStage = stage;
    }
}
