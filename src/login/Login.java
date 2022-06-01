package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import administrator.Admmenu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import student.StudentMain;

public class Login extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	private int WINDOWS_WIDTH = 800;
	private int WINDOWS_HEIGHT = 500;
	private VBox root = new VBox(30);
	private HBox Hpane1 = new HBox(25);
	private HBox Hpane2 = new HBox(25);
	private HBox Hpane3 = new HBox(25);
	private HBox Hpane4 = new HBox(25);
	private HBox Hpane5 = new HBox(25);
	private Label title = new Label("欢迎登录网络安全知识在线测试系统！");
	private Label username = new Label("账号");
	private Label password = new Label("密码");
	private TextField userInput = new TextField();
	private TextField passwordInput = new TextField();
	private ToggleGroup tg = new ToggleGroup();
	private RadioButton ad = new RadioButton("管理员");
	private RadioButton stu = new RadioButton("学生");
	private Button login = new Button("登录");
	private Button reinput = new Button("重新输入");
	private Button register = new Button("注册");

	private Scene scene = new Scene(root, WINDOWS_WIDTH, WINDOWS_HEIGHT);

	@Override
	public void start(Stage loginStage) throws Exception {
		// 界面搭建
		root.setAlignment(Pos.CENTER);
		Hpane1.setAlignment(Pos.CENTER);
		Hpane2.setAlignment(Pos.CENTER);
		Hpane3.setAlignment(Pos.CENTER);
		Hpane4.setAlignment(Pos.CENTER);
		Hpane5.setAlignment(Pos.CENTER);
		Hpane1.getChildren().addAll(title);
		Hpane2.getChildren().addAll(username, userInput);
		Hpane3.getChildren().addAll(password, passwordInput);
		Hpane4.getChildren().addAll(ad, stu);
		Hpane5.getChildren().addAll(login, reinput, register);
		root.getChildren().addAll(Hpane1, Hpane2, Hpane3, Hpane4, Hpane5);
		ad.setToggleGroup(tg);
		stu.setToggleGroup(tg);
		root.getStyleClass().add("root");
		// 数据库连接
		String DBDriver = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
		String DBUser = "root";
		String DBPass = "123456";
		MyDBConnection myDB2 = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		@SuppressWarnings("unused")
		Connection conn2 = myDB2.getMyConnection();
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();

		// 重新输入按钮，点击清空文本框输入内容及身份选择
		reinput.setOnMouseClicked(e -> {
			userInput.setText("");
			passwordInput.setText("");
			ad.setSelected(false);
			stu.setSelected(false);
		});
		// 注册按钮，点击跳转至注册界面
		register.setOnMouseClicked(e -> {
			Register registerStage = new Register();
			try {
				registerStage.start(new Stage());
				loginStage.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		// 登录按钮
		login.setOnMouseClicked(e -> {
			// 首先保证输入非空
			if (userInput.getText().equals("") || passwordInput.getText().equals("")
					|| (ad.isSelected() == false && stu.isSelected() == false)) {
				Alert warning = new Alert(AlertType.WARNING, "请将账号密码输入完整！");
				warning.setTitle("账号密码未输入");
				warning.show();
			}
			// 输入格式符合要求
			if (userInput.getText().matches("[\\da-zA-Z]+") && userInput.getText().length() < 20
					&& passwordInput.getText().matches("[\\da-zA-Z]+") && passwordInput.getText().length() < 20) {
				// 进行查询对比判断是否可以登录（管理员界面）
				if (ad.isSelected() == true) {
					String sql = "select Apassword,Aname from administrator where Aid=" + userInput.getText();
					ResultSet rset2;

					try {
						Statement stmt2;
						stmt2 = conn.createStatement();
						rset2 = stmt2.executeQuery(sql);
						if (rset2.next() && rset2.getString("Apassword").equals(passwordInput.getText())) {
							// 允许管理员登录至管理员界面
							String Aname = rset2.getString("Aname");
							Stage Adstage = new Stage();
							Admmenu admmenu = new Admmenu(Adstage, Aname, userInput.getText());
							Scene scene = new Scene(admmenu);
							Adstage.setScene(scene);
							Adstage.setTitle("管理员界面");
							Adstage.show();
							loginStage.hide();
							// 登录成功后再关闭查询与数据库连接
							stmt2.close();
							myDB2.closeMyConnection();
						} else {
							Alert warning = new Alert(AlertType.WARNING, "账号密码输入错误或未注册！" + "\n" + "请检查您的账号密码是否输入有误！");
							warning.setTitle("账号密码输入错误");
							warning.show();
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (Throwable e1) {

						e1.printStackTrace();
					}

				}
				// 进行查询对比判断是否可以登录（学生界面）
				if (stu.isSelected() == true) {
					String sql = "select * from student where Sid=" + userInput.getText();
					ResultSet rset;
					try {
						Statement stmt;
						stmt = conn.createStatement();
						rset = stmt.executeQuery(sql);
						// 查询到数据
						if (rset.next()) {
							// 信息一致，允许登录
							if (rset.getString("Spassword").equals(passwordInput.getText())
									&& rset.getString("state").equals("正常")) {
								Stage stage = new Stage();
								Scene scene = new Scene(new StudentMain(stage, rset.getString("Sname"),
										userInput.getText(), rset.getString("Ssex")));
								stage.setScene(scene);
								stage.setTitle("学生主界面");
								stage.show();
								loginStage.hide();
								// stulogin.setName(stulogin.getName(),
								// rset.getString("Sname"));
								// stulogin.setView(stulogin.getView(),
								// rset.getString("Ssex"));
								// stulogin.start(stustage);
								// 登录成功后再关闭查询与数据库连接
								stmt.close();
								myDB.closeMyConnection();
							}
							// 密码正确，状态异常
							else if (rset.getString("Spassword").equals(passwordInput.getText())
									&& !rset.getString("state").equals("正常")) {
								Alert warning = new Alert(AlertType.WARNING, "账号状态异常，请联系管理员");
								warning.setTitle("账号状态异常");
								warning.show();
							}
							// 状态正常，密码错误
							else if (!rset.getString("Spassword").equals(passwordInput.getText())
									&& rset.getString("state").equals("正常")) {
								Alert warning = new Alert(AlertType.WARNING, "密码输入有误，请重新输入");
								warning.setTitle("密码错误");
								warning.show();
							}

						}
						// 未查询到数据
						else {
							Alert warning = new Alert(AlertType.WARNING, "请检查账号信息是否输入正确，若未注册，请至注册界面进行注册或联系管理员添加！");
							warning.setTitle("账号信息错误");
							warning.show();
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					} catch (Throwable e1) {

						e1.printStackTrace();
					}

				}
			} else {
				Alert warning = new Alert(AlertType.WARNING, "账号与密码必须是字母或数字且长度小于20！");
				warning.setTitle("格式输入错误");
				warning.show();
			}

		});
		scene.getStylesheets().add(Login.class.getResource("../css/login.css").toExternalForm());
		loginStage.setScene(scene);
		loginStage.setTitle("网络安全知识在线测试系统");
		loginStage.setResizable(false);
		// loginStage.initStyle(StageStyle.UTILITY);
		root.setId("root");
		title.setId("font");
		username.setId("font");
		password.setId("font");
		ad.setId("font");
		stu.setId("font");
		loginStage.show();
	}
}
