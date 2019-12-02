package Heroes;

import Abilities.Ability;

import java.util.ArrayList;

public abstract class Hero {
  protected int initHP;
  protected int HP;
  protected int XP;
  protected int level;
  private boolean isDead;
  protected HeroesType type;
  protected int[] position = new int[2];
  protected Ability overtimeAbility;
  private int overtimeRoundStart;
  private int overtimeRoundEnd;
  private float overtimeDamage;
  private boolean isParalyzed;
  private boolean isSlammed;

  protected Hero(ArrayList<Integer> position) {
    this.XP = 0;
    this.level = 0;
    this.position[0] = position.get(0);
    this.position[1] = position.get(1);
    this.isDead = false;
    this.overtimeRoundStart = -1;
    this.overtimeRoundEnd = -1;
    this.isParalyzed = false;
    this.isSlammed = false;
  }

  public void setDead(boolean dead) {
    isDead = dead;
  }

  public boolean isDead() {
    return isDead;
  }

  public int[] getPosition() {
    return position;
  }

  public HeroesType getType() {
    return type;
  }

  public int getHP() {
    return HP;
  }

  public int getXP() {
    return XP;
  }

  public int getInitHP() {
    return initHP;
  }

  public int getLevel() {
    return level;
  }

  public int getOvertimeRoundStart() {
    return overtimeRoundStart;
  }

  public boolean isParalyzed() {
    return isParalyzed;
  }

  public void setParalyzed(boolean paralyzed) {
    isParalyzed = paralyzed;
  }

  public boolean isSlammed() {
    return isSlammed;
  }

  public void setSlammed(boolean slammed) {
    isSlammed = slammed;
  }
  /*
   * @param enemy hero level
   * @returns new calculated xp for the winner
   */
  public void win(int loserLevel) {
    this.XP += Math.max(0, 200 - (this.level - loserLevel) * 40);
  }
  /*
   * @returns if exceeds the threshold, hero will level up
   */
  public abstract void levelUp();
  /*
   * @returns calculated threshold
   */
  protected int getThreshold() {
    return (250 + this.level * 50);
  }
  /*
   * Update the hero position according to the input move
   * @param movement character
   */
  public void updatePosition(char move) {
    switch (move) {
      case 'U':
        position[0]--;
        break;
      case 'D':
        position[0]++;
        break;
      case 'L':
        position[1]--;
        break;
      case 'R':
        position[1]++;
        break;
      default:
    }
  }
  /*
   * @param enemy hero, terrain type & round
   * @returns total damage done by a hero
   */
  public abstract int getTotalDamage(Hero enemyHero, char terrainType, int round);
  /*
   * Used for wizard's deflect ability
   * @param terrain type & round
   * @returns total damage done by a hero without modifier
   */
  public abstract int getTotalDamageWithoutModifier(char terrainType, int round);
  /*
   * Apply the damage done by enemy hero
   * @param damage done by enemy hero
   */
  public void takeDamage(long damage) {
    int currentHP = this.HP;
    currentHP -= damage;
    if (currentHP < 0) {
      this.HP = 0;
    } else {
      this.HP = currentHP;
    }
  }
  /*
   * Set overtime ability if exists
   * @param overtime ability, overtime round range & terrain type
   */
  public void setOvertime(
      Ability overtimeAbility, int overtimeRoundStart, int overtimeRoundEnd, char terrainType) {
    this.overtimeAbility = overtimeAbility;
    this.overtimeRoundStart = overtimeRoundStart;
    this.overtimeRoundEnd = overtimeRoundEnd;
    this.overtimeDamage = overtimeAbility.executeOvertimeAbility(this, terrainType);
  }
  /*
   * Deactivate or overtime ability
   * @param round number
   */
  public void updateOvertime(int round) {
    if (round >= overtimeRoundEnd) {
      overtimeRoundEnd = -1;
      overtimeRoundStart = -1;
      overtimeAbility = null;
      isParalyzed = false;
      isSlammed = false;
    } else {
      this.takeDamage((long) this.overtimeDamage);
    }
  }
}
