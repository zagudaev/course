package bin.analyzers;


import bin.Items.Index;
import bin.Items.Numeric;
import bin.Items.Token;
import bin.Tables.TotalInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LexicalAnalyzer {

    private static boolean comment;

    public static TotalInformation scanner(String program) throws Exception {
        ArrayList<String> words = separator(program);

        TotalInformation totalInformation = new TotalInformation();
        ArrayList<Token> listToken = new ArrayList();
        for (String word: words) {
            if (totalInformation.getTableServiceWords().contains(word))
                listToken.add(totalInformation.getTableServiceWords().getElement(word));
            else if (totalInformation.getTableLimiters().contains(word))
                listToken.add(totalInformation.getTableLimiters().getElement(word));
            else if (totalInformation.getTableNumbers().setNumber(word))
                listToken.add(totalInformation.getTableNumbers().getElement(word));
            else if (totalInformation.getTableIndices().setIndex(word))
                listToken.add(totalInformation.getTableIndices().getElement(word));
            else throw new Exception(word + " - не является лексемой данного языка");
        }
        listToken.forEach(token -> {
            System.out.println(token.toString());
        });
        totalInformation.setTokenList(listToken);
        return totalInformation;
    }

    private static ArrayList<String> separator(String program) throws Exception {
        program = program.replaceAll("\t","");
        program = program.replaceAll("\r","");

        String delims = ":;,{}()<>/*+-=\n";
        String bufStr;
        comment = false;

        StringTokenizer stringTokenizer;
        String[] bufStrList = program.split(" ");
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < bufStrList.length; i++) {
            //bufStrList[i] = bufStrList[i].trim();
            if (bufStrList[i].length()>0 ){
                stringTokenizer = new StringTokenizer(bufStrList[i],delims,true);
                while (stringTokenizer.hasMoreElements()){
                    bufStr = stringTokenizer.nextToken();
                    if (bufStr.equals("/")){
                        if (!comment) {
                            bufStr = stringTokenizer.nextToken();
                            if (bufStr.equals("*")) {
                                comment = true;
                            } else {
                                strings.add("/");
                            }
                        }
                    }
                    if (bufStr.equals("*")){
                        if (comment) {
                            bufStr = stringTokenizer.nextToken();
                            if (bufStr.equals("/")) {
                                bufStr = bufStr.replace("/","");
                                comment = false;
                            }
                        }
                    }
                    if (!comment){
                        if (bufStr.endsWith("E") | bufStr.endsWith("e")){
                            if (bufStr.matches("^[0-9]+(.*)$")){

                                String s =stringTokenizer.nextToken();
                                if (s.endsWith("+")| s.endsWith("-")){
                                    bufStr += s;
                                    s = stringTokenizer.nextToken();
                                    if (s.matches("^[1-9][0-9]*$")){
                                        bufStr += s;
                                        strings.add(bufStr);
                                    }else {
                                        throw new Exception("После знака +/- должно быть число!");
                                    }
                                }else {
                                    strings.add(bufStr);
                                    strings.add(s);
                                }

                            }
                            else {
                                strings.add(bufStr);
                            }
                        }
                        else if (bufStr.equals("<")){
                            if (stringTokenizer.hasMoreTokens()){
                                bufStr = stringTokenizer.nextToken();
                                if (bufStr.equals("=")) {
                                    strings.add("<=");
                                }else if ( bufStr.equals(">")){
                                    strings.add("<>");
                                }else {
                                    strings.add("<");
                                    strings.add(bufStr);
                                }
                            }else {
                                strings.add(bufStr);
                            }
                        }
                        else if (bufStr.equals(">")){
                            if (stringTokenizer.hasMoreTokens()) {
                                bufStr = stringTokenizer.nextToken();
                                if (bufStr.equals("=")) {
                                    strings.add(">=");
                                } else {
                                    strings.add(">");
                                    strings.add(bufStr);
                                }
                            }else {
                                strings.add(bufStr);
                            }
                        }
                        else if (bufStr.equals("\n")){
                            strings.add(bufStr);
                        }
                        else if (!bufStr.isEmpty()){
                            strings.add(bufStr.trim());
                        }
                    }
                }
            }
        }
        return strings;
    }


}

//"^(.*)[(<>)(=)(<)(<=)(>)(>=)(+)(-)(*)(/)({)(})(()())(:)(;)(,)](.*)$"