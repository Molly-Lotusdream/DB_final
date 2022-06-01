package administrator;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Admmenu extends Pane{

	public Admmenu(Stage stage,String name,String id) throws SQLException {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Admmenu.fxml"));
			this.getChildren().add(fxmlloader.load());
              // 获得控制器对象,并把数据传给控制器对象
			((AdmmenuController)fxmlloader.getController()).setAname(name,id);
			((AdmmenuController)fxmlloader.getController()).setOldStage(stage);
			((AdmmenuController)fxmlloader.getController()).setTestCK();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
