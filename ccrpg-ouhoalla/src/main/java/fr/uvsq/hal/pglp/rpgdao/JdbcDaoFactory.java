package fr.uvsq.hal.pglp.rpgdao;

import fr.uvsq.hal.pglp.rpg.Character;

import java.sql.Connection;

/**
 * La classe <code>JdbcDaoFactory</code> permet de créer des DAO basés sur JDBC.
 *
 * @author SARAH & Melissa
 * @version 2022
 */
public class JdbcDaoFactory extends DaoFactory {
    private final Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    public Dao<Character> getCharacterDao() {
        return new CharacterJdbcDao(connection);
    }
}