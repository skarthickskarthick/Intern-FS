package Excel_Connections;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;



public class Excel_Operations {
    private static  String FILE_PATH = "D:\\Karthick\\ppt\\Student.xlsx"; // Change to your Excel file path

    private static List<List<Object>> excelData = new ArrayList<>();
   static String SOURCE_FILE_PATH,DESTINATION_FILE_PATH;// Store Excel data

    public static void readExcel() throws IOException {
        excelData.clear(); // Clear previous data

        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = FILE_PATH.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                System.out.println("\nSheet: " + sheet.getSheetName());

                int maxColumns = getMaxColumns(sheet);

                for (Row row : sheet) {
                    List<Object> rowData = new ArrayList<>();
                    for (int col = 0; col < maxColumns; col++) {
                        Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        rowData.add(getCellValue(cell,col)); // Store cell value with original type
                    }
                    excelData.add(rowData);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }

        printStoredData();
    }

    private static int getMaxColumns(Sheet sheet) {
        int maxColumns = 0;
        for (Row row : sheet) {
            maxColumns = Math.max(maxColumns, row.getLastCellNum());
        }
        return maxColumns;
    }

    private static Object getCellValue(Cell cell,int columnIndex) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new java.text.SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()); // Convert to formatted date string
                    // Keep as Date
                }
                double numValue = cell.getNumericCellValue();
                // Convert ONLY the first column (Reg No) to long, keep others as double
                if (columnIndex == 0 && numValue % 1 == 0) {
                    return (long) numValue;
                }
                return numValue;// Keep whole numbers as Integer
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula(); // Store formula as string
            default:
                return null;
        }
    }

    private static void printStoredData() {
        System.out.println("\nStored Excel Data:");

        if (excelData.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        // Determine the max column widths
        int columnCount = excelData.get(0).size();
        int[] colWidths = new int[columnCount];

        for (List<Object> row : excelData) {
            for (int col = 0; col < columnCount; col++) {
                String cellValue = (row.get(col) != null) ? row.get(col).toString() : "Null";
                colWidths[col] = Math.max(colWidths[col], cellValue.length());
            }
        }

        // Print formatted table
        for (List<Object> row : excelData) {
            for (int col = 0; col < columnCount; col++) {
                String format = "%-" + (colWidths[col] + 2) + "s";
                System.out.printf(format, (row.get(col) != null) ? row.get(col).toString() : "Null");
            }
            System.out.println();
        }

        System.out.println("-".repeat(100));
    }
    // Method to check if a sheet exists
    public static boolean doesSheetExist(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            return workbook.getSheet(sheetName) != null;
        } catch (IOException e) {
            System.err.println("❌ Error: Unable to read the Excel file - " + e.getMessage());
            return false;
        }
    }
    public static void findByRegno()
    {
        Scanner scanner=new Scanner(System.in);
        String sheetName = "student";

        // Step 1: Validate Sheet Name
        while (true) {
            System.out.print("Enter the sheet name: ");
            sheetName = scanner.nextLine().trim();

            if (!sheetName.isEmpty() && doesSheetExist(FILE_PATH, sheetName)) {
                break;
            } else {
                System.out.println("❌ Error: Sheet does not exist! Please enter a valid sheet name.");
            }
        }

        Object regNoInput=4;
        while (true) {
            System.out.print("Enter Registration Number: ");
            String input = scanner.nextLine().trim();

            // Try parsing as number first
            try {
                if (input.contains(".")) {
                    regNoInput = Double.parseDouble(input); // Handles decimal input
                } else {
                    regNoInput = Long.parseLong(input); // Convert whole number to Long
                }
                break; // Exit loop if valid number
            } catch (NumberFormatException e) {
                // If not a number, treat as a string
                if (!input.isEmpty()) {
                    regNoInput = input; // Store as String
                    break;
                }
                System.out.println("Error: Registration Number cannot be empty.");
            }
        }


        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = FILE_PATH.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {
                    int maxColumns = getMaxColumns(sheet);

                    for (Row row : sheet) {
                        Cell regNoCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        Object cellRegNo = getCellValue(regNoCell, 0);
                        String inputRegNoStr = regNoInput.toString().trim();
                        String cellRegNoStr = (cellRegNo != null) ? cellRegNo.toString().trim() : "";// Get Reg No value

                        if (inputRegNoStr.equals(cellRegNoStr)) { // Match found
                            for (int col = 0; col < maxColumns; col++) {
                                Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                System.out.print(getCellValue(cell, col) + "\t"); // Print cell value
                            }
                            System.out.println(); // New line after printing the row
                            return; // Stop searching after finding the first match
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }

        System.out.println("No record found for Reg No: " + regNoInput);
    }
    public static Sheet getSheetByName(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = filePath.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            // Return the sheet if found, otherwise null
            return workbook.getSheet(sheetName);
        } catch (IOException e) {
            System.err.println("❌ Error reading Excel file: " + e.getMessage());
            return null;
        }
    }

    public static void ColumnData() {
        Scanner scanner=new Scanner(System.in);
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = FILE_PATH.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            String sheetName = "student";

            // Step 1: Validate Sheet Name
            while (true) {
                System.out.print("Enter the sheet name: ");
                sheetName = scanner.nextLine().trim();

                if (!sheetName.isEmpty() && doesSheetExist(FILE_PATH, sheetName)) {
                    break;
                } else {
                    System.out.println("❌ Error: Sheet does not exist! Please enter a valid sheet name.");
                }
            }
            int columnIndex;
            Sheet sheet = getSheetByName(FILE_PATH, sheetName);
            while (true) {
                try {
                    System.out.print("Enter the column index: ");
                    columnIndex = Integer.parseInt(scanner.nextLine().trim());
                    if(getMaxColumns(sheet)>columnIndex)
                        break;
                    else
                        System.out.println("enter a valid one!");
                } catch (NumberFormatException e) {
                    System.out.println("Error: Enter a valid number");
                }
            }




             System.out.println("\nSheet: " + sheet.getSheetName());


                for (Row row : sheet) {
                        Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        Object value = getCellValue(cell, columnIndex);
                        System.out.println(value != null ? value.toString() : "Null");
                }

        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }
    }


    public static void appendTuple(List<Object> rowInputData) throws IOException
    {
        String sheetName="student";
        Sheet sheet=null;
        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = FILE_PATH.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                 sheet = workbook.getSheetAt(sheetIndex);
                if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {
                  break;
                }
            }
            Row newRow=sheet.createRow(sheet.getLastRowNum()+1);

            for(int col=0;col<rowInputData.size();col++)
            {
                Cell newcell=newRow.createCell(col);
                Object value=rowInputData.get(col);

                if(value instanceof String)
                    newcell.setCellValue((String) value);
                else if(value instanceof Integer)
                    newcell.setCellValue((Integer) value);
                else if(value instanceof Long)
                    newcell.setCellValue((Long) value);
                else if(value instanceof Double)
                    newcell.setCellValue((Double) value);
                else if(value instanceof Boolean)
                    newcell.setCellValue((Boolean) value);
                else if(value instanceof Date)
                {
                    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                    CreationHelper creationHelper = sheet.getWorkbook().getCreationHelper();
                    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
                    newcell.setCellValue((Date) value);
                    newcell.setCellStyle(cellStyle);
                }
                else
                    newcell.setCellValue("");

            }
            FileOutputStream fos=new FileOutputStream(FILE_PATH);
            workbook.write(fos);
            System.out.println("Data appended successfully");
            System.out.println();
            readExcel();

            }

        }

    public static void deleteByRegNo() {
        Scanner scanner = new Scanner(System.in);
        String sheetName = "student";
        boolean recordFound = false;


        Object regNoInput;

        while (true) {
            System.out.print("Enter Registration Number to delete: ");
            String input = scanner.nextLine().trim();
            try {
                if (input.contains(".")) {
                    regNoInput = Double.parseDouble(input);
                } else {
                    regNoInput = Long.parseLong(input);
                }
                break;
            } catch (NumberFormatException e) {
                if (!input.isEmpty()) {
                    regNoInput = input;
                    break;
                }
                System.out.println("Error: Registration Number cannot be empty.");
            }
        }

        try (FileInputStream fis = new FileInputStream(new File(FILE_PATH));
             Workbook workbook = FILE_PATH.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Error: Sheet '" + sheetName + "' not found!");
                return;
            }

            int lastRowNum = sheet.getLastRowNum();
            int rowToDelete = -1;

            // Find the row index to delete
            for (int rowIndex = 0; rowIndex <= lastRowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;

                Cell regNoCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Object cellRegNo = getCellValue(regNoCell, 0);
                String inputRegNoStr = regNoInput.toString().trim();
                String cellRegNoStr = (cellRegNo != null) ? cellRegNo.toString().trim() : "";

                if (inputRegNoStr.equals(cellRegNoStr)) {
                    rowToDelete = rowIndex;
                    recordFound = true;
                    break;
                }
            }

            if (recordFound) {
                if (rowToDelete >= 0) {
                    sheet.removeRow(sheet.getRow(rowToDelete));

                    // **Shift rows only if it's NOT the last row**
                    if (rowToDelete < lastRowNum) {
                        sheet.shiftRows(rowToDelete + 1, lastRowNum, -1);
                    }
                }

                try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                    workbook.write(fos);
                }
                System.out.println("✅ Record deleted successfully.");
                readExcel(); // Display updated Excel data
            } else {
                System.out.println("⚠ No record found for Reg No: " + regNoInput);
                deleteByRegNo();
            }
        } catch (IOException e) {
            System.err.println("❌ Error updating the Excel file: " + e.getMessage());
        }
    }





        public static void copyExcelFile() {
            Scanner scanner = new Scanner(System.in);
            String sourceFilePath;
            String destinationFolderPath;

            // Get valid source file path
            while (true) {
                System.out.print("Enter a source path: ");
                sourceFilePath = scanner.nextLine().trim();
                File sourceFile = new File(sourceFilePath);

                if (!sourceFilePath.isEmpty() && sourceFile.exists() && sourceFile.isFile()) {
                    break; // Valid file, exit loop
                } else {
                    System.out.println("❌ Error: Invalid source file! Please enter a valid Excel file path.");
                }
            }

            // Get valid destination directory
            while (true) {
                System.out.print("Enter a Destination folder path: ");
                destinationFolderPath = scanner.nextLine().trim();
                File destinationDir = new File(destinationFolderPath);

                // Create folder if it doesn't exist
                if (!destinationFolderPath.isEmpty()) {
                    if (!destinationDir.exists()) {
                        destinationDir.mkdirs(); // Create directory if not present
                        System.out.println("📁 Destination folder created: " + destinationFolderPath);
                    }
                    break;
                } else {
                    System.out.println("❌ Error: Invalid destination folder! Please enter a valid path.");
                }
            }

            // Create destination file in the given folder with the same name as source
            File sourceFile = new File(sourceFilePath);
            File destinationFile = new File(destinationFolderPath, sourceFile.getName());

            // Perform File Copy
            try (FileInputStream fis = new FileInputStream(sourceFile);
                 FileOutputStream fos = new FileOutputStream(destinationFile)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }

                System.out.println("✅ Excel file copied successfully to: " + destinationFile.getAbsolutePath());

            } catch (IOException e) {
                System.out.println("❌ Error copying file: " + e.getMessage());
                copyExcelFile();
            }
        }





    public static void mergeExcelFiles() {
        Scanner scanner = new Scanner(System.in);
        String FILE_PATH1, FILE_PATH2, outputFILE_PATH;

        while (true) {
            System.out.print("Enter a file path 1: ");
            FILE_PATH1 = scanner.nextLine().trim();
            if (!FILE_PATH1.isEmpty() && Files.exists(Path.of(FILE_PATH1))) {
                break;
            } else {
                System.out.println("Error: Invalid path! Please enter a valid file path.");
            }
        }

        while (true) {
            System.out.print("Enter a file path 2: ");
            FILE_PATH2 = scanner.nextLine().trim();
            if (!FILE_PATH2.isEmpty() && Files.exists(Path.of(FILE_PATH2)) && !FILE_PATH1.equals(FILE_PATH2)) {
                break;
            } else {
                System.out.println("Error: Invalid path! Please enter a valid file path.");
            }
        }

        while (true) {
            System.out.print("Enter a Destination path to merge: ");
            outputFILE_PATH = scanner.nextLine().trim();
            if (!outputFILE_PATH.isEmpty() && !outputFILE_PATH.equals(FILE_PATH1) && !outputFILE_PATH.equals(FILE_PATH2)) {
                break;
            } else {
                System.out.println("Error: Invalid destination path! Please enter a valid path.");
            }
        }

        try {
            mergeFiles(FILE_PATH1, FILE_PATH2, outputFILE_PATH);
            System.out.println("Merged Excel file saved at: " + outputFILE_PATH);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error merging Excel files: " + e.getMessage());
            System.out.println("Please enter a new destination path to merge: ");
            outputFILE_PATH = scanner.nextLine().trim();
            try {
                mergeFiles(FILE_PATH1, FILE_PATH2, outputFILE_PATH);
                System.out.println("Merged Excel file saved at: " + outputFILE_PATH);
            } catch (IOException ex) {
                System.err.println("Failed again. Exiting... Error: " + ex.getMessage());
            }
        }
    }

    private static void mergeFiles(String FILE_PATH1, String FILE_PATH2, String outputFILE_PATH) throws IOException {
        try (FileInputStream fis1 = new FileInputStream(new File(FILE_PATH1));
             FileInputStream fis2 = new FileInputStream(new File(FILE_PATH2));
             Workbook workbook1 = FILE_PATH1.endsWith(".xlsx") ? new XSSFWorkbook(fis1) : new HSSFWorkbook(fis1);
             Workbook workbook2 = FILE_PATH2.endsWith(".xlsx") ? new XSSFWorkbook(fis2) : new HSSFWorkbook(fis2);
             Workbook outputWorkbook = new XSSFWorkbook()) {

            for (int i = 0; i < workbook1.getNumberOfSheets(); i++) {
                String sheetName = getUniqueSheetName(outputWorkbook, workbook1.getSheetName(i));
                copySheet(workbook1.getSheetAt(i), outputWorkbook.createSheet(sheetName));
            }

            for (int i = 0; i < workbook2.getNumberOfSheets(); i++) {
                String sheetName = getUniqueSheetName(outputWorkbook, workbook2.getSheetName(i));
                copySheet(workbook2.getSheetAt(i), outputWorkbook.createSheet(sheetName));
            }

            try (FileOutputStream fos = new FileOutputStream(outputFILE_PATH)) {
                outputWorkbook.write(fos);
            }
        }
    }

    private static String getUniqueSheetName(Workbook workbook, String sheetName) {
        String newSheetName = sheetName;
        int counter = 1;
        while (workbook.getSheet(newSheetName) != null) {
            newSheetName = sheetName + "_" + counter;
            counter++;
        }
        return newSheetName;
    }

    private static void copySheet(Sheet sourceSheet, Sheet targetSheet) {
        for (int i = 0; i <= sourceSheet.getLastRowNum(); i++) {
            Row sourceRow = sourceSheet.getRow(i);
            Row targetRow = targetSheet.createRow(i);
            if (sourceRow != null) {
                for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
                    Cell sourceCell = sourceRow.getCell(j);
                    Cell targetCell = targetRow.createCell(j);
                    if (sourceCell != null) {
                        copyCell(sourceCell, targetCell, sourceSheet.getWorkbook(), targetSheet.getWorkbook());
                    }
                }
            }
        }
    }

    private static void copyCell(Cell sourceCell, Cell targetCell, Workbook sourceWorkbook, Workbook targetWorkbook) {
        CellStyle newCellStyle = targetWorkbook.createCellStyle();
        newCellStyle.cloneStyleFrom(sourceCell.getCellStyle());
        targetCell.setCellStyle(newCellStyle);

        switch (sourceCell.getCellType()) {
            case STRING:
                targetCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(sourceCell)) {
                    targetCell.setCellValue(sourceCell.getDateCellValue());
                } else {
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                targetCell.setCellFormula(sourceCell.getCellFormula());
                break;
            case BLANK:
                targetCell.setBlank();
                break;
            default:
                break;
        }
    }
    public static void removeDuplicates(String filePath, int regNoColumnIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            Set<String> uniqueRegNos = new HashSet<>();
            List<Row> duplicateRows = new ArrayList<>();

            int lastRowNum = sheet.getLastRowNum();

            // Identify duplicate rows
            for (int i = 1; i <= lastRowNum; i++) { // Assuming the first row is headers
                Row row = sheet.getRow(i);
                if (row == null) continue;


                Cell regNoCell = row.getCell(regNoColumnIndex);
                if (regNoCell == null) continue;

                String regNo = regNoCell.toString().trim();

                if (!uniqueRegNos.add(regNo)) {
                    duplicateRows.add(row); // Add duplicate row to list
                }
            }
            if(duplicateRows.isEmpty())
            {
                System.out.println("No duplicates found");
                return;
            }
            // Display removed records
            System.out.println("Removed Duplicate Records:");
            for (Row row : duplicateRows) {
                for (Cell cell : row) {
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();
                sheet.removeRow(row);
            }

            // Save updated sheet
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Duplicate records removed successfully!");

        } catch (IOException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
        }
    }
        public static void mergingfiles()
        {

            Scanner scanner = new Scanner(System.in);
            String FILE_PATH1, FILE_PATH2, outputFILE_PATH;

            while (true) {
                System.out.print("Enter a file path 1: ");
                FILE_PATH1 = scanner.nextLine().trim();
                if (!FILE_PATH1.isEmpty() && Files.exists(Path.of(FILE_PATH1))) {
                    break;
                } else {
                    System.out.println("Error: Invalid path! Please enter a valid file path.");
                }
            }

            while (true) {
                System.out.print("Enter a file path 2: ");
                FILE_PATH2 = scanner.nextLine().trim();
                if (!FILE_PATH2.isEmpty() && Files.exists(Path.of(FILE_PATH2)) && !FILE_PATH1.equals(FILE_PATH2)) {
                    break;
                } else {
                    System.out.println("Error: Invalid path! Please enter a valid file path.");
                }
            }

            while (true) {
                System.out.print("Enter a Destination path to merge: ");
                outputFILE_PATH = scanner.nextLine().trim();
                if (!outputFILE_PATH.isEmpty() && !outputFILE_PATH.equals(FILE_PATH1) && !outputFILE_PATH.equals(FILE_PATH2)) {
                    break;
                } else {
                    System.out.println("Error: Invalid destination path! Please enter a valid path.");
                }
            }

            try {
                File fileInput1 = new File(FILE_PATH1);
                File fileInput2 = new File(FILE_PATH2);
                File destinationFile = new File(outputFILE_PATH);

                FileInputStream fileInputStream1 = new FileInputStream(fileInput1);
                FileInputStream fileInputStream2 = new FileInputStream(fileInput2);
                FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

                XSSFWorkbook workbookFile1 = new XSSFWorkbook(fileInputStream1);
                XSSFWorkbook workbookFile2 = new XSSFWorkbook(fileInputStream2);
                XSSFWorkbook workbookDestination = new XSSFWorkbook();

                XSSFSheet sheet1 = workbookFile1.getSheetAt(0);
                XSSFSheet sheet2 = workbookFile2.getSheetAt(0);
                XSSFSheet destine = workbookDestination.createSheet();

                Iterator<Row> rowIterator1 = sheet1.rowIterator();
                int rowId = 0;

                while (rowIterator1.hasNext()) {
                    Row row1 = destine.createRow(rowId++);
                    Row row2 = rowIterator1.next();
                    int cellId = 0;
                    Iterator<Cell> cellIterator = row2.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = row1.createCell(cellId++);
                        Cell oldCell = cellIterator.next();
                        switch (oldCell.getCellType()) {
                            case STRING:
                                cell.setCellValue(oldCell.getStringCellValue());
                                break;
                            case NUMERIC:
                                cell.setCellValue(oldCell.getNumericCellValue());
                                break;
                            case BLANK:
                                cell.setCellValue("");
                                break;
                            case BOOLEAN:
                                cell.setCellValue(oldCell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cell.setCellFormula(oldCell.getCellFormula());
                                break;
                            default:
                                cell.setCellValue("");
                                break;
                        }
                    }

                }


                Iterator<Row> rowIterator2 = sheet2.rowIterator();

                while (rowIterator2.hasNext()) {
                    Row oldRow = rowIterator2.next();
                    Row newRow = destine.createRow(rowId++);
                    int cellId = 0;
                    Iterator<Cell> cellIterator = oldRow.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = newRow.createCell(cellId++);
                        Cell oldCell = cellIterator.next();
                        switch (oldCell.getCellType()) {
                            case STRING:
                                cell.setCellValue(oldCell.getStringCellValue());
                                break;
                            case NUMERIC:
                                cell.setCellValue(oldCell.getNumericCellValue());
                                break;
                            case BLANK:
                                cell.setCellValue("");
                                break;
                            case BOOLEAN:
                                cell.setCellValue(oldCell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cell.setCellFormula(oldCell.getCellFormula());
                                break;
                            default:
                                cell.setCellValue("");
                                break;
                        }
                    }
                }

                workbookDestination.write(fileOutputStream);

                fileInputStream1.close();
                fileInputStream2.close();
                fileOutputStream.close();
                workbookFile1.close();
                workbookFile2.close();
                workbookDestination.close();
                System.out.println("Files merged successfully!");



            }
            catch (Exception e){
                System.out.println("File Not Found");
                mergingfiles();
            }
        }

    public static void main(String args[]) throws IOException {
    int choice;
        Scanner scanner=new Scanner(System.in);

        while (true) {
            System.out.println("Enter a valid File path: ");
            FILE_PATH = scanner.nextLine().trim();
           // FILE_PATH = "D:\\Karthick\\ppt\\Student.xlsx";
            // Check if the input is not empty and is a valid directory
            if (!FILE_PATH.isEmpty() && Files.exists(Path.of(FILE_PATH))) {
                break; // Valid directory, exit loop
            } else {
                System.out.println("Error: Invalid path! Please enter a valid directory.");
            }
        }

        while(true)
        {


            while (true) {
                try {

                    System.out.println("1-Read Data 2-Find Tuple by Regno 3-Find column by column index 4-Add Tuple 5-Delete By Reg no 6-Copy excel files 7-Merge Excel files 8-Duplicate files  9-Exit");
                    System.out.println("enter the choice");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 1 && choice <= 9)
                        break;
                    else
                        System.out.println("enter number less than 9");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Format!");
                }
            }

            if(choice==1)
            {
                readExcel();
            }

          else  if(choice==2)
            {
              findByRegno();
            }
          else if (choice==3)
            {

                ColumnData();
            }
          else if(choice ==4)
            {
                List<Object> rowInputData=new ArrayList<>();


                while (true) {
                    try {
                        System.out.print("Enter the Reg no: ");
                        rowInputData.add(Integer.parseInt(scanner.nextLine().trim()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Enter a valid number");
                    }
                }
                while (true) {
                    System.out.print("Enter the Student name: ");
                  String  name = scanner.nextLine().trim();
                    if (name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                        rowInputData.add(name);
                        break;
                    } else {
                        System.out.println("Error: Invalid  name");
                    }
                }
                while(true)
                {
                    System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
                    try {
                        rowInputData.add(java.sql.Date.valueOf(scanner.nextLine().trim()));
                        break;// Convert to Date
                    } catch (Exception e) {
                        System.out.println("Enter valid DATE");// Handle invalid date
                    }
                }

                while (true) {
                    System.out.print("Enter the Department name: ");
                    String  name = scanner.nextLine().trim();
                    if (name.matches("[A-Za-z]+( [A-Za-z]+)*")) {
                        rowInputData.add(name);
                        break;
                    } else {
                        System.out.println("Error: Invalid  name");
                    }
                }
                while (true) {
                    try {
                        System.out.print("Enter the Mathematics marks");
                        rowInputData.add(Double.valueOf(scanner.nextLine().trim()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Enter the valid marks");
                    }
                }
                while (true) {
                    try {
                        System.out.print("Enter the Reasoning marks ");
                        rowInputData.add(Double.valueOf(scanner.nextLine().trim()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Enter a valid number");
                    }
                }
                while (true) {
                    try {
                        System.out.print("Enter the Genreal science marks: ");
                        rowInputData.add(Double.valueOf(scanner.nextLine().trim()));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Enter a valid number");
                    }
                }
                System.out.println(rowInputData.toString());
                // Calculate and Store Total Marks
                double total = (double) rowInputData.get(4) + (double) rowInputData.get(5) + (double) rowInputData.get(6);
                rowInputData.add(total);

                appendTuple(rowInputData);
            } else if (choice==5) {
              deleteByRegNo();

            } else if (choice==6) {


                copyExcelFile();
            }
          else if (choice==7) {

              mergingfiles();
            } else if (choice==8) {
              removeDuplicates(FILE_PATH,0);

            } else
            {
                break;
            }
        }
    }
}
