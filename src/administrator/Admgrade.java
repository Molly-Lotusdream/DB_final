package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Admgrade extends Pane{

	public Admgrade(Stage stage,String name,String id) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Admgrade.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((AdmgradeController)fxmlloader.getController()).setOldStage(stage);
			((AdmgradeController)fxmlloader.getController()).showAllgrade(1);
			((AdmgradeController)fxmlloader.getController()).setnameText(name,id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}