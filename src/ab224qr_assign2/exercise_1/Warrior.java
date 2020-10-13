/*
 * Date: 2020-09-20
 * File Name: Warrior.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.List;

/**
 * Class description: Represents the character Warrior
 * @version 1.0
 * @author Adam Bergman
 */
public class Warrior extends Physical {

    public Warrior (String name, int level) throws Exception {
        super(name, CharacterClass.Warrior, level);
    }

    /**
     * Adds the given ability to the character's list of abilities
     * @param ability to be added
     * @throws Exception if the character aren't allowed to learn the ability, or if it already knows it
     */
    public void learnAbility (Ability ability) throws Exception {
        if (!Rules.WARRIOR_PROFICIENCIES.contains(ability.getProficiencyAsEnum())) {
            throw new Exception(ability.getProficiency() + " is not allowed for " + this.getCharacter());
        }

        if (abilities.contains(ability)) {
            throw new Exception(this.getName() + " Already knows " + ability.getName());
        }

        abilities.add(ability);
    }

    /**
     * Get the abilities that the character knows
     * @return the abilities that the character knows
     */
    public List<Ability> getKnownAbilities () {
        return abilities;
    }
}
