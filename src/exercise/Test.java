package exercise;

import java.io.IOException;
import java.sql.SQLException;

import com.sun.javafx.application.PlatformImpl;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SuppressWarnings({ "unused", "restriction" })
public class Test extends GridPane{

  public Test(Stage stage,String Sid,String Sname,String Ssex,int flag) throws InterruptedException, SQLException {
    try {
      FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("test2.fxml"));
      this.getChildren().add(fxmlloader.load());

      // 获得控制器对象,并把数据传给控制器对象
      ((TestController)fxmlloader.getController()).setOldStage(stage);
      ((TestController)fxmlloader.getController()).getSid(Sid,Sname,flag);
      ((TestController)fxmlloader.getController()).Init();
      ((TestController)fxmlloader.getController()).setSex(Ssex);
      ((TestController)fxmlloader.getController()).Clock();
      ((TestController)fxmlloader.getController()).timelabel();



    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}