package modelo;

import controlador.Controlador;

public class Funciones {
	
	public static TerminalDao conexion = new TerminalDao();

	private static final int LISTADODECURSO = 1;
	private static final int DETALLEDECURSO = 2;
	private static final int DETALLEDECURSOCONRESEÑA = 3;

	public static void infmenu() {

		System.out.println("--------- ELORRIETA ----------");

		System.out.println("1 Listado de Cursos ");
		System.out.println("2 Detalle de Curso ");
		System.out.println("3 Detalle de Curso Con Reseña ");

		System.out.println("------------------------------");
		swichtMenu();
	}
	
	/**
	 * Este metodo solamente el switch para poder elegir las opciones de menu 
	 * 
	 * 
	*/
	public static void swichtMenu() {

		int opcion = Controlador.isNumberMenu();

		switch (opcion) {
		case LISTADODECURSO:
			//TODO listardoCurso() TERMINAL DAO
			conexion.listadoDeCursos();
			infmenu();
			break;
		case DETALLEDECURSO:
			//TODO detalleCurso() TERMINAL DAO 
			conexion.detalleDeCursos();
			infmenu();
			break;
		case DETALLEDECURSOCONRESEÑA:
			//TODO detalleCursoConReseña() TERMINAL DAO
			conexion.detalleDeCursoConResena();
			infmenu();
			break;

		default:
			break;
		}
	}
}