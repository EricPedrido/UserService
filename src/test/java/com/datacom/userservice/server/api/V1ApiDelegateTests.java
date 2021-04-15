package com.datacom.userservice.server.api;

import com.datacom.userservice.server.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class V1ApiDelegateTests extends AbstractTest {

    @InjectMocks
    V1ApiDelegate v1ApiDelegate = new V1ApiDelegateImpl();

    @Mock
    UserService userService;

    private final List<User> mockUsers = new ArrayList<>();

    @Before
    public void setup() {
        super.setup();
        mockUsers.add(new User().email("mock@user.com").password("mockPassword").firstName("first").lastName("last"));
    }

    @Test
    public void testCreateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        User user = new User().email("test@email.com").password("password").firstName("John").lastName("Smith");

        doAnswer(invocationOnMock -> {
            mockUsers.add(user);
            return null;
        }).when(userService).save(user);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.create(user, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/" + user.getEmail());
        assertThat(mockUsers).contains(user);
    }

    @Test
    public void testCreateNullUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        doNothing().when(userService).save(null);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.create(null, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testGetUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        when(userService.getUserById(mockUser.getEmail())).thenReturn(mockUser);

        ResponseEntity<User> responseEntity = v1ApiDelegate.retrieve(mockUser.getEmail(), "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(mockUser);
    }

    @Test
    public void testGetUnknownUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(userService.getUserById(any(String.class))).thenReturn(null); // null indicating the user wasn't found

        ResponseEntity<User> responseEntity = v1ApiDelegate.retrieve("unknown@email.com", "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void testGetUserWithEmptyEmail() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        when(userService.getUserById("")).thenReturn(mockUser);

        ResponseEntity<User> responseEntity = v1ApiDelegate.retrieve("", "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testDeleteUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        doAnswer(invocationOnMock -> {
            mockUsers.remove(mockUser);
            return null;
        }).when(userService).delete(mockUser.getEmail());

        ResponseEntity<Void> responseEntity = v1ApiDelegate.delete(mockUser.getEmail(), "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(mockUsers).doesNotContain(mockUser);
    }

    @Test
    public void testDeleteUserWithEmptyEmail() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        doNothing().when(userService).delete("");

        ResponseEntity<Void> responseEntity = v1ApiDelegate.delete("", "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testUpdateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        User newUser = new User().email("new@user.com").password("newPassword").firstName("new").lastName("user");

        doAnswer(invocationOnMock -> {
            mockUsers.set(0, newUser);
            return null;
        }).when(userService).update(mockUser.getEmail(), newUser);
        when(userService.getUserById(mockUser.getEmail())).thenReturn(mockUser);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.update(mockUser.getEmail(), newUser, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(204);
        assertThat(mockUsers).contains(newUser);
    }

    @Test
    public void testUpdateUserWithEmptyEmail() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        doNothing().when(userService).update("", mockUser);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.update("", mockUser, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testUpdateUserWithNullUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        doNothing().when(userService).update(mockUser.getEmail(), null);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.update(mockUser.getEmail(), null, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testUpdateUserWithUnknownUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        User mockUser = mockUsers.get(0);
        when(userService.getUserById(any(String.class))).thenReturn(null);

        ResponseEntity<Void> responseEntity = v1ApiDelegate.update("unkown@email.com", mockUser, "");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}
