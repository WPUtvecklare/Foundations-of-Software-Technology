/*
 * Date: 2020-09-20
 * File Name: Mage.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.List;

/**
 * Class description: Represents the character Mage
 * @version 1.0
 * @author Adam Bergman
 */
public class Mage extends Magical {

    public Mage (String name, int level) throws Exception {
        super(name, CharacterClass.Mage, level);
    }

    /**
     * Adds the given spell to the character's list of spells
     * @param spell to be added
     * @throws Exception if the character aren't allowed to learn the spell, or if it already knows it
     */
    @Override
    public void learnSpell(Spell spell) throws Exception {
        if (!Rules.MAGE_SCHOOLS.contains(spell.getSchoolAsEnum())) {
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
