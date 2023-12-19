package org.example;

import java.io.Serializable;

public record UserInfo(String name, String surname, int age) implements Serializable
{
}
