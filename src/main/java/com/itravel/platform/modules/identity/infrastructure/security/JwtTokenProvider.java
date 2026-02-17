package com.itravel.platform.modules.identity.infrastructure.security;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.service.TokenProvider;
import com.itravel.platform.modules.identity.infrastructure.exception.InvalidVerificationTokenException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${security.jwt.signerKey}")
    String SIGNER_KEY;

    @Value("${security.jwt.issuer}")
    String ISSUER;

    @Value("${security.jwt.audience}")
    String AUDIENCE;

    @Value("${security.jwt.valid-duration}")
    long VALID_DURATION;

    @Override
    public String generateAccessToken(AccountId accountId, AccountLinkType accountType, String roleList, String permissionList) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet;
        if(accountType.equals(AccountLinkType.CUSTOMER)){
            jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(accountId.value())
                .issuer(ISSUER)
                .audience(AUDIENCE)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .claim("type", accountType.toString())
                .jwtID(UUID.randomUUID().toString())
                    .build();

        }else {
            jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(accountId.value())
                .issuer(ISSUER)
                .audience(AUDIENCE)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                )).claim("role", roleList)
                .claim("permission", permissionList)
                .claim("type", accountType.toString())
                .jwtID(UUID.randomUUID().toString())
                .build();

        }

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        signedJWT.sign(new MACSigner(SIGNER_KEY));
        return signedJWT.serialize();
    }

    public boolean introspect(String token) {
        boolean valid = true;
        try {
            verifyToken(token);
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verified = signedJWT.verify(jwsVerifier);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!(verified && expiryTime.after(new Date())))
            throw new InvalidVerificationTokenException();

        return signedJWT;
    }
}
