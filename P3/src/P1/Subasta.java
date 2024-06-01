package P1;

import java.util.ArrayList;
import java.util.List;

public class Subasta {
    private boolean enCurso;
    private List<Oferta> ofertas; // Lista para mantener registro de todas las ofertas realizadas en la subasta

    public Subasta() {
        this.enCurso = false;
        this.ofertas = new ArrayList<>();
    }

    public void iniciarSubasta() {
        this.enCurso = true;
        System.out.println("Subasta iniciada.");
    }

    public void finalizarSubasta() {
        this.enCurso = false;
        System.out.println("Subasta finalizada.");
    }

    // Este es el único método para registrar ofertas en la clase Subasta
    public void registrarOferta(Comprador comprador, double monto) {
        Oferta nuevaOferta = new Oferta(comprador, monto);
        // Asignar la nueva oferta a la lista de ofertas
        this.ofertas.add(nuevaOferta);
        // Imprimir confirmación
        System.out.println("Nueva oferta registrada: " + nuevaOferta);
    }
    
    public boolean estaEnCurso() {
        return enCurso;
    }

    // Clase interna Oferta
    public class Oferta {
        private Comprador comprador;
        private double monto;

        public Oferta(Comprador comprador, double monto) {
            this.comprador = comprador;
            this.monto = monto;
        }

        @Override
        public String toString() {
            // Suponemos que Comprador tiene un método getNombre()
            return "Oferta de " + comprador.getNombre() + " por " + monto;
        }

        // Métodos getters para obtener el comprador y el monto de la oferta
        public Comprador getComprador() {
            return comprador;
        }

        public double getMonto() {
            return monto;
        }
    }

	
	

	public Pieza[] getPiezas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPiezas(List<Pieza> piezas) {
		// TODO Auto-generated method stub
	}

	public void setCompradores(List<Comprador> compradores) {
		// TODO Auto-generated method stub
		
	}

	public Comprador[] getCompradores() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPagos(List<Pago> pagos) {
		// TODO Auto-generated method stub
		
	}

	public Pago[] getPagos() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
	



    
    
    
    
 

