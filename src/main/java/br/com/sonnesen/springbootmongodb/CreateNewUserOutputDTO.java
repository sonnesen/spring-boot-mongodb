package br.com.sonnesen.springbootmongodb;

import lombok.Builder;

@Builder
public record CreateNewUserOutputDTO(String id, String name, String email, String document,
    CreateNewUserAddressOutputDTO address, String createdAt, String updatedAt) {

}
