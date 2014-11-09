package com.csheth.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PLAYER")
public class Player implements Serializable{
 
@Id
@Column(name = "PLAYER_ID")
 private int playerId;
 
@Column(name = "PLAYERNAME")
 private String playerName;
 
@Column(name = "PLAYER_POS_ID")
 private int positionId;
 
@Column(name = "VOTES")
private int votes;


 
public Player(int playerId, String playerName, int positionId) {
	super();
	this.playerId = playerId;
	this.playerName = playerName;
	this.positionId = positionId;
}



public int getVotes() {
	return votes;
}



public void setVotes(int votes) {
	this.votes = votes;
}



public Player() {
 
}



public int getPlayerId() {
	return playerId;
}



public void setPlayerId(int playerId) {
	this.playerId = playerId;
}



public String getPlayerName() {
	return playerName;
}



public void setPlayerName(String playerName) {
	this.playerName = playerName;
}



public int getPositionId() {
	return positionId;
}



public void setPositionId(int positionId) {
	this.positionId = positionId;
}

	
}