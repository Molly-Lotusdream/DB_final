package administrator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Questiontable {
      private int qid;
      private String qcontent;
      private String qA;
      private String qB;
      private String qC;
      private String qD;
      private String answer;

      public Questiontable(){}
      public Questiontable(int qid,String qcontent,String qA,String qB,String qC,String qD,String answer){
    	  this.qid=qid;
    	  this.qcontent=qcontent;
    	  this.qA=qA;
    	  this.qB=qB;
    	  this.qC=qC;
    	  this.qD=qD;
    	  this.answer=answer;
      }
      public void setQid(int qid){
    	  this.qid=qid;
      }

      public void setQcontent(String qcontent){
    	  this.qcontent=qcontent;
      }

      public void setQA(String qA){
    	  this.qA=qA;
      }

      public void setQB(String qB){
    	  this.qB=qB;
      }

      public void setQC(String qC){
    	  this.qC=qC;
      }

      public void setQD(String qD){
    	  this.qD=qD;
      }

      public void setAnswer(String answer){
    	  this.answer=answer;
      }

      public int getQid(){
    	  return qid;
      }

      public String getQcontent(){
    	  return qcontent;
      }

      public String getQA(){
    	  return qA;
      }

      public String getQB(){
    	  return qB;
      }

      public String getQC(){
    	  return qC;
      }

      public String getQD(){
    	  return qD;
      }

      public String getAnswer(){
    	  return answer;
      }

      /*查看题库*/
      public static List<Questiontable> getAllquestion(int m,int n){
    	//数据库连接
  		String DBDriver = "com.mysql.cj.jdbc.Driver";
  		String DBURL = "jdbc:mysql://localhost:3306/question answering system?serverTimezone=UTC&characterEncoding=utf-8";
  		String DBUser = "root";
  		String DBPass = "123456";
  		MyDBConnection myDB = new MyDBConnection(DBDriver, DBURL, DBUser, DBPass);
  		Connection conn = myDB.getMyConnection();

  		List<Questiontable> allquestion = new ArrayList<>();
  		String sql;
  		if(m==1){
  			if(n==1){
  				sql = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from sselection";
  			}else{
  				sql = "select q_id,q_content,q_A,q_B,q_C,q_D,q_answer from mchoices";
  			}
  		}else{
  			if(n==1){
  				sql = "select q_id,q_content,q_answer from judgment";
  			}else{
  				sql = "select q_id,q_content,q_answer from blanks";
  			}
  		}
  		ResultSet rset;
    	try {
    		Statement stmt;
    		stmt = conn.createStatement();
    		rset = stmt.executeQuery(String.format(sql));
    		while(rset.next()){
    			System.out.println(allquestion.add(Questiontable.getQuestion(rset,m)));
    		}
    		// 查询完毕关闭数据库
    		stmt.close();
    		myDB.closeMyConnection();
    	} catch (SQLException e11) {
    		// TODO 自动生成的 catch 块
    		e11.printStackTrace();
    	}
  		return allquestion;
      }

      public static Questiontable getQuestion(ResultSet rs,int m){
    	  Questiontable question =new Questiontable();
    	  try{
    		  if(m==1){
                 question.setQid(rs.getInt("q_id"));
                 question.setQcontent(rs.getString("q_content"));
                 question.setQA(rs.getString("q_A"));
                 question.setQB(rs.getString("q_B"));
                 question.setQC(rs.getString("q_C"));
                 question.setQD(rs.getString("q_D"));
                 question.setAnswer(rs.getString("q_answer"));
    		  }else{
    			  question.setQid(rs.getInt("q_id"));
                  question.setQcontent(rs.getString("q_content"));
                  question.setAnswer(rs.getString("q_answer"));
    		  }

    	  }catch(SQLException throwables) {
	            throwables.printStackTrace();
	        }
    	  return question;
      }


}
