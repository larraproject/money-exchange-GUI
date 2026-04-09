import javax.swing.*;
import java.awt.*;
import java.awt.Desktop;
import java.net.URI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class moneyExchange extends JFrame {
    private JLabel greeting;
    private JTextField beginSumma;
    private JPanel inputPanel;
    private JLabel input;
    private JLabel choiceCur;
    private JComboBox<String> fromCur;
    private JComboBox<String> inCur;
    private JPanel currenciesPanel;
    private JPanel fromPanel;
    private JPanel inPanel;
    private JPanel outputPanel;
    private JLabel output;
    private JTextField endSumma;
    private JButton convertButton;
    private JPanel linksPanel;
    private JLabel linksTitle;

    public moneyExchange() {
        super("Обменник валют");
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(false);

        greeting = new JLabel("Приветствую Вас в обменнике валют!", SwingConstants.CENTER);
        greeting.setFont(new Font("Times New Roman", Font.ITALIC, 18));
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        beginSumma = new JTextField("");
        beginSumma.setHorizontalAlignment(JTextField.CENTER);
        beginSumma.setFont(new Font("Times New Roman", Font.ITALIC, 16));
        beginSumma.setMaximumSize(new Dimension(400, 30));
        beginSumma.setAlignmentX(Component.CENTER_ALIGNMENT);
        beginSumma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Ввод суммы и выбор валют"));
        inputPanel.setMaximumSize(new Dimension(600, 600));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        input = new JLabel("Пожалуйста, введите сумму:", SwingConstants.CENTER);
        input.setFont(new Font("Times New Roman", Font.BOLD, 16));
        input.setAlignmentX(Component.CENTER_ALIGNMENT);

        choiceCur = new JLabel("Выберите из какой валюты в какую валюту конвертировать сумму", SwingConstants.CENTER);
        choiceCur.setFont(new Font("Times New Roman", Font.BOLD, 14));
        choiceCur.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] currencies = {"RUB", "USD", "EUR", "KOLMCOIN"};
        fromCur = new JComboBox<>(currencies);
        fromCur.setMaximumSize(new Dimension(120, 30));
        fromCur.setAlignmentX(Component.CENTER_ALIGNMENT);
        fromCur.setFont(new Font("Times New Roman", Font.BOLD, 16));

        inCur = new JComboBox<>(currencies);
        inCur.setMaximumSize(new Dimension(120, 30));
        inCur.setAlignmentX(Component.CENTER_ALIGNMENT);
        inCur.setFont(new Font("Times New Roman", Font.BOLD, 16));

        fromPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        fromPanel.add(new JLabel("Из: "));
        fromPanel.add(fromCur);

        inPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        inPanel.add(new JLabel("В: "));
        inPanel.add(inCur);

        currenciesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        currenciesPanel.add(fromPanel);
        currenciesPanel.add(inPanel);

        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(input);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(beginSumma);
        inputPanel.add(Box.createVerticalStrut(15));
        inputPanel.add(choiceCur);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(currenciesPanel);
        inputPanel.add(Box.createVerticalStrut(10));

        outputPanel = new JPanel();
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBorder(BorderFactory.createTitledBorder("Результат конвертации"));
        outputPanel.setMaximumSize(new Dimension(600, 600));
        outputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        output = new JLabel("Ваша сумма в другой валюте: ", SwingConstants.CENTER);
        output.setFont(new Font("Times New Roman", Font.BOLD, 16));
        output.setAlignmentX(Component.CENTER_ALIGNMENT);

        endSumma = new JTextField("");
        endSumma.setHorizontalAlignment(JTextField.CENTER);
        endSumma.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        endSumma.setMaximumSize(new Dimension(400, 30));
        endSumma.setAlignmentX(Component.CENTER_ALIGNMENT);

        convertButton = new JButton("Конвертировать");
        convertButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        convertButton.setMaximumSize(new Dimension(200, 40));
        convertButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        outputPanel.add(Box.createVerticalStrut(30));
        outputPanel.add(output);
        outputPanel.add(Box.createVerticalStrut(30));
        outputPanel.add(endSumma);
        outputPanel.add(Box.createVerticalStrut(30));
        outputPanel.add(convertButton);
        outputPanel.add(Box.createVerticalStrut(30));

        linksPanel = new JPanel();
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
        linksPanel.setBorder(BorderFactory.createTitledBorder("Полезные ссылки"));
        linksPanel.setMaximumSize(new Dimension(500, 200));
        linksPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        linksPanel.setBackground(new Color(250, 250, 250));

        linksTitle = new JLabel("Обязательно посетите данные сайты!", SwingConstants.CENTER);
        linksTitle.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        linksTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        linksPanel.add(Box.createVerticalStrut(10));
        linksPanel.add(linksTitle);
        linksPanel.add(Box.createVerticalStrut(10));

        JLabel link1 = createClickableLink("ЦБ РФ (официальные курсы)",
                "https://www.cbr.ru/currency_base/daily/");
        JLabel link2 = createClickableLink("Лучшие биточки",
                "https://t.me/prodwooken");
        JLabel link3 = createClickableLink("Рацион питания для набора массы",
                "https://larraproject.github.io/site_massanabor/");
        JLabel link4 = createClickableLink("GitHub разработчика",
                "https://github.com/larraproject");

        linksPanel.add(link1);
        linksPanel.add(Box.createVerticalStrut(8));
        linksPanel.add(link2);
        linksPanel.add(Box.createVerticalStrut(8));
        linksPanel.add(link3);
        linksPanel.add(Box.createVerticalStrut(8));
        linksPanel.add(link4);
        linksPanel.add(Box.createVerticalStrut(8));

        this.add(Box.createVerticalStrut(30));
        this.add(greeting);
        this.add(Box.createVerticalStrut(30));
        this.add(inputPanel);
        this.add(Box.createVerticalStrut(30));
        this.add(outputPanel);
        this.add(linksPanel);
        this.add(Box.createVerticalGlue());

        this.setVisible(true);
    }

    private JLabel createClickableLink(String text, String url) {
        JLabel link = new JLabel("<HTML>" + text + "</HTML>");
        link.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        link.setForeground(new Color(0, 0, 205));
        link.setCursor(new Cursor(Cursor.HAND_CURSOR));
        link.setAlignmentX(Component.CENTER_ALIGNMENT);
        link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(moneyExchange.this,
                            "Не удалось открыть ссылку");
                }
            }
        });
        return link;
    }

    private void convertCurrency() {
        try {
            String inputText = beginSumma.getText().trim();
            if (inputText.isEmpty()) {
                endSumma.setText("Ошибка! Введите сумму");
                return;
            }
            double inputsumma = Double.parseDouble(inputText);
            if (inputsumma < 0) {
                endSumma.setText("Ошибка! Сумма отрицательна");
                return;
            }
            String fromcurrency = (String) fromCur.getSelectedItem();
            String incurrency = (String) inCur.getSelectedItem();
            double RUBtoUSD = 0.012465;
            double RUBtoEUR = 0.010871;
            double USDtoRUB = 80.23;
            double USDtoEUR = 0.8739;
            double EURtoRUB = 91.98;
            double EURtoUSD = 1.14;
            double RUBtoKOLMCOIN = 0.01923;
            double USDtoKOLMCOIN = 0.01493;
            double EURtoKOLMCOIN = 0.02381;
            double KOLMCOINtoRUB = 52;
            double KOLMCOINtoUSD = 67;
            double KOLMCOINtoEUR = 42;

            String result = "";

            if (fromcurrency.equals("RUB")) {
                if (incurrency.equals("USD")) {
                    result = String.format("%.4f USD", inputsumma * RUBtoUSD);
                } else if (incurrency.equals("EUR")) {
                    result = String.format("%.4f EUR", inputsumma * RUBtoEUR);
                } else if (incurrency.equals("KOLMCOIN")) {
                    result = String.format("%.4f KOLMCOIN", inputsumma * RUBtoKOLMCOIN);
                } else {
                    result = "Невозможно выполнить действие!";
                }
            } else if (fromcurrency.equals("USD")) {
                if (incurrency.equals("RUB")) {
                    result = String.format("%.4f RUB", inputsumma * USDtoRUB);
                } else if (incurrency.equals("EUR")) {
                    result = String.format("%.4f EUR", inputsumma * USDtoEUR);
                } else if (incurrency.equals("KOLMCOIN")) {
                    result = String.format("%.4f KOLMCOIN", inputsumma * USDtoKOLMCOIN);
                } else {
                    result = "Невозможно выполнить действие!";
                }
            } else if (fromcurrency.equals("EUR")) {
                if (incurrency.equals("RUB")) {
                    result = String.format("%.4f RUB", inputsumma * EURtoRUB);
                } else if (incurrency.equals("USD")) {
                    result = String.format("%.4f USD", inputsumma * EURtoUSD);
                } else if (incurrency.equals("KOLMCOIN")) {
                    result = String.format("%.4f KOLMCOIN", inputsumma * EURtoKOLMCOIN);
                } else {
                    result = "Невозможно выполнить действие!";
                }
            } else if (fromcurrency.equals("KOLMCOIN")) {
                if (incurrency.equals("RUB")) {
                    result = String.format("%.4f RUB", inputsumma * KOLMCOINtoRUB);
                } else if (incurrency.equals("USD")) {
                    result = String.format("%.4f USD", inputsumma * KOLMCOINtoUSD);
                } else if (incurrency.equals("EUR")) {
                    result = String.format("%.4f EUR", inputsumma * KOLMCOINtoEUR);
                } else {
                    result = "Невозможно выполнить действие!";
                }
            }
            endSumma.setText(result);
        } catch (NumberFormatException e) {
            endSumma.setText("Ошибка! Введите корректную сумму");
        }
    }
    public static void main(String[] args) {
        new moneyExchange();
    }
}