/*
 * Date: 2020-09-20
 * File Name: CharacterClass.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

/**
 * Enum to represent different classes of a character
 */
public enum CharacterClass {
    Mage(Attribute.Intelligence),
    Warrior(Attribute.Strength),
    Rogue(Attribute.Agility),
    Cleric(Attribute.Wisdom);

    private Attribute primaryAttribute;

    /**
     * Sets the primary attribute of the class
     * @param primaryAttribute to be set
     */
    CharacterClass(Attribute primaryAttribute) {
        this.primaryAttribute = primaryAttribute;
    }

    /**
     * Get the primary attribute of the class
     * @return the primary attribute of the class
     */
    public Attribute getPrimaryAttribute() {
        return primaryAttribute;
    }
}
