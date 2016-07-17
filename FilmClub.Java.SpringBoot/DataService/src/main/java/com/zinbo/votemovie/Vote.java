package com.zinbo.votemovie;

import com.zinbo.shared.Entity;

import javax.persistence.Id;

@javax.persistence.Entity
public class Vote extends Entity {
    private boolean upVote;
    private Voter voter;

}
