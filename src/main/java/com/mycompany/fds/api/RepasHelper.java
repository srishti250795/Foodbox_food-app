
package com.mycompany.fds.api;
import com.mycompany.fds.model.Repas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class RepasHelper {
    public static ArrayList<Repas> getRepas(ResultSet repasDatabase) throws SQLException {
        ArrayList<Repas> repas = new ArrayList<>();

        while(repasDatabase.next()) {
            int idRepas = repasDatabase.getInt("id_repas");
            String nomRepas = repasDatabase.getString("nom_repas");
            float prix = repasDatabase.getFloat("prix");
            String restaurant = repasDatabase.getString("restaurant");
            int rank = repasDatabase.getInt("rank");
            String description = repasDatabase.getString("description");
            String img = repasDatabase.getString("img");
            Repas r = new Repas(idRepas, nomRepas, prix, restaurant, rank, description, img);
            repas.add(r);
        }
        return repas;
    }
}
