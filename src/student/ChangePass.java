package student;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChangePass extends GridPane{

  public ChangePass(Stage stage,String id,String name,String sex) {
    try {

      FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("changePass.fxml"));
      this.getChildren().add(fxmlloader.load());

      ((ChangePassController)fxmlloader.getController()).setOldStage(stage);
	  ((ChangePassController)fxmlloader.getController()).setText(id, name, sex);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}