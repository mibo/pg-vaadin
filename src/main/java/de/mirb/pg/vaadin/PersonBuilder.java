package de.mirb.pg.vaadin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PersonBuilder {
  private String name;
  private String lastname;
  private String nickname;

  private static final String[] NAMES = {"Micky", "Mini", "Donald", "Daisy", "Dagobert", "Kater", "Gustav"};
  private static final String[] LAST_NAMES = {"Mouse", "Duck", "Karlo", "Gans"};
  private static final String[] NICK_NAMES = {"The Small", "The Beauty", "The Fast", "The Rich", "The Fat"};

  public static PersonBuilder personWith() {
    return new PersonBuilder();
  }

  public static Collection<Person> randomPersons(int amount) {
    Collection<Person> p = new ArrayList<>(amount);

    for (int i = 0; i < amount; i++) {
       p.add(randomPerson());
    }
    return p;
  }

  public static Person randomPerson() {
    // Fix possible IOoB
    String name = NAMES[(int)(Math.random() * NAMES.length)];
    String lastname = LAST_NAMES[(int)(Math.random() * LAST_NAMES.length)];
    String nickname = NICK_NAMES[(int)(Math.random() * NICK_NAMES.length)];

    return new Person(name, lastname, nickname);
  }

  public PersonBuilder name(String name) {
    this.name = name;
    return this;
  }

  public PersonBuilder lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public PersonBuilder nickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public Person create() {
    return new Person(name, lastname, nickname);
  }
}