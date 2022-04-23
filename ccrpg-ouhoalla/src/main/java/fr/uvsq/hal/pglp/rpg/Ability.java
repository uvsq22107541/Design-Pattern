package fr.uvsq.hal.pglp.rpg;

/**
 * La classe <code>Ability</code> représente une caractéristique d'un personnage.
 *
 * @author sarah & melissa
 * @version 2022
 */
public enum Ability {
 FORCE, DEXTERITE, CONSTITUTION, INTELLIGENCE, SAGESSE, CHARISME;

 private int score;
 private int modificateur;
 private int priorite;

 //setters
 public void setScore(int score) {
  this.score = score;
 }

 public void setModificateur() {
  // Attribuer le modificateur automatiquement lors de la création
  if(this.score == 2 || this.score == 3){this.modificateur = -4;}
  if(this.score == 4 || this.score == 5){this.modificateur = -3;}
  if(this.score == 6 || this.score == 7){this.modificateur = -2;}
  if(this.score == 8 || this.score == 9){this.modificateur = -1;}
  if(this.score == 10 || this.score == 11){this.modificateur = 0;}
  if(this.score == 12 || this.score == 13){this.modificateur = 1;}
  if(this.score == 14 || this.score == 15){this.modificateur = 2;}
  if(this.score == 16 || this.score == 17){this.modificateur = 3;}
  if(this.score == 18 || this.score == 19){this.modificateur = 4;}
  if(this.score == 20){this.modificateur = 5;}
}

 public void setPriorite(int priorite) {
  this.priorite = priorite;
 }

// getters
 public int getScore() {
  return score;
 }

 public int getModificateur() {
  return modificateur;
 }

 public int getPriorite() {
  return priorite;
 }
}
