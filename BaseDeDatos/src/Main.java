
public class Main {

	static DataBase conexion;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		conexion = new DataBase();
		conexion.intoducir_usuario("Pepe1", "pepe1@uem.com");
		conexion.intoducir_usuario("Pepe2", "pepe2@uem.com");
		conexion.intoducir_usuario("Pepe3", "pepe3@uem.com");
		conexion.intoducir_usuario("Pepe4", "pepe4@uem.com");
		conexion.intoducir_usuario("Pepe5", "pepe5@uem.com");
		conexion.intoducir_usuario("Pepe6", "pepe6@uem.com");
		conexion.intoducir_usuario("Pepe7", "pepe7@uem.com");
		conexion.intoducir_usuario("Pepe8", "pepe8@uem.com");
		conexion.intoducir_usuario("Pepe9", "pepe9@uem.com");
		conexion.intoducir_usuario("Pepe10", "pepe10@uem.com");
		
		conexion.intoducir_test("Test1", "2015-01-14 17:02:00", 1);
		conexion.intoducir_test("Test2", "2015-01-14 17:02:00", 2);
		conexion.intoducir_test("Test3", "2015-01-14 17:02:00", 3);
		conexion.intoducir_test("Test4", "2015-01-14 17:02:00", 4);
		conexion.intoducir_test("Test5", "2015-01-14 17:02:00", 5);
		conexion.intoducir_test("Test6", "2015-01-14 17:02:00", 6);
		conexion.intoducir_test("Test7", "2015-01-14 17:02:00", 7);
		conexion.intoducir_test("Test8", "2015-01-14 17:02:00", 8);
		conexion.intoducir_test("Test9", "2015-01-14 17:02:00", 9);
		conexion.intoducir_test("Test10", "2015-01-14 17:02:00", 10);
		
		conexion.intoducir_question("Pregunta1", 1);
		conexion.intoducir_question("Pregunta2", 1);
		conexion.intoducir_question("Pregunta3", 1);
		conexion.intoducir_question("Pregunta4", 1);
		conexion.intoducir_question("Pregunta5", 1);
		conexion.intoducir_question("Pregunta1", 2);
		conexion.intoducir_question("Pregunta2", 2);
		conexion.intoducir_question("Pregunta3", 2);
		conexion.intoducir_question("Pregunta4", 2);
		conexion.intoducir_question("Pregunta5", 2);

		conexion.intoducir_answer("Restuesta1", true, 1);
		conexion.intoducir_answer("Restuesta2", true, 2);
		conexion.intoducir_answer("Restuesta3", true, 3);
		conexion.intoducir_answer("Restuesta4", true, 4);
		conexion.intoducir_answer("Restuesta5", true, 5);
		conexion.intoducir_answer("Restuesta6", true, 6);
		conexion.intoducir_answer("Restuesta7", true, 7);
		conexion.intoducir_answer("Restuesta8", true, 8);
		conexion.intoducir_answer("Restuesta9", true, 9);
		conexion.intoducir_answer("Restuesta10", true, 10);

	}

}
