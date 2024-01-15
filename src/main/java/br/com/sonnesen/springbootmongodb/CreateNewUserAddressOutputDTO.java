package br.com.sonnesen.springbootmongodb;

import lombok.Builder;

@Builder
public record CreateNewUserAddressOutputDTO(String street, String number, String complement, String neighborhood,
    String city, String state, String country, String zipCode) {

}
