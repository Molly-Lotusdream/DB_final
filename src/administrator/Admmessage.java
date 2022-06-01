package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Admmessage extends Pane{

	public Admmessage(Stage stage,String id,String name,String sex,String phone) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Admmessage.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((AdmmessageController)fxmlloader.getController()).setOldStage(stage);
			((AdmmessageController)fxmlloader.getController()).setText(id, name, sex, phone);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
