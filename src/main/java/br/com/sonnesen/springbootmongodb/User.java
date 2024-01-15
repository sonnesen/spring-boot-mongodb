package br.com.sonnesen.springbootmongodb;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class User {

  @Id
  private String id;
  private String name;
  private String email;
  private String document;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
