package com.dilshad.social_media_rest_api.helloworld;

import com.dilshad.social_media_rest_api.helloworld.person.Name;
import com.dilshad.social_media_rest_api.helloworld.person.PersonV1;
import com.dilshad.social_media_rest_api.helloworld.person.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIVersionController {

    //---  URI Verisoning ----//
    @GetMapping("v1/person")
    public PersonV1 getFirstVersionPerson(){
        return new PersonV1("Mohammad Husain");
    }

    @GetMapping("v2/person")
    public PersonV2 getSecondVersionPerson(){
        return new PersonV2(new Name("Mohammad", "Husain"));
    }
    /// ----------------------------- ///


}
