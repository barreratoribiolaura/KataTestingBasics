package com.kata.testing;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import com.kata.testing.jUnit.JUnitChefService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JUnitChefServiceTest {

    static final Chef CHEF = new Chef("chef","apellido", LocalDate.now());

    @InjectMocks
    JUnitChefService jUnitChefService;

    @BeforeEach
    public void beforeEach(){
        jUnitChefService = new JUnitChefService(new ArrayList(Arrays.asList(CHEF)));
    }

    @Test
    void findChefReturnNullTest() {
        jUnitChefService = new JUnitChefService(new ArrayList(Arrays.asList()));
        assertNull(jUnitChefService.findChef(CHEF));
    }

    @Test
    void findChefReturnChefResponseTest() {
        ChefResponse EXPECTED_CHEF_RESPONSE = new ChefResponse("chef","apellido", CHEF.getBirthday());
        jUnitChefService = new JUnitChefService(new ArrayList(Arrays.asList(CHEF)));
        ChefResponse CHEF_RESPONSE = jUnitChefService.findChef(CHEF);
        assertInstanceOf(ChefResponse.class,jUnitChefService.findChef(CHEF));
        assertThat(CHEF_RESPONSE).usingRecursiveComparison().isEqualTo(EXPECTED_CHEF_RESPONSE);
    }

    @Test
    void findAllPersonsReturnListChefResponseTest() {
        assertInstanceOf(List.class,jUnitChefService.findAllPersons());
    }

    @Test
    void savePersonInChefListTest() {
        Chef newChef = new Chef("nombre","apellidos", LocalDate.now());
        String EXPECTED_STRING_RESPONSE = "Has guardado correctamente a " + newChef.getName() + " " + newChef.getSurname();
        assertEquals(EXPECTED_STRING_RESPONSE,jUnitChefService.save(newChef));
    }

    @Test
    void deletePersonInChefListTest() {
        jUnitChefService.delete(CHEF);
    }

    @Test
    void chefExistsInChefListReturnFalseTest() {
        Chef newChef = new Chef("nombre","apellidos", LocalDate.now());
        assertFalse(jUnitChefService.chefExist(newChef));
    }

    @Test
    void chefExistsInChefListReturnTrueTest() {

        assertTrue(jUnitChefService.chefExist(CHEF));
    }

}
