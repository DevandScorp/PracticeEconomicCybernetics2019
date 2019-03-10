package lab4;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MainFrame extends JFrame implements Printable {
    private MainFrame() {
        initComponents();
        this.setTitle("Cardioid");
        this.setSize(400, 470);
        File f = new File("src");
        if (f.exists()) {
            System.out.println(f.getAbsolutePath());
        }
        codeText = getCodeText(new File("src\\Cardioid.java"));
    }

    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JPanel jPanel;

    private String[] codeText;
    private int[] pageBreaks;
    private BufferedImage b;

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2D = (Graphics2D) g;
                g2D.setColor(java.awt.Color.WHITE);
                g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
                g2D.setColor(java.awt.Color.BLACK);
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2D.setStroke(new CardioidStroke(3));
                g2D.translate(250, 210);
                g2D.setFont(new Font("Sans", Font.BOLD + Font.ITALIC, 14));
                g2D.drawString("Cardioid", -75, 0);
                g2D.translate(-250, -210);
                g2D.draw(new Cardioid(getWidth() / 2 + 50, getHeight() / 2, 70));
            }
        }
        ;
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 279, Short.MAX_VALUE)
        );

        jMenu.setText("Options");

        jMenuItem.setText("Print");
        jMenuItem.addActionListener(this::jMenuItemActionPerformed);
        jMenu.add(jMenuItem);

        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        PrinterJob job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        printRequestAttributeSet.add(Sides.DUPLEX);
        printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);

        job.setPrintable(this);
        boolean ok = job.printDialog(printRequestAttributeSet);
        if (ok) {
            try {
                job.print(printRequestAttributeSet);
            } catch (PrinterException ex) {
                System.err.print(ex);
            }
        }

    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }


    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        int y = 0;
        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int lineHeight = metrics.getHeight();
        if (pageIndex == 0) {
            BufferedImage bufferedImageAll = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2DForImage = bufferedImageAll.createGraphics();
            this.printAll(graphics2DForImage);
            double scale = pageFormat.getWidth() / this.getWidth();
            int newWidth = (int) (this.getWidth() * scale / 2.5);
            int newHeight = (int) (this.getHeight() * scale / 2.5);
            b = bufferedImageAll;
            Image scaledImage = bufferedImageAll.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            graphics.drawImage(scaledImage,
                    (int) (pageFormat.getImageableWidth() / 2),
                    (int) (pageFormat.getImageableHeight() / 4), null);
            return PAGE_EXISTS;
        }

        if (pageBreaks == null) {
            int linesPerPage = (int) (pageFormat.getImageableHeight() / lineHeight);
            int numBreaks = (codeText.length - 1) / linesPerPage + 1;
            pageBreaks = new int[numBreaks];
            for (int b = 0; b < numBreaks; b++) {
                pageBreaks[b] = b * linesPerPage;
            }
        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2D = (Graphics2D) graphics;
        g2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        int start = pageBreaks[pageIndex - 1];
        int end = (pageIndex == pageBreaks.length) ? codeText.length : pageBreaks[pageIndex];
        for (int line = start; line < end; line++) {
            y += lineHeight;
            graphics.drawString(codeText[line], 0, y);
        }
        return PAGE_EXISTS;
    }

    private String[] getCodeText(File file) {
        LinkedList<String> result = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            return null;
        }
        return result.toArray(new String[result.size()]);
    }
}