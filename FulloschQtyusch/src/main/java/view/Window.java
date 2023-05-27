package view;

import control.Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import model.Game;
import model.Virologist;
import model.codes.GeneticCode;
import model.equipments.Equipment;
import model.map.Field;
import model.map.Material;

public class Window extends Observer {

	/**
	 * Az utolsó üzenet tartalma
	 */
	String msgText;

	private JProgressBar aminoBar;
	private JProgressBar nucleoBar;

	private JLabel aminoLabel;
	private JLabel nucleoLabel;
	private JLabel turnCounter;

	private JLabel currentField;
	/**
	 * Az üzenetbuborék
	 */
	private JLabel actionBubble;
	/**
	 * Az üzenetbuborék szövege
	 */
	private JTextArea actionBubbleText;
	private ArrayList<JButton> equipmentButtons;

	private JLabel backGround;
	private final JFrame frame;

	private final Controller controller;
	private final Game game;

	public Window(Controller controller, Game game) {

		this.controller = controller;
		this.game = game;

		frame = new JFrame("Vilagtalan virologusok vilaga");
		JMenuBar mainMenu = new JMenuBar();
		JMenu actions = new JMenu("Actions");
		mainMenu.add(actions);
		frame.setJMenuBar(mainMenu);

		JMenu attack = createAttackMenu();
		actions.add(attack);

		JMenu move = createMoveMenu();
		actions.add(move);

		JMenuItem drop = createDropMenu();
		actions.add(drop);

		JMenu lootAminoFrom = createLootAminoMenu();
		actions.add(lootAminoFrom);

		JMenu lootNucleoFrom = createLootNucleoFrom();
		actions.add(lootNucleoFrom);

		JMenu lootEquipmentFrom = createLootEquipmentMenu();
		actions.add(lootEquipmentFrom);

		JMenuItem learn = new JMenuItem("learn");
		actions.add(learn);
		learn.addActionListener(e -> controller.learn());

		JMenuItem equip = new JMenuItem("equip");
		actions.add(equip);
		equip.addActionListener(e -> controller.equip());

		JMenu inject = createInjectMenu();
		actions.add(inject);

		JMenuItem endTurn = new JMenuItem("endTurn");
		actions.add(endTurn);
		endTurn.addActionListener(e -> controller.endTurn());

		createRandomizationMenu(actions);

		drawInterface();

		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		update();
	}

