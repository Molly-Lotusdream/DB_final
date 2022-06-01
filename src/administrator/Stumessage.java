package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Stumessage extends Pane{

	public Stumessage(Stage stage,String id,String name,String sex,String school,String state,String phone,String Adname,String aid) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Stumessage.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((StumessageController)fxmlloader.getController()).setOldStage(stage);
			((StumessageController)fxmlloader.getController()).setstuText(id,name,sex,school,state,phone,Adname);
			((StumessageController)fxmlloader.getController()).setAdname(Adname,aid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
