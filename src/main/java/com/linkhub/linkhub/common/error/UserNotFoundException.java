package com.linkhub.linkhub.common.error;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(Long userId) {
    super("User not found by Id: " + userId);
  }
  public UserNotFoundException(String username) {
      super("User not found by username: " + username);
  }
}
