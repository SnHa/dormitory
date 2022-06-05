package com.atsun.dormitory.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author SH
 */
public class JwtUtil {

    private static final String TOKEN_SECRET = "6950808042088342028L";
    private static final Long EXPIRE_TIME = 0L;
    private static final Map<String, Object> HEADER = new HashMap<>(2);
    public static final String JWT_USER_ID_KEY = "userid";

    static {
        HEADER.put("typ", "JWT");
        HEADER.put("alg", "HS256");
    }

    public static boolean verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String generateToken(String userid) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return JWT.create()
                .withHeader(HEADER)
                .withClaim(JWT_USER_ID_KEY, String.valueOf(userid))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(algorithm);
    }

    public static String decode(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(JWT_USER_ID_KEY).asString();
    }
}
