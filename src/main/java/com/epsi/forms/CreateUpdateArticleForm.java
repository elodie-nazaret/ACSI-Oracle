package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.ArticleDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class CreateUpdateArticleForm extends JFrame implements ActionListener {
    private JPanel root;
    private JPanel head;
    private JPanel body;
    private JPanel footer;
    private JLabel titleLabel;
    private JPanel imagePanel;
    private JPanel detailsPanel;
    private JButton validateButton;
    private JPanel referencePanel;
    private JPanel designationPanel;
    private JPanel pricePanel;
    private JPanel descriptionPanel;
    private JTextField referenceText;
    private JLabel referenceLabel;
    private JTextField designationText;
    private JTextField priceText;
    private JLabel designationLabel;
    private JLabel priceLabel;
    private JTextField descriptionText;
    private JLabel descriptionLabel;
    private JButton cancelButton;
    private JButton getImageButton;

    private Article article;

    private byte[] image;

    public CreateUpdateArticleForm() {
        this.init();

        this.article = null;

        setTitle("Nouvel article");
    }

    public CreateUpdateArticleForm(Article article) {
        this.init();

        this.article = article;
        this.referenceText.setText(this.article.getReference());
        this.designationText.setText(this.article.getDesignation());
        this.descriptionText.setText(this.article.getDescription());
        this.priceText.setText(String.valueOf(this.article.getPrice()));

        this.setArticleIcon(new ImageIcon(this.article.getImage()));

        setTitle("Editer " + this.article.getDesignation());
    }

    private void init() {
        setContentPane(root);
        pack();
        setResizable(false);
        setVisible(true);

        getImageButton.addActionListener(this);
        validateButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        ArticleDAO articleDAO = new ArticleDAO();

        if (e.getSource() == this.getImageButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage myPicture = ImageIO.read(fileChooser.getSelectedFile());
                    Image image = myPicture.getScaledInstance(-1, 100, -1);
                    this.setArticleIcon(new ImageIcon(image));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == this.cancelButton) {
            this.dispose();

        } else if (e.getSource() == this.validateButton) {
            if (this.areFieldsValid()) {
                if (this.article == null) {
                    Article article = new Article();

                    this.article.setDesignation(this.designationText.getText());
                    this.article.setDescription(this.descriptionText.getText());
                    this.article.setReference(this.referenceText.getText());
                    this.article.setPrice(Float.valueOf(this.priceText.getText()));
                    this.article.setIsVisible(true);
                    this.article.setCreatedAt(new Date());
                    this.article.setUpdatedAt(new Date());
                    this.article.setImage(this.getByteArrayFromImageIcon());

                    articleDAO.addArticle(article);
                    new ArticleForm(article);
                    this.dispose();

                } else {
                    this.article.setReference(this.referenceText.getText());
                    this.article.setDesignation(this.designationText.getText());
                    this.article.setDescription(this.descriptionText.getText());
                    this.article.setPrice(Float.valueOf(this.priceText.getText()));
                    this.article.setUpdatedAt(new Date());
                    this.article.setImage(this.getByteArrayFromImageIcon());

                    articleDAO.updateArticle(this.article);
                    this.dispose();
                }
            }
        }
    }

    private boolean areFieldsValid() {
        boolean valid = true;

        if (referenceText.getText().length() == 0) {
            referenceLabel.setForeground(Color.RED);
            valid = false;
        }

        if (designationText.getText().length() == 0) {
            designationLabel.setForeground(Color.RED);
            valid = false;
        }

        if (descriptionText.getText().length() == 0) {
            descriptionLabel.setForeground(Color.RED);
            valid = false;
        }

        if (priceText.getText().length() == 0) {
            priceLabel.setForeground(Color.RED);
            valid = false;
        }

        if (getImageButton.getIcon() == null) {
            getImageButton.setForeground(Color.RED);
        }

        return valid;
    }

    /**
     * Sets the icon to the button and makes it appear like if it was only the icon
     *
     * @param icon
     */
    private void setArticleIcon(ImageIcon icon) {
        getImageButton.setIcon(icon);
        getImageButton.setText("");
        getImageButton.setFocusPainted(false);
        getImageButton.setMargin(new Insets(0, 0, 0, 0));
        getImageButton.setContentAreaFilled(false);
        getImageButton.setBorderPainted(false);
        getImageButton.setOpaque(false);
    }

    private byte[] getByteArrayFromImageIcon() {
        try {
            Image image = ((ImageIcon) getImageButton.getIcon()).getImage();
            BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpeg", out);
            return out.toByteArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[] {};
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
