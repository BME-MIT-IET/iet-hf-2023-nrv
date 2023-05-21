package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ForgetCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ForgetCodeTest {
    private ForgetCode forgetCode = new ForgetCode();

    @Test
    void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> forgetCode.Create(virologist));
    }

    @Test
    void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(forgetCode.getAminoAcidPrice());
        virologist.AddNucleotide(forgetCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> forgetCode.Create(virologist));
    }
}
