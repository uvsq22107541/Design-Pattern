package fr.uvsq.hal.pglp.rpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamTest {
  private Character dragon;
  private Character elve;
  private Team team;

  /* Initialisation des test de la classe Team */
  @Before
  public void setup() {
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    dragon = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();

    priorites = new ArrayList<>();
    priorites.add(Ability.CHARISME);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.DEXTERITE);

    elve = new Character.Builder("Dragon", priorites)
        .competence(Skill.INTIMIDATION, 3)
        .build();

    team = new Team();
    team.add(dragon);
    team.add(elve);
  }

  /* tester la méthode unTeamContientDesCharacters()  */
  @Test
  public void unTeamContientDesCharactersTest() {
    Assert.assertTrue(team.contains(dragon));
    Assert.assertTrue(team.contains(elve));
  }

  /* tester qu'un team peut contenir d'autres team  */
  @Test
  public void unTeamContientTeamTest() {
    Team team1 = new Team();
    team.add(team1);
  }

  /* tester la récursivité de la recherche d'un team  */
  @Test
  public void rechercheRecursiveTest() {
    Team team1 = new Team();
    team1.add(dragon);
    Team team2 = new Team();
    team2.add(elve);

    Assert.assertTrue(team1.contains(dragon));
  }

  /* tester qu'un team ne contient pas lui meme directement  */
  @Test
  public void neContientPasLuiMemeTest() {
    team.add(team);
    Assert.assertFalse(team.contains(team));
  }

  /* tester qu'un team ne contient pas lui meme indirectement  */
  @Test
  public void unTeameSeContientPasLuiMemeMemeIndirectement() {
    Team team1 = new Team();
    team1.add(dragon);
    Team team2 = new Team();
    team2.add(elve);
    team2.add(team1);
    assertFalse(team1.contains(team1));
  }

  /* tester la méthode remove qui (supprime un team)  */
  @Test
  public void removeTest() {
    team.remove(dragon);
    Assert.assertFalse(team.contains(dragon));
  }

  /* Test de la méthode getSize */
  @Test
  public void getSizeTest(){
    Assert.assertEquals(2, team.getSize());
  }

  /* Test De Iterator */
  /* Tester qu'un team peut etre parcouru */
  @Test
  public void unTeamPeutEtreParcourus() {
    final List<MembreDeTeam> expectedCharachters = List.of(dragon, elve);
    List<MembreDeTeam> visitedCharachter = new ArrayList<>();
    for (MembreDeTeam element : team) {
      visitedCharachter.add(element);
    }
    assertEquals(expectedCharachters, visitedCharachter);
  }

  /* Tester qu'une team imbriquée peut etre parcouru */
  @Test
  public void unTeamImbriquePeutEtreParcourus() {
    final List<MembreDeTeam> expectedCharacters = List.of(dragon, elve);
    List<MembreDeTeam> visitedChracters = new ArrayList<>();
    Team team1 = new Team();
    team1.add(dragon);
    Team team2 = new Team();
    team2.add(elve);
    team1.add(team2);
    for (MembreDeTeam element : team1) {
      visitedChracters.add(element);
    }
    assertEquals(expectedCharacters, visitedChracters);
  }

  /* Tester qu'une team imbriquée peut etre parcouru récursivement */
  @Test
  public void unAutreTeamImbriquePeutEtreParcourus() {
    final List<MembreDeTeam> expectedCharacters = List.of(dragon, elve, dragon, elve, dragon, elve,
        elve, dragon, elve, dragon);
    List<MembreDeTeam> visitedCharacters = new ArrayList<>();
    Team team1 = new Team();
    team1.add(dragon);
    Team team2 = new Team();
    team2.add(elve);
    Team team3 = new Team();
    team3.add(dragon); team3.add(elve);
    team2.add(team3);
    team2.add(dragon);
    team1.add(team2);
    team1.add(elve);
    team1.add(team2);
    for (MembreDeTeam element : team1) {
      visitedCharacters.add(element);
    }
    assertEquals(expectedCharacters, visitedCharacters);
  }
}