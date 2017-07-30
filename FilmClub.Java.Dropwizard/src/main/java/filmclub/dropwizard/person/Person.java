package filmclub.dropwizard.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Person {

    @Id
    public int id;

    @Column
    public String name;
}
