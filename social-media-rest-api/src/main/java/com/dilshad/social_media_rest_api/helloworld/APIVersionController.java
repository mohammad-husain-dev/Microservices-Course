package com.dilshad.social_media_rest_api.helloworld;

import com.dilshad.social_media_rest_api.helloworld.person.Name;
import com.dilshad.social_media_rest_api.helloworld.person.PersonV1;
import com.dilshad.social_media_rest_api.helloworld.person.PersonV2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "/person", params = "version")
    public ResponseEntity<Object> getPerosn(@RequestParam int version){
        ResponseEntity<Object> responseEntity=null;
        if(version == 1){
            responseEntity=new ResponseEntity<>(new PersonV1("Mohammad Husain"), HttpStatus.OK);
        } else if(version == 2){
            responseEntity=new ResponseEntity<>(new PersonV2(new Name("Mohammad", "Husain")), HttpStatus.OK);
        }

        return responseEntity;
    }
}
