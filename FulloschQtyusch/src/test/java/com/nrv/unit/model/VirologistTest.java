package com.nrv.unit.model;

import model.Game;
import model.Virologist;
import model.map.Field;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VirologistTest {
    @Mock
    private Game game;

    @InjectMocks
    private Virologist virologist = new Virologist();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_kill() {
        Field field = new Field();
        field.AddVirologist(virologist);

        when(game.GetCurrentPlayer()).thenReturn(virologist);

        virologist.Kill();

        Assertions.assertEquals(0, field.GetVirologists().size());
        verify(game, times(1)).GetCurrentPlayer();
    }
}
