package chnu.edu.websecurity26;

/*
 @author Lenovo
 @project web-security26
 @class AccessTest
 @version 1.0.0
 @since 10/19/2026 - 17.10
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTest {

    private static final String BASE = "/api/v1/books";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    private int statusOf(String url) throws Exception {
        return mockMvc.perform(get(url)).andReturn().getResponse().getStatus();
    }

    // ==================== 4. ANONYMOUS / WRONG CREDENTIALS ====================

    // 1
    @Test
    @WithAnonymousUser
    void whenAnonymousGetUserEndpointThen401() throws Exception {
        ResultActions result = mockMvc.perform(get(BASE + "/hello/user"));
        var response = result.andReturn().getResponse();
        assertEquals(401, response.getStatus());
        assertNotNull(response.getErrorMessage());
        assertEquals("Unauthorized", response.getErrorMessage());
    }

    // 2
    @Test
    @WithAnonymousUser
    void whenAnonymousGetAllBooksThen401() throws Exception {
        assertEquals(401, statusOf(BASE));
    }

    // 3
    @Test
    @WithAnonymousUser
    void whenAnonymousGetAdminEndpointThen401() throws Exception {
        assertEquals(401, statusOf(BASE + "/hello/admin"));
    }

    // 4
    @Test
    @WithAnonymousUser
    void whenAnonymousGetManEndpointThen401() throws Exception {
        assertEquals(401, statusOf(BASE + "/hello/man"));
    }

    // 5
    @Test
    @WithAnonymousUser
    void whenWrongPasswordThen401() throws Exception {
        int status = mockMvc.perform(get(BASE + "/hello/admin")
                        .with(httpBasic("admin", "wrongpass")))
                .andReturn().getResponse().getStatus();
        assertEquals(401, status);
    }

    // 6
    @Test
    @WithAnonymousUser
    void whenAnonymousPostBookThen401() throws Exception {
        int status = mockMvc.perform(post(BASE)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"101\",\"title\":\"Anon\",\"author\":\"Anon\",\"year\":2026}"))
                .andReturn().getResponse().getStatus();
        assertEquals(401, status);
    }

    // ==================== 1. USER ====================

    // 7
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserGetUserEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/user"));
    }

    // 8
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserGetAdminEndpointThen403() throws Exception {
        ResultActions result = mockMvc.perform(get(BASE + "/hello/admin"));
        var response = result.andReturn().getResponse();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getErrorMessage());
    }

    // 9
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserGetManEndpointThen403() throws Exception {
        assertEquals(403, statusOf(BASE + "/hello/man"));
    }

    // 10
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserGetUnknownEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/unknown"));
    }

    // 11
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserPostBookThenSuccess() throws Exception {
        int status = mockMvc.perform(post(BASE)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"100\",\"title\":\"Role Testing Book\",\"author\":\"Security Tester\",\"year\":2026}"))
                .andReturn().getResponse().getStatus();
        assertTrue(status == 200 || status == 201, "Expected 200 or 201 but was " + status);
    }

    // ==================== 2. ADMIN ====================

    // 12
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminGetAdminEndpointThen200() throws Exception {
        var response = mockMvc.perform(get(BASE + "/hello/admin")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        assertEquals("Hello Admin!", response.getContentAsString());
    }

    // 13
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminGetManEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/man"));
    }

    // 14
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminGetUserEndpointThen403() throws Exception {
        ResultActions result = mockMvc.perform(get(BASE + "/hello/user"));
        var response = result.andReturn().getResponse();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getErrorMessage());
    }

    // 15
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminGetAllBooksThen200() throws Exception {
        assertEquals(200, statusOf(BASE));
    }

    // 16
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminGetUnknownEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/unknown"));
    }

    // ==================== 3. SUPERADMIN ====================

    // 17
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminGetManEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/man"));
    }

    // 18
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminGetAdminEndpointThen403() throws Exception {
        assertEquals(403, statusOf(BASE + "/hello/admin"));
    }

    // 19
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminGetUserEndpointThen403() throws Exception {
        assertEquals(403, statusOf(BASE + "/hello/user"));
    }

    // 20
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminGetUnknownEndpointThen200() throws Exception {
        assertEquals(200, statusOf(BASE + "/hello/unknown"));
    }
}