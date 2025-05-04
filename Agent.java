/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprojectparti;
import java.util.*;
/**
 *
 * @author chenhenr
 */
// Represents an agent in the network.
// Each agent can form directed or undirected pairs with others.
public class Agent {
    private int index;
    private int pairNum;
    private PriorityQueue<Integer> pairAgents;
    private int agentNum;
    private double p;
// Constructs an Agent.
    public Agent(int index, int pairNum, int agentNum, double p){
        this.index = index;
        this.pairNum = pairNum;
        this.pairAgents = new PriorityQueue<>();
        this.agentNum = agentNum;
        this.p = p;
    }
    /*
    public void ranPair(int i, Agent agenti){
        Random ran = new Random();
        double ranN = ran.nextInt(11)/10.0;
        //ranN = 1;
        if(index == i){
            return;
        }
        if(agenti.checkPair(index)){
            pairNum++;
            pairAgents.add(i);
        }
        else{
            if(ranN > p){
                pairNum++;
                pairAgents.add(i);
            }
            else{ 
                return;
            }
        }
    }
    */
    // Randomly attempts to form a pair with another agent based on probability p.
    public void ranPair(int i, Agent agenti){
        Random ran = new Random();
        double ranN = ran.nextInt(11)/10.0;
        if(ranN > p){
            pair(i, agenti);
        }
        else{ 
            return;
        }
    }
    
// Forms a pair with another agent. If the other agent already has this agent as a pair, it creates a reciprocal link.
    public void pair(int i, Agent agenti){
        if(index == i){
            return;
        }
        if(agenti.checkPair(index)){
            pairNum++;
            pairAgents.add(i);
        }
        else{
            pairNum++;
            pairAgents.add(i);
            agenti.getPairs().add(index);
        }
    }
    
// Checks if this agent is already paired with the given agent index.
    public boolean checkPair (int i){
        return pairAgents.contains(i);
    }
    public Agent getSelf(){
        return this;
    }
// Returns the list of paired agent indices.
    public PriorityQueue<Integer> getPairs(){
        return pairAgents;
    }
// Creates a one-way (unilateral) pair with another agent. 
    public void unilateralPair(int i){
        pairAgents.add(i);
    }
// Returns the index of this agent.
    public int getIndex(){
        return index;
    }
}
