package ru.itmo.kotiki.exception;

public class ValidationException extends RuntimeException {

  private String message;

  public ValidationException(String message) {
  }

  public String getMessage() {
    return message;
  }
}
