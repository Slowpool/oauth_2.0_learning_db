package com.swetlokognatsk.oauth_db.models;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

@Entity
@Table(name = "access_tokens")
public final class AccessToken {
    @Id
    // TODO what does this strategy influence on?
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    // TODO what's going on here?
    @AttributeOverride(name = "value", column = @Column(name = "access_token", nullable = false))
    private AccessTokenValue value;

    @Column(name = "client_id")
    private int clientId;

    // TODO why there's no session at the moment of accessing it
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<ScopeEntity> scopes;

    @Column(name = "expires_in")
    private int expiresIn;

    @Column(name = "created_on_programmatically")
    private LocalDateTime createdOnProgrammatically;

    @Column(name = "created_on_db", insertable = false, updatable = false)
    private LocalDateTime createdOnDb;

    public int getId() {
        return id;
    }

    public AccessTokenValue getValue() {
        return value;
    }

    public int getClientId() {
        return clientId;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    // experiment. what if you have two created_on
    public LocalDateTime getCreatedOnProgrammatically() {
        return createdOnProgrammatically;
    }

    public LocalDateTime getCreatedOnDb() {
        return createdOnDb;
    }

    public Set<ScopeEntity> getScopes() {
        return scopes;
    }

    public AccessToken() {
    }

    public AccessToken(final AccessTokenValue value, final int clientId, final LocalDateTime createdOnProgrammatically, final int expiresIn) {
        this.value = value;
        this.clientId = clientId;
        this.expiresIn = expiresIn;
        this.createdOnProgrammatically = createdOnProgrammatically;
    }
}
