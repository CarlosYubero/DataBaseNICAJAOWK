import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
	Connection conexion;
	public DataBase() {
		conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion =   DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/practica01jdbc", "root", "");

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
		if (conexion !=null){
			System.out.println("Conexión correcta");
		}
		String sqlUser = "CREATE TABLE IF NOT EXISTS USER(_id TINYINT(2) NOT NULL AUTO_INCREMENT, name VARCHAR(15), email VARCHAR(15), PRIMARY KEY (_id)) ENGINE=InnoDB";
		String sqlAnswers = "CREATE TABLE IF NOT EXISTS ANSWER(id TINYINT(2) NOT NULL AUTO_INCREMENT, text VARCHAR(80), is_correct BOOLEAN, id_question TINYINT(2), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlConstAnwer = "ALTER TABLE ANSWER ADD CONSTRAINT FK_ANSWERS_QUESTIONS FOREIGN KEY (id_question ) REFERENCES QUESTION( id)";
		String sqlQuestion = "CREATE TABLE IF NOT EXISTS QUESTION(id TINYINT(2) NOT NULL AUTO_INCREMENT, text VARCHAR(80),id_test TINYINT(2), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlConstAnwer2 = "ALTER TABLE QUESTION ADD CONSTRAINT FK_QUESTIONS_TEST FOREIGN KEY (id_test ) REFERENCES TEST( id)";
		String sqlTest = "CREATE TABLE IF NOT EXISTS TEST(id TINYINT(2) NOT NULL AUTO_INCREMENT, name VARCHAR(15), date VARCHAR(19), score INT(5), PRIMARY KEY (id)) ENGINE=InnoDB";
		
		String[] sentenciasStr = {sqlUser, sqlAnswers, sqlQuestion, sqlConstAnwer, sqlTest, sqlConstAnwer2};
		try {
			Statement sentencia = conexion.createStatement();
			for(String sentencias: sentenciasStr)
			sentencia.executeUpdate(sentencias);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean intoducir_usuario (String usuario, String email){
		int retornar = 0;
		try {
			String sentenciaSql = "INSERT INTO USER(name,email) VALUES ('"+ usuario + "','" + email + "')";
			Statement sentencia = conexion.createStatement();
			retornar = sentencia.executeUpdate(sentenciaSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retornar == 0;
	}
	
	public boolean intoducir_test (String name, String date, int score){
		int retornar = 0;
		try {
			String sentenciaSql = "INSERT INTO USER(name,date,score) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			
			sentencia.setString(1,name);
			sentencia.setString(2,date);
			sentencia.setInt(3,score);
			retornar = sentencia.executeUpdate(sentenciaSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retornar == 0;
	}
	
	

}
