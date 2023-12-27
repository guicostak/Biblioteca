package main.view;

import main.service.item.ItemServiceImpl;
import main.service.usuario.UsuarioServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterfaceGrafica extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private final ItemServiceImpl itemService = ItemServiceImpl.getInstance();

    private final UsuarioServiceImpl usuarioService = UsuarioServiceImpl.getInstance();

    public void mainInterface() {

        setTitle("Sistema de Biblioteca - PUC MINAS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton userButton = new JButton("Cadastrar Usuário");
        JButton itemButton = new JButton("Cadastrar Item");

        userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(userButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(itemButton);

        final var gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;

        final var userPanel = createUserPanel();
        final var itemPanel = createItemPanel();

        mainPanel.add(buttonPanel, "Initial");
        mainPanel.add(userPanel, "User");
        mainPanel.add(itemPanel, "Item");

        userButton.addActionListener(e -> cardLayout.show(mainPanel, "User"));
        itemButton.addActionListener(e -> cardLayout.show(mainPanel, "Item"));

        add(mainPanel);

        setVisible(true);
    }

    private JPanel createItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // Layout em grade

        JComboBox<String> tipoComboBox = new JComboBox<>(new String[]{"CD", "DVD", "LIVRO", "REVISTA", "TESE"});
        JTextField autorField = new JTextField();
        JTextField tituloField = new JTextField();
        JTextField categoriaField = new JTextField();
        JSpinner anoSpinner = new JSpinner(new SpinnerNumberModel(2020, 1900, 2100, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(anoSpinner, "#");
        final var format = editor.getFormat();
        if (format != null) {
            format.setGroupingUsed(false);
        }
        anoSpinner.setEditor(editor);
        JSpinner quantidadeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton cadastrarItemButton = new JButton("Cadastrar Item");

        panel.add(new JLabel("Tipo:"));
        panel.add(tipoComboBox);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaField);
        panel.add(new JLabel("Ano:"));
        panel.add(anoSpinner);
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidadeSpinner);
        panel.add(cadastrarItemButton);

        try {
            cadastrarItemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ano = ((Number) anoSpinner.getValue()).intValue();
                    int quantidadeExemplares = ((Number) quantidadeSpinner.getValue()).intValue();
                    String tipo = (String) tipoComboBox.getSelectedItem();
                    String autor = autorField.getText();
                    String titulo = tituloField.getText();
                    String categoria = categoriaField.getText();

                    itemService.cadastrarItem(Objects.requireNonNull(tipo), autor, titulo, ano, categoria, quantidadeExemplares);

                    JOptionPane.showMessageDialog(InterfaceGrafica.this, "Item cadastrado com sucesso!",
                            "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);

                    cardLayout.show(mainPanel, "Initial");

                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro ao cadastrar novo item: "
                    + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        JButton backButton = new JButton("Voltar");
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Initial");
            }
        });

        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        JTextField nomeField = new JTextField();
        JTextField cursoField = new JTextField();
        JTextField topicosField = new JTextField();
        JButton cadastrarUsuarioButton = new JButton("Cadastrar Usuário");

        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Curso:"));
        panel.add(cursoField);
        panel.add(new JLabel("Tópicos Relacionados:"));
        panel.add(topicosField);
        panel.add(cadastrarUsuarioButton);

        JButton backButton = new JButton("Voltar");
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Initial");
            }
        });

        try {
            cadastrarUsuarioButton.addActionListener(e -> {
                usuarioService.cadastrarUsuarios(nomeField.getText(), cursoField.getText(), topicosField.getText());
                JOptionPane.showMessageDialog(InterfaceGrafica.this, "Usuário cadastrado com sucesso!",
                        "Cadastro Realizado", JOptionPane.INFORMATION_MESSAGE);

                cardLayout.show(mainPanel, "Initial");

            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(InterfaceGrafica.this, "Erro ao cadastrar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return panel;
    }
}
