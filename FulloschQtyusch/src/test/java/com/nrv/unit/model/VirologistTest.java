package com.nrv.unit.model;

import model.Game;
import model.Virologist;
import model.Virologist.VirologistException;
import model.agents.Agent;
import model.codes.GeneticCode;
import model.equipments.Equipment;
import model.map.Field;
import model.map.Material;
import model.strategy.IAttackStr;
import model.strategy.ICollectStr;
import model.strategy.IDropStr;
import model.strategy.IEquipStr;
import model.strategy.IInjectStr;
import model.strategy.IInjectedStr;
import model.strategy.ILearnStr;
import model.strategy.ILootStr;
import model.strategy.ILootedStr;
import model.strategy.IMoveStr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VirologistTest {
    @Mock
    private Field mockField;

    @Mock
    private Game mockGame;

    @Mock
    private IAttackStr mockAttackStr;

    @Mock
    private IInjectedStr mockInjectedStr;

    @Mock
    private IDropStr mockDropStr;

    @Mock
    private IMoveStr mockMoveStr;

    @Mock
    private ILootStr mockLootStr;

    @Mock
    private ILootedStr mockLootedStr;

    @Mock
    private ICollectStr mockCollectStr;

    @Mock
    private ILearnStr mockLearnStr;

    @Mock
    private IEquipStr mockEquipStr;

    @Mock
    private IInjectStr mockInjectStr;

    @InjectMocks
    private Virologist virologist = new Virologist();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testKill() {
        when(mockGame.getCurrentPlayer()).thenReturn(virologist);

        virologist.kill();

        verify(mockGame, times(1)).getCurrentPlayer();
        verify(mockGame, times(1)).removeVirologist(virologist);
        verify(mockField, times(1)).removeVirologist(virologist);
    }

    @Test
    public void testAttack() {
        Virologist target = mock(Virologist.class);

        virologist.attack(target);

        verify(mockAttackStr, times(1)).attack(virologist, target);
    }

    @Test
    public void testAttack_Fails() {
        Virologist target = mock(Virologist.class);
        virologist.setActionCount(0);

        virologist.attack(target);

        verify(mockAttackStr, times(0)).attack(virologist, target);
    }

    @Test
    public void testTargetedWith() {
        Virologist mockAttacker = mock(Virologist.class);
        Agent mockAgent = mock(Agent.class);

        virologist.targetedWith(mockAttacker, mockAgent);

        verify(mockInjectedStr, times(1)).injected(mockAttacker, virologist, mockAgent);
    }

    @Test
    public void testRemoveEquipment() {
        Equipment equipment = mock(Equipment.class);
        virologist.addEquipment(equipment);

        virologist.removeEquipment(equipment);

        Assertions.assertEquals(0, virologist.getEquipments().size());
    }

    @Test
    public void testMoveToField() {
        Field newField = mock(Field.class);

        virologist.move(newField);

        verify(mockMoveStr).move(virologist, mockField, newField);
    }

    @Test
    public void testRandomMove() {
        List<Field> neighbours = new ArrayList<>();
        Field neighbour = mock(Field.class);
        neighbours.add(neighbour);

        when(mockField.getNeighbours()).thenReturn(neighbours);

        virologist.move();

        verify(mockMoveStr).move(virologist, mockField, neighbour);
    }

    @Test
    public void testDrop() {
        Field field = mock(Field.class);
        Equipment equipment = mock(Equipment.class);

        virologist.setField(field);
        virologist.addEquipment(equipment);

        virologist.drop();

        verify(mockDropStr).drop(virologist, field, equipment);

        Assertions.assertTrue(virologist.getEquipments().isEmpty());
    }

    @Test
    public void testLootAminoAcidFrom() {
        Virologist targetVirologist = mock(Virologist.class);

        virologist.lootAminoAcidFrom(targetVirologist);

        verify(mockLootStr).lootAmino(virologist, targetVirologist);
    }

    @Test
    public void testLootNucleotideFrom() {
        Virologist targetVirologist = mock(Virologist.class);

        virologist.lootNucleotideFrom(targetVirologist);

        verify(mockLootStr).lootNucleotide(virologist, targetVirologist);
    }

    @Test
    public void testLootEquipmentFrom() {
        Virologist targetVirologist = mock(Virologist.class);

        virologist.lootEquipmentFrom(targetVirologist);

        verify(mockLootStr).lootEquipment(virologist, targetVirologist);
    }

    @Test
    public void testCollect() {
        Material material = mock(Material.class);

        virologist.collect(material);

        verify(mockCollectStr).collect(virologist, mockField, material);
    }

    @Test
    public void testLearn() {
        virologist.learn();

        verify(mockLearnStr).learn(virologist, mockField);
    }

    @Test
    public void testEquip() {
        virologist.equip();

        verify(mockEquipStr).equip(virologist, mockField);
    }

    @Test
    public void testAddAgent_ExistingAgentWithLowerTTL_ReplacesTTL() {
        Agent existingAgent = mock(Agent.class);
        Agent newAgent = mock(Agent.class);


        virologist.getAgents().add(existingAgent);
        virologist.addAgent(newAgent);

        when(newAgent.getTtl()).thenReturn(10);

        Assertions.assertTrue(virologist.getAgents().contains(newAgent));
        Assertions.assertEquals(10, newAgent.getTtl());


    }

    @Test
    public void testAddAgent_ExistingAgentWithHigherTTL_NoChange() {
        Agent existingAgent = mock(Agent.class);
        Agent newAgent = mock(Agent.class);

        virologist.getAgents().add(existingAgent);
        virologist.addAgent(newAgent);

        Assertions.assertTrue(virologist.getAgents().contains(newAgent));

    }

    @Test
    public void testAddAgent_NewAgent_AddedToList() {
        Agent newAgent = mock(Agent.class);

        when(newAgent.getTtl()).thenReturn(10);
        virologist.addAgent(newAgent);

        Assertions.assertTrue(virologist.getAgents().contains(newAgent));

        Assertions.assertEquals(10, newAgent.getTtl());
    }

    @Test
    public void testAddEquipment_LessThanMaxItems_EquipmentAddedAndApplied() {
        Equipment equipment = mock(Equipment.class);

        virologist.getEquipments().add(mock(Equipment.class));
        virologist.addEquipment(equipment);

        Assertions.assertTrue(virologist.getEquipments().contains(equipment));

        verify(equipment).apply(virologist);

        verify(equipment).applyStrategy(virologist);

    }

    @Test
    public void testAddEquipment_MaxItemsReached_EquipmentNotAddedOrApplied() {
        Equipment equipment = mock(Equipment.class);


        virologist.getEquipments().add(mock(Equipment.class));
        virologist.getEquipments().add(mock(Equipment.class));
        virologist.getEquipments().add(mock(Equipment.class));

        virologist.addEquipment(equipment);

        Assertions.assertFalse(virologist.getEquipments().contains(equipment));

        verify(equipment, never()).apply(any());

        verify(equipment, never()).applyStrategy(any());

    }

    @Test
    public void testGetEquipment_EquipmentsNotEmpty_EquipmentReturnedAndRemoved() {
        Equipment equipment1 = mock(Equipment.class);
        Equipment equipment2 = mock(Equipment.class);
        virologist.getEquipments().add(equipment1);
        virologist.getEquipments().add(equipment2);

        Equipment result = virologist.getEquipment();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(equipment2, result);

        Assertions.assertFalse(virologist.getEquipments().contains(equipment2));
    }

    @Test
    public void testGetEquipment_EquipmentsEmpty_ExceptionThrown() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, virologist::getEquipment);
    }

    @Test
    public void testAddGeneticCode_CodeAddedToList() {
        GeneticCode code = mock(GeneticCode.class);

        virologist.addGeneticCode(code);

        Assertions.assertTrue(virologist.getGeneticCodes().contains(code));
    }

    @Test
    public void testAddGeneticCode_CodeAlreadyAdded_CodeNotAddedToList() {
        Virologist virologist = new Virologist();
        GeneticCode code = mock(GeneticCode.class);
        virologist.getGeneticCodes().add(code);

        virologist.addGeneticCode(code);

        Assertions.assertEquals(1, virologist.getGeneticCodes().size());
    }

    @Test
    public void testInject() {
        Virologist target = mock(Virologist.class);
        GeneticCode mockGeneticCode = mock(GeneticCode.class);

        virologist.inject(target, mockGeneticCode);

        verify(mockInjectStr, times(1)).inject(virologist,target, mockGeneticCode);
    }

    @Test
    public void testSelfTargetedWith(){
        Agent agent = mock(Agent.class);

        virologist.targetedWith(agent);

        verify(mockInjectedStr, times(1)).injected(virologist, agent);
    }

    @Test
    public void testStealAminoAcid(){
        Virologist target = mock(Virologist.class);

        virologist.stealAminoAcid(target);

        verify(mockLootedStr, times(1)).lootedForAminoAcid(target, virologist);
    }

    @Test
    public void testStealNucleotide(){
        Virologist target = mock(Virologist.class);

        virologist.stealNukleotid(target);

        verify(mockLootedStr, times(1)).lootedForNukleotide(target, virologist);
    }

    @Test
    public void testStealEquipment() {
        Equipment equipment = mock(Equipment.class);
        Virologist target = mock(Virologist.class);
        virologist.getEquipments().add(equipment);
        virologist.getEquipments().add(equipment);

        virologist.stealEquipment(target);

        verify(mockLootedStr).lootedForEquipment(target, virologist, equipment);
    }
    @Test
    public void testEndTurn() {
        virologist.endTurn();

        verify(mockGame).nextPlayer(virologist.getGeneticCodes().size());
        Assertions.assertEquals(3, virologist.getActionCount());
    }
    @Test
    public void testAddAminoAcid_increaseWithinLimit() {
        int delta = 10;
        virologist.addAminoAcid(delta);
        Assertions.assertEquals(10, virologist.getAminoAcid());
    }

    @Test
    public void testAddAminoAcid_increaseAboveLimit() {
        int delta = 40;
        virologist.addAminoAcid(delta);
        Assertions.assertEquals(20, virologist.getAminoAcid());
    }

    @Test
    public void testAddNucleotide_increaseWithinLimit() {
        int delta = 10;
        virologist.addNucleotide(delta);
        Assertions.assertEquals(delta, virologist.getNucleotide());
    }

    @Test
    public void testAddNucleotide_increaseAboveLimit() {
        int delta = 40;
        virologist.addNucleotide(delta);
        Assertions.assertEquals(20, virologist.getNucleotide());
    }

    @Test
    public void testRemoveNucleotide_decreaseWithinLimit() throws VirologistException {
        int initialNucleotide = 20;
        int delta = 10;
        virologist.setNucleotide(20);
        virologist.removeNucleotide(delta);
        Assertions.assertEquals(initialNucleotide - delta, virologist.getNucleotide());
    }

    @Test
    public void testRemoveAminoAcid_decreaseWithinLimit() throws VirologistException {
        int initialAminoAcid = 20;
        int delta = 10;
        virologist.setAminoAcid(initialAminoAcid);
        virologist.removeAminoAcid(delta);
        Assertions.assertEquals(initialAminoAcid - delta, virologist.getAminoAcid());
    }

    @Test
    public void testRemoveNucleotide_decreaseAboveLimit() {
        int initialNucleotide = 20;
        int delta = 30;
        virologist.setNucleotide(initialNucleotide);
        Assertions.assertThrows(VirologistException.class, () -> virologist.removeNucleotide(delta));
    }
    @Test
    public void testRemoveAminoAcid_decreaseAboveLimit() {
        int initialAminoAcid = 20;
        int delta = 30;
        virologist.setAminoAcid(initialAminoAcid);
        Assertions.assertThrows(VirologistException.class, () -> virologist.removeAminoAcid(delta));
    }
}
