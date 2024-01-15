package br.com.sonnesen.springbootmongodb;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String param, String id) {
    super(String.format("User not found with %s: %s", param, id));
  }
}
