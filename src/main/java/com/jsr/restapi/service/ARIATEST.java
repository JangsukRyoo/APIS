package com.jsr.restapi.service;

import java.util.HashMap;

public class ARIATEST {
    static String encryptedPack;

    public static void main(String args[]) {
        ARIA aria = new ARIA("key");
        String map = "12";
        ariaEncrypt(map, aria);
        Decrypt(aria);
    }

    public static void ariaEncrypt(String map, ARIA aria){
        String [] splitWord =map.split("");
        HashMap<String, String> ariaMap = new HashMap();
        encryptedPack ="";
        System.out.println("******** 암호화 시작 ********");
        for(String word : splitWord){
            String encryptData = aria.Encrypt(word);
            System.out.println(word+" 의 암호화: "+encryptData);
            ariaMap.put(word,encryptData);
        }

        for(String key : ariaMap.keySet()){
            encryptedPack += ariaMap.get(key)+" ";
        }

        System.out.println("암호화 합침: "+ encryptedPack);
        System.out.println("******** 암호화 종료 ********");
    }

    public static void Decrypt(ARIA aria){
        System.out.println("******** 복호화 시작 ********");
        String [] splitEncrytpedWord = encryptedPack.split(" ");
        String decryptedPack ="";

        for(String word : splitEncrytpedWord){
            String decryptData = aria.Decrypt(word);
            System.out.println("복호화: "+decryptData);
            decryptedPack += decryptData;
        }

        System.out.println("복호화 완성: "+ decryptedPack);
        System.out.println("******** 복호화 종료 ********");
    }

    String[] splitencryptedWord = encryptedPack.split(" ");


}


