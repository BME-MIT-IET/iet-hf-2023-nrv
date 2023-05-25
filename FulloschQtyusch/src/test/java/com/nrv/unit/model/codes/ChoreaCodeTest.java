package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ChoreaCode;
import model.codes.GeneticCode;
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
        Assertions.assertThrows(GeneticCode.GeneticCodeException.class,() -> choreaCode.create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.addAminoAcid(choreaCode.getAminoAcidPrice());
        virologist.addNucleotide(choreaCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> choreaCode.create(virologist));
    }
}
