/*
 * Date: 2020-09-20
 * File Name: Spell.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

/**
 * Class Description: Represents the spell of a character
 * @version 1.0
 * @author Adam Bergman
 */
public class Spell {
    private String name;
    private SpellSchool school;

    public Spell (String name, SpellSchool school) throws Exception {
        this.setName(name);
        this.school = school;
    }

    /**
     * Get the name of the spell
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Get the school of the spell
     * @return the school
     */
    public String getSchool () {
        return school.name();
    }

    /**
     * Get the school as Enum
     * @return the school as enum
     */
    public SpellSchool getSchoolAsEnum () {
        return school;
    }

    /**
     * Validate the spell name
     * @param name to be set
     * @throws Exception if it does not match the required condition
     */
    public void setName (String name) throws Exception {
        if (name.length() < Rules.MIN_SPELL_NAME) {
            throw new Exception("Name cannot be shorter than " + Rules.MIN_SPELL_NAME);
        } else if (name.length() > Rules.MAX_SPELL_NAME) {
            throw new Exception("School cannot be greater than " + Rules.MAX_SPELL_NAME);
        }

        this.name = name;
    }
}
