package com.swetlokognatsk.oauth_db.models;

public record RefreshAndAccessTokensPair(RefreshToken refreshToken, AccessToken accessToken) {

    public String toString() {
        return "refreshToken: %s </br> accessToken: %s".formatted(refreshToken, accessToken);
    }
}
