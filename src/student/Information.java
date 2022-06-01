package student;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Information extends GridPane{

  public Information(Stage stage,String name) throws SQLException {
    try {

      FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("information.fxml"));
      this.getChildren().add(fxmlloader.load());
      ((InformationController)fxmlloader.getController()).setInfo(name);
      ((InformationController)fxmlloader.getController()).setOldStage(stage);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}