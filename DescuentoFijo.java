package paquetePOO;

public class DescuentoFijo extends Descuento {
	public double valorFinal(double valorInicial) {//valor inicial es el monto del carro
		return valorInicial - this.dameValorDesc();
		}


}
