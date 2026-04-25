package com.api_search.project.cryptography;

import org.mindrot.jbcrypt.BCrypt;

public class Cryptography {
    public String enc(String password){
        String password_hash = BCrypt.hashpw(password, BCrypt.gensalt());
        return password_hash;
    }


}
