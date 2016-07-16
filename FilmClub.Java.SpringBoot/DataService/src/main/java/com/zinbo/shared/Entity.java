package com.zinbo.shared;

public abstract class Entity {
    private long id;

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
