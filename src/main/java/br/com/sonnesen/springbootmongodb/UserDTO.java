package br.com.sonnesen.springbootmongodb;

import lombok.Builder;

@Builder
public record UserDTO(String id, String name, String email, String document,
    AddressDTO address, String createdAt, String updatedAt) {

}
