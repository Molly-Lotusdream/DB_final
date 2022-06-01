package administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Stugrade {
	private String sid;
	private String sname;
	private String sschool;
	private int sgrade;
	public Stugrade(){}
	public Stugrade(String sid,String sname,String sschool,int sgrade){
		this.sid=sid;
		this.sname=sname;
		this.sschool=sschool;
		this.sgrade=sgrade;
	}
	public void setSid(String id){
    	sid=id;
    }

    public void setSname(String name){
    	sname=name;
    }

    public void setSschool(String school){
    	sschool=school;
    }

    public void setSgrade(int grade){
    	sgrade=grade;
    }

    public String getSid(){
    	return sid;
    }

    public String getSname(){
    	return sname;
    }

    public String getSschool(){
    	return sschool;
    }

    public int getSgrade(){
    	return sgrade;
    }
	/*查找所有学生成绩*/
	public static List<Stugrade> getAllStugrade(int t){
		//数据库连接
		String DBDriver = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
		String DBUser = "root";
		String DBPass = "123456";
		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
		Connection conn = myDB.getMyConnection();

        List<Stugrade> allgrade = new ArrayList<>();
        String sql;
        if(t==1){
        	sql = "select Sid,Sname,Sschool,Sgrade from student";
        }else{
            sql = "select Sid,Sname,Sschool,Sgrade from student order by Sgrade desc";
        }
    	ResultSet rset;
    	try {
    		Statement stmt;
    		stmt = conn.createStatement();
    		rset = stmt.executeQuery(String.format(sql));
    		while(rset.next()){
    			System.out.println(allgrade.add(Stugrade.getAllgrade(rset)));
    		}
    		// 查询完毕关闭数据库
    		stmt.close();
    		myDB.closeMyConnection();
    	} catch (SQLException e11) {
    		// TODO 自动生成的 catch 块
    		e11.printStackTrace();
    	}
		return allgrade;
	}
	 public static Stugrade getAllgrade(ResultSet rs)  {
		 Stugrade grade= new Stugrade();
		 try{
	            grade.setSid(rs.getString("Sid"));
	            grade.setSname(rs.getString("Sname"));
	            grade.setSschool(rs.getString("Sschool"));
                grade.setSgrade(rs.getInt("Sgrade"));

	            }catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }
		return grade;
	 }
}
