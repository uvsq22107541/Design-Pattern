package fr.uvsq.hal.pglp.rpg;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CharacterTest {
  /*@Test
  public void aCharacterShouldWhen() {
    fail("Not implemented");
  }*/

  /*Test de la méthode nonRandomAbilities */
  @Test
  public void nonRandomAbilitiesTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();
    character.nonRandomAbilities(priorites);
    Assert.assertEquals(12, character.getForce().getPriorite());
  }

  /*Test de la méthode setAbilityScore */
  @Test
  public void setAbilityScoreTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();
    character.setAbilityScore(Ability.DEXTERITE,9);
    Assert.assertEquals(9, character.getDexterite().getScore());
  }

  /*Test de la méthode getModifier(Ability ability) */
  @Test
  public void getModificateurTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();

    // On peut changer le characterestique pour tester
    int mod = character.getModificateur(Ability.DEXTERITE);
    if(character.getDexterite().getScore()== 2 || character.getDexterite().getScore()== 3 ) {
      Assert.assertEquals(-4, mod);
    }
    if(character.getDexterite().getScore()== 4 || character.getDexterite().getScore()== 5 ) {
      Assert.assertEquals(-3, mod);
    }
    if(character.getDexterite().getScore()== 6 || character.getDexterite().getScore()== 7 ) {
      Assert.assertEquals(-2, mod);
    }
    if(character.getDexterite().getScore()== 8 || character.getDexterite().getScore()== 9 ) {
      Assert.assertEquals(-1, mod);
    }
    if(character.getDexterite().getScore()== 10 || character.getDexterite().getScore()== 11 ) {
      Assert.assertEquals(0, mod);
    }
    if(character.getDexterite().getScore()== 12 || character.getDexterite().getScore()== 13 ) {
      Assert.assertEquals(1, mod);
    }
    if(character.getDexterite().getScore()== 14 || character.getDexterite().getScore()== 15 ) {
      Assert.assertEquals(2, mod);
    }
    if(character.getDexterite().getScore()== 16 || character.getDexterite().getScore()== 17 ) {
      Assert.assertEquals(3, mod);
    }
    if(character.getDexterite().getScore()== 18 || character.getDexterite().getScore()== 19 ) {
      Assert.assertEquals(4, mod);
    }
    if(character.getDexterite().getScore()== 20) {
      Assert.assertEquals(5, mod);
    }
  }

  /*Test de la méthode (getScore(Ability ability)) */
  @Test
  public void getScoreTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();

    int sum = 0;
    sum += character.getScore(Ability.DEXTERITE);
    sum += character.getScore(Ability.SAGESSE);
    sum += character.getScore(Ability.INTELLIGENCE);
    sum += character.getScore(Ability.FORCE);
    sum += character.getScore(Ability.CONSTITUTION);
    sum += character.getScore(Ability.CHARISME);

    Assert.assertTrue(sum > 60 && sum < 80);
  }

  /* Test de la méthode abilityCheck effectuant un jet de caractéristique */
  @Test
  public void abilityCheckTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();
    boolean check = character.abilityCheck(Ability.DEXTERITE, 20);
    Assert.assertFalse(check);
    check = character.abilityCheck(Ability.DEXTERITE,2);
    Assert.assertTrue(check);
  }

  /* Test de la méthode skillCheck effectuant un jet de compétence */
  @Test
  public void skillCheckTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();
    boolean check = character.skillCheck(Skill.ACROBATIES, 30);
    Assert.assertFalse(check);
    check = character.skillCheck(Skill.ACROBATIES, 2);
    Assert.assertTrue(check);
  }

  /* Test de la méthode setProficencyBonus qui permettra de fixer le bonus de maîtrise
  du personnage. */
  @Test
  public void setProficencyBonusTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(Skill.ACROBATIES, 2)
        .build();
    Assert.assertEquals(2 + character.getModificateur(Ability.DEXTERITE),
        character.getSkillProficiency(Skill.ACROBATIES));

    character.setProficencyBonus(Skill.ACROBATIES,3);
    Assert.assertEquals(3 + character.getModificateur(Ability.DEXTERITE),
        character.getSkillProficiency(Skill.ACROBATIES));
  }
}
