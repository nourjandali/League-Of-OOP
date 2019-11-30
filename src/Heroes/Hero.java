package Heroes;

import java.util.ArrayList;

// Note : Heroes may kill each other. In this case, both will receive the appropriate XP.
public abstract class Hero {
  protected int initHP;
  protected double HP;
  protected int XP;
  protected int level;
  protected HeroesType type;
  protected int[] position = new int[2];

  protected Hero(ArrayList<Integer> position) {
    this.XP = 0;
    this.level = 0;
    this.position[0] = position.get(0);
    this.position[1] = position.get(1);
  }

  public void win(int loserLevel) {
    // Calculating new XP
    this.XP += Math.max(0, 200 - (this.level - loserLevel) * 40);
  }

  protected int getThreshold() {
    return (250 + this.level * 50);
  }

  public int[] getPosition() {
    return position;
  }

  public void updatePosition(char move) {
    switch(move) {
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
  public abstract double getTotalDamage(HeroesType enemyType, char terrainType, int round);

  public HeroesType getType() {
    return type;
  }

  public double getHP() {
    return HP;
  }

  public void takeDamage(double damage) {
    double currentHP = this.HP;
    currentHP -= damage;
    if(currentHP < 0){
      this.HP = 0;
    }
    else {
      this.HP = currentHP;
    }
  }
}
