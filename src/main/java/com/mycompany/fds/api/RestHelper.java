package com.mycompany.fds.api;

import com.mycompany.fds.model.Repas;
import com.mycompany.fds.model.Rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestHelper {
    public static ArrayList<Rest> getRest(ResultSet RestDatabase) throws SQLException {
        ArrayList<Rest> rest = new ArrayList<>();

        while(RestDatabase.next()) {
            int idRest = RestDatabase.getInt("id_rest");
            String nomRest = RestDatabase.getString("nom_rest");
            String description = RestDatabase.getString("description");
            String img = RestDatabase.getString("img");
            Rest r = new Rest(nomRest, idRest, img,description );
            rest.add(r);
        }
        return rest;
    }
}
