import java.io.*;
import java.util.List;
import java.util.Set;

public class Main {
    private static final int LINE_OF_CODE = 100_000;
    public static void main(String[] args) {
        final String instagram = "Instagram";
        final List<String> screenResolution = List.of("1170x2532",
                "1242x2688",
                "828x1792",
                "1125x2436",
                "750x1334",
                "640x1136",
                "828x1792",
                "1242x2688",
                "1125x2436",
                "750x1334",
                "640x1136",
                "640x960",
                "640x1136",
                "750x1334",
                "1125x2436",
                "1242x2688",
                "828x1792",
                "1170x2532",
                "1242x2688",
                "828x1792",
                "1125x2436",
                "1080x1920",
                "750x1334",
                "640x1336",
                "1440x2560",
                "1080x2160",
                "1536x2048");
        final Set<String> screen = Set.copyOf(screenResolution);
        String filePath = "src/formatted_devices.txt";
        String filesaved = "src/user_agent.txt";

        List<String> instagramVerison = List.of("374.0.0.0.5",
                "373.0.0.0.65",
                "373.0.0.0.64",
                "373.0.0.0.61",
                "373.0.0.0.57",
                "373.0.0.0.54",
                "373.0.0.48.60",
                "373.0.0.0.48",
                "373.0.0.0.44",
                "373.0.0.40.60",
                "373.0.0.0.42");
        System.out.println(screen.size());
        List<String> dpi = List.of("320dpi, 400dpi", "440dpi", "480dpi", "560dpi", "640dpi", "720dpi", "800dpi", "960dpi");
        List<String> versiAndroid = List.of("7.0/7.1.2",
                "8.0/8.1.0",
                "9/9.0",
                "10/10.0",
                "11/11.0",
                "12/12.0",
                "13/13.0",
                "14/14.0");
        List<String> bahasa = List.of("en_US",
                "en_GB",
                "en_AU",
                "en_CA",
                "en_IN",
                "en_NZ",
                "en_PH",
                "en_SG",
                "en_ZA",
                "en_IE",
                "en_MA",
                "en_NG",
                "en_PK",
                "en_TW",
                "en_VN");
        long size = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filesaved))) {
            for (String screenRes : screenResolution) {
                for (String instaVersion : instagramVerison) {
                    for (String dpiValue : dpi) {
                        for (String androidVersion : versiAndroid) {
                            for (String language : bahasa) {
                                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        writer.write(instagram + " " + instaVersion + " Android " + "(" + androidVersion + "; " + dpiValue + "; " + screenRes + "; " + line + "; " + language + ")");
                                        writer.newLine();
                                        System.out.print("\rSuccess " +  String.format("%.2f", (double) size/LINE_OF_CODE*100) + "%" + " " + size + "/" + LINE_OF_CODE);
                                        size++;
                                        if (size == LINE_OF_CODE) {
                                            return;
                                        }
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
