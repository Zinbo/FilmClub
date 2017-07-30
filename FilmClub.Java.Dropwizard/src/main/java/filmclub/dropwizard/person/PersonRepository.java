package filmclub.dropwizard.person;

import org.springframework.data.jpa.repository.JpaRepository;

interface PersonRepository extends JpaRepository<Person, Integer> {
}
