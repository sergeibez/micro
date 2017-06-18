package com.micro.auth.config.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Additional information of user in authorization token
 *
 * @author Sergey Bezvershenko
 */
@Data
@Builder
public class TokenExtra implements Serializable {
    /**
     * User ID
     */
    long userId;
}
