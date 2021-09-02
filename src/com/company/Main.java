package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1400;
    public static int bossDamage = 80;
    public static String bossDefense = " ";
    public static int[] heroesHealth = {150, 150, 100, 150, 250, 150, 150};
    public static int[] heroesDamage = {35, 20, 25, 0, 15, 15, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic", "Tank", "Dodger", "Thor"};
    public static int medicTreat = 18;


    public static void main(String[] args) {
        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        medicTreat();
        changeBossDefence();
        bossHit();
        heroesHit();
        fightInfo();
        tankBlockDamage();
        dodgerAbility();
        thorStunned();


    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[4] <= 0
                && heroesHealth[5] <= 0 && heroesHealth[6] <= 0) {
            System.out.println("game over");
            return true;
        }
        return false;
    }

    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefense = heroesAttackType[randomIndex];
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] <= 0) {
                heroesHealth[i] = 0;
            } else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }


    public static void tankBlockDamage() {
        int index = 0;
        for (String str : heroesAttackType) {
            if (str.equals("Tank")) {
                if (heroesHealth[index] > 0) {
                    System.out.println("tank block ");
                    heroesHealth[index] -= bossDamage;
                    for (int i = 0; i < heroesHealth.length; i++) {
                        heroesHealth[i] += bossDamage;

                    }
                }
            }
            index++;
        }
    }

    public static void dodgerAbility() {
        Random random3 = new Random();
        boolean luckyMan = random3.nextBoolean();
        if (heroesHealth[5] <= 0) {
            heroesHealth[5] = 0;
        } else {
            if (luckyMan) {
                heroesHealth[5] += bossDamage;
                System.out.println("luckyMan Boss hit Missed");
            }
        }
    }

    public static void thorStunned() {
        Random random4 = new Random();
        int Stunned = random4.nextInt(6);
        if (heroesHealth[6] <= 0) {
            heroesHealth[6] = 0;
        } else if (Stunned == 1) {
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i] += bossDamage;
            }
            System.out.println("boss is Stunned " + Stunned + " round"
            );


        }

    }


    public static void medicTreat() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[3] < 0) {
                medicTreat = 0;
            }
            heroesHealth[i] = heroesHealth[i] + medicTreat;
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefense.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int attack = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * attack < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * attack;
                    }
                    System.out.println(heroesAttackType[i] + "critical hit" + heroesDamage[i] * attack);

                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }


        }
    }

    public static void fightInfo() {
        System.out.println("_____________________");
        System.out.println("boss health: " + bossHealth);
        System.out.println("warrior health: " + heroesHealth[0]);
        System.out.println("magic health: " + heroesHealth[1]);
        System.out.println("kinetic health: " + heroesHealth[2]);
        System.out.println("medic health: " + heroesHealth[3]);
        System.out.println("tank health " + heroesHealth[4]);
        System.out.println("dodger health " + heroesHealth[5]);
        System.out.println("thor health " + heroesHealth[6]);
        System.out.println("_____________________");

    }


}

