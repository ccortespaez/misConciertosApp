package cl.inacap.misconciertosapp.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.misconciertosapp.dto.Concierto;

public class ConciertosDAO {
    private static List<Concierto> conciertos = new ArrayList<Concierto>();

    public void add(Concierto concierto){
        conciertos.add(concierto);
    }
    public List<Concierto> getAll(){
        return conciertos;
    }

}
