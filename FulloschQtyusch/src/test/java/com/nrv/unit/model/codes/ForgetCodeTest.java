package com.nrv.unit.model.codes;

import model.Virologist;
import model.codes.ForgetCode;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ForgetCodeTest {
    private ForgetCode forgetCode = new ForgetCode();

    @Test
    public void test_createFail() {
        Virologist virologist = new Virologist();
        Assertions.assertThrows(Exception.class,() -> forgetCode.Create(virologist));
    }

    @Test
    public void test_createSuccess() {
        Virologist virologist = new Virologist();
        virologist.AddAminoAcid(forgetCode.getAminoAcidPrice());
        virologist.AddNucleotide(forgetCode.getNucleotidePrice());
        Assertions.assertDoesNotThrow(() -> forgetCode.Create(virologist));
    }
}
