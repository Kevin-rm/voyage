/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu1844.models;

import etu1844.utils.OffsetLimit;
import java.sql.SQLException;
import mg.tonymushah.dbconnection.DBConnect;
import mg.tonymushah.dbconnection.utils.annotations.Column;
import mg.tonymushah.dbconnection.utils.annotations.PrimaryKey;
import mg.tonymushah.dbconnection.utils.annotations.Table;
import java.sql.*;

/**
 *
 * @author tonymushah
 */
@Table(name = "activite")
public class Activite {

    @Column(name = "id")
    @PrimaryKey
    private int id;
    @Column(name = "nom")
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Activite() {

    }

    public Activite(int id) {
        this.setId(id);
    }

    public Activite(String nom) {
        this.setNom(nom);
    }

    public Activite(int id, String nom) {
        this.setId(id);
        this.setNom(nom);
    }

    public Bouquet[] getBouquets(DBConnect con, OffsetLimit ol) throws SQLException, Exception {
        Statement stmt = con.getConnection().createStatement();

        String req = String.format("SELECT bouq_id as id, bouq_nom as nom FROM v_act_rel_bouquet\n"
                + "WHERE act_id = %d\n"
                + "LIMIT %d OFFSET %d", this.getId(), ol.limit(), ol.offset());
        ResultSet res = stmt.executeQuery(req);
        return con.resultset_toObjects(res, Bouquet.class);
    }
}
