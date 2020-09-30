package cl.inacap.misconciertosapp.dto;

public class Concierto {
    private String nombreArtista;
    private String fecha;
    private int valorEntrada;
    private String calificacion;
    private String generosMusicales;

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getGenerosMusicales() {
        return generosMusicales;
    }

    public void setGenerosMusicales(String generosMusicales) {
        this.generosMusicales = generosMusicales;
    }

    @Override
    public String toString() {
        return fecha+" "+nombreArtista + " " + "$"+ valorEntrada +" "+ calificacion +" "+ generosMusicales;
    }
}
