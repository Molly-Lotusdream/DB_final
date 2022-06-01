package exercise;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise extends GridPane{

  public Exercise(Stage stage,String Sid,String Sname,String Ssex) {
    try {

      FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource("exercise.fxml"));
      this.getChildren().add(fxmlloader.load());

      // 获得控制器对象,并把数据传给控制器对象
      ((ExerciseController)fxmlloader.getController()).setSex(Ssex);
      ((ExerciseController)fxmlloader.getController()).setOldStage(stage);
      ((ExerciseController)fxmlloader.getController()).getSid(Sid,Sname);
      ((ExerciseController)fxmlloader.getController()).init();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}