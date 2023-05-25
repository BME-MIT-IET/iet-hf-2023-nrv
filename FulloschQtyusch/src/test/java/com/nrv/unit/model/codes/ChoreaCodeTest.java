package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ChoreaCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChoreaCodeTest {
    private ChoreaCode choreaCode = new ChoreaCode();

    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> choreaCode.Create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(choreaCode.getAminoAcidPrice());
        virologist.AddNucleotide(choreaCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> choreaCode.Create(virologist));
    }
}
