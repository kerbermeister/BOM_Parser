package project.Formatters;

public final class TextFormatter {

    public static String deleteChineseSymbols(String s) {
        String formattedString = s.replaceAll("[^A-Za-zА-Яа-я-^0-9-/.-/,]", "");
        return formattedString;
    }
}
