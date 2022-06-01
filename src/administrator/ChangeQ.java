package administrator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeQ extends Pane{

	public ChangeQ(Stage stage,String name,int m,int n,String id) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ChangeQ.fxml"));
			this.getChildren().add(fxmlloader.load());

			// 获得控制器对象,并把数据传给控制器对象
			((ChangeQController)fxmlloader.getController()).setOldStage(stage);
			((ChangeQController)fxmlloader.getController()).setnameText(name,m,n,id);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
