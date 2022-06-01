package student;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentMain extends GridPane{

  public StudentMain(Stage stage,String name,String ID,String sex) throws SQLException {
    try {

      FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("studentMain.fxml"));
      this.getChildren().add(fxmlloader.load());

      ((StudentMainController)fxmlloader.getController()).setName(name);
      ((StudentMainController)fxmlloader.getController()).setID(ID);
      ((StudentMainController)fxmlloader.getController()).setView(sex);
      ((StudentMainController)fxmlloader.getController()).setOldStage(stage);
      ((StudentMainController)fxmlloader.getController()).setGrade(ID);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}