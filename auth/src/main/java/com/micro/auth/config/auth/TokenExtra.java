package com.micro.auth.config.auth;

import java.io.Serializable;

/**
 * Additional information of user in authorization token
 *
 * @author Sergey Bezvershenko
 */
public class TokenExtra implements Serializable {
    /**
     * User ID
     */
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Create Builder for TokenExtra
     */
    public static TokenExtraBuilder builder() {
        return TokenExtraBuilder.aTokenExtra();
    }

    /**
     * TokenExtra Builder
     */
    public static final class TokenExtraBuilder {
        private long userId;

        private TokenExtraBuilder() {
        }

        public static TokenExtraBuilder aTokenExtra() {
            return new TokenExtraBuilder();
        }

        public TokenExtraBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public TokenExtra build() {
            TokenExtra tokenExtra = new TokenExtra();
            tokenExtra.setUserId(userId);
            return tokenExtra;
        }
    }
}
