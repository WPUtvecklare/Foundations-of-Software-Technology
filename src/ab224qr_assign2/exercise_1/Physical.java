/*
 * Date: 2020-09-20
 * File Name: Physical.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Abstract class to remove redundancy from Physical character classes
 * @version 1.0
 * @author Adam Bergman
 */
public abstract class Physical extends Character {
    protected List<Ability> abilities = new ArrayList<>();

    public Physical (String name, CharacterClass cc, int level) throws Exception {
        super(name, cc, level);
    }

    /**
     * Adds an ability to the character's list of abilities
     * @param ability to be added
     * @throws Exception if the character already knows the ability or if it cannot learn it
     */
    public abstract void learnAbility(Ability ability) throws Exception;

    /**
     * Get the abilities that the character knows
     * @return the abilities that the character knows
     */
    public abstract List<Ability> getKnownAbilities();
}
