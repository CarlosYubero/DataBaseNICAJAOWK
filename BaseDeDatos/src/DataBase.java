import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


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
		String sqlUser = "CREATE TABLE IF NOT EXISTS USER(id TINYINT(2) NOT NULL AUTO_INCREMENT, name VARCHAR(15), email VARCHAR(15), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlAnswers = "CREATE TABLE IF NOT EXISTS ANSWER(id TINYINT(2) NOT NULL AUTO_INCREMENT, text VARCHAR(80), is_correct BOOLEAN, id_question TINYINT(2), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlConstAnwer = "ALTER TABLE ANSWER ADD CONSTRAINT FK_ANSWERS_QUESTIONS FOREIGN KEY (id_question) REFERENCES QUESTION(id)";
		String sqlQuestion = "CREATE TABLE IF NOT EXISTS QUESTION(id TINYINT(2) NOT NULL AUTO_INCREMENT, text VARCHAR(80),id_test TINYINT(2), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlConstAnwer2 = "ALTER TABLE QUESTION ADD CONSTRAINT FK_QUESTIONS_TEST FOREIGN KEY (id_test) REFERENCES TEST(id)";
		String sqlTest = "CREATE TABLE IF NOT EXISTS TEST(id TINYINT(2) NOT NULL AUTO_INCREMENT, name VARCHAR(15), date VARCHAR(19), score INT(5), PRIMARY KEY (id)) ENGINE=InnoDB";
		String sqlUserTest = "CREATE TABLE IF NOT EXISTS RESULTS(id_user TINYINT(2) NOT NULL, id_test TINYINT(2) NOT NULL) ENGINE=InnoDB";
		String sqlConstAnwer3 = "ALTER TABLE RESULTS ADD CONSTRAINT FK_USER FOREIGN KEY (id_user) REFERENCES USER(id)";
		String sqlConstAnwer4 = "ALTER TABLE RESULTS ADD CONSTRAINT FK_TEST FOREIGN KEY (id_test) REFERENCES TEST(id)";
		
		String[] sentenciasStr = {sqlUser, sqlAnswers, sqlQuestion, sqlConstAnwer, sqlTest, sqlConstAnwer2, sqlUserTest, sqlConstAnwer3, sqlConstAnwer4};
		try {
			Statement sentencia = conexion.createStatement();
			for(String sentencias: sentenciasStr)
			sentencia.executeUpdate(sentencias);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean introducir_usuario (String usuario, String email){
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

	
	public boolean introducir_test (String name, String date, int score){
		int retornar = 0;
		try {
			String sentenciaSql = "INSERT INTO TEST(name,date,score) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			
			sentencia.setString(1,name);
			sentencia.setString(2,date);
			sentencia.setInt(3,score);
			
			retornar = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retornar == 0;
	}
	
	
	public boolean introducir_question (String text, int id){
		int retornar = 0;
		try {
			String sentenciaSql = "INSERT INTO QUESTION(text,id_test) VALUES (?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			
			sentencia.setString(1,text);
			sentencia.setInt(2,id);
			
			retornar = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retornar == 0;
	}
	
	public boolean introducir_answer (String text, boolean is_correct, int id_question){
		int retornar = 0;
		try {
			String sentenciaSql = "INSERT INTO ANSWER(text,is_correct,id_question) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
			
			sentencia.setString(1,text);
			sentencia.setBoolean(2,is_correct);
			sentencia.setInt(3,id_question);
			
			retornar = sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retornar == 0;
	}
	
	public void introducir_pregunta_actividad(Pregunta pregunta, Respuesta[] respuestas, Test test){
		introducir_test(test.getName(), test.getDate(), test.getScore());
		int id_test;
		String sql = "SELECT id FROM test WHERE name = '" + test.getName() + "'";
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			rs.next();
			id_test = rs.getInt("id");
			introducir_question(pregunta.getText(), id_test);
			sql = "SELECT id FROM question WHERE text = '" + pregunta.getText() + "'";
			rs = sentencia.executeQuery(sql);
			rs.next();
			for(Respuesta respuesta : respuestas)
			introducir_answer(respuesta.getText(), respuesta.isIs_correct(), rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void imprimir_test(Test test){
		String sql = "SELECT id FROM test WHERE name = '" + test.getName() + "'";	
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			rs.next();
			int id_test = rs.getInt("id");
			sql = "SELECT q.text textp, a.text texta, a.is_correct res FROM question q, answer a WHERE q.id = a.id_question and q.id_test = '" + id_test + "'";
			rs = sentencia.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("textp")+ "\n" + rs.getString("texta"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void hacer_test(User user, Test test){
		String sql = "SELECT id FROM test WHERE name = '" + test.getName() + "'";	
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			rs.next();
			int id_test = rs.getInt("id");
			sql = "SELECT q.text textp, a.text texta, a.is_correct res FROM question q, answer a WHERE q.id = a.id_question and q.id_test = '" + id_test + "'";
			rs = sentencia.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("textp")+ "\n" + rs.getString("texta"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
