import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args)throws SQLException {
		String url = "jdbc:mysql://localhost:3306/Emp";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "root";
       
        Connection conn = DriverManager.getConnection(url,userName,password);
      
        Statement st = conn.createStatement();
                
   ResultSet res=  st.executeQuery("select * from Empinfo");
   while(res.next())
   {
	   System.out.println(res.getString("name"));
   }
   
       

        }
}