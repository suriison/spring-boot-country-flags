package com.continent.countryflags;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentsRepository extends MongoRepository<Continent, Long> {
    List<Continent> findAll();
    List<Continent> insert(List<Continent> entities);
}