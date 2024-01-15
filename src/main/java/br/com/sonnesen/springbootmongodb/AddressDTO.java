package br.com.sonnesen.springbootmongodb;

import lombok.Builder;

@Builder
public record AddressDTO(String street, String number, String complement, String neighborhood,
    String city, String state, String country, String zipCode) {
}
