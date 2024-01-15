package br.com.sonnesen.springbootmongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "addresses")
public class Address {

  @Id
  private String id;
  private String userId;
  private String street;
  private String number;
  private String complement;
  private String neighborhood;
  private String city;
  private String state;
  private String country;
  private String zipCode;
}
