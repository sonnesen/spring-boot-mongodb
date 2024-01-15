package br.com.sonnesen.springbootmongodb;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private static final String ID = "id";
  private static final String E_MAIL = "e-mail";

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;

  @Transactional
  @Override
  public CreateNewUserOutputDTO createNewUser(CreateNewUserInputDTO newUserInput) {
    var newUser = User.builder()
        .id(UUID.randomUUID().toString())
        .name(newUserInput.name())
        .email(newUserInput.email())
        .document(newUserInput.document())
        .createdAt(LocalDateTime.now())
        .build();

    var newAddress = Address.builder()
        .id(UUID.randomUUID().toString())
        .userId(newUser.getId())
        .street(newUserInput.address().street())
        .number(newUserInput.address().number())
        .neighborhood(newUserInput.address().neighborhood())
        .city(newUserInput.address().city())
        .state(newUserInput.address().state())
        .country(newUserInput.address().country())
        .zipCode(newUserInput.address().zipCode())
        .build();

    var createdUser = userRepository.save(newUser);
    var createdAddress = addressRepository.save(newAddress);

    return CreateNewUserOutputDTO.builder()
        .id(createdUser.getId())
        .name(createdUser.getName())
        .email(createdUser.getEmail())
        .document(createdUser.getDocument())
        .address(CreateNewUserAddressOutputDTO.builder()
            .street(createdAddress.getStreet())
            .number(createdAddress.getNumber())
            .neighborhood(createdAddress.getNeighborhood())
            .city(createdAddress.getCity())
            .state(createdAddress.getState())
            .country(createdAddress.getCountry())
            .zipCode(createdAddress.getZipCode())
            .build())
        .createdAt(createdUser.getCreatedAt().toString())
        .updatedAt(createdUser.getUpdatedAt() != null ? createdUser.getUpdatedAt().toString() : null)
        .build();
  }

  @Override
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .document(user.getDocument())
            .address(addressRepository.findByUserId(user.getId())
                .map(address -> AddressDTO.builder()
                    .street(address.getStreet())
                    .number(address.getNumber())
                    .complement(address.getComplement())
                    .neighborhood(address.getNeighborhood())
                    .city(address.getCity())
                    .state(address.getState())
                    .country(address.getCountry())
                    .zipCode(address.getZipCode())
                    .build())
                .orElseThrow(() -> new AddressNotFoundException(user.getId())))
            .createdAt(user.getCreatedAt().toString())
            .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null)
            .build())
        .toList();
  }

  @Override
  public UserDTO getUserById(String id) {
    Address address = addressRepository.findByUserId(id)
        .orElseThrow(() -> new AddressNotFoundException(id));

    return userRepository.findById(id)
        .map(user -> UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .document(user.getDocument())
            .address(AddressDTO.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .build())
            .createdAt(user.getCreatedAt().toString())
            .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null)
            .build())
        .orElseThrow(() -> new UserNotFoundException(ID, id));
  }

  @Transactional
  @Override
  public void deleteUserById(String id) {
    userRepository.deleteById(id);
    addressRepository.deleteByUserId(id);
  }

  @Override
  public UserDTO getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(user -> UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .document(user.getDocument())
            .createdAt(user.getCreatedAt().toString())
            .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null)
            .build())
        .orElseThrow(() -> new UserNotFoundException(E_MAIL, email));
  }

}
