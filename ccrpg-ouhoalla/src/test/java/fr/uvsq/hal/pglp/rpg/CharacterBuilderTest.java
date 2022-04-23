package fr.uvsq.hal.pglp.rpg;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CharacterBuilderTest {
  /* Test du pattern Builder en créant un personnage avec les paramètres obligatoires (Nom)*/
  @Test
  public void unPersonnageAvecParamObligTest() {
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("DUNGEONS AND DRAGON", priorites).build();
    Assert.assertEquals("DUNGEONS AND DRAGON", character.getNom());
  }

  /* Test du pattern Builder en créant un personnage avec les paramètres obligatoires (Carecteristiques)
   + Test de génération des personnages équilibrés (la somme des valeurs des caractéristiques comprise entre 60 et 80)
  */
  @Test
  public void parametresCorrecteTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("DUNGEONS AND DRAGON", priorites).build();
    Assert.assertEquals("DUNGEONS AND DRAGON", character.getNom());
    int score = character.getForce().getScore();

    score += character.getDexterite().getScore();

    score += character.getConstitution().getScore();

    score += character.getIntelligence().getScore();

    score += character.getSagesse().getScore();

    score += character.getCharisme().getScore();

    Assert.assertTrue(score >60 && score < 80);
  }

  /* Test de génération des valeurs des caracteristiques selon l'ordre passé en parametres*/
  @Test
  public void parametresCorrecteOrdreTest(){
    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("AAA", priorites).build();
    Assert.assertEquals("AAA", character.getNom());

    int s1 = character.getDexterite().getScore();
    int s2 = character.getSagesse().getScore();
    Assert.assertTrue(s1 >= s2);

    s1 = character.getSagesse().getScore();
    s2 = character.getIntelligence().getScore();
    Assert.assertTrue(s1 >= s2);

    s1 = character.getIntelligence().getScore();
    s2 = character.getForce().getScore();
    Assert.assertTrue(s1 >= s2);

    s1 = character.getForce().getScore();
    s2 = character.getConstitution().getScore();
    Assert.assertTrue(s1 <= s2);

    s1 = character.getConstitution().getScore();
    s2 = character.getCharisme().getScore();
    Assert.assertTrue(s1 >= s2);
  }

  /* Test de la création d'un caractere avec un seul parametre optionnel (compétence)*/
  @Test
  public void charachterAvecUneCompetenceTest(){
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
    Assert.assertEquals("Dragon", character.getNom());
    Assert.assertTrue(character.getCompetences().contains(Skill.ACROBATIES));
  }

 /* Test de la création d'un caractere avec plusieurs parametres optionnels (compétences)*/
 @Test
  public void charachterAvecDesCompetencesTest(){

    ArrayList<Skill> competences = new ArrayList<>();
    ArrayList<Integer> bonus = new ArrayList<>();

    competences.add(Skill.ATHLETHISME);
    competences.add(Skill.ESCAMOTAGE);
    bonus.add(1);
    bonus.add(2);

    ArrayList<Ability> priorites = new ArrayList<>();
    priorites.add(Ability.DEXTERITE);
    priorites.add(Ability.SAGESSE);
    priorites.add(Ability.INTELLIGENCE);
    priorites.add(Ability.FORCE);
    priorites.add(Ability.CONSTITUTION);
    priorites.add(Ability.CHARISME);

    Character character = new Character.Builder("Dragon", priorites)
        .competence(competences, bonus)
        .build();

    Assert.assertEquals(competences, character.getCompetences());
    Assert.assertEquals(bonus,  character.getCompetencesBonus());
  }
}
