package com.zinbo.shared;

import org.springframework.data.repository.CrudRepository;

public interface Repository<T extends AggregateRoot> extends CrudRepository<T, Long> {

}
