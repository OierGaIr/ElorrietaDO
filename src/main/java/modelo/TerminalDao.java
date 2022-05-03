package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controlador.Controlador;

public class TerminalDao {

	private static Connection conexion;
	static final String URL_CONEXION = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
	static final String USUARIO = "root";
	static final String PASSWORD = "root";
	static final String FORNAME = "com.mysql.jdbc.Driver";

	public TerminalDao() {
		// CONEXION BASE DE DATOS MYSQL
		try {
			// cargar el driver
			Class.forName(FORNAME);
			// Establecer la conexion con la bd empresa
			conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD);
			// Hay que cerrar en orden
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Imposible establecer la conexion");
			e.printStackTrace();
		}
	}

	// APARTADO DE QUERYS
	private static final String SELECTLISTADOCURSOS = " select fecha 'FECHA',forma.nombre_curso 'CURSO' , num_horas 'HORAS' , profe.nombre_profe 'NOMBRE_PROFESOR',profe.apellido_profe 'APELLIDO_PROFESOR', horario 'HORARIO', descripcion 'IDIOMA' \r\n"
			+ "from curso cur join formacion forma on cur.id_formacion = forma.id_formacion \r\n"
			+ "join formador profe on cur.id_formador = profe.id_formador\r\n"
			+ "join turno t on cur.id_turno = t.id_turno\r\n" + "join Idioma i on cur.id_idioma = i.id_idioma\r\n"
			+ "order by fecha desc;";
	private static final String SELECTDETALLECURSO = "select fecha 'FECHA',forma.nombre_curso 'CURSO' , num_horas 'HORAS' , profe.nombre_profe 'NOMBRE_PROFESOR',profe.apellido_profe 'APELLIDO_PROFESOR', a.nombre_alumn 'NOMBRE_ALUMNO' \r\n"
			+ "from curso cur join formacion forma on cur.id_formacion = forma.id_formacion \r\n"
			+ "join formador profe on cur.id_formador = profe.id_formador\r\n"
			+ "join participantes p on p.id_curso=cur.id_curso \r\n" + "join alumno a on a.id_alumno = p.id_alumno \r\n"
			+ "order by fecha desc;  ";
	private static final String SELECTDETALLECURSOCONRESENA = " select fecha 'FECHA',forma.nombre_curso 'CURSO' , num_horas 'HORAS' , profe.nombre_profe 'NOMBRE_PROFESOR',profe.apellido_profe 'APELLIDO_PROFESOR', a.nombre_alumn 'NOMBRE_ALUMNO', r.descrip 'RESENA'\r\n"
			+ "from curso cur join formacion forma on cur.id_formacion = forma.id_formacion \r\n"
			+ "join formador profe on cur.id_formador = profe.id_formador\r\n"
			+ "join participantes p on p.id_curso=cur.id_curso \r\n" + "join alumno a on a.id_alumno = p.id_alumno \r\n"
			+ "join resena r on r.id_formacion = forma.id_formacion\r\n" + "order by fecha desc; ";

	public static void listadoDeCursos() {
		// TODO ESTE METODO ES PARA LISTAR TODOS LOS CURSOS QUE SE
		// IMPARTEN EN EL CENTRO

		try (Statement con = conexion.createStatement(); ResultSet rs = con.executeQuery(SELECTLISTADOCURSOS);) {

			System.out.println("-------------------------------------------------------");
			System.out.println(
					"ID 		 FECHA            	 CURSO			  HORAS		   NOMBRE_PROFESOR 		 HORARIO 	  IDIOMA");
			System.out.println("-------------------------------------------------------");
			while (rs.next()) {

				String fecha = rs.getString("FECHA");
				String curso = rs.getString("CURSO");
				int horas = rs.getInt("HORAS");
				String nombrePro = rs.getString("NOMBRE_PROFESOR");
				String apellidoPro = rs.getString("APELLIDO_PROFESOR");
				String horario = rs.getString("HORARIO");
				String idioma = rs.getString("IDIOMA");
				System.out.printf(" %-4s %25s %18s %18s %10s %18s %18s\n", fecha, curso, horas, nombrePro, apellidoPro,
						horario, idioma);

			} // while

			System.out.println("---------------------- TOTAL X Alumnos -------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void detalleDeCursos() {
		// TODO ESTE METODO ES PARA LISTAR TODA LA INFORMACION DEL CURSO SIN RESEÑA

		try (Statement con = conexion.createStatement(); ResultSet rs = con.executeQuery(SELECTDETALLECURSO);) {

			System.out.println("-------------------------------------------------------");
			System.out.println(
					" FECHA            	 CURSO			  HORAS		   NOMBRE_PROFESOR  	 APELLIDO_PROFESOR 	  NOMBRE_ALUMNO");
			System.out.println("-------------------------------------------------------");
			while (rs.next()) {

				String fecha = rs.getString("FECHA");
				String curso = rs.getString("CURSO");
				int horas = rs.getInt("HORAS");
				String nombrePro = rs.getString("NOMBRE_PROFESOR");
				String apellidoPro = rs.getString("APELLIDO_PROFESOR");
				String nombreAlum = rs.getString("NOMBRE_ALUMNO");
				System.out.printf(" %-4s %25s %15s %20s %28s %20s\n", fecha, curso, horas, nombrePro, apellidoPro,
						nombreAlum);

			} // while

			System.out.println("---------------------- TOTAL X Alumnos -------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void detalleDeCursoConResena() {
		// TODO ESTE METODO ES PARA LISTAR TODA LA INFORMACION DEL CURSO CON RESEÑA
		try (Statement con = conexion.createStatement(); ResultSet rs = con.executeQuery(SELECTDETALLECURSOCONRESENA);

		) {

			System.out.println("-------------------------------------------------------");
			System.out.println(
					" FECHA            	 CURSO			  HORAS		   NOMBRE_PROFESOR  	 APELLIDO_PROFESOR 	  NOMBRE_ALUMNO		RESEÑA");
			System.out.println("-------------------------------------------------------");

			while (rs.next()) {

				String fecha = rs.getString("FECHA");
				String curso = rs.getString("CURSO");
				int horas = rs.getInt("HORAS");
				String nombrePro = rs.getString("NOMBRE_PROFESOR");
				String apellidoPro = rs.getString("APELLIDO_PROFESOR");
				String nombreAlum = rs.getString("NOMBRE_ALUMNO");
				String resena = rs.getString("RESENA");
				System.out.printf(" %-4s %25s %15s %20s %28s %20s %25s\n", fecha, curso, horas, nombrePro, apellidoPro,
						nombreAlum, resena);

			} // while

			System.out.println("---------------------- TOTAL X Alumnos -------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}