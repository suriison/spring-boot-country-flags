package com.continent.countryflags;

import java.util.List;

public interface IContinentsService {
   List<Continent> findAll();
   List<Continent> insert(List<Continent> entities);
}