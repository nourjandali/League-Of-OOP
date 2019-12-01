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
  private int overtimeRoundEnd;
  private Hero overtimeHero;
  private float overtimeDamage;

  protected Hero(ArrayList<Integer> position) {
    this.XP = 0;
    this.level = 0;
    this.position[0] = position.get(0);
    this.position[1] = position.get(1);
    this.isDead = false;
    this.overtimeRoundEnd = -1;
  }

  public void win(int loserLevel) {
    // Calculating new XP
    this.XP += Math.max(0, 200 - (this.level - loserLevel) * 40);
  }

  public abstract void levelUp();

  public void setDead(boolean dead) {
    isDead = dead;
  }

  public boolean isDead() {
    return isDead;
  }

  protected int getThreshold() {
    return (250 + this.level * 50);
  }

  public int[] getPosition() {
    return position;
  }

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

  public abstract int getTotalDamage(Hero enemyHero, char terrainType, int round);

  public abstract int getTotalDamageWithoutModifier(char terrainType, int round);

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

  public void takeDamage(long damage) {
    int currentHP = this.HP;
    currentHP -= damage;
    if (currentHP < 0) {
      this.HP = 0;
    } else {
      this.HP = currentHP;
    }
  }

  public int getLevel() {
    return level;
  }

  public void setOvertime(Ability overtimeAbility, int overtimeRoundEnd, Hero overtimeHero) {
    this.overtimeAbility = overtimeAbility;
    this.overtimeRoundEnd = overtimeRoundEnd;
    this.overtimeHero = overtimeHero;
    this.overtimeDamage = overtimeAbility.executeOvertimeAbility(this);
  }

  public void updateOvertime(int round) {
    if (round > overtimeRoundEnd) {
      overtimeRoundEnd = -1;
    } else {
      this.HP -= this.overtimeDamage;
    }
  }
}
