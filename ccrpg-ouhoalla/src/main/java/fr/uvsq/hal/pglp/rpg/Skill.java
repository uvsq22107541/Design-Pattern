package fr.uvsq.hal.pglp.rpg;

/**
 * La classe <code>Ability</code> représente une compétence d'un personnage.
 *
 * @author sarah&Melissa
 * @version 2022
 */
public enum Skill {
  ATHLETHISME(Ability.FORCE),
  ACROBATIES(Ability.DEXTERITE), DISCRETION(Ability.DEXTERITE), ESCAMOTAGE(Ability.DEXTERITE),
  ARCANES(Ability.INTELLIGENCE), INVESTIGATION(Ability.INTELLIGENCE), HISTOIRE(Ability.INTELLIGENCE),
  NATURE(Ability.INTELLIGENCE), RELIGION(Ability.INTELLIGENCE),
  DRESSAGE(Ability.SAGESSE), INTUITION(Ability.SAGESSE), MEDECINE(Ability.SAGESSE),
  PERCEPTION(Ability.SAGESSE), SURVIE(Ability.SAGESSE),
  INTIMIDATION(Ability.CHARISME), PERSUASION(Ability.CHARISME), REPRESENTATION(Ability.CHARISME),
  TROMPERIE(Ability.CHARISME);

  private final Ability associatedAbility;

  Skill(Ability associatedAbility){
    this.associatedAbility = associatedAbility;
  }

  public Ability getAssociatedAbility() {
    return associatedAbility;
  }
}
