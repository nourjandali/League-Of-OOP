package Abilities;

public final class AbilitiesFactory {
  private static AbilitiesFactory instance = null;

  public static AbilitiesFactory getInstance() {
    if (instance == null) {
      instance = new AbilitiesFactory();
    }
    return instance;
  }
  // Used to create any chosen ability
  public Ability createAbility(final String type, final int level, final int round, char terrainType) {
    switch (type) {
      case "Fireblast":
        return new Fireblast(level);
      case "Ignite":
        return new Ignite(level, round);
      case "Execute":
        return new Execute(level);
      case "Slam":
        return new Slam(level);
      case "Backstab":
        return new Backstab(level);
      case "Paralysis":
        return new Paralysis(level);
      case "Drain":
        return new Drain(level);
      case "Deflect":
        return new Deflect(level, round, terrainType);
      default:
        return null;
    }
  }
}
