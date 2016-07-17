package com.zinbo.shared;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@javax.persistence.Entity
public abstract class Entity {

    protected long id;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;
        if(id == 0 || entity.id == 0) return false;

        return id == entity.id;

    }

    @Override
    public int hashCode() {
        return (getClass().getCanonicalName() + id).hashCode();
    }
}
