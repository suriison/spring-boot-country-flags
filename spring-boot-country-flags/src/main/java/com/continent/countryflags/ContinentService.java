package com.continent.countryflags;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContinentService implements IContinentsService {

    @Autowired
    private ContinentsRepository repository;

    @Override
    public List<Continent> findAll() {

        return (List<Continent>) repository.findAll();
    }
    
    @Override
    public List<Continent> insert(List<Continent> entities) {
        List<Continent> mCont = new ArrayList<>();
    	for(Continent continent: entities) {
    		mCont.add(repository.insert(continent));
    	}
        return mCont;
    }
}