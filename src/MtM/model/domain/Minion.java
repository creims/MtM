/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MtM.model.domain;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Colin
 */
public class Minion {

    private static final double STAT_MIN = 1, STAT_MAX = 6;

    private String name;
    private Mission mission;

    private double perception, tech, gathering, fighting;

    private boolean active;

    public Minion() {
        this.name = randomName();
        this.perception = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, 3);
        this.tech = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, 3);
        this.gathering = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, 3);
        this.fighting = DiceRandomizer.nextVal(STAT_MIN, STAT_MAX, 3);
        this.mission = null;
    }

    public Minion(String name, double perception, double tech, double gathering, double fighting) {
        this.name = name;
        this.perception = perception;
        this.tech = tech;
        this.gathering = gathering;
        this.fighting = fighting;
        this.mission = null;
    }

    /**
     * Get the value of mission
     *
     * @return the value of mission
     */
    public Mission getMission() {
        return mission;
    }

    /**
     * Set the value of mission
     *
     * @param mission new value of mission
     */
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public double getTech() {
        return tech;
    }

    public void setTech(double tech) {
        this.tech = tech;
    }

    public double getGathering() {
        return gathering;
    }

    public void setGathering(double gathering) {
        this.gathering = gathering;
    }

    public double getFighting() {
        return fighting;
    }

    public void setFighting(double fighting) {
        this.fighting = fighting;
    }

    /**
     * Get the value of perception
     *
     * @return the value of perception
     */
    public double getPerception() {
        return perception;
    }

    /**
     * Set the value of perception
     *
     * @param perception new value of perception
     */
    public void setPerception(double perception) {
        this.perception = perception;
    }

    private String randomName() {
        return "Agent " + ThreadLocalRandom.current().nextInt(9000);
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n\nPerception: " + perception + "\nTech: " + tech + "\nGathering: " + gathering + "\nFighting: " + fighting;
    }

    public String saveString() {
        return name + "," + perception + "," + tech + "," + gathering + "," + fighting;
    }

    /**
     * Get the value of active
     *
     * @return the value of active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the value of active
     *
     * @param active new value of active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
