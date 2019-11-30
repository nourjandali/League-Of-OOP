package Abilities;

public final class AbilitiesFactory {
  private static AbilitiesFactory instance = null;

  public static AbilitiesFactory getInstance() {
    if (instance == null) {
      instance = new AbilitiesFactory();
    }
    return instance;
  }

  public Ability createAbility(final String type, final int level, final int round) {
    switch (type) {
      case "Fireblast":
        return new Fireblast(level);
      case "Ignite":
        return new Ignite(level, round);

      default:
        return null;
    }
  }
}
