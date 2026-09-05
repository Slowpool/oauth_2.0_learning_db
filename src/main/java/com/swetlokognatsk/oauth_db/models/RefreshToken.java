package com.swetlokognatsk.oauth_db.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "refresh_token", nullable = false))
    private RefreshTokenValue value;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "expires_in")
    private int expiresIn;

    @Column(name = "created_on_programmatically")
    private LocalDateTime createdOnProgrammatically;

    @Column(name = "created_on_db", insertable = false, updatable = false)
    private LocalDateTime createdOnDb;

    public int getId() {
        return id;
    }

    public RefreshTokenValue getValue() {
        return value;
    }

    public int getClientId() {
        return clientId;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public LocalDateTime getCreatedOnProgrammatically() {
        return createdOnProgrammatically;
    }

    public LocalDateTime getCreatedOnDb() {
        return createdOnDb;
    }

    public RefreshToken() {
    }

    public RefreshToken(final RefreshTokenValue refreshToken, final int clientId, final LocalDateTime createdOnProgrammatically, final int expiresIn) {
        this.value = refreshToken;
        this.clientId = clientId;
        this.createdOnProgrammatically = createdOnProgrammatically;
        this.expiresIn = expiresIn;
    }
}
