package br.com.sonnesen.springbootmongodb;

import java.util.List;

public interface UserService {

  CreateNewUserOutputDTO createNewUser(CreateNewUserInputDTO newUserInput);

  List<UserDTO> getAllUsers();

  UserDTO getUserById(String id);

  void deleteUserById(String id);

  UserDTO getUserByEmail(String email);
}
