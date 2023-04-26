package paquetePOO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComprasconBasedDatos {


			public static void main(String[] args) {
				try {
					Connection conX = DriverManager.getConnection("jdbc:mysql://localhost:3306/compraSuper","root","");
					Statement sT = conX.createStatement();
					
					String consulta = "select * from clientes";
					
					ResultSet sql = sT.executeQuery(consulta);
					
					System.out.println("CODIGO\tNOMBRE\t\tDIRECCION\tDNI");
					while (sql.next()) {
						System.out.println(sql.getInt(1)+"\t"+sql.getString(2)+"\t"+sql.getString(3)+"\t"+sql.getInt(4));
					
					
					}
					Scanner entrada = new Scanner(System.in);
					System.out.println();
					System.out.println("Seleccione el CODIGO del cliente");
					int codigo = entrada.nextInt();
					
					
					consulta = String.format("select * from clientes where idC = %s",codigo);
					sql = sT.executeQuery(consulta);
					int codC=0, dniC=0;
					String nomC="", direC="";
					
					
					while (sql.next()) {
					
						codC = sql.getInt(1);
						dniC = sql.getInt(4);
						nomC = sql.getString(2);
						direC =	sql.getString(3);	
					
					}
							
						
					Cliente c1 = new Cliente(codC,dniC,nomC,direC);
					System.out.println("Ingrese el número del carrito");
					codigo = entrada.nextInt();
					Carrito carro = new Carrito(codigo,c1);
					
					Producto p1 = new Producto(100,"Dulce","Repostero x 500grs.",450.30,1000);
					Producto p2 = new Producto(101,"Leche","En polvo x 500grs.",200,1000);
					Producto p3 = new Producto(102,"Pan","Casero x 500grs.",150.30,500);
					Producto p4 = new Producto(103,"Café","Frasco x 500grs.",450.30,1000);
					Producto p5 = new Producto(100,"Arroz","Repostero x 500grs.",450.30,1000);
					Producto p6 = new Producto(101,"Fideo","En polvo x 500grs.",200,1000);
					Producto p7 = new Producto(102,"Te","Casero x 500grs.",150.30,500);
					Producto p8 = new Producto(103,"Cacao","Frasco x 500grs.",450.30,1000);

					ItemCarrito itemC1 = new ItemCarrito(carro,p1,1);
					ItemCarrito itemC2 = new ItemCarrito(carro,p2,2);
					ItemCarrito itemC3 = new ItemCarrito(carro,p3,3);
					ItemCarrito itemC4 = new ItemCarrito(carro,p4,4);
					ItemCarrito itemC5 = new ItemCarrito(carro,p5,2);	
					ItemCarrito itemC6 = new ItemCarrito(carro,p6,1);
					ItemCarrito itemC7 = new ItemCarrito(carro,p7,3);	
					ItemCarrito itemC8 = new ItemCarrito(carro,p8,2);	
					
					List<ItemCarrito> itemL;
					itemL= new ArrayList<ItemCarrito>();
					
					itemL.add(itemC1);
					itemL.add(itemC2);
					itemL.add(itemC3);
					itemL.add(itemC4);
					itemL.add(itemC5);
					itemL.add(itemC6);
					itemL.add(itemC7);
					itemL.add(itemC8);
					
					mostrarItems(itemL, carro, c1, conX);
					
					System.out.println("\n\nListado de las compras del día");
					consulta = "select * from carrito";
					sql = sT.executeQuery(consulta);
					
					System.out.println("CODIGO\tCLIENTE\tMONTO\tDESC\tFECHA COMPRA");
					while (sql.next()) {
						System.out.println(sql.getInt(1)+"\t"+sql.getInt(2)+"\t"+sql.getDouble(3)+"\t"+sql.getDouble(4)+"\t"+sql.getDate(5));
					
					
					}
					
					
				}catch(Exception obj) {
					
					System.out.println("Error en la conexión  de la base de datos");
					System.out.println(obj.fillInStackTrace());
				
				}
			
					Producto p1 = new Producto(100,"Dulce","Repostero x 500grs.",450.30,1000);
					Producto p2 = new Producto(101,"Leche","En polvo x 500grs.",200,1000);
					Producto p3 = new Producto(102,"Pan","Casero x 500grs.",150.30,500);
					Producto p4 = new Producto(103,"Café","Frasco x 500grs.",450.30,1000);
					Producto p5 = new Producto(100,"Arroz","Repostero x 500grs.",450.30,1000);
					Producto p6 = new Producto(101,"Fideo","En polvo x 500grs.",200,1000);
					Producto p7 = new Producto(102,"Te","Casero x 500grs.",150.30,500);
					Producto p8 = new Producto(103,"Cacao","Frasco x 500grs.",450.30,1000);
					
			
			}
			
			
			public static void mostrarItems(List itemL, Carrito carro, Cliente c1, Connection conX) {
				
				Iterator<ItemCarrito> itL = itemL.iterator();
				double total=0, montoDes= 0;
				boolean mostrarTitulo=true;
				LocalDate fecha = LocalDate.now();
			
				while (itL.hasNext()) {
					ItemCarrito datosI = itL.next();
					if (mostrarTitulo) {
						mostrarTitulo = false;
						datosI.mostrarTitulo();				
					}
					datosI.mostrarItems();
					total = total + datosI.dameMontoItem();
					carro.agregarMontototal(total);
				}
				
				carro.mostrarmontoTotal(total);	
				if (total>0)
					montoDes = descuentos(total);
				else
					System.out.println("No se aplican descuentos a montos <= a cero");
				
				String consulta = "insert into carrito(idCA, idC, montoCA,	montoDescCA, fechaCA) values (idCA,?,?,?,?)";
				try {
					PreparedStatement sqlUP = conX.prepareStatement(consulta);
					sqlUP.setInt(1, c1.damecodigo());
					sqlUP.setDouble(2, carro.damemontototal());
					sqlUP.setDouble(3, montoDes);
					sqlUP.setString(4, fecha.toString());
					
					sqlUP.executeUpdate();
					
				} catch (SQLException obj) {
					System.out.println("Error en la consulta  de la tabla carrito");
					obj.fillInStackTrace();
				}
						
			}
			
			public static double descuentos(double total) {
				 final double montoMin = 5000; 
				 LocalDate dia = LocalDate.now();
				 double montoD=0;

			     int descuento = dia.getDayOfMonth();
			     if (descuento %2==0) {
				 Descuento descFijo=new DescuentoFijo();
				 descFijo.asignaValorDesc(200);
				 montoD = descFijo.valorFinal(total);
				 	if (montoD>0)
				 		System.out.println("El monto total a pagar con descuento es: "+ String.format("%.2f",montoD));
				 	else 
				 		System.out.println("El descuento no se puede realizar");
			     }
			    else {
				Descuento descPorc = new DescuentoPorcentaje();
				
				if (total<montoMin) {
					descPorc.asignaValorDesc(0.05);
				}
				else
				descPorc.asignaValorDesc(0.10);
				montoD = descPorc.valorFinal(total);	
				if (montoD>0)
					System.out.println("El monto total a pagar con descuento es: "+ String.format("%.2f",montoD));
				else 
					System.out.println("El descuento no se puede realizar");
			
			    }
			     
			     return montoD;
				
			}
		}
