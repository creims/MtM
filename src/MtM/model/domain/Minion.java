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

    private String name;
    private Mission mission;

    private double perception, tech, gathering, fighting;

    private boolean active;

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

    @Override
    public String toString() {
        return "Name: " + name + "\n\nPerception: " + MtMUtil.round(perception, 2) 
                + "\nTech: " + MtMUtil.round(tech, 2) + "\nGathering: " 
                + MtMUtil.round(gathering, 2) + "\nFighting: " + MtMUtil.round(fighting, 2);
    }

    public String saveString() {
        return name + "," + perception + "," + tech + "," + gathering + "," + fighting + "," + active;
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

    public void growStat(Stat stat) {
        if(stat == null) return;
        
        switch(stat.getType()) {
            case PERCEPTION: perception += .2; break;
            case TECH: tech += .2; break;
            case GATHERING: gathering += .2; break;
            case FIGHTING: fighting += .2; break;
        }
    }
}
