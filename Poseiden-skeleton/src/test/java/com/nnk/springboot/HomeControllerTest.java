package com.nnk.springboot;

import com.nnk.springboot.controllers.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void homeTest() {
        String viewName = homeController.home(model);

        assertEquals("home", viewName);
    }

    @Test
    public void adminHomeTest() {
        String viewName = homeController.adminHome(model);

        assertEquals("redirect:/bidList/list", viewName);
    }
}

