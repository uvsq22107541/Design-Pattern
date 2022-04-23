package fr.uvsq.hal.pglp.rpgdao;

import fr.uvsq.hal.pglp.rpg.Ability;
import fr.uvsq.hal.pglp.rpg.Character;
import fr.uvsq.hal.pglp.rpg.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

/**
 * La classe <code>EmployeeJdbcDao</code> implémente la persistance des personnels avec JDBC.
 *
 * @author SARAH&MELISSA
 * @version 2022
 */

public class CharacterJdbcDao implements Dao<Character> {
    private Connection connection;

    public CharacterJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Character objet) {
        try {
            PreparedStatement psInsert = connection.prepareStatement("INSERT INTO characters VALUES(?, ?, ?, ?, ?, ?,?)");
            psInsert.setString(1, objet.getNom());
            psInsert.setString(2, objet.getForce().toString());
            psInsert.setString(3, objet.getDexterite().toString());
            psInsert.setString(4, objet.getConstitution().toString());
            psInsert.setString(5, objet.getIntelligence().toString());
            psInsert.setString(6, objet.getSagesse().toString());
            psInsert.setString(7, objet.getCharisme().toString());

            psInsert.executeUpdate();

            psInsert = connection.prepareStatement("INSERT INTO competences VALUES(?, ?, ?)");
            for (Skill skill : objet.getCompetences()) {

                psInsert.setString(1, skill.toString());
                psInsert.setString(2, objet.getNom());
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public Optional<Character> read(String identifier) {
        Character character = null;
        try {
            PreparedStatement psInsert = connection.prepareStatement("SELECT * FROM employees WHERE lastname = ?");
            psInsert.setString(1, identifier);
            ResultSet rs = psInsert.executeQuery();
            if (rs.next()) {
                ArrayList<Ability> priorites = new ArrayList<>();
                priorites.add(Ability.DEXTERITE);
                priorites.add(Ability.SAGESSE);
                priorites.add(Ability.INTELLIGENCE);
                priorites.add(Ability.FORCE);
                priorites.add(Ability.CONSTITUTION);
                priorites.add(Ability.CHARISME);
                character = new Character.Builder(rs.getString(1), priorites).build();

                psInsert = connection.prepareStatement("SELECT * FROM competences WHERE character = ?");
                psInsert.setString(1, identifier);
                rs = psInsert.executeQuery();
                while (rs.next()) {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(character);
    }

    @Override
    public boolean update(Character objet) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE character SET nom = ? WHERE nom= ?");
            ps.setString(1, objet.getNom());
            ps.setString(2, objet.getNom());
            ps.executeUpdate();

            ps = connection.prepareStatement("DELETE FROM characters WHERE character = ?");
            ps.setString(1, objet.getNom());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void delete(Character objet) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM characters WHERE employee = ?");
            ps.setString(1, objet.getNom());
            ps.executeUpdate();
            ps = connection.prepareStatement("DELETE FROM characters WHERE lastname = ?");
            ps.setString(1, objet.getNom());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
