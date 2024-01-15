package br.com.sonnesen.springbootmongodb;

public class AddressNotFoundException extends RuntimeException {

  public AddressNotFoundException(String id) {
    super(String.format("Address for user %s not found", id));
  }

}
