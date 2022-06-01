package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Register extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private int WINDOWS_WIDTH = 800;
	private int WINDOWS_HEIGHT = 500;
	private VBox root = new VBox(10);
	private HBox Hpane1 = new HBox(35);
	private HBox Hpane2 = new HBox(35);
	private HBox Hpane3 = new HBox(25);
	private HBox Hpane4 = new HBox(35);
	private HBox Hpane5 = new HBox(35);
	private HBox Hpane6 = new HBox(35);
	private HBox Hpane7 = new HBox(2);
	private HBox Hpane8 = new HBox(25);
	private Label id = new Label("ID  ");
	private Label name = new Label("姓名");
	private Label sex = new Label("性别           ");
	private Label school = new Label("学校");
	private Label tel = new Label("电话");
	private Label p1 = new Label("密码");
	private Label p2 = new Label("确定密码 ");
	private TextField idInput = new TextField();
	private TextField nameInput = new TextField();
	private TextField schoolInput = new TextField();
	private TextField telInput = new TextField();
	private TextField p1Input = new TextField();
	private TextField p2Input = new TextField();
	private ToggleGroup tg1 = new ToggleGroup();
	private RadioButton male = new RadioButton("男");
	private RadioButton female = new RadioButton("女");
	private Button register = new Button("注册并返回");
	private Scene scene = new Scene(root, WINDOWS_WIDTH, WINDOWS_HEIGHT);

	// 界面搭建
	@Override
	public void start(Stage registerStage) throws Exception {
		root.setAlignment(Pos.CENTER);
		Hpane1.setAlignment(Pos.CENTER);
		Hpane2.setAlignment(Pos.CENTER);
		Hpane3.setAlignment(Pos.CENTER);
		Hpane4.setAlignment(Pos.CENTER);
		Hpane5.setAlignment(Pos.CENTER);
		Hpane6.setAlignment(Pos.CENTER);
		Hpane7.setAlignment(Pos.CENTER);
		Hpane8.setAlignment(Pos.CENTER);
		Hpane1.getChildren().addAll(id, idInput);
		Hpane2.getChildren().addAll(name, nameInput);
		Hpane3.getChildren().addAll(sex, male, female);
		Hpane4.getChildren().addAll(school, schoolInput);
		Hpane5.getChildren().addAll(tel, telInput);
		Hpane6.getChildren().addAll(p1, p1Input);
		Hpane7.getChildren().addAll(p2, p2Input);
		Hpane8.getChildren().addAll(register);
		root.getChildren().addAll(Hpane1, Hpane2, Hpane3, Hpane4, Hpane5, Hpane6, Hpane7, Hpane8);
		male.setToggleGroup(tg1);
		female.setToggleGroup(tg1);

		// 数据库连接
		String DBDriver = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
		String DBUser = "root";
		String DBPass = "123456";
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();
		MyDBConnection myDB1 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn1 = myDB1.getMyConnection();


		// 事件处理
		register.setOnMouseClicked(e -> {
			// 信息输入不完整
			if (idInput.getText().equals("") || nameInput.getText().equals("") || telInput.getText().equals("")
					|| (male.isSelected() == false && female.isSelected() == false)) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("信息填写不完整");
				alert.setHeaderText("您的用户信息尚未填写完整！确定要返回登录界面吗？" + "\n" + "返回后您刚刚填写的信息不会保留");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					alert.close();
					Login loginStage = new Login();
					try {
						loginStage.start(new Stage());
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					registerStage.hide();
				} else {
					alert.close();
				}
			}
			// 信息输入完整
			else {
				//账号重复
				try {
					Statement stmt1 = conn1.createStatement();
					String selectID = "select * from student where Sid="+idInput.getText();
					ResultSet rset1;
					rset1 = stmt1.executeQuery(selectID);


			if(rset1.next()){
				Alert warning = new Alert(AlertType.WARNING, "该账号已被注册，请修改账号信息后重新提交注册！");
				warning.setTitle("账号重复");
				warning.show();
			}else{
				// 两次密码输入不一致
				if (!idInput.getText().equals("") && idInput.getText().length() <= 8
						&& idInput.getText().matches("[\\da-zA-Z]+") && !nameInput.getText().equals("")
						&& nameInput.getText().length() <= 12
						&& (male.isSelected() == true || female.isSelected() == true)
						&& !schoolInput.getText().equals("") && schoolInput.getText().length() <= 20
						&& !telInput.getText().equals("") && telInput.getText().length() <= 15
						&& !p1Input.getText().equals("") && p1Input.getText().length() <= 15
						&& !p1Input.getText().equals(p2Input.getText()) && p1Input.getText().matches("[\\da-zA-Z]+")) {
					Alert warning = new Alert(AlertType.WARNING, "两次输入密码不一致！");
					warning.setTitle("密码错误");
					warning.show();
				}
				// 信息填写格式正确
				else if (!idInput.getText().equals("") && idInput.getText().length() <= 8
						&& idInput.getText().matches("[\\da-zA-Z]+") && !nameInput.getText().equals("")
						&& nameInput.getText().length() <= 12
						&& (male.isSelected() == true || female.isSelected() == true)
						&& !schoolInput.getText().equals("") && schoolInput.getText().length() <= 20
						&& !telInput.getText().equals("") && telInput.getText().length() <= 15
						&& !p1Input.getText().equals("") && p1Input.getText().length() <= 15
						&& p1Input.getText().equals(p2Input.getText()) && p1Input.getText().matches("[\\da-zA-Z]+")) {
					try {
						Statement stmt = conn.createStatement();
						String Sex = new String();
						if (male.isSelected() == true) {
							Sex = "M";
						} else {
							Sex = "F";
						}
						String sql = "insert into student(Sid,Sname,Ssex,Sphone,Sschool,Spassword,state) values('"
								+ idInput.getText() + "','" + nameInput.getText() + "','" + Sex + "','"
								+ telInput.getText() + "','" + schoolInput.getText() + "','" + p1Input.getText() + "','正常')";
						stmt.executeUpdate(sql);
						Alert warning = new Alert(AlertType.INFORMATION, "恭喜您注册成功！");
						warning.setTitle("注册成功");
						warning.show();
						stmt.close();
						myDB.closeMyConnection();// 关闭连接
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}

				}
				// 信息填写格式有误
				else {
					Alert warning = new Alert(AlertType.WARNING, "注册信息填写格式有误，在填写时，请注意：" + "\n" + "账号密码均为字母或数字；" + "\n"
							+ "账号长度不超过8位，密码长度不超过15位；" + "\n" + "电话长度不超过15位；");
					warning.setTitle("注册信息填写格式有误！");
					warning.show();
				}
			}
				} catch (SQLException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}

			}

		});
		root.getStyleClass().add("root");
		scene.getStylesheets().add(Register.class.getResource("../css/login.css").toExternalForm());
		registerStage.setScene(scene);
		registerStage.setTitle("网络安全知识在线测试系统");
		registerStage.setResizable(false);
//		registerStage.initStyle(StageStyle.UTILITY);
		root.setId("root");
		id.setId("font");
		name.setId("font");
		sex.setId("font");
		male.setId("font");
		female.setId("font");
		school.setId("font");
		tel.setId("font");
		register.setId("font");
		registerStage.show();
	}
}
