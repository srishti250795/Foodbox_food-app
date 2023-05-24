package com.mycompany.fds.api;

import com.mycompany.fds.model.Prof;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProfHelper {
        public static ArrayList<Prof> getProf(ResultSet rs) throws SQLException {
        ArrayList<Prof> profs = new ArrayList<>();

        while(rs.next()) {

            int id=rs.getInt("id");
            String username=rs.getString("username");
            Prof p=new Prof(id,username);
            profs.add(p);
        }
        return profs;
    }
}
