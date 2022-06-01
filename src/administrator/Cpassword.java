package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Cpassword extends Pane{

	public Cpassword(Stage stage,String name,String id) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Cpassword.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((CpasswordController)fxmlloader.getController()).setOldStage(stage);
			((CpasswordController)fxmlloader.getController()).setName(name,id);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
