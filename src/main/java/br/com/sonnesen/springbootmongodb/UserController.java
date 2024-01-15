package br.com.sonnesen.springbootmongodb;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<CreateNewUserOutputDTO> createNewUser(@RequestBody CreateNewUserInputDTO newUserInput) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(newUserInput));
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
  }

  @GetMapping("/with-email")
  public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByEmail(email));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
    userService.deleteUserById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
