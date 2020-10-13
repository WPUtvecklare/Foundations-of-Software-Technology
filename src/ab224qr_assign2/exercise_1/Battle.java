/*
 * Date: 2020-09-20
 * File Name: Battle.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

/**
 * Class description: Handles logic for battle between parties and characters
 * @version 1.0
 * @author Adam Bergman
 */
public class Battle {
    /**
     * Presents statistics about level, spells and abilities within a party
     * @param party the party to display statistics of
     */
    public static void printStatistics (Character party[]) {
        int averageLevel = 0;

        int mageSpells = 0;
        int clericSpells = 0;
        int warriorAbilities = 0;
        int rogueAbilities = 0;

        for (Character c : party) {
            averageLevel += c.getLevel();

            if (c instanceof Mage) {
                mageSpells += ((Mage) c).getKnownSpells().size();
            } else if (c instanceof Cleric) {
                clericSpells += ((Cleric) c).getKnownSpells().size();
            } else if (c instanceof Warrior) {
                warriorAbilities += ((Warrior) c).getKnownAbilities().size();
            } else if (c instanceof  Rogue) {
                rogueAbilities += ((Rogue) c).getKnownAbilities().size();
            }
        }

        averageLevel = averageLevel / party.length;
        System.out.println("\n---\nStatistics:\n---");
        System.out.println("Average level: " + averageLevel);
        System.out.println("Total number of spells: " + (mageSpells + clericSpells));
        System.out.println("Total number of mage spells: " + mageSpells);
        System.out.println("Total number of cleric spells: " + clericSpells);
        System.out.println("Total number of abilities: " + (warriorAbilities + rogueAbilities));
        System.out.println("Total number of warrior abilities: " + warriorAbilities);
        System.out.println("Total number of rogue abilities: " + rogueAbilities);
    }

    /**
     * Determines which party of the parties provided wins
     * @param party1 the first party
     * @param party2 the second party
     * @return the winning party, if it's a tie, an empty party
     */
    public static Character[] resolve(Character party1[], Character party2[]) throws Exception {
        if (party1.length == 0 && party2.length == 0) {
            throw new Exception("The both parties cannot be empty");
        }

        if (party1.length > party2.length) {
            return party1;
        } else if (party2.length > party1.length) {
            return party2;
        } else {
            int firstPartyAverage = getAverageLevel(party1);
            int secondPartyAverage = getAverageLevel(party2);

            if (firstPartyAverage > secondPartyAverage) {
                return party1;
            } else if (secondPartyAverage > firstPartyAverage) {
                return party2;
            } else {
                int partyOneTotalSumOfPrimaryAttributes = getSumOfPrimaryAttributes(party1);
                int partyTwoTotalSumOfPrimaryAttributes = getSumOfPrimaryAttributes(party2);

                if (partyOneTotalSumOfPrimaryAttributes > partyTwoTotalSumOfPrimaryAttributes) {
                    return party1;
                } else if (partyTwoTotalSumOfPrimaryAttributes > partyOneTotalSumOfPrimaryAttributes) {
                    return party2;
                } else {
                    Character tie[] = {};
                    return tie;
                }
            }
        }
    }

    /**
     * Determines the winner of two characters from the same class and presents the winner
     * @param first the first character
     * @param second the second character
     * @throws Exception if the characters aren't the same class
     */
    public static void singleCombat (Character first, Character second) throws Exception {
        if (!first.getCharacter().equals(second.getCharacter())) {
            throw new Exception("The characters must be of the same class");
        }

        String firstWins = first.getName() + " wins";
        String secondWins = second.getName() + " wins";

        int firstLevel = first.getLevel();
        int secondLevel = second.getLevel();

        if (firstLevel > secondLevel) {
            System.out.println(firstWins);
        } else if (secondLevel > firstLevel) {
            System.out.println(secondWins);
        } else {
            int firstPrimaryAttributeScore = getPrimaryAttributeScore(first);
            int secondPrimaryAttributeScore = getPrimaryAttributeScore(second);

            if (firstPrimaryAttributeScore > secondPrimaryAttributeScore) {
                System.out.println(firstWins);
            } else if (secondPrimaryAttributeScore > firstPrimaryAttributeScore) {
                System.out.println(secondWins);
            } else {
                int firstAmountOfPowers = getSumOfPowers(first);
                int secondAmountOfPowers = getSumOfPowers(second);

                if (firstAmountOfPowers > secondAmountOfPowers) {
                    System.out.println(firstWins);
                } else if (secondAmountOfPowers > firstAmountOfPowers) {
                    System.out.println(secondWins);
                } else {
                    System.out.println("The single combat is a tie");
                }
            }
        }
    }

    /**
     * Calculates the average level of a party
     * @param party to be calculated
     * @return the average level
     */
    public static int getAverageLevel(Character party[]) {
        int amount = 0;

        for (Character c : party) {
            amount += c.getLevel();
        }

        return amount / party.length;
    }

    /**
     * Calculates the sum of the primary attributes in a party
     * @param party to be calculated
     * @return the sum of the primary attributes
     */
    public static int getSumOfPrimaryAttributes(Character party[]) {
        int sum = 0;

        for (Character c : party) {
            sum += getPrimaryAttributeScore(c);
        }

        return sum;
    }

    /**
     * Calculates the primary attribute score for one character
     * @param c the character
     * @return the primary attribute score
     */
    public static int getPrimaryAttributeScore(Character c) {
        Attribute primaryAttribute = Attribute.valueOf(c.getPrimaryAttribute());
        int score = 0;

        if (primaryAttribute == Attribute.Intelligence) {
            score += c.attributes.getIntelligence();
        } else if (primaryAttribute == Attribute.Wisdom) {
            score += c.attributes.getWisdom();
        } else if (primaryAttribute == Attribute.Strength) {
            score += c.attributes.getStrength();
        } else if (primaryAttribute == Attribute.Agility) {
            score += c.attributes.getAgility();
        }

        return score;
    }

    /**
     * Calculates the amount of abilities (or spells) a character has
     * @param c the character
     * @return the amount of abilities (or spells)
     */
    public static int getSumOfPowers(Character c) {
        int sum = 0;

        if (c instanceof Physical) {
            sum += ((Physical) c).getKnownAbilities().size();
        } else if (c instanceof Magical) {
            sum += ((Magical) c).getKnownSpells().size();
        }

        return sum;
    }
}
