package fr.uvsq.hal.pglp.rpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * La classe <code>Character</code> représente un personnage de JdR.
 *
 * @author Sarah,Melissa
 * @version 2022
 */
public class Character implements MembreDeTeam {
  private final String nom;

  private final Ability force;
  private final Ability dexterite;
  private final Ability constitution;
  private final Ability intelligence;
  private final Ability sagesse;
  private final Ability charisme;

  private ArrayList<Skill> competences;
  private ArrayList<Integer> competencesBonus;

  private Character(Builder builder) {
    nom = builder.nom;
    force = builder.force;
    dexterite = builder.dexterite;
    constitution = builder.dexterite;
    intelligence = builder.intelligence;
    sagesse = builder.sagesse;
    charisme = builder.charisme;

    competences = builder.competences;
    competencesBonus = builder.competencesBonus;
  }
  // getters

  public String getNom() {
    return nom;
  }

  public Ability getForce() {
    return force;
  }

  public Ability getDexterite() {
    return dexterite;
  }

  public Ability getConstitution() {
    return constitution;
  }

  public Ability getIntelligence() {
    return intelligence;
  }

  public Ability getSagesse() {
    return sagesse;
  }

  public Ability getCharisme() {
    return charisme;
  }

  public ArrayList<Skill> getCompetences() {
    return competences;
  }

  public ArrayList<Integer> getCompetencesBonus() {
    return competencesBonus;
  }

  /** getScore. */
  public int getScore(Ability ability) {
    switch (ability) {
      case FORCE: return this.force.getScore();
      case DEXTERITE: return this.dexterite.getScore();
      case CONSTITUTION: return this.constitution.getScore();
      case INTELLIGENCE: return this.intelligence.getScore();
      case SAGESSE: return this.sagesse.getScore();
      case CHARISME: return this.charisme.getScore();
      default: return 0;
    }
  }

  /** getModificateur. */
  public int getModificateur(Ability ability) {
    switch (ability) {
      case FORCE: return this.force.getModificateur();
      case DEXTERITE: return this.dexterite.getModificateur();
      case CONSTITUTION: return this.constitution.getModificateur();
      case INTELLIGENCE: return this.intelligence.getModificateur();
      case SAGESSE: return this.sagesse.getModificateur();
      case CHARISME: return this.charisme.getModificateur();
      default: return 0;
    }
  }

  /** nonRandomAbilities. */
  public void nonRandomAbilities(ArrayList<Ability> priorities) {
    this.force.setPriorite(15 - priorities.indexOf(Ability.FORCE));
    this.dexterite.setPriorite(15 - priorities.indexOf(Ability.DEXTERITE));
    this.constitution.setPriorite(15 - priorities.indexOf(Ability.CONSTITUTION));
    this.intelligence.setPriorite(15 - priorities.indexOf(Ability.INTELLIGENCE));
    this.sagesse.setPriorite(15 - priorities.indexOf(Ability.SAGESSE));
    this.charisme.setPriorite(15 - priorities.indexOf(Ability.CHARISME));
  }

  /** setAbilityScore. */
  public void setAbilityScore(Ability ability, int score) {
    switch(ability) {
      case FORCE: this.force.setScore(score);
      case DEXTERITE: this.dexterite.setScore(score);
      case CONSTITUTION: this.constitution.setScore(score);
      case INTELLIGENCE: this.intelligence.setScore(score);
      case SAGESSE: this.sagesse.setScore(score);
      case CHARISME: this.charisme.setScore(score);
    }
  }

  /** abilityCheck. */
  public boolean abilityCheck(Ability ability, int defec) {
    switch (ability) {
      case FORCE: return ((this.getForce().getScore() + this.getForce().getModificateur()) > defec);
      case DEXTERITE: return ((this.getDexterite().getScore() + this.getDexterite().getModificateur()) > defec);
      case CONSTITUTION: return ((this.getConstitution().getScore() + this.getConstitution().getModificateur()) > defec);
      case INTELLIGENCE: return ((this.getIntelligence().getScore() + this.getIntelligence().getModificateur()) > defec);
      case SAGESSE: return ((this.getSagesse().getScore() + this.getSagesse().getModificateur()) > defec);
      case CHARISME: return ((this.getCharisme().getScore() + this.getCharisme().getModificateur()) > defec);
      default: return false;
    }
  }

