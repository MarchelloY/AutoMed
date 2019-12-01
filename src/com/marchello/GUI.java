package com.marchello;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class GUI extends JFrame {
    private JLabel
            surnameLabel = new JLabel("Фамилия"),
            nameLabel = new JLabel("Имя"),
            patronymicLabel = new JLabel("Отчество"),
            phoneLabel = new JLabel("Телефон"),
            streetLabel = new JLabel("Улица"),
            houseLabel = new JLabel("Дом"),
            caseLabel = new JLabel("Корпус"),
            apartmentLabel = new JLabel("Квартира"),
            passportSeriesLabel = new JLabel("Серия"),
            passportNumberLabel = new JLabel("Номер"),
            passportIDLabel = new JLabel("Идент. номер"),
            passportWhenIssuedLabel = new JLabel("Дата выдачи"),
            passportWhoIssuedLabel = new JLabel("Кем выдан"),
            addPatientTitile = new JLabel("Добаление клиента:");

    private JTextField
            surnameInput = new JTextField(),
            nameInput = new JTextField(),
            patronymicInput = new JTextField(),
            streetInput = new JTextField(),
            houseInput = new JTextField(),
            caseInput = new JTextField(),
            apartmentInput = new JTextField(),
            phoneInput = new HintTextField("+375#########", "+375"),
            passportSeriesInput = new JTextField(),
            passportNumberInput = new JTextField(),
            passportIDInput = new JTextField(),
            passportWhenIssuedInput = new HintTextField("DD/MM/YYYY", ""),
            passportWhoIssuedInput = new JTextField();

    private JPanel
            addPatientCommonInfoPanel = new JPanel(),
            addPatientPassportInfoPanel = new JPanel();

    private JButton
            addPatientButton = new JButton("Добавить клиента"),
            clearDataButton = new JButton("Стереть все");

    public GUI() {
        super("Patient Base");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //TITLE
        this.addPatientTitile.setBounds(20,20,350,20);
        this.addPatientTitile.setFont(new Font("Century Gothic", Font.BOLD, 20));
        this.addPatientTitile.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(this.addPatientTitile);

        //THE FIRST BLOCK
        this.addPatientCommonInfoPanel.setBounds(20,60,350,180);
        this.addPatientCommonInfoPanel.setBorder(BorderFactory.createTitledBorder("Введите основную информацию клиента"));
        this.addPatientCommonInfoPanel.setLayout(new FlowLayout());
        this.getContentPane().add(this.addPatientCommonInfoPanel);

        //THE SECOND BLOCK
        this.addPatientPassportInfoPanel.setBounds(20,260,350,130);
        this.addPatientPassportInfoPanel.setBorder(BorderFactory.createTitledBorder("Введите паспортные данные клиента"));
        this.addPatientPassportInfoPanel.setLayout(new FlowLayout());
        this.getContentPane().add(this.addPatientPassportInfoPanel);

        //BUTTON "ADD"
        this.addPatientButton.setBounds(20, 410, 200,35);
        this.getContentPane().add(this.addPatientButton);
        this.addPatientButton.addActionListener(e -> {
        });

        //BUTTON "CLEAR"
        this.clearDataButton.setBounds(220, 410, 150,35);
        this.getContentPane().add(this.clearDataButton);
        this.clearDataButton.addActionListener(e -> {
            surnameInput.setText("");
            nameInput.setText("");
            patronymicInput.setText("");
            phoneInput.setText("+375");
            phoneInput.requestFocus(true);
            streetInput.setText("");
            houseInput.setText("");
            caseInput.setText("");
            apartmentInput.setText("");
            passportIDInput.setText("");
            passportNumberInput.setText("");
            passportSeriesInput.setText("");
            passportWhenIssuedInput.setText("");
            passportWhenIssuedInput.requestFocus(true);
            passportWhoIssuedInput.setText("");
            surnameInput.requestFocus(true);
        });

        //ADD LINES
        addLineInForm(this.surnameLabel, this.surnameInput,100,200,true);
        addLineInForm(this.nameLabel, this.nameInput,100,200, true);
        addLineInForm(this.patronymicLabel, this.patronymicInput,100,200,true);
        addLineInForm(this.phoneLabel, this.phoneInput,100,200,true);
        addLineInForm(this.streetLabel, this.streetInput,100,200,true);
        addLineInForm(this.houseLabel, this.houseInput,37,40,true);
        addLineInForm(this.caseLabel, this.caseInput,57,30,true);
        addLineInForm(this.apartmentLabel, this.apartmentInput,75,40,true);
        addLineInForm(this.passportSeriesLabel, this.passportSeriesInput,55,30,false);
        addLineInForm(this.passportNumberLabel, this.passportNumberInput,55,100,false);
        addLineInForm(this.passportIDLabel, this.passportIDInput,100,200,false);
        addLineInForm(this.passportWhenIssuedLabel, this.passportWhenIssuedInput,100,200,false);
        addLineInForm(this.passportWhoIssuedLabel, this.passportWhoIssuedInput,100,200,false);
    }

    public void addLineInForm(JLabel label, JTextField input, int w1, int w2, boolean flag){
        label.setPreferredSize(new Dimension(w1, 20));
        input.setPreferredSize(new Dimension(w2, 20));
        label.setFont(new Font("Century Gothic", Font.BOLD, 14));
        input.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        if(flag){
            this.addPatientCommonInfoPanel.add(label);
            this.addPatientCommonInfoPanel.add(input);
        } else {
            this.addPatientPassportInfoPanel.add(label);
            this.addPatientPassportInfoPanel.add(input);
        }
    }
}