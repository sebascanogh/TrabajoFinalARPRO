package paquetePOO;

public class ItemCarrito {
	private Carrito num;
	private Producto prod;
	private int cant;
	private double montoItem;
	private int i;
	
	public ItemCarrito(Carrito num,Producto producto, int cant) {
		this.num=num;
		prod = producto;
		this.cant = cant;
		montoItem = this.cant * prod.un_precio();		
	}
	public int dameCant() {
		return cant;
	}
	public double dameMontoItem() {
		return montoItem;
	}
	public Producto dameProducto() {
		return prod;
	}
	public void mostrarTitulo() {
		System.out.println("NumItem\tCant\tPrecio\tSubTotal\tProducto");
	}
	
	public void mostrarItems() {
		System.out.println(cant+"\t"+prod.un_precio()+"\t"+String.format("%.2f", montoItem)+"\t\t"+prod.un_nombre());
	}

}
