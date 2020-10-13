package ab224qr_assign2.exercise_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class BattleTest {

    @Test
    void shouldBeATie() {
        try {
            Mage mage = new Mage("Hello", 5);
            Spell spellOne = new Spell("Some spell", SpellSchool.Alteration);
            mage.learnSpell(spellOne);

            Warrior warrior = new Warrior("Wawa", 3);
            Ability abilityOne = new Ability("Some ability", Proficiency.Athletics);
            Ability abilityTwo = new Ability("Some other ability", Proficiency.Survival);
            warrior.learnAbility(abilityOne);
            warrior.learnAbility(abilityTwo);

            Character[] party1 = {mage, warrior};

            Cleric cleric = new Cleric("Clex", 7);
            Spell clericSpellOne = new Spell("Clash", SpellSchool.Restoration);
            Spell clericSpellTwo = new Spell("Clash", SpellSchool.Divination);
            cleric.learnSpell(clericSpellOne);
            cleric.learnSpell(clericSpellTwo);

            Rogue rogue = new Rogue("RPGRogue", 1);
            Ability rogueAbilityOne = new Ability("Coolstuff", Proficiency.Acrobatics);
            rogue.learnAbility(rogueAbilityOne);

            Character[] party2 = {cleric, rogue};

            Character[] result = Battle.resolve(party1, party2);
            Character[] result2 = Battle.resolve(party2, party1);

            Assertions.assertEquals(result.length, 0);
            Assertions.assertEquals(result2.length, 0);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnLargestParty() {
        try {
            Mage mage = new Mage("Hello", 5);
            Spell spellOne = new Spell("Some spell", SpellSchool.Alteration);
            mage.learnSpell(spellOne);

            Warrior warrior = new Warrior("Wawa", 3);
            Ability abilityTwo = new Ability("Some other ability", Proficiency.Survival);
            warrior.learnAbility(abilityTwo);

            Character[] party1 = {mage, warrior};

            Cleric cleric = new Cleric("Clex", 7);
            Spell clericSpellOne = new Spell("Clash", SpellSchool.Restoration);
            Spell clericSpellTwo = new Spell("Clash", SpellSchool.Divination);
            cleric.learnSpell(clericSpellOne);
            cleric.learnSpell(clericSpellTwo);

            Character[] party2 = {cleric};

            Character[] result = Battle.resolve(party1, party2);
            Character[] result2 = Battle.resolve(party2, party1);

            Assertions.assertEquals(party1, result);
            Assertions.assertEquals(party1, result2);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnPartyWithHighestAvgLevel() {
        try {
            Mage mage = new Mage("Hello", 5);
            Warrior warrior = new Warrior("Wawa", 3);

            Character[] party1 = {mage, warrior};

            Cleric cleric = new Cleric("Clex", 7);
            Mage mage2 = new Mage("MGM", 5);
            Character[] party2 = {cleric, mage2};

            Character[] result = Battle.resolve(party1, party2);
            Character[] result2 = Battle.resolve(party2, party1);

            Assertions.assertEquals(party2, result);
            Assertions.assertEquals(party2, result2);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldThrowErrWhenPartiesAreEmpty() {
        Character[] party1 = {};
        Character[] party2 = {};

        Assertions.assertThrows(Exception.class, () -> {
            Battle.resolve(party1, party2);
        });
    }

    @Test
    void shouldThrowErrWhenCharsArentOfSameClass() {
        try {
            Warrior warrior = new Warrior("Wgm", 10);
            Rogue rogue = new Rogue("Rgp", 8);

            Assertions.assertThrows(Exception.class, () -> {
                Battle.singleCombat(warrior, rogue);
            });
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnAverageLevelOfParty() {
        try {
            Mage mage = new Mage("Mam", 5);
            Rogue rogue = new Rogue("Rog", 8);
            Cleric cleric = new Cleric("Czxa", 25);

            Character[] party = {mage, rogue, cleric};

            int avgLevel = Battle.getAverageLevel(party);

            Assertions.assertEquals(12, avgLevel);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnSumOfPrimaryAttributes() {
        try {
            Mage mage = new Mage("Mam", 10);
            Rogue rogue = new Rogue("Rog", 2);
            Cleric cleric = new Cleric("Czxa", 40);

            Character[] party = {mage, rogue, cleric};

            int sumOfPrimaryAttributes = Battle.getSumOfPrimaryAttributes(party);

            Assertions.assertEquals(26, sumOfPrimaryAttributes);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnPrimaryAttributeScore() {
        try {
            Mage mage = new Mage("Mam", 10);

            Assertions.assertEquals(5, mage.attributes.getIntelligence());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnSumOfPowers() {
        try {
            Warrior warrior = new Warrior("WGPMG", 3);
            Ability ability = new Ability("Some ability", Proficiency.Athletics);
            Ability ability2 = new Ability("Some other ability", Proficiency.Athletics);
            Ability ability3 = new Ability("Another", Proficiency.Survival);
            warrior.learnAbility(ability);
            warrior.learnAbility(ability2);
            warrior.learnAbility(ability3);

            Assertions.assertEquals(3, Battle.getSumOfPowers(warrior));

        } catch (Exception e) {
            fail();
        }
    }
}