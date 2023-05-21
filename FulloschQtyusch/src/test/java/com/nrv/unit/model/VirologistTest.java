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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void test_kill_success(){
        Field field = new Field();
        field.AddVirologist(virologist);
        virologist.SetField(field);

        when(game.GetCurrentPlayer()).thenReturn(virologist);
        doNothing().when(game).RemoveVirologist(virologist);

        virologist.Kill();

        Assertions.assertEquals(0, field.GetVirologists().size());
        verify(game,times(1)).GetCurrentPlayer();
    }
}
