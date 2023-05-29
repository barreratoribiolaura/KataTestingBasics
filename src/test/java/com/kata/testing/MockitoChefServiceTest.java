package com.kata.testing;

import com.kata.testing.common.Chef;
import com.kata.testing.common.ChefResponse;
import com.kata.testing.jUnit.JUnitChefService;
import com.kata.testing.mockito.MockitoChefService;
import com.kata.testing.mockito.resources.ChefEntity;
import com.kata.testing.mockito.resources.IChefRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoChefServiceTest {

    @InjectMocks
    MockitoChefService mockitoChefService;

    @Mock
    IChefRepository chefRepository;

    static final Chef CHEF = new Chef("chef","apellido", LocalDate.now());

    @Test
    void findChefReturnNullTest() {
        assertNull(mockitoChefService.findChef(CHEF));
    }

    @Test
    void findChefReturnChefResponseTest() {
        ChefResponse EXPECTED_CHEF_RESPONSE = new ChefResponse(CHEF.getName(),CHEF.getSurname(), CHEF.getBirthday());
        ChefEntity CHEF_ENTITY = new ChefEntity(CHEF.getName(),CHEF.getSurname(), CHEF.getBirthday());

        when(chefRepository.findByNameAndSurname(CHEF.getName(),CHEF.getSurname())).thenReturn(Optional.of(CHEF_ENTITY));

        ChefResponse CHEF_RESPONSE = mockitoChefService.findChef(CHEF);
        assertThat(CHEF_RESPONSE).usingRecursiveComparison().isEqualTo(EXPECTED_CHEF_RESPONSE);
    }

    @Test
    void findAllPersonsReturnChefListTest() {
        assertInstanceOf(List.class,mockitoChefService.findAllPersons());
    }

    @Test
    void savePersonTest() {
        Chef newChef = new Chef("nombre","apellidos", LocalDate.now());
        String EXPECTED_STRING_RESPONSE = "Has guardado correctamente al chef " + newChef.getName() + " " + newChef.getSurname();
        assertEquals(EXPECTED_STRING_RESPONSE,mockitoChefService.save(newChef));
    }

    @Test
    void deletePersonInChefListTest() {
        ChefEntity CHEF_ENTITY = new ChefEntity(CHEF.getName(),CHEF.getSurname(), CHEF.getBirthday());
        mockitoChefService.delete(CHEF);
        Mockito.verify(chefRepository).delete(CHEF_ENTITY);
    }

    @Test
    void chefExistsInChefListReturnFalseTest() {
        Chef newChef = new Chef("nombre","apellidos", LocalDate.now());
        assertFalse(mockitoChefService.chefExist(newChef));
    }

    @Test
    void chefExistsInChefListReturnTrueTest() {
        when(chefRepository.existsByNameAndSurname(CHEF.getName(),CHEF.getSurname())).thenReturn(true);
        assertTrue(mockitoChefService.chefExist(CHEF));
    }





}
