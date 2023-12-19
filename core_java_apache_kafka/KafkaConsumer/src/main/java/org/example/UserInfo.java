package org.example;

public record UserInfo(String name, String surname, int age)
{
    @Override
    public String toString()
    {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
