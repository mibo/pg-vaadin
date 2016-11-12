package de.mirb.pg.vaadin;

/**
 * Created by michael on 12.11.16.
 */
public class Person {
  private String name;
  private String lastname;
  private String nickname;

  public Person(String name, String lastname, String nickname) {
    this.name = name;
    this.lastname = lastname;
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
