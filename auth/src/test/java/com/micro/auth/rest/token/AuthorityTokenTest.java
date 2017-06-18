package com.micro.auth.rest.token;

import com.micro.auth.rest.BaseRestTest;
import org.junit.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test the spring security endpoint '/oauth/token'
 *
 * @author Sergey Bezvershenko
 */
public class AuthorityTokenTest extends BaseRestTest {
    private ResultActions obtainTokenResult(String docName, String grantType, Map<String, String> extraParams) throws Exception {
        String clientId = "micro";
        String clientSecret = "micro1234";

        String username = "admin";
        String password = "admin1234";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (extraParams != null) {
            params.setAll(extraParams);
        }

        params.add("grant_type", grantType);

        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        params.add("username", username);
        params.add("password", password);

        return mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("token_type").value("bearer"))
                .andExpect(jsonPath("access_token").isNotEmpty())
                .andExpect(jsonPath("refresh_token").isNotEmpty())
                .andExpect(jsonPath("expires_in").isNumber())
                .andExpect(jsonPath("scope").value("read write"))
                .andExpect(jsonPath("jti").isNotEmpty())
                .andExpect(jsonPath("authorities").value(containsInAnyOrder("IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_ADMIN", "ROLE_STAFF")))
                .andExpect(jsonPath("extra.userId").value(4L))

                .andDo(document(docName,
                        requestHeaders(
                                headerWithName("Accept").description(MediaType.APPLICATION_JSON_VALUE),
                                headerWithName("Authorization").description("Basic auth credentials ('Basic base64('clientId:clientSecret')')")
                        ),
                        requestParameters(
                                parameterWithName("grant_type").description("OAuth 2 grant type. 'password' ot 'refresh_token'"),
                                parameterWithName("refresh_token").description("Refresh token. Is used with 'password' grant type.").optional(),
                                parameterWithName("client_id").description("Client ID"),
                                parameterWithName("client_secret").description("Client Secret"),
                                parameterWithName("username").description("User name"),
                                parameterWithName("password").description("User password")
                        ),
                        responseFields(
                                fieldWithPath("token_type").description("Token type: 'bearer'"),
                                fieldWithPath("access_token").description("Access token"),
                                fieldWithPath("refresh_token").description("Refresh token"),
                                fieldWithPath("expires_in").description("The lifetime in seconds of the access token"),
                                fieldWithPath("scope").description("Scope: read, write"),
                                fieldWithPath("jti").optional().description("JWT ID - case sensitive unique identifier of the token even among different issuers"),

                                fieldWithPath("authorities").description("Authorities granted to user"),

                                fieldWithPath("extra").description("Extra user information"),
                                fieldWithPath("extra.userId").description("User ID")
                        )
                ));
    }

    private String obtainRefreshToken() throws Exception {
        String resultString = obtainTokenResult("auth-obtain-refresh-token", "password", null)
                .andReturn().getResponse().getContentAsString();
        return new JacksonJsonParser().parseMap(resultString).get("refresh_token").toString();
    }

    @Test
    public void testObtainAccessToken() throws Exception {
        obtainTokenResult("auth-obtain-token", "password", null);
    }

    @Test
    public void testObtainAccessTokenByRefreshToken() throws Exception {
        String refreshToken = obtainRefreshToken();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("refresh_token", refreshToken);

        obtainTokenResult("auth-obtain-token-by-refresh", "refresh_token", params);
    }
}
