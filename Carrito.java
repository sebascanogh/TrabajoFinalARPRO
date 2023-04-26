package paquetePOO;

public class Carrito {
	private int numc;
	private Cliente cli;
	private double montototal;
	
	public Carrito(int num, Cliente cliente) {
		numc = num;
		cli = cliente;
		montototal = 0.0;
		
	}
	public int damenumc() {
		return numc;
		
	}
	public Cliente dameCliente() {
		return cli;
	}
	public double damemontototal() {
		return montototal;
	}
	public void agregarMontototal(double monto) {
		montototal = monto;
	}
	public void mostrarmontoTotal(double monto) {
		System.out.println("EL MONTO TOTAL DEL CARRITO ES : "+monto);
	}

}
