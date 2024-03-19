package test;

import data.datasource.DataSource;
import data.process.SQLDataHandler;
import process.RequestProcess;
import process.file.TextProcess;
import tmplFile.Children;
import tmplFile.Tmpl;

public class App {
    public static void main(String[] args) throws Exception {
        scriptProcess("C:\\Users\\MISA\\Desktop\\Workspace\\S6\\Mr-Naina\\Genesis\\test\\fichierdegeneration.tmpl",
                "java-springy-model", new String[] { "Emprunts" });
    }

    public static void scriptProcess(String generationFile, String templateName, String[] table) throws Exception {

        Tmpl tmpl = new Tmpl(generationFile);
        // from configuration
        String url = tmpl.getChild().BFS("url").getValue().trim();
        String user = tmpl.getChild().BFS("user").getValue().trim();
        String paswd = tmpl.getChild().BFS("password").getValue().trim();
        String globV = tmpl.getChild().BFS("global_variable").getValue().trim();
        String templateFile = tmpl.search("template", templateName).getValue().trim();
        String fileExtension = search(tmpl, new String[] { "fileExtension" }, templateName).trim();
        String outputPath = search(tmpl, new String[] { "outputPath" }, templateName).trim();
        String fileNameSuffix = search(tmpl, new String[] { "fileNameSuffix" }, templateName).trim();
        // ----------------------------------------
        // process

        DataSource[] dataSources = new SQLDataHandler(url, user,
                paswd).getDataSources();

        if (table.length == 0) {
            System.out.println("No file generated because no table given");
        }

        for (int i = 0; i < table.length; i++) {
            if (!table[i].equals("*")) {
                DataSource dataSource = null;
                for (int j = 0; j < dataSources.length; j++) {
                    if (dataSources[j].getLabel().equalsIgnoreCase(table[i]))
                        dataSource = dataSources[j];
                }
                if (dataSource == null) {
                    throw new Exception("No table named " + table[i] + " found");
                }
                RequestProcess requestProcess = new RequestProcess(dataSource,
                        globV,
                        templateFile);
                String fileName = dataSource.getLabelUpFirst();
                if (!fileNameSuffix.isBlank()) {
                    fileName = fileName + fileNameSuffix;
                }
                TextProcess.writeTextToFile(requestProcess.getResultstAsString(),
                        outputPath + fileName + fileExtension);
                System.out.println(fileName + fileExtension + " generated successfully!");

            } else {
                for (int j = 0; j < dataSources.length; j++) {
                    RequestProcess requestProcess = new RequestProcess(dataSources[j],
                            globV,
                            templateFile);
                    String fileName = dataSources[j].getLabelUpFirst();
                    if (!fileNameSuffix.isBlank()) {
                        fileName = fileName + fileNameSuffix;
                    }
                    TextProcess.writeTextToFile(requestProcess.getResultstAsString(),
                            outputPath + fileName + fileExtension);
                }
                break;
            }
        }
        System.out.println("Generated successfully!!!");

    }

    public static void process() throws Exception {
        DataSource[] dataSources = new SQLDataHandler("jdbc:postgresql://localhost:5432/test", "postgres",
                "post").getDataSources();
        RequestProcess requestProcess = new RequestProcess(dataSources[0],
                "C:\\Users\\MISA\\Desktop\\Workspace\\S6\\Mr-Naina\\Genesis\\configuration\\GLOBAL_VARIABLE",
                "C:\\Users\\MISA\\Desktop\\Workspace\\S6\\Mr-Naina\\Genesis\\test\\SpringModel");

        String fileExtension = ".java";
        String outputPath = "C:\\Users\\MISA\\Desktop\\Workspace\\S6\\Mr-Naina\\Genesis\\test\\";
        String text = requestProcess.getResultstAsString();
        String fileName = dataSources[0].getLabelUpFirst();

        TextProcess.writeTextToFile(text, outputPath + fileName + fileExtension);

    }

    private static String search(Tmpl tmpl, String[] toSearch, String templateName) throws Exception {
        Children c = tmpl.search(toSearch);
        try {
            return c.BFS(templateName).getValue();
        } catch (Exception e) {
            while (templateName.contains("-")) {
                templateName = templateName.substring(0, templateName.lastIndexOf("-"));
                try {
                    // return c.BFS(templateName).getValue().trim();
                    return c.BFS(templateName).getValue();

                } catch (Exception e1) {
                }
            }
        }
        return c.BFS("All").getValue();
    }
}
