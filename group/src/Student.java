import java.io.*;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int midtermMarks;
    private int finalMarks;
    private double totalMarks;
    private String result;

    public Student(int id, String firstName, String lastName, int midtermMarks, int finalMarks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.midtermMarks = midtermMarks;
        this.finalMarks = finalMarks;
        this.totalMarks = (midtermMarks+finalMarks) /2;
        if (this.totalMarks >= 50) {
            this.result = "Pass";
        } else {
            this.result = "Fail";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMidtermMarks() {
        return midtermMarks;
    }

    public void setMidtermMarks(int midtermMarks) {
        this.midtermMarks = midtermMarks;
    }

    public int getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(int finalMarks) {
        this.finalMarks = finalMarks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getResult() {
        return result;
    }

    public void writeToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(filename), true));
            writer.printf("%d\t\t\t\t%-1s %s\t\t\t%d\t\t\t\t%d\t\t\t\t%.1f\t\t\t%s%n", id, firstName, lastName,
                    midtermMarks,
                    finalMarks, totalMarks, result);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromFile(String filename, int idToRemove) {
        try {
            File inputFile = new File(filename);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                int id = Integer.parseInt(line.split("\t")[0]);
                if (id != idToRemove) {
                    writer.println(line);
                }
            }

            reader.close();
            writer.flush();
            writer.close();

            if (!inputFile.delete()) {
                System.out.println("Failed to delete the original file.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Failed to rename the temporary file.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}