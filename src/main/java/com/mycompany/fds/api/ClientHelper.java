
package com.mycompany.fds.api;

import com.mycompany.fds.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHelper {
    public static ArrayList<Client> getClient(ResultSet rs) throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        while(rs.next()) {

            int id=rs.getInt("id_client");
            String username=rs.getString("username");
            String nom = rs.getString("nom_client");
            String password = rs.getString("password");
            String email = rs.getString("email");
            Client client = new Client(id,username,nom,email,password);
            clients.add(client);
        }
        return clients;
    }
    public static void addClient(Client c){
        String sql= " insert into client"
                + " values (NULL, ?, ?, ?, ?, ?)";
        try (Connection conn= DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,c.getName());
            stmt.setString(2,c.getUsername());
            stmt.setString(3,c.getPassword());
            stmt.setString(4,c.getEmail());
            stmt.setString(5, "images/ensias.png");
//
            stmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
