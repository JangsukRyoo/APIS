package com.jsr.restapi.service;

import java.security.InvalidKeyException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/key")
public class KeyController {

    @PostMapping("/aria")
    public String getAriaKey(@RequestBody Map<String, String> map) throws InvalidKeyException {

        String encryptedPack = "";

        ARIA aria = new ARIA("key");
        Map<String, String> datastore = new HashMap();

        String[] splitWord = map.get("text").split("");

        for(String word : splitWord){

            System.out.println(word);
            String encryptData = aria.Encrypt(word);
            System.out.println(encryptData);

            datastore.put(word, encryptData);

            System.out.println(datastore.get("1"));
            System.out.println("이거 " + aria.Decrypt(datastore.get("1")));
        }

        for(String key : datastore.keySet()) {
            encryptedPack = "";
            encryptedPack += datastore.get(key) + " ";
            System.out.print(encryptedPack);
        }

        return encryptedPack;
    }

    // @PostMapping("/aria-button")
    // public String getAriadecrypt() {
    //
    // 	ARIA aria = new ARIA("key");
    // 	String decyprtAriaData = "";
    //
    // 	System.out.println("이거? " + encryptedPack);
    //
    // 	String[] splitencryptedWord = encryptedPack.split(" ");
    // 	for(String word : splitencryptedWord) {
    // 		decyprtAriaData += aria.Decrypt(word);
    // 		System.out.println(decyprtAriaData);
    // 	}
    // 	return decyprtAriaData;
    // }

    @PostMapping("/aria-button")
    public String getAriadecrypt(@RequestBody Map<String, String> map) {

        ARIA aria = new ARIA("key");
        Map<String, String> datastore = new HashMap();

        String encryptedPack = "";
        String decryptedPack = "";
        String[] splitWord = map.get("text").split("");

        for(String word : splitWord){
            System.out.println(word);
            String encryptData = aria.Encrypt(word);
            System.out.println(encryptData);
            datastore.put(word, encryptData);
        }

        for(String key : datastore.keySet()) {
            encryptedPack += datastore.get(key) + " ";
            System.out.print("마지막 예제 " + encryptedPack);
        }


        String[] splitencryptedWord = encryptedPack.split(" ");

        for(String word : splitencryptedWord) {
            decryptedPack += aria.Decrypt(word);
            System.out.println(decryptedPack);
        }

        return decryptedPack;
    }
}