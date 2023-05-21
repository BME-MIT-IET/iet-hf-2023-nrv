package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ChoreaCode;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChoreaCodeTest {
    private ChoreaCode choreaCode = new ChoreaCode();

    @Test
    public void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> choreaCode.Create(virologist));
    }

    @Test
    public void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(choreaCode.getAminoAcidPrice());
        virologist.AddNucleotide(choreaCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> choreaCode.Create(virologist));
    }
}
