package com.zinbo.shared;

import org.springframework.data.repository.CrudRepository;

public interface Repository<T> extends CrudRepository<T, Long> {

}
