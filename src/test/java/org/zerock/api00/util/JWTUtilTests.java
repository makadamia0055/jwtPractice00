package org.zerock.api00.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Log4j2
@SpringBootTest
public class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenerate(){
        Map<String, Object> claimMap = Map.of("mid", "ABCDE");

        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        log.info(jwtStr);
    }
    @Test
    public void expiredTokenTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MjI4MzY4MTgsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNzIyODM2NzU4fQ.fmxFSLj3BOI6oH5AsV1_Vh2jjlRTSM7IAxhsNEbhHnc";

        Assertions.assertThrows(ExpiredJwtException.class, ()-> jwtUtil.validateToken(token));


    }
    @Test
    public void wrongTokenTest(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MjI4MzY4MTgsIm1pZCI6IkFCQ0RFasIiwiaWF0IjoxNzIyODM2NzU4fQ.fmxFSLj3BOI6oH5AsV1_Vh2jjlRTSM7IAxhsNEbhHnc";

        Assertions.assertThrows(SignatureException.class, ()-> jwtUtil.validateToken(token));


    }

    @Test
    public void testAll(){
        String jwtStr = jwtUtil.generateToken(Map.of("mid", "AAAA", "email", "aaaa@bbb.com"), 1);

        log.info(jwtStr);

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        log.info("MID : "+ claim.get("mid"));
        log.info("EMAIL : " + claim.get("email"));
    }
}
