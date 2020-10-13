/*
 * Date: 2020-09-20
 * File Name: Attributes.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

/**
 * Class Description: Represents the attributes of a character
 * @version 1.0
 * @author Adam Bergman
 */
public class Attributes {
    private int strength;
    private int agility;
    private int intelligence;
    private int wisdom;

    public Attributes (int strength, int agility, int intelligence, int wisdom) throws Exception {
        setStrength(strength);
        setAgility(agility);
        setIntelligence(intelligence);
        setWisdom(wisdom);
    }

    public Attributes () throws Exception {
        setStrength(0);
        setAgility(0);
        setIntelligence(0);
        setWisdom(0);
    }

    /**
     * Get the strength score
     * @return strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Get the agility score
     * @return agility
     */
    public int getAgility() {
        return agility;
    }

    /**
     * Get the intelligence score
     * @return intelligence
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Get the wisdom score
     * @return wisdom
     */
    public int getWisdom() {
        return wisdom;
    }

    /**
     * Validate and set strength
     * @param strength to be set
     * @throws Exception when the required condition isn't fulfilled
     */
    private void setStrength (int strength) throws Exception {
        if (strength < Rules.MIN_STRENGTH) {
            throw new Exception("Strength cannot be less than " + Rules.MIN_STRENGTH);
        } else if (strength > Rules.MAX_STRENGTH) {
            throw new Exception("Strength cannot be greater than " + Rules.MAX_STRENGTH);
        }

        this.strength = strength;
    }

    /**
     * Validate and set agility
     * @param agility to be set
     * @throws Exception when the required condition isn't fulfilled
     */
    private void setAgility (int agility) throws Exception {
        if (agility < Rules.MIN_AGILITY) {
            throw new Exception("Agility cannot be less than " + Rules.MIN_AGILITY);
        } else if (agility > Rules.MAX_AGILITY) {
            throw new Exception("Agility cannot be greater than " + Rules.MAX_AGILITY);
        }

        this.agility = agility;
    }

    /**
     * Validate and set intelligence
     * @param intelligence to be set
     * @throws Exception when the required condition isn't fulfilled
     */
    private void setIntelligence (int intelligence) throws Exception {
        if (intelligence < Rules.MIN_INTELLIGENCE) {
            throw new Exception("Intelligence cannot be less than " + Rules.MIN_INTELLIGENCE);
        } else if (intelligence > Rules.MAX_INTELLIGENCE) {
            throw new Exception("Intelligence cannot be greater than " + Rules.MAX_INTELLIGENCE);
        }

        this.intelligence = intelligence;
    }

    /**
     * Validate and set wisdom
     * @param wisdom to be set
     * @throws Exception when the required condition isn't fulfilled
     */
    private void setWisdom (int wisdom) throws Exception {
        if (wisdom < Rules.MIN_WISDOM) {
            throw new Exception("Wisdom cannot be less than " + Rules.MIN_WISDOM);
        } else if (wisdom > Rules.MAX_WISDOM) {
            throw new Exception("Wisdom cannot be greater than " + Rules.MAX_WISDOM);
        }

        this.wisdom = wisdom;
    }

    /**
     * Returns the attributes in a user-friendly format
     * @return the attributes separated by commas
     */
    @Override
    public String toString() {
        return "Strength: " + strength
                + ", Agility: " + agility
                + ", Intelligence: " + intelligence
                + ", Wisdom: " + wisdom;
    }
}
