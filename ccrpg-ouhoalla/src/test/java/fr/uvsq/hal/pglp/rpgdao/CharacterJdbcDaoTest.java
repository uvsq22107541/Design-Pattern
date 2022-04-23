package fr.uvsq.hal.pglp.rpgdao;

import fr.uvsq.hal.pglp.rpg.Ability;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterJdbcDaoTest {

  private static final String DB_URL = "jdbc:derby:memory:testdb;create=true";
  private Connection connection;

  /*
  @Before
  public void  setupBeforeAll() throws SQLException {
    connection = DriverManager.getConnection(DB_URL);
    Statement statement = connection.createStatement();
    statement.execute("CREATE TABLE character(id int, nom VARCHAR(20), force VARCHAR(20), dexterite VARCHAR(20)," +
        "constitution  VARCHAR(20), intelligence VARCHAR(20),sagesse VARCHAR(20), charisme) VARCHAR(20)");
    }

    @Before
    public void setup() throws SQLException {
      Statement statement = connection.createStatement();
      statement.execute("TRUNCATE TABLE characters");
      PreparedStatement psInsert = connection.prepareStatement("INSERT INTO characters VALUES(?, ?, ?, ?, ?, ?,?)");
      psInsert.setString(1, "Dragon");
      psInsert.setString(2, Ability.FORCE.toString());
      psInsert.setString(3, Ability.DEXTERITE.toString());
      psInsert.setString(4, Ability.CONSTITUTION.toString());
      psInsert.setString(5, Ability.INTELLIGENCE.toString());
      psInsert.setString(6, Ability.SAGESSE.toString());
      psInsert.setString(7, Ability.CHARISME.toString());

      psInsert.executeUpdate();

    }

    @Test
    public void selectTest() throws SQLException {
      assertDbState(List.of("Dragon"));

    }

    private void assertDbState(List<String> expectedPersons) throws SQLException {
    List<String> persons = new ArrayList<>();
    Statement statement = connection.createStatement();
    ResultSet rs = statement.executeQuery("SELECT id, nom FROM charcter ORDER BY nom");
    while (rs.next()) {
      persons.add(rs.getString(2));
    }
    Assert.assertEquals(expectedPersons, persons);
  } */
}