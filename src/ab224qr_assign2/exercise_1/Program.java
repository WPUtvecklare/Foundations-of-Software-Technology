/*
 * Date: 2020-09-20
 * File Name: Program.java
 * Author: Adam Bergman
 */
package ab224qr_assign2.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class description: Demonstrates the use of the application
 * @version 1.0
 * @author Adam Bergman
 */
public class Program {
    // Helpers to randomly pick names of the characters and their proficiency or spell school
    private static final String[] MAGES_NAMES = {"Ogipius", "Idus", "Agabarin", "Aleus"};
    private static final String[] CLERIC_NAMES = {"Ujamar", "Xoqor", "Oguhone", "Zaharad"};
    private static final String[] WARRIOR_NAMES = {"Astrum", "Cafaris", "Uthar", "Evumonar"};
    private static final String[] ROGUE_NAMES = {"Vrapius", "Ogrugeor", "Oloforn", "Unzuxon"};

    private static final String[] EVOCATION_SPELL_NAMES = {"Air", "Earth", "Fire", "Water", "Dimension"};
    private static final String[] ALTERATION_SPELL_NAMES = {"Magic Armor", "Magic Resistance", "Spell Absorption", "Paralyze"};
    private static final String[] RESTORATION_SPELL_NAMES = {"Healing", "Lesser Ward", "Fast Healing", "Healing Hands"};
    private static final String[] DIVINATION_SPELL_NAMES = {"Arcane Eye", "Augury", "Clairvoyance", "Commune"};

    private static final String[] ATHLETICS_PROFICIENCY_NAMES = {"Jumping", "Digging", "Swimming", "Climbing", "Smashing rocks"};
    private static final String[] SURVIVAL_PROFICIENCY_NAMES = {"Hunting", "Fishing", "Build Fire"};
    private static final String[] ACROBATICS_PROFICIENCY_NAMES = {"Forward Roll", "Backward Roll", "Handstand", "Round Off"};
    private static final String[] STEALTH_PROFICIENCY_NAMES = {"Backstab", "Spymistress", "Southsea Deckhand", "Deadly Poison"};


    public static void main(String[] args) {
        try {
            Program p = new Program();

            Character[] party1 = p.getParty(randomBetween(6, 10));
            Character[] party2 = p.getParty(randomBetween(6, 11));
            Character[] party3 = p.getParty(randomBetween(8, 10));

            Battle.printStatistics(party1);
            Battle.printStatistics(party2);

            Character[] winningParty = Battle.resolve(party1, party2);

            if (winningParty.length > 1) {
                System.out.println("\nThe winning party is: ");
                for (Character c : winningParty) {
                    System.out.println(c);
                }
            } else {
                System.out.println("\nIt's a tie!");
            }

            System.out.println("\nNew battle between the winning party and a third party:");
            Battle.printStatistics(winningParty);
            Battle.printStatistics(party3);

            Character[] absoluteWinners = Battle.resolve(winningParty, party3);

            if (absoluteWinners.length > 1) {
                System.out.println("\nThe winning party is: ");
                for (Character c : absoluteWinners) {
                    System.out.println(c);
                }
            } else {
                System.out.println("\nIt's a tie!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Creates a party with the given amount of characters
     * @param amountOfCharacters how many characters that should be created
     * @return a party containing randomly selected characters
     * @throws Exception if generating a character fails
     */
    public Character[] getParty(int amountOfCharacters) throws Exception {
        List<Character> partyList = new ArrayList<>();

        for (int i = 0; i < amountOfCharacters; i++) {
            partyList.add(getRandomCharacter());
        }

        Character[] party = new Character[partyList.size()];
        party = partyList.toArray(party);
        return party;
    }

    /**
     * Creates a character randomly and adds a random amount of spells or abilities depending on it's type
     * @return a character
     * @throws Exception from Character
     */
    public Character getRandomCharacter() throws Exception {
        CharacterClass cc = randomFrom(CharacterClass.values());

        int randomLevel = randomBetween(4, 8);
        int firstRandomRange = randomBetween(1, 3);
        int secondRandomRange = randomBetween(1, 3);

        if (cc == CharacterClass.Mage) {
            Mage mage = new Mage(randomFrom(MAGES_NAMES), randomLevel);

            for (int i = 0; i < firstRandomRange; i++) {
                Spell spell = new Spell(randomFrom(EVOCATION_SPELL_NAMES), SpellSchool.Evocation);
                mage.learnSpell(spell);
            }

            for (int i = 0; i < secondRandomRange; i++) {
                Spell spell = new Spell(randomFrom(ALTERATION_SPELL_NAMES), SpellSchool.Alteration);
                mage.learnSpell(spell);
            }

            return mage;

        } else if (cc == CharacterClass.Cleric) {
            Cleric cleric = new Cleric(randomFrom(CLERIC_NAMES), randomLevel);

            for (int i = 0; i < firstRandomRange; i++) {
                Spell spell = new Spell(randomFrom(RESTORATION_SPELL_NAMES), SpellSchool.Restoration);
                cleric.learnSpell(spell);
            }

            for (int i = 0; i < secondRandomRange; i++) {
                Spell spell = new Spell(randomFrom(DIVINATION_SPELL_NAMES), SpellSchool.Divination);
                cleric.learnSpell(spell);
            }

            return cleric;
        } else if (cc == CharacterClass.Warrior) {
            Warrior warrior = new Warrior(randomFrom(WARRIOR_NAMES), randomLevel);

            for (int i = 0; i < firstRandomRange; i++) {
                Ability ability = new Ability(randomFrom(ATHLETICS_PROFICIENCY_NAMES), Proficiency.Athletics);
                warrior.learnAbility(ability);
            }

            for (int i = 0; i < secondRandomRange; i++) {
                Ability ability = new Ability(randomFrom(SURVIVAL_PROFICIENCY_NAMES), Proficiency.Survival);
                warrior.learnAbility(ability);
            }

            return warrior;
        } else {
            Rogue rogue = new Rogue(randomFrom(ROGUE_NAMES), randomLevel);

            for (int i = 0; i < firstRandomRange; i++) {
                 Ability ability = new Ability(randomFrom(ACROBATICS_PROFICIENCY_NAMES), Proficiency.Acrobatics);
                 rogue.learnAbility(ability);
            }

            for (int i = 0; i < secondRandomRange; i++) {
                Ability ability = new Ability(randomFrom(STEALTH_PROFICIENCY_NAMES), Proficiency.Stealth);
                rogue.learnAbility(ability);
            }

            return rogue;
        }

    }

    /**
     * Generic implementation to get a randomly picked element from a provided set
     * @param items the list to randomly pick from
     * @param <T> the generic type
     * @return the randomly picked element
     */
    private static <T> T randomFrom(T... items) {
        Random random = new Random();
        return items[random.nextInt(items.length)];
    }

    /**
     * Picks a random element from a given range
     * @param start the starting value
     * @param end the ending value
     * @return the randomly picked element
     */
    private static int randomBetween(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start ) + start;
    }
}
