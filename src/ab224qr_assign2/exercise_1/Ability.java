/*
 * Date: 2020-09-20
 * File Name: Ability.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

/**
 * Class Description: Represents the ability of a character
 * @version 1.0
 * @author Adam Bergman
 */
public class Ability {
    private String name;
    private Proficiency proficiency;

    public Ability (String name, Proficiency proficiency) throws Exception {
        this.setName(name);
        this.proficiency = proficiency;
    }

    /**
     * Get the name of the ability
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the proficiency of the ability
     * @return the proficiency
     */
    public String getProficiency() {
        return proficiency.name();
    }

    /**
     * Get the proficiency as Enum
     * @return the proficiency as enum
     */
    public Proficiency getProficiencyAsEnum () {
        return proficiency;
    }

    /**
     * Validate the ability name
     * @param name to be set
     * @throws Exception if it does not match the required condition
     */
    private void setName (String name) throws Exception {
        if (name.length() < Rules.MIN_ABILITY_NAME) {
            throw new Exception("Name is too short. Minimum length: " + Rules.MIN_ABILITY_NAME);
        } else if (name.length() > Rules.MAX_ABILITY_NAME) {
            throw new Exception("Name is too long. Maximum length: " + Rules.MAX_ABILITY_NAME);
        }

        this.name = name;
    }
}
