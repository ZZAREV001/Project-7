package com.nnk.springboot;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
public class UserTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @Test
    public void homeTest() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

        Model model = new ExtendedModelMap();

        // Act
        String viewName = userController.home(model);

        // Assert
        assertEquals("user/list", viewName);
        assertEquals(expectedUsers, model.getAttribute("users"));
    }

    @Test
    public void addUserTest() {
        // Arrange
        User user = new User();

        // Act
        String viewName = userController.addUser(user);

        // Assert
        assertEquals("user/add", viewName);
    }

    @Test
    public void validateTest() {
        // Arrange
        User user = new User();
        user.setPassword("password");
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        // Mock interactions
        Mockito.when(userRepository.save(any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]); // return the same user object
        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        String viewName = userController.validate(user, bindingResult, model);

        // Assert
        assertEquals("redirect:/user/list", viewName);
        Mockito.verify(userRepository).save(any(User.class)); // verify that save was called
        Mockito.verify(model).addAttribute(eq("users"), anyList()); // verify that model attribute was added
    }

    @Test
    public void showUpdateFormTest() {
        // Arrange
        User user = new User();
        user.setId(1);
        Model model = mock(Model.class);

        // Mock interactions
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        String viewName = userController.showUpdateForm(user.getId(), model);

        // Assert
        assertEquals("user/update", viewName);
        Mockito.verify(userRepository).findById(user.getId()); // verify that findById was called
        Mockito.verify(model).addAttribute("user", user); // verify that model attribute was added
    }

    @Test
    public void updateUserTest() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setUsername("Test User");
        user.setPassword("Test Password");

        // Mock interactions
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Act
        String viewName = userController.updateUser(user.getId(), user, bindingResult, model);

        // Assert
        assertEquals("redirect:/user/list", viewName);
        Mockito.verify(userRepository).save(any(User.class)); // verify that save was called
        Mockito.verify(model)
                .addAttribute("users", Collections.singletonList(user)); // verify that model attribute was added
    }

    @Test
    public void deleteUserTest() {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setUsername("Test User");
        user.setPassword("Test Password");

        // Mock interactions
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        String viewName = userController.deleteUser(user.getId(), model);

        // Assert
        assertEquals("redirect:/user/list", viewName);
        Mockito.verify(userRepository).delete(user); // verify that delete was called
        Mockito.verify(model).addAttribute("users", Collections.emptyList()); // verify that model attribute was added
    }

}
