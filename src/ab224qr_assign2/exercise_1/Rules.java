/*
 * Date: 2020-09-20
 * File Name: Rules.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.List;

import static ab224qr_assign2.exercise_1.Proficiency.*;
import static ab224qr_assign2.exercise_1.SpellSchool.*;

/**
 * Class description: The main point of where different rules are defined
 * @version 1.0
 * @author Adam Bergman
 */
public class Rules {
    // Allowed spell schools for Mage
    public static final List<SpellSchool> MAGE_SCHOOLS = List.of(Alteration, Evocation);
    // Allowed spell schools for Cleric
    public static final List<SpellSchool> CLERIC_SCHOOLS = List.of(Restoration, Divination);
    // Allowed spell schools for Warrior
    public static final List<Proficiency> WARRIOR_PROFICIENCIES = List.of(Athletics, Survival);
    // Allowed spell schools for Rogue
    public static final List<Proficiency> ROGUE_PROFICIENCIES = List.of(Acrobatics, Stealth);

    // Allowed attributes
    public static final int MIN_STRENGTH = 0;
    public static final int MIN_AGILITY = 0;
    public static final int MIN_INTELLIGENCE = 0;
    public static final int MIN_WISDOM = 0;

    public static final int MAX_STRENGTH = 100;
    public static final int MAX_AGILITY = 100;
    public static final int MAX_INTELLIGENCE = 100;
    public static final int MAX_WISDOM = 100;

    // Allowed length for ability name
    public static final int MIN_ABILITY_NAME = 3;
    public static final int MAX_ABILITY_NAME = 20;

    // Allowed length for spell name
    public static final int MIN_SPELL_NAME = 3;
    public static final int MAX_SPELL_NAME = 20;

    // Allowed length for a character's name
    public static final int MIN_CHARACTER_NAME = 3;
    public static final int MAX_CHARACTER_NAME = 20;

    // Allowed levels
    public static final int MIN_LEVEL = 0;
    public static final int MAX_LEVEL = 200;
}
