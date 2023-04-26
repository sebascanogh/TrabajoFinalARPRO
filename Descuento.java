package paquetePOO;

public abstract class Descuento {
	private double valor;
	
	public double dameValorDesc() {
	return valor;
	}
	public void asignaValorDesc(double valor) {
	this.valor = valor;
	}
	//tiene un metodo abstract
	
	public abstract double valorFinal(double valorInicial);


}
