package br.com.sonnesen.springbootmongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

  void deleteByUserId(String id);

  Optional<Address> findByUserId(String id);

}
