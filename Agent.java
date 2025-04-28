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
public class Agent {
    private int index;
    private int pairNum;
    private ArrayList<Integer> pairAgents;
    private int agentNum;
    private double p;

    public Agent(int index, int pairNum, int agentNum, double p){
        this.index = index;
        this.pairNum = pairNum;
        this.pairAgents = new ArrayList<>();
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
        }
    }
    
    
    public boolean checkPair (int i){
        return pairAgents.contains(i);
    }
    public Agent getSelf(){
        return this;
    }
    
    public ArrayList<Integer> getPairs(){
        return pairAgents;
    }
    
    public int getIndex(){
        return index;
    }
}
