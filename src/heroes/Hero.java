package heroes;

import abilities.Ability;
import main.Constants;

import java.util.ArrayList;

public abstract class Hero {
  protected int initHP;
  protected int hp;
  protected int xp;
  protected int level;
  private boolean isDead;
  protected HeroesType type;
  protected int[] position = new int[2];
  protected Ability overtimeAbility;
  private int overtimeRoundStart;
  private int overtimeRoundEnd;
  private float overtimeDamage;
  private boolean isStunned;

  protected Hero(final ArrayList<Integer> position) {
    this.xp = 0;
    this.level = 0;
    this.position[0] = position.get(0);
    this.position[1] = position.get(1);
    this.isDead = false;
    this.overtimeRoundStart = -1;
    this.overtimeRoundEnd = -1;
    this.isStunned = false;
  }

  public final void setDead(final boolean dead) {
    isDead = dead;
  }

  public final boolean isDead() {
    return isDead;
  }

  public final int[] getPosition() {
    return position;
  }

  public final HeroesType getType() {
    return type;
  }

  public final int getHP() {
    return hp;
  }

  public final int getXP() {
    return xp;
  }

  public final int getInitHP() {
    return initHP;
  }

  public final int getLevel() {
    return level;
  }

  public final int getOvertimeRoundStart() {
    return overtimeRoundStart;
  }

  public final boolean isStunned() {
    return isStunned;
  }

  public final void setStunned(final boolean stunned) {
    isStunned = stunned;
  }
  /*
   * @param enemy hero level
   * @returns new calculated xp for the winner
   */
  public void win(final int loserLevel) {
    this.xp +=
        Math.max(
            0,
            Constants.WIN_CONSTANT - (this.level - loserLevel) * Constants.WIN_CONSTANT_PER_LEVEL);
  }
  /*
   * @returns if exceeds the threshold, hero will level up
   */
  public abstract void levelUp();
  /*
   * @returns calculated threshold
   */
  protected int getThreshold() {
    return (Constants.THRESHOLD_COMSTANT + this.level * Constants.THRESHOLD_COMSTANT_PER_LEVEL);
  }
  /*
   * Update the hero position according to the input move
   * @param movement character
   */
  public void updatePosition(final char move) {
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
  public void takeDamage(final long damage) {
    int currentHP = this.hp;
    currentHP -= damage;
    if (currentHP < 0) {
      this.hp = 0;
    } else {
      this.hp = currentHP;
    }
  }
  /*
   * Set overtime ability if exists
   * @param overtime ability, overtime round range & terrain type
   */
  public void setOvertime(
      final Ability overtimeAbility,
      final int overtimeRoundStart,
      final int overtimeRoundEnd,
      final char terrainType) {
    this.overtimeAbility = overtimeAbility;
    this.overtimeRoundStart = overtimeRoundStart;
    this.overtimeRoundEnd = overtimeRoundEnd;
    this.overtimeDamage = overtimeAbility.executeOvertimeAbility(this, terrainType);
  }
  /*
   * Deactivate or overtime ability
   * @param round number
   */
  public void updateOvertime(final int round) {
    if (round >= overtimeRoundEnd) {
      overtimeRoundEnd = -1;
      overtimeRoundStart = -1;
      overtimeAbility = null;
      isStunned = false;
    } else {
      this.takeDamage((long) this.overtimeDamage);
    }
  }
}