	private JMenu createMoveMenu() {
		JMenu move = new JMenu("move");
		move.addMenuListener(new ViewMenuListener(() -> {
			move.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Field field : v.getField().getNeighbours()) {
				JMenuItem item = new JMenuItem(field.getName());
				item.addActionListener(e -> controller.move(field));
				move.add(item);
			}
		}));
		return move;
	}
	private JMenuItem createDropMenu(){
		JMenuItem drop = new JMenuItem("drop");
		drop.addActionListener(e -> controller.drop());
		return drop;
	}
	private JMenu createInjectMenu() {
		JMenu inject = new JMenu("inject");
		inject.addMenuListener(new ViewMenuListener(() -> {
			inject.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Virologist vir : v.getField().getVirologists()) {
				JMenu virMenu = new JMenu(vir.getName());
				virMenu.addMenuListener(new ViewMenuListener(() -> {
					virMenu.removeAll();
					for (GeneticCode code : v.getGeneticCodes()) {
						JMenuItem codeItem = new JMenuItem(code.getName());
						codeItem.addActionListener(e -> controller.inject(vir, code));
						virMenu.add(codeItem);
					}
				}));
				inject.add(virMenu);
			}
		}));
		return inject;
	}
	private JMenu createLootEquipmentMenu(){
		JMenu lootEquipmentFrom = new JMenu("lootEquipmentFrom");
		lootEquipmentFrom.addMenuListener(new ViewMenuListener(() -> {
			lootEquipmentFrom.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Virologist vir : v.getField().getVirologists()) {
				if (!vir.equals(v)) {
					JMenuItem item = new JMenuItem(vir.getName());
					item.addActionListener(e -> controller.lootEquipmentFrom(vir));
					lootEquipmentFrom.add(item);
				}
			}
		}));
		return lootEquipmentFrom;
	}
	private JMenu createLootAminoMenu(){
		JMenu lootAminoFrom = new JMenu("lootAminoFrom");
		lootAminoFrom.addMenuListener(new ViewMenuListener(() -> {
			lootAminoFrom.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Virologist vir : v.getField().getVirologists()) {
				if (!vir.equals(v)) {
					JMenuItem item = new JMenuItem(vir.getName());
					item.addActionListener(e -> controller.lootAminoFrom(vir));
					lootAminoFrom.add(item);
				}
			}
		}));
		return lootAminoFrom;
	}

	private JMenu createLootNucleoFrom(){
		JMenu lootNucleoFrom = new JMenu("lootNucleoFrom");
		lootNucleoFrom.addMenuListener(new ViewMenuListener(() -> {
			lootNucleoFrom.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Virologist vir : v.getField().getVirologists()) {
				if (!vir.equals(v)) {
					JMenuItem item = new JMenuItem(vir.getName());
					item.addActionListener(e -> controller.lootNucleoFrom(vir));
					lootNucleoFrom.add(item);
				}
			}
		}));
		return lootNucleoFrom;
	}
	private JMenu createAttackMenu() {
		JMenu attack = new JMenu("attack");
		attack.addMenuListener(new ViewMenuListener(() -> {
			attack.removeAll();
			Virologist v = game.getCurrentPlayer();
			for (Virologist vir : v.getField().getVirologists()) {
				JMenuItem item = new JMenuItem(vir.getName());
				item.addActionListener(e -> controller.attack(vir));
				attack.add(item);
			}
		}));
		return attack;
	}
	private void createRandomizationMenu(JMenu actions){
		JMenuItem randSwitch = game.randOn ? new JMenuItem("randomization factor: on")
				: new JMenuItem("randomization factor: off");
		actions.add(randSwitch);
		randSwitch.addActionListener(e -> {
			game.randOn = !game.randOn;
			String title = game.randOn ? "randomization factor: on" : "randomization factor: off";
			randSwitch.setText(title);
			actions.remove(6);
			actions.add(initCollectMenuItem(controller, game), 6);
		});
	}

	private static JMenuItem initCollectMenuItem(Controller controller, Game game) {
		JMenuItem collect;
		if (!game.randOn) {
			JMenu collectMaterial = new JMenu("collect");
			JMenuItem amino = new JMenuItem("Amino acid");
			JMenuItem nucleo = new JMenuItem("Nucleotide");
			amino.addActionListener(e -> controller.collect(Material.AMINO_ACID));
			nucleo.addActionListener(e -> controller.collect(Material.NUCLEOTIDE));
			collectMaterial.add(amino);
			collectMaterial.add(nucleo);
			return collectMaterial;
		} else {
			collect = new JMenuItem("collect");
			collect.addActionListener(e -> controller.collect(Material.GENERIC));
		}
		return collect;
	}
	private void updateActionCounter(Virologist player){
		turnCounter.setText(player.getActionCount() + " / 3");

		currentField.setText("Current field: " + player.getField().getName());

		currentField.setText("Current field: " + player.getField().getName());
	}
	private void updateMaterials(Virologist player){
		nucleoBar.setValue(player.getNucleotide());
		nucleoBar.setMaximum(player.getMaterialLimit());
		nucleoLabel.setText("Nucleotide: " + player.getNucleotide());
		aminoBar.setValue(player.getAminoAcid());
		aminoBar.setMaximum(player.getMaterialLimit());
		aminoLabel.setText("Amino acid: " + player.getAminoAcid());
	}
	private void updateEquipments(Virologist player){
		List<Equipment> equipment = player.getEquipments();
		Image equipmentSlotIcon = null;
		for (int i = 0; i < 3; i++) {
			if (equipment.size() > i) {
				Drawable drawableEquipment = (Drawable) equipment.get(i);
				try {
					equipmentSlotIcon = ImageIO.read(Objects.requireNonNull(
							getClass().getResourceAsStream(drawableEquipment.getTexture())));
					equipmentSlotIcon = equipmentSlotIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				} catch (IOException ignored) {
				}
			} else {
				try {
					equipmentSlotIcon = ImageIO.read(
							Objects.requireNonNull(getClass().getResourceAsStream("/textures/itemSlot.png")));
					equipmentSlotIcon = equipmentSlotIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				} catch (IOException ignored) {
				}
			}
			assert equipmentSlotIcon != null;
			equipmentButtons.get(i).setIcon(new ImageIcon(equipmentSlotIcon));
		}
	}
	private void updateMessage(Virologist player){
		if (!controller.getActionMessage().equals("")) {
			msgText = player.getName() + ": " + controller.getActionMessage();
		}

		if (msgText.equals("")) {
			actionBubble.setVisible(false);
			actionBubbleText.setVisible(false);
		} else {

			actionBubbleText.setText(msgText);
			actionBubble.setVisible(true);
			actionBubbleText.setVisible(true);
		}
	}
	private void updateBackground(Virologist player){
		Drawable drawableField = (Drawable) player.getField();
		Image backGroundIMG;
		try {
			backGroundIMG = ImageIO.read(
					Objects.requireNonNull(getClass().getResourceAsStream(drawableField.getTexture())));
			backGroundIMG = backGroundIMG.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
			backGround.setIcon(new ImageIcon(backGroundIMG));
			backGround.setBounds(0, 0, 600, 600);
		} catch (IOException ignored) {
		}
	}
	@Override
	public void update() {
		Virologist player = game.getCurrentPlayer();

		updateActionCounter(player);

		updateMaterials(player);

		updateEquipments(player);

		updateMessage(player);

		updateBackground(player);
	}
	public JButton setEndTurnButton(){
		Image endButtonIcon = null;
		try {
			endButtonIcon = ImageIO.read(
					Objects.requireNonNull(getClass().getResourceAsStream("/textures/endButton.png")));
			endButtonIcon = endButtonIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert endButtonIcon != null;
		JButton endButton = new JButton(new ImageIcon(endButtonIcon));
		endButton.addActionListener(e -> controller.endTurn());
		endButton.setBorder(null);
		endButton.setContentAreaFilled(false);
		endButton.setBorderPainted(false);
		endButton.setBackground(Color.BLACK);
		endButton.setOpaque(false);
		endButton.setBounds(480, 450, 70, 70);

		return endButton;
	}
	private JProgressBar createNucleoProgressBar(){
		JProgressBar nucleoBar = new JProgressBar();
		nucleoBar.setBounds(215, 460, 170, 25);
		nucleoBar.setMaximum(20);
		nucleoBar.setMinimum(0);
		return nucleoBar;
	}
	private JLabel createNucleoLabel(){
		JLabel nucleoLabel = new JLabel("min");
		nucleoLabel.setBounds(215, 465, 170, 15);
		nucleoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nucleoLabel.setFont(new Font("sans-serif", Font.BOLD, 13));
		return nucleoLabel;
	}
	private JProgressBar createAminoProgressBar(){
		JProgressBar aminoBar = new JProgressBar();
		aminoBar.setBounds(215, 490, 170, 25);
		aminoBar.setMaximum(20);
		aminoBar.setMinimum(0);
		return aminoBar;
	}
	private JLabel createAminoLabel(){
		JLabel aminoLabel = new JLabel("min");
		aminoLabel.setBounds(215, 495, 170, 15);
		aminoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aminoLabel.setFont(new Font("sans-serif", Font.BOLD, 13));
		return aminoLabel;
	}
	private void setEquipmentButtons(){
		Image equipmentSlotIcon = null;
		try {
			equipmentSlotIcon = ImageIO.read(
					Objects.requireNonNull(getClass().getResourceAsStream("/textures/itemSlot.png")));
			equipmentSlotIcon = equipmentSlotIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		equipmentButtons = new ArrayList<>(3);
		int y = 340;
		for (int i = 0; i < 3; i++) {
			JButton eq;
			assert equipmentSlotIcon != null;
			eq = new JButton(new ImageIcon(equipmentSlotIcon));
			eq.setBounds(15, y, 50, 50);
			eq.setFocusPainted(false);
			eq.setBorderPainted(false);
			eq.setContentAreaFilled(false);
			eq.setBorder(null);
			equipmentButtons.add(eq);
			y += 60;
		}
	}
	private JLabel createActionCounter(){
		JLabel actionCounter = new JLabel("3 / 3");
		actionCounter.setFont(new Font("sans-serif", Font.BOLD, 48));
		actionCounter.setForeground(Color.white);
		actionCounter.setBounds(480, 25, 160, 50);
		return actionCounter;
	}
	private JLabel createCurrentFieldLabel(){
		JLabel currentField = new JLabel();
		currentField.setFont(new Font("sans-serif", Font.BOLD, 20));
		currentField.setForeground(Color.white);
		currentField.setBounds(225, 425, 200, 25);
		return currentField;
	}
	private void setActionBubble(){
		Image actionBubbleIMG;
		ImageIcon actionBubbleIcon;
		try {
			actionBubbleIMG = ImageIO.read(
					Objects.requireNonNull(getClass().getResourceAsStream("/textures/bubble.png")));
			actionBubbleIMG = actionBubbleIMG.getScaledInstance(190, 88, Image.SCALE_SMOOTH);
			actionBubbleIcon = new ImageIcon(actionBubbleIMG);
			actionBubble = new JLabel(actionBubbleIcon);
			actionBubble.setBounds(280, 225, 190, 88);
		} catch (IOException e) {
			e.printStackTrace();
		}

		msgText = "kiskúgya kunya gúgyuszka kiskufya\n" +
				"kiskugya kútya sulya lislyuta\n" +
				"kizskugka kutja kútja kiskuhya\n" +
				"gizsgugya    kuta    kutyna    kiskuxya\n" +
				"gizskutya    kislyutya    kutnya    kiskuya\n" +
				"kiskutius    gidzsigegizsgutya    kitsikugya    kitsigugya\n" +
				"hutya        kúgyugya    lizya      kűgyja\n" +
				"kutsus         kogya    gidzsgudja    nyuta\n" +
				"gizsgúgya    kutttya    qgya        pimbull \n" +
				"gűtya     kizsgutya    kutgya       kugyja\n" +
				"kugyuzs    qúhggyah    qkútgyikah    gútjya\n" +
				"guggya    gizsgyugya    kúdtja    gizskugya\n" +

				"kuhya    kúja    kudgya     tutya\n" +
				"gúgyah    kugyha    qutja    kislutya\n" +
				"kutsa    outya    kuyua    lizya\n" +
				"lutyw    litya    kitya    lutxa\n" +
				"kuxta    gidzsigegugyus    kuzxa    kikúgyka\n" +

				"gútyja    kutxa    kigya    gugyuska\n" +
				"gisguya      kuxgy    kurya    gogya\n" +
				"kisgugytkya    jutya    kufya    gugklya\n" +
				"kiskulya       gizsgugyuzsga     kucsa    kiskytya\n" +
				"kismulya    guty    gizsgutyi    kiskhtya\n" +
				"kuva         vau    kizskugya    kiskjtya\n" +
				"qutya     kúgyka    kiskutja    kugyus\n" +
				"qugya      kuty    kulyuska    gutus \n" +
				"gisgúgya    lugya    kuxa    tugya\n" +
				"qúgya      gűgya    gúgyika    kutga\n" +

				"kuja    rót valter (rottweiler)    gi gutga    kisgutya\n" +

				"kulya    kugta    kiekutya    kutuska\n" +
				"kucus    mutya    kiwkutya    nyutya\n" +
				"gizsigutygyja      gizs gugya    kiqkutya    kis kuta\n" +
				"kukia     gízsgúgya    kikugya    kiskutyu\n" +
				"gyutya        qtya    kikutya    kutyuli\n" +
				"gutya     gigygugya    kiskurya     mutyuli\n" +
				"zuka       qkutya    kizskutga    kizskuja\n" +
				"guta    qtyja     gisgutgya    kúgyús\n" +
				"zutyi      guka    gizsgutya    kúgya\n" +
				"könya      kuttya    tutus    kumgya\n" +
				"kölya        putya    tuta    lutya\n" +
				"bidbulgugya    giszkutya    tyutyu    kúlya\n" +

				"qtja     kizsgútya    tyutyus    gugyuzsga\n" +
				"köjök    kis kugya    kizsgugya    kuthya    gútyja\n" +
				"gugyus    gugyusga    kutyhus    gudgya\n" +
				"gizsgutga    kuya    gizsgutya    gizsgutya\n" +
				"bidbugugya    kisgugya    gutyna    gizsgutyus\n" +
				"kizskutja    kucuka    kismutya    ulya\n" +
				"gudja      kuszus    kutyulimutyuli    qty\n" +
				"kuzya     kutyha     kugdlya           gútyja\n" +
				"kissqutya    kűgyka    kudglya    gútygya\n" +
				"kissqtya    qügya    dutya    kunyus \n" +
				"kiss kutya    kis kutyuss    kuyga    kúnya\n" +
				"kiskógya          kuggya     gi guya    ksigugyq\n" +
				"kitzsikutynyuzska    kucs    kuryz    gizsgyutya\n" +
				"kislutyuy         giskunya    gizs kugyúgya    kisgucsa\n" +
				"kisgógya      giskugyulimugyuli    gyutyulimugyuli    kurgya\n" +
				"gizs gudja    gisgugya    gisguya    kurtya\n" +
				"guttya         qutgya     quttya     kis lutyuj \n" +
				"glutya       gulytjya    kisluytuj     discsucsa\n" +
				"kiskzóuyta     kutjda    katya        lutyiluty\n" +
				"kutyika     kis kutsus    kútxa     kutxuzs\n" +
				"kuyly    kuyla    kiskunyus        gugyja\n" +
				"kúfka    kúdka    kissgugyuska    kisskugyus\n" +
				"kütya    gidzsigutya    gunyus     kisgunyus\n" +
				"qs qtya    gugyuli-mugyuli          kizskzgya    kútdja\n" +
				"krudja       krugyja    gizsguggya    kiskukia\n" +
				"kutyulu         kislutuy    kisgugyja    gutyja\n" +
				"kizs zsutyila    gúgyulka-mugyulka        kizsgudzsuka     kisgudzsus\n" +
				"kigy gyuka      kúqggyuzska    kusugyulj    qizs qtya\n" +
				"kufa        gúdzsus-mudzsus    kizs zslutya    qúdzsa\n" +
				"qugyka     gudzsuska    qkútgya    kutguzska\n" +
				"kiskugy a     dicsakbuksi    qugyulimugyuli    tyutya\n" +
				"kisgutju         kisgyúgya    kigyugya         kisgugy";

		actionBubbleText = new JTextArea("Hello vak virologus!\n" + msgText);
		actionBubbleText.setBackground(Color.white);
		actionBubbleText.setBorder(BorderFactory.createEmptyBorder());
		actionBubbleText.setBounds(290, 235, 170, 50);
		actionBubbleText.setEditable(false);
		actionBubbleText.setFont(new Font("sans-serif", Font.BOLD, 12));
		actionBubbleText.setColumns(28);
		actionBubbleText.setLineWrap(true);
	}
	private void setBackGround(){
		Image backGroundIMG;
		ImageIcon backGroundIcon;
		backGround = new JLabel();
		try {
			backGroundIMG = ImageIO.read(
					Objects.requireNonNull(getClass().getResourceAsStream("/textures/Field.png")));
			backGroundIMG = backGroundIMG.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
			backGroundIcon = new ImageIcon(backGroundIMG);
			backGround = new JLabel(backGroundIcon);
			backGround.setBounds(0, 0, 600, 600);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void drawInterface() {

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 600, 600);

		JButton endButton = setEndTurnButton();

		nucleoBar = createNucleoProgressBar();
		nucleoLabel = createNucleoLabel();

		aminoBar = createAminoProgressBar();
		aminoLabel = createAminoLabel();

		setEquipmentButtons();

		turnCounter = createActionCounter();

		currentField = createCurrentFieldLabel();

		setActionBubble();

		setBackGround();

		// ELRENDEZÉS BEÁLLÍTÁSA

		layeredPane.add(backGround, Integer.valueOf(0));
		layeredPane.add(actionBubble, Integer.valueOf(1));
		layeredPane.add(endButton, Integer.valueOf(1));
		layeredPane.add(aminoBar, Integer.valueOf(1));
		layeredPane.add(nucleoBar, Integer.valueOf(1));
		layeredPane.add(equipmentButtons.get(0), Integer.valueOf(1));
		layeredPane.add(equipmentButtons.get(1), Integer.valueOf(1));
		layeredPane.add(equipmentButtons.get(2), Integer.valueOf(1));
		layeredPane.add(turnCounter, Integer.valueOf(1));
		layeredPane.add(actionBubbleText, Integer.valueOf(2));
		layeredPane.add(nucleoLabel, Integer.valueOf(2));
		layeredPane.add(aminoLabel, Integer.valueOf(2));
		layeredPane.add(currentField, Integer.valueOf(2));
		frame.add(layeredPane);
	}

}
