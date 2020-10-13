/*
 * Date: 2020-09-20
 * File Name: Magical.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Abstract class to remove redundancy from Magical character classes
 * @version 1.0
 * @author Adam Bergman
 */
public abstract class Magical extends Character {
    protected List<Spell> spells = new ArrayList<>();

    public Magical (String name, CharacterClass cc, int level) throws Exception {
        super(name, cc, level);
    }

    /**
     * Adds a spell to the character's list of spells
     * @param spell to be added
     * @throws Exception if the character already knows the spell or if it cannot learn it
     */
    public abstract void learnSpell(Spell spell) throws Exception;

    /**
     * Get the spells that the character knows
     * @return the spells that the character knows
     */
    public abstract List<Spell> getKnownSpells();
}
