package bin.Tables;

import bin.Enumirations.IndicesTables;
import bin.Enumirations.TypesData;
import bin.Items.Token;

import java.util.Arrays;
import java.util.List;

public class TableServiceWords implements Tables<Token> {
    private final static Token[] serviceWords= {
            new Token("end",IndicesTables.SERVICE_WORD,0),
            new Token("let",IndicesTables.SERVICE_WORD,1),
            new Token("int", TypesData.INT,IndicesTables.SERVICE_WORD,2),
            new Token("real",TypesData.REAL,IndicesTables.SERVICE_WORD,3),
            new Token("bool",TypesData.BOOL,IndicesTables.SERVICE_WORD,4),
            new Token("true",TypesData.BOOL,IndicesTables.SERVICE_WORD,5),
            new Token("false",TypesData.BOOL,IndicesTables.SERVICE_WORD,6),
            new Token("if",IndicesTables.SERVICE_WORD,7),
            new Token("then",IndicesTables.SERVICE_WORD,8),
            new Token("else",IndicesTables.SERVICE_WORD,9),
            new Token("for",IndicesTables.SERVICE_WORD,10),
            new Token("end_else",IndicesTables.SERVICE_WORD,11),
            new Token("do while",IndicesTables.SERVICE_WORD,12),
            new Token("loop",IndicesTables.SERVICE_WORD,13),
            new Token("input",IndicesTables.SERVICE_WORD,14),
            new Token("output",IndicesTables.SERVICE_WORD,15)

    };

    public static Token[] getServiceWords() {
        return serviceWords;
    }

    @Override
    public boolean contains(String word){
        for (int i = 0; i < serviceWords.length; i++) {
            if (serviceWords[i].getItem().equals(word)){
                return true;
            }
        }
        return false;
    }

    @Override
    public  List<Token> getListElements(){
        return Arrays.asList(serviceWords);
    }

    @Override
    public Token getElement(String word) {
        if (!contains(word)){
            try {
                throw new Exception("Данного значения:" +word+" нет в таблице сервисных слов");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < serviceWords.length; i++) {
            if (serviceWords[i].getItem().equals(word)){
                return serviceWords[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Token token: serviceWords) {
            stringBuilder.append(token.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}

