package paquetePOO;

public class Cliente {
	private int codigo;
	private int dni;
	private String nombre;
	private String dire;	
			
	//metodo constructor	
	public Cliente(int codigo, int dni, String nombre, String dire) { //METODO CONSTRUCTOR DE LA CLASE PERSONA
		this.codigo=codigo;
		this.dni= dni;
		this.nombre = nombre;
		this.dire = dire;
	}
	public int damedni () {
		return dni;
	}
	public String damenombre() {
		return nombre;
		
	}
	public String damedire() {
		return dire;
	}
	public int damecodigo() {
		return codigo;
	}
	public void mostrarpersona() {
		System.out.println("persona codigo: "+codigo);
		System.out.println("dni-nombre: "+dni+"--"+nombre);
		System.out.println("direccion: "+dire);
		
	}
}
