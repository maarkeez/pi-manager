package com.pi.email;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {
  
  private List<String> to;
  private String subject;
  private String body;
}