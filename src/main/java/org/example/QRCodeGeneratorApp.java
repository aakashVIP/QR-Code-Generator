package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGeneratorApp extends JFrame {

    private static final int MAX_CHARACTERS = 200;
    private static final int QR_SIZE = 350;

    private final JTextArea textArea = new JTextArea(5, 30);
    private final JLabel characterCountLabel = new JLabel("0 / 200");
    private final JLabel qrLabel = new JLabel("", SwingConstants.CENTER);

    public QRCodeGeneratorApp() {
        setTitle("QR Code Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(
                new CharacterLimitFilter(MAX_CHARACTERS)
        );

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCharacterCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCharacterCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCharacterCount();
            }
        });

        JButton generateButton = new JButton("Generate QR Code");
        generateButton.addActionListener(e -> generateQRCode());

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Text (Maximum 200 Characters)"));
        inputPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        inputPanel.add(characterCountLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateButton);

        qrLabel.setPreferredSize(new Dimension(QR_SIZE, QR_SIZE));
        qrLabel.setBorder(BorderFactory.createTitledBorder("QR Code"));

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(qrLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateCharacterCount() {
        int count = textArea.getText().length();
        characterCountLabel.setText(count + " / " + MAX_CHARACTERS);
    }

    private void generateQRCode() {
        String text = textArea.getText().trim();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter text before generating a QR code.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            BufferedImage qrImage = createQRCodeImage(text, QR_SIZE, QR_SIZE);
            qrLabel.setIcon(new ImageIcon(qrImage));
        } catch (WriterException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to generate QR code: " + ex.getMessage(),
                    "QR Generation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private BufferedImage createQRCodeImage(String text, int width, int height)
            throws WriterException {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix matrix = new QRCodeWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                width,
                height,
                hints
        );

        BufferedImage image = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(
                        x,
                        y,
                        matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
                );
            }
        }

        return image;
    }

    static class CharacterLimitFilter extends DocumentFilter {
        private final int limit;

        CharacterLimitFilter(int limit) {
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length,
                            String text, AttributeSet attrs) throws BadLocationException {

            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset)
                    + (text == null ? "" : text)
                    + currentText.substring(offset + length);

            if (newText.length() <= limit) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QRCodeGeneratorApp::new);
    }
}