package com.dilshad.social_media_rest_api.helloworld.person;

public class PersonV2 {

    private Name name;

    public PersonV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name=" + name +
                '}';
    }
}
