/*
 * Date: 2020-09-20
 * File Name: Character.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.Random;

/**
 * Class description: Represents a character in a role play game
 * @version 1.0
 * @author Adam Bergman
 */
public class Character {
    private String name;
    private CharacterClass characterClass;
    private int level;
    public Attributes attributes;

    public Character (String name, CharacterClass characterClass, int level) throws Exception {
        this.setName(name);
        this.characterClass = characterClass;
        this.setLevel(level);
        this.setAttributes();
    }

    /**
     * Get the name of the character
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Get the character's type/name
     * @return the character's type/name
     */
    public String getCharacter () {
        return characterClass.name();
    }

    /**
     * Get the level of the character
     * @return the level
     */
    public int getLevel () {
        return level;
    }

    /**
     * Get the primary attribute of the character
     * @return the primary attribute
     */
    public String getPrimaryAttribute () {
        return this.characterClass.getPrimaryAttribute().toString();
    }

    /**
     * Validate and set name
     * @param name to be set
     * @throws Exception if the name is too short or long
     */
    public void setName (String name) throws Exception {
        if (name.length() < Rules.MIN_CHARACTER_NAME) {
            throw new Exception("Name is too short. Minimum length: " + Rules.MIN_CHARACTER_NAME);
        } else if (name.length() > Rules.MAX_CHARACTER_NAME) {
            throw new Exception("Name is too long. Maximum length: " + Rules.MAX_CHARACTER_NAME);
        }

        this.name = name;
    }

    /**
     * Validate and set level
     * @param level to be set
     * @throws Exception if the level is too small or high
     */
    public void setLevel (int level) throws Exception {
        if (level < Rules.MIN_LEVEL) {
            throw new Exception("Level cannot be less than " + Rules.MIN_LEVEL);
        } else if (level > Rules.MAX_LEVEL) {
            throw new Exception("Level cannot be greater than " + Rules.MAX_LEVEL);
        }

        this.level = level;
    }

    /**
     * Calculates and sets the attributes of the character
     * @throws Exception if attributes weren't accepted
     */
    public void setAttributes () throws Exception {
        if (level == 0) {
            // Set the attributes to default values (0)
            this.attributes = new Attributes();
            return;
        }

        int strength = 0;
        int agility = 0;
        int intelligence = 0;
        int wisdom = 0;

        Attribute primaryAttribute = this.characterClass.getPrimaryAttribute();

        int primaryAttributeScore = level / 2;

        int rest = level - primaryAttributeScore;

        // Assign the largest score to the primary attribute
        // The second largest to the next attribute that is closest to the character
        // Randomly pick the next based on what's left
        switch (primaryAttribute) {
            case Strength:
                strength = primaryAttributeScore;
                agility = getRandomBetween(rest / 2, rest);
                rest = rest - agility;
                intelligence = getRandomBetween(0, rest);
                rest = rest - intelligence;
                wisdom = rest;
                break;

            case Agility:
                agility = primaryAttributeScore;
                strength = getRandomBetween(rest / 2, rest);
                rest = rest - strength;
                intelligence = getRandomBetween(0, rest);
                rest = rest - intelligence;
                wisdom = rest;
                break;

            case Intelligence:
                intelligence = primaryAttributeScore;
                wisdom = getRandomBetween(rest / 2, rest);
                rest = rest - wisdom;
                agility = getRandomBetween(0, rest);
                rest = rest - agility;
                strength = rest;
                break;

            case Wisdom:
                wisdom = primaryAttributeScore;
                intelligence = getRandomBetween(rest / 2, rest);
                rest = rest - intelligence;
                agility = getRandomBetween(0, rest);
                rest = rest - agility;
                strength = rest;
                break;
        }

        this.attributes = new Attributes(strength,agility,intelligence,wisdom);
    }

    /**
     * Picks a random number between a given range
     * @param start the starting value
     * @param end the ending value
     * @return a randomly picked number
     */
    private int getRandomBetween(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start ) + start;
    }

    /**
     * Converts the character's details into a user-friendly format
     * @return the character's details separated by commas
     */
    @Override
    public String toString() {
        String primaryAttribute = getPrimaryAttribute();

        return "Name: " + name +
                ", Class: " + characterClass +
                ", Level: " + level +
                ", Primary: " + primaryAttribute +
                ", Attributes: {" + attributes + "}";
    }
}
