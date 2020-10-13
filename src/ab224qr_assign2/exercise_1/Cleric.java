/*
 * Date: 2020-09-20
 * File Name: Cleric.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.List;

/**
 * Class description: Represents the character Cleric
 * @version 1.0
 * @author Adam Bergman
 */
public class Cleric extends Magical {

    public Cleric (String name, int level) throws Exception {
        super(name, CharacterClass.Cleric, level);
    }

    /**
     * Adds the given spell to the character's list of spells
     * @param spell to be added
     * @throws Exception if the character aren't allowed to learn the spell, or if it already knows it
     */
    @Override
    public void learnSpell (Spell spell) throws Exception {
        if (!Rules.CLERIC_SCHOOLS.contains(spell.getSchoolAsEnum())) {
            throw new Exception(spell.getSchool() + " is not allowed for " + this.getCharacter());
        }

        if (spells.contains(spell)) {
            throw new Exception(this.getName() + " Already knows " + spell.getName());
        }

        spells.add(spell);
    }

    /**
     * Get the spells that the character knows
     * @return the spells that the character knows
     */
    public List<Spell> getKnownSpells () {
        return spells;
    }
}
