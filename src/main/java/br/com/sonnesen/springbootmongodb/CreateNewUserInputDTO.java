package br.com.sonnesen.springbootmongodb;

import lombok.Builder;

@Builder
public record CreateNewUserInputDTO(String name, String email, String document, CreateNewUserAddressInputDTO address) {

}
