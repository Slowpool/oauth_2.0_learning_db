package com.swetlokognatsk.oauth_db.daos;

import org.springframework.transaction.annotation.Transactional;
import com.swetlokognatsk.oauth_db.*;
import com.swetlokognatsk.oauth_db.models.*;
import jakarta.persistence.*;

public class RefreshTokensDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RefreshToken findByValue(final RefreshTokenValue refreshTokenValue) throws RefreshTokenNotFoundException {
        var criteriaBuilder = entityManager.getCriteriaBuilder();

        var query = criteriaBuilder.createQuery(RefreshToken.class);

        var root = query.from(RefreshToken.class);

        var valuePredicate = criteriaBuilder.equal(root.get("value"), refreshTokenValue);

        query.where(valuePredicate);

        var result = entityManager.createQuery(query);

        try {
            return result.getSingleResult();
        } catch (NoResultException e) {
            throw new RefreshTokenNotFoundException();
        }
    }

    @Transactional
    public void save(final RefreshToken refreshToken) {
        entityManager.persist(refreshToken);
    }
}
