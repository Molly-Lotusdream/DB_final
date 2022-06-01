package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Cquestion extends Pane{

	public Cquestion(Stage stage,String name,String id) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Cquestion.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((CquestionController)fxmlloader.getController()).setOldStage(stage);
			((CquestionController)fxmlloader.getController()).init();
			((CquestionController)fxmlloader.getController()).setnameText(name,id);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
