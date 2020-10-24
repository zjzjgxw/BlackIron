package com.gxw.store.project.sso.service;

import java.util.HashMap;

/**
 * token
 */
public interface TokenService {

    HashMap<String, String> createToken(Long id, String name);

    HashMap<String, String> createToken(Long id, String name, Long businessId);

    HashMap<String, String> createToken(Long id, String name, String keyPrefix);

    HashMap<String, String> createToken(Long id, String name, Long businessId, String keyPrefix);

    void deleteToken(String token);

    void deleteToken(String token, String keyPrefix);

    HashMap<String, String> RefreshToken(String token);

    HashMap<String, String> RefreshToken(String token, String keyPrefix);

}