  /** skillCheck. */
  public boolean skillCheck(Skill skill, int defec) {
    int rand = ((int) Math.floor(Math.random() * 20));
    rand += this.getSkillProficiency(skill);
    return rand > defec;
  }

  /** getSkillProficiency. */
  public int getSkillProficiency(Skill skill) {
    if(this.competences.contains(skill)){
      int i = this.competences.indexOf(skill);
      return this.getModificateur(skill.getAssociatedAbility()) + this.competencesBonus.get(i);
    }
    else return this.getModificateur(skill.getAssociatedAbility());
  }

  /** setProficencyBonus. */
  public void setProficencyBonus(Skill skill, int bonus){
    if (this.competences.contains(skill)){
      this.competencesBonus.set(this.competences.indexOf(skill), bonus);
    }
    else {
    this.competences.add(skill);
    this.competencesBonus.add(bonus);
    }
  }

  public static class Builder {
    // Obligatoire
    private final String nom;
    private Ability force = Ability.FORCE;
    private Ability dexterite = Ability.DEXTERITE;
    private Ability constitution = Ability.CONSTITUTION;
    private Ability intelligence = Ability.INTELLIGENCE;
    private Ability sagesse = Ability.SAGESSE;
    private Ability charisme = Ability.CHARISME;

    private ArrayList<Skill> competences;
    private ArrayList<Integer> competencesBonus;

 private int calculateAbility(){
      int sum = 0;
      List<Integer> rand = new ArrayList<>();
      Random random = new Random();
      for(int i=0; i<4; i++){
        rand.add((random.nextInt(6)));
      }
      Collections.sort(rand);
      rand.remove(0);

      for (Integer integer : rand) {
        sum += integer;
      }
      return sum;
    }

    private List<Integer> calculateAbilities(){
      List<Integer> abilities = new ArrayList<>();
      /* must be in range [60,80] */
      int sum = 0;
      while (sum < 60 || sum > 80){
        sum = 0;
        abilities = new ArrayList<>();
        for(int i=0; i<6; i++){
          abilities.add(calculateAbility());
        }
        for (Integer ability : abilities) {
          sum += ability;
        }
      }
      Collections.sort(abilities);
      Collections.reverse(abilities);
      return abilities;
    }



    /**
     * Les paramètres obligatoires
     * @param nom obligatoire
     * @param priorites obligatoire
     */

    public Builder(String nom, ArrayList<Ability> priorites){
      this.nom = nom;

      List<Integer> abilitiesValeurs = calculateAbilities();

      this.force.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.FORCE)));
      this.dexterite.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.DEXTERITE)));
      this.constitution.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.CONSTITUTION)));
      this.intelligence.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.INTELLIGENCE)));
      this.sagesse.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.SAGESSE)));
      this.charisme.setScore(abilitiesValeurs.get(priorites.indexOf(Ability.CHARISME)));

      //Initialisation des modificateurs des caracteristiques
      this.force.setModificateur();
      this.dexterite.setModificateur();
      this.constitution.setModificateur();
      this.intelligence.setModificateur();
      this.sagesse.setModificateur();
      this.charisme.setModificateur();

      /* Pas obligatoire */
      this.competences = new ArrayList<>();
      this.competencesBonus = new ArrayList<>();
    }

    public Character build(){
      return new Character(this);
    }

    /** . */
    public Builder competence (Skill competence, int bonus){
      if(competences.contains(competence)){
        competencesBonus.add(competences.indexOf(competence), bonus);;
      }
      else{
      competences.add(competence);
      competencesBonus.add(bonus);
    }
      return this;
    }

    /** . */
    // Polymorphisme
    public Builder competence(ArrayList<Skill> skills, ArrayList<Integer> bonus) {
       this.competences = skills;
       this.competencesBonus = bonus;
       return this;
    }
  }
}
