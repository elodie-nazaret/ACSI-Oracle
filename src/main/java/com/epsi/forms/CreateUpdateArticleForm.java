package com.epsi.forms;

import com.epsi.entities.Article;
import com.epsi.entities.ArticleDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    /**
     * Constructeur vide pour la création d'un article
     */
    public CreateUpdateArticleForm() {
        this.init();

        this.article = null;

        setTitle("Nouvel article");
    }

    /**
     * Constructeur avec un article pour la modification
     * @param article article à modifier
     */
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

    /**
     * Initialise la form
     */
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
        if (e.getSource() == this.getImageButton) {
            this.chooseImage();
        } else if (e.getSource() == this.cancelButton) {
            this.dispose();

        } else if (e.getSource() == this.validateButton) {
            this.saveArticle();
        }
    }

    /**
     * Enregistre l'article en base de données ou le crée
     */
    private void saveArticle() {
        ArticleDAO articleDAO = new ArticleDAO();

        if (this.areFieldsValid()) {
            if (this.article == null) {
                /**
                 * Création d'article
                 */
                Article article = new Article();

                article.setDesignation(this.designationText.getText());
                article.setDescription(this.descriptionText.getText());
                article.setReference(this.referenceText.getText());
                article.setPrice(Float.valueOf(this.priceText.getText()));
                article.setIsVisible(true);
                article.setCreatedAt(new Date());
                article.setUpdatedAt(new Date());
                article.setImage(this.getByteArrayFromImageIcon());

                articleDAO.addArticle(article);
                new ArticleForm(article);
                this.dispose();

            } else {
                /**
                 * Mise à jour de l'article
                 */
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

    /**
     * Permet à l'utilisateur d'ajouter une image
     */
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        fileChooser.setFileFilter(imageFilter);
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage myPicture = ImageIO.read(fileChooser.getSelectedFile());
                Image image = myPicture.getScaledInstance(100, -1, -1);
                this.setArticleIcon(new ImageIcon(image));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Vérifie la validité des champs
     * @return boolean
     */
    private boolean areFieldsValid() {
        boolean valid = true;
        Color base = Color.black;
        Color error = Color.RED;

        referenceLabel.setForeground(base);
        designationLabel.setForeground(base);
        descriptionLabel.setForeground(base);
        priceLabel.setForeground(base);
        getImageButton.setForeground(base);

        if (referenceText.getText().length() == 0) {
            referenceLabel.setForeground(error);
            valid = false;
        }

        if (designationText.getText().length() == 0) {
            designationLabel.setForeground(error);
            valid = false;
        }

        if (descriptionText.getText().length() == 0) {
            descriptionLabel.setForeground(error);
            valid = false;
        }

        if (!priceText.getText().matches("^\\d+(\\.\\d+)?$")) {
            priceLabel.setForeground(error);
            valid = false;
        }

        if (getImageButton.getIcon() == null) {
            getImageButton.setForeground(error);
        }

        return valid;
    }

    /**
     * Met en forme le bouton d'ajout d'image pour qu'il apparaisse comme s'il s'agissait uniquement d'une icône
     *
     * @param icon image à afficher
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

    /**
     * Renvoi le byte[] correspondant à l'image couramment affichée
     * @return byte[]
     */
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
