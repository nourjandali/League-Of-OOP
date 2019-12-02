package abilities;

import heroes.Hero;
import heroes.HeroesType;
import java.util.HashMap;

public abstract class Ability {
  protected HashMap<HeroesType, Float> modifiers = new HashMap<HeroesType, Float>();
  /*
   * @returns ability damage without modifier
   */
  public abstract float execute();
  /*
   * @param enemy hero in which the ability is executed
   * @returns ability damage with applying enemy hero modifier
   */
  public abstract float execute(Hero enemyHero);
  /*
   * @param enemy hero in which the overtime ability is executed & terrain type
   * @returns overtime ability damage
   */
  public abstract float executeOvertimeAbility(Hero enemyHero, char terrainType);
}
