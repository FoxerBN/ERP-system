package sk.foxer.erpstock.util;

import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import sk.foxer.erpstock.model.stockin.StockIn;
import sk.foxer.erpstock.model.stockout.StockOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class PdfExportUtil {

    private static final ColumnSpec[] COMMON_COLUMNS = {
            new ColumnSpec("ID", 45f, Alignment.CENTER),
            new ColumnSpec("Dátum", 80f, Alignment.CENTER),
            new ColumnSpec("Produkt", 190f, Alignment.CENTER),
            new ColumnSpec("Množstvo", 70f, Alignment.CENTER),
            new ColumnSpec("Jedn. cena", 70f, Alignment.CENTER),
            new ColumnSpec("Celkom", 60f, Alignment.CENTER)
    };

    private PdfExportUtil() {
    }

    public static Path exportStockOutTable(ObservableList<StockOut> items) throws IOException {
        Path defaultPath = Paths.get(System.getProperty("user.home"), "customer_outcomes.pdf");
        return exportStockOutTable(items, defaultPath);
    }

    public static Path exportStockOutTable(List<StockOut> items, Path output) throws IOException {
        return exportTable(
                items,
                output,
                "Prehľad výdajov zákazníka",
                stockOut -> new String[]{
                        String.valueOf(stockOut.getId()),
                        String.valueOf(stockOut.getDate()),
                        nullSafe(stockOut.getProductName()),
                        formatNumber(stockOut.getQuantity()),
                        formatCurrency(stockOut.getUnitPrice()),
                        formatCurrency(stockOut.getTotalPrice())
                }
        );
    }

    public static Path exportStockInTable(ObservableList<StockIn> items) throws IOException {
        Path defaultPath = Paths.get(System.getProperty("user.home"), "supplier_incomes.pdf");
        return exportStockInTable(items, defaultPath);
    }

    public static Path exportStockInTable(List<StockIn> items, Path output) throws IOException {
        return exportTable(
                items,
                output,
                "Prehľad príjmov dodávateľa",
                stockIn -> new String[]{
                        String.valueOf(stockIn.getId()),
                        String.valueOf(stockIn.getDate()),
                        nullSafe(stockIn.getProductName()),
                        formatNumber(stockIn.getQuantity()),
                        formatCurrency(stockIn.getUnitPrice()),
                        formatCurrency(stockIn.getTotal())
                }
        );
    }

    private static <T> Path exportTable(
            List<T> items,
            Path output,
            String title,
            Function<T, String[]> rowMapper
    ) throws IOException {
        if (items == null || items.isEmpty()) {
            throw new IOException("No data to export.");
        }
        Objects.requireNonNull(rowMapper, "rowMapper");

        if (output.getParent() != null) {
            Files.createDirectories(output.getParent());
        }

        try (PDDocument document = new PDDocument()) {
            ExportContext context = new ExportContext(document, COMMON_COLUMNS);
            context.writeTitle(title);
            context.writeHeader();
            for (T item : items) {
                context.writeRow(rowMapper.apply(item));
            }
            context.close();
            document.save(output.toFile());
        }

        return output;
    }

    private static String nullSafe(String value) {
        return value == null ? "" : value;
    }

    private static String formatNumber(Double value) {
        return value == null ? "" : String.format("%.2f", value);
    }

    private static String formatCurrency(Double value) {
        return value == null ? "" : String.format("%.2f EUR", value);
    }

    private static String sanitize(String text) {
        if (text == null) {
            return "";
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }

    private static final class ExportContext {
        private final PDDocument document;
        private final ColumnSpec[] columns;
        private PDPage page;
        private PDPageContentStream contentStream;
        private PDRectangle mediaBox;
        private float xStart;
        private float yPosition;
        private final float tableWidth;
        private int rowCounter = 0;
        private boolean headerWritten = false;

        private static final float MARGIN = 40f;
        private static final float ROW_HEIGHT = 24f;
        private static final float PADDING = 6f;
        private static final float TITLE_FONT_SIZE = 15f;
        private static final float HEADER_FONT_SIZE = 11f;
        private static final float BODY_FONT_SIZE = 10f;
        private static final PDType1Font TITLE_FONT = PDType1Font.HELVETICA_BOLD;
        private static final PDType1Font HEADER_FONT = PDType1Font.HELVETICA_BOLD;
        private static final PDType1Font BODY_FONT = PDType1Font.HELVETICA;

        ExportContext(PDDocument document, ColumnSpec[] columns) throws IOException {
            this.document = document;
            this.columns = columns;
            this.tableWidth = ColumnSpec.totalWidth(columns);
            createNewPage();
        }

        void writeTitle(String title) throws IOException {
            ensureContentStream();
            contentStream.setNonStrokingColor(33, 37, 41);
            contentStream.setFont(TITLE_FONT, TITLE_FONT_SIZE);
            contentStream.beginText();
            contentStream.newLineAtOffset(xStart, yPosition);
            contentStream.showText(sanitize(title));
            contentStream.endText();

            contentStream.setStrokingColor(166, 177, 194);
            contentStream.moveTo(xStart, yPosition - 4);
            contentStream.lineTo(xStart + tableWidth, yPosition - 4);
            contentStream.stroke();

            yPosition -= ROW_HEIGHT;
        }

        void writeHeader() throws IOException {
            ensureContentStream();
            headerWritten = true;

            drawRowBackground(true, false);
            drawCellBorders(ROW_HEIGHT);

            contentStream.setNonStrokingColor(33, 37, 41);
            contentStream.setFont(HEADER_FONT, HEADER_FONT_SIZE);

            float xCursor = xStart;
            for (ColumnSpec column : columns) {
                String headerText = fitText(column, column.header, HEADER_FONT, HEADER_FONT_SIZE);
                float textWidth = textWidth(HEADER_FONT, HEADER_FONT_SIZE, headerText);
                float textX = computeAlignedX(xCursor, column.width, textWidth, column.alignment);
                float textY = baselineForFont(HEADER_FONT_SIZE);

                contentStream.beginText();
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText(headerText);
                contentStream.endText();

                xCursor += column.width;
            }

            yPosition -= ROW_HEIGHT;
        }

        void writeRow(String[] values) throws IOException {
            ensureSpaceForRow();
            boolean shaded = (rowCounter % 2) == 1;

            drawRowBackground(false, shaded);
            drawCellBorders(ROW_HEIGHT);

            contentStream.setNonStrokingColor(45, 45, 45);
            contentStream.setFont(BODY_FONT, BODY_FONT_SIZE);

            float xCursor = xStart;
            for (int i = 0; i < columns.length; i++) {
                ColumnSpec column = columns[i];
                String value = fitText(column, values[i], BODY_FONT, BODY_FONT_SIZE);
                float textWidth = textWidth(BODY_FONT, BODY_FONT_SIZE, value);
                float textX = computeAlignedX(xCursor, column.width, textWidth, column.alignment);
                float textY = baselineForFont(BODY_FONT_SIZE);

                contentStream.beginText();
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText(value);
                contentStream.endText();

                xCursor += column.width;
            }

            yPosition -= ROW_HEIGHT;
            rowCounter++;
        }

        void close() throws IOException {
            if (contentStream != null) {
                contentStream.close();
            }
        }

        private void ensureSpaceForRow() throws IOException {
            if (yPosition - ROW_HEIGHT <= mediaBox.getLowerLeftY() + MARGIN) {
                contentStream.close();
                createNewPage();
                writeHeader();
            } else if (!headerWritten) {
                writeHeader();
            }
        }

        private void createNewPage() throws IOException {
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            mediaBox = page.getMediaBox();
            xStart = mediaBox.getLowerLeftX() + MARGIN;
            yPosition = mediaBox.getUpperRightY() - MARGIN;
            contentStream = new PDPageContentStream(document, page);
            contentStream.setLineWidth(0.5f);
            contentStream.setStrokingColor(180, 180, 180);
            headerWritten = false;
            rowCounter = 0;
        }

        private void drawRowBackground(boolean header, boolean shaded) throws IOException {
            if (header) {
                contentStream.setNonStrokingColor(217, 232, 255);
            } else if (shaded) {
                contentStream.setNonStrokingColor(245, 245, 245);
            } else {
                contentStream.setNonStrokingColor(255, 255, 255);
            }

            contentStream.addRect(xStart, yPosition - ROW_HEIGHT, tableWidth, ROW_HEIGHT);
            contentStream.fill();
            contentStream.setNonStrokingColor(0, 0, 0);
        }

        private void drawCellBorders(float rowHeight) throws IOException {
            contentStream.setStrokingColor(200, 200, 200);
            float xCursor = xStart;
            for (ColumnSpec column : columns) {
                contentStream.addRect(xCursor, yPosition - rowHeight, column.width, rowHeight);
                contentStream.stroke();
                xCursor += column.width;
            }
            contentStream.setStrokingColor(180, 180, 180);
        }

        private float computeAlignedX(float columnX, float columnWidth, float textWidth, Alignment alignment) {
            switch (alignment) {
                case RIGHT:
                    return Math.max(columnX + PADDING, columnX + columnWidth - PADDING - textWidth);
                case CENTER:
                    float centered = columnX + (columnWidth - textWidth) / 2f;
                    float min = columnX + PADDING;
                    float max = columnX + columnWidth - PADDING - textWidth;
                    if (max <= min) {
                        return min;
                    }
                    return Math.max(min, Math.min(max, centered));
                default:
                    return columnX + PADDING;
            }
        }

        private String fitText(ColumnSpec column, String text, PDType1Font font, float fontSize) throws IOException {
            float available = column.width - 2 * PADDING;
            if (available <= 0) {
                return "";
            }

            String sanitized = sanitize(text);
            if (textWidth(font, fontSize, sanitized) <= available) {
                return sanitized;
            }

            String ellipsis = "...";
            float ellipsisWidth = textWidth(font, fontSize, ellipsis);
            StringBuilder builder = new StringBuilder();
            for (char ch : sanitized.toCharArray()) {
                String candidate = builder.toString() + ch;
                if (textWidth(font, fontSize, candidate) + ellipsisWidth > available) {
                    break;
                }
                builder.append(ch);
            }
            builder.append(ellipsis);
            return builder.toString();
        }

        private float textWidth(PDType1Font font, float fontSize, String text) throws IOException {
            return font.getStringWidth(text) / 1000f * fontSize;
        }

        private float baselineForFont(float fontSize) {
            float centered = yPosition - ROW_HEIGHT / 2f + fontSize * 0.35f;
            float minBaseline = yPosition - ROW_HEIGHT + PADDING + fontSize * 0.2f;
            float maxBaseline = yPosition - PADDING;
            return Math.max(minBaseline, Math.min(maxBaseline, centered));
        }

        private void ensureContentStream() {
            if (contentStream == null) {
                throw new IllegalStateException("Content stream is not initialized.");
            }
        }
    }

    private enum Alignment {
        LEFT,
        CENTER,
        RIGHT
    }

    private static final class ColumnSpec {
        final String header;
        final float width;
        final Alignment alignment;

        ColumnSpec(String header, float width, Alignment alignment) {
            this.header = header;
            this.width = width;
            this.alignment = alignment;
        }

        static float totalWidth(ColumnSpec[] columns) {
            float total = 0f;
            for (ColumnSpec column : columns) {
                total += column.width;
            }
            return total;
        }
    }
}
