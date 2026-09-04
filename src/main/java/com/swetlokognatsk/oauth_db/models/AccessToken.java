package com.swetlokognatsk.oauth_db.models;

import java.util.Set;
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
    private String clientId;

    // TODO why there's no session at the moment of accessing it
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<ScopeEntity> scopes;

    public int getId() {
        return id;
    }

    public AccessTokenValue getValue() {
        return value;
    }

    public String getClientId() {
        return clientId;
    }

    public Set<ScopeEntity> getScopes() {
        return scopes;
    }

    public AccessToken() {
    }

    public AccessToken(final int id, final AccessTokenValue value) {
        this.id = id;
        this.value = value;
    }

    public AccessToken(final AccessTokenValue value, final String clientId) {
        this.value = value;
        this.clientId = clientId;
    }
}
