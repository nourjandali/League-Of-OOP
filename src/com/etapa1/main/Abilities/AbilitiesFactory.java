package com.etapa1.main.Abilities;

public final class AbilitiesFactory {
  private static AbilitiesFactory instance = null;

  private static class FireblastModifier {
    public static final double ROGUE = -0.2;
    public static final double KNIGHT = 0.2;
    public static final double PYROMANCER = -0.1;
    public static final double WIZARD = 0.05;
  }

  private static class Ignite {
    public static final double ROGUE = -0.2;
    public static final double KNIGHT = 0.2;
    public static final double PYROMANCER = -0.1;
    public static final double WIZARD = 0.05;
  }

  private static class Execute {
    public static final double ROGUE = 0.15;
    public static final double KNIGHT = 0;
    public static final double PYROMANCER = 0.1;
    public static final double WIZARD = -0.2;
  }

  private static class Slam {
    public static final double ROGUE = -0.2;
    public static final double KNIGHT = 0.2;
    public static final double PYROMANCER = -0.1;
    public static final double WIZARD = 0.05;
  }

  private static class Drain {
    public static final double ROGUE = -0.2;
    public static final double KNIGHT = 0.2;
    public static final double PYROMANCER = -0.1;
    public static final double WIZARD = 0.05;
  }

  private static class Deflect {
    public static final double ROGUE = 0.2;
    public static final double KNIGHT = 0.4;
    public static final double PYROMANCER = 0.3;
  }

  private static class Backstab {
    public static final double ROGUE = 0.2;
    public static final double KNIGHT = -0.1;
    public static final double PYROMANCER = 0.25;
    public static final double WIZARD = 0.25;
  }

  private static class Paralysis {
    public static final double ROGUE = -0.1;
    public static final double KNIGHT = -0.2;
    public static final double PYROMANCER = 0.2;
    public static final double WIZARD = 0.25;
  }

  public static AbilitiesFactory getInstance() {
    if (instance == null) {
      instance = new AbilitiesFactory();
    }
    return instance;
  }

  Ability createAbility(final String type, final int level) {
    switch (type) {
      case "Fireblast": return new Fireblast(level);
      default : return null;
    }
  }
}
